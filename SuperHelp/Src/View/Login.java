package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serial;


public class Login extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;

    JPanel contentPane;
    JTextField emailField;
    JPasswordField passwordField;
    JButton loginButton;
    JButton registerButton;
    JButton contactUsButton;
    JLabel adLabel;
    JLabel mainHeader;
    JLabel emailLabel;
    JLabel passwordLabel;
    JLabel adImageLabel;

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

    public int getAppWidth() { return 451; }

    public int getAppHeight() { return 480; }

    public String title() { return "SuperHelp"; }

    public String mainHeaderText() {
        return "Welcome to Super Help";
    }

    public Login() {
        // TODO Add Images

        setTitle(title());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, getAppWidth(), getAppHeight());
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        mainHeader = new JLabel(mainHeaderText());
        mainHeader.setForeground(SystemColor.activeCaption);
        mainHeader.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 28));
        mainHeader.setBounds(40, 0, 360, 43);
        contentPane.add(mainHeader);

        emailLabel = new JLabel("Email:");
        emailLabel.setForeground(SystemColor.activeCaptionText);
        emailLabel.setFont(new Font("Georgia", Font.BOLD, 22));
        emailLabel.setBounds(10, 229, 194, 43);
        contentPane.add(emailLabel);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Georgia", Font.BOLD, 22));
        passwordLabel.setBounds(11, 251, 129, 60);
        contentPane.add(passwordLabel);

        emailField = new JTextField();
        emailField.setBounds(180, 240, 200, 25);
        contentPane.add(emailField);
        emailField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 271, 200, 25);
        contentPane.add(passwordField);

        loginButton = new JButton("Log in");
        loginButton.setBounds(67, 344, 85, 23);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setBackground(SystemColor.activeCaption);
        loginButton.setForeground(Color.WHITE);
        contentPane.add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(296, 344, 85, 23);
        registerButton.setBorderPainted(false);
        registerButton.setFocusPainted(false);
        registerButton.setBackground(SystemColor.activeCaption);
        registerButton.setForeground(Color.WHITE);
        contentPane.add(registerButton);

        contactUsButton  = new JButton("C o n t a c t   u s");
        contactUsButton.setBounds(150, 390, 145, 40);
        contactUsButton.setBorderPainted(false);
        contactUsButton.setFocusPainted(false);
        contactUsButton.setBackground(SystemColor.activeCaption);
        contactUsButton.setForeground(Color.WHITE);
        contentPane.add(contactUsButton);

        adLabel = new JLabel("A D s :");
        adLabel.setBounds(190, 54, 133, 14);
        contentPane.add(adLabel);

        adImageLabel = new JLabel("");
        BufferedImage img = null;
        try {
            img = ImageIO.read(Login.class.getResource("/Images/screen.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(400, 130, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        adImageLabel.setIcon(imageIcon);
        adImageLabel.setBounds(17, 80, 445, 130);
        contentPane.add(adImageLabel);

        // TODO Add Images
    }

    public void addLoginListener(ActionListener actionLoginListener) {
        loginButton.addActionListener(actionLoginListener);
    }

    public void addRegisterListener(ActionListener actionRegisterListener) {
        registerButton.addActionListener(actionRegisterListener);
    }

    public void addContactListener(ActionListener actionContactListener) {
        contactUsButton.addActionListener(actionContactListener);
    }

    public void displaySuccessMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void displayErrorMessage(String errorMsg) {
        JOptionPane.showMessageDialog(this,errorMsg);
    }

    public String getEmailField() {
        return emailField.getText();
    }

    public String getPasswordField() {
        return String.valueOf(passwordField.getPassword());
    }
}
