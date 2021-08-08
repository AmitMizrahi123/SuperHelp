package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
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
    JLabel adsButton;
    JLabel mainHeader;
    JLabel emailLabel;
    JLabel passwordLabel;

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

    public int getAppHeight() { return 430; }

    public String mainHeaderText() {
        return "Welcome to Super Help";
    }

    public Login() {
        // TODO Add Images

        setTitle("SuperHelp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, getAppWidth(), getAppHeight());
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        emailField = new JTextField();
        emailField.setBounds(180, 220, 170, 25);
        contentPane.add(emailField);
        emailField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(180, 251, 170, 25);
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

        contactUsButton  = new JButton("Contact us");
        contactUsButton.setBounds(328, 136, 99, 23);
        contactUsButton.setBorderPainted(false);
        contactUsButton.setFocusPainted(false);
        contactUsButton.setBackground(SystemColor.activeCaption);
        contactUsButton.setForeground(Color.WHITE);
        contentPane.add(contactUsButton);

        adsButton = new JLabel("want your ads here?");
        adsButton.setBounds(315, 114, 133, 14);
        contentPane.add(adsButton);

        mainHeader = new JLabel(mainHeaderText());
        mainHeader.setForeground(SystemColor.activeCaption);
        mainHeader.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 28));
        mainHeader.setBounds(40, 0, 360, 43);
        contentPane.add(mainHeader);

        emailLabel = new JLabel("Email:");
        emailLabel.setForeground(SystemColor.activeCaptionText);
        emailLabel.setFont(new Font("Georgia", Font.BOLD, 22));
        emailLabel.setBounds(10, 209, 194, 43);
        contentPane.add(emailLabel);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Georgia", Font.BOLD, 22));
        passwordLabel.setBounds(11, 231, 129, 60);
        contentPane.add(passwordLabel);

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
