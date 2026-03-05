import java.util.Date;

public abstract class Employee {
    // Attributes
    protected int id;
    protected String firstName, lastName,
            email, phone,
            department;
    protected Date hireDate;
    protected double salary;

    // Default Constructor
    public Employee() {
        this.setId(0);
        this.setFistName("");
        this.setLastName("");
        this.setEmail("");
        this.setPhone("");
        this.setDepartment("");
        this.setHireDate(null);
        this.setSalary(0);
    }

    // Full Constructor
    public Employee(int id, String firstName, String lastName,
            String email, String phone, String department,
            Date hireDate, double salary) {
        this.setId(id);
        this.setFistName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setPhone(phone);
        this.setDepartment(department);
        this.setHireDate(hireDate);
        this.setSalary(salary);
    }

    // Getters
    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getDepartment() {
        return this.department;
    }

    public Date getHireDate() {
        return this.hireDate;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Setters
    public void setFistName(String firstName) {
        if (firstName != null) {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        if (lastName != null) {
            this.lastName = lastName;
        }
    }

    public void setEmail(String email) {
        if (email != null) {
            this.email = email;
        }
    }

    public void setPhone(String phone) {
        if (phone != null) {
            this.phone = phone;
        }
    }

    public void setDepartment(String department) {
        if (department != null) {
            this.department = department;
        }
    }

    public void setHireDate(Date date) {
        this.hireDate = date;
    }

    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        }
    }

    // Methods
    public String getFullName() {
        return String.format("%s %s", this.getFirstName(), this.getLastName());
    }

    public abstract String getRole();

    public abstract int getVacationDays();
}