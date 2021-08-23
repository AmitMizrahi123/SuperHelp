package View;

import Model.SingletonVolunteeringDetails;
import Model.Volunteering;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class AdminUI extends JFrame {
    private JPanel contentPane;
    private JTextPane mainHeaderText;
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel genderLabel;
    private JLabel adressLabel;
    private JLabel phoneNumberLabel;
    private JTextPane problemText;
    private JButton addButton;
    private JTextField nameText;
    private JTextField phoneNumberText;
    private JComboBox<Integer> ageCB;
    private JRadioButton genderMaleRB;
    private JRadioButton genderFemaleRB;
    private ButtonGroup genderBG;
    private JComboBox<String> addressCB;
    private JList list;
    private DefaultListModel DLM_result;
    private JButton deleteButton;
    private JButton logoutButton;

    private Font headerFont() { return new Font("Georgia", Font.BOLD | Font.ITALIC, 28); }

    private Font labelFont() { return new Font("Georgia", Font.BOLD | Font.ITALIC, 18); }

    public AdminUI() {
        // The title is been set already - LoginController

        SingletonVolunteeringDetails details = SingletonVolunteeringDetails.getInstance();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1300, 650);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        mainHeaderText = new JTextPane();
        mainHeaderText.setText("Volunteering Details");
        mainHeaderText.setFont(headerFont());
        mainHeaderText.setEditable(false);
        mainHeaderText.setBackground(SystemColor.activeCaption);
        mainHeaderText.setForeground(Color.WHITE);
        mainHeaderText.setBounds(30, 10, 310, 40);
        contentPane.add(mainHeaderText);

        nameLabel = new JLabel();
        nameLabel.setText("Name:");
        nameLabel.setFont(labelFont());
        nameLabel.setBackground(SystemColor.activeCaption);
        nameLabel.setBounds(15, 80, 85, 22);
        contentPane.add(nameLabel);

        nameText = new JTextField();
        nameText.setBounds(120, 80, 195, 22);
        nameText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nameText.getText().equals("Enter your name..")) {
                    nameText.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameText.getText().isEmpty()) {
                    nameText.setText("Enter your name..");
                }
            }
        });
        contentPane.add(nameText);

        ageLabel = new JLabel();
        ageLabel.setText("Age:");
        ageLabel.setFont(labelFont());
        ageLabel.setBackground(SystemColor.activeCaption);
        ageLabel.setBounds(15, 120, 85, 22);
        contentPane.add(ageLabel);

        ageCB = new JComboBox<>(details.getAge());
        ageCB.setBounds(120, 120, 195, 22);
        contentPane.add(ageCB);

        genderLabel = new JLabel();
        genderLabel.setText("Gender:");
        genderLabel.setFont(labelFont());
        genderLabel.setBackground(SystemColor.activeCaption);
        genderLabel.setBounds(15, 160, 83, 22);
        contentPane.add(genderLabel);

        genderMaleRB = new JRadioButton(details.getGender()[0]);
        genderMaleRB.setBounds(150, 160, 80, 22);
        genderMaleRB.setActionCommand(details.getGender()[0]);
        contentPane.add(genderMaleRB);

        genderFemaleRB = new JRadioButton(details.getGender()[1]);
        genderFemaleRB.setBounds(250, 160, 80, 22);
        genderFemaleRB.setActionCommand(details.getGender()[1]);
        contentPane.add(genderFemaleRB);

        genderBG = new ButtonGroup();
        genderBG.add(genderMaleRB);
        genderBG.add(genderFemaleRB);

        adressLabel = new JLabel();
        adressLabel.setText("Address:");
        adressLabel.setFont(labelFont());
        adressLabel.setBackground(SystemColor.activeCaption);
        adressLabel.setBounds(15, 200, 83, 22);
        contentPane.add(adressLabel);

        addressCB = new JComboBox(details.getLocation().toArray(new String[details.getLocation().size()]));
        addressCB.setBounds(120, 200, 195, 22);
        contentPane.add(addressCB);

        phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setFont(labelFont());
        phoneNumberLabel.setBackground(SystemColor.activeCaption);
        phoneNumberLabel.setBounds(15, 240, 150, 22);
        contentPane.add(phoneNumberLabel);

        phoneNumberText = new JTextField();
        phoneNumberText.setBounds(120, 270, 195, 22);
        contentPane.add(phoneNumberText);

        problemText = new JTextPane();
        problemText.setBounds(15, 310, 300, 150);
        problemText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (problemText.getText().equals("Enter the problem..")) {
                    problemText.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (problemText.getText().isEmpty()) {
                    problemText.setText("Enter the problem..");
                }
            }
        });
        contentPane.add(problemText);

        addButton = new JButton("Add");
        addButton.setBounds(45, 480, 240, 50);
        addButton.setBorderPainted(false);
        addButton.setFocusPainted(false);
        addButton.setBackground(SystemColor.activeCaption);
        addButton.setForeground(Color.WHITE);
        contentPane.add(addButton);

        DLM_result = new DefaultListModel();

        list = new JList();
        list.setBounds(380, 60, 890, 400);
        list.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        contentPane.add(list);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(710, 480, 240, 50);
        deleteButton.setBorderPainted(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setBackground(SystemColor.activeCaption);
        deleteButton.setForeground(Color.WHITE);
        contentPane.add(deleteButton);

        logoutButton = new JButton("Log out");
        logoutButton.setBounds(480, 550, 300, 50);
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

    public void addVolunteeringListener(ActionListener add) { addButton.addActionListener(add); }

    public void deleteVolunteeringListener(ActionListener delete) { deleteButton.addActionListener(delete); }

    public void addMouseListener(MouseListener click) { list.addMouseListener(click); }

    public String getName() { return nameText.getText(); }

    public String getGender() {
        return genderBG.getSelection().getActionCommand();
    }

    public String getPhoneNumber() {
        return phoneNumberText.getText();
    }

    public String getAddress() {
        return addressCB.getSelectedItem().toString();
    }

    public String getProblem() {
        return problemText.getText();
    }

    public int getAge() {
        return Integer.parseInt(ageCB.getSelectedItem().toString());
    }

    public void addVolunteeringToList(Volunteering volunteering) {
        DLM_result.addElement(volunteering);
    }

    public void setListResult() {
        list.setModel(DLM_result);
    }

    public Volunteering getSelectedItem() {
        return (Volunteering) list.getSelectedValue();
    }

    public int getSelectedIndex() {
        return list.getSelectedIndex();
    }

    public void removeItemFromList(int index) {
        DLM_result.remove(index);
    }
}
