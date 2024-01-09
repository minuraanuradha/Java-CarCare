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

    }
    public JPanel getMainframe() {
        return mainframe;
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
}
