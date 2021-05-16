package org.example;

import java.util.regex.Pattern;

public class PasswordPolicyRegex {

    private PasswordPolicyRegex() {
        // intentionally left empty
    }

    public static boolean checkPassword(String password) {
        if (password == null) {
            return false;
        }
        return Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,}$")
                      .matcher(password)
                      .matches();
    }
}
