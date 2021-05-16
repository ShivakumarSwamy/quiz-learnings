package org.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.PasswordPolicyRegex.checkPassword;

import org.junit.jupiter.api.Test;

class PasswordPolicyRegexTest {

    @Test
    void shouldBeFalseWhenPasswordIsNull() {
        assertThat(checkPassword(null)).isFalse();
    }

    @Test
    void shouldAllowLowercaseCharacters() {
        assertThat(checkPassword("a55GGhh33")).isTrue();
    }

    @Test
    void shouldAllowUppercaseCharacters() {
        assertThat(checkPassword("Abb00cc99")).isTrue();
    }

    @Test
    void shouldAllowDigits() {
        assertThat(checkPassword("0AAccBB77")).isTrue();
    }

    @Test
    void shouldBeFalseWhenLengthIsLessThanEight() {
        assertThat(checkPassword("0")).isFalse();
    }

    @Test
    void shouldBeTrueWhenLengthIsGreaterThanSeven() {
        assertThat(checkPassword("0hF12121313")).isTrue();
    }

    @Test
    void shouldBeFalseWhenAtLeastOneDigitIsNotPresent() {
        assertThat(checkPassword("aaaaBBBB")).isFalse();
    }

    @Test
    void shouldBeFalseWhenAtLeastOneLowercaseIsNotPresent() {
        assertThat(checkPassword("00934876AAAA")).isFalse();
    }

    @Test
    void shouldBeFalseWhenAtLeastOneUppercaseIsNotPresent() {
        assertThat(checkPassword("aaaa888934")).isFalse();
    }

    @Test
    void shouldPassAllChecks() {
        assertThat(checkPassword("a0A123456789")).isTrue();
    }
}
