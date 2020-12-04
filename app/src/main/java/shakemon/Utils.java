package shakemon;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.util.Map;
import java.util.Properties;

public class Utils {
    public static String resourceAsString(String resourceName, Class<?> interestedClass) {
        try (var resource = interestedClass.getResourceAsStream(resourceName)) {
            return new String(resource.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    public static Map<String, String> propertiesAsMap(Reader reader) {
        var properties = new Properties();
        try {
            properties.load(reader);
            //noinspection unchecked,rawtypes
            return (Map) properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    public static Reader uriReader(String path) {
        try {
            var url = URI.create(path).toURL();
            return new InputStreamReader(url.openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
