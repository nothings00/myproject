package core2.security.auth;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.PrivilegedAction;

/**
 * 认证测试
 * @author zenghh
 * @date 2020/11/4 2:49 PM
 * @version 1.0
 */
public class AuthTest {
    public static void main(String[] args) {
        System.setProperty("java.security.policy","security/auth/AuthTest.policy");
        System.setProperty("java.security.auth.login.config","security/auth/jaas.config");
        System.setSecurityManager(new SecurityManager());
        try{
            LoginContext context = new LoginContext("Login1");
            context.login();
            System.out.println("Authentication successful");
            Subject subject = context.getSubject();
            System.out.println("subject = " + subject);
            PrivilegedAction<String> action  = new SysPropAction("user.home");
            String result = Subject.doAsPrivileged(subject,action,null);
            System.out.println(result);
            context.logout();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
