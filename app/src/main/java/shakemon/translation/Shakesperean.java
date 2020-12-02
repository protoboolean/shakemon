package shakemon.translation;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class Shakesperean {
    private final String translation;

    public Shakesperean(String translation) {
        checkArgument(!isBlank(translation), "blank");
        this.translation = translation.trim();
    }

    public String asString() {
        return translation;
    }
}
