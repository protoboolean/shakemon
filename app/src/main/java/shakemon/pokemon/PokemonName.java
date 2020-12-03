package shakemon.pokemon;

import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class PokemonName {
    private static final Pattern VALID_PATTERN = Pattern.compile("\\s*[a-z]+\\s*", CASE_INSENSITIVE);

    private final String name;

    public PokemonName(String name) {
        checkArgument(!isBlank(name), "blank");
        checkArgument(VALID_PATTERN.matcher(name).matches(), "not ascii alphabetic");
        this.name = name.trim();
    }

    public String asString() {
        return name;
    }
}
