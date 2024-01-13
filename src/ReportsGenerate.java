import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ReportsGenerate {
    public JPanel main;
    private JButton BACKButton;
    private JTable orders;
    private JTable employees;
    private JTable empord;
    private JTable suppliers;

    Connection con;
    PreparedStatement pst;

    public void connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/car_care","root","");
            System.out.println("Succes");
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException(e2);
        } catch (SQLException e3) {
            throw new RuntimeException(e3);
        }

    }
   //table Lording
    void orders_table_lord() {
        try {
            pst = con.prepareStatement("select O_code,O_name,O_num,O_email,O_cost from orders");
            ResultSet rs=pst.executeQuery();
            orders.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e012) {
            e012.printStackTrace();
        }
    }

    void suppliers_table_lord() {
        try {
            pst = con.prepareStatement("select S_code,S_name,S_email,S_product,S_status from suppliers");
            ResultSet rs=pst.executeQuery();
            suppliers.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e012) {
            e012.printStackTrace();
        }
    }

    void emp_table_lord() {
        try {
            pst = con.prepareStatement("SELECT e_code,e_name,e_num,e_email,e_department,e_address FROM employees");
            ResultSet rs=pst.executeQuery();
            employees.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e012) {
            e012.printStackTrace();
        }
    }

    void emp_ord_table_lord() {
        try {
            pst = con.prepareStatement("SELECT e_code, e_name, O_code, O_name, e_department, O_status FROM empandord");
            ResultSet rs=pst.executeQuery();
            empord.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e012) {
            e012.printStackTrace();
        }
    }
    public ReportsGenerate() {

    connect();

    orders_table_lord();
    suppliers_table_lord();
    emp_table_lord();
    emp_ord_table_lord();

    BACKButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame ReportFrame = (JFrame) SwingUtilities.getWindowAncestor(main);
            ReportFrame.dispose(); // Close the OrderManage frame

            JFrame homeFrame = new JFrame("Car Care Dashboard");// Create the Home frame
            Home home = new Home();

            homeFrame.setSize(1100, 650);
            homeFrame.setLocationRelativeTo(null);
            homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            homeFrame.getContentPane().add(home.getMainframe());
            homeFrame.setVisible(true);
        }
    });

    }



}




