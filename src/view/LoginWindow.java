package src.view;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Arrays;
import src.service.UserService;

public class LoginWindow extends JFrame implements ActionListener {
    // Attributes
    private JTextField textField;
    private JPasswordField passwordField;
    private JLabel logoLabel, titleLabel, loginIdentifierLabel, passwordLabel, footerLabel;
    private JButton loginButton, registerButton;
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
        Insets defaultInsets = new Insets(5, 10, 5, 10);
        Insets firstButtonInsets = new Insets(20, 10, 5, 10);

        // Logo — fixed in NORTH, centered
        this.logoLabel = new JLabel(UITheme.LOGO_ICON);
        this.logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(this.logoLabel,
                UITheme.gbc(0, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 1, 1.0, 0.0,
                        defaultInsets));

        this.titleLabel = new JLabel("LOGIN");
        this.titleLabel.setFont(UITheme.FONT_TITLE);
        this.titleLabel.setForeground(UITheme.FONT_COLOR);
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(this.titleLabel,
                UITheme.gbc(0, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 1, 1.0, 0.0,
                        defaultInsets));

        this.loginIdentifierLabel = new JLabel("Username or Email:");
        this.loginIdentifierLabel.setFont(UITheme.FONT_NORMAL);
        this.loginIdentifierLabel.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.loginIdentifierLabel,
                UITheme.gbc(0, 2, GridBagConstraints.NONE, GridBagConstraints.WEST, 1, 0.0, 0.0, defaultInsets));

        this.textField = new JTextField();
        this.textField.setPreferredSize(new Dimension(255, 25));
        this.textField.setBackground(UITheme.INPUT_BACKGROUND);
        this.textField.setBorder(UITheme.INPUT_BORDER);
        this.textField.setFont(UITheme.FONT_INPUT);
        this.textField.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.textField,
                UITheme.gbc(0, 3, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 1, 1.0, 0.0, defaultInsets));

        this.passwordLabel = new JLabel("Password:");
        this.passwordLabel.setFont(UITheme.FONT_NORMAL);
        this.passwordLabel.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.passwordLabel,
                UITheme.gbc(0, 4, GridBagConstraints.NONE, GridBagConstraints.WEST, 1, 0.0, 0.0, defaultInsets));

        this.passwordField = new JPasswordField();
        this.passwordField.setPreferredSize(new Dimension(255, 25));
        this.passwordField.setBackground(UITheme.INPUT_BACKGROUND);
        this.passwordField.setBorder(UITheme.INPUT_BORDER);
        this.passwordField.setFont(UITheme.FONT_INPUT);
        this.passwordField.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.passwordField,
                UITheme.gbc(0, 5, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 1, 1.0, 0.0, defaultInsets));

        this.loginButton = new JButton("Login");
        this.loginButton.setBackground(UITheme.BUTTON_COLOR);
        this.loginButton.setFont(UITheme.FONT_INPUT);
        this.loginButton.setForeground(UITheme.BUTTON_FONT);
        this.loginButton.addActionListener(this);
        formPanel.add(this.loginButton,
                UITheme.gbc(0, 6, GridBagConstraints.NONE, GridBagConstraints.CENTER, 1, 0.0, 0.0, firstButtonInsets));

        this.footerLabel = new JLabel("Don't have an account?");
        this.footerLabel.setFont(UITheme.FONT_ITALIC);
        this.footerLabel.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.footerLabel,
                UITheme.gbc(0, 7, GridBagConstraints.NONE, GridBagConstraints.CENTER, 1, 0.0, 0.0, defaultInsets));

        this.registerButton = new JButton("Register Now");
        this.registerButton.setBackground(UITheme.BUTTON_COLOR);
        this.registerButton.setFont(UITheme.FONT_INPUT);
        this.registerButton.setForeground(UITheme.BUTTON_FONT);
        this.registerButton.addActionListener(this);
        formPanel.add(this.registerButton,
                UITheme.gbc(0, 8, GridBagConstraints.NONE, GridBagConstraints.CENTER, 1, 0.0, 0.0, defaultInsets));

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
        if (e.getSource() != this.loginButton && e.getSource() != this.registerButton) {
            JOptionPane.showMessageDialog(null, "FATAL ERROR!");
            return;
        }

        if (e.getSource() == this.loginButton) {

            char[] rawPassword = this.passwordField.getPassword();
            ;
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

        if (e.getSource() == this.registerButton) {
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
