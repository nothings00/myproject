package core2.security.jaas;

import lombok.Data;
import lombok.NonNull;

import javax.security.auth.Subject;
import java.security.Principal;

/**
 * 权限特征
 * A principal with named value (such as "role=HR" or "username=harry")
 * @author zenghh
 * @date 2020/11/25 10:18 AM
 * @version 1.0
 */
@Data
public class SimplePrincipal implements Principal {
    @NonNull
    private String descr;
    @NonNull
    private String value;

    @Override
    public String getName() {
        return descr + "=" + value;
    }
}
