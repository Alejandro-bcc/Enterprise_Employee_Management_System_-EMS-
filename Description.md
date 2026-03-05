# Enterprise Employee Management System (EMS)

A desktop GUI application built with **Java** and **Swing (JFrame)** that simulates a real-world enterprise employee management system. The project demonstrates core **Object-Oriented Programming (OOP)** principles and is designed to serve as a strong portfolio piece for Java developer roles.

> **Note:** This project intentionally avoids database integration. All data is managed in-memory using Java collections (`ArrayList`, `HashMap`), keeping the focus on Java and OOP design.

---

## Purpose

This project was built to:

- Practice and demonstrate Java OOP fundamentals in a realistic context.
- Implement a complete CRUD system with a GUI.
- Apply software design patterns (DAO, Singleton, MVC-like separation).
- Produce a portfolio project that showcases clean architecture and real-world problem solving.

---

## Technologies

| Layer        | Technology                              |
|--------------|-----------------------------------------|
| Language     | Java 8+                                 |
| GUI          | Java Swing (`JFrame`, `JPanel`, `JTable`) |
| Events       | `ActionListener`, `MouseListener`       |
| Data storage | In-memory (`ArrayList`, `HashMap`)      |
| Build        | Manual `javac` / any IDE (IntelliJ, VS Code, NetBeans) |

---

## OOP Concepts Demonstrated

| Concept           | Where it appears                                                  |
|-------------------|-------------------------------------------------------------------|
| **Encapsulation** | `Employee`, `User` — private fields with public getters/setters  |
| **Inheritance**   | `Manager`, `Developer`, `Intern` all extend `Employee`           |
| **Polymorphism**  | Method overriding: `getRole()`, `getVacationDays()` per subclass |
| **Abstraction**   | `IEmployeeService`, `IUserService` interfaces define contracts   |
| **Composition**   | `Dashboard` is composed of independent `JPanel` views            |
| **Design Pattern: DAO** | `EmployeeDAO`, `UserDAO` isolate data logic from the UI   |
| **Design Pattern: Singleton** | `EmployeeRepository` holds one shared in-memory store |

---

## Required Functionalities

### 1. Authentication Module
- **Register:** New users fill in username, email, and password. Duplicate usernames are rejected. Password must be at least 6 characters.
- **Login:** Validates credentials against the in-memory user store. Incorrect credentials show a clear error message.
- **Logout:** Returns the user to the Login window from any point in the app.
- **Session:** A `Session` class (Singleton) stores the currently logged-in user.

### 2. Employee CRUD Module
- **Create (Add Employee):**  Form to input: ID (auto-generated), first name, last name, email, phone, department, role (Manager / Developer / Intern), hire date, and salary.
- **Read (List Employees):**  All employees displayed in a `JTable` with columns: ID, Full Name, Department, Role, Hire Date. Rows are clickable to view full details.
- **Read (Employee Detail):**  A dedicated panel/window showing all fields for a selected employee.
- **Update (Edit Employee):**  Pre-filled form with the selected employee's data. User can modify any field and save changes.
- **Delete (Remove Employee):**  Confirmation dialog before permanently removing an employee from the system.

### 3. Search & Filter
- **Search by name** (partial match, case-insensitive) using a text field that filters the `JTable` in real time.
- **Filter by department** using a `JComboBox` dropdown.
- **Filter by role** using a second `JComboBox`.
- Filters can be combined.

### 4. Dashboard / Statistics Panel
- Total number of employees.
- Breakdown by department (e.g., "IT: 5 | HR: 3 | Finance: 2").
- Breakdown by role.
- Most recently added employee.

---

## Class Architecture

```
src/
├── model/
│   ├── Employee.java          # Abstract base class: id, name, email, dept, salary, hireDate
│   ├── Manager.java           # extends Employee — overrides getRole(), getVacationDays()
│   ├── Developer.java         # extends Employee — overrides getRole(), getVacationDays()
│   ├── Intern.java            # extends Employee — overrides getRole(), getVacationDays()
│   └── User.java              # username, email, passwordHash
│
├── service/
│   ├── IEmployeeService.java  # Interface: add, update, delete, findById, findAll, search
│   ├── IUserService.java      # Interface: register, login, logout
│   ├── EmployeeService.java   # Implements IEmployeeService — business logic
│   └── UserService.java       # Implements IUserService — auth logic
│
├── dao/
│   ├── EmployeeDAO.java       # Manages in-memory ArrayList<Employee>
│   └── UserDAO.java           # Manages in-memory HashMap<String, User>
│
├── repository/
│   └── EmployeeRepository.java  # Singleton — single shared data source
│
├── session/
│   └── Session.java           # Singleton — holds the logged-in User
│
└── view/
    ├── LoginWindow.java        # Login screen (entry point)
    ├── RegisterWindow.java     # New user registration screen
    ├── DashboardWindow.java    # Main app shell: menu bar + content area
    ├── EmployeeListPanel.java  # JTable panel — lists all employees
    ├── EmployeeFormPanel.java  # Reusable form for Add and Edit
    ├── EmployeeDetailPanel.java# Read-only details view
    └── StatisticsPanel.java    # Dashboard stats panel
```

---

## GUI Window Flow

```
[LoginWindow]
     │
     ├──(no account?)──► [RegisterWindow] ──► back to [LoginWindow]
     │
     └──(success)──► [DashboardWindow]
                          │
                          ├── Menu: Employees ──► [EmployeeListPanel]
                          │                            │
                          │                     [EmployeeFormPanel] (Add / Edit)
                          │                     [EmployeeDetailPanel] (View)
                          │
                          ├── Menu: Statistics ──► [StatisticsPanel]
                          │
                          └── Menu: Logout ──► back to [LoginWindow]
```

---

## Employee Model Details

```java
// Employee.java (abstract)
- int id
- String firstName, lastName
- String email, phone
- String department
- Date hireDate
- double salary
+ abstract String getRole()
+ abstract int getVacationDays()
+ String getFullName()
+ String toString()

// Manager.java
+ getRole()        → "Manager"
+ getVacationDays()→ 20

// Developer.java
+ getRole()        → "Developer"
+ getVacationDays()→ 15

// Intern.java
+ getRole()        → "Intern"
+ getVacationDays()→ 5
```

---

## Validation Rules

| Field         | Rule                                              |
|---------------|---------------------------------------------------|
| First / Last name | Non-empty, letters only, max 50 chars        |
| Email         | Must match basic email format (`x@x.x`)          |
| Phone         | Digits only, 7–15 characters                     |
| Salary        | Positive decimal number                           |
| Department    | Selected from a fixed list via `JComboBox`        |
| Username (register) | Non-empty, no spaces, 4–20 chars, unique  |
| Password      | Minimum 6 characters                             |

---

## How to Build and Run

```bash
# 1. Compile all source files
javac -d out src/**/*.java

# 2. Run the application
java -cp out view.LoginWindow
```

Or open the project folder in your IDE and run `LoginWindow.java` as the main entry point.

---

## Suggested Development Order

1. `Employee`, `Manager`, `Developer`, `Intern` model classes.
2. `User` class and `Session` singleton.
3. `EmployeeRepository` singleton and `EmployeeDAO` / `UserDAO`.
4. `IEmployeeService` interface and `EmployeeService` implementation.
5. `IUserService` interface and `UserService` implementation.
6. `LoginWindow` and `RegisterWindow` GUI.
7. `DashboardWindow` with menu bar and panel switching.
8. `EmployeeListPanel` with `JTable` and search/filter.
9. `EmployeeFormPanel` (shared for Add and Edit).
10. `EmployeeDetailPanel` and `StatisticsPanel`.

---

## Future Improvements (Out of Scope for Now)

- Persist data to a file (`.csv` or `.json`) on app close / load.
- Connect to a relational database (MySQL / PostgreSQL) via JDBC.
- Replace DAO layer with JPA / Hibernate ORM.
- Add role-based access control (only Managers can delete employees).
- Export employee list to PDF or Excel.
- Migrate GUI to JavaFX for a more modern look.

---

## Author

Developed by **Alejandro Nava** as a portfolio project to demonstrate Java OOP design and Swing GUI development skills.

