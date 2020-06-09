package core.chapter14.list10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/6/5 5:32 下午
 * @version 1.0
 */
public class FutureTest {
    public static void main(String[] args) {
        // direcotry : " /Users/zenghuanhui/local/ylz "
        // keyword : " select "
        try (Scanner in = new Scanner(System.in)){
            System.out.print("Enter base directory (e.g. /usr/local/jdk5.0/src): ");
            String directory = in.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String keyword = in.nextLine();

            MatchCounter matchCounter = new MatchCounter(new File(directory),keyword);
            FutureTask<Integer> task = new FutureTask<>(matchCounter);
            Thread t = new Thread(task);
            t.start();
            try {
                System.out.println(task.get() + " matching files");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
}

class MatchCounter implements Callable<Integer>{
    private File directory;
    private String keyword;

    public MatchCounter(File directory, String keyword) {
        this.directory = directory;
        this.keyword = keyword;
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;
        try {
            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();
            for (File file : files){
                if (file.isDirectory()){
                    MatchCounter counter = new MatchCounter(file,keyword);
                    FutureTask<Integer> task = new FutureTask<>(counter);
                    results.add(task);
                    Thread t = new Thread(task);
                    t.start();
                }else {
                    if (search(file)){
                        count++;
                    }
                }
            }
            for (Future<Integer> result : results){
                try {
                    count +=result.get();
                }catch (ExecutionException e){
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean search(File file){
        try {
            try (Scanner in = new Scanner(file,"UTF-8")){
                boolean found = false;
                while (!found && in.hasNextLine()){
                    String line = in.nextLine();;
                    if (line.contains(keyword)){
                        found = true;
                    }
                }
                return found;
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
