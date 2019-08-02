package design.pattern.behavioral.command;

/**
 * 电视机（接收者）
 * @author zenghh, 625111833@qq.com
 * @date 2019-08-02 17:16
 * @version 1.0.0
 */
public class Television {
    Integer id;


    public Television(Integer id) {
        this.id = id;
    }

    /**
     * 关闭
     */
    void off(){
        System.out.println("TV{"+id +"} close;");
    }

    /**
     * 打开
     */
    void open(){
        System.out.println("TV{"+id +"} open");
    }

    /**
     * 改变频道
     */
    void changeChannel(){
        System.out.println("TV{"+id +"} change channel");
    }
}
