package src.dao;

import src.model.Employee;
import src.repository.EmployeeRepository;
import java.util.ArrayList;

public class EmployeeDAO {
    // Attribute
    private EmployeeRepository repository;

    // Constructor
    public EmployeeDAO() {
        this.repository = EmployeeRepository.getInstance();
    }

    // Methods
    public void add(Employee e) {
        this.repository.getEmployees().add(e);
        this.repository.save();
    }

    public boolean remove(int id) {
        boolean removed = this.repository.getEmployees().removeIf(e -> e.getId() == id);
        if(removed){
            this.repository.save();
        }
        return removed;
    }

    public Employee findById(int id) {
        ArrayList<Employee> list = this.repository.getEmployees();

        for (int i = 0; i < list.size(); i++) {
            Employee e = list.get(i);
            if (e.getId() == id)
                return e;
        }
        return null;
    }

    public ArrayList<Employee> findByName(String query) {
        if (query == null || query.isBlank())
            return this.findAll();

        ArrayList<Employee> list = this.repository.getEmployees();
        ArrayList<Employee> arr = new ArrayList<Employee>();
        String q = query.toLowerCase();

        for (int i = 0; i < list.size(); i++) {
            Employee e = list.get(i);
            if (e.getFullName().toLowerCase().contains(q))
                arr.add(e);
        }
        return arr;
    }

    public ArrayList<Employee> findByDepartment(String department) {
        if (department == null || department.isBlank())
            return this.findAll();

        ArrayList<Employee> list = this.repository.getEmployees();
        ArrayList<Employee> arr = new ArrayList<Employee>();

        for (int i = 0; i < list.size(); i++) {
            Employee e = list.get(i);
            if (e.getDepartment().equalsIgnoreCase(department))
                arr.add(e);
        }
        return arr;
    }

    public ArrayList<Employee> findByRole(String role) {
        if (role == null || role.isBlank())
            return this.findAll();

        ArrayList<Employee> list = this.repository.getEmployees();
        ArrayList<Employee> arr = new ArrayList<Employee>();

        for (int i = 0; i < list.size(); i++) {
            Employee e = list.get(i);
            if (e.getRole().equalsIgnoreCase(role))
                arr.add(e);
        }
        return arr;
    }

    public ArrayList<Employee> findAll() {
        return new ArrayList<>(this.repository.getEmployees());
    }

    public boolean existsById(int id) {
        return this.findById(id) != null;
    }

    public boolean update(Employee updated) {
        ArrayList<Employee> list = this.repository.getEmployees();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == updated.getId()) {
                list.set(i, updated);
                this.repository.save();
                return true;
            }
        }
        return false;
    }
}
