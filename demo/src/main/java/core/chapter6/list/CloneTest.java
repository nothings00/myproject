package core.chapter6.list;

import lombok.Data;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 克隆测试
 * @author zenghh, 625111833@qq.com
 * @date 2019-12-10 15:09
 * @version 1.0.0
 */
public class CloneTest {
    public static void main(String[] args) {
        Employee original =  new Employee("John",5000);
        original.setHireDay(2019,12,10);
        try {
            Employee copy = original.clone();
            copy.raiseSalary(10);
            copy.setHireDay(2018,10,1);
            System.out.println("original="+original);
            System.out.println("copy="+copy);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}

class Employee implements Cloneable{
    private String name;
    private double salary;
    private Date hireDay;

    public Employee(String name,double salary){
        this.name = name;
        this.salary = salary;
        this.hireDay = new Date();
    }

    @Override
    public Employee clone() throws CloneNotSupportedException {
        // call Object.clone()
        Employee cloned = (Employee)super.clone();
        cloned.hireDay = (Date)hireDay.clone();
        return cloned;
    }

    public void setHireDay(int year, int month, int day){
        Date newHireDate = new GregorianCalendar(year,month-1,day).getTime();
        hireDay.setTime(newHireDate.getTime());
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