package View;

import java.awt.*;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;

import Model.Client;
import Model.Volunteering;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;


public class Manager extends JFrame {
    private DefaultListModel DLM_result;
    private JList list;
    private JPanel contentPane;
    private JButton logoutButton;
    private JButton removeButton;
    private JButton addButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Manager frame = new Manager();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Manager() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 550);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        Map<TextAttribute, Object> attributes = new HashMap<TextAttribute, Object>(); // Letter spacing
        attributes.put(TextAttribute.TRACKING, 0.3);

        list = new JList();
        list.setBounds(20, 20, 645, 356);
        contentPane.add(list);

        addButton = new JButton("Add");
        addButton.setBounds(80, 395, 120, 30);
        addButton.setBorderPainted(false);
        addButton.setFocusPainted(false);
        addButton.setBackground(SystemColor.activeCaption);
        addButton.setForeground(Color.WHITE);
        addButton.setFont(addButton.getFont().deriveFont(attributes));
        contentPane.add(addButton);

        removeButton = new JButton("Remove");
        removeButton.setBounds(485, 395, 120, 30);
        removeButton.setBorderPainted(false);
        removeButton.setFocusPainted(false);
        removeButton.setBackground(SystemColor.activeCaption);
        removeButton.setForeground(Color.WHITE);
        removeButton.setForeground(Color.WHITE);
        removeButton.setFont(removeButton.getFont().deriveFont(attributes));
        contentPane.add(removeButton);

        logoutButton = new JButton("logout");
        logoutButton.setBounds(260, 450, 150, 40);
        logoutButton.setBorderPainted(false);
        logoutButton.setFocusPainted(false);
        logoutButton.setBackground(SystemColor.activeCaption);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(logoutButton.getFont().deriveFont(attributes));
        contentPane.add(logoutButton);

        DLM_result = new DefaultListModel();
    }

    public void addGoBackListener(ActionListener goBack) {
        logoutButton.addActionListener(goBack);
    }

    public void displayErrorMessage(String errorMsg) {
        JOptionPane.showMessageDialog(this, errorMsg);
    }

    public void setListResult() {
        list.setModel(DLM_result);
    }

    public void addClientToListElement(Client user) {
        DLM_result.addElement(user);
    }
}
