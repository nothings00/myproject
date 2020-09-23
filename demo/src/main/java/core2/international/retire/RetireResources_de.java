package core2.international.retire;

import java.awt.*;
import java.util.ListResourceBundle;

/**
 *
 * @author zenghh
 * @email 625111833@qq.com
 * @date 2020/9/22 5:07 PM
 * @version 1.0
 */
public class RetireResources_de extends ListResourceBundle {
    private static final Object[][] contents = {
            {"colorPre", Color.yellow},{"colorGain",Color.black},{"colorLoss",Color.red}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
