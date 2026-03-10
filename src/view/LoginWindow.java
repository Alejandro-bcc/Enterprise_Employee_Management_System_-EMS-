package src.view;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.util.Arrays;
import java.awt.*;

public class LoginWindow extends JFrame implements ActionListener {
    // Attributes
    private JTextField textField;
    private JPasswordField passwordField;
    private JLabel label1, label2, label3, label4;
    private JButton button1, button2;
    private String loginIdentifier;
    private char[] rawPassword;

    // Constructor
    public LoginWindow() {

        // Colors
        Color backgroundColor = new Color(245, 245, 245);
        Color fontColor = new Color(53, 56, 57);
        Color inputBackgroundColor = new Color(234, 234, 234);
        // Fonts
        Font fontTitle = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        Font fontNormal = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
        Font fontInput = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
        // Borders
        Border line = BorderFactory.createLineBorder(Color.BLACK);
        // Images
        ImageIcon rawIcon = new ImageIcon(getClass().getResource("/assets/img/EMS_logo.png"));
        Image scaledImg = rawIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImg);
        ImageIcon iconWindow = new ImageIcon(getClass().getResource("/assets/img/EMS_logo_icon_48x48.png"));

        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("EMS");
        this.getContentPane().setBackground(backgroundColor);
        this.setIconImage(iconWindow.getImage());

        // Form panel — contains all form elements
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        // Logo — fixed in NORTH, centered
        this.label1 = new JLabel(icon);
        this.label1.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(this.label1, gbc);

        this.label2 = new JLabel("Login");
        this.label2.setFont(fontTitle);
        this.label2.setForeground(fontColor);
        this.label2.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(this.label2, gbc);

        this.label3 = new JLabel("Username or Email:");
        this.label3.setFont(fontNormal);
        this.label3.setForeground(fontColor);
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(this.label3, gbc);

        this.textField = new JTextField();
        this.textField.setPreferredSize(new Dimension(255, 25));
        this.textField.setBackground(inputBackgroundColor);
        this.textField.setBorder(line);
        this.textField.setFont(fontInput);
        this.textField.setForeground(fontColor);
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(this.textField, gbc);

        this.label4 = new JLabel("Password:");
        this.label4.setFont(fontNormal);
        this.label4.setForeground(fontColor);
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(this.label4, gbc);

        this.passwordField = new JPasswordField();
        this.passwordField.setPreferredSize(new Dimension(255, 25));
        this.passwordField.setBackground(inputBackgroundColor);
        this.passwordField.setBorder(line);
        this.passwordField.setFont(fontInput);
        this.passwordField.setForeground(fontColor);
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(this.passwordField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(backgroundColor);

        this.button1 = new JButton("Login");
        this.button1.setBackground(inputBackgroundColor);
        this.button1.setFont(fontInput);
        this.button1.setForeground(fontColor);
        this.button1.addActionListener(this);
        buttonPanel.add(this.button1);

        this.button2 = new JButton("Register");
        this.button2.setBackground(inputBackgroundColor);
        this.button2.setFont(fontInput);
        this.button2.setForeground(fontColor);
        this.button2.addActionListener(this);
        buttonPanel.add(this.button2);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(buttonPanel, gbc);

        // centerWrapper: GridBagLayout with no weights → keeps formPanel at its
        // preferred size and centers it regardless of window size
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(backgroundColor);
        centerWrapper.add(formPanel);
        this.add(formPanel);
    }

    // Methods
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() != this.button1 && e.getSource() != this.button2) {
            JOptionPane.showMessageDialog(null, "FATAL ERROR!");
            return;
        }

        if (e.getSource() == this.button1) {

            this.loginIdentifier = this.textField.getText().trim();
            this.rawPassword = this.passwordField.getPassword();

            if (this.loginIdentifier.isEmpty() || this.rawPassword.length == 0) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                return;
            }

            JOptionPane.showMessageDialog(null, this.loginIdentifier);
            Arrays.fill(this.rawPassword, '\0');

            return;
        }

        if (e.getSource() == this.button2) {
            // Open register window
        }
    }

    public static void main(String args[]) {
        LoginWindow login = new LoginWindow();
        login.setBounds(0, 0, 350, 450);
        login.setVisible(true);
        login.setResizable(true);
        login.setLocationRelativeTo(null);
    }
}
