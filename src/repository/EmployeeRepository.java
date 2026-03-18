package src.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import src.model.Developer;
import src.model.Employee;
import src.model.Intern;
import src.model.Manager;

public class EmployeeRepository {
    private static final Path DATA_FILE = Paths.get("data", "employees.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static EmployeeRepository instance;
    private final ArrayList<Employee> employees;
    private int nextId;

    private EmployeeRepository() {
        this.employees = new ArrayList<>();
        this.nextId = 1;
        this.loadFromDisk();
    }

    public static EmployeeRepository getInstance() {
        if (instance == null) {
            instance = new EmployeeRepository();
        }
        return instance;
    }

    public ArrayList<Employee> getEmployees() {
        return this.employees;
    }

    public synchronized int generateId() {
        return this.nextId++;
    }

    public synchronized void save() {
        try {
            Files.createDirectories(DATA_FILE.getParent());

            EmployeeStore store = new EmployeeStore();
            store.nextId = this.nextId;
            for (Employee e : this.employees) {
                store.employees.add(EmployeeRecord.fromEmployee(e));
            }

            try (BufferedWriter writer = Files.newBufferedWriter(DATA_FILE, StandardCharsets.UTF_8)) {
                GSON.toJson(store, writer);
            }
        } catch (IOException ex) {
            throw new RuntimeException("Could not save employees.json", ex);
        }
    }

    private synchronized void loadFromDisk() {
        if (!Files.exists(DATA_FILE)) {
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(DATA_FILE, StandardCharsets.UTF_8)) {
            EmployeeStore store = GSON.fromJson(reader, EmployeeStore.class);
            if (store == null) {
                return;
            }

            this.employees.clear();
            if (store.employees != null) {
                for (EmployeeRecord r : store.employees) {
                    Employee e = r.toEmployee();
                    if (e != null) {
                        this.employees.add(e);
                    }
                }
            }

            int maxId = 0;
            for (Employee e : this.employees) {
                if (e.getId() > maxId) {
                    maxId = e.getId();
                }
            }

            this.nextId = Math.max(store.nextId, maxId + 1);
            if (this.nextId < 1) {
                this.nextId = 1;
            }
        } catch (Exception ex) {
            throw new RuntimeException("Could not load employees.json", ex);
        }
    }

    private static class EmployeeStore {
        int nextId = 1;
        List<EmployeeRecord> employees = new ArrayList<>();
    }

    private static class EmployeeRecord {
        int id;
        String firstName;
        String lastName;
        String email;
        String phone;
        String department;
        String hireDate;
        double salary;
        String role;

        static EmployeeRecord fromEmployee(Employee e) {
            EmployeeRecord r = new EmployeeRecord();
            r.id = e.getId();
            r.firstName = e.getFirstName();
            r.lastName = e.getLastName();
            r.email = e.getEmail();
            r.phone = e.getPhone();
            r.department = e.getDepartment();
            r.hireDate = e.getHireDate() == null ? null : e.getHireDate().toString();
            r.salary = e.getSalary();
            r.role = e.getRole();
            return r;
        }

        Employee toEmployee() {
            LocalDate parsedDate = (this.hireDate == null || this.hireDate.isBlank())
                    ? null
                    : LocalDate.parse(this.hireDate);

            if ("Manager".equalsIgnoreCase(this.role)) {
                return new Manager(this.id, this.firstName, this.lastName, this.email, this.phone,
                        this.department, parsedDate, this.salary);
            }

            if ("Developer".equalsIgnoreCase(this.role)) {
                return new Developer(this.id, this.firstName, this.lastName, this.email, this.phone,
                        this.department, parsedDate, this.salary);
            }

            if ("Intern".equalsIgnoreCase(this.role)) {
                return new Intern(this.id, this.firstName, this.lastName, this.email, this.phone,
                        this.department, parsedDate, this.salary);
            }

            return null;
        }
    }
}
