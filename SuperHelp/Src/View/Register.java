package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.Serial;

public class Register extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;

    JPanel contentPane;
    JLabel registerHeaderLabel;
    JLabel emailLabel;
    JLabel passwordLabel;
    JLabel firstNameLabel;
    JLabel lastNameLabel;
    JLabel addressLabel;
    JLabel phoneNumberLabel;
    JLabel permissionLabel;

    JLabel emailError;
    JLabel passwordError;
    JLabel firstNameError;
    JLabel lastNameError;
    JLabel addressError;
    JLabel phoneNumberError;


    JTextField emailField;
    JPasswordField passwordField;
    JTextField firstNameField;
    JTextField lastNameField;
    JTextField addressField;
    JTextField phoneNumberField;
    JComboBox permissionComboBox;

    JButton registerButton;
    JButton goBackButton;

    String[] permissions = { "Admin", "Volunteer" };

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Register frame = new Register();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Font txtRegisterFont() { return new Font("Tahoma", Font.BOLD, 30); }

    private Font txtPanelFont() { return new Font("Tahoma", Font.BOLD, 20); }

    private Font txtComboBoxFont() { return new Font("Tahoma", Font.BOLD, 15); }

    private Font errorFont() { return new Font("Tahoma", Font.BOLD, 15); }

    private Color errorColor() { return Color.red; }

    public Register() {
        ImageIcon image = new ImageIcon(Login.class.getResource("/Images/icon.jpg"));
        setIconImage(image.getImage());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 840, 660);
        setTitle("Super Help");
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        registerHeaderLabel = new JLabel("Register New Account");
        registerHeaderLabel.setFont(txtRegisterFont());
        registerHeaderLabel.setBounds(50, 0, 500, 100);
        contentPane.add(registerHeaderLabel);

        emailLabel = new JLabel("Email: ");
        emailLabel.setFont(txtPanelFont());
        emailLabel.setBounds(30, 100, 100, 30);
        contentPane.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(350, 100, 250, 30);
        contentPane.add(emailField);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(txtPanelFont());
        passwordLabel.setBounds(30, 160, 500, 30);
        contentPane.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(350, 160, 250, 30);
        contentPane.add(passwordField);

        firstNameLabel = new JLabel("First Name: ");
        firstNameLabel.setFont(txtPanelFont());
        firstNameLabel.setBounds(30, 220, 500, 30);
        contentPane.add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(350, 220, 250, 30);
        contentPane.add(firstNameField);

        lastNameLabel = new JLabel("Last Name: ");
        lastNameLabel.setFont(txtPanelFont());
        lastNameLabel.setBounds(30, 280, 500, 30);
        contentPane.add(lastNameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(350, 280, 250, 30);
        contentPane.add(lastNameField);

        addressLabel = new JLabel("Address: ");
        addressLabel.setFont(txtPanelFont());
        addressLabel.setBounds(30, 340, 500, 30);
        contentPane.add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(350, 340, 250, 30);
        contentPane.add(addressField);

        phoneNumberLabel = new JLabel("Phone Number: ");
        phoneNumberLabel.setFont(txtPanelFont());
        phoneNumberLabel.setBounds(30, 400, 500, 30);
        contentPane.add(phoneNumberLabel);

        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(350, 400, 250, 30);
        contentPane.add(phoneNumberField);

        permissionLabel = new JLabel("Permission: ");
        permissionLabel.setFont(txtPanelFont());
        permissionLabel.setBounds(30, 460, 500, 30);
        contentPane.add(permissionLabel);

        permissionComboBox = new JComboBox(permissions);
        permissionComboBox.setFont(txtComboBoxFont());
        permissionComboBox.setBounds(350, 460, 250, 30);
        contentPane.add(permissionComboBox);

        registerButton = new JButton("Register");
        registerButton.setFont(txtPanelFont());
        registerButton.setBounds(200, 530, 150, 50);
        registerButton.setBorderPainted(false);
        registerButton.setFocusPainted(false);
        registerButton.setBackground(SystemColor.activeCaption);
        registerButton.setForeground(Color.WHITE);
        contentPane.add(registerButton);

        goBackButton = new JButton("Go Back");
        goBackButton.setFont(txtPanelFont());
        goBackButton.setBounds(500, 530, 150, 50);
        goBackButton.setBorderPainted(false);
        goBackButton.setFocusPainted(false);
        goBackButton.setBackground(SystemColor.activeCaption);
        goBackButton.setForeground(Color.WHITE);
        contentPane.add(goBackButton);
    }

    //<editor-fold desc="Error Messages">
    public void emailError() {
        emailError = new JLabel("Invalid email");
        emailError.setBounds(650, 60, 500, 100);
        emailError.setFont(errorFont());
        emailError.setForeground(errorColor());
        contentPane.add(emailError);
        contentPane.validate();
        contentPane.repaint();
    }

    public void removeEmailError() {
        try {
            Container emailParent = emailError.getParent();
            emailParent.remove(emailError);
            emailParent.validate();
            emailParent.repaint();
        } catch (Exception e) {
            // Do Nothing
        }
    }

    public void passwordError() {
        passwordError = new JLabel("Invalid password");
        passwordError.setBounds(650, 120, 500, 100);
        passwordError.setFont(errorFont());
        passwordError.setForeground(errorColor());
        contentPane.add(passwordError);
        contentPane.validate();
        contentPane.repaint();
    }

    public void removePasswordError() {
        try {
            Container passwordParent = passwordError.getParent();
            passwordParent.remove(passwordError);
            passwordParent.validate();
            passwordParent.repaint();
        } catch (Exception e) {
            // Do Nothing
        }
    }

    public void firstNameError() {
        firstNameError = new JLabel("Invalid first name");
        firstNameError.setBounds(650, 180, 500, 100);
        firstNameError.setFont(errorFont());
        firstNameError.setForeground(errorColor());
        contentPane.add(firstNameError);
        contentPane.validate();
        contentPane.repaint();
    }

    public void removeFirstNameError() {
        try {
            Container firstNameParent = firstNameError.getParent();
            firstNameParent.remove(firstNameError);
            firstNameParent.validate();
            firstNameParent.repaint();
        } catch (Exception e) {
            // Do Nothing
        }
    }

    public void lastNameError() {
        lastNameError = new JLabel("Invalid last name");
        lastNameError.setBounds(650, 240, 500, 100);
        lastNameError.setFont(errorFont());
        lastNameError.setForeground(errorColor());
        contentPane.add(lastNameError);
        contentPane.validate();
        contentPane.repaint();
    }

    public void removeLastNameError() {
        try {
            Container lastNameParent = lastNameError.getParent();
            lastNameParent.remove(lastNameError);
            lastNameParent.validate();
            lastNameParent.repaint();
        } catch (Exception e) {
            // Do Nothing
        }
    }

    public void addressError() {
        addressError = new JLabel("Invalid address");
        addressError.setBounds(650, 300, 500, 100);
        addressError.setFont(errorFont());
        addressError.setForeground(errorColor());
        contentPane.add(addressError);
        contentPane.validate();
        contentPane.repaint();
    }

    public void removeAddressError() {
        try {
            Container addressParent = addressError.getParent();
            addressParent.remove(addressError);
            addressParent.validate();
            addressParent.repaint();
        } catch (Exception e) {
            // Do Nothing
        }
    }

    public void phoneNumberError() {
        phoneNumberError = new JLabel("Invalid phone number");
        phoneNumberError.setBounds(650, 360, 500, 100);
        phoneNumberError.setFont(errorFont());
        phoneNumberError.setForeground(errorColor());
        contentPane.add(phoneNumberError);
        contentPane.validate();
        contentPane.repaint();
    }

    public void removePhoneNumberError() {
        try {
            Container phoneNumberParent = phoneNumberError.getParent();
            phoneNumberParent.remove(phoneNumberError);
            phoneNumberParent.validate();
            phoneNumberParent.repaint();
        } catch (Exception e) {
            // Do Nothing
        }
    }

    //</editor-fold>

    public void addGoBackListener(ActionListener actionGoBackListener) {
        goBackButton.addActionListener(actionGoBackListener);
    }

    public void addRegisterListener(ActionListener actionRegisterListener) {
        registerButton.addActionListener(actionRegisterListener);
    }

    public String getEmailField() {
        return emailField.getText();
    }

    public void setEmailField(String email) {
        emailField.setText(email);
    }

    public String getPasswordField() {
        return String.valueOf(passwordField.getPassword());
    }

    public void setPasswordField(String password) {
        passwordField.setText(password);
    }

    public String getFirstNameField() {
        return firstNameField.getText();
    }

    public void setFirstNameField(String firstName) {
        firstNameField.setText(firstName);
    }

    public String getLastNameField() {
        return lastNameField.getText();
    }

    public void setLastNameField(String lastName) {
        lastNameField.setText(lastName);
    }

    public String getAddressField() {
        return addressField.getText();
    }

    public void setAddressField(String address) {
        addressField.setText(address);
    }

    public String getPhoneNumberField() {
        return phoneNumberField.getText();
    }

    public void setPhoneNumber(String phoneNumber) {
        phoneNumberField.setText(phoneNumber);
    }

    public String getPermissionComboBox() {
        return permissionComboBox.getSelectedItem().toString();
    }

    public void displaySuccessMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public void displayErrorMessage(String errorMsg) {
        JOptionPane.showMessageDialog(this,errorMsg);
    }

    public String getNameScreen() { return this.getClass().getSimpleName(); }

    public Login clickGoBack() { goBackButton.doClick(); return new Login(); }

    public Login clickRegister() { registerButton.doClick(); return new Login(); }
}
