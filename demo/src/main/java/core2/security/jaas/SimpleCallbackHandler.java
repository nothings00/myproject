package core2.security.jaas;

import lombok.Data;
import lombok.NonNull;

import javax.security.auth.callback.*;
import java.io.IOException;

/**
 * This sample callback handler presents the given user name and password.
 *
 * @author zenghh
 * @version 1.0
 * @date 2020/11/25 10:34 AM
 */
@Data
public class SimpleCallbackHandler implements CallbackHandler {
    @NonNull
    private String username;
    @NonNull
    private char[] password;

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback){
                ((NameCallback) callback).setName(username);
            }else if (callback instanceof PasswordCallback){
                ((PasswordCallback) callback).setPassword(password);
            }
        }
    }
}
