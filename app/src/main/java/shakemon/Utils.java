package shakemon;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

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
}
