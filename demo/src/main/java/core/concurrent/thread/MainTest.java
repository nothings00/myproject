package core.concurrent.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/6/12 10:09 上午
 * @version 1.0
 */
public class MainTest {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(50);
        for (int i = 0; i < 1000; i++) {
//            ThreadFactory threadFactory = Executors.defaultThreadFactory();
            // avoid OOM
//            ExecutorService executorService = new ThreadPoolExecutor(50,100,1, TimeUnit.MINUTES,new LinkedBlockingQueue<>(),threadFactory);
            ExecutorService executorService = Executors.newCachedThreadPool();
//            ExecutorService executorService = Executors.newFixedThreadPool(50);
            int finalI = i;
            Runnable run = () ->{
                countDownLatch.countDown();
                if (countDownLatch.getCount() != 0){

                }
                Map<String,Object> user = new HashMap<>(3);
                user.put("userName","test"+ finalI);
                user.put("password","123456");
                user.put("address","ylz");
                ThreadHolder threadHolder = new ThreadHolder(user);
            };
            executorService.submit(run);
            executorService.shutdown();
        }
    }
}
