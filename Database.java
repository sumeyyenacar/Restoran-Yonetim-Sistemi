
import java.util.*;

import java.io.*;
import java.lang.*;
import java.util.Comparator;

public class Database
{
    private final static String STAFF_FILE = "dataFiles/staff.txt";
    private final static String MANAGER_FILE = "dataFiles/manager.txt";
    private final static String MENU_FILE = "dataFiles/menu_item.txt";
    private final static String REPORT_FILE = "dataFiles/reports/report_";
    private final static String PAYMENT_FILE = "dataFiles/reports/payment_";
    private final static String WAGE_INFO_FILE = "dataFiles/wage_info.txt";
    
    private ArrayList<Staff> staffList = new ArrayList<Staff>();
    private ArrayList<Menu> menuList = new ArrayList<Menu>();
    private ArrayList<Siparis_duzen> orderList = new ArrayList<Siparis_duzen>();
    
    private Date    date;
    int     todaysOrderCounts;
    
    public Database()
    {
        date = new Date();
        todaysOrderCounts = 0; 
    }
    
     public ArrayList<Staff> getStaffList()
     {
         return staffList;
     }
     
     public ArrayList<Menu> getMenuList()
     {
         return menuList;
     }
     
     public ArrayList<Siparis_duzen> getOrderList()
     {
         return orderList;
     }
     
     public int getTodaysOrderCount()
     {
         return this.todaysOrderCounts;
     }
     
    
    public Staff   findStaffByID(int id)
    {
        Iterator<Staff> it = staffList.iterator();
        Staff           re = null;
        boolean         found = false;
        
        if(id < 0){
            return null;
        }
        
        while (it.hasNext() && !found) {
            re = (Staff)it.next();  
            if( re.getID() == id)
            {
                found = true;
            }
        }
        
        if(found)
            return re;
        else        
            return null;
    }
    
   
    public Menu   findMenuItemByID(int id)
    {
        Iterator<Menu> it = menuList.iterator();
        Menu           re = null;
        boolean         found = false;
        
        if(id < 0){
            return null;
        }
        
        while (it.hasNext() && !found) {
            re = (Menu)it.next();  
            if( re.getID() == id)
            {
                found = true;
            }
        }
        
        if(found)
            return re;
        else        
            return null;
    }
  
    public Siparis_duzen   findOrderByID(int id)
    {
        Iterator<Siparis_duzen> it = orderList.iterator();
        Siparis_duzen           re = null;
        boolean         found = false;
        
        if(id < 0){
            return null;
        }
        
        while (it.hasNext() && !found) {
            re = it.next();  
            if( re.getOrderID() == id)
            {
                found = true;
            }
        }
        
        if(found)
            return re;
        else        
            return null;
    }
     
     public final static int EDIT_LAST_NAME = 1;
     public final static int EDIT_FIRST_NAME = 2;
     public final static int EDIT_PASSWORD = 3;
     
     public void editStaffData(int staffID, String newPassword, String newFirstName, String newLastName) throws DatabaseException
     {
        Staff  rStaff = findStaffByID(staffID);
        rStaff.setPassword(newPassword);
        rStaff.setLastName(newLastName);
        rStaff.setFirstName(newFirstName);
    
        try
        {
            if(rStaff instanceof Manager)
           
            updateStaffFile(true);//Yonetici guncelle.
            else
            updateStaffFile(false);//Calisan guncelle.
        }
        catch(DatabaseException dbe)
        {
            throw dbe;
        }
    }
     
     public void editStaffData(Staff rStaff, int which, String newData) throws DatabaseException
     {
         switch(which)
         {
             case EDIT_LAST_NAME:
                rStaff.setLastName(newData);
             break;
             case EDIT_FIRST_NAME:
                rStaff.setFirstName(newData);
             break;
             case EDIT_PASSWORD:
                rStaff.setPassword(newData);
             break;
             default:
             break;
         }
         
         try
         {
             if(rStaff instanceof Manager)
           
                updateStaffFile(true);
             else
                updateStaffFile(false);
         }
         catch(DatabaseException dbe)
         {
             throw dbe;
         }
     }
     
     public void deleteStaff(Staff rStaff) throws DatabaseException
     {
         boolean isManager = false;
         staffList.remove(rStaff);
        
        if(rStaff instanceof Manager)
         isManager = true;
        try
        {
            updateStaffFile(isManager);
        }
        catch(DatabaseException dbe)
        {
            throw dbe;
        }
     }
     
     
     public void addStaff(int newID, String newPassward, String newFirstName, String newLastName, boolean isManager) throws DatabaseException
     {
         Staff newStaff;
         if(isManager)
            newStaff = new Manager(newID, newLastName, newFirstName, newPassward);
         else
            newStaff = new Employee(newID, newLastName, newFirstName, newPassward);
         staffList.add(newStaff);
         if(newStaff instanceof Manager)
        
            isManager = true;
        try
        {
            updateStaffFile(isManager);
        }
        catch(DatabaseException dbe)
        {
            throw dbe;
        }
     }

     public final static int EDIT_ITEM_NAME = 1;
     public final static int EDIT_ITEM_PRICE = 2;
     public final static int EDIT_ITEM_TYPE = 3;
     
     public void editMenuItemData(int id, String newName, double newPrice, byte menuType) throws DatabaseException
     {
         Menu rMenuItem = findMenuItemByID(id);
         rMenuItem.setName(newName);
         rMenuItem.setPrice(newPrice);
         rMenuItem.setType(menuType);
         
       
     }
     
     public void editMenuItemData(Menu rMenuItem, int which, String newData) throws DatabaseException
     {
         try
         {
             switch(which)
             {
                 case EDIT_ITEM_NAME:
                    rMenuItem.setName(newData);
                    break;
                 case EDIT_ITEM_PRICE:
                    double newPrice = Double.parseDouble(newData);
                    if(newPrice < 0)
                        throw new DatabaseException("Fiyat pozitif olmalidir");
                    else
                        rMenuItem.setPrice(newPrice);
                    break;
                 case EDIT_ITEM_TYPE:
                    byte newType = Byte.parseByte(newData);
                    if(newType < Menu.MAIN || Menu.DESSERT < newType)
                        throw new DatabaseException("Sadece su aralikta olabilir. " + Menu.MAIN
                                             + Menu.DESSERT + ")");
                    else
                        rMenuItem.setType(Byte.parseByte(newData));
                    break;
                 default:
                    break;
             }
        }
        catch(DatabaseException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new DatabaseException(e.getMessage());
        }
     }
     
     public void setMenuItemAsPromotionItem(Menu rMenuItem, double price)
     {
         rMenuItem.setState(Menu.PROMOTION_ITEM, price);
     }
     
     public void resetMenuState(Menu rMenuItem)
     {
         rMenuItem.resetState();
     }
     
     public void deleteMenuItem(Menu rMenuItem) throws DatabaseException
     {
         menuList.remove(rMenuItem);
         
     }
     
     public void addMenuItem(int newID, String newName, double newPrice, byte newType) throws DatabaseException
     {
         Menu newMenuItem = new Menu(newID, newName,newPrice, newType);
         menuList.add(newMenuItem);
         Collections.sort(menuList, new MenuItemComparator());
         
     }
     
     public int addOrder(int staffID, String staffName)//Siparisler alinir.
     {
         int newOrderID = ++todaysOrderCounts;
         Siparis_duzen newOrder = new Siparis_duzen(staffID, staffName);
         newOrder.setOrderID( newOrderID);
         orderList.add(newOrder);
         return newOrderID;
     }
     
     public void addOrderItem(int orderID, Menu rItem, byte quantity)
     {
         Siparis_duzen rOrder = findOrderByID(orderID);
         rOrder.addItem(rItem, quantity);
     }
     
     public boolean deleteOrderItem(int orderID, int index)
     {
          Siparis_duzen rOrder = findOrderByID(orderID);
          if(rOrder == null)
            return false;
          return rOrder.deleteItem(index);
     }
     
     
     
     public boolean cancelOrder(int orderID)
     {
         Siparis_duzen rOrder = findOrderByID(orderID);
        if(rOrder == null)
            return false;
         rOrder.setState(Siparis_duzen.ORDER_CANCELED);
         return true;
     }
     
     public boolean deleteOrder(int orderID) // Verilerin silinmesi saglanir.
     {
         Siparis_duzen rOrder = findOrderByID(orderID);
        if(rOrder == null)
            return false;
         orderList.remove(rOrder);
         todaysOrderCounts--;
         return true;
     }
     
     public boolean closeOrder(int orderID)
     {
         Siparis_duzen rOrder = findOrderByID(orderID);
        if(rOrder == null)
            return false;
         rOrder.setState(Siparis_duzen.ORDER_CLOSED);
         return true;
     }
     
     public void closeAllOrder()
     {
        Iterator<Siparis_duzen> it = orderList.iterator();
        Siparis_duzen           re = null;
        
        while (it.hasNext()) {
            re = it.next();  
            if( re.getState() == 0)
            {
                re.setState(Siparis_duzen.ORDER_CLOSED);
            }
        }
     }
     
     public int getOrderState(int orderID)
     {
         Siparis_duzen  re = findOrderByID(orderID);
         if(re == null)
             return -1;
         return re.getState();
     }
     
     public double getOrderTotalCharge(int orderID)
     {
         Siparis_duzen  re = findOrderByID(orderID);
         if(re == null)
             return -1;
         return re.getTotal();
     }
     
     public boolean checkIfAllOrderClosed()
     {
        Iterator<Siparis_duzen> it = orderList.iterator();
        Siparis_duzen           re = null;
        
        while (it.hasNext()) {
            re = it.next();  
            if( re.getState() == 0)
            {
                return false;
            }
        }
        return true;
     }
     
     public boolean checkIfAllStaffCheckout()
     {
        Iterator<Staff> it = staffList.iterator();
        Staff           re = null;
        
        while (it.hasNext()) {
            re = it.next();  
            if( re.getWorkState() == Staff.WORKSTATE_ACTIVE)
            {
                return false;
            }
        }
        return true;
     }
     
     public void forthClockOutAllStaff()
     {
         Iterator<Staff> it = staffList.iterator();
         Staff           re = null;
        
         while (it.hasNext()) {
            re = it.next();  
            if( re.getWorkState() == Staff.WORKSTATE_ACTIVE)
            {
                re.clockOut();
            }
         }
     }
     
    public void loadFiles() throws DatabaseException
    {
        loadStaffFile();
        loadManagerFile();
        Collections.sort(staffList, new StaffComparator());
        loadMenuFile();
        loadWageInfoFile();
    }
    
    private void loadStaffFile() throws DatabaseException
    {
            try {
            BufferedReader reader = new BufferedReader(new FileReader(STAFF_FILE));
            String line = reader.readLine();

            while (line != null) {
                String[] record = line.split(",");

                String id = record[0].trim();
                String passward = record[1].trim();
                String firstName = record[2].trim();
                String lastName = record[3].trim();

                
                Employee rEmployee = new Employee(Integer.parseInt(id),lastName, firstName, passward);
                staffList.add(rEmployee);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ioe) {
            String message = ioe.getMessage() + ioe.getStackTrace();
            throw new DatabaseException(message);
        }
    }
 
    private void loadManagerFile() throws DatabaseException
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(MANAGER_FILE));
            String line = reader.readLine();

            while (line != null) {
                String[] record = line.split(",");

                String id = record[0].trim();
                String passward = record[1].trim();
                String firstName = record[2].trim();
                String lastName = record[3].trim();

                Manager rManager = new Manager(Integer.parseInt(id),lastName,firstName, passward);
                staffList.add(rManager);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ioe) {
            String message = ioe.getMessage() + ioe.getStackTrace();
            throw new DatabaseException(message);
        }
    }
    
    private void loadMenuFile() throws DatabaseException
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE));
            String line = reader.readLine();

            while (line != null) {
                String[] record = line.split(",");

                String id = record[0].trim();
                String name = record[1].trim();
                String price = record[2].trim();
                String type = record[3].trim();

                
                Menu rMenuItem = new Menu(Integer.parseInt(id), name, Double.parseDouble(price), Byte.parseByte(type));
                menuList.add(rMenuItem);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ioe) {
            String message = ioe.getMessage() + ioe.getStackTrace();
            throw new DatabaseException(message);
        }
    }
    
    private void loadWageInfoFile() throws DatabaseException
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(WAGE_INFO_FILE));
            String line = reader.readLine();

            while (line != null) {
                String[] record = line.split(",");

                String id = record[0].trim();
                String rate = record[1].trim();
                
                double dRate = Double.parseDouble(rate);
                int iId = Integer.parseInt(id);
                
                Staff rStaff = findStaffByID(iId);
                if(rStaff == null)
                {
                    throw new DatabaseException("Maas yukleme hatasi\n Calisan ID:" + iId + " bulunamdi.");
                }
                rStaff.setWageRate(dRate);

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException ioe) {
            String message = ioe.getMessage() + ioe.getStackTrace();
            throw new DatabaseException(message);
        }
        catch(Exception e)
        {
            String message = e.getMessage() + e.getStackTrace();
            throw new DatabaseException(message);
        }
    }
    
    public void updateStaffFile(boolean isManager) throws DatabaseException
    {
        Writer          writer;
        String          id;
        String          line;
        String          fileName;
        String          tempFileName = "dataFiles/temp.txt";
            
        if(isManager)
            fileName =MANAGER_FILE;
        else
            fileName = STAFF_FILE;
        
        Collections.sort(staffList, new StaffComparator());
        File tempFile = new File(tempFileName);
     
        try{
            writer = new BufferedWriter(new FileWriter(tempFile));
            Iterator it = staffList.iterator();
        
            while (it.hasNext())
            {
                Staff re = (Staff)it.next();
            
                
                if(isManager)
                {
                    
                    if(re instanceof Employee)
                    
                    continue;
                }
                else
                {
                   
                    if(re instanceof Manager)
                    continue;
                }
            
                writer.write(re.getID() + "," + re.getPassword() + "," + re.getFirstName() + "," + re.getLastName()+ "\r\n");
            }
            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            String message = e.getMessage() + e.getStackTrace();
            throw new DatabaseException(message);
        }
        
        
        File deleteFile = new File(fileName);
        deleteFile.delete();
        
       
        File newFile = new File(fileName);  
        tempFile.renameTo(newFile);
        
        updateWageFile();
    }
    
    public void updateWageFile() throws DatabaseException
    {
        Writer          writer;
        String          id;
        String          line;
        String          fileName;
        String          tempFileName = "dataFiles/temp.txt";
            
        File tempFile = new File(tempFileName);
     
        try{
            writer = new BufferedWriter(new FileWriter(tempFile));
            Iterator it = staffList.iterator();
        
            while (it.hasNext())
            {
                Staff re = (Staff)it.next();
                writer.write(re.getID() + "," + re.getWageRate() + "\r\n");
            }
            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            String message = e.getMessage() + e.getStackTrace();
            throw new DatabaseException(message);
        }
        
        //delete current file
        File deleteFile = new File(WAGE_INFO_FILE);
        deleteFile.delete();
        
        // renames temporaly file to new file
        File newFile = new File(WAGE_INFO_FILE);  
        tempFile.renameTo(newFile);
    }
    
    public void updateMenuFile() throws DatabaseException
    {
        Writer          writer;
        String          id;
        String          line;
        String          tempFileName = "dataFiles/temp.txt";
        
        //Collections.sort(menuList, new MenuItemComparator());
        File tempFile = new File(tempFileName);
     
        try{
            writer = new BufferedWriter(new FileWriter(tempFile));
            Iterator it = menuList.iterator();
        
            while (it.hasNext())
            {
                Menu re = (Menu)it.next();

                writer.write(re.getID() + "," + re.getName() + "," + re.getPrice() + "," + re.getType()+ "\r\n");
            }
            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            String message = e.getMessage() + e.getStackTrace();
            throw new DatabaseException(message);
        }
        
        //delete current file
        File deleteFile = new File(MENU_FILE);
        deleteFile.delete();
        
        // renames temporaly file to new file
        File newFile = new File(MENU_FILE);  
        tempFile.renameTo(newFile);
    }
    
    public String generateOrderReport( String todaysDate) throws DatabaseException
    {
        Writer          writer = null;
        String          line;
        int             state;
        double          totalAllOrder = 0;
        String          generateFileName;
        File            newFile;
        int             orderCnt = 0;
        int             cancelCnt = 0;
        double          cancelTotal = 0;
        
        String[] record = todaysDate.split("/");
        String today = record[0].trim() + "_" + record[1].trim() + "_" + record[2].trim();
        generateFileName = REPORT_FILE + today + ".txt";
        newFile = new File(generateFileName);
        
        try{
            writer = new BufferedWriter(new FileWriter(newFile));

            line = "*********** Sipariþ Listesi (" + today + ") ***********\r\n";
            writer.write(line);
            
            Iterator<Siparis_duzen> it = orderList.iterator();
            while (it.hasNext())
            {
                Siparis_duzen re = it.next();
                state = re.getState();
                String stateString = "";
                double totalOfEachOrder = re.getTotal();
                switch(state)
                {
                    case Siparis_duzen.ORDER_CLOSED:
                        stateString = "";
                        totalAllOrder += totalOfEachOrder;
                        orderCnt++;
                    break;
                    case Siparis_duzen.ORDER_CANCELED:
                        stateString = "Iptal Edildi";
                        cancelTotal += totalOfEachOrder;
                        cancelCnt++;
                    break;
                    default:
                        stateString = "";
                        totalAllOrder += totalOfEachOrder;
                        orderCnt++;
                    break;
                }
                String output = String.format("Order ID:%4d  StaffName:%-30s  Total:$%-5.2f %s\r\n",
                                            re.getOrderID(),re.getStaffName(),totalOfEachOrder, stateString);
                writer.write(output);
                
                
            }
            writer.write("-------------------------------------------------------\r\n");
            
            writer.write("Toplam Satis" + totalAllOrder + "(" + orderCnt + ")" +
                    "  Canceled:$" + cancelTotal + "(" + cancelCnt + ")\r\n");
            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            String message = e.getMessage() + e.getStackTrace();
            newFile.delete();
            throw new DatabaseException(message);
        }
        return generateFileName;
        //System.out.println("File <" + generateFileName + "> has been generated.");
    }
    
    public String generatePaymentReport( String todaysDate) throws DatabaseException
    {
        Writer          writer = null;
        String          line;
        double          totalPayment = 0;
        String          generateFileName;
        File            newFile;
        int             staffNum = 0;
        
        String[] record = todaysDate.split("/");
        String today = record[0].trim() + "_" + record[1].trim() + "_" + record[2].trim();
        generateFileName = PAYMENT_FILE + today + ".txt";
        newFile = new File(generateFileName);
        
        try{
            writer = new BufferedWriter(new FileWriter(newFile));

            line = "*********** Odeme Listesi (" + today + ") ***********\r\n";
            writer.write(line);
            
            Iterator<Staff> it = staffList.iterator();
            while (it.hasNext())
            {
                Staff re = it.next();
 
                if(re.getWorkState() == Staff.WORKSTATE_FINISH)
                {
                    double pay = re.culculateWages();
                    String output = String.format("Order ID:%4d  StaffName:%-30s  Work time:%-5.2f Pay:%-5.2f\r\n",
                                                re.getID(),re.getFullName(),re.culculateWorkTime(), pay);
                    writer.write(output);
                    staffNum++;
                    totalPayment += pay;
                }
            }
            writer.write("-------------------------------------------------------\r\n");
            
            writer.write("Toplam Odeme" + totalPayment + "(" + staffNum + ")\r\n");
            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            String message = e.getMessage() + e.getStackTrace();
            newFile.delete();
            throw new DatabaseException(message);
        }
        return generateFileName;
    }
    
    private class StaffComparator implements Comparator<Staff> {

        @Override
        public int compare(Staff s1, Staff s2) {
            return s1.getID() < s2.getID() ? -1 : 1;
        }
    }
    
    private class MenuItemComparator implements Comparator<Menu> {

        @Override
        public int compare(Menu m1, Menu m2) {
            return m1.getID() < m2.getID() ? -1 : 1;
        }
    }
}
