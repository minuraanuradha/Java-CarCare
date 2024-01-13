import net.proteanit.sql.DbUtils;

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
    private JButton UPDATEButton;
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
            pst = con.prepareStatement("SELECT e_code,e_name,e_num,e_email,e_department,e_address FROM empandord");
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
    }


}
