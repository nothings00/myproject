package core.chapter14.list1;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/6/2 3:38 下午
 * @version 1.0
 */
public class Ball {
    public static final int XSIZE = 15;
    public static final int YSIZE = 15;
    private double x = 0;
    private double y = 0;
    private double dx =1;
    private double dy =1;

    public void move(Rectangle2D bounds){
        x += dx;
        y += dy;
        if (x <bounds.getMinX()){
            x = bounds.getMaxX();
            dx = -dx;
        }
        if (x + XSIZE >= bounds.getMaxX()){
            x = bounds.getMaxX() - XSIZE;
            dx = -dx;
        }
        if (y < bounds.getMinY()){
            y = bounds.getMinY();
            dy = -dy;
        }
        if (y + YSIZE >= bounds.getMaxY()){
            y = bounds.getMaxY() - YSIZE;
            dy = -dy;
        }
    }

    public Ellipse2D getShape(){
        return new Ellipse2D.Double(x,y,XSIZE,YSIZE);
    }
}
