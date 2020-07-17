package core2.io;

import com.znothings.test.object.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;
import java.time.LocalDate;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/7/17 3:14 PM
 * @version 1.0
 */
public class SerialCloneTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Employee harry = new Employee("Harry Hacker",35000,1989,10,1);
        Employee harry2 = harry.clone();

        harry.raiseSalary(10);

        System.out.println(harry);
        System.out.println(harry2);

        Test test = new Test("s",1,LocalDate.now());
        Test test2 = (Test) test.clone();
        test.raiseD(10);

        System.out.println(test);
        System.out.println(test2);
    }
}

class SerialCloneable implements Cloneable, Serializable{
    @Override
    protected Object clone() throws CloneNotSupportedException {
        try{
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try (ObjectOutputStream out = new ObjectOutputStream(bos)){
                out.writeObject(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (InputStream bin = new ByteArrayInputStream(bos.toByteArray())){
                ObjectInputStream is= new ObjectInputStream(bin);
                return is.readObject();
            }
        }catch (IOException | ClassNotFoundException e){
            CloneNotSupportedException e2 = new CloneNotSupportedException();
            e2.initCause(e);
            throw e2;
        }
    }
}
@Data
@AllArgsConstructor
class Test extends SerialCloneable{
    private String s;
    private double d;
    private LocalDate ld;

    public void raiseD(double byPercent){
        d += d * byPercent/100;
    }
}
