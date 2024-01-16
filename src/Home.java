import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home  {
    private JButton ORDERSButton;
    private JButton SUPPLIERSButton;
    private JButton MANAGEINVENTORYButton;
    private JButton MANAGEEMPLOYEESButton;
    private JButton REPORTSButton ;
    private JPanel mainframe;
    private JLabel Title;
    private JButton EMPLOYEESAndORDERSButton;

    public Home() {
        ORDERSButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            JFrame homeFrame = (JFrame) SwingUtilities.getWindowAncestor(mainframe);
            OrderMange orderMange = new OrderMange();

            JFrame frame = new JFrame("Order Manage");

            frame.setSize(1100, 650); // Set a fixed size for the frame
            frame.setLocationRelativeTo(null);// Set the frame to appear in the center of the screen

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setContentPane(orderMange.main);
            frame.setVisible(true);

            homeFrame.dispose();

            }
        });
        SUPPLIERSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame homeFrame = (JFrame) SwingUtilities.getWindowAncestor(mainframe);
                SuppliersManage suppliersManage = new SuppliersManage();

                JFrame frame = new JFrame("Suppliers Manage");

                frame.setSize(1100, 650); // Set a fixed size for the frame
                frame.setLocationRelativeTo(null);// Set the frame to appear in the center of the screen

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(suppliersManage.main);
                frame.setVisible(true);

                homeFrame.dispose();

            }
        });
        MANAGEINVENTORYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame homeFrame = (JFrame) SwingUtilities.getWindowAncestor(mainframe);
                InventoryManage inventoryManage = new InventoryManage();

                JFrame frame = new JFrame("Inventory Manage");

                frame.setSize(1100, 650); // Set a fixed size for the frame
                frame.setLocationRelativeTo(null);// Set the frame to appear in the center of the screen

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(inventoryManage.main);
                frame.setVisible(true);

                homeFrame.dispose();

            }
        });
        MANAGEEMPLOYEESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame homeFrame = (JFrame) SwingUtilities.getWindowAncestor(mainframe);
                EmployeesManage employeesManage = new EmployeesManage();

                JFrame frame = new JFrame("Employs Manage");

                frame.setSize(1100, 650); // Set a fixed size for the frame
                frame.setLocationRelativeTo(null);// Set the frame to appear in the center of the screen

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(employeesManage.main);
                frame.setVisible(true);

                homeFrame.dispose();

            }
        });
        REPORTSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame homeFrame = (JFrame) SwingUtilities.getWindowAncestor(mainframe);
                ReportsGenerate reportsGenerate = new ReportsGenerate();

                JFrame frame = new JFrame("Reports Generate");

                frame.setSize(1100, 650); // Set a fixed size for the frame
                frame.setLocationRelativeTo(null);// Set the frame to appear in the center of the screen

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(reportsGenerate.main);
                frame.setVisible(true);

                homeFrame.dispose();
            }
        });
        EMPLOYEESAndORDERSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame homeFrame = (JFrame) SwingUtilities.getWindowAncestor(mainframe);
                EmpAndOrder empAndOrder =  new EmpAndOrder();

                JFrame frame = new JFrame("Orders & EmployeeS Generate");

                frame.setSize(1100, 650); // Set a fixed size for the frame
                frame.setLocationRelativeTo(null);// Set the frame to appear in the center of the screen

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(empAndOrder.main);
                frame.setVisible(true);

                homeFrame.dispose();

            }
        });
    }

    public static void main(String[] args) {
        // Create and display the Home GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Car Care Dash Board");
                Home home = new Home();

                frame.setSize(1100, 650);// Set a fixed size for the frame
                frame.setLocationRelativeTo(null);// Set the frame to appear in the center of the screen

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(home.getMainframe()); // Adding ORDERSButton to the frame
                frame.setVisible(true);

            }
        });
    }
    public JPanel getMainframe() {
        return mainframe;
    }

}
