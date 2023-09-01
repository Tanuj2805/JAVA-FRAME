import com.mysql.cj.jdbc.Driver;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Receiver extends JFrame{

    private JLabel idlbl, donornamelbl, heading, bloodgrouplbl,bloodamountlbl;

    private JTextField id_txt, donorname_txt,bloodamount_txt;
    private JComboBox <String> bloodcb ;
    private JButton addbtn, deletbtn_car;
    private JTable jt;
    private DefaultTableModel dtm;
    private JScrollPane jsp;


    public Receiver()
    {

        dtm = new DefaultTableModel();
        String[] cols ={"Id","Name","Blood Group"};
        for(String i : cols)
        {
            dtm.addColumn(i);
        }
        jt = new JTable(dtm);
        jt.setBounds(300,100,100,300);
        jt.getTableHeader().setForeground(Color.RED);
        jt.getTableHeader().setBackground(Color.WHITE);
        jt.setBackground(Color.WHITE);
        jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jtMouseClicked(e);

            }
        });


        jsp = new JScrollPane(jt);
        jsp.setBounds(410,120,350,250);
        this.setLayout(null);
        JLabel background = new JLabel(new ImageIcon("D:\\Sanket\\Java projects\\Blood Bank\\Blood Bank\\images\\back1.jpg"));
        background.setBounds(0,0,800,600);



        heading = new JLabel("Receiver", JLabel.CENTER);
        heading.setFont(new Font("gabriola", Font.BOLD| Font.ITALIC, 40));
        heading.setBounds(280, 25, 210, 60);
        heading.setForeground(Color.RED);



        idlbl = new JLabel("Id :");
        idlbl.setFont(new Font("gabriola",Font.ITALIC,35));
        idlbl.setBounds(80,150,40,50);
        idlbl.setForeground(Color.BLACK);


        id_txt = new JTextField();
        id_txt.setFont(new Font("serif",Font.BOLD,20));
        id_txt.setBounds(215,146,180,30);


        donornamelbl = new JLabel("Name:");
        donornamelbl.setFont(new Font("gabriola",Font.ITALIC,35));
        donornamelbl.setBounds(80,200,80,50);
        donornamelbl.setForeground(Color.BLACK);

        donorname_txt = new JTextField();
        donorname_txt.setFont(new Font("serif",Font.BOLD,20));
        donorname_txt.setBounds(215,196,180,30);


        bloodgrouplbl = new JLabel("Blood Group : ");
        bloodgrouplbl.setFont(new Font("gabriola",Font.ITALIC,35));
        bloodgrouplbl.setBounds(80,260,80,50);
        bloodgrouplbl.setForeground(Color.BLACK);
        this.add(bloodgrouplbl);


        String groups [] = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        bloodcb = new JComboBox<String>(groups);
        bloodcb.setBounds(215,260,180,30);
        this.add(bloodcb);

        bloodamountlbl = new JLabel("Amount : ");
        bloodamountlbl.setFont(new Font("gabriola",Font.ITALIC,35));
        bloodamountlbl.setBounds(80,320,150,50);
        bloodamountlbl.setForeground(Color.BLACK);
        this.add(bloodamountlbl);

        bloodamount_txt = new JTextField();
        bloodamount_txt.setFont(new Font("serif",Font.BOLD,20));
        bloodamount_txt.setBounds(215,320,180,30);
        this.add(bloodamount_txt);

        addbtn = new JButton("ADD", new ImageIcon("D:\\Sanket\\Java projects\\images\\addbtn.jpg"));
        addbtn.setFont(new Font("gabriola",Font.BOLD,25));
        addbtn.setForeground(Color.RED);
        addbtn.setBackground(Color.WHITE);
        addbtn.setBounds(150,390,140,50);
        this.add(addbtn);
        addbtn.addActionListener(new ActionListener() {
          double group_qty = 0;

            @Override
            public void actionPerformed(ActionEvent ex)
            {

                try
                {
                    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/blood", "root", "Tanuj@2005");
                    int n = Integer.parseInt(id_txt.getText());
                    String name = donorname_txt.getText();
                    String blood = (String) bloodcb.getSelectedItem();
                    PreparedStatement psblood = con.prepareStatement(" select quantity from bl_storage where bl_group = ?" );
                    psblood.setString(1,blood);

                    double qty = Double.parseDouble(bloodamount_txt.getText());

                    ResultSet rsblood = psblood.executeQuery();
                    while (rsblood.next())
                    {
                        group_qty = rsblood.getDouble(1);
                    }
                    qty = group_qty+qty;
                    PreparedStatement psgroup = con.prepareStatement(" update bl_storage set quantity = ? where bl_group = ?");

                    psgroup.setDouble(1,qty);
                    psgroup.setString(2,blood);

                    int o = psgroup.executeUpdate();


                    // DriverManager.registerDriver(new Driver());
                    //Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/blood", "root", "Tanuj@2005");
                    PreparedStatement ps = con.prepareStatement("insert into receiver values(?,?,?)");
                    ps.setInt(1,n);
                    ps.setString(2,name);
                    ps.setString(3,blood);

                    int q = ps.executeUpdate();
                    if(q == 1)
                    {
                        JOptionPane.showMessageDialog(null,"Inserted Successfully");
                        id_txt.setText("");
                        donorname_txt.setText("");
                        loadTable();
                    }
                    else
                        JOptionPane.showMessageDialog(null," Not Inserted Successfully");

                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Add Btn"+e.toString());
                }
            }
        });

        deletbtn_car = new JButton("DELETE", new ImageIcon("D:\\Sanket\\Java projects\\images\\deletebtn.jpg"));
        deletbtn_car.setBounds(150,430,140,50);
        deletbtn_car.setFont(new Font("gabriola",Font.BOLD,20));
        deletbtn_car.setBackground(Color.WHITE);
        deletbtn_car.setForeground(Color.RED);
        deletbtn_car.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try {


                    int n = Integer.parseInt(id_txt.getText());

                    DriverManager.registerDriver(new Driver());
                    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/blood", "root", "Tanuj@2005");
                    PreparedStatement ps = con.prepareStatement("delete from receiver where id = ? ");
                    ps.setInt(1,n);

                    int na = ps.executeUpdate();
                    if(na == 1)
                    {
                        JOptionPane.showMessageDialog(null, "Item deleted Successfully");
                        loadTable();
                        id_txt.setText("");
                        donorname_txt.setText("");
                    }


                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"" + ex.toString());
                }
            }
        });




        background.add(heading);
        background.add(idlbl);
        background.add(id_txt);
        background.add(donornamelbl);
        background.add(donorname_txt);
        background.add(addbtn);
        background.add(deletbtn_car);
        background.add(jsp);
        this.add(background);

        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);

        loadTable();
    }


    public void jtMouseClicked(java.awt.event.MouseEvent event)
    {
        DefaultTableModel model = (DefaultTableModel)this.jt.getModel();

        int selectedRow = jt.getSelectedRow();
        id_txt.setText(model.getValueAt(selectedRow,0).toString());
        donorname_txt.setText(model.getValueAt(selectedRow,1).toString());
        bloodcb.setSelectedItem(model.getValueAt(selectedRow,2).toString());

    }
    private void loadTable() {
        try
        {   
            dtm.setRowCount(0);

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/blood", "root", "Tanuj@2005");
            PreparedStatement ps = con.prepareStatement("select * from receiver");

            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String bloodgroup = rs.getString(3);
                dtm.addRow(new Object[]{id,name,bloodgroup});
                jt.setModel(dtm);
            }
        }
        catch (Exception e )
        {
            JOptionPane.showMessageDialog(null, "loadTable"+ e.toString());
        }

    }


    public static void main(String[] args) {
        Receiver f = new Receiver();
        //f.getContentPane().setBackground(Color.WHITE);
    }
}
