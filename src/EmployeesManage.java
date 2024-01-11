import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeesManage {
    public JPanel main;
    private JButton BACKButton;
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
}
