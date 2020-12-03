package shakemon;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class Utils {
    public static String resourceAsString(String resourceName, Class<?> interestedClass) {
        try {
            var resource = resource(resourceName, interestedClass);
            return new String(Files.readAllBytes(resource));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Path resource(String resourceName, Class<?> interestedClass) {
        var resource = interestedClass.getResource(resourceName);
        Objects.requireNonNull(resource, () -> "resource " + resourceName + "not found");
        try {
            URI uri = resource.toURI();
            return Paths.get(uri);
        } catch (URISyntaxException e) {
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
