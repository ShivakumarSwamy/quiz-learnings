package org.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.Program.hash;

import java.security.GeneralSecurityException;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ProgramTest {

    private static final String DEFAULT_PASSWORD = "defaultPassword";
    private static final String DEFAULT_SALT = "minimumSaltLengthIs20";
    private static final int DEFAULT_ITERATION = 10000;
    private static final int DEFAULT_KEYLENGTH = 256;

    @Nested
    class PasswordValidation {

        @Test
        void shouldThrowIllegalArgumentExceptionWhenNull() {
            assertThatThrownBy(() -> hashWithPassword(null))
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Empty Password");
        }

        @Test
        void shouldThrowIllegalArgumentExceptionWhenEmpty() {
            assertThatThrownBy(() -> hashWithPassword(""))
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Empty Password");
        }

        @Test
        void shouldThrowIllegalArgumentExceptionWhenLengthIsLessThan12() {
            assertThatThrownBy(() -> hashWithPassword("password"))
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Password length should be between 12 - 100");
        }

        @Test
        void shouldThrowIllegalArgumentExceptionWhenLengthIsGreaterThan100() {
            assertThatThrownBy(() -> hashWithPassword("123456789123456789123456789123456789123456789123456789123456789"
                                                    + "123456789123456789123456789123456789123456789123456789123456789"))
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Password length should be between 12 - 100");
        }
    }

    @Nested
    class SaltValidation {

        private static final String SALT_LENGTH_EXCEPTION_MESSAGE = "Salt length should be greater then or equal to 20";

        @Test
        void shouldThrowIllegalArgumentExceptionWhenNull() {
            assertThatThrownBy(() -> hashWithSalt(null))
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Salt value is null");
        }

        @Test
        void shouldThrowIllegalArgumentExceptionWhenEmpty() {
            assertThatThrownBy(() -> hashWithSalt(""))
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessage(SALT_LENGTH_EXCEPTION_MESSAGE);
        }

        @Test
        void shouldThrowIllegalArgumentExceptionWhenLengthIsLessThan20() {
            assertThatThrownBy(() -> hashWithSalt("salt-length-14"))
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessage(SALT_LENGTH_EXCEPTION_MESSAGE);
        }
    }

    @Nested
    class IterationValidation {

        private static final String ITERATION_VALUE_EXCEPTION_MESSAGE = "Iteration value should be between 10000 - 100000";

        @Test
        void shouldThrowIllegalArgumentExceptionWhenLessThan10000() {
            assertThatThrownBy(() -> hashWithIteration(999))
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ITERATION_VALUE_EXCEPTION_MESSAGE);
        }

        @Test
        void shouldThrowIllegalArgumentExceptionWhenGreaterThan100000() {
            assertThatThrownBy(() -> hashWithIteration(999999))
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ITERATION_VALUE_EXCEPTION_MESSAGE);
        }
    }

    @Nested
    class KeylengthValidation {

        @Test
        void shouldThrowIllegalArgumentExceptionWhenKeyLengthIsNot256or512() {
            assertThatThrownBy(() -> hashWithKeyLength(12873))
                    .isExactlyInstanceOf(IllegalArgumentException.class)
                    .hasMessage("keylength value should be 256 or 512");
        }
    }

    @Test
    void shouldGenerateHash() throws GeneralSecurityException {
        assertThat(hash("passwordPassword", DEFAULT_SALT, 15000, 512))
                .isEqualTo("Qvjm9jpDtnyzp7ceq3/XQrdgQjzATzPh23cUZuIZbqCvxZ8MNqnDNS+Cwlhzyu3WCSt1UPDXESvhhAHboZiaPg==");
    }

    private static void hashWithPassword(String password) throws GeneralSecurityException {
        hash(password, DEFAULT_SALT, DEFAULT_ITERATION, DEFAULT_KEYLENGTH);
    }

    private static void hashWithSalt(String salt) throws GeneralSecurityException {
        hash(DEFAULT_PASSWORD, salt, DEFAULT_ITERATION, DEFAULT_KEYLENGTH);
    }

    private static void hashWithIteration(int iteration) throws GeneralSecurityException {
        hash(DEFAULT_PASSWORD, DEFAULT_SALT, iteration, DEFAULT_KEYLENGTH);
    }

    private static void hashWithKeyLength(int keyLength) throws GeneralSecurityException {
        hash(DEFAULT_PASSWORD, DEFAULT_SALT, DEFAULT_ITERATION, keyLength);
    }
}
