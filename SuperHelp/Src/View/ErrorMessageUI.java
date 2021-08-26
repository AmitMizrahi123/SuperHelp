package View;

import Controller.LoginController;
import DB.ClientRepository;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class ErrorMessageUI extends JFrame{
    private JPanel contentPane;
    private JLabel messageLabel;
    private JButton okButton;

    private Font labelFont() { return new Font("Georgia", Font.BOLD | Font.ITALIC, 18); }

    public ErrorMessageUI(String errorMessage) {
        ImageIcon image = new ImageIcon(Login.class.getResource("/Images/icon.jpg"));
        setIconImage(image.getImage());
        setResizable(false);
        setTitle("Welcome to Super Help");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 350, 150);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        messageLabel = new JLabel(errorMessage, SwingConstants.CENTER);
        messageLabel.setFont(labelFont());
        messageLabel.setBounds(0, 10, 300, 30);
        contentPane.add(messageLabel);

        okButton = new JButton("OK");
        okButton.setBounds(110, 60, 100, 30);
        okButton.setBorderPainted(false);
        okButton.setFocusPainted(false);
        okButton.setBackground(SystemColor.activeCaption);
        okButton.setForeground(Color.WHITE);
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        contentPane.add(okButton);

        setVisible(true);
    }

    public String getErrorText() { return messageLabel.getText(); }

    public void clickOnOkButton() { okButton.doClick(); }
}
