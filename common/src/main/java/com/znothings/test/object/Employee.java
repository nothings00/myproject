package com.znothings.test.object;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Employee implements Cloneable, Serializable {
    public static final int NAME_SIZE = 40;
    public static final double SALARY_SIZE = 1;
    public static final int HIRE_DAY_SIZE = 3;
    public static final int RECORD_SIZE = 100;

    protected String name;
    protected double salary;
    protected LocalDate hireDay;

    public Employee(){}

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
