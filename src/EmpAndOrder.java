import net.proteanit.sql.DbUtils;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EmpAndOrder {
    public JPanel main;
    private JButton BACKButton;
    private JTextField txtE_Id;
    private JTextField txtO_Id;
    private JButton ADDButton;
    private JButton DELETEButton;
    private JButton SEACHButton;
    private JTextField txtE_search;
    private JLabel address;
    private JTextField txtE_status;
    private JTable EOtable;
    private JTable emptable;
    private JTable ordtable;

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

    void e_table_lord() {
        try {
            pst = con.prepareStatement("SELECT e_code,e_name,e_department FROM employees");
            ResultSet rs=pst.executeQuery();
            emptable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e012) {
            e012.printStackTrace();
        }
    }

    void o_table_lord() {
        try {
            pst = con.prepareStatement("SELECT O_code,O_name,O_email FROM orders");
            ResultSet rs=pst.executeQuery();
            ordtable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e012) {
            e012.printStackTrace();
        }
    }

    void eo_table_lord() {
        try {
            pst = con.prepareStatement("SELECT id,e_code,e_name,O_code,O_name,e_department,o_status FROM empandord");
            ResultSet rs=pst.executeQuery();
            EOtable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e012) {
            e012.printStackTrace();
        }
    }

    public EmpAndOrder() {

        connect();
        e_table_lord();
        o_table_lord();
        eo_table_lord();
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
                String e_code, o_code, e_name, o_name, e_department, o_status;

                e_code = txtE_Id.getText();
                o_code = txtO_Id.getText();

                // Retrieve details from the employees table
                try {
                    pst = con.prepareStatement("SELECT e_name, e_department FROM employees WHERE e_code = ?");
                    pst.setString(1, e_code);
                    ResultSet rsE = pst.executeQuery();

                    if (rsE.next()) {
                        e_name = rsE.getString("e_name");
                        e_department = rsE.getString("e_department");

                        // Retrieve details from the orders table
                        pst = con.prepareStatement("SELECT O_name FROM orders WHERE O_code = ?");
                        pst.setString(1, o_code);
                        ResultSet rsO = pst.executeQuery();

                        if (rsO.next()) {
                            o_name = rsO.getString("O_name");

                            // Get O_status from the text field
                            o_status = txtE_status.getText();

                            // Insert into empandord table
                            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/car_care", "root", "");
                                 PreparedStatement pst = con.prepareStatement("INSERT INTO empandord(e_code, e_name, O_code, O_name, e_department, O_status) VALUES (?, ?, ?, ?, ?, ?)")) {
                                pst.setString(1, e_code);
                                pst.setString(2, e_name);
                                pst.setString(3, o_code);
                                pst.setString(4, o_name);
                                pst.setString(5, e_department);
                                pst.setString(6, o_status);
                                pst.executeUpdate();

                                JOptionPane.showMessageDialog(null, "Added!");
                                eo_table_lord();
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Order not found for code: " + o_code);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Employee not found for code: " + e_code);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                // Send email
                //sendEmail("recipient@example.com", "Subject", "Body: Employee and Order Details - " + e_code + ", " + o_code);
            }
        });
        /*ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String e_code, e_name, O_code, O_name, e_department, O_status;

                e_code = txtE_Id.getText();
                O_code = txtO_Id.getText();
                O_status = txtE_status.getText();

                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/car_care", "root", "");
                     PreparedStatement pst = con.prepareStatement("select e_name,e_department from employees where e_code = ?")) {
                    pst.setString(1, e_code);
                    ResultSet rsE = pst.executeQuery();

                    if (rsE.next()) {
                        e_name = rsE.getString("e_name");
                        e_department = rsE.getString("e_department");

                        (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/car_care", "root", "");
                        PreparedStatement pst = con.prepareStatement("select O_name from orders where O_code = ?"))
                        {
                            pst.setString(1, O_code);
                            ResultSet rsO = pst.executeQuery();

                            if (rsO.next()) {
                                O_name = rsO.getString("O_name");

                                O_status = txtE_status.getText();

                                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/car_care", "root", "");
                                     PreparedStatement pst = con.prepareStatement("INSERT INTO empandord(e_code, e_name, O_code, O_name, e_department, O_status) VALUES (?, ?, ?, ?, ?, ?)")) {
                                    pst.setString(1, e_code);
                                    pst.setString(2, e_name);
                                    pst.setString(3, O_code);
                                    pst.setString(4, O_name);
                                    pst.setString(5, e_department);
                                    pst.setString(6, O_status);
                                    pst.executeUpdate();

                                    JOptionPane.showMessageDialog(null, "Added!");
                                    eo_table_lord();


                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Order not found for code: " + O_code);
                            }else{
                            JOptionPane.showMessageDialog(null, "Employee not found for code: " + e_code);
                        }
                        } catch(SQLException ex){
                            throw new RuntimeException(ex);
                        }
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } ;
            }


        }*/

        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emordpid;
                emordpid = txtE_search.getText();

                try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost/car_care", "root", "");
                    PreparedStatement pst = con.prepareStatement("delete from empandord where id = ? "))
                {
                    pst.setString(1,emordpid);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Employee Delete!");
                    eo_table_lord();
                    txtE_Id.setText("");
                    txtO_Id.setText("");
                    txtE_status.setText("");

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
                    pst = con.prepareStatement("select e_code, e_name, O_code, O_name, e_department, O_status from empandord where id = ? ");// Use to seach emp
                    pst.setString(1,Empcode);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()==true){
                        String code = rs.getString("e_code");
                        String name = rs.getString("e_name");
                        String ocode = rs.getString("O_code");
                        String oname = rs.getString("O_name");
                        String department = rs.getString("e_department");
                        String status = rs.getString("O_status");

                        txtE_Id.setText(code);
                        txtO_Id.setText(ocode);
                        txtE_status.setText(status);
                    }else {
                        txtE_Id.setText("");
                        txtO_Id.setText("");
                        txtE_status.setText("");
                        JOptionPane.showMessageDialog(null,"Invalid details");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /*private void sendEmail(String to, String subject, String body) {
        // Set up JavaMail properties
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "your_smtp_server");
        properties.put("mail.smtp.port", "your_smtp_port");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create a Session object
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("your_email@example.com", "your_email_password");
            }
        });

        try {
            // Create a MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set the recipients, subject, and body of the email
            message.setFrom(new InternetAddress("your_email@example.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(body);

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully...");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }*/
    }
