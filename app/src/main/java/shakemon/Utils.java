package shakemon;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Map;
import java.util.Properties;

import static com.google.common.base.Preconditions.checkArgument;

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
    public static Reader fileReader(String path) {
        try {
            var configFile = new File(path);
            checkArgument(configFile.exists(), "%s does not exists", path);
            checkArgument(configFile.isFile(), "%s not a regular file", path);
            checkArgument(configFile.canRead(), "%s not readable", path);
            return new InputStreamReader(new FileInputStream(configFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
