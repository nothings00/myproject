package core2.io;


import lombok.Data;
import sun.util.resources.LocaleData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/7/13 5:38 PM
 * @version 1.0
 */
public class TextFileTest {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Carl Cracker",75000,1987,12,15);
        staff[1] = new Employee("Harry Hacker",50000,1989,10,1);
        staff[2] = new Employee("Tony Tester",40000,1990,3,15);

        try (PrintWriter out = new PrintWriter("employee.dat", String.valueOf(StandardCharsets.UTF_8))){
            writeData(staff,out);
        }

        try (Scanner in = new Scanner(new FileInputStream("employee.dat"), String.valueOf(StandardCharsets.UTF_8))){
            Employee[] newStaff = readData(in);

            for (Employee e : newStaff){
                System.out.println(e);
            }
        }
    }

    /**
     * Writes all employees in an array to a print writer
     * @param employees
     * @param out
     */
    private static void writeData(Employee[] employees, PrintWriter out){
        out.println(employees.length);
        for (Employee e : employees){
            writeEmployee(out,e);
        }
    }


    private static Employee[] readData(Scanner in){
        int n = in.nextInt();
        in.nextLine();
        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++) {
            employees[i] = readEmployee(in);
        }
        return employees;
    }

    /**
     * Writes employee data to a print writer
     * @param out
     * @param e
     */
    public static void writeEmployee(PrintWriter out,Employee e){
        out.println(e.getName() + "|" + e.getSalary() + "|" + e.getHireDay());
    }


    /**
     * Reads employee data from a buffered reader
     * @param in the scanner
     * @return
     */
    public static Employee readEmployee(Scanner in){
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        String name = tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        LocalDate hireDate = LocalDate.parse(tokens[2]);
        int year = hireDate.getYear();
        int month = hireDate.getMonthValue();
        int day = hireDate.getDayOfMonth();
        return new Employee(name,salary,year,month,day);

    }
}

@Data
class Employee implements Cloneable{
    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee(String name,double salary){
        this.name = name;
        this.salary = salary;
        this.hireDay = LocalDate.now();
    }

    public Employee(String name,double salary,int year,int month,int day){
        this.name = name;
        this.salary = salary;
        this.hireDay = LocalDate.of(year,month,day);
    }

    @Override
    public Employee clone() throws CloneNotSupportedException {
        // call Object.clone()
        Employee cloned = (Employee)super.clone();
        cloned.hireDay = hireDay;
        return cloned;
    }

    public void setHireDay(int year, int month, int day){
        hireDay  = LocalDate.of(year,month,day);
    }

    public void raiseSalary(double byPercent){
        double raise = this.salary * byPercent /100;
        salary +=raise;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}
