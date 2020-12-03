package shakemon.translation;

import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ShakespereanTest {
    @Test
    void returns_own_trimmed_value() {
        var shakesperean = new Shakesperean(" some translation ");
        assertThat(shakesperean.asString()).isEqualTo("some translation");
    }

    @Test
    void rejects_invalid_value() {
        assertThatIllegalArgumentException().describedAs("null argument")
                .isThrownBy(() -> new Shakesperean(null));
        assertThatIllegalArgumentException().describedAs("empty argument")
                .isThrownBy(() -> new Shakesperean(EMPTY));
        assertThatIllegalArgumentException().describedAs("blank argument")
                .isThrownBy(() -> new Shakesperean(SPACE));
    }
}
