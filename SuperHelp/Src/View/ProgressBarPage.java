package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ProgressBarPage extends JFrame {
    JPanel contentPane;
    JProgressBar bar;
    JLabel mainHeaderLabel;
    ImageIcon image;
    JLabel imageLabel;

    public static int getWidthApp() { return 390; }

    public static int getHeightApp() { return 350; }

    public static String getMainHeaderText() { return "Welcome To Super Help"; }

    public static Font getMainHeaderFont() { return new Font("Georgia", Font.BOLD | Font.ITALIC, 28); }

    public ProgressBarPage() {
        mainHeaderLabel = new JLabel(getMainHeaderText());
        mainHeaderLabel.setBounds(10, -60, 400, 167);
        mainHeaderLabel.setFont(getMainHeaderFont());
        mainHeaderLabel.setForeground(SystemColor.activeCaption);

        imageLabel = new JLabel("");
        BufferedImage img = null;
        try {
            img = ImageIO.read(Login.class.getResource("/Images/progressBarImage.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(300, 130, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        imageLabel.setIcon(imageIcon);
        imageLabel.setBounds(35, 70, 445, 130);

        bar = new JProgressBar();
        bar.setValue(0);
        bar.setBounds(0, 230, 390, 80);
        bar.setStringPainted(true);

        add(imageLabel);
        add(mainHeaderLabel);
        add(bar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(getWidthApp(), getHeightApp());
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);

        fill();

        dispose();
    }

    public void fill() {
        int counter = 0;

        while (counter <= 100) {
            bar.setValue(counter);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            counter += 10;
        }
    }
}
