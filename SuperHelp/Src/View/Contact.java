package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Contact extends JFrame {
    JPanel contentPane;
    JLabel phoneLabel;
    JLabel emailLabel;
    JLabel imageLabel;
    JButton goBackButton;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Contact frame = new Contact();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public String title() { return "SuperHelp"; }

    public Font labelsFont() { return new Font("Tahoma", Font.PLAIN, 20); }

    public Font buttonFont() { return new Font("Tahoma", Font.PLAIN, 18); }

    public int getAppWidth() { return 380; }

    public int getAppHeight() { return 240; }

    public Contact() throws IOException {
        setTitle(title());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, getAppWidth(), getAppHeight());
        setLocationRelativeTo(null);
        setLayout(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        emailLabel = new JLabel("Email: SuperHelp@gmail.com");
        emailLabel.setBounds(50, 10, 500, 50);
        emailLabel.setFont(labelsFont());
        contentPane.add(emailLabel);

        phoneLabel = new JLabel("Phone: 050-1112222");
        phoneLabel.setBounds(50, 60, 250, 50);
        phoneLabel.setFont(labelsFont());
        contentPane.add(phoneLabel);

        goBackButton = new JButton("Go Back");
        goBackButton.setBounds(50, 120, 250, 50);
        goBackButton.setFont(buttonFont());
        goBackButton.setBorderPainted(false);
        goBackButton.setFocusPainted(false);
        goBackButton.setBackground(SystemColor.activeCaption);
        goBackButton.setForeground(Color.WHITE);
        contentPane.add(goBackButton);

        imageLabel = new JLabel("");
        BufferedImage img = null;
        try {
            img = ImageIO.read(Login.class.getResource("/Images/contact.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(getAppWidth(), getAppHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        imageLabel.setIcon(imageIcon);
        imageLabel.setBounds(0, 0, getAppWidth(), getAppHeight());
        contentPane.add(imageLabel);
    }

    public void addGoBackListener(ActionListener actionGoBackListener) {
        goBackButton.addActionListener(actionGoBackListener);
    }

    public void displayErrorMessage(String errorMsg) {
        JOptionPane.showMessageDialog(this,errorMsg);
    }
}
