package core2.io.file;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.CRC32;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/7/23 4:07 PM
 * @version 1.0
 */
public class MemoryMapTest {

    public static void main(String[] args) throws IOException {
        String filepath = "demo/target/demo-1.0-SNAPSHOT.jar";
//        List<Path> walk = Files.walk(Paths.get("")).collect(Collectors.toList());
//        System.out.println();
        System.out.println(Files.size(Paths.get(filepath)));
        System.out.println("Input Stream");
        long start = System.currentTimeMillis();
        Path filename = Paths.get(filepath);
        long crcValue = checksumInputStream(filename);
        long end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println( (end - start) + " milliseconds");

        System.out.println("Buffered Input Stream");
        start = System.currentTimeMillis();
        crcValue = checksumBufferedInputStream(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println( (end - start) + " milliseconds");

        System.out.println("Random Access File");
        start = System.currentTimeMillis();
        crcValue = checksumRandomAccessFile(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println( (end - start) + " milliseconds");

        System.out.println("Mapped File");
        start = System.currentTimeMillis();
        crcValue = checksumMappedFile(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println( (end - start) + " milliseconds");


    }

    public static long checksumInputStream(Path filename) throws IOException {
        try(InputStream in = Files.newInputStream(filename)){
            return checkByCrc32(in);
        }
    }

    public static long checksumBufferedInputStream(Path filename) throws IOException {
        try(InputStream in = new BufferedInputStream(Files.newInputStream(filename))){
            return checkByCrc32(in);
        }
    }

    public static long checksumMappedFile(Path filename) throws IOException {
        try (FileChannel channel = FileChannel.open(filename)){
            CRC32 crc32 = new CRC32();
            int length = (int) channel.size();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY,0,length);

            for (int i = 0; i < length; i++) {
                int c = buffer.get(i);
                crc32.update(c);
            }
            return crc32.getValue();
        }
    }

    public static long checksumRandomAccessFile(Path filename) throws IOException {
        try(RandomAccessFile file = new RandomAccessFile(filename.toFile(),"r")){
            long length = file.length();
            CRC32 crc32 = new CRC32();

            for (int i = 0; i < length; i++) {
                file.seek(i);
                int c = file.read();
                crc32.update(c);
            }
            return crc32.getValue();
        }
    }

    private static long checkByCrc32(InputStream in) throws IOException {
        CRC32 crc = new CRC32();
        int c;
        while ((c = in.read()) != -1) {
            crc.update(c);
        }
        return crc.getValue();
    }
}
