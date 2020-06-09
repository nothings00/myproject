package core.chapter14.list11;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/6/8 5:14 下午
 * @version 1.0
 */
public class ThreadPoolTest {
    // direcotry : " /Users/zenghuanhui/local/ylz "
    // keyword : " select "
    public static void main(String[] args) {
        try(Scanner in = new Scanner(System.in)){
            System.out.print("Enter base directory (e.g. /usr/local/jdk5.0/src): ");
            String directory = in.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String keyword = in.nextLine();

//            ExecutorService pool = Executors.newCachedThreadPool();
//            ExecutorService pool = Executors.newSingleThreadExecutor();
            ExecutorService pool = Executors.newFixedThreadPool(50);

            MatchCounter counter = new MatchCounter(new File(directory),keyword,pool);
            Future<Integer> result = pool.submit(counter);

            try {
                System.out.println(result.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            pool.shutdown();;

            int largestPoolSize = ((ThreadPoolExecutor)pool).getLargestPoolSize();
            System.out.println("largest pool size =" +largestPoolSize);
        }
    }
}

class MatchCounter implements Callable<Integer>{
    private File directory;
    private String keyword;
    private ExecutorService pool;
    private int count;

    public MatchCounter(File directory, String keyword, ExecutorService pool) {
        this.directory = directory;
        this.keyword = keyword;
        this.pool = pool;
    }

    @Override
    public Integer call() {
        count = 0;
        try {
            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();
            for (File file : files){
                if (file.isDirectory()){
                    MatchCounter counter = new MatchCounter(file,keyword,pool);
                    Future<Integer> result = pool.submit(counter);
                    results.add(result);
                } else {
                    if (search(file)){
                        count++;
                    }
                }
            }
            for (Future<Integer> result : results){
                try {
                    count += result.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean search(File file){
        try {
            try (Scanner in =  new Scanner(file,"UTF-8")){
                boolean found = false;
                while (!found && in.hasNextLine()){
                    String line = in.nextLine();
                    if (line.contains(keyword)){
                        found = true;
                    }
                }
                return found;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}





















