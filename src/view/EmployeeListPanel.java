package src.view;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.Dimension;
import java.util.ArrayList;
import src.model.Employee;
import src.service.EmployeeService;

public class EmployeeListPanel extends JPanel {

    // Attributes
    private DefaultTableModel model;
    private JTable table;
    private EmployeeService service;
    private ArrayList<Employee> visibleEmployees;

    // Constructor
    public EmployeeListPanel() {
        this.service = new EmployeeService();
        this.visibleEmployees = new ArrayList<>();

        this.setLayout(new BorderLayout());

        String[] columns = { "ID", "Full name", "Department", "Role", "Hire Date" };
        this.model = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        this.table = new JTable(this.model);
        this.table.setFont(UITheme.FONT_NORMAL);
        this.table.setForeground(UITheme.FONT_COLOR);
        this.table.setBackground(UITheme.BACKGROUND);
        this.table.setRowHeight(28);
        this.table.setGridColor(UITheme.BORDER_COLOR);
        this.table.setSelectionBackground(UITheme.BUTTON_COLOR);
        this.table.setSelectionForeground(UITheme.BUTTON_FONT);
        this.table.setIntercellSpacing(new Dimension(1, 1));
        JTableHeader header = this.table.getTableHeader();
        header.setFont(UITheme.FONT_INPUT);
        header.setBackground(UITheme.INPUT_BACKGROUND);
        header.setForeground(UITheme.FONT_COLOR);
        header.setReorderingAllowed(false);
        this.add(new JScrollPane(this.table), BorderLayout.CENTER);

        refreshTable("", "All Departments", "All Roles");
    }

    // Methods
    public void refreshTable(String query, String department, String role) {
        this.model.setRowCount(0);
        this.visibleEmployees.clear();
        for (Employee emp : this.service.findAll()) {
            boolean matchesName = emp.getFullName().toLowerCase().contains(query.toLowerCase());
            boolean matchesDept = department.equals("All Departments") || emp.getDepartment().equals(department);
            boolean matchesRole = role.equals("All Roles") || emp.getRole().equals(role);
            if (matchesName && matchesDept && matchesRole) {
                this.visibleEmployees.add(emp);
                this.model.addRow(new Object[] {
                        emp.getId(), emp.getFullName(),
                        emp.getDepartment(), emp.getRole(), emp.getHireDate()
                });
            }
        }
    }

    public void addSelectionListener(ListSelectionListener listener) {
        this.table.getSelectionModel().addListSelectionListener(listener);
    }

    public Employee getSelectedEmployee() {
        int selectedRow = this.table.getSelectedRow();
        if (selectedRow < 0 || selectedRow >= this.visibleEmployees.size()) {
            return null;
        }
        return this.visibleEmployees.get(selectedRow);
    }

    public void clearSelection() {
        this.table.clearSelection();
    }
}
