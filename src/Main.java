import java.io.*;
import java.util.ArrayList;
import java.util.List;

interface IEmployee {
    void showInfo();
}

class Employee implements IEmployee, Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String fullName;
    private String dob;
    private String phoneNumber;
    private String email;
    private String employeeType;
    private static int employeeCount = 0;

    public Employee(int id, String fullName, String dob, String phoneNumber, String email, String employeeType) {
        this.id = id;
        this.fullName = fullName;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.employeeType = employeeType;
        employeeCount++;
    }

    public static int getEmployeeCount() {
        return employeeCount;
    }

    public void showInfo() {
        System.out.println("ID: " + id);
        System.out.println("Full Name: " + fullName);
        System.out.println("Date of Birth: " + dob);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Employee Type: " + employeeType);
    }
}

class ExperienceEmployee extends Employee {
    private int expInYear;
    private String proSkill;

    public ExperienceEmployee(int id, String fullName, String dob, String phoneNumber, String email, int expInYear, String proSkill) {
        super(id, fullName, dob, phoneNumber, email, "Experience");
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Experience in years: " + expInYear);
        System.out.println("Professional Skill: " + proSkill);
    }
}

class FresherEmployee extends Employee {
    private String graduationDate;
    private String graduationRank;
    private String education;

    public FresherEmployee(int id, String fullName, String dob, String phoneNumber, String email, String graduationDate, String graduationRank, String education) {
        super(id, fullName, dob, phoneNumber, email, "Fresher");
        this.graduationDate = graduationDate;
        this.graduationRank = graduationRank;
        this.education = education;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Graduation Date: " + graduationDate);
        System.out.println("Graduation Rank: " + graduationRank);
        System.out.println("Education: " + education);
    }
}

class InternEmployee extends Employee {
    private String majors;
    private String semester;
    private String universityName;

    public InternEmployee(int id, String fullName, String dob, String phoneNumber, String email, String majors, String semester, String universityName) {
        super(id, fullName, dob, phoneNumber, email, "Intern");
        this.majors = majors;
        this.semester = semester;
        this.universityName = universityName;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Majors: " + majors);
        System.out.println("Semester: " + semester);
        System.out.println("University Name: " + universityName);
    }
}

class EmployeeManagement {
    private List<Employee> employees;

    public EmployeeManagement() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void showAllEmployees() {
        for (Employee employee : employees) {
            employee.showInfo();
            System.out.println("----------------------");
        }
    }

    public static void writeEmployeesToFile(List<Employee> employees, String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(employees);
            out.close();
            fileOut.close();
            System.out.println("Employee data is written to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Employee> readEmployeesFromFile(String filename) {
        List<Employee> employees = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            employees = (List<Employee>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}

public class Main {
    public static void main(String[] args) {
        // Create some employees
        EmployeeManagement employeeManagement = new EmployeeManagement();
        Employee employee1 = new ExperienceEmployee(1, "Nguyễn Văn A", "1990-01-01", "0123456789", "nguyenvana@example.com", 5, "Java");
        Employee employee2 = new FresherEmployee(2, "Trần Thị B", "1995-05-05", "0987654321", "tranthib@example.com", "2022-05-05", "Xuất sắc", "Đại học Bách Khoa");
        Employee employee3 = new InternEmployee(3, "Lê Hoàng C", "2000-10-10", "036123789", "lehoangc@example.com", "Khoa học máy tính", " 2024", "Trường Đại học Duy Tan");


        // Add employees to the management system
        employeeManagement.addEmployee(employee1);
        employeeManagement.addEmployee(employee2);
        employeeManagement.addEmployee(employee3);

        // Show all employees
        System.out.println("All Employees:");
        employeeManagement.showAllEmployees();

        // Write employees to file
        EmployeeManagement.writeEmployeesToFile(employeeManagement.getEmployees(), "employees_output.txt");

        // Read employees from file
        List<Employee> loadedEmployees = EmployeeManagement.readEmployeesFromFile("employees_input.txt");
        System.out.println("\nLoaded Employees:");
        for (Employee employee : loadedEmployees) {
            employee.showInfo();
            System.out.println("----------------------");
        }
    }
}
