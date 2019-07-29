package design.pattern.creational.singleton;

/**
 * 单例
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-29 14:05
 * @version 1.0.0
 */
public class Singleton {
    private  static Singleton instance = null;
    private Singleton(){

    }

    public static Singleton getInstance(){
        if (instance == null){
            instance =  new Singleton();
        }
        return instance;
    }
}
