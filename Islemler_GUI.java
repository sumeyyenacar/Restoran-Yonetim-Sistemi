

import java.util.*;
import java.text.*;

public class Islemler_GUI
{
    private Kullanici_gui cView;
    private Database    cDatabase;
    private int         userType;
    private int         currentUserID;
    private String      currentUserName;
    private String      todaysDate;
    
    private int         todaysOrderCnt;     //Toplam siparis Mikari icin olusturuldu.
    private double      totalSales;         //Toplam satis Mikari icin olusturuldu.
    private int         todaysCancelCnt;    //Toplam iptal edilen siparis mikyari icin olusturuldu.
    private double      cancelTotal;        //Toplam iptal edilen siparis maliyeti icin olusturuldu.
    
    
    private String      errorMessage;
    
    //define user type
    public final static int USER_ANONYMOUS = 0;
    public final static int USER_EMPLOYEE = 1;
    public final static int USER_MANAGER = 2; 
    
    
    public Islemler_GUI()
    {
        this.cDatabase = new Database();
        try
        {
            cDatabase.loadFiles();
        }
        catch(DatabaseException de)
        {
            System.out.println(de.getErrMessage());
            System.exit(0);
        }
        
        cView = new Kullanici_gui( this);
        
        Date date = new Date();
        SimpleDateFormat stf = new SimpleDateFormat("yyyy/MM/dd");
        todaysDate = stf.format(date);
        cView.setVisible(true);
        cView.setTodaysDate(todaysDate);
        this.userType = USER_ANONYMOUS;
        
        todaysOrderCnt = 0;
        totalSales = 0;
        todaysCancelCnt = 0;
        cancelTotal = 0;
    }
    
    private void  setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }
    
    public String  getErrorMessage()
    {
        String result = this.errorMessage;
        this.errorMessage = "";
        return result;
    }
    
    public int getTodaysOrderCnt()
    {
        return this.todaysOrderCnt;
    }
    
    public int getTodaysCancelCnt()
    {
        return this.todaysCancelCnt;
    }
    
    public double getTotalSales()
    {
        return this.totalSales;
    }
    
    public double getCancelTotal()
    {
        return this.cancelTotal;
    }
    
    public double getOrderTotalCharge(int orderID)
    {
        return cDatabase.getOrderTotalCharge(orderID);
    }
    
    public int getOrderState(int orderID)
    {
        return cDatabase.getOrderState(orderID);
    }
    
    public String getCurrentUserName()
    {
        return this.currentUserName;
    }
    
    public boolean checkIfUserClockedOut()
    {
        Staff   rStaff = cDatabase.findStaffByID(currentUserID);
        
        if( rStaff == null) return false;
        if( rStaff.getWorkState() == Staff.WORKSTATE_ACTIVE)
            return true;
        else 
            return false;
    }
    
     /***********************************************************
     * Login 
     **********************************************************/
    //----------------------------------------------------------
    // Find user
    //----------------------------------------------------------  
    public boolean loginCheck( int inputID, String inputPassword, boolean isManager)
    {
        String searchClassName;
            
        //---------search user----------
        Staff   rStaff = cDatabase.findStaffByID(inputID);

        if(isManager)   searchClassName = "Manager";
        else            searchClassName = "Employee";
        
        if( rStaff != null)//User data is found
        {
            //Search only particular target(Manager or Employee)
            if( rStaff.getClass().getName().equalsIgnoreCase(searchClassName))
            {
                if(rStaff.getPassword().equals(inputPassword))
                {
                    if(rStaff.getWorkState() == 0)  //Not clocked in yet
                    {
                        rStaff.clockIn();
                    }
                    if(isManager)
                    {
                        userType = USER_MANAGER;
                        cView.changeMode(cView.MODE_MANAGER);
                    }
                    else 
                    {
                        userType = USER_EMPLOYEE;
                        cView.changeMode(cView.MODE_EMPLOYEE);
                    }
                    currentUserID = inputID;
                    currentUserName = rStaff.getFullName();
                    cView.setLoginUserName(currentUserName);  //show user name on the view
                    
                    return true; //Login success
                }
                else
                {
                    setErrorMessage("Parola Eslemedi.");
                    //printErrorMessageToView("Password unmatching.");
                    return false;
                }
            }
            else    //ID is found but type(Manager or Employee) is umnatching
            {
                setErrorMessage("Bulunamdi.");
                //printErrorMessageToView("Not found.");
                return false;
            }
        }
        else
        {
            setErrorMessage("Bulunamdi.");
            return false;
        }

    }   
    
    //----------------------------------------------------------
    // Logout (Set state as Anonymous)
    //----------------------------------------------------------
    public void userLogout()
    {
        userType = USER_ANONYMOUS;
        currentUserID = 0;
        cView.setLoginUserName("");
    }
    
     /***********************************************************
     * Staff management
     **********************************************************/
    public boolean addNewStaff(int newID, String newPassword, String newFirstName, String newLastName, boolean isManager)
    {
        Staff rStaff = cDatabase.findStaffByID(newID);
        if(rStaff != null)
        {
            setErrorMessage("ID:" + newID + " zaten kullanýlýyor: " + rStaff.getFullName());
            return false;
        }
        
        try
        {
            cDatabase.addStaff(newID, newPassword, newFirstName, newLastName, isManager);
            return true;
        }
        catch(DatabaseException de)
        {
            setErrorMessage(de.getErrMessage());
            return false;
        }
    }
    
    public boolean updateStaff(int id, String newPassword, String newFirstName, String newLastName)
    {
        try
        {
            cDatabase.editStaffData(id, newPassword, newFirstName, newLastName);
            return true;
        }
        catch(DatabaseException de)
        {
            setErrorMessage(de.getErrMessage());
            return false;
        }
    }
    
    public boolean deleteStaff(int id)
    {
        Staff rStaff = cDatabase.findStaffByID(id);
        if(rStaff == null)
        {
            setErrorMessage("StaffID:" + id + " bulunamadi.");
            return false;
        }
        
        try
        {
            cDatabase.deleteStaff(rStaff);
        }
        catch(DatabaseException de)
        {
            setErrorMessage(de.getErrMessage());
            return false;
        }
        return true;
    }
    
    public Staff    getStaffData(int staffID)
    {
        return cDatabase.findStaffByID(staffID);
    }
    
    public boolean clockOut()
    {
        return clockOut(currentUserID);
    }
    
    public boolean clockOut(int staffID)
    {
        Staff rStaff = cDatabase.findStaffByID(staffID);
        
        byte state = rStaff.getWorkState();
        boolean result = false;
        switch(state)
        {
            case Staff.WORKSTATE_ACTIVE:
                rStaff.clockOut();
                result = true;
                break;
            case Staff.WORKSTATE_FINISH:
                setErrorMessage("Staff:" + rStaff.getFullName() + " zaten is bitimi yapildi.");
                break;
           default:
                setErrorMessage("Staff:" + rStaff.getFullName() + "bugun calismiyor.");
                break;
        }
        
        return result;
    }
    
    public void clockOutAll()
    {
        cDatabase.forthClockOutAllStaff();
    }

     /***********************************************************
     * Menu management
     **********************************************************/
    public boolean addNewMenuItem(int newID, String newName, double newPrice, byte menuType)
    {
        Menu rMenuItem = cDatabase.findMenuItemByID(newID);
        if(rMenuItem != null)
        {
            setErrorMessage("Bu ID:" + newID + " zaten kullaniliyor: " + rMenuItem.getName());
            return false;
        }
        
        try
        {
            cDatabase.addMenuItem(newID, newName, newPrice, menuType);
            return true;
        }
        catch(DatabaseException de)
        {
            setErrorMessage(de.getErrMessage());
            return false;
        }
    }
    
    public boolean updateMenuItem(int id, String newName, double newPrice, byte menuType)
    {
        try
        {
            cDatabase.editMenuItemData(id, newName, newPrice, menuType);
            return true;
        }
        catch(DatabaseException de)
        {
            setErrorMessage(de.getErrMessage());
            return false;
        }
    }
    
    public boolean deleteMenuItem(int id)
    {
        Menu rMenuItem= cDatabase.findMenuItemByID(id);
        if(rMenuItem == null)
        {
            setErrorMessage("Menu Urun ID:" + id + " bulunamadi.");
            return false;
        }
        
        try
        {
            cDatabase.deleteMenuItem(rMenuItem);
        }
        catch(DatabaseException de)
        {
            setErrorMessage(de.getErrMessage());
            return false;
        }
        return true;
    }
     
    public Menu    getMenuItemData(int menuItemID)
    {
        return cDatabase.findMenuItemByID(menuItemID);
    }
     /***********************************************************
     * Order management
     **********************************************************/
    public int createOrder()
    {
        return cDatabase.addOrder(currentUserID, currentUserName);
        //editOrderItem(newOrderID);
    }
    
    public boolean addNewOrderItem(int orderID, int addItemID, byte addItemQuantity)
    {
        Siparis_duzen rOrder = cDatabase.findOrderByID(orderID);
        if( currentUserID != rOrder.getStaffID())
        {
            setErrorMessage("Siparis Yonetme Yetkiniz Yok.\nBu yeki " + rOrder.getStaffName() + "na aittir.");
            //printErrorMessageToView("You are not eligible to edit the order.\nThe order belonges to " + rOrder.getStaffName() + ")");
            return false;    
        }
        
        Menu    rNewItem = null;
        
        rNewItem = cDatabase.findMenuItemByID(addItemID);
        if(rNewItem == null)
        {
            setErrorMessage("MenuID[" + addItemID + "] Bulunamadi.");
            //printErrorMessageToView("MenuID[" + addItemID + "]is not found.");
            addItemID = 0;
            return false;
        }
        
         //////////ADD!!!(database)/////////////
         cDatabase.addOrderItem(orderID, rNewItem, addItemQuantity);
         return true;
    }
    
    public boolean deleteOrderItem(int orderID, int deleteNo)
    {
        Siparis_duzen rOrder = cDatabase.findOrderByID(orderID);
        if( currentUserID != rOrder.getStaffID())
        {
            setErrorMessage("Siparis Yonetme Yetkiniz Yok.\\nBu yeki" + rOrder.getStaffName() + "na aittir");
            //printErrorMessageToView("You are not eligible to delete the order.\nThe order belonges to " + rOrder.getStaffName() + ")");
            return false;    
        }
        
        deleteNo -=1; 
        if(!cDatabase.deleteOrderItem(orderID, deleteNo))
        {
            //printErrorMessageToView("Not found.");
            setErrorMessage("Bulunamadi.");
            return false;
        }
        return true;
    }
    
    public boolean closeOrder(int closeOrderID)
    {
        Siparis_duzen rOrder = cDatabase.findOrderByID(closeOrderID);
        if( currentUserID != rOrder.getStaffID())
        {
            setErrorMessage("Siparis Yonetme Yetkiniz Yok.\\nBu yeki " + rOrder.getStaffName() + " na aittir.");
            return false;    
        }
        
        if(rOrder.getState() != 0)
        {
            setErrorMessage("Bu siparis iptal edildi..");
            return false;
        }
        cDatabase.closeOrder(closeOrderID);
        todaysOrderCnt++;
        totalSales += rOrder.getTotal();
        return true;
    }
    
    public boolean cancelOrder(int cancelOrderID)
    {
        Siparis_duzen rOrder = cDatabase.findOrderByID(cancelOrderID);
        if( currentUserID != rOrder.getStaffID())
        {
            setErrorMessage("Siparis Yonetme Yetkiniz Yok.\\nBu yeki " + rOrder.getStaffName() + "na aittir.");
            return false;    
        }
        
        if( rOrder.getState() != 0)
        {
            setErrorMessage("Bu siparis zaten iptal edildi ya da kapandi..");
            return false;
        }

        cDatabase.cancelOrder(cancelOrderID);
        todaysCancelCnt++;
        cancelTotal += rOrder.getTotal();
        return true;
    }
    
    public void closeAllOrder()
    {
        cDatabase.closeAllOrder();
    }
    
    /***********************************************************
     * Reports
     * return:Report file name
     **********************************************************/
     public String generateSalesReport()
     {
        if(!cDatabase.checkIfAllOrderClosed())
        {
             setErrorMessage("Rapor uretmek icin siparisin iptal edilmis olmasi gerekir.");
             return null;
        }
            
         try
        {
            return cDatabase.generateOrderReport(todaysDate);
        }
        catch(DatabaseException de)
        {
            setErrorMessage(de.getErrMessage());
            return null;
        }
    }
    
    public String generatePaymentReport()
    {
        if(!cDatabase.checkIfAllStaffCheckout())
        {
            setErrorMessage("Rapor olusturmadan once kontrol ediniz");
            return null;
        }

        try
        {
            return cDatabase.generatePaymentReport(todaysDate);
        }
        catch(DatabaseException de)
        {
            setErrorMessage(de.getErrMessage());
            return null;
        }
    }
    
    
    public  ArrayList<String>  createStaffList()
    {
        Iterator<Staff> it = cDatabase.getStaffList().iterator();
        ArrayList<String> initData = new ArrayList<String>();
        
        while (it.hasNext()) {
            Staff re = (Staff)it.next();
            String fullName = re.getFullName();
            String output = String.format("Calisan ID:%4d  Isým:%-25s",
                                            re.getID(), fullName);
            switch(re.getWorkState())
            {
                case Staff.WORKSTATE_ACTIVE:
                    output += "["+re.getStartTime() + "den beri:]";
                break;
                case Staff.WORKSTATE_FINISH:
                    output +=  re.getStartTime() + " den " + re.getFinishTime() + " 'a]";
                break;
                default:
                    output += "[Calismiyor]";
                break;
            }
            
            //if(re.getClass().getName().equalsIgnoreCase("Manager"))
             if(re instanceof Manager)
            {
                output += " * Yonetici";
            }
            initData.add(output);
        }
        
        return initData;
    }
    
    public ArrayList<String>  createOrderList()
    {
        Iterator<Siparis_duzen> it = cDatabase.getOrderList().iterator();
        String          state;
        ArrayList<String> initData = new ArrayList<String>();
        String          output;
        
        while (it.hasNext()) {
            Siparis_duzen re = it.next();
            switch(re.getState())
            {
                case Siparis_duzen.ORDER_CLOSED:
                    state = "Kapandi";
                break;
                case Siparis_duzen.ORDER_CANCELED:
                    state = "Iptal edildi";
                break;
                default:
                    state = "-";
                break;
            }
            
            output = String.format("Siparis ID:%4d  Calisan Adi:%-20s  Toplam:%5.2f ",
                                            re.getOrderID(),re.getStaffName(),re.getTotal(),state);
            initData.add(output);
        }
        if(initData.isEmpty())
            initData.add("Sparis Yok.");
        return initData;
    }
    
    public ArrayList<String> createOrderItemlList(int orderID)
   
    {
        Siparis_duzen rOrder = cDatabase.findOrderByID(orderID);
        ArrayList<String> initData = new ArrayList<String>();

        if(rOrder == null)
        {
            initData.add("Sparis Bilgisi Yok");
            //list.setListData(initData);
            return initData;
        }
        
        String output;

        Iterator<Siparis> it = rOrder.getOrderDetail().iterator();
        Siparis    re;
        
        int count = 0;
        
        while (it.hasNext()) {
            re = it.next();
            output = String.format("%-4d|%-24s|%5d|%5.2f",
                                    ++count, re.getItemName(), re.getQuantity(), re.getTotalPrice());
           initData.add(output);
        }
        if(initData.isEmpty())
            initData.add("Urun Yok");
        //list.setListData(initData);
        return initData;
    }   
    
    public ArrayList<String> createMenuList(int disuplayMenuType)
    {
        Iterator<Menu> it = cDatabase.getMenuList().iterator();
        ArrayList<String> initData = new ArrayList<String>();
        
        while (it.hasNext()) {
            Menu re = (Menu)it.next();
             byte menuType = re.getType();
             
             if(disuplayMenuType!= 0 && disuplayMenuType != menuType)
                continue;
             String strMenuType;
            switch( menuType)
            {
                case Menu.MAIN:
                strMenuType = "Ana Yemek";
                break;
                case Menu.DRINK:
                strMenuType = "Icecekler";
                break;
                
                case Menu.DESSERT:
                strMenuType = "Tatlilar";
                break;
                default:
                strMenuType = "Tanimlanmamis";
                break;
            }
            String output = String.format("Menu ID:%4d  Isim:%-20s  Fiyat:%5.2f Tur:%s",
                                            re.getID(),re.getName(),re.getPrice(),strMenuType);
           if(re.getState() == Menu.PROMOTION_ITEM)
           {
               output += " *Bugunun Spesyeli! *";
            }
            
            initData.add(output);
        }
        if(initData.isEmpty())
            initData.add("Siparis Yok.");
        return initData;
    }
    
    public String createPaymentList()
    {
        double          totalPayment = 0;
        int             staffNum = 0;
        String          output = "";
        
        Iterator<Staff> it = cDatabase.getStaffList().iterator();
        while (it.hasNext())
        {
            Staff re = it.next();

            if(re.getWorkState() == Staff.WORKSTATE_FINISH)
            {
                double pay = re.culculateWages();
                output += String.format("Calisan ID:%4d  Isým:%-20s  Calisma Zamani:%5.2f Odeme:%5.2f\n",
                                            re.getID(),re.getFullName(),re.culculateWorkTime(), pay);
                staffNum++;
                totalPayment += pay;
            }
            else if(re.getWorkState() == Staff.WORKSTATE_ACTIVE)
            {
                output += String.format("Calisan ID:%4d  Isim:%-20s  * Calisiyor *\n",
                                            re.getID(),re.getFullName());
                staffNum++;
            }
        }
        output += "**********************************************************************************************\n\n";
        output += String.format("Toplam Odeme: %.2f (%d)", totalPayment, staffNum);
        return output;
        
    }
}
