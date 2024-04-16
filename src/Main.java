import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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


    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDob() {
        return dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getEmployeeType() {
        return employeeType;
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

    public int getExpInYear() {
        return expInYear;
    }

    public String getProSkill() {
        return proSkill;
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
    public String getGraduationDate() {
        return graduationDate;
    }

    public String getGraduationRank() {
        return graduationRank;
    }

    public String getEducation() {
        return education;
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
    public String getMajors() {
        return majors;
    }

    public String getSemester() {
        return semester;
    }

    public String getUniversityName() {
        return universityName;
    }
}


class EmployeeManagement {
    private List<Employee> employees;

    public EmployeeManagement() {
        this.employees = new ArrayList<>();
    }

    // Phương thức thêm nhân viên vào danh sách quản lý
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Phương thức hiển thị tất cả nhân viên trong danh sách
    public void showAllEmployees() {
        for (Employee employee : employees) {
            employee.showInfo();
            System.out.println("----------------------");
        }
    }
    // Phương thức ghi dữ liệu vào file text
    public static void writeEmployeesToTextFile(List<Employee> employees, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Employee employee : employees) {
                // Ghi thông tin của mỗi nhân viên vào file
                writer.println("ID: " + employee.getId());
                writer.println("Full Name: " + employee.getFullName());
                writer.println("Date of Birth: " + employee.getDob());
                writer.println("Phone Number: " + employee.getPhoneNumber());
                writer.println("Email: " + employee.getEmail());
                writer.println("Employee Type: " + employee.getEmployeeType());
                if (employee instanceof ExperienceEmployee) {
                    ExperienceEmployee expEmployee = (ExperienceEmployee) employee;
                    writer.println("Experience in years: " + expEmployee.getExpInYear());
                    writer.println("Professional Skill: " + expEmployee.getProSkill());
                } else if (employee instanceof FresherEmployee) {
                    FresherEmployee fresherEmployee = (FresherEmployee) employee;
                    writer.println("Graduation Date: " + fresherEmployee.getGraduationDate());
                    writer.println("Graduation Rank: " + fresherEmployee.getGraduationRank());
                    writer.println("Education: " + fresherEmployee.getEducation());
                } else if (employee instanceof InternEmployee) {
                    InternEmployee internEmployee = (InternEmployee) employee;
                    writer.println("Majors: " + internEmployee.getMajors());
                    writer.println("Semester: " + internEmployee.getSemester());
                    writer.println("University Name: " + internEmployee.getUniversityName());
                }
                writer.println("----------------------");
            }
            System.out.println("Employee data is written to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Phương thức đọc dữ liệu từ file text
    public static List<Employee> readEmployeesFromTextFile(String filename) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Đọc thông tin từ file và tạo các đối tượng nhân viên tương ứng
                int id = Integer.parseInt(line.split(": ")[1]);
                String fullName = reader.readLine().split(": ")[1];
                String dob = reader.readLine().split(": ")[1];
                String phoneNumber = reader.readLine().split(": ")[1];
                String email = reader.readLine().split(": ")[1];
                String employeeType = reader.readLine().split(": ")[1];
                switch (employeeType) {
                    case "Experience":
                        int expInYear = Integer.parseInt(reader.readLine().split(": ")[1]);
                        String proSkill = reader.readLine().split(": ")[1];
                        employees.add(new ExperienceEmployee(id, fullName, dob, phoneNumber, email, expInYear, proSkill));
                        break;
                    case "Fresher":
                        String graduationDate = reader.readLine().split(": ")[1];
                        String graduationRank = reader.readLine().split(": ")[1];
                        String education = reader.readLine().split(": ")[1];
                        employees.add(new FresherEmployee(id, fullName, dob, phoneNumber, email, graduationDate, graduationRank, education));
                        break;
                    case "Intern":
                        String majors = reader.readLine().split(": ")[1];
                        String semester = reader.readLine().split(": ")[1];
                        String universityName = reader.readLine().split(": ")[1];
                        employees.add(new InternEmployee(id, fullName, dob, phoneNumber, email, majors, semester, universityName));
                        break;
                    default:
                        break;
                }
                // Đọc và bỏ qua dòng gạch ngang
                reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

}




public class Main {
    // Biến scanner để đọc dữ liệu từ bàn phím
    private static Scanner scanner = new Scanner(System.in);

    // Phương thức thêm nhân viên mới
    public static void addNewEmployee(EmployeeManagement employeeManagement) {
        // Nhập thông tin từ người dùng
        System.out.println("Enter employee information:");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Date of Birth: ");
        String dob = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Employee Type (Experience/Fresher/Intern): ");
        String employeeType = scanner.nextLine();

        // Tạo đối tượng nhân viên tương ứng
        switch (employeeType.toLowerCase()) {
            case "experience":
                System.out.print("Years of Experience: ");
                int expInYear = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Professional Skill: ");
                String proSkill = scanner.nextLine();
                employeeManagement.addEmployee(new ExperienceEmployee(id, fullName, dob, phoneNumber, email, expInYear, proSkill));
                break;
            case "fresher":
                System.out.print("Graduation Date: ");
                String graduationDate = scanner.nextLine();
                System.out.print("Graduation Rank: ");
                String graduationRank = scanner.nextLine();
                System.out.print("Education: ");
                String education = scanner.nextLine();
                employeeManagement.addEmployee(new FresherEmployee(id, fullName, dob, phoneNumber, email, graduationDate, graduationRank, education));
                break;
            case "intern":
                System.out.print("Majors: ");
                String majors = scanner.nextLine();
                System.out.print("Semester: ");
                String semester = scanner.nextLine();
                System.out.print("University Name: ");
                String universityName = scanner.nextLine();
                employeeManagement.addEmployee(new InternEmployee(id, fullName, dob, phoneNumber, email, majors, semester, universityName));
                break;
            default:
                System.out.println("Invalid Employee Type!");
                break;
        }
        System.out.println("Employee added successfully!");
    }

    // Phương thức hiển thị danh sách nhân viên
    public static void showAllEmployees(EmployeeManagement employeeManagement) {
        System.out.println("All Employees:");
        employeeManagement.showAllEmployees();
    }

    public static void main(String[] args) {
        EmployeeManagement employeeManagement = new EmployeeManagement();

        // Gọi phương thức thêm mới nhân viên và hiển thị danh sách
        addNewEmployee(employeeManagement);
        showAllEmployees(employeeManagement);
    }
}
