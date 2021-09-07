package View;

import java.awt.*;
import javax.swing.*;

import javax.swing.border.EmptyBorder;

import Model.Client;

import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.JOptionPane.showConfirmDialog;


public class Manager extends JFrame {
    private DefaultListModel DLM_result;
    private JList list;
    private JScrollPane scrollPane;
    private JPanel contentPane;
    private JButton logoutButton;
    private JButton deleteButton;
    private JButton addButton;

    public Manager() {
        ImageIcon image = new ImageIcon(Login.class.getResource("/Images/icon.jpg"));
        setIconImage(image.getImage());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 550);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>(); // Letter spacing
        attributes.put(TextAttribute.TRACKING, 0.3);

        DLM_result = new DefaultListModel();

        list = new JList();
        list.setVisibleRowCount(10);
        scrollPane = new JScrollPane(list);
        scrollPane.setBounds(20, 20, 645, 356);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        contentPane.add(scrollPane);

        addButton = new JButton("Add");
        addButton.setBounds(80, 395, 120, 30);
        addButton.setBorderPainted(false);
        addButton.setFocusPainted(false);
        addButton.setBackground(SystemColor.activeCaption);
        addButton.setForeground(Color.WHITE);
        addButton.setFont(addButton.getFont().deriveFont(attributes));
        contentPane.add(addButton);

        deleteButton = new JButton("Remove");
        deleteButton.setBounds(485, 395, 120, 30);
        deleteButton.setBorderPainted(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setBackground(SystemColor.activeCaption);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(deleteButton.getFont().deriveFont(attributes));
        contentPane.add(deleteButton);

        logoutButton = new JButton("logout");
        logoutButton.setBounds(260, 450, 150, 40);
        logoutButton.setBorderPainted(false);
        logoutButton.setFocusPainted(false);
        logoutButton.setBackground(SystemColor.activeCaption);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(logoutButton.getFont().deriveFont(attributes));
        contentPane.add(logoutButton);
    }

    public String addClientEmail() {
        JFrame f = new JFrame();
        String email = JOptionPane.showInputDialog("Enter email");

        if (email == null || (email != null && ("".equals(email)))) {
            f.dispose();
            return null;
        }

        return email;
    }

    public String addClientPassword() {
        JFrame f = new JFrame();
        JPasswordField pf = new JPasswordField();
        showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        return new String(pf.getPassword());
    }

    public String addClientFirstName() {
        JFrame f = new JFrame();
        String firstName = JOptionPane.showInputDialog("Enter first name");
        return firstName;
    }

    public String addClientLastName() {
        JFrame f = new JFrame();
        String lastName = JOptionPane.showInputDialog("Enter last name");
        return lastName;
    }

    public String addClientAddress() {
        JFrame f = new JFrame();
        String address = JOptionPane.showInputDialog("Enter address");
        return address;
    }

    public String addClientPermission() {
        JFrame f = new JFrame();
        String permission = JOptionPane.showInputDialog("Enter permission");
        return permission;
    }

    public String addClientPhoneNumber() {
        JFrame f = new JFrame();
        String phoneNumber = JOptionPane.showInputDialog("Enter phone number");
        return phoneNumber;
    }

    public void addGoBackListener(ActionListener goBack) {
        logoutButton.addActionListener(goBack);
    }

    public void deleteClientListener(ActionListener deleteCleint) { deleteButton.addActionListener(deleteCleint); }

    public void addClientListener(ActionListener addClient) { addButton.addActionListener(addClient); }

    public void displayErrorMessage(String errorMsg) {
        JOptionPane.showMessageDialog(this, errorMsg);
    }

    public int displayQuestionMessage(String message) { return showConfirmDialog(this, message, "Delete Client", JOptionPane.YES_NO_OPTION); }

    public void setListResult() {
        list.setModel(DLM_result);
    }

    public void addClientToListElement(Client client) {
        DLM_result.addElement(client);
    }

    public Client getSelectedItem() {
        return (Client) list.getSelectedValue();
    }

    public void removeItemFromList(int index) {
        DLM_result.removeElementAt(index);
    }

    public int getSelectedIndex() {
        return list.getSelectedIndex();
    }

    public String getNameScreen() { return this.getClass().getSimpleName(); }

    public Login clickLogout() { logoutButton.doClick(); return new Login(); }
}
