import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeesManage {
    public JPanel main;
    private JButton BACKButton;
    private JTextField txtE_Id;
    private JTextField txtE_name;
    private JTextField txtO_num;
    private JTextField txtO_email;
    private JTextField txtO_cost;
    private JButton ADDButton;
    private JButton UPDATEButton;
    private JButton DELETEButton;
    private JButton SEACHButton;
    private JTextField txtO_search;
    private JTable table1;

    public EmployeesManage() {
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

    /*
    public static void main(String[] args) {

        JFrame frame = new JFrame("OrderMange");

        frame.setSize(1100, 650);// Set a fixed size for the frame
        frame.setLocationRelativeTo(null);// Set the frame to appear in the center of the screen

        frame.setContentPane(new EmployeesManage().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }*/
}
