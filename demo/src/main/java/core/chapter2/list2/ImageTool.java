package core.chapter2.list2;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * image tool
 * @author zenghh, 625111833@qq.com
 * @date 2019-07-10 9:46
 * @version 1.0.0
 */
@Slf4j
public class ImageTool {
    /**
     * get the image width
     * @param file
     * @return
     */
    public static int getImageWidth(File file){
        InputStream inputStream=null;
        BufferedImage bufferedImage = null;
        int ret = -1 ;
        try {
            inputStream = new FileInputStream(file);
            bufferedImage = ImageIO.read(inputStream);
            ret = bufferedImage.getWidth();
        } catch (FileNotFoundException e) {
            log.error("File Not Found:"+file.getAbsolutePath());
            e.printStackTrace();
        } catch (IOException e) {
            log.error("File Open Failed:"+file.getAbsolutePath());
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * get the image height
     * @param file
     * @return
     */
    public static int getImageHeight(File file){
        InputStream inputStream=null;
        BufferedImage bufferedImage = null;
        int ret = -1 ;
        try {
            inputStream = new FileInputStream(file);
            bufferedImage = ImageIO.read(inputStream);
            ret = bufferedImage.getHeight();
        } catch (FileNotFoundException e) {
            log.error("File Not Found:"+file.getAbsolutePath());
            e.printStackTrace();
        } catch (IOException e) {
            log.error("File Open Failed:"+file.getAbsolutePath());
            e.printStackTrace();
        }
        return ret;
    }
}
