package src.view;

import javax.swing.*;
import src.session.Session;
import java.awt.*;
import java.awt.event.*;

public class DashboardWindow extends JFrame implements ActionListener {
    // Attributes
    private JLabel titleLabel, userLoggedLabel, searchLabel;
    private JTextField searchField;
    private JComboBox<String> deptCombo, roleCombo;
    private JButton addButton;
    private EmployeeListPanel listPanel;

    // Constructor
    public DashboardWindow() {

        this.setLayout(new GridBagLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("EMS");
        this.getContentPane().setBackground(UITheme.BACKGROUND);
        this.setIconImage(UITheme.WINDOW_ICON.getImage());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(UITheme.BACKGROUND);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        this.titleLabel = new JLabel("Employee Management System");
        this.titleLabel.setFont(UITheme.FONT_TITLE);
        this.titleLabel.setForeground(UITheme.FONT_COLOR);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(this.titleLabel, gbc);

        this.userLoggedLabel = new JLabel("@" + Session.getInstance().getCurrentUser().getUsername());
        this.userLoggedLabel.setFont(UITheme.FONT_TITLE);
        this.userLoggedLabel.setForeground(UITheme.FONT_COLOR);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(this.userLoggedLabel, gbc);

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

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 0, 10);
        formPanel.add(toolbarPanel, gbc);

        this.listPanel = new EmployeeListPanel();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 10, 0, 10);
        formPanel.add(this.listPanel, gbc);

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

    private void openFormPanel() {
        EmployeeFormPanel formPanel = new EmployeeFormPanel();
        formPanel.setBounds(0, 0, 450, 600);
        formPanel.setVisible(true);
        formPanel.setResizable(true);
        formPanel.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() != this.addButton)
            return;

        if (e.getSource() == this.addButton) {
            openFormPanel();
            return;
        }
    }
}
