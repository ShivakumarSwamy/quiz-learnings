package org.example;

import com.nulabinc.zxcvbn.Zxcvbn;

public class PasswordStrength {

    private PasswordStrength() {
        // intentionally left empty
    }

    public static boolean passwordStrength(String password) {
        return new Zxcvbn()
                    .measure(password)
                    .getScore() >= 2;
    }
}
