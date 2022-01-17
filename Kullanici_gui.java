import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.util.*;

public class Kullanici_gui extends JFrame implements ActionListener
{
    private Container       con;
    private Islemler_GUI  rcController;
    private String          currentUserName;
    
    
    private JMenuBar   menuBar; // Menu icin gerekli degiskenler.
    private JMenu      mnFile;
    private JMenuItem  mntm1, mntm2;
    
    
   
    
    private JPanel         mainPanel; // Ana Panel icin olusturuldu.
    
   
    private JPanel         headPanel; // Ust Panel icin olusturuldu..
    private JLabel         headTitle;
    private JButton        headBtnLogin;
    private JButton        headBtnLogout;
    
    private JPanel         mainBtnsPanel; // Ana butonlar icin olusturuldu..
    private JButton        mainBtnShowMenu;
    private JButton        mainBtnManageOrder;
    
    private JButton        mainBtnManageEmployee; // Yonetim butonlari icin Olusturuldu.
    private JButton        mainBtnManageMenuItem;
    private JButton        mainBtnShowTotalSales;
    private JButton        mainBtnShowPayment;
    
    private JPanel         infoPanel; // Bilgi Paneli icin icin olusturuldu.
    private JLabel         labelLoginUserName;
    private JButton         btnClockOut;
    private JTextArea      taMessage;
    
  
   
    private JPanel         homePanel; // Ana sayfa paneli icin olusturuldu.
    private JLabel         homeImage;
    
    private LoginPanel          cLoginPanel;
    private MenuListPanel       cMenuListPanel;
    private OrderListPanel      cOrderListPanel;
    private OrderDetailPanel    cOrderDetailPanel;
    private EmployeeListPanel   cEmployeeListPanel;
    private EditEmployeePanel   cEditEmployeePanel;
    private MenuManagementPanel       cMenuManagementPanel;
    private EditMenuItemPanel       cEditMenuItemPanel;
    private TotalSalesPanel       cTotalSalesPanel;
    private PaymentPanel        cPaymentPanel;


    private final static int WINDOW_X = 100;
    private final static int WINDOW_Y = 100;
    private final static int WINDOW_WIDTH = 1400;
    private final static int WINDOW_HEIGHT = 800;
    
    public Kullanici_gui(Islemler_GUI rController)
    {
        this.rcController = rController;
        this.con = getContentPane();
        
       
        setTitle("Restoran Yonetim Sistemi");
        setBounds(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        createMasterPanelConpornents();
        currentUserName = "";
        setLoginUserName(currentUserName);
        
        
        homePanel = new JPanel();
        homePanel.setBackground(Color.orange);
        homeImage = new JLabel();
        
        
        int i = new Random().nextInt(4)+1; // Random veri olusturucu.
        homeImage.setHorizontalAlignment(SwingConstants.CENTER);
        homeImage.setVerticalAlignment(SwingConstants.CENTER);
        homeImage.setIcon(new ImageIcon("images/home"  + ".jpg"));
        homePanel.add(homeImage);
        homePanel.setBackground(Color.ORANGE);
        mainPanel.add("Home", homePanel);

        cLoginPanel = new LoginPanel(); // Ana giris ekrani renk ve diger duzenlemeler.
        cLoginPanel.setBackground(Color.orange);
        mainPanel.add("Login", cLoginPanel);
        cMenuListPanel = new MenuListPanel();
        mainPanel.add("MenuList", cMenuListPanel);
        cOrderListPanel = new OrderListPanel();
        cOrderListPanel.setBackground(Color.orange);
        mainPanel.add("OrderList", cOrderListPanel);
        cOrderDetailPanel = new OrderDetailPanel();
        mainPanel.add("OrderDetail", cOrderDetailPanel);
        cOrderDetailPanel.setBackground(Color.orange);
        cEmployeeListPanel = new EmployeeListPanel();
        cEmployeeListPanel.setBackground(Color.orange);
        mainPanel.add("EmployeeList", cEmployeeListPanel);
        cEditEmployeePanel = new EditEmployeePanel();
        cEditEmployeePanel.setBackground(Color.orange);
        mainPanel.add("EditEmployee", cEditEmployeePanel);
        cMenuManagementPanel = new MenuManagementPanel();
        cMenuManagementPanel.setBackground(Color.orange);
        mainPanel.add("MenuManagement", cMenuManagementPanel);
        cEditMenuItemPanel = new EditMenuItemPanel();
        cEditMenuItemPanel.setBackground(Color.orange);
        mainPanel.add("EditMenuItem", cEditMenuItemPanel);
        cTotalSalesPanel = new TotalSalesPanel();
        cTotalSalesPanel.setBackground(Color.orange);
        mainPanel.add("TotalSalesPanel", cTotalSalesPanel);
        cPaymentPanel = new PaymentPanel();
        cPaymentPanel .setBackground(Color.orange);
        mainPanel.add("PaymentPanel", cPaymentPanel);
        
        changeMode(MODE_ANONYMOUS);
    }
    
    private void createMasterPanelConpornents()
    {
                
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        mnFile = new JMenu("Restoran Yonetim Sistemi");
        menuBar.add(mnFile);

        mntm1 = new JMenuItem("[1] Giris Yap");
        mnFile.add(mntm1);
        mntm1.addActionListener(this);
        
        mntm2 = new JMenuItem("[2] Cikis");
        mnFile.add(mntm2);
        mntm2.addActionListener(this);
        
      
        con.setLayout(new BorderLayout()); // Ana paneller olusturuluyor.
        
       
        headPanel = new JPanel();
        headPanel.setBackground(Color.gray);
        headPanel.setLayout(new FlowLayout());
        headTitle = new JLabel("Restorant Yonetim Sistemi");
        headTitle.setForeground(Color.white);
        headTitle.setPreferredSize(new Dimension(500, 30));
        headTitle.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 30));
        headBtnLogin = new JButton("Gris Yap");
        headBtnLogin.setBackground(Color.orange);
        headBtnLogin.addActionListener(this);
        headBtnLogout = new JButton("Cikis Yap");
        headBtnLogout.setBackground(Color.orange);
        headBtnLogout.addActionListener(this);
        headPanel.add(headTitle);
        headPanel.add(headBtnLogin);
        headPanel.add(headBtnLogout);
        con.add(headPanel, BorderLayout.NORTH);
        
        
        mainPanel = new JPanel();
        mainPanel.setOpaque(true);
        mainPanel.setLayout(new CardLayout());
        con.add(mainPanel, BorderLayout.CENTER);
        
        
        mainBtnsPanel = new JPanel();
        mainBtnsPanel.setLayout(new GridLayout(0,2));
        
        mainBtnShowMenu = new JButton("Menuyu Goruntule");
        mainBtnShowMenu.setForeground(Color.WHITE);
        mainBtnShowMenu.addActionListener(this);
        mainBtnsPanel.add(mainBtnShowMenu);
        mainBtnShowMenu.setBackground(Color.GRAY);
    
        
        mainBtnManageOrder = new JButton("Sparis Yonetimi");
        mainBtnManageOrder.setForeground(Color.WHITE);
        mainBtnManageOrder.addActionListener(this);
        mainBtnsPanel.add(mainBtnManageOrder);
        mainBtnManageOrder.setBackground(Color.GRAY);
        
        mainBtnManageEmployee = new JButton("Calisanari Goruntule");
        mainBtnManageEmployee.setForeground(Color.WHITE);
        mainBtnManageEmployee.addActionListener(this);
        mainBtnsPanel.add(mainBtnManageEmployee);
        mainBtnManageEmployee.setBackground(Color.GRAY);
        
        mainBtnManageMenuItem = new JButton("Menu Yonetimi");
        mainBtnManageMenuItem.setForeground(Color.WHITE);
        mainBtnManageMenuItem.addActionListener(this);
        mainBtnsPanel.add(mainBtnManageMenuItem);
        mainBtnManageMenuItem.setBackground(Color.GRAY);
        
        mainBtnShowTotalSales = new JButton("Toplam Satislar");
        mainBtnShowTotalSales.setForeground(Color.WHITE);
        mainBtnShowTotalSales.addActionListener(this);
        mainBtnsPanel.add(mainBtnShowTotalSales);
        mainBtnShowTotalSales.setBackground(Color.GRAY);
        
        mainBtnShowPayment = new JButton("Odemeler ve Raporlar");
        mainBtnShowPayment.setForeground(Color.WHITE);
        mainBtnShowPayment.addActionListener(this);
        mainBtnsPanel.add(mainBtnShowPayment);
        mainBtnShowPayment.setBackground(Color.gray);
        
        con.add(mainBtnsPanel, BorderLayout.WEST);
        
        
        infoPanel = new JPanel();
        infoPanel.setLayout(new FlowLayout());
        labelLoginUserName = new JLabel();
        labelLoginUserName.setPreferredSize(new Dimension(100, 50));
        taMessage = new JTextArea(3,20);
        taMessage.setEditable(false);
        taMessage.setText("Hosgeldiniz!");
        taMessage.setOpaque(true);
        btnClockOut = new JButton("Is Bitimi");
        btnClockOut.setBackground(Color.orange);
        btnClockOut.setEnabled(false);
        btnClockOut.addActionListener(this);
        LineBorder border = new LineBorder(Color.WHITE, 3, true);
        taMessage.setBorder(border);
        taMessage.setBackground(Color.LIGHT_GRAY);
        infoPanel.add(labelLoginUserName);
        infoPanel.add(btnClockOut);
        infoPanel.add(taMessage);
        con.add(infoPanel, BorderLayout.SOUTH);
    }
    

    public void setLoginUserName(String newName)
    {
        currentUserName = newName;
         if(newName == "")
         {
             labelLoginUserName.setText("Lutfen ilk once giris yapin.");
         }
         else
         {
            labelLoginUserName.setText("<html>Login user<br>" + newName + "</html>");
        }
    }

    public final static byte MODE_ANONYMOUS = 0;
    public final static byte MODE_EMPLOYEE = 1;
    public final static byte MODE_MANAGER = 2;
    
    public void changeMode(byte state)
    {
        switch(state)
        {
            case MODE_ANONYMOUS:
                headBtnLogout.setEnabled(false);
                mainBtnShowMenu.setEnabled(false);
                mainBtnManageOrder.setEnabled(false);
                mainBtnManageEmployee.setEnabled(false);
                mainBtnManageMenuItem.setEnabled(false);
                mainBtnShowTotalSales.setEnabled(false);
                mainBtnShowPayment.setEnabled(false);
                break;
            case MODE_EMPLOYEE:
                headBtnLogout.setEnabled(true);
                mainBtnShowMenu.setEnabled(true);
                mainBtnManageOrder.setEnabled(true);
                mainBtnManageEmployee.setEnabled(false);
                mainBtnManageMenuItem.setEnabled(false);
                mainBtnShowTotalSales.setEnabled(false);
                mainBtnShowPayment.setEnabled(false);
                break;
           case MODE_MANAGER:
                headBtnLogout.setEnabled(true);
                mainBtnShowMenu.setEnabled(true);
                mainBtnManageOrder.setEnabled(true);
                mainBtnManageEmployee.setEnabled(true);
                mainBtnManageMenuItem.setEnabled(true);
                mainBtnShowTotalSales.setEnabled(true);
                mainBtnShowPayment.setEnabled(true);
                break;
        }
    }
    
    public void setTodaysDate(String today)
    {
        
    }
    
    void setClockOutButton()
    {
        if(rcController.checkIfUserClockedOut())
            btnClockOut.setEnabled(true);
        else
            btnClockOut.setEnabled(false);
    }
    
    public void displayMessage(String message)
    {
        taMessage.setForeground(Color.BLACK);
        taMessage.setText(message);
    }
    
    public void displayErrorMessage(String message)
    {
        taMessage.setForeground(Color.ORANGE);
        taMessage.setText(message);
    }
    
    
    final static int DIALOG_YES = JOptionPane.YES_OPTION; // Diyalog mesajlar gosterilir.
    final static int DIALOG_NO = JOptionPane.NO_OPTION;
    final static int DIALOG_CANCEL = JOptionPane.CANCEL_OPTION;
    
    public int showYesNoDialog(String title, String message)
    {
        int option = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION, 
        JOptionPane.QUESTION_MESSAGE);
        return option;
    }
    
    public int showYesNoCancelDiaglog(String title, String message)
    {
        int option = JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_CANCEL_OPTION, 
        JOptionPane.QUESTION_MESSAGE);
        return option;
    }
    
    public void showErrorDialog(String title, String message)
    {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    public void showConfirmDialog(String title, String message)
    {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.PLAIN_MESSAGE);
    }
        

    
    
    private int getIDfromString(String stringLine, int length)
    {
        int index = stringLine.indexOf("ID:"); 
        if(index == -1)
        {
            showErrorDialog("Error", "String 'ID:' bulunamadi!");
            return -1;
        }
        
        try
        {
            String strID = stringLine.substring(index + 3, index + 3 + length);
            int id = Integer.parseInt(strID.trim());
            return id;
        }
        catch(Exception e)
        {
            showErrorDialog("Error", "Parse error");
            return -1;
        }
    }
   
    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource() == mntm2)
        {
            System.exit(0);
        }
        else if (ae.getSource() == mainBtnShowMenu)
        {
            
            changeMainPanel("MenuList");
            cMenuListPanel.init();
        }
        else if (ae.getSource() == mainBtnManageOrder)
        {
           
            changeMainPanel("OrderList");
            cOrderListPanel.init();
        }
        else if (ae.getSource() == mainBtnManageEmployee)
        {
            changeMainPanel("EmployeeList");
            cEmployeeListPanel.init();
        }
        else if (ae.getSource() == mainBtnManageMenuItem)
        {
            changeMainPanel("MenuManagement");
            cMenuManagementPanel.init();
        }
        else if (ae.getSource() == mainBtnShowTotalSales)
        {
            changeMainPanel("TotalSalesPanel");
            cTotalSalesPanel.init();
        }
        else if (ae.getSource() == mainBtnShowPayment)
        {
            changeMainPanel("PaymentPanel");
            cPaymentPanel.init();
        }
        else if (ae.getSource() == headBtnLogin || ae.getSource() == mntm1) {
            changeMainPanel("Login");
            cLoginPanel.init();
            displayMessage("Gris Id'niz ve Parolanizi Girin.");
        }
        else if (ae.getSource() == headBtnLogout) {
            if( showYesNoDialog("Logout","Cikis yapmak istediginizden emin misiniz?") == DIALOG_YES)
            {
                rcController.userLogout();
                changeMainPanel("Home");
                changeMode(MODE_ANONYMOUS);
                setClockOutButton();
            }
        }
        else if (ae.getSource() == btnClockOut){
            if( showYesNoDialog("Clock Out","Is bitimi yapmak istedigine emin misin?") == DIALOG_YES)
            {
                rcController.clockOut();
                setClockOutButton();
            }
        }
    }
    
    //---------------------------Giris Ekrani-------------------
    private class LoginPanel extends JPanel implements ActionListener
    {
        private JLabel          lblUserID;
        private JTextField      tfUserID;
        private JLabel          lblPassword;
        private JPasswordField  pwPassword;
        private JCheckBox       chbIsManager;
        private JButton         btnLoginOK;
        public LoginPanel()
        {
            
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();
            lblUserID = new JLabel("Kullanici ID:");
            lblUserID.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 0;
         
            gbLayout.setConstraints(lblUserID, gbc);
            this.add(lblUserID);
            
            tfUserID = new JTextField(20);
            tfUserID.setInputVerifier(new IntegerInputVerifier(0));
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbLayout.setConstraints(tfUserID, gbc);
            this.add(tfUserID);
            
            lblPassword = new JLabel("Parola:");
            lblPassword.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbLayout.setConstraints(lblPassword, gbc);
            this.add(lblPassword);
            
            pwPassword = new JPasswordField(20);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbLayout.setConstraints(pwPassword, gbc);
            this.add(pwPassword);
            
            chbIsManager = new JCheckBox("Yonetici olarak giris yap");
            chbIsManager.setBackground(Color.orange);
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(chbIsManager, gbc);
            this.add(chbIsManager);
            
            btnLoginOK = new JButton("Giris Yap");
            btnLoginOK.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(btnLoginOK, gbc);
            this.add(btnLoginOK);
        }
        
        private void setUserID(String id)
        {
            tfUserID.setText(id);
        }
        
        private void setPassword(String password)
        {
            pwPassword.setText(password);
        }
        
        public void init()
        {
            setUserID("");
            setPassword("");
            tfUserID.setBackground( UIManager.getColor( "TextField.background" ) ); 
        }
         
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnLoginOK)
            {
                
                if (btnLoginOK.getVerifyInputWhenFocusTarget()) { // Verilerin dogrulugu kontrol ediliyor.
                    //Try to get focus
                    btnLoginOK.requestFocusInWindow();
                    if (!btnLoginOK.hasFocus()) {    
                        return;
                    }
                }  
               
                    
                char[] password;
                boolean isManager = chbIsManager.isSelected(); 
                
                byte state = -1;
                
                String inputID = tfUserID.getText();
               
                if(inputID.equals(""))
                {
                    displayErrorMessage("Kullanici ID: ");
                    return;
                }
                
     
                password= pwPassword.getPassword();
                String inputPassword = new String(password);
                if(inputPassword.equals(""))
                {
                    displayErrorMessage("Parolaniz Girin!");
                    return;
                }
                
                if( rcController.loginCheck(Integer.parseInt(inputID), inputPassword, isManager))
                {
                    showConfirmDialog("Message", "Giris Basarili!");
                    displayMessage("Hosgeldiniz, " + currentUserName);
                    tfUserID.setText("");
                    pwPassword.setText("");
                    changeMainPanel("Home");
                    setClockOutButton();
                }
                else
                {
                    displayErrorMessage(rcController.getErrorMessage());
                }
            }
        }
    }
    
    private void  changeMainPanel(String panelName)
    {
        ((CardLayout) mainPanel.getLayout()).show( mainPanel, panelName);
        displayMessage("Main paanel change :" + panelName);
    }
    
    // ----------------------------- MENU-----------------------------      
    private class MenuListPanel extends JPanel implements ActionListener
    {
        private JScrollPane     scrollPanel;
        private JTextArea       displayArea;
        private JPanel          btnPanel;
        private JButton         btnAll;
        private JButton         btnMain;
        private JButton         btnDrink;
       
        private JButton         btnDessert;
        
        public MenuListPanel()
        {
            this.setLayout( new BorderLayout());
            displayArea = new JTextArea();
            displayArea.setBackground(Color.orange);
            displayArea.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            displayArea.setEditable(false);
            displayArea.setMargin(new Insets(5, 5, 5, 5));
            scrollPanel = new JScrollPane(displayArea);
            scrollPanel.setPreferredSize(new Dimension(200, 400));
            add(scrollPanel, BorderLayout.CENTER);
            
           btnPanel = new JPanel();
           btnPanel.setLayout( new FlowLayout());
           btnAll = new JButton("Menü");
           btnAll.addActionListener(this);
           btnMain = new JButton("Ana Yemek");
           btnMain.addActionListener(this);
           btnDrink = new JButton("Ýcecekler");
           btnDrink.addActionListener(this);
          
           btnDessert = new JButton("Tatlilar");
           btnDessert.addActionListener(this);
           
           btnPanel.add(btnAll);
           btnPanel.add(btnMain);
           btnPanel.add(btnDrink);
          
           btnPanel.add(btnDessert);
           
           add(btnPanel, BorderLayout.SOUTH);
        }
    
        public void init()
        {
            showMenuList(0);
            
        }
        
        private void showMenuList(int menuType)
        {
            displayArea.setText("");
            ArrayList<String> menuList = rcController.createMenuList(menuType);
            for(int i = 0; i < menuList.size(); i++)
                displayArea.append(menuList.get(i) + "\n");
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnAll)
            {
                showMenuList(0);
                
            }
            else if (ae.getSource() == btnMain)
            {
                showMenuList(Menu.MAIN);
                
            }
            else if (ae.getSource() == btnDrink)
            {
                showMenuList(Menu.DRINK);
              
            }
  
            else if (ae.getSource() == btnDessert)
            {
                showMenuList(Menu.DESSERT);
                
            }
        }
    }
    
    
    private class MenuManagementPanel extends JPanel implements ActionListener
    {
        private JScrollPane     scrollPanel;
        private JList           displayList;
        private JButton         btnAddNewMenuItem;
        private JButton         btnEditMenuItem;
        private JButton         btnDeleteMenuItem;
        
        public MenuManagementPanel()
        {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();

            scrollPanel = new JScrollPane();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.gridwidth = 3;
            gbLayout.setConstraints(scrollPanel, gbc);
            this.add(scrollPanel);
            
            btnAddNewMenuItem     = new JButton("Yeni Urun Ekle");
            btnAddNewMenuItem.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbc.weightx = 0.5;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbLayout.setConstraints(btnAddNewMenuItem, gbc);
            this.add(btnAddNewMenuItem);
            
            btnEditMenuItem    = new JButton("Urunleri Duzenle");
            btnEditMenuItem.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbLayout.setConstraints(btnEditMenuItem, gbc);
            this.add(btnEditMenuItem);
            
            btnDeleteMenuItem   = new JButton("Urunleri Sil");
            btnDeleteMenuItem.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 1;
            gbLayout.setConstraints(btnDeleteMenuItem, gbc);
            this.add(btnDeleteMenuItem);
            
            displayList = new JList();
            displayList.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            displayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
        
        public void init()
        {
            showMenuList();
        }
        
        private void showMenuList()
        {
            displayList.setListData(rcController.createMenuList(0).toArray());
            scrollPanel.getViewport().setView(displayList);
        }
        
        private int getSelectedMenuID()
        {
            String orderLine = (String)displayList.getSelectedValue();
            if (orderLine == null)
                return -1;
                
            return getIDfromString( orderLine, 4);
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnAddNewMenuItem)
            {
                cEditMenuItemPanel.init(0);
                changeMainPanel("EditMenuItem");
            }
            else if (ae.getSource() == btnEditMenuItem)
            {
                int menuID = getSelectedMenuID();
                if( menuID == -1)    return;
                cEditMenuItemPanel.init(menuID);
                changeMainPanel("EditMenuItem");
            }
            else if (ae.getSource() == btnDeleteMenuItem)
            {
                int deleteMenuID = getSelectedMenuID();
                if( deleteMenuID == -1)    return;
                
                if( showYesNoDialog("", "Urunu silmek istedigine emin misin?") == DIALOG_YES)
                {
                    if(!rcController.deleteMenuItem(deleteMenuID))
                    {
                        showErrorDialog("Error", rcController.getErrorMessage());
                    }
                    else
                    {
                        displayMessage("Urun Silindi.");
                        init();
                    }
                }
            }
        }
    }
    
    // ------------------------URUN EKLEME--------------------------------      
    private class EditMenuItemPanel extends JPanel implements ActionListener
    {
        private JLabel          lblMenuItemID;
        private JTextField      tbMenuItemID;
        private JLabel          lblName;
        private JTextField      tbName;
        private JLabel          lblPrice;
        private JTextField      tbPrice;
        private JLabel          lblType;
        private JComboBox       cbType;
        private JButton         btnOK;
        
        private boolean         isUpdate;
        
        public EditMenuItemPanel()
        {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();
            
            lblMenuItemID = new JLabel("Urun ID:");
            lblMenuItemID.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(lblMenuItemID, gbc);
            this.add(lblMenuItemID);
            
            tbMenuItemID = new JTextField(4);
            tbMenuItemID.setInputVerifier(new IntegerInputVerifier(1,10000));
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbLayout.setConstraints(tbMenuItemID, gbc);
            this.add(tbMenuItemID);
            
            lblName = new JLabel("Urun Adi:");
            lblName.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbLayout.setConstraints(lblName, gbc);
            this.add(lblName);
            
            tbName = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbLayout.setConstraints(tbName, gbc);
            this.add(tbName);
            
            lblPrice = new JLabel("Urun Fiyati:");
            lblPrice.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbLayout.setConstraints(lblPrice, gbc);
            this.add(lblPrice);
            
            tbPrice = new JTextField(10);
            tbPrice.setInputVerifier(new DoubleInputVerifier(1,10000));
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbLayout.setConstraints(tbPrice, gbc);
            this.add(tbPrice);
            
            lblType = new JLabel("Urun Tipi:");
            lblType.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbLayout.setConstraints(lblType, gbc);
            this.add(lblType);
            
            String[] combodata = {"Ana Yemek", "Icecekler", "Tatlilar"};
            cbType = new JComboBox(combodata);
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbLayout.setConstraints(cbType, gbc);
            this.add(cbType);
            
            btnOK = new JButton("OK");
            btnOK.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(btnOK, gbc);
            this.add(btnOK);
        }
        
        private void setMenuID(String id)
        {
            tbMenuItemID.setText(id);
        }
        
        private void setItemName(String name)
        {
            tbName.setText(name);
        }
        
        private void setPrice(String price)
        {
            tbPrice.setText(price);
        }
        
        private void setType(String type)
        {
            cbType.setSelectedItem(type);
        }
        
        
        public void init(int menuItemID)
        {
            //--------------------------URUN EKLE------------------
            if( menuItemID == 0)    
            {
                setMenuID("");
                tbMenuItemID.setEditable(true);
                setItemName("");
                setPrice("");
                setType("Ana Yemek");
                isUpdate = false;
                return;
            }
            
            //-------------URUN DUZENLE ------------
            
            Menu   rMenuItem = rcController.getMenuItemData(menuItemID);
            isUpdate = true;
            
            if( rMenuItem == null)
            {
                showErrorDialog("Error", "Get menu item data failed.");
                setItemName("");
                setPrice("");
                setType("Ana Yemek");
                return;
            }
            setMenuID(Integer.toString(rMenuItem.getID()));
            setItemName(rMenuItem.getName());
            setPrice(Double.toString(rMenuItem.getPrice()));
            tbPrice.setBackground( UIManager.getColor( "TextField.background" ) ); 
            switch( rMenuItem.getType())
            {
                case Menu.MAIN:
                    setType("Ana Yemek");
                break;
                case Menu.DRINK:
                    setType("Icecekler");
                break;
        
                case Menu.DESSERT:
                    setType("Tatlilar");
                break;
            }
            tbMenuItemID.setEditable(false);
            tbMenuItemID.setBackground( UIManager.getColor( "TextField.background" ) ); 
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnOK)
            {
                
                if (btnOK.getVerifyInputWhenFocusTarget()) {
                    //Try to get focus
                    btnOK.requestFocusInWindow();
                    if (!btnOK.hasFocus()) {   
                        return;
                    }
                }  
                
                if( tbMenuItemID.getText().equals("") || tbName.getText().equals("") || tbPrice.getText().equals(""))
                {
                    displayErrorMessage("Tum bosluklari doldurun!");
                    return;
                }

                int menuItemID = Integer.parseInt(tbMenuItemID.getText());
 
                String strMenuType = (String)cbType.getSelectedItem();
                byte    menuType;
                
                if( strMenuType.equals("Ana Yemek"))
                {
                    menuType = Menu.MAIN;
                }
                else if( strMenuType.equals("Icecekler"))
                {
                    menuType = Menu.DRINK;
                }
               
                else    
                {
                    menuType = Menu.DESSERT;
                }
                
                if(isUpdate)
                {
                    if(! rcController.updateMenuItem(menuItemID , tbName.getText(), Double.parseDouble(tbPrice.getText()), menuType))
                    {
                        showErrorDialog("Error", rcController.getErrorMessage());
                        return;
                    }
                    showConfirmDialog("Message", "Guncelleme Basarili!!");
                }
                else
                {                   
                    if(! rcController.addNewMenuItem(menuItemID , tbName.getText(), Double.parseDouble(tbPrice.getText()), menuType))
                    {
                        showErrorDialog("Error", rcController.getErrorMessage());
                        return;
                    }
                    showConfirmDialog("Message", "Yeni Urunler Eklendi!!");
                }
                init(menuItemID);
            }
        }
    }
    
    /****************************************************************
     * Employee list panel
    *****************************************************************/       
    private class EmployeeListPanel extends JPanel implements ActionListener
    {
        private JScrollPane     scrollPanel;
        private JList           displayList;
        //private JPanel          btnPanel;
        private JButton         btnAddStaff;
        private JButton         btnEditStaff;
        private JButton         btnDeleteStaff;
        private JButton         btnClockOut;
        
        public EmployeeListPanel()
        {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();

            scrollPanel = new JScrollPane();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.gridwidth = 4;
            gbLayout.setConstraints(scrollPanel, gbc);
            this.add(scrollPanel);
            
            btnAddStaff     = new JButton("Calisan Ekle");
            btnAddStaff.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbc.weightx = 0.25;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbLayout.setConstraints(btnAddStaff, gbc);
            this.add(btnAddStaff);
            
            btnEditStaff    = new JButton("Calisan Bigi Duzenle");
            btnEditStaff.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbLayout.setConstraints(btnEditStaff, gbc);
            this.add(btnEditStaff);
            
            btnDeleteStaff   = new JButton("Calisan Sil");
            btnDeleteStaff.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 1;
            gbLayout.setConstraints(btnDeleteStaff, gbc);
            this.add(btnDeleteStaff);
            
            btnClockOut  = new JButton("Is Bitir");
            btnClockOut.addActionListener(this);
            gbc.gridx = 3;
            gbc.gridy = 1;
            gbLayout.setConstraints(btnClockOut, gbc);
            this.add(btnClockOut);
            
            displayList = new JList();
            displayList.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            displayList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
        
        public void init()
        {
            showStaffList();
        }
        
        public void showStaffList()
        {
            displayList.setListData(rcController.createStaffList().toArray());
            scrollPanel.getViewport().setView(displayList);
        }
        
        private int getSelectedStaffID()
        {
            String orderLine = (String)displayList.getSelectedValue();
            if (orderLine == null)
                return -1;
                
            return getIDfromString( orderLine, 4);
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnAddStaff)
            {
                cEditEmployeePanel.init(0);
                changeMainPanel("EditEmployee");
            }
            else if (ae.getSource() == btnEditStaff)
            {
                int staffID = getSelectedStaffID();
                if( staffID == -1)    return;
                 cEditEmployeePanel.init(staffID);
                 changeMainPanel("EditEmployee");
            }
            else if (ae.getSource() == btnDeleteStaff)
            {
                int deleteStaffID = getSelectedStaffID();
                if( deleteStaffID == -1)    return;
                
                if( showYesNoDialog("", "Calisani silmek istediginizden emin msiniz") == DIALOG_YES)
                {
                    if(!rcController.deleteStaff(deleteStaffID))
                    {
                        showErrorDialog("Error", rcController.getErrorMessage());
                    }
                    else
                    {
                        displayMessage("Silindi.");
                        init();
                    }
                }
            }
            else if (ae.getSource() == btnClockOut)
            {
                int staffID = getSelectedStaffID();
                if( staffID == -1)    return;
                if(showYesNoDialog("", "Isi bitirmek istediginizden emin misiniz?") == DIALOG_NO)
                    return;
                if( rcController.clockOut(staffID) == false)
                    showErrorDialog("Error", rcController.getErrorMessage());
                else
                {
                    displayMessage("Is sonu Yapildi.");
                    init();
                }
            }
        }
    }
    
    // -------------------------- CALISAN----------------------------- 
    private class EditEmployeePanel extends JPanel implements ActionListener
    {
        private JLabel          lblStaffID;
        private JTextField      tbStaffID;
        private JLabel          lblFirstName;
        private JTextField      tbFirstName;
        private JLabel          lblLastName;
        private JTextField      tbLastName;
        private JLabel          lblPassword;
        private JPasswordField  tbPassword;
        private JButton         btnOK;
        
        private boolean         isUpdate;
        
        public EditEmployeePanel()
        {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();
            
            lblStaffID = new JLabel("Calisan ID:");
            lblStaffID.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbLayout.setConstraints(lblStaffID, gbc);
            this.add(lblStaffID);
            
            tbStaffID = new JTextField(4);
            tbStaffID.setInputVerifier(new IntegerInputVerifier(1,10000));
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbLayout.setConstraints(tbStaffID, gbc);
            this.add(tbStaffID);
            
            lblFirstName = new JLabel("Isim:");
            lblFirstName.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbLayout.setConstraints(lblFirstName, gbc);
            this.add(lblFirstName);
            
            tbFirstName = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbLayout.setConstraints(tbFirstName, gbc);
            this.add(tbFirstName);
            
            lblLastName = new JLabel("Soy isim:");
            lblLastName.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbLayout.setConstraints(lblLastName, gbc);
            this.add(lblLastName);
            
            tbLastName = new JTextField(20);
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbLayout.setConstraints(tbLastName, gbc);
            this.add(tbLastName);
            
            lblPassword = new JLabel("Password:");
            lblPassword.setPreferredSize(new Dimension(100, 30));
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbLayout.setConstraints(lblPassword, gbc);
            this.add(lblPassword);
            
            tbPassword = new JPasswordField(20);
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbLayout.setConstraints(tbPassword, gbc);
            this.add(tbPassword);
            
            btnOK = new JButton("OK");
            btnOK.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(btnOK, gbc);
            this.add(btnOK);
        }
        
        private void setUserID(int id)
        {
            tbStaffID.setText(Integer.toString(id));
        }
        
        private void setPassword(String password)
        {
            tbPassword.setText(password);
        }
        
        private void setLastName(String lastName)
        {
            tbLastName.setText(lastName);
        }
        
        private void setFirstName(String firstName)
        {
            tbFirstName.setText(firstName);
        }
        
        public void init(int employeeID)
        {
            //------------- Yeni Calisan ------------
            if( employeeID == 0)    
            {
                setUserID(0);
                tbStaffID.setEditable(true);
                setPassword("");
                setLastName("");
                setFirstName("");
                isUpdate = false;
                return;
            }
            
            //------------- CALÝSAN BÝLGÝ DUZENLE ------------
            
            Staff   rStaff = rcController.getStaffData(employeeID);
            isUpdate = true;
            
            if( rStaff == null)
            {
                showErrorDialog("Error", "Guncellenemedi!");
                setLastName("");
                setFirstName("");
                return;
            }
            setUserID(rStaff.getID());
            setPassword(rStaff.getPassword());
            setLastName(rStaff.getLastName());
            setFirstName(rStaff.getFirstName());
            tbStaffID.setEditable(false);
            tbStaffID.setBackground( UIManager.getColor( "TextField.background" ) ); 
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnOK)
            {
               
                if (btnOK.getVerifyInputWhenFocusTarget()) {
                    
                    btnOK.requestFocusInWindow();
                    if (!btnOK.hasFocus()) {    
                        return;
                    }
                }  
                
                
                int test = tbPassword.getPassword().length;
                
                if(tbPassword.getPassword().length == 0 || tbFirstName.getText().equals("") || tbLastName.getText().equals(""))
                {
                    displayErrorMessage("Tum Bosluklari Doldurun!");
                    return;
                }
                
                int staffID = Integer.parseInt(tbStaffID.getText());
                
                if(isUpdate)
                {
                    if(! rcController.updateStaff(staffID , new String(tbPassword.getPassword()), tbFirstName.getText(), tbLastName.getText())) 
                    {
                        showErrorDialog("Error", rcController.getErrorMessage());
                        return;
                    }
                    showConfirmDialog("Message", "Guncelleme Basarili!!");
                }
                else
                {
                    boolean isManager = false;
                    
                    if( showYesNoDialog("", "Yonetici mi eklemek istiyorsunuz?") == DIALOG_YES)
                        isManager = true;
                    
                    if(!rcController.addNewStaff(staffID,
                                                new String(tbPassword.getPassword()),
                                                tbFirstName.getText(),
                                                tbLastName.getText(),
                                                isManager))
                    {
                        showErrorDialog("Error", rcController.getErrorMessage());
                        return;
                    }
                    showConfirmDialog("Message", "Eklendi!!");
                    
                }
                init(staffID);
            }
        }
    }
    
    // --------------------------- SIPARIS -----------------------------  
    private class OrderListPanel extends JPanel implements ActionListener
    {
        private JScrollPane     scrollPanel;
        //private JTextArea       displayArea;
        private JPanel          btnPanel;
        private JButton         btnNewOrder;
        private JButton         btnEditOrder;
        private JButton         btnCloseOrder;
        private JButton         btnCancelOrder;
        private JLabel          lblTotalSales;
        private JLabel          lblTotalCount;
        private JLabel          lblCancelTotal;
        private JLabel          lblCancelCount;
        private JList           displayList;
        
        public OrderListPanel()
        {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();
            
            scrollPanel = new JScrollPane();
            scrollPanel.setPreferredSize(new Dimension(500, 300));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 4;
            gbLayout.setConstraints(scrollPanel, gbc);
            this.add(scrollPanel);
            
            lblTotalCount = new JLabel();
            lblTotalCount.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            gbc.insets = new Insets(10, 10, 10, 10);
            gbLayout.setConstraints(lblTotalCount, gbc);
            this.add(lblTotalCount);
            
            lblTotalSales = new JLabel();
            lblTotalSales.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            gbc.gridx = 2;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(lblTotalSales, gbc);
            this.add(lblTotalSales);
            
            lblCancelCount = new JLabel();
            lblCancelCount.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(lblCancelCount, gbc);
            this.add(lblCancelCount);
            
            lblCancelTotal = new JLabel();
            lblCancelTotal.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            gbc.gridx = 2;
            gbc.gridy = 2;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(lblCancelTotal, gbc);
            this.add(lblCancelTotal);
            
            btnNewOrder     = new JButton("Yeni");
            btnNewOrder.addActionListener(this);
            gbc.gridx = 3;
            gbc.gridy = 3;
            gbc.gridwidth = 1;
            gbc.weightx = 0.25;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbLayout.setConstraints(btnNewOrder, gbc);
            this.add(btnNewOrder);
            
            btnEditOrder    = new JButton("Duzenle");
            btnEditOrder.addActionListener(this);
            gbc.gridx = 3;
            gbc.gridy = 4;
            gbLayout.setConstraints(btnEditOrder, gbc);
            this.add(btnEditOrder);
            
            btnCloseOrder   = new JButton("Kapa");
            btnCloseOrder.addActionListener(this);
            gbc.gridx = 4;
            gbc.gridy = 4;
            gbLayout.setConstraints(btnCloseOrder, gbc);
            this.add(btnCloseOrder);
            
            btnCancelOrder  = new JButton("Iptal");
            btnCancelOrder.addActionListener(this);
            gbc.gridx = 5;
            gbc.gridy = 4;
            gbLayout.setConstraints(btnCancelOrder, gbc);
            this.add(btnCancelOrder);
            
            displayList = new JList();
        }
        
        private void setTotalCount( int count)
        {
            lblTotalCount.setText("Bugunun Siparisleri: " + count);
        }
        
        private void setTotalSales( double sales)
        {
            lblTotalSales.setText("Toplam: " + sales);
        }
        
        private void setCancelCount( int count)
        {
            lblCancelCount.setText("Ýptal Edilmis Siparisler: " + count);
        }
        
        private void setCancelTotal( double sales)
        {
            lblCancelTotal.setText("Toplam Iptal Edilen: " + sales);
        }
        
        private void showOrderList()
        {
            displayList.setListData(rcController.createOrderList().toArray());
            scrollPanel.getViewport().setView(displayList);
            
            setTotalCount(rcController.getTodaysOrderCnt());
            setTotalSales(rcController.getTotalSales());
            setCancelCount(rcController.getTodaysCancelCnt());
            setCancelTotal(rcController.getCancelTotal());
            
        }
        
        public void init()
        {
            showOrderList();
        }
        
        private int getSelectedOrderID()
        {
            String orderLine = (String)displayList.getSelectedValue();
            if (orderLine == null)
                return -1;
                
            return getIDfromString( orderLine, 4);
        }
        
        private String getSelectedOrderStaffName()
        {
            String stringLine = (String)displayList.getSelectedValue();
            if (stringLine == null)
                return null;
                
            int index = stringLine.indexOf("Isim:"); 
            if(index == -1)
            {
                showErrorDialog("Error", "String 'Isim::' bulunamadi!");
                return null;
            }
            

            String staffName = stringLine.substring(index + 5, index + 5 + 22);
            return staffName.trim();
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnNewOrder)
            {
                
                changeMainPanel("OrderDetail");
                int orderID = rcController.createOrder();
                String staffName = rcController.getCurrentUserName();
                cOrderDetailPanel.init(orderID, staffName);
               
            }
            else if (ae.getSource() == btnEditOrder)
            {
                int orderID = getSelectedOrderID();
                String staffName = getSelectedOrderStaffName();
                if(orderID == -1) return;
                    
                ((CardLayout) mainPanel.getLayout()).show( mainPanel, "OrderDetail");
               
                cOrderDetailPanel.init(orderID, staffName);
            }
            else if (ae.getSource() == btnCloseOrder)
            {
                int orderID = getSelectedOrderID();
                if(orderID == -1) return;
                
                if( showYesNoDialog("Sparisi Kapa","Siparisi kapamak istediginiz emin misiniz?") == DIALOG_YES)
                {
                    if( !rcController.closeOrder(orderID))
                        displayErrorMessage(rcController.getErrorMessage());
                    showOrderList();
                }
            }
            else if (ae.getSource() == btnCancelOrder)
            {
                int orderID = getSelectedOrderID();
                if(orderID == -1) return;
                
                if( showYesNoDialog("Siparsi Kapa","Siparisi kapamak istediginiz emin misiniz?") == DIALOG_YES)
                {
                    if(!rcController.cancelOrder(orderID))
                        displayErrorMessage(rcController.getErrorMessage());
                    showOrderList();
                }
            }
        }
    }
    
          
    private class OrderDetailPanel extends JPanel implements ActionListener, ListSelectionListener
    {
       
        private JLabel          lblRightTitle;// SAÐ TARAFÝ
       
        private JScrollPane     menuScrollPanel;
        private JButton         btnAll;
        private JButton         btnMain;
        private JButton         btnDrink;
      
        private JButton         btnDessert;
        
       
        private JLabel          lblLeftTitle;
        private JLabel          lblLeftInfo;
        private JScrollPane     orderScrollPanel;
        
        private JPanel          btnPanel; // Metin bolgesi.
        private JButton         btnAddItem;
        private JButton         btnDeleteItem;
        private JLabel          lblQuantity;
        private JTextField      tfQuantity;
        
        private JLabel              lblTotalSales;
        private JLabel              lblOrderState;
        private JLabel              lblStaffName;
        private JList               orderItemList;
        private JList               menuList;
        
        private int             currentOrderID;
        private int             orderItemCnt;
        private int             currentOrderState;
        
        private JPanel          orderDetailPanel;
        private JPanel          menuListPanel;
        
        public OrderDetailPanel()
        {
            this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
            
            
            orderDetailPanel = new JPanel();
           
            
            GridBagLayout gbLayout = new GridBagLayout();
            orderDetailPanel.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();
            
            lblLeftTitle = new JLabel("Siparis Detayi");
            
            
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 4;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.insets = new Insets(5, 5, 5, 5);
            gbLayout.setConstraints(lblLeftTitle, gbc);
            orderDetailPanel.add(lblLeftTitle);
            
            lblLeftInfo = new JLabel("No  Urun Adi                 Miktar    Fiyat");
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 4;
            gbLayout.setConstraints(lblLeftInfo, gbc);
            orderDetailPanel.add(lblLeftInfo);
            
            orderScrollPanel = new JScrollPane();
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.ipadx = 0;
            gbc.ipady = 0;
            gbc.weighty = 1.0;
           
            gbLayout.setConstraints(orderScrollPanel, gbc);
            orderDetailPanel.add(orderScrollPanel);
            
            lblTotalSales = new JLabel();
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.weighty = 0;
            gbc.gridwidth = 4;
            
            gbLayout.setConstraints(lblTotalSales, gbc);
            orderDetailPanel.add(lblTotalSales);
            
            lblOrderState = new JLabel();
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbLayout.setConstraints(lblOrderState, gbc);
            orderDetailPanel.add(lblOrderState);
            
            lblStaffName = new JLabel();
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.gridwidth = 4;
            gbLayout.setConstraints(lblStaffName, gbc);
            orderDetailPanel.add(lblStaffName);
            
            lblQuantity = new JLabel("Miktar");
            gbc.ipadx = 20;
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(lblQuantity, gbc);
            orderDetailPanel.add(lblQuantity);
            
            tfQuantity = new JTextField();
            tfQuantity.setInputVerifier(new IntegerInputVerifier(1,100));
            tfQuantity.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 7;
            gbLayout.setConstraints(tfQuantity, gbc);
            orderDetailPanel.add(tfQuantity);
            
            btnAddItem  = new JButton("Ekle");
            btnAddItem.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 6;
            gbc.gridwidth = 1;
            gbc.gridheight = 2;
            gbLayout.setConstraints(btnAddItem, gbc);
            orderDetailPanel.add(btnAddItem);
            
            btnDeleteItem   = new JButton("Sil");
            btnDeleteItem.addActionListener(this);
            gbc.gridx = 3;
            gbc.gridy = 6;
            gbLayout.setConstraints(btnDeleteItem, gbc);
            orderDetailPanel.add(btnDeleteItem);
            
            
                    
            menuListPanel = new JPanel();
            
            menuListPanel.setLayout( gbLayout);
            
            lblRightTitle = new JLabel("Menu Listesi");
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.ipadx = 0;
            gbc.gridwidth = 5;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.BOTH;
            gbLayout.setConstraints(lblRightTitle, gbc);
            menuListPanel.add(lblRightTitle);
            
            menuScrollPanel = new JScrollPane();
           
            gbc.gridy = 1;
            gbc.weighty = 1.0;
            
            gbLayout.setConstraints(menuScrollPanel, gbc);
            menuListPanel.add(menuScrollPanel);
            
            btnAll  = new JButton("Tumu");
            btnAll.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbc.fill = GridBagConstraints.BOTH;
            gbLayout.setConstraints(btnAll, gbc);
            menuListPanel.add(btnAll);
            
            btnMain  = new JButton("Ana Yemek");
            btnMain.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbLayout.setConstraints(btnMain, gbc);
            menuListPanel.add(btnMain);
            
            btnDrink  = new JButton("Icecek");
            btnDrink.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 2;
            gbLayout.setConstraints(btnDrink, gbc);
            menuListPanel.add(btnDrink);
            
            btnDessert  = new JButton("Tatlilar");
            btnDessert.addActionListener(this);
            gbc.gridx = 4;
            gbc.gridy = 2;
            gbLayout.setConstraints(btnDessert, gbc);
            menuListPanel.add(btnDessert);
            
            LineBorder border = new LineBorder(Color.BLACK, 1, false);
            menuListPanel.setBorder(border);
            orderDetailPanel.setBorder(border);
            this.add(orderDetailPanel);
            this.add(menuListPanel);
            
            
            
            
            orderItemList   = new JList();
            orderItemList.setFont(new Font(Font.MONOSPACED,Font.PLAIN,10));
            orderItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            menuList = new JList();
            menuList.addListSelectionListener(this);
            menuList.setFont(new Font(Font.MONOSPACED,Font.PLAIN,10));
            menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
       }
        
        public void init(int orderID, String staffName)
        {
            currentOrderID = orderID;
            currentOrderState = rcController.getOrderState(orderID);
            switch(currentOrderState)
            {
                case Siparis_duzen.ORDER_CLOSED:
                    setOrderState("Kapandi");
                break;
                case Siparis_duzen.ORDER_CANCELED:
                    setOrderState("Iptal Edildi");
                break;
                default:
                break;
            }
            
             if(currentOrderState != 0)
            {
                btnAddItem.setEnabled(false);
                btnDeleteItem.setEnabled(false);
            }
            else
            {
                btnAddItem.setEnabled(true);
                btnDeleteItem.setEnabled(true);
            }
            
            refleshOrderDetailList();
            menuList.setListData(rcController.createMenuList(0).toArray());
            menuScrollPanel.getViewport().setView(menuList);
            tfQuantity.setText("");
            tfQuantity.setBackground( UIManager.getColor( "TextField.background" ) );
            setStaffName(staffName);
        }
        
        private void setTotal(double total)
        {
            lblTotalSales.setText("Toplam Gereken: " + total);
        }
        
        private void setOrderState(String state)
        {
            lblOrderState.setText("Siparis Durumu: " + state);
        }
        
        private void setStaffName(String name)
        {
            lblStaffName.setText("Ismi: " + name);
        }
        
        private void refleshOrderDetailList()
        {
            ArrayList<String> list = rcController.createOrderItemlList(currentOrderID);
            setTotal(rcController.getOrderTotalCharge(currentOrderID));
            orderItemCnt = list.size();
            orderItemList.setListData(list.toArray());
            //createOrderItemlList(currentOrderID, orderItemList);
            orderScrollPanel.getViewport().setView(orderItemList);
        }
        
        private int getOrderDetailIndexFromString(String orderLine)
        {
            try
            {
                String strIndex = orderLine.substring(0, 4);
                int index = Integer.parseInt(strIndex.trim());
                return index;
            }
            catch(Exception e)
            {
                
                return -1;
            }
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnAddItem)
            {
                
                if (btnAddItem.getVerifyInputWhenFocusTarget()) {
                    
                    btnAddItem.requestFocusInWindow();
                    if (!btnAddItem.hasFocus()) {    
                        return;
                    }
                }  
                
                String menuLine = (String)menuList.getSelectedValue();
                if (menuLine == null)
                    return;

                int     id = getIDfromString( menuLine, 4);
                if(id == -1)
                    return;
                if( tfQuantity.getText().equals(""))
                {
                    showErrorDialog("Error", "Miktar Girin!");
                    return;
                }
                byte    quantity = Byte.parseByte(tfQuantity.getText().trim());
              
                displayMessage("Menu ID = "+ id + " Miktar = " + quantity);
                if( rcController.addNewOrderItem(currentOrderID, id, quantity) == false)
                {
                    displayErrorMessage("addNewOrderItem Error!!\n" + rcController.getErrorMessage());
                }
                refleshOrderDetailList();
                
                orderItemList.ensureIndexIsVisible(orderItemCnt-1);
                
            }
            else if (ae.getSource() == btnDeleteItem)
            {
                String orderLine = (String)orderItemList.getSelectedValue();
                if(orderLine == null)
                    return;
                    
                int     index = getOrderDetailIndexFromString(orderLine);
                if(index == -1)
                    return;
                if( rcController.deleteOrderItem(currentOrderID, index) == false)
                {
                    displayErrorMessage("deleteOrderItem Error!!\n" + rcController.getErrorMessage());
                }
                refleshOrderDetailList();
            }
             else if (ae.getSource() == btnAll)
            {
                menuList.setListData(rcController.createMenuList(0).toArray());
                menuScrollPanel.getViewport().setView(menuList);
            }
             else if (ae.getSource() == btnMain)
            {
                
                menuList.setListData(rcController.createMenuList(Menu.MAIN).toArray());
                menuScrollPanel.getViewport().setView(menuList);
            }
             else if (ae.getSource() == btnDrink)
            {
               
                menuList.setListData(rcController.createMenuList(Menu.DRINK).toArray());
                menuScrollPanel.getViewport().setView(menuList);
            }
             
             else if (ae.getSource() == btnDessert)
            {
                
                menuList.setListData(rcController.createMenuList(Menu.DESSERT).toArray());
                menuScrollPanel.getViewport().setView(menuList);
            }
        }
        
        public void valueChanged( ListSelectionEvent e ) {
            if( e.getValueIsAdjusting() == true ){  
                if( e.getSource() == menuList ){
                     tfQuantity.setText("1");
                }
            }
        }
    }
   
    // ------------------------ TOPLAM SATISLAR-------------------------
    private class TotalSalesPanel extends JPanel implements ActionListener
    {
        private JScrollPane     scrollPanel;
        private JList           displayList;
        private JButton         btnPrint;
        private JButton         btnCloseAllOrder;
        private JLabel          lblTotalSales;
        private JLabel          lblTotalCount;
        private JLabel          lblCancelTotal;
        private JLabel          lblCancelCount;
        
        
        public TotalSalesPanel()
        {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();

            scrollPanel = new JScrollPane();
            
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 4;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.BOTH;
            gbLayout.setConstraints(scrollPanel, gbc);
            this.add(scrollPanel);
            
            lblTotalCount = new JLabel();
            lblTotalCount.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            gbc.weighty = 0;
            gbLayout.setConstraints(lblTotalCount, gbc);
            this.add(lblTotalCount);
            
            lblTotalSales = new JLabel();
            lblTotalSales.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            gbc.gridx = 2;
            gbc.gridy = 1;
            gbLayout.setConstraints(lblTotalSales, gbc);
            this.add(lblTotalSales);
            
            lblCancelCount = new JLabel();
            lblCancelCount.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbLayout.setConstraints(lblCancelCount, gbc);
            this.add(lblCancelCount);
            
            lblCancelTotal = new JLabel();
            lblCancelTotal.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            gbc.gridx = 2;
            gbc.gridy = 2;
            gbLayout.setConstraints(lblCancelTotal, gbc);
            this.add(lblCancelTotal);
            
            btnPrint    = new JButton("Metin Dosyasi Olustur");
            btnPrint.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.gridwidth = 2;
            gbLayout.setConstraints(btnPrint, gbc);
            this.add(btnPrint);
            
            btnCloseAllOrder    = new JButton("Tum Siparisleri Kapa");
            btnCloseAllOrder.addActionListener(this);
            gbc.gridx = 2;
            gbc.gridy = 3;
            gbLayout.setConstraints(btnCloseAllOrder, gbc);
            this.add(btnCloseAllOrder);
            
            displayList = new JList();
        }
        
        private void setTotalCount( int count)
        {
            lblTotalCount.setText("Bugunun Siparisleri: " + count);
        }
        
        private void setTotalSales( double sales)
        {
            lblTotalSales.setText("Toplam:$ " + sales);
        }
        
        private void setCancelCount( int count)
        {
            lblCancelCount.setText("Iptal edilenler: " + count);
        }
        
        private void setCancelTotal( double sales)
        {
            lblCancelTotal.setText("Iptal Toplam: " + sales);
        }
        
        private void showOrderList()
        {
            displayList.setListData(rcController.createOrderList().toArray());
            scrollPanel.getViewport().setView(displayList);
            
            setTotalCount(rcController.getTodaysOrderCnt());
            setTotalSales(rcController.getTotalSales());
            setCancelCount(rcController.getTodaysCancelCnt());
            setCancelTotal(rcController.getCancelTotal());
        }
        
        public void init()
        {
            showOrderList();
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnPrint)
            {
                String createFineName = rcController.generateSalesReport();
                if( createFineName == null)
                    displayErrorMessage(rcController.getErrorMessage());
                else
                    displayMessage(createFineName + " Olusturuldu.");
            }
            else if (ae.getSource() == btnCloseAllOrder)
            {
                if (showYesNoDialog("", "Siparisler apamak istedigine emin misin?") == DIALOG_YES)
                {
                    rcController.closeAllOrder();
                    init();
                    displayMessage("");
                }
            }
        }
    }
    
    //--------------------------------ODEMELER---------------------------
    private class PaymentPanel extends JPanel implements ActionListener
    {
        private JScrollPane     scrollPanel;
        private JTextArea       displayArea;
        private JButton         btnPrint;
        private JButton         btnAllClockOut;
        
        public PaymentPanel()
        {
            GridBagLayout gbLayout = new GridBagLayout();
            this.setLayout( gbLayout);
            GridBagConstraints gbc = new GridBagConstraints();

            displayArea = new JTextArea();
            displayArea.setBackground(Color.orange);
            displayArea.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));
            displayArea.setEditable(false);
            displayArea.setMargin(new Insets(5, 5, 5, 5));
            scrollPanel = new JScrollPane(displayArea);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.BOTH;
            gbLayout.setConstraints(scrollPanel, gbc);
            this.add(scrollPanel);
            
            btnPrint = new JButton("Odeme Rapor Dosyasi Olustur");
            btnPrint.addActionListener(this);
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbLayout.setConstraints(btnPrint, gbc);
            this.add(btnPrint);
            
            btnAllClockOut = new JButton("Ýs Bitir");
            btnAllClockOut.addActionListener(this);
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridwidth = 1;
            gbc.weighty = 0;
            gbLayout.setConstraints(btnAllClockOut, gbc);
            this.add(btnAllClockOut);
        }
        

        
        public void init()
        {
            displayArea.setText(rcController.createPaymentList());
        }
        
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == btnPrint)
            {
                String createFineName = rcController.generatePaymentReport();
                if( createFineName == null)
                    displayErrorMessage(rcController.getErrorMessage());
                else
                    displayMessage(createFineName + " Oludturuldu.");
            }
            else if (ae.getSource() == btnAllClockOut)
            {
                if (showYesNoDialog("", "Tum isi bitirmek istedigine emin misin?") == DIALOG_YES)
                {
                    rcController.clockOutAll();
                    init();
                }
            }
        }
    }

    
    private class IntegerInputVerifier extends InputVerifier{
        private int state = 0;  //0:no range check 1:min check 2:min and max check
        private int MAX = 0;
        private int MIN = 0;
        
        public IntegerInputVerifier()
        {
            super();
        }
        
        public IntegerInputVerifier(int min)
        {
            super();
            MIN = min;
            state = 1;
        }
        
        public IntegerInputVerifier(int min, int max)
        {
            super();
            MIN = min;
            MAX = max;
            state = 2;
        }
        
        @Override public boolean verify(JComponent c)
        {
            JTextField textField = (JTextField)c;
            boolean result = false;
            
            try
            {
                int number = Integer.parseInt(textField.getText());
                
                switch(state)
                {
                    case 0:
                        result = true;
                    case 1:
                        if( number < MIN)
                        {
                            
                            displayErrorMessage("Minimum girdi " + MIN);
                            textField.setBackground( Color.red );
                            result = false;
                        }
                        else
                        {
                            textField.setBackground( UIManager.getColor( "TextField.background" ) );  
                            result = true;
                        }
                    break;
                    case 2:
                        if( number < MIN)
                        {
                            displayErrorMessage("Minimum girdi: " + MIN);
                            textField.setBackground( Color.red );
                            result = false;
                        }
                        else
                        {
                            if(number > MAX)
                            {
                                displayErrorMessage("Maximum girdi: " + MAX);
                                textField.setBackground( Color.red );
                                result = false;
                            }
                            else
                            {
                                textField.setBackground( UIManager.getColor( "TextField.background" ) );  
                                result = true;
                            }
                        }
                    break;
                }
            }catch(NumberFormatException e) {
                  displayErrorMessage("Sadece rakam girebilirsiniz.");
                  textField.setBackground( Color.red );
                result = false;
            }
            return result;
        }
    }
    
    private class DoubleInputVerifier extends InputVerifier{
        private int state = 0;  
        private double MAX = 0;
        private double MIN = 0;
        
        public DoubleInputVerifier()
        {
            super();
        }
        
        public DoubleInputVerifier(double min)
        {
            super();
            MIN = min;
            state = 1;
        }
        
        public DoubleInputVerifier(double min, double max)
        {
            super();
            MIN = min;
            MAX = max;
            state = 2;
        }
        
        @Override public boolean verify(JComponent c)
        {
            JTextField textField = (JTextField)c;
            boolean result = false;
            
            try
            {
                double number = Double.parseDouble(textField.getText());
                
                switch(state)
                {
                    case 0:
                        result = true;
                    case 1:
                        if( number < MIN)
                        {
                            
                            displayErrorMessage("Minimum girdi: " + MIN);
                            textField.setBackground( Color.red );
                            result = false;
                        }
                        else
                        {
                            textField.setBackground( UIManager.getColor( "TextField.background" ) );  
                            result = true;
                        }
                    break;
                    case 2:
                        if( number < MIN)
                        {
                            displayErrorMessage("Minimum girdi: " + MIN);
                            textField.setBackground( Color.red );
                            result = false;
                        }
                        else
                        {
                            if(number > MAX)
                            {
                                displayErrorMessage("Maximum girdi: " + MAX);
                                textField.setBackground( Color.red );
                                result = false;
                            }
                            else
                            {
                                textField.setBackground( UIManager.getColor( "TextField.background" ) );  
                                result = true;
                            }
                        }
                    break;
                }
            }catch(NumberFormatException e) {
                  displayErrorMessage("Sadece rakam girebilirsiniz.");
                  textField.setBackground( Color.red );
                result = false;
            }
            return result;
        }
    }
}
