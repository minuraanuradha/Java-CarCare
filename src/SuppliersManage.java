import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SuppliersManage {
    public JPanel main;
    private JButton BACKButton;
    private JTextField txtS_Id;
    private JTextField txtS_name;
    private JTextField txtS_email;
    private JTextField txtS_product;
    private JTextField txtS_status;
    private JButton ADDButton;
    private JButton UPDATEButton;
    private JButton DELETEButton;
    private JButton SEACHButton;
    private JTextField txtS_search;
    private JTable table2;

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

    void table_lord() {
        try {
            pst = con.prepareStatement("select * from suppliers");
            ResultSet rs=pst.executeQuery();
            table2.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e012) {
            e012.printStackTrace();
        }
    }

    public SuppliersManage() {

        connect();
        table_lord();

        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String S_code,S_name,S_email,S_product,S_status;

                S_code = txtS_Id.getText();
                S_name = txtS_name.getText();
                S_email = txtS_email.getText();
                S_product = txtS_product.getText();
                S_status = txtS_status.getText();

                try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost/car_care", "root", "");
                    PreparedStatement pst = con.prepareStatement("insert into suppliers(S_code,S_name,S_email,S_product,S_status)values(?,?,?,?,?)"))
                {
                    pst.setString(1,S_code);
                    pst.setString(2,S_name);
                    pst.setString(3,S_email);
                    pst.setString(4,S_product);
                    pst.setString(5,S_status);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Order Added!");
                    table_lord();
                    txtS_Id.setText("");
                    txtS_name.setText("");
                    txtS_email.setText("");
                    txtS_product.setText("");
                    txtS_status.setText("");

                } catch (SQLException e1){
                    e1.printStackTrace();
                }

            }
        });

       SEACHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String supplierscode = txtS_search.getText();

                try {
                    pst = con.prepareStatement("select S_code,S_name,S_email,S_product,S_status from suppliers where id = ? ");// Use to seach Sup
                    pst.setString(1,supplierscode);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()==true){
                        String code = rs.getString("S_code");
                        String name = rs.getString("S_name");
                        String email = rs.getString("S_email");
                        String product = rs.getString("S_product");
                        String status = rs.getString("S_status");

                        txtS_Id.setText(code);
                        txtS_name.setText(name);
                        txtS_email.setText(email);
                        txtS_product.setText(product);
                        txtS_status.setText(status);
                    }else {
                        txtS_Id.setText("");
                        txtS_name.setText("");
                        txtS_email.setText("");
                        txtS_product.setText("");
                        txtS_status.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid details");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


       UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String S_code,S_name,S_email,S_product,S_status,supplierid;

                S_code = txtS_Id.getText();
                S_name = txtS_name.getText();
                S_email = txtS_email.getText();
                S_product = txtS_product.getText();
                S_status = txtS_status.getText();
                supplierid = txtS_search.getText();

                try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost/car_care", "root", "");
                    PreparedStatement pst = con.prepareStatement("update suppliers SET S_code = ?, S_name = ?, S_email = ?,S_product = ?, S_status = ? where id = ? "))
                {
                    pst.setString(1,S_code);
                    pst.setString(2,S_name);
                    pst.setString(3,S_email);
                    pst.setString(4,S_product);
                    pst.setString(5,S_status);
                    pst.setString(6,supplierid);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Order Updateed!");
                    table_lord();
                    txtS_Id.setText("");
                    txtS_name.setText("");
                    txtS_email.setText("");
                    txtS_product.setText("");
                    txtS_status.setText("");

                } catch (SQLException e1){

                    e1.printStackTrace();
                }
            }
       });


       DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String orderid;
                orderid = txtS_search.getText();

                try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost/car_care", "root", "");
                    PreparedStatement pst = con.prepareStatement("delete from suppliers where id = ? "))
                {
                    pst.setString(1,orderid);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Order Delete!");
                    table_lord();
                    txtS_Id.setText("");
                    txtS_name.setText("");
                    txtS_email.setText("");
                    txtS_product.setText("");
                    txtS_status.setText("");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

       BACKButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame SupplierFrame = (JFrame) SwingUtilities.getWindowAncestor(main);
            SupplierFrame.dispose(); // Close the OrderManage frame

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
