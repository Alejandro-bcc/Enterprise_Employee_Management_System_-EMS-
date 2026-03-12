package src.view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Arrays;
import src.service.UserService;
import src.session.*;;


public class LoginWindow extends JFrame implements ActionListener {
    // Attributes
    private JTextField textField;
    private JPasswordField passwordField;
    private JLabel logoLabel, titleLabel, loginIdentifierLabel, passwordLabel, footerLabel;
    private JButton button1, button2;
    private String loginIdentifier;
    private UserService userService;

    // Constructor
    public LoginWindow() {

        this.userService = new UserService();

        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("EMS");
        this.getContentPane().setBackground(UITheme.BACKGROUND);
        this.setIconImage(UITheme.WINDOW_ICON.getImage());

        // Form panel — contains all form elements
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(UITheme.BACKGROUND);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        // Logo — fixed in NORTH, centered
        this.logoLabel = new JLabel(UITheme.LOGO_ICON);
        this.logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(this.logoLabel, gbc);

        this.titleLabel = new JLabel("LOGIN");
        this.titleLabel.setFont(UITheme.FONT_TITLE);
        this.titleLabel.setForeground(UITheme.FONT_COLOR);
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(this.titleLabel, gbc);

        this.loginIdentifierLabel = new JLabel("Username or Email:");
        this.loginIdentifierLabel.setFont(UITheme.FONT_NORMAL);
        this.loginIdentifierLabel.setForeground(UITheme.FONT_COLOR);
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(this.loginIdentifierLabel, gbc);

        this.textField = new JTextField();
        this.textField.setPreferredSize(new Dimension(255, 25));
        this.textField.setBackground(UITheme.INPUT_BACKGROUND);
        this.textField.setBorder(UITheme.INPUT_BORDER);
        this.textField.setFont(UITheme.FONT_INPUT);
        this.textField.setForeground(UITheme.FONT_COLOR);
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(this.textField, gbc);

        this.passwordLabel = new JLabel("Password:");
        this.passwordLabel.setFont(UITheme.FONT_NORMAL);
        this.passwordLabel.setForeground(UITheme.FONT_COLOR);
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(this.passwordLabel, gbc);

        this.passwordField = new JPasswordField();
        this.passwordField.setPreferredSize(new Dimension(255, 25));
        this.passwordField.setBackground(UITheme.INPUT_BACKGROUND);
        this.passwordField.setBorder(UITheme.INPUT_BORDER);
        this.passwordField.setFont(UITheme.FONT_INPUT);
        this.passwordField.setForeground(UITheme.FONT_COLOR);
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(this.passwordField, gbc);

        this.button1 = new JButton("Login");
        this.button1.setBackground(UITheme.BUTTON_COLOR);
        this.button1.setFont(UITheme.FONT_INPUT);
        this.button1.setForeground(UITheme.BUTTON_FONT);
        this.button1.addActionListener(this);
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 5, 10);
        formPanel.add(this.button1, gbc);

        this.footerLabel = new JLabel("Don't have an account?");
        this.footerLabel.setFont(UITheme.FONT_ITALIC);
        this.footerLabel.setForeground(UITheme.FONT_COLOR);
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(this.footerLabel, gbc);

        this.button2 = new JButton("Register Now");
        this.button2.setBackground(UITheme.BUTTON_COLOR);
        this.button2.setFont(UITheme.FONT_INPUT);
        this.button2.setForeground(UITheme.BUTTON_FONT);
        this.button2.addActionListener(this);
        gbc.gridy = 8;
        gbc.insets = new Insets(5, 10, 5, 10);
        formPanel.add(this.button2, gbc);

        // centerWrapper: GridBagLayout with no weights → keeps formPanel at its
        // preferred size and centers it regardless of window size
        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(UITheme.BACKGROUND);
        centerWrapper.add(formPanel);
        this.add(centerWrapper);
    }

    // Methods
    private void goToRegisterWindow() {
        RegisterWindow register = new RegisterWindow();
        register.setBounds(0, 0, 350, 530);
        register.setVisible(true);
        register.setResizable(true);
        register.setLocationRelativeTo(null);
        this.dispose();
    }

    private void goToDashboardWindow() {
        DashboardWindow dashboard = new DashboardWindow();
        dashboard.setBounds(0, 0, 900, 600);
        dashboard.setVisible(true);
        dashboard.setResizable(true);
        dashboard.setLocationRelativeTo(null);
        this.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() != this.button1 && e.getSource() != this.button2) {
            JOptionPane.showMessageDialog(null, "FATAL ERROR!");
            return;
        }

        if (e.getSource() == this.button1) {

            char [] rawPassword = this.passwordField.getPassword();;
            this.loginIdentifier = this.textField.getText().trim();

            if (this.loginIdentifier.isEmpty() || rawPassword.length == 0) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                Arrays.fill(rawPassword, '\0');
                return;
            }

            if (!this.userService.login(loginIdentifier, rawPassword)) {
                JOptionPane.showMessageDialog(null, "Error: user not found or invalid password.");
                Arrays.fill(rawPassword, '\0');
                return;
            }

            JOptionPane.showMessageDialog(null, String.format("Welcome %s", this.loginIdentifier));
            Arrays.fill(rawPassword, '\0');
            this.goToDashboardWindow();
            return;
        }

        if (e.getSource() == this.button2) {
            this.goToRegisterWindow();
            return;
        }
    }

    public static void main(String args[]) {
        LoginWindow login = new LoginWindow();
        login.setBounds(0, 0, 350, 470);
        login.setVisible(true);
        login.setResizable(true);
        login.setLocationRelativeTo(null);
    }
}
