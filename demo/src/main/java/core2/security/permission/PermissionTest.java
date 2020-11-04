package core2.security.permission;

import java.security.Policy;

/**
 * 权限测试
 * @author zenghh
 * @date 2020/11/3 5:47 PM
 * @version 1.0
 */
public class PermissionTest {
    public static void main(String[] args) {
        String text = "";
        String policy = " grant { permission core2.security.permission.WordCheckPermission \"sex,drugs,C++\", \"avoid\"}";
        //1.加载安全策略，开启安全管理器
//        System.setProperty("java.security.policy","permissions/PermissionTest.policy");
        System.setProperty("java.security.policy",policy);
        System.setSecurityManager(new SecurityManager());
        //2.权限校验
        WordCheckPermission p = new WordCheckPermission(text,"insert");
        SecurityManager manager = System.getSecurityManager();
        if (manager!=null){
            manager.checkPermission(p);
        }
        System.out.println("success");
    }
}
