import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EmployeesManage {
    public JPanel main;
    private JButton BACKButton;
    private JTextField txtE_Id;
    private JTextField txtE_name;
    private JTextField txtE_num;
    private JTextField txtE_email;
    private JTextField txtE_department;
    private JButton ADDButton;
    private JButton UPDATEButton;
    private JButton DELETEButton;
    private JButton SEACHButton;
    private JTextField txtE_search;
    private JTable table1;
    private JLabel address;
    private JTextField txtE_address;

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
            pst = con.prepareStatement("SELECT e_code,e_name,e_num,e_email,e_department,e_address FROM employees");
            ResultSet rs=pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e012) {
            e012.printStackTrace();
        }
    }

    public EmployeesManage() {
        connect();
        table_lord();
    BACKButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame empFrame = (JFrame) SwingUtilities.getWindowAncestor(main);
            empFrame.dispose(); // Close the OrderManage frame

            JFrame homeFrame = new JFrame("Car Care Dashboard");// Create the Home frame
            Home home = new Home();

            homeFrame.setSize(1100, 650);
            homeFrame.setLocationRelativeTo(null);

            homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            homeFrame.getContentPane().add(home.getMainframe());
            homeFrame.setVisible(true);
        }
    });
        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String e_code,e_name,e_num,e_email,e_department,e_address;

                e_code = txtE_Id.getText();
                e_name = txtE_name.getText();
                e_num = txtE_num.getText();
                e_email = txtE_email.getText();
                e_department = txtE_department.getText();
                e_address = txtE_address.getText();

                try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost/car_care", "root", "");
                    PreparedStatement pst = con.prepareStatement("insert into employees(e_code,e_name,e_num,e_email,e_department,e_address)values(?,?,?,?,?,?)"))
                {
                    pst.setString(1,e_code);
                    pst.setString(2,e_name);
                    pst.setString(3,e_num);
                    pst.setString(4,e_email);
                    pst.setString(5,e_department);
                    pst.setString(6,e_address);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Employee Added!");
                    table_lord();

                } catch (SQLException e1){
                    e1.printStackTrace();
                }
            }
        });
        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String e_code,e_name,e_num,e_email,e_department,e_address,empid;

                e_code = txtE_Id.getText();
                e_name = txtE_name.getText();
                e_num = txtE_num.getText();
                e_email = txtE_email.getText();
                e_department = txtE_department.getText();
                e_address = txtE_address.getText();
                empid = txtE_search.getText();

                try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost/car_care", "root", "");
                    PreparedStatement pst = con.prepareStatement("update employees SET e_code = ?, e_name = ?, e_num = ?,e_email = ?, e_department = ?,e_address =? where e_code = ? "))
                {
                    pst.setString(1,e_code);
                    pst.setString(2,e_name);
                    pst.setString(3,e_num);
                    pst.setString(4,e_email);
                    pst.setString(5,e_department);
                    pst.setString(6,e_address);
                    pst.setString(7,empid);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Order Updateed!");
                    table_lord();
                    txtE_Id.setText("");
                    txtE_name.setText("");
                    txtE_num.setText("");
                    txtE_email.setText("");
                    txtE_department.setText("");
                    txtE_address.setText("");

                } catch (SQLException e1){

                    e1.printStackTrace();
                }

            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empid;
                empid = txtE_search.getText();

                try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost/car_care", "root", "");
                    PreparedStatement pst = con.prepareStatement("delete from employees where e_code = ? "))
                {
                    pst.setString(1,empid);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Employee Delete!");
                    table_lord();
                    txtE_Id.setText("");
                    txtE_name.setText("");
                    txtE_num.setText("");
                    txtE_email.setText("");
                    txtE_department.setText("");
                    txtE_address.setText("");

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        SEACHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Empcode = txtE_search.getText();

                try {
                    pst = con.prepareStatement("select e_code,e_name,e_num,e_email,e_department,e_address from employees where e_code = ? ");// Use to seach emp
                    pst.setString(1,Empcode);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()==true){
                        String code = rs.getString("e_code");
                        String name = rs.getString("e_name");
                        String num = rs.getString("e_num");
                        String email = rs.getString("e_email");
                        String department = rs.getString("e_department");
                        String address = rs.getString("e_address");

                        txtE_Id.setText(code);
                        txtE_name.setText(name);
                        txtE_num.setText(num);
                        txtE_email.setText(email);
                        txtE_department.setText(department);
                        txtE_address.setText(address);
                    }else {
                        txtE_Id.setText("");
                        txtE_name.setText("");
                        txtE_num.setText("");
                        txtE_email.setText("");
                        txtE_department.setText("");
                        txtE_address.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid details");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }


    public static void main(String[] args) {

        JFrame frame = new JFrame("Employee Mange");

        frame.setSize(1100, 650);// Set a fixed size for the frame
        frame.setLocationRelativeTo(null);// Set the frame to appear in the center of the screen

        frame.setContentPane(new EmployeesManage().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
