import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class OrderMange {
    public JPanel main;
    private JTextField txtO_Id;
    private JTextField txtO_name;
    private JTextField txtO_num;
    private JTextField txtO_email;
    private JTextField txtO_cost;
    private JTable table1;
    private JTextField txtO_search;
    private JButton SEACHButton;
    private JButton ADDButton;
    private JButton UPDATEButton;
    private JButton DELETEButton;
    private JButton BACKButton;
    private JScrollPane table_1;

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
            pst = con.prepareStatement("SELECT O_code, O_name, O_num, O_email, O_cost FROM orders");
            ResultSet rs=pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e012) {
            e012.printStackTrace();
        }
    }

    public OrderMange() {
        connect();
        table_lord();

        ADDButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            String O_code,O_name,O_num,O_email,O_cost;

            O_code = txtO_Id.getText();
            O_name = txtO_name.getText();
            O_num = txtO_num.getText();
            O_email = txtO_email.getText();
            O_cost = txtO_cost.getText();

            try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost/car_care", "root", "");
                PreparedStatement pst = con.prepareStatement("insert into orders(O_code,O_name,O_num,O_email,O_cost)values(?,?,?,?,?)"))
            {
                pst.setString(1,O_code);
                pst.setString(2,O_name);
                pst.setString(3,O_num);
                pst.setString(4,O_email);
                pst.setString(5,O_cost);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Order Added!");
                table_lord();
                txtO_Id.setText("");
                txtO_name.setText("");
                txtO_num.setText("");
                txtO_email.setText("");
                txtO_cost.setText("");

            } catch (SQLException e1){
                e1.printStackTrace();
            }

        }
        });


        SEACHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ordercode = txtO_search.getText();

                try {
                    pst = con.prepareStatement("select O_code,O_name,O_num,O_email,O_cost from orders where O_code = ? ");// Use to seach Orders
                    pst.setString(1,ordercode);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()==true){
                        String code = rs.getString("O_code");
                        String name = rs.getString("O_name");
                        String num = rs.getString("O_num");
                        String date = rs.getString("O_email");
                        String cost = rs.getString("O_cost");

                        txtO_Id.setText(code);
                        txtO_name.setText(name);
                        txtO_num.setText(num);
                        txtO_email.setText(date);
                        txtO_cost.setText(cost);
                    }else {
                        txtO_Id.setText("");
                        txtO_name.setText("");
                        txtO_num.setText("");
                        txtO_email.setText("");
                        txtO_cost.setText("");
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

                String O_code,O_name,O_num,O_email,O_cost,orderid;

                O_code = txtO_Id.getText();
                O_name = txtO_name.getText();
                O_num = txtO_num.getText();
                O_email = txtO_email.getText();
                O_cost = txtO_cost.getText();
                orderid = txtO_search.getText();

                try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost/car_care", "root", "");
                    PreparedStatement pst = con.prepareStatement("update orders SET O_code = ?, O_name = ?, O_num = ?,O_email = ?, O_cost = ? where id = ? "))
                {
                    pst.setString(1,O_code);
                    pst.setString(2,O_name);
                    pst.setString(3,O_num);
                    pst.setString(4,O_email);
                    pst.setString(5,O_cost);
                    pst.setString(6,orderid);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Order Updateed!");
                    table_lord();
                    txtO_Id.setText("");
                    txtO_name.setText("");
                    txtO_num.setText("");
                    txtO_email.setText("");
                    txtO_cost.setText("");

                } catch (SQLException e1){

                    e1.printStackTrace();
                }
            }
        });


        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String orderid;
                orderid = txtO_search.getText();

                try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost/car_care", "root", "");
                    PreparedStatement pst = con.prepareStatement("delete from orders where O_code = ? "))
                {
                    pst.setString(1,orderid);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Order Delete!");
                    table_lord();
                    txtO_Id.setText("");
                    txtO_name.setText("");
                    txtO_num.setText("");
                    txtO_email.setText("");
                    txtO_cost.setText("");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame orderFrame = (JFrame) SwingUtilities.getWindowAncestor(main);
                orderFrame.dispose(); // Close the OrderManage frame

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
    /* {Main function}
    public static void main(String[] args) {

        JFrame frame = new JFrame("OrderMange");

        frame.setSize(1100, 650);// Set a fixed size for the frame
        frame.setLocationRelativeTo(null);// Set the frame to appear in the center of the screen

        frame.setContentPane(new OrderMange().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }*/

}
