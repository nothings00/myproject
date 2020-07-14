package core2;

import lombok.Data;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/7/14 5:41 PM
 * @version 1.0
 */
@Data
public class Manager extends Employee{
    private Employee secretary;
    private double bounds;

    public Manager(){}

    public Manager(String name,double salary,int year,int month,int day){
        super(name,salary,year,month,day);
    }
}
