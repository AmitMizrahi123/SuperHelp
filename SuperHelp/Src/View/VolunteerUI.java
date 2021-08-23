package View;

import Model.SingletonVolunteeringDetails;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class VolunteerUI extends JFrame {
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminUI frame = new AdminUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VolunteerUI() {
        SingletonVolunteeringDetails details = SingletonVolunteeringDetails.getInstance();

        ImageIcon image = new ImageIcon(Login.class.getResource("/Images/icon.jpg"));
        setIconImage(image.getImage());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 580);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
    }
}
