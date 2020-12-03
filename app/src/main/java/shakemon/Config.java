package shakemon;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

class Config {
    private final Map<String, String> properties;

    private Config(Map<String, String> properties) {
        this.properties = properties;
    }

    int port() {
        return Integer.parseInt(properties.get("shakemon.port"));
    }

    int adminPort() {
        var adminPort = properties.get("shakemon.admin_port");
        return Integer.parseInt(adminPort);
    }

    static Config load() {
        try {
            var properties = new Properties();
            var stream = Main.class.getResourceAsStream("config.properties");
            properties.load(stream);
            //noinspection unchecked,rawtypes
            return new Config((Map) properties);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
