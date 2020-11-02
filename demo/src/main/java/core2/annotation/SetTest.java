package core2.annotation;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zenghh
 * @date 2020/11/2 5:48 PM
 * @version 1.0
 */
public class SetTest {
    public static void main(String[] args) {
        Logger.getLogger("core2.annotation").setLevel(Level.FINEST);
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.FINEST);
        Logger.getLogger("core2.annotation").addHandler(handler);

        Set<Item> parts = new HashSet<>();
        parts.add(new Item("Toaster",1279));
        parts.add(new Item("Microwave",4104));
        parts.add(new Item("Toaster",1279));
        System.out.println(parts);
    }
}
