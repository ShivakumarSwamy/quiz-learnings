package org.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.PasswordStrength.passwordStrength;

import org.junit.jupiter.api.Test;

class PasswordStrengthTest {

    @Test
    void shouldThrowIllegalArgumentExceptionWhenPasswordIsNull() {
        assertThatThrownBy(() -> passwordStrength(null))
                  .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shouldBeFalseWhenScoreLessThan2() {
        assertThat(passwordStrength("")).isFalse();
    }

    @Test
    void shouldBeTrueWhenScoreGreaterThan2() {
        assertThat(passwordStrength("a!24Had735")).isTrue();
    }
}
