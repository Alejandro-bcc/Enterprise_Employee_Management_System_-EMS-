package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EmployeeFormPanel extends JFrame {

    // Attributes
    private JLabel titleLabel, firstNameLabel, lastNameLabel, emailLabel,
            phoneLabel, deptLabel, roleLabel, hireDateLabel, salaryLabel;
    private JTextField firstNameField, lastNameField, emailField,
            phoneField, hireDateField, salaryField;
    private JComboBox deptCombo, roleCombo;
    private JButton submitButton, cancelButton;

    // Constructor

    public EmployeeFormPanel() {

        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("EMS");
        this.getContentPane().setBackground(UITheme.BACKGROUND);
        this.setIconImage(UITheme.WINDOW_ICON.getImage());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(UITheme.BACKGROUND);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        this.titleLabel = new JLabel("User Form");
        this.titleLabel.setFont(UITheme.FONT_NORMAL);
        this.titleLabel.setForeground(UITheme.FONT_COLOR);
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(this.titleLabel, gbc);

        JPanel namesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        namesPanel.setBackground(UITheme.BACKGROUND);

        this.firstNameLabel = new JLabel("First Name:");
        this.firstNameLabel.setFont(UITheme.FONT_NORMAL);
        this.firstNameLabel.setForeground(UITheme.FONT_COLOR);
        this.firstNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(this.firstNameLabel, gbc);

        this.firstNameField = new JTextField();
        this.firstNameField.setBackground(UITheme.INPUT_BACKGROUND);
        this.firstNameField.setBorder(UITheme.INPUT_BORDER);
        this.firstNameField.setFont(UITheme.FONT_INPUT);
        this.firstNameField.setForeground(UITheme.FONT_COLOR);
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(this.firstNameField, gbc);

        this.lastNameLabel = new JLabel("Last Name:");
        this.lastNameLabel.setFont(UITheme.FONT_NORMAL);
        this.lastNameLabel.setForeground(UITheme.FONT_COLOR);
        this.lastNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 2;
        gbc.gridy = 1;
        formPanel.add(this.lastNameLabel, gbc);

        this.lastNameField = new JTextField();
        this.lastNameField.setBackground(UITheme.INPUT_BACKGROUND);
        this.lastNameField.setBorder(UITheme.INPUT_BORDER);
        this.lastNameField.setFont(UITheme.FONT_INPUT);
        this.lastNameField.setForeground(UITheme.FONT_COLOR);
        gbc.gridx = 2;
        gbc.gridy = 2;
        formPanel.add(this.lastNameField, gbc);

        this.emailLabel = new JLabel("Email:");
        this.emailLabel.setFont(UITheme.FONT_NORMAL);
        this.emailLabel.setForeground(UITheme.FONT_COLOR);
        this.emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 3;
        formPanel.add(this.emailLabel, gbc);

        this.emailField = new JTextField();
        this.emailField.setBackground(UITheme.INPUT_BACKGROUND);
        this.emailField.setBorder(UITheme.INPUT_BORDER);
        this.emailField.setFont(UITheme.FONT_INPUT);
        this.emailField.setForeground(UITheme.FONT_COLOR);
        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(this.emailField, gbc);

        this.phoneLabel = new JLabel("Phone:");
        this.phoneLabel.setFont(UITheme.FONT_NORMAL);
        this.phoneLabel.setForeground(UITheme.FONT_COLOR);
        this.phoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 2;
        gbc.gridy = 3;
        formPanel.add(this.phoneLabel, gbc);

        this.phoneField = new JTextField();
        this.phoneField.setBackground(UITheme.INPUT_BACKGROUND);
        this.phoneField.setBorder(UITheme.INPUT_BORDER);
        this.phoneField.setFont(UITheme.FONT_INPUT);
        this.phoneField.setForeground(UITheme.FONT_COLOR);
        gbc.gridx = 2;
        gbc.gridy = 4;
        formPanel.add(this.phoneField, gbc);

        this.deptLabel = new JLabel("Department:");
        this.deptLabel.setFont(UITheme.FONT_NORMAL);
        this.deptLabel.setForeground(UITheme.FONT_COLOR);
        this.deptLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 5;
        formPanel.add(this.deptLabel, gbc);

        String[] departments = { "", "IT", "HR", "Finance", "Marketing", "Operations" };
        this.deptCombo = new JComboBox<>(departments);
        this.deptCombo.setFont(UITheme.FONT_NORMAL);
        this.deptCombo.setBackground(UITheme.INPUT_BACKGROUND);
        this.deptCombo.setForeground(UITheme.FONT_COLOR);
        gbc.gridx = 1;
        gbc.gridy = 6;
        formPanel.add(this.deptCombo, gbc);

        this.roleLabel = new JLabel("Department:");
        this.roleLabel.setFont(UITheme.FONT_NORMAL);
        this.roleLabel.setForeground(UITheme.FONT_COLOR);
        this.roleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 2;
        gbc.gridy = 5;
        formPanel.add(this.roleLabel, gbc);

        String[] roles = { "", "Manager", "Developer", "Intern" };
        this.roleCombo = new JComboBox<>(roles);
        this.roleCombo.setFont(UITheme.FONT_NORMAL);
        this.roleCombo.setBackground(UITheme.INPUT_BACKGROUND);
        this.roleCombo.setForeground(UITheme.FONT_COLOR);
        gbc.gridx = 2;
        gbc.gridy = 6;
        formPanel.add(this.roleCombo, gbc);

        this.hireDateLabel = new JLabel("First Name:");
        this.hireDateLabel.setFont(UITheme.FONT_NORMAL);
        this.hireDateLabel.setForeground(UITheme.FONT_COLOR);
        this.hireDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 7;
        formPanel.add(this.hireDateLabel, gbc);

        this.hireDateField = new JTextField();
        this.hireDateField.setBackground(UITheme.INPUT_BACKGROUND);
        this.hireDateField.setBorder(UITheme.INPUT_BORDER);
        this.hireDateField.setFont(UITheme.FONT_INPUT);
        this.hireDateField.setForeground(UITheme.FONT_COLOR);
        gbc.gridx = 1;
        gbc.gridy = 8;
        formPanel.add(this.hireDateField, gbc);

        this.salaryLabel = new JLabel("First Name:");
        this.salaryLabel.setFont(UITheme.FONT_NORMAL);
        this.salaryLabel.setForeground(UITheme.FONT_COLOR);
        this.salaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 2;
        gbc.gridy = 7;
        formPanel.add(this.salaryLabel, gbc);

        this.salaryField = new JTextField();
        this.salaryField.setBackground(UITheme.INPUT_BACKGROUND);
        this.salaryField.setBorder(UITheme.INPUT_BORDER);
        this.salaryField.setFont(UITheme.FONT_INPUT);
        this.salaryField.setForeground(UITheme.FONT_COLOR);
        gbc.gridx = 2;
        gbc.gridy = 8;
        formPanel.add(this.salaryField, gbc);

        this.submitButton = new JButton("Done");
        this.submitButton.setBackground(UITheme.BUTTON_COLOR);
        this.submitButton.setFont(UITheme.FONT_INPUT);
        this.submitButton.setForeground(UITheme.BUTTON_FONT);
        gbc.gridx = 1;
        gbc.gridy = 9;
        formPanel.add(this.submitButton, gbc);

        this.cancelButton = new JButton("Cancel");
        this.cancelButton.setBackground(UITheme.BUTTON_COLOR);
        this.cancelButton.setFont(UITheme.FONT_INPUT);
        this.cancelButton.setForeground(UITheme.BUTTON_FONT);
        gbc.gridx = 2;
        gbc.gridy = 9;
        formPanel.add(this.cancelButton, gbc);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(UITheme.BACKGROUND);
        centerWrapper.add(formPanel);
        this.add(centerWrapper);
    }
}
