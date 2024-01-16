import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class InventoryManage {
    public JPanel main;
    private JButton BACKButton;
    private JTextField txtI_Id;
    private JTextField txtI_name;
    private JButton ADDButton;
    private JButton UPDATEButton;
    private JButton DELETEButton;
    private JButton SEACHButton;
    private JTextField txtI_search;
    private JTextField txtI_quntity;
    private JTextField txtI_price;
    private JTable table1;
    private JScrollPane table01;
    private JTable table02;

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

    void table01_lord() {
        try {
            pst = con.prepareStatement("SELECT I_code, I_name, I_quantity, I_unit price, FROM inventory");
            ResultSet rs=pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e012) {
            e012.printStackTrace();
        }
    }

    void table02_lord()  {
        try {
            pst = con.prepareStatement("SELECT I_name, I_quantity FROM inventory");
            ResultSet rs=pst.executeQuery();
            table02.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e012) {
            e012.printStackTrace();
        }
    }

    public InventoryManage() {
    BACKButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame inventoryFrame = (JFrame) SwingUtilities.getWindowAncestor(main);
            inventoryFrame.dispose(); // Close the OrderManage frame

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
