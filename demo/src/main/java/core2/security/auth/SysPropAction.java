package core2.security.auth;

import java.security.PrivilegedAction;

/**
 * 系统属性查看
 * @author zenghh
 * @date 2020/11/4 2:52 PM
 * @version 1.0
 */
public class SysPropAction implements PrivilegedAction<String> {
    private String propertyName;

    public SysPropAction(String propertyName) {
        this.propertyName = propertyName;
    }

    @Override
    public String run() {
        return System.getProperty(propertyName);
    }
}
