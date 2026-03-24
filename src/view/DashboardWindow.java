package src.view;

import javax.swing.*;

import src.model.Employee;
import src.service.EmployeeService;
import src.service.UserService;
import src.session.Session;
import java.awt.*;
import java.awt.event.*;

public class DashboardWindow extends JFrame implements ActionListener {
    // Attributes
    private JLabel titleLabel, userLoggedLabel, searchLabel;
    private JLabel detailIdValue, detailNameValue, detailEmailValue, detailPhoneValue,
            detailDepartmentValue, detailRoleValue, detailHireDateValue, detailSalaryValue, detailVacationValue;
    private JTextField searchField;
    private JComboBox<String> deptCombo, roleCombo;
    private JButton addButton, editButton, deleteButton, logoutButton;
    private EmployeeListPanel listPanel;
    private UserService userService;
    private EmployeeService employeeService;

    // Constructor
    public DashboardWindow() {

        ImageIcon raw = new ImageIcon(UITheme.class.getResource("/assets/img/logoutIcon.png"));
        Image scaled = raw.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon logoutIcon = new ImageIcon(scaled);

        this.userService = new UserService();
        this.employeeService = new EmployeeService();

        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("EMS");
        this.getContentPane().setBackground(UITheme.BACKGROUND);
        this.setIconImage(UITheme.WINDOW_ICON.getImage());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(UITheme.BACKGROUND);
        Insets rowInsets = new Insets(5, 10, 5, 10);
        Insets toolbarInsets = new Insets(10, 10, 0, 10);
        Insets listInsets = new Insets(5, 10, 0, 10);

        this.titleLabel = new JLabel("Employee Management System");
        this.titleLabel.setFont(UITheme.FONT_TITLE);
        this.titleLabel.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.titleLabel,
                UITheme.gbc(0, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 1, 1.0, 0.0, rowInsets));

        this.userLoggedLabel = new JLabel("@" + Session.getInstance().getCurrentUser().getUsername());
        this.userLoggedLabel.setFont(UITheme.FONT_TITLE);
        this.userLoggedLabel.setForeground(UITheme.FONT_COLOR);
        formPanel.add(this.userLoggedLabel,
                UITheme.gbc(1, 0, GridBagConstraints.NONE, GridBagConstraints.EAST, 1, 0.0, 0.0, rowInsets));

        this.logoutButton = new JButton(logoutIcon);
        this.logoutButton.setToolTipText("Logout");
        this.logoutButton.setBorder(BorderFactory.createEmptyBorder());
        this.logoutButton.setContentAreaFilled(false);
        this.logoutButton.setFocusPainted(false);
        this.logoutButton.setOpaque(false);
        this.logoutButton.setMargin(new Insets(0, 0, 0, 0));
        this.logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.logoutButton.addActionListener(this);
        formPanel.add(this.logoutButton,
                UITheme.gbc(2, 0, GridBagConstraints.NONE, GridBagConstraints.EAST, 1, 0.0, 0.0, rowInsets));

        JPanel toolbarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        toolbarPanel.setBackground(UITheme.BACKGROUND);

        this.searchLabel = new JLabel("Search:");
        this.searchLabel.setFont(UITheme.FONT_NORMAL);
        this.searchLabel.setForeground(UITheme.FONT_COLOR);

        this.searchField = new JTextField(20);
        this.searchField.setFont(UITheme.FONT_INPUT);
        this.searchField.setBackground(UITheme.INPUT_BACKGROUND);
        this.searchField.setBorder(UITheme.INPUT_BORDER);
        this.searchField.setForeground(UITheme.FONT_COLOR);

        String[] departments = { "All Departments", "IT", "HR", "Finance", "Marketing", "Operations" };
        this.deptCombo = new JComboBox<>(departments);
        this.deptCombo.setFont(UITheme.FONT_NORMAL);
        this.deptCombo.setBackground(UITheme.INPUT_BACKGROUND);
        this.deptCombo.setForeground(UITheme.FONT_COLOR);

        String[] roles = { "All Roles", "Manager", "Developer", "Intern" };
        this.roleCombo = new JComboBox<>(roles);
        this.roleCombo.setFont(UITheme.FONT_NORMAL);
        this.roleCombo.setBackground(UITheme.INPUT_BACKGROUND);
        this.roleCombo.setForeground(UITheme.FONT_COLOR);

        this.addButton = new JButton("+ Add Employee");
        this.addButton.setBackground(UITheme.BUTTON_COLOR);
        this.addButton.setFont(UITheme.FONT_INPUT);
        this.addButton.setForeground(UITheme.BUTTON_FONT);
        this.addButton.addActionListener(this);

        toolbarPanel.add(this.searchLabel);
        toolbarPanel.add(this.searchField);
        toolbarPanel.add(this.deptCombo);
        toolbarPanel.add(this.roleCombo);
        toolbarPanel.add(this.addButton);

        formPanel.add(toolbarPanel,
                UITheme.gbc(0, 1, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 3, 1.0, 0.0, toolbarInsets));

        this.listPanel = new EmployeeListPanel();
        JPanel detailsPanel = buildDetailsPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.listPanel, detailsPanel);
        splitPane.setResizeWeight(0.68);
        splitPane.setBorder(BorderFactory.createLineBorder(UITheme.BORDER_COLOR));
        splitPane.setContinuousLayout(true);
        formPanel.add(splitPane,
            UITheme.gbc(0, 2, GridBagConstraints.BOTH, GridBagConstraints.WEST, 3, 1.0, 1.0, listInsets));

        KeyAdapter onType = new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                applyFilters();
            }
        };
        ActionListener onSelect = e -> applyFilters();

        this.searchField.addKeyListener(onType);
        this.deptCombo.addActionListener(onSelect);
        this.roleCombo.addActionListener(onSelect);
        this.listPanel.addSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                updateDetailPanel(this.listPanel.getSelectedEmployee());
            }
        });

        clearDetailPanel();

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(UITheme.BACKGROUND);
        centerWrapper.add(formPanel);
        this.add(centerWrapper);
    }

    // Methods
    private JPanel buildDetailsPanel() {
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBackground(UITheme.BACKGROUND);
        detailsPanel.setBorder(UITheme.FORMBORDER);
        Insets insets = new Insets(4, 6, 4, 6);

        JLabel detailsTitle = new JLabel("Employee Details");
        detailsTitle.setFont(UITheme.FONT_INPUT);
        detailsTitle.setForeground(UITheme.FONT_COLOR);
        detailsPanel.add(detailsTitle,
                UITheme.gbc(0, 0, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 2, 1.0, 0.0, insets));

        this.detailIdValue = addDetailRow(detailsPanel, "ID:", 1, insets);
        this.detailNameValue = addDetailRow(detailsPanel, "Full Name:", 2, insets);
        this.detailEmailValue = addDetailRow(detailsPanel, "Email:", 3, insets);
        this.detailPhoneValue = addDetailRow(detailsPanel, "Phone:", 4, insets);
        this.detailDepartmentValue = addDetailRow(detailsPanel, "Department:", 5, insets);
        this.detailRoleValue = addDetailRow(detailsPanel, "Role:", 6, insets);
        this.detailHireDateValue = addDetailRow(detailsPanel, "Hire Date:", 7, insets);
        this.detailSalaryValue = addDetailRow(detailsPanel, "Salary:", 8, insets);
        this.detailVacationValue = addDetailRow(detailsPanel, "Vacation Days:", 9, insets);

        JPanel actionsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        actionsPanel.setBackground(UITheme.BACKGROUND);

        this.editButton = new JButton("Edit");
        this.editButton.setBackground(UITheme.BUTTON_COLOR);
        this.editButton.setFont(UITheme.FONT_INPUT);
        this.editButton.setForeground(UITheme.BUTTON_FONT);
        this.editButton.addActionListener(this);

        this.deleteButton = new JButton("Delete");
        this.deleteButton.setBackground(UITheme.BUTTON_COLOR);
        this.deleteButton.setFont(UITheme.FONT_INPUT);
        this.deleteButton.setForeground(UITheme.BUTTON_FONT);
        this.deleteButton.addActionListener(this);

        actionsPanel.add(this.editButton);
        actionsPanel.add(this.deleteButton);

        detailsPanel.add(actionsPanel,
                UITheme.gbc(0, 10, GridBagConstraints.HORIZONTAL, GridBagConstraints.CENTER, 2, 1.0, 0.0, insets));

        detailsPanel.add(Box.createVerticalGlue(),
                UITheme.gbc(0, 11, GridBagConstraints.BOTH, GridBagConstraints.CENTER, 2, 1.0, 1.0, insets));

        return detailsPanel;
    }

    private JLabel addDetailRow(JPanel detailsPanel, String labelText, int row, Insets insets) {
        JLabel label = new JLabel(labelText);
        label.setFont(UITheme.FONT_NORMAL);
        label.setForeground(UITheme.FONT_COLOR);
        detailsPanel.add(label,
                UITheme.gbc(0, row, GridBagConstraints.NONE, GridBagConstraints.WEST, 1, 0.0, 0.0, insets));

        JLabel value = new JLabel("-");
        value.setFont(UITheme.FONT_NORMAL);
        value.setForeground(UITheme.FONT_COLOR);
        detailsPanel.add(value,
                UITheme.gbc(1, row, GridBagConstraints.HORIZONTAL, GridBagConstraints.WEST, 1, 1.0, 0.0, insets));

        return value;
    }

    private void updateDetailPanel(Employee employee) {
        if (employee == null) {
            clearDetailPanel();
            return;
        }

        this.detailIdValue.setText(String.valueOf(employee.getId()));
        this.detailNameValue.setText(employee.getFullName());
        this.detailEmailValue.setText(employee.getEmail());
        this.detailPhoneValue.setText(employee.getPhone());
        this.detailDepartmentValue.setText(employee.getDepartment());
        this.detailRoleValue.setText(employee.getRole());
        this.detailHireDateValue.setText(String.valueOf(employee.getHireDate()));
        this.detailSalaryValue.setText(String.format("%.2f", employee.getSalary()));
        this.detailVacationValue.setText(String.valueOf(employee.getVacationDays()));

        this.editButton.setEnabled(true);
        this.deleteButton.setEnabled(true);
    }

    private void clearDetailPanel() {
        this.detailIdValue.setText("-");
        this.detailNameValue.setText("Select an employee");
        this.detailEmailValue.setText("-");
        this.detailPhoneValue.setText("-");
        this.detailDepartmentValue.setText("-");
        this.detailRoleValue.setText("-");
        this.detailHireDateValue.setText("-");
        this.detailSalaryValue.setText("-");
        this.detailVacationValue.setText("-");

        if (this.editButton != null) {
            this.editButton.setEnabled(false);
        }
        if (this.deleteButton != null) {
            this.deleteButton.setEnabled(false);
        }
    }

    private void applyFilters() {
        this.listPanel.refreshTable(
                this.searchField.getText(),
                (String) this.deptCombo.getSelectedItem(),
                (String) this.roleCombo.getSelectedItem());
        this.listPanel.clearSelection();
        clearDetailPanel();
    }

    private void goToFormPanel() {
        goToFormPanel(null);
    }

    private void goToFormPanel(Employee employee) {
        EmployeeFormPanel formPanel = employee == null
                ? new EmployeeFormPanel()
                : new EmployeeFormPanel(employee);
        formPanel.setBounds(0, 0, 900, 600);
        formPanel.setVisible(true);
        formPanel.setResizable(true);
        formPanel.setLocationRelativeTo(null);
        this.dispose();
    }

    private void goToLoginWindow() {
        LoginWindow login = new LoginWindow();
        login.setBounds(0, 0, 350, 470);
        login.setVisible(true);
        login.setResizable(true);
        login.setLocationRelativeTo(null);
        this.dispose();
    }

    private void deleteSelectedEmployee() {
        Employee selected = this.listPanel.getSelectedEmployee();
        if (selected == null) {
            JOptionPane.showMessageDialog(this, "Please select an employee first.");
            return;
        }

        int option = JOptionPane.showConfirmDialog(
                this,
                "Delete " + selected.getFullName() + "? This action cannot be undone.",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (option != JOptionPane.YES_OPTION) {
            return;
        }

        if (!this.employeeService.remove(selected.getId())) {
            JOptionPane.showMessageDialog(this, "Could not delete employee.");
            return;
        }

        JOptionPane.showMessageDialog(this, "Employee deleted successfully.");
        applyFilters();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() != this.addButton
                && e.getSource() != this.logoutButton
                && e.getSource() != this.editButton
                && e.getSource() != this.deleteButton)
            return;

        if (e.getSource() == this.addButton) {
            goToFormPanel();
            return;
        }

        if (e.getSource() == this.editButton) {
            Employee selected = this.listPanel.getSelectedEmployee();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Please select an employee first.");
                return;
            }
            goToFormPanel(selected);
            return;
        }

        if (e.getSource() == this.deleteButton) {
            deleteSelectedEmployee();
            return;
        }

        if (e.getSource() == this.logoutButton) {
            this.userService.logout();
            goToLoginWindow();
            return;
        }
    }
}
