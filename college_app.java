import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;



class database 
{
        private static Connection con;
        public static void connect()
        {
            try
            {
               Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/College", "root", "Tanuj@2005");
        
            }
            catch(Exception e)
            {
             JOptionPane.showMessageDialog(null," "+e) ;
            }
        }

        public static void disconnect()
        {
            try{
                 con.close();
               }
               
            catch(Exception e)
            {
            
              JOptionPane.showMessageDialog(null,e);
            }
        }

}
class STUDENT_LOGIN_SECTION extends JFrame
{

    private JButton b_student, b_staff, b_manager;
    private ImageIcon sl;

   public STUDENT_LOGIN_SECTION()
   {


    this.setLayout(new GridLayout(3,1));
    
    sl = new ImageIcon("D:\rs_lobin.jpg");
    b_student = new JButton(sl);

    
    this.add(b_student);
      this.setVisible(true);
    this.setSize(500, 800);

   }

}
class fees_sub extends JFrame implements ActionListener , ItemListener
{
    private JTextField txt_name, txt_idcode, txt_amt;
    private JComboBox  <String> jc_category;
    private JButton b;
    private database con;
    private PreparedStatement stmt;


    public fees_sub()
    {
        txt_name = new JTextField(" ");
        txt_idcode = new JTextField(" ");
   
        txt_amt = new JTextField();

        this.setLayout(new GridLayout(5, 2));
        this.add(new JLabel("ENTER YOUR NAME: "));
        this.add(txt_name);
        this.add(new JLabel("ENTER YOUR IDCODE: "));
        this.add(txt_idcode);
        this.add(new JLabel("ENTER YOUR CATEGORY: "));

        Vector vec_cat = new Vector<String>();
        String str_cat[] = {"CATEGORY", "OPEN", "OBC", "SC", "ST", "NT","TFWS", "EWS"};
        
        for(String i : str_cat)
        vec_cat.addElement(i);

        jc_category = new JComboBox(vec_cat);

        this.add(jc_category);
        jc_category.addItemListener(this);

        this.add(new JLabel("PAID AMOUNT: "));
        this.add(txt_amt);
        txt_amt.setEditable(false);
        b = new JButton("SUBMIT");
        this.add(b);
        b.addActionListener(this);
       
              

 
        this.setVisible(true);
        this.setSize(300, 300);

        this.addWindowListener(new WindowAdapter(){

            public void windowClosing(WindowEvent e)
            {
                myframe.fees_f = null;
                dispose();
            }
        });
    
    

    }
    

    public void actionPerformed(ActionEvent e)
    {
    
        String err = " ";
        if(jc_category.getSelectedItem().toString().equals("CATEGORY"))
        err = err + "ENTER CATEGORY \n";

        if(txt_idcode.getText().toString().equals(" "))
        err = err + "ENTER IDCODE \n ";

        if(txt_name.getText().toString().equals(" "))
        err = err + "ENTER NAME \n"; 


        if(err.equals(" "))
        JOptionPane.showMessageDialog(this, "FEES FOR IDCODE "+txt_idcode.getText().toUpperCase()+" HAS BEEN COLLECTED");
        else
        JOptionPane.showMessageDialog(this,err);
        
       

    }
   

    
    public void itemStateChanged(ItemEvent e)
    {
       
        switch(jc_category.getSelectedItem().toString())
        {
            case "OPEN" : txt_amt.setText("9500");
            break;

             case "OBC" : txt_amt.setText("5500");
            break;

             case "SC" : txt_amt.setText("1500");
            break;

             case "ST" : txt_amt.setText("1500");
            break;
            
             case "NT" : txt_amt.setText("3500");
            break;

             case "TFWS" : txt_amt.setText("1500");
            break;

             case "EWS" : txt_amt.setText("5500");
            break;
    }
}


}

class myframe extends JFrame implements ActionListener , MouseListener
{
   private JMenuBar tb;
   private JButton jm1,jm2,jm3,jm4;
   public static fees_sub fees_f = null;
   private JTextArea about_gpa;
   private JLabel JLgpa,background;


 
    public myframe()
    {
            super("HOME PAGE");
       this.setBackground(Color.BLACK);
       background=new JLabel(new ImageIcon("D:/rgpa.jpg"));
      add(background);
      

       tb = new JMenuBar();
       tb.setLayout(new GridLayout(1, 3));
       jm1 = new JButton("MENU SECTION");
       jm2 = new JButton("MODERATOR LOGIN ");
       jm3 = new JButton("FEES COLLECTOR");
       jm4 = new JButton("STUDENT LOGIN");
       tb.add(jm1);
       jm1.addActionListener(this);
       jm1.addMouseListener(this);
       tb.add(jm2);
       jm2.addActionListener(this);
       jm2.addMouseListener(this);
        tb.add(jm4);
        jm4.addActionListener(this);
       jm4.addMouseListener(this);
       tb.setBackground(Color.RED);
       tb.add(jm3);
       jm3.addActionListener(this);
       jm3.addMouseListener(this);
       tb.setBackground(Color.RED);
      
      
       
       
        jm1.setBackground(Color.RED);
        jm1.setForeground(Color.WHITE);

        jm2.setBackground(Color.RED);
        jm2.setForeground(Color.WHITE);

        jm3.setBackground(Color.RED);
        jm3.setForeground(Color.WHITE);

        jm4.setBackground(Color.RED);
        jm4.setForeground(Color.WHITE);

       background.add(tb);
       tb.setBounds(0, 0, 1550, 30);

       JLgpa = new JLabel("GOVERNMENT POLYTECHNIC ");
       JLgpa.setBounds(10, 570, 450, 20);
       JLgpa.setFont(new Font("Arial", Font.BOLD, 28));
       JLgpa.setForeground(Color.RED);
       JLgpa.setBackground(new Color(0, 0, 0, 0));
       background.add(JLgpa);

       JLabel amt = new JLabel("AMRAVATI");
       amt.setBounds(420 ,570, 300, 20);
       amt.setFont(new Font("Arial", Font.BOLD, 28));
       amt.setForeground(Color.RED);
       amt.setBackground(new Color(0, 0, 0, 0));
       background.add(amt);

       
        about_gpa = new JTextArea("Government Polytechnic, Amravati is an autonomous Institute of Govt. Polytechnic, Maharashtra established in the year 1955. This institute has a long history of producing technical manpower and rendering technical services to the \nsociety. Many students of this institute are chairing the topmost positions in various govt. offices and in reputed industries. Few of the students became successful entrepreneur looking to the overall performance of the institute.\n MSBTE, Mumbai has awarded academic autonomy to the institute from 1995.\n"+
          "The ISTE of Maharashtra & Goa section has awarded the institute by Narsee Monje Award in 2005 & Best Polytechnic award in 2015. The institute has separate trainee hostel with all facilities for accommodation purpose.\nThe institute has separate Library building with all equipped resources");
        about_gpa.setFont(new Font("Arial", Font.BOLD, 14));
        about_gpa.setForeground(Color.WHITE);
        about_gpa.setBackground(new Color(0, 0, 0, 0));
        about_gpa.setBounds(10, 600, 1500, 200);
          about_gpa.setEditable(false);
          background.add(about_gpa);
         
      
         this.setVisible(true);
        this.setSize(1920, 1080);
        this.setResizable(false);
    }


     public void actionPerformed(ActionEvent e)
     {
        String txt = e.getActionCommand();

        switch(txt)
        {
            case "MENU SECTION":
                               JOptionPane.showMessageDialog(this,"hell0");
                               break; 
            case "STUDENT LOGIN":
                                  new login_page();
                                  

                               break;
            case "FEES COLLECTOR":
                                
                                if(fees_f == null)
                                {
                                   fees_f = new fees_sub(); 
                                }
                                 else
                                 JOptionPane.showMessageDialog(this,"ALREADY OPENED");
                                break;
        }
     }

     public void mouseEntered(MouseEvent e)
     {
       
         Component c = e.getComponent();
         
            
         if(c.equals(jm1))
          jm1.setBackground(Color.BLUE);

           if(c.equals(jm2))
          jm2.setBackground(Color.BLUE);

           if(c.equals(jm3))
          jm3.setBackground(Color.BLUE);

          if(c.equals(jm4))
          jm4.setBackground(Color.BLUE);

        
     }


     public void mouseExited(MouseEvent e)
     {

        jm1.setBackground(Color.RED);
        jm2.setBackground(Color.RED);
        jm3.setBackground(Color.RED);
        jm4.setBackground(Color.RED);
       
     }

     public void mouseClicked(MouseEvent e){}
     public void mouseReleased(MouseEvent e){}
     public void mousePressed(MouseEvent e){}
      
}
class login_page extends JFrame implements ActionListener
{
    private static JButton check,clear;
    private static JPasswordField pass;
    private static JTextField username;
    public static String log[] = {};

   


    public login_page()
    {
         username = new JTextField(20);
         pass = new JPasswordField(20);
         check = new JButton("OK");
         clear = new JButton("CLEAR");
         
         this.setLayout(new GridLayout(3, 3));

         this.add(new JLabel("Enter YOUR USERNAME"));
         this.add(new JLabel(" "));
         this.add(username);
         
         this.add(new JLabel("Enter YOUR PASSWORD"));
         this.add(new JLabel(" "));
         this.add(pass);

        
         this.add(check);
         check.addActionListener(this);
         this.add(clear);
         clear.addActionListener(this);
         this.add(new JLabel(" "));


         this.setSize(500,400);
         this.setVisible(true);

         

      


         
    }
     public void actionPerformed(ActionEvent e)
     {
       
     }

  
}



class college_app
{
    public static void main(String[] args) {

       
        new myframe();
    }
}



    

