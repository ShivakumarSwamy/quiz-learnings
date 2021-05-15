package org.example;

import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Program {

    private static final int MINIMUM_SALT_LENGTH = 20;
    private static final int LOWER_BOUND_PASSWORD_LENGTH = 12;
    private static final int UPPER_BOUND_PASSWORD_LENGTH = 100;
    private static final int LOWER_BOUND_ITERATION = 10000;
    private static final int UPPER_BOUND_ITERATION = 100000;
    private static final List<Integer> ALLOWED_KEYLENGTH = List.of(256, 512);

    private Program() {
        // intentionally left empty
    }

    public static String hash(String password, String salt, int iteration, int keylength) throws GeneralSecurityException {
        hashArgumentsValidation(password, salt, iteration, keylength);

        var secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        var pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), iteration, keylength);
        var secretKey = secretKeyFactory.generateSecret(pbeKeySpec);
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    private static void hashArgumentsValidation(String password, String salt, int iteration, int keylength) {
        handlePasswordValidation(password);
        handleSaltValidation(salt);
        handleIterationValidation(iteration);
        handleKeylengthValidation(keylength);
    }

    private static void handlePasswordValidation(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Empty Password");
        }
        if (password.length() < LOWER_BOUND_PASSWORD_LENGTH || password.length() > UPPER_BOUND_PASSWORD_LENGTH) {
            throw new IllegalArgumentException("Password length should be between "
                                                       + LOWER_BOUND_PASSWORD_LENGTH + " - "  + UPPER_BOUND_PASSWORD_LENGTH);
        }
    }

    private static void handleSaltValidation(String salt) {
        if (salt == null) {
            throw new IllegalArgumentException("Salt value is null");
        }

        if (salt.isEmpty() || salt.length() < MINIMUM_SALT_LENGTH) {
            throw new IllegalArgumentException("Salt length should be greater then or equal to " + MINIMUM_SALT_LENGTH);
        }
    }

    private static void handleIterationValidation(int iteration) {
        if (iteration < LOWER_BOUND_ITERATION || iteration > UPPER_BOUND_ITERATION) {
            throw new IllegalArgumentException("Iteration value should be between "
                                                       + LOWER_BOUND_ITERATION + " - "  + UPPER_BOUND_ITERATION);
        }
    }

    private static void handleKeylengthValidation(int keylength) {
        if (ALLOWED_KEYLENGTH.stream().noneMatch(requiredKeyLength -> requiredKeyLength == keylength)) {
            throw new IllegalArgumentException("keylength value should be 256 or 512");
        }
    }
}
