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
    }

    public boolean remove(int id) {
        return this.repository.getEmployees().removeIf(e -> e.getId() == id);
    }

    public Employee findById(int id) {
        for (Employee e : this.repository.getEmployees()) {
            if (e.getId() == id)
                return e;
        }
        return null;
    }

    public ArrayList<Employee> findAll() {
        return new ArrayList<>(this.repository.getEmployees());
    }

    public boolean existsById(int id) {
        return this.findById(id) != null;
    }

    public boolean update(Employee updated) {
        for (int i = 0; i < this.repository.getEmployees().size(); i++) {
            if (this.repository.getEmployees().get(i).getId() == updated.getId()) {
                this.repository.getEmployees().set(i, updated);
                return true;
            }
        }
        return false;
    }
}
