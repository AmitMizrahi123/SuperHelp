package View;

import Model.SingletonVolunteeringDetails;
import Model.Volunteering;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Volunteer extends JFrame {
    private JPanel contentPane;
    private JComboBox addressCB;
    private JButton searchButton;
    private JButton clearButton;
    private JList list;
    private JScrollPane scrollPane;
    private DefaultListModel DLM_result;
    private JButton takeVolunteeringButton;
    private JButton logoutButton;
    private JButton myVolunteeringButton;
    private JList listMyVolunteering;
    private JScrollPane scrollPaneMyVolunteering;
    private DefaultListModel DLMVolunteering;

    public Volunteer() {
        SingletonVolunteeringDetails details = SingletonVolunteeringDetails.getInstance();

        ImageIcon image = new ImageIcon(Login.class.getResource("/Images/icon.jpg"));
        setIconImage(image.getImage());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 620);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        list = new JList();
        list.setValueIsAdjusting(true);
        list.setVisibleRowCount(10);
        list.setBackground(SystemColor.info);
        scrollPane = new JScrollPane(list);
        scrollPane.setBounds(320, 30, 730, 500);
        contentPane.add(scrollPane);

        DLM_result = new DefaultListModel();

        addressCB = new JComboBox(details.getLocation().toArray(new String[details.getLocation().size()]));
        addressCB.setBounds(30, 30, 130, 30);
        addressCB.setFocusable(false);
        contentPane.add(addressCB);

        searchButton = new JButton("Search");
        searchButton.setBounds(170, 30, 130, 30);
        searchButton.setBorderPainted(false);
        searchButton.setFocusPainted(false);
        searchButton.setBackground(SystemColor.activeCaption);
        searchButton.setForeground(Color.WHITE);
        contentPane.add(searchButton);

        clearButton = new JButton("Clear Search");
        clearButton.setBounds(30, 80, 270, 30);
        clearButton.setBorderPainted(false);
        clearButton.setFocusPainted(false);
        clearButton.setBackground(SystemColor.activeCaption);
        clearButton.setForeground(Color.WHITE);
        contentPane.add(clearButton);

        takeVolunteeringButton = new JButton("Take me!");
        takeVolunteeringButton.setBounds(30, 150, 270, 30);
        takeVolunteeringButton.setBorderPainted(false);
        takeVolunteeringButton.setFocusPainted(false);
        takeVolunteeringButton.setBackground(SystemColor.activeCaption);
        takeVolunteeringButton.setForeground(Color.WHITE);
        contentPane.add(takeVolunteeringButton);

        myVolunteeringButton = new JButton("My Volunteering");
        myVolunteeringButton.setBounds(30, 200, 270, 30);
        myVolunteeringButton.setBorderPainted(false);
        myVolunteeringButton.setFocusPainted(false);
        myVolunteeringButton.setBackground(SystemColor.activeCaption);
        myVolunteeringButton.setForeground(Color.WHITE);
        contentPane.add(myVolunteeringButton);

        logoutButton = new JButton("Log Out");
        logoutButton.setBounds(30, 500, 270, 30);
        logoutButton.setBorderPainted(false);
        logoutButton.setFocusPainted(false);
        logoutButton.setBackground(SystemColor.activeCaption);
        logoutButton.setForeground(Color.WHITE);
        contentPane.add(logoutButton);
    }

    public void displayErrorMessage(String errorMsg) {
        JOptionPane.showMessageDialog(this,errorMsg);
    }

    public void displayMessage(String msg) {
        JOptionPane.showMessageDialog(this,msg);
    }

    public void addLogoutListener(ActionListener logout) {
        logoutButton.addActionListener(logout);
    }

    public void addMouseListener(MouseListener click) { list.addMouseListener(click); }

    public void addSearchListener(ActionListener search) {
        searchButton.addActionListener(search);
    }

    public void addClearListener(ActionListener clear) { clearButton.addActionListener(clear); }

    public void addTakeVolunteeringListener(ActionListener takeVolunteering) { takeVolunteeringButton.addActionListener(takeVolunteering); }

    public void addMyVolunteeringListener(ActionListener myVolunteering) { myVolunteeringButton.addActionListener(myVolunteering); }

    public void setListResult() {
        list.setModel(DLM_result);
    }

    public void addVolunteeringToList(Volunteering volunteering) {
        DLM_result.addElement(volunteering);
    }

    public int getListSize() {
        return DLM_result.size();
    }

    public String getAddress() {
        return addressCB.getSelectedItem().toString();
    }

    public void clearList() {
        DLM_result.removeAllElements();
    }

    public String getNameScreen() { return this.getClass().getSimpleName(); }

    public void enableSearchButton(boolean enable) {
        if (enable) {
            searchButton.setEnabled(true);
        } else {
            searchButton.setEnabled(false);
        }
    }

    public void enableClearButton(boolean enable) {
        if (enable) {
            clearButton.setEnabled(true);
        } else {
            clearButton.setEnabled(false);
        }
    }

    public void enableTakeMeButton(boolean enable) {
        if (enable) {
            takeVolunteeringButton.setEnabled(true);
        } else {
            takeVolunteeringButton.setEnabled(false);
        }
    }

    public Volunteering getSelectedItem() {
        return (Volunteering)list.getSelectedValue();
    }

    public int getSelectedIndex() {
        return list.getSelectedIndex();
    }

    public void removeItemFromList(int index) {
        DLM_result.remove(index);
    }

    public Login clickLogout() { logoutButton.doClick(); return new Login(); }

    public void displayMyVolunteering(ArrayList<Volunteering> myVolunteering) {
        JList list = new JList((ListModel) myVolunteering);
        JScrollPane scrollPane = new JScrollPane(list);
        JOptionPane.showMessageDialog(null, scrollPane, "volunteerings", JOptionPane.OK_CANCEL_OPTION);
    }
}
