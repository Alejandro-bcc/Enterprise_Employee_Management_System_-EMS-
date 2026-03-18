package src.view;

import javax.swing.*;

import src.service.UserService;
import src.session.Session;
import java.awt.*;
import java.awt.event.*;

public class DashboardWindow extends JFrame implements ActionListener {
    // Attributes
    private JLabel titleLabel, userLoggedLabel, searchLabel;
    private JTextField searchField;
    private JComboBox<String> deptCombo, roleCombo;
    private JButton addButton, logoutButton;
    private EmployeeListPanel listPanel;
    private UserService userService;

    // Constructor
    public DashboardWindow() {

        ImageIcon raw = new ImageIcon(UITheme.class.getResource("/assets/img/logoutIcon.png"));
        Image scaled = raw.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon logoutIcon = new ImageIcon(scaled);

        this.userService = new UserService();

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
        formPanel.add(this.listPanel,
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

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setBackground(UITheme.BACKGROUND);
        centerWrapper.add(formPanel);
        this.add(centerWrapper);
    }

    // Methods
    private void applyFilters() {
        this.listPanel.refreshTable(
                this.searchField.getText(),
                (String) this.deptCombo.getSelectedItem(),
                (String) this.roleCombo.getSelectedItem());
    }

    private void goToFormPanel() {
        EmployeeFormPanel formPanel = new EmployeeFormPanel();
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() != this.addButton && e.getSource() != this.logoutButton)
            return;

        if (e.getSource() == this.addButton) {
            goToFormPanel();
            return;
        }

        if (e.getSource() == this.logoutButton) {
            this.userService.logout();
            goToLoginWindow();
            return;
        }
    }
}
