package core2.international.retire;

import java.awt.*;
import java.util.ListResourceBundle;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/9/22 5:04 PM
 * @version 1.0
 */
public class RetireResources extends ListResourceBundle {
    private static final Object[][] contents = {
            {"colorPre", Color.BLUE},{"colorGain",Color.white},{"colorLoss",Color.red}
    };

    @Override
    public  Object[][] getContents() {
        return contents;
    }
}
