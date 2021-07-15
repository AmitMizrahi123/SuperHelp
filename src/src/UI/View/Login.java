package View;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class Login extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
    private JPanel contentPanel;
    private JLabel mainHeaderLabel;
    private JLabel txtUserNameLabel;
    private JLabel txtPasswordLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public String mainHeaderText() {
        return "Welcome to Super Help";
    }

    public Font mainHeaderFont() { return new Font("Georgia", Font.BOLD | Font.ITALIC, 28); }

    public Font formFont() { return new Font("Georgia", Font.BOLD | Font.ITALIC, 20); }

    public Login() { // this == JFrame
        this.setLayout(new FlowLayout());
        this.setSize(500, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        mainHeaderLabel = new JLabel(mainHeaderText());
        mainHeaderLabel.setHorizontalAlignment(JLabel.CENTER);
        mainHeaderLabel.setVerticalAlignment(JLabel.TOP);
        mainHeaderLabel.setForeground(SystemColor.activeCaption);
        mainHeaderLabel.setFont(mainHeaderFont());
        this.add(mainHeaderLabel);

        txtUserNameLabel = new JLabel("Username: ");
        txtUserNameLabel.setHorizontalAlignment(JLabel.CENTER);
        txtUserNameLabel.setVerticalAlignment(JLabel.CENTER);
        mainHeaderLabel.setFont(formFont());
        this.add(txtUserNameLabel);
    }
}
