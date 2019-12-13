package core.lambda;

import java.util.function.IntConsumer;

/**
 * lambda 测试
 * @author zenghh, 625111833@qq.com
 * @date 2019-12-11 9:50
 * @version 1.0.0
 */
public class LambdaTest {
    public static void main(String[] args) {
        //函数式接口
        Runnable action = () -> {
            System.out.println("repeat");
        };
//        repeat(10,action);

        IntConsumer countdown = (i)->{
            System.out.println("Countdown:"+i);
        };
        countdown(10,countdown);
    }

    public static void repeat(int n,Runnable action){
        for (int i = n; i > 0; i--) {
            action.run();
        }
    }

    public static void countdown(int n, IntConsumer action)  {
        for (int i = n; i > 0 ; i--) {
            action.accept(i);
        }
    }
}


