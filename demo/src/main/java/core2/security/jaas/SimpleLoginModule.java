package core2.security.jaas;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * This login module authenticates users by reading usernames,passwords,and roles from a text file
 * @author zenghh
 * @date 2020/11/25 10:21 AM
 * @version 1.0
 */
public class SimpleLoginModule implements LoginModule {
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map<String,?> options;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.options = options;
    }

    @Override
    public boolean login() throws LoginException {
        if (callbackHandler == null) {
            throw new LoginException("no handler");
        }

        NameCallback nameCallback = new NameCallback("username");
        PasswordCallback passwordCallback = new PasswordCallback("password", false);
        try {
            callbackHandler.handle(new Callback[]{nameCallback,passwordCallback});
        } catch (UnsupportedCallbackException e) {
            LoginException e2 = new LoginException("Unsupported callback");
            e2.initCause(e);
            throw e2;
        } catch (IOException e) {
            LoginException e2 = new LoginException("I/O Exception in callback");
            e2.initCause(e);
            throw  e2;
        }


        return false;
    }

    @Override
    public boolean commit() throws LoginException {
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return true;
    }

    @Override
    public boolean logout() throws LoginException {
        return true;
    }

    private boolean checkLogin(String username,char[] password) throws IOException {
        try (Scanner in = new Scanner(Paths.get(""+options.get("pwfile")),"UTF-8")){
            while (in.hasNextLine()){
                String[] inputs = in.nextLine().split("\\|");
                if (inputs[0].equals(username) && Arrays.equals(inputs[1].toCharArray(),password)){
                    String role = inputs[2];
                    Set<Principal> principals = subject.getPrincipals();
                    principals.add(new SimplePrincipal("username",username));
                    principals.add(new SimplePrincipal("role",role));
                    return true;
                }
            }
            return false;
        }
    }
}
