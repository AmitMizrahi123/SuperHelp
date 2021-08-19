package View;

import Model.SingletonVolunteeringDetails;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class AdminUI extends JFrame {
    private JPanel contentPane;
    private JTextPane mainHeaderText;
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel genderLabel;
    private JLabel adressLabel;
    private JTextPane problemText;
    private JButton addButton;
    private JTextPane nameText;
    private JComboBox<Integer> ageCB;
    private JRadioButton genderMaleRB;
    private JRadioButton genderFemaleRB;
    private JComboBox<String> addressCB;
    private JList list;
    private DefaultListModel DLM_result;
    private JButton deleteButton;
    private JButton logoutButton;


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

    private Font headerFont() { return new Font("Georgia", Font.BOLD | Font.ITALIC, 28); }

    private Font labelFont() { return new Font("Georgia", Font.BOLD | Font.ITALIC, 18); }

    public AdminUI() {
        // The title is been set already - LoginController

        SingletonVolunteeringDetails details = SingletonVolunteeringDetails.getInstance();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 610);
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

        nameText = new JTextPane();
        nameText.setBounds(120, 80, 195, 22);
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

        adressLabel = new JLabel();
        adressLabel.setText("Address:");
        adressLabel.setFont(labelFont());
        adressLabel.setBackground(SystemColor.activeCaption);
        adressLabel.setBounds(15, 200, 83, 22);
        contentPane.add(adressLabel);

        addressCB = new JComboBox(details.getLocation().toArray(new String[details.getLocation().size()]));
        addressCB.setBounds(120, 200, 195, 22);
        contentPane.add(addressCB);

        problemText = new JTextPane();
        problemText.setBounds(15, 240, 300, 150);
        contentPane.add(problemText);

        addButton = new JButton("Add");
        addButton.setBounds(45, 400, 240, 50);
        addButton.setBorderPainted(false);
        addButton.setFocusPainted(false);
        addButton.setBackground(SystemColor.activeCaption);
        addButton.setForeground(Color.WHITE);
        contentPane.add(addButton);

        list = new JList();
        list.setBounds(400, 60, 460, 330);
        list.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        contentPane.add(list);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(510, 402, 240, 50);
        deleteButton.setBorderPainted(false);
        deleteButton.setFocusPainted(false);
        deleteButton.setBackground(SystemColor.activeCaption);
        deleteButton.setForeground(Color.WHITE);
        contentPane.add(deleteButton);

        logoutButton = new JButton("Log out");
        logoutButton.setBounds(280, 500, 300, 50);
        logoutButton.setBorderPainted(false);
        logoutButton.setFocusPainted(false);
        logoutButton.setBackground(SystemColor.activeCaption);
        logoutButton.setForeground(Color.WHITE);
        contentPane.add(logoutButton);
    }
}
