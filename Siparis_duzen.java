
import java.util.*;

public class Siparis_duzen
{
    final public static int ORDER_CLOSED = 1;
    final public static int ORDER_CANCELED = 2;
    
    private int orderID;
    private int staffID;
    private String staffName;
    private String date;
    private int state;  
    private double total;
    private ArrayList<Siparis> orderDetailList = new ArrayList<Siparis>();

   
    public Siparis_duzen(int newStaffID, String newStaffName)
    {
        this.orderID =-1;
        this.state = 0;
        this.staffID = newStaffID;
        this.staffName = newStaffName;
        this.total = 0;
    }
    
    int getOrderID()
    {
        return this.orderID;
    }
    int getStaffID()
    {
        return this.staffID;
    }
    String getStaffName()
    {
        return this.staffName;
    }
    int getState()
    {
        return this.state;
    }
    double getTotal()
    {
        return this.total;
    }
    ArrayList<Siparis> getOrderDetail()
    {
        return this.orderDetailList;
    }
    
    
    public void setOrderID(int newID)
    {
        this.orderID = newID;
    }
    
    public void setState(int state)
    {
        this.state = state;
    }
    
    public void addItem(Menu rNewMenuItem, byte quantity)
    {
        Iterator<Siparis> it = orderDetailList.iterator();
        Siparis re;
        
        boolean found = false;
        
        while( it.hasNext() && !found)
        {
            re = it.next();
            if( rNewMenuItem.getID() == re.getItemID())
            {
                found = true;
                re.addQuantity(quantity);
            }
        }
        
        if(!found)
        {
            Siparis detail = new Siparis(rNewMenuItem, quantity);
            orderDetailList.add(detail);
            
        }
        
        calculateTotal();
    }
    
    public boolean deleteItem(int index)
    {
        try
        {
            orderDetailList.remove(index);
            calculateTotal();
            return true;
        }
        catch(Exception e)
        {
            
             return false;
        }
    }
    
    public void  calculateTotal()
    {
        total = 0;
        Siparis re;
         Iterator<Siparis> it = orderDetailList.iterator();
         while (it.hasNext()) {
            re = it.next();
            total += re.getTotalPrice();
        }
    }
}
