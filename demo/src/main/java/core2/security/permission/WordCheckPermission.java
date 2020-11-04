package core2.security.permission;

import java.security.Permission;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 文字权限
 * @author zenghh
 * @date 2020/11/3 5:33 PM
 * @version 1.0
 */
public class WordCheckPermission extends Permission {
    private final String action;

    public WordCheckPermission(String name, String action) {
        //name 权限名称
        super(name);
        this.action = action;
    }

    @Override
    public boolean implies(Permission permission) {
        if (!(permission instanceof WordCheckPermission)) {
            return false;
        }
        WordCheckPermission b = (WordCheckPermission) permission;
        if ("insert".equals(action)){
            return "insert".equals(b.action) && getName().contains(b.getName());
        }else if ("avoid".equals(action)){
            if ("avoid".equals(b.action)) {
                return b.badWordSet().containsAll(badWordSet());
            } else if ("insert".equals(b.action)){
                for (String badWord :badWordSet()){
                    if (b.getName().contains(badWord)) {
                        return false;
                    }
                }
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        WordCheckPermission b = (WordCheckPermission) obj;
        if (!Objects.equals(action,b.action)) {
            return false;
        }
        if ("insert".equals(action)) {
            return Objects.equals(getName(),b.getName());
        } else if ("avoid".equals(action)) {
            return badWordSet().equals(b.badWordSet());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(),action);
    }

    @Override
    public String getActions() {
        return action;
    }

    public Set<String> badWordSet(){
        return new HashSet<>(Arrays.asList(getName().split(",")));
    }
}
