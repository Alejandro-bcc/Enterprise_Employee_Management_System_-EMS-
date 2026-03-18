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
        Insets defaultInsets = new Insets(5, 10, 5, 10);
        Insets firstButtonInsets = new Insets(20, 10, 5, 10);

        this.logoLabel = new JLabel(UITheme.LOGO_ICON);
        this.logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(this.logoLabel,
                UITheme.gbc(0, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 1, 1.0, 0.0,
                        defaultInsets));

        this.titleLabel = new JLabel("REGISTER");
        this.titleLabel.setFont(UITheme.FONT_TITLE);
        this.titleLabel.setForeground(UITheme.FONT_COLOR);
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(this.titleLabel,
                UITheme.gbc(0, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 1, 1.0, 0.0,
                        defaultInsets));

        this.usernameLabel = new JLabel("Username:");
        this.usernameLabel.setFont(UITheme.FONT_NORMAL);
        this.usernameLabel.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.usernameLabel,
                UITheme.gbc(0, 2, GridBagConstraints.NONE, GridBagConstraints.WEST, 1, 0.0, 0.0, defaultInsets));

        this.usernameField = new JTextField();
        this.usernameField.setPreferredSize(new Dimension(255, 25));
        this.usernameField.setBackground(UITheme.INPUT_BACKGROUND);
        this.usernameField.setBorder(UITheme.INPUT_BORDER);
        this.usernameField.setFont(UITheme.FONT_INPUT);
        this.usernameField.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.usernameField,
                UITheme.gbc(0, 3, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 1, 1.0, 0.0, defaultInsets));

        this.emailLabel = new JLabel("Email:");
        this.emailLabel.setFont(UITheme.FONT_NORMAL);
        this.emailLabel.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.emailLabel,
                UITheme.gbc(0, 4, GridBagConstraints.NONE, GridBagConstraints.WEST, 1, 0.0, 0.0, defaultInsets));

        this.emailField = new JTextField();
        this.emailField.setPreferredSize(new Dimension(255, 25));
        this.emailField.setBackground(UITheme.INPUT_BACKGROUND);
        this.emailField.setBorder(UITheme.INPUT_BORDER);
        this.emailField.setFont(UITheme.FONT_INPUT);
        this.emailField.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.emailField,
                UITheme.gbc(0, 5, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 1, 1.0, 0.0, defaultInsets));

        this.passwordLabel = new JLabel("Password:");
        this.passwordLabel.setFont(UITheme.FONT_NORMAL);
        this.passwordLabel.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.passwordLabel,
                UITheme.gbc(0, 6, GridBagConstraints.NONE, GridBagConstraints.WEST, 1, 0.0, 0.0, defaultInsets));

        this.passwordField = new JPasswordField();
        this.passwordField.setPreferredSize(new Dimension(255, 25));
        this.passwordField.setBackground(UITheme.INPUT_BACKGROUND);
        this.passwordField.setBorder(UITheme.INPUT_BORDER);
        this.passwordField.setFont(UITheme.FONT_INPUT);
        this.passwordField.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.passwordField,
                UITheme.gbc(0, 7, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 1, 1.0, 0.0, defaultInsets));

        this.button1 = new JButton("Register User");
        this.button1.setBackground(UITheme.BUTTON_COLOR);
        this.button1.setFont(UITheme.FONT_INPUT);
        this.button1.setForeground(UITheme.BUTTON_FONT);
        this.button1.addActionListener(this);
        formPanel.add(this.button1,
                UITheme.gbc(0, 8, GridBagConstraints.NONE, GridBagConstraints.CENTER, 1, 0.0, 0.0, firstButtonInsets));

        this.footerLabel = new JLabel("Already have an account?");
        this.footerLabel.setFont(UITheme.FONT_ITALIC);
        this.footerLabel.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.footerLabel,
                UITheme.gbc(0, 9, GridBagConstraints.NONE, GridBagConstraints.CENTER, 1, 0.0, 0.0, defaultInsets));

        this.button2 = new JButton("Back to Login");
        this.button2.setBackground(UITheme.BUTTON_COLOR);
        this.button2.setFont(UITheme.FONT_INPUT);
        this.button2.setForeground(UITheme.BUTTON_FONT);
        this.button2.addActionListener(this);
        formPanel.add(this.button2,
                UITheme.gbc(0, 10, GridBagConstraints.NONE, GridBagConstraints.CENTER, 1, 0.0, 0.0, defaultInsets));

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

            if (!this.userService.register(username, email, rawPassword)) {
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
