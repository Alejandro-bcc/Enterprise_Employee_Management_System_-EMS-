package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import src.service.EmployeeService;
import src.repository.EmployeeRepository;
import src.model.Employee;
import src.model.Manager;
import src.model.Developer;
import src.model.Intern;

public class EmployeeFormPanel extends JFrame implements ActionListener {

    // Attributes
    private JLabel titleLabel, firstNameLabel, lastNameLabel, emailLabel,
            phoneLabel, deptLabel, roleLabel, hireDateLabel, salaryLabel;
    private JTextField firstNameField, lastNameField, emailField,
            phoneField, hireDateField, salaryField;
    private JComboBox<String> deptCombo, roleCombo;
    private JButton submitButton, cancelButton;
    private EmployeeService employeeService;

    // Constructor

    public EmployeeFormPanel() {

        this.employeeService = new EmployeeService();

        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("EMS");
        this.getContentPane().setBackground(UITheme.BACKGROUND);
        this.setIconImage(UITheme.WINDOW_ICON.getImage());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(UITheme.BACKGROUND);
        formPanel.setBorder(UITheme.FORMBORDER);
        Insets formInsets = new Insets(6, 10, 6, 10);

        this.titleLabel = new JLabel("Employee Form");
        this.titleLabel.setFont(UITheme.FONT_TITLE);
        this.titleLabel.setForeground(UITheme.FONT_COLOR);
        this.titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(this.titleLabel,
                UITheme.gbc(0, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 2, 1.0, 0.0, formInsets));

        this.firstNameLabel = new JLabel("First Name:");
        this.firstNameLabel.setFont(UITheme.FONT_NORMAL);
        this.firstNameLabel.setForeground(UITheme.FONT_COLOR);
        this.firstNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(this.firstNameLabel,
                UITheme.gbc(0, 1, GridBagConstraints.NONE, GridBagConstraints.WEST, 1, 0.0, 0.0, formInsets));

        this.lastNameLabel = new JLabel("Last Name:");
        this.lastNameLabel.setFont(UITheme.FONT_NORMAL);
        this.lastNameLabel.setForeground(UITheme.FONT_COLOR);
        this.lastNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(this.lastNameLabel,
                UITheme.gbc(1, 1, GridBagConstraints.NONE, GridBagConstraints.WEST, 1, 0.0, 0.0, formInsets));

        this.firstNameField = new JTextField();
        this.firstNameField.setBackground(UITheme.INPUT_BACKGROUND);
        this.firstNameField.setBorder(UITheme.INPUT_BORDER);
        this.firstNameField.setFont(UITheme.FONT_INPUT);
        this.firstNameField.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.firstNameField,
                UITheme.gbc(0, 2, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 1, 1.0, 0.0, formInsets));

        this.lastNameField = new JTextField();
        this.lastNameField.setBackground(UITheme.INPUT_BACKGROUND);
        this.lastNameField.setBorder(UITheme.INPUT_BORDER);
        this.lastNameField.setFont(UITheme.FONT_INPUT);
        this.lastNameField.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.lastNameField,
                UITheme.gbc(1, 2, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 1, 1.0, 0.0, formInsets));

        this.emailLabel = new JLabel("Email:");
        this.emailLabel.setFont(UITheme.FONT_NORMAL);
        this.emailLabel.setForeground(UITheme.FONT_COLOR);
        this.emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(this.emailLabel,
                UITheme.gbc(0, 3, GridBagConstraints.NONE, GridBagConstraints.WEST, 1, 0.0, 0.0, formInsets));

        this.phoneLabel = new JLabel("Phone:");
        this.phoneLabel.setFont(UITheme.FONT_NORMAL);
        this.phoneLabel.setForeground(UITheme.FONT_COLOR);
        this.phoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(this.phoneLabel,
                UITheme.gbc(1, 3, GridBagConstraints.NONE, GridBagConstraints.WEST, 1, 0.0, 0.0, formInsets));

        this.emailField = new JTextField();
        this.emailField.setBackground(UITheme.INPUT_BACKGROUND);
        this.emailField.setBorder(UITheme.INPUT_BORDER);
        this.emailField.setFont(UITheme.FONT_INPUT);
        this.emailField.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.emailField,
                UITheme.gbc(0, 4, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 1, 1.0, 0.0, formInsets));

        this.phoneField = new JTextField();
        this.phoneField.setBackground(UITheme.INPUT_BACKGROUND);
        this.phoneField.setBorder(UITheme.INPUT_BORDER);
        this.phoneField.setFont(UITheme.FONT_INPUT);
        this.phoneField.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.phoneField,
                UITheme.gbc(1, 4, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 1, 1.0, 0.0, formInsets));

        this.deptLabel = new JLabel("Department:");
        this.deptLabel.setFont(UITheme.FONT_NORMAL);
        this.deptLabel.setForeground(UITheme.FONT_COLOR);
        this.deptLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(this.deptLabel,
                UITheme.gbc(0, 5, GridBagConstraints.NONE, GridBagConstraints.WEST, 1, 0.0, 0.0, formInsets));

        this.roleLabel = new JLabel("Role:");
        this.roleLabel.setFont(UITheme.FONT_NORMAL);
        this.roleLabel.setForeground(UITheme.FONT_COLOR);
        this.roleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(this.roleLabel,
                UITheme.gbc(1, 5, GridBagConstraints.NONE, GridBagConstraints.WEST, 1, 0.0, 0.0, formInsets));

        String[] departments = { "", "IT", "HR", "Finance", "Marketing", "Operations" };
        this.deptCombo = new JComboBox<>(departments);
        this.deptCombo.setFont(UITheme.FONT_NORMAL);
        this.deptCombo.setBackground(UITheme.INPUT_BACKGROUND);
        this.deptCombo.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.deptCombo,
                UITheme.gbc(0, 6, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 1, 1.0, 0.0, formInsets));

        String[] roles = { "", "Manager", "Developer", "Intern" };
        this.roleCombo = new JComboBox<>(roles);
        this.roleCombo.setFont(UITheme.FONT_NORMAL);
        this.roleCombo.setBackground(UITheme.INPUT_BACKGROUND);
        this.roleCombo.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.roleCombo,
                UITheme.gbc(1, 6, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 1, 1.0, 0.0, formInsets));

        this.hireDateLabel = new JLabel("Hire Date:");
        this.hireDateLabel.setFont(UITheme.FONT_NORMAL);
        this.hireDateLabel.setForeground(UITheme.FONT_COLOR);
        this.hireDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(this.hireDateLabel,
                UITheme.gbc(0, 7, GridBagConstraints.NONE, GridBagConstraints.WEST, 1, 0.0, 0.0, formInsets));

        this.salaryLabel = new JLabel("Salary:");
        this.salaryLabel.setFont(UITheme.FONT_NORMAL);
        this.salaryLabel.setForeground(UITheme.FONT_COLOR);
        this.salaryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(this.salaryLabel,
                UITheme.gbc(1, 7, GridBagConstraints.NONE, GridBagConstraints.WEST, 1, 0.0, 0.0, formInsets));

        this.hireDateField = new JTextField();
        this.hireDateField.setBackground(UITheme.INPUT_BACKGROUND);
        this.hireDateField.setBorder(UITheme.INPUT_BORDER);
        this.hireDateField.setFont(UITheme.FONT_INPUT);
        this.hireDateField.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.hireDateField,
                UITheme.gbc(0, 8, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 1, 1.0, 0.0, formInsets));

        this.salaryField = new JTextField();
        this.salaryField.setBackground(UITheme.INPUT_BACKGROUND);
        this.salaryField.setBorder(UITheme.INPUT_BORDER);
        this.salaryField.setFont(UITheme.FONT_INPUT);
        this.salaryField.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.salaryField,
                UITheme.gbc(1, 8, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 1, 1.0, 0.0, formInsets));

        JPanel buttons = new JPanel(new GridLayout(1, 2, 12, 0));
        buttons.setBackground(UITheme.BACKGROUND);

        this.submitButton = new JButton("Done");
        this.submitButton.setBackground(UITheme.BUTTON_COLOR);
        this.submitButton.setFont(UITheme.FONT_INPUT);
        this.submitButton.setForeground(UITheme.BUTTON_FONT);
        this.submitButton.setPreferredSize(new Dimension(220, 32));
        this.submitButton.addActionListener(this);

        this.cancelButton = new JButton("Cancel");
        this.cancelButton.setBackground(UITheme.BUTTON_COLOR);
        this.cancelButton.setFont(UITheme.FONT_INPUT);
        this.cancelButton.setForeground(UITheme.BUTTON_FONT);
        this.cancelButton.setPreferredSize(new Dimension(220, 32));
        this.cancelButton.addActionListener(this);

        buttons.add(this.submitButton);
        buttons.add(this.cancelButton);

        formPanel.add(buttons,
                UITheme.gbc(0, 9, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 2, 1.0, 0.0, formInsets));

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(UITheme.BACKGROUND);
        centerWrapper.add(formPanel);
        this.add(centerWrapper);
    }

    // Methods

    private void goToDashboardWindow() {
        DashboardWindow dashboard = new DashboardWindow();
        dashboard.setBounds(0, 0, 900, 600);
        dashboard.setVisible(true);
        dashboard.setResizable(true);
        dashboard.setLocationRelativeTo(null);
        this.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() != this.cancelButton && e.getSource() != this.submitButton)
            return;

        if (e.getSource() == this.submitButton) {
            String firstName = this.firstNameField.getText().trim();
            String lastName = this.lastNameField.getText().trim();
            String email = this.emailField.getText().trim();
            String phone = this.phoneField.getText().trim();
            String dept = String.valueOf(this.deptCombo.getSelectedItem()).trim().toUpperCase();
            String role = String.valueOf(this.roleCombo.getSelectedItem()).trim().toLowerCase();

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty()
                    || dept.isEmpty() || role.isEmpty()
                    || this.hireDateField.getText().trim().isEmpty()
                    || this.salaryField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
                JOptionPane.showMessageDialog(this, "Invalid email format.");
                return;
            }

            if (!phone.matches("^\\d{7,15}$")) {
                JOptionPane.showMessageDialog(this, "Phone must contain only digits (7-15).");
                return;
            }

            LocalDate hireDate;
            try {
                // Expected format: yyyy-MM-dd (example: 2026-03-17)
                hireDate = LocalDate.parse(this.hireDateField.getText().trim());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid hire date. Use yyyy-MM-dd.");
                return;
            }

            double salary;
            try {
                salary = Double.parseDouble(this.salaryField.getText().trim());
                if (salary <= 0) {
                    JOptionPane.showMessageDialog(this, "Salary must be greater than 0.");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid salary value.");
                return;
            }

            int id = EmployeeRepository.getInstance().generateId();

            Employee newEmployee;

            switch (role) {
                case "manager":
                    newEmployee = new Manager(id, firstName, lastName, email, phone, dept, hireDate, salary);
                    break;
                case "developer":
                    newEmployee = new Developer(id, firstName, lastName, email, phone, dept, hireDate, salary);
                    break;
                case "intern":
                    newEmployee = new Intern(id, firstName, lastName, email, phone, dept, hireDate, salary);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Please select a valid role.");
                    newEmployee = null;
                    break;
            }

            if(!this.employeeService.add(newEmployee)){
                JOptionPane.showMessageDialog(this, "Could not add employee.");
                return;
            }

            JOptionPane.showMessageDialog(this, "Employee added successfully.");
            goToDashboardWindow();
            return;
        }

        if (e.getSource() == this.cancelButton) {
            goToDashboardWindow();
            return;
        }
    }
}
