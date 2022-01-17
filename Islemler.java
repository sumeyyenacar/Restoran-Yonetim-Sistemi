
import java.util.*;
import java.io.*;
import java.text.*;
 
public class Islemler
{
    
    public final static int SCENE_MAIN_MENU = 0;   
    public final static int SCENE_LOGIN = 1;        
    public final static int SCENE_LOGOUT = 2;
    public final static int SCENE_MENU_LIST = 3;
    public final static int SCENE_ORDER = 4;
    public final static int SCENE_EMPLOYEE_LIST = 5;
    public final static int SCENE_EDIT_EMPLOYEE = 6;
    public final static int SCENE_EDIT_MENU_ITEM = 7;
    public final static int SCENE_GENERATE_REPORTS = 8;
    
    
    public final static int USER_ANONYMOUS = 0;
    public final static int USER_EMPLOYEE = 1;
    public final static int USER_MANAGER = 2; 

    private Kuulanici cView; 
    private Database    cDatabase;
    
  
    private int scene;
    private int state;  
    private int userType;
    private int currentUserID;
    private String currentUserName;
    private String    todaysDate;
    
    public Islemler()
    {
        this.cDatabase = new Database();
        this.cView = new Kuulanici(this.cDatabase);
        this.scene = SCENE_MAIN_MENU;
        this.userType = USER_ANONYMOUS;
        this.currentUserID = 0;
        this.currentUserName = "";
        
       
        Date date = new Date();
        SimpleDateFormat stf = new SimpleDateFormat("yyyy/MM/dd");
        todaysDate = stf.format(date);
        cView.setTodaysDate(todaysDate);
        
        try
        {
            cDatabase.loadFiles();
            this.state = 0;
        }
        catch(DatabaseException de)
        {
            this.state = -1;
            printErrorMessageToView(de.getErrMessage());
        }
    }
    
   
 
   
   
    
   
}
