package core.chapter14.list9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/6/4 10:36 上午
 * @version 1.0
 */
public class BlockingQueueTest {
    public static final int FILE_QUEUE_SIZE = 10;
    public static final int SEARCH_THREADS = 100;
    public static final File DUMMY = new File("");
    public static long start ;
    public static BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args) {
        try ( Scanner in = new Scanner(System.in) ){
            System.out.print("Enter base directory (e.g. /opt/jdk1.8.0/src): ");
            String directory = in.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String keyword = in.nextLine();
            start = System.currentTimeMillis();
            Runnable enumerator = () -> {
                try {
                    enumerate(new File(directory));
                    queue.put(DUMMY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            System.out.println("scanner file take:" + (System.currentTimeMillis()-start) + "ms");

            new Thread(enumerator).start();
            for (int i = 0; i < SEARCH_THREADS; i++) {
                Runnable searcher = () -> {
                    boolean done = false;
                    while (!done){
                        File file = null;
                        try {
                            file = queue.take();
                            if (file == DUMMY){
                                queue.put(file);
                                done = true;
                            } else {
                                search(file,keyword);
                            }
                        } catch (InterruptedException | FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                };
                new Thread(searcher).start();
            }
        }
    }

    public static void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();
        for (File file : files){
            if (file.isDirectory()){
                enumerate(file);
            } else {
              queue.put(file);
            }
        }
    }

    public static void search(File file,String keyword) throws FileNotFoundException {
        try (Scanner in = new Scanner(file,"UTF-8")){
            int lineNumber = 0 ;
            while (in.hasNextLine()){
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyword)){
                    long end = System.currentTimeMillis();
                    System.out.printf("%s:%d:%s,take:%dms%n",file.getPath(),lineNumber,line,end-start);

                }
            }
        }
    }
}
