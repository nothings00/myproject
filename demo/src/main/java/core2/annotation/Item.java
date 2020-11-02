package core2.annotation;

import lombok.Data;

import java.util.Objects;

/**
 *
 * @author zenghh
 * @date 2020/11/2 4:48 PM
 * @version 1.0
 */
@Data
public class Item {
    private String description;
    private int partNumber;

    public Item(String description, int partNumber) {
        this.description = description;
        this.partNumber = partNumber;
    }

    @Override
    public boolean equals(Object ob){
        if (this == ob) return true;
        if (ob == null) return false;
        if (getClass()!= ob.getClass()) return false;
        Item other = (Item) ob;
        return Objects.equals(description,other.description) && partNumber == other.partNumber;
    }

    @Override

    public int hashCode(){
        return Objects.hash(description,partNumber);
    }
}
