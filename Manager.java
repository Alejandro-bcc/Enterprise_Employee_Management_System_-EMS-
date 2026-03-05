import java.util.Date;

public class Manager extends Employee {
    // Default Constructor
    public Manager() {
        super();
    }

    // Full Constructor
    public Manager(int id, String firstName, String lastName,
            String email, String phone, String department,
            Date hireDate, double salary) {
        super(id, firstName, lastName, email, phone, department, hireDate, salary);
    }

    public String getRole() {
        return "Manager";
    }

    public int getVacationDays() {
        return 20;
    }
}
