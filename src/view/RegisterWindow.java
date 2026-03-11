package src.view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Arrays;
import src.service.UserService;

public class RegisterWindow extends JFrame implements ActionListener {
    // Attributes
    private JTextField usernameField, emailField;
    private JPasswordField passwordField;
    private JLabel logoLabel, titleLabel, usernameLabel, emailLabel, passwordLabel, footerLabel;
    private JButton button1, button2;
    private String username, email;
    private char[] rawPassword;
    private UserService userService;

    // Constructor
    public RegisterWindow() {

        this.userService = new UserService();

        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("EMS");
        this.getContentPane().setBackground(UITheme.BACKGROUND);
        this.setIconImage(UITheme.WINDOW_ICON.getImage());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(UITheme.BACKGROUND);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        this.logoLabel = new JLabel(UITheme.LOGO_ICON);
        this.logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(this.logoLabel, gbc);

        this.titleLabel = new JLabel("REGISTER");
        this.titleLabel.setFont(UITheme.FONT_TITLE);
        this.titleLabel.setForeground(UITheme.FONT_COLOR);
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(this.titleLabel, gbc);

        this.usernameLabel = new JLabel("Username:");
        this.usernameLabel.setFont(UITheme.FONT_NORMAL);
        this.usernameLabel.setForeground(UITheme.FONT_COLOR);
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(this.usernameLabel, gbc);

        this.usernameField = new JTextField();
        this.usernameField.setPreferredSize(new Dimension(255, 25));
        this.usernameField.setBackground(UITheme.INPUT_BACKGROUND);
        this.usernameField.setBorder(UITheme.INPUT_BORDER);
        this.usernameField.setFont(UITheme.FONT_INPUT);
        this.usernameField.setForeground(UITheme.FONT_COLOR);
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(this.usernameField, gbc);

        this.emailLabel = new JLabel("Email:");
        this.emailLabel.setFont(UITheme.FONT_NORMAL);
        this.emailLabel.setForeground(UITheme.FONT_COLOR);
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(this.emailLabel, gbc);

        this.emailField = new JTextField();
        this.emailField.setPreferredSize(new Dimension(255, 25));
        this.emailField.setBackground(UITheme.INPUT_BACKGROUND);
        this.emailField.setBorder(UITheme.INPUT_BORDER);
        this.emailField.setFont(UITheme.FONT_INPUT);
        this.emailField.setForeground(UITheme.FONT_COLOR);
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(this.emailField, gbc);

        this.passwordLabel = new JLabel("Password:");
        this.passwordLabel.setFont(UITheme.FONT_NORMAL);
        this.passwordLabel.setForeground(UITheme.FONT_COLOR);
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(this.passwordLabel, gbc);

        this.passwordField = new JPasswordField();
        this.passwordField.setPreferredSize(new Dimension(255, 25));
        this.passwordField.setBackground(UITheme.INPUT_BACKGROUND);
        this.passwordField.setBorder(UITheme.INPUT_BORDER);
        this.passwordField.setFont(UITheme.FONT_INPUT);
        this.passwordField.setForeground(UITheme.FONT_COLOR);
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(this.passwordField, gbc);

        this.button1 = new JButton("Register User");
        this.button1.setBackground(UITheme.BUTTON_COLOR);
        this.button1.setFont(UITheme.FONT_INPUT);
        this.button1.setForeground(UITheme.BUTTON_FONT);
        this.button1.addActionListener(this);
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 5, 10);
        formPanel.add(this.button1, gbc);

        this.footerLabel = new JLabel("Already have an account?");
        this.footerLabel.setFont(UITheme.FONT_ITALIC);
        this.footerLabel.setForeground(UITheme.FONT_COLOR);
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(this.footerLabel, gbc);

        this.button2 = new JButton("Back to Login");
        this.button2.setBackground(UITheme.BUTTON_COLOR);
        this.button2.setFont(UITheme.FONT_INPUT);
        this.button2.setForeground(UITheme.BUTTON_FONT);
        this.button2.addActionListener(this);
        gbc.gridy = 10;
        gbc.insets = new Insets(5, 10, 5, 10);
        formPanel.add(this.button2, gbc);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(UITheme.BACKGROUND);
        centerWrapper.add(formPanel);
        this.add(centerWrapper);
    }
    // Methods

    private void goToLoginWindow() {
        LoginWindow login = new LoginWindow();
        login.setBounds(0, 0, 350, 470);
        login.setVisible(true);
        login.setResizable(true);
        login.setLocationRelativeTo(null);
        this.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() != this.button1 && e.getSource() != this.button2)
            return;

        if (e.getSource() == this.button1) {

            this.username = this.usernameField.getText().trim();
            this.email = this.emailField.getText().trim();
            this.rawPassword = this.passwordField.getPassword();

            if (this.username.isEmpty() || this.email.isEmpty() || this.rawPassword.length == 0) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                Arrays.fill(this.rawPassword, '\0');
                return;
            }

            if(!this.userService.register(username, email, rawPassword)){
                JOptionPane.showMessageDialog(null, "Error: invalid fields. Try again with valid data.");
                Arrays.fill(this.rawPassword, '\0');    
                return;
            }

            JOptionPane.showMessageDialog(null, String.format("New user '%s' registered sucessfully", this.username));
            Arrays.fill(this.rawPassword, '\0');
            this.goToLoginWindow();
            return;
        }

        if (e.getSource() == this.button2) {
            this.goToLoginWindow();
            return;
        }
    }

}
