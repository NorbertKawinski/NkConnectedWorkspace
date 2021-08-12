package net.kawinski.connectedworkspace.configuration;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.kawinski.logging.NkTrace;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;

@Slf4j
public class Config {
    private final static Path[] DEFAULT_CONFIG_FILE_LOCATIONS = new Path[] {
            Paths.get("NkConnectedWorkspace.properties"),
            Paths.get(System.getProperty("user.home"), "NkConnectedWorkspace.properties")
    };

    private final Properties properties;

    @Getter
    private final String applicationMode;

    public Config() throws IOException {
        try(NkTrace trace = NkTrace.info(log)) {
            Path configLocation = findConfigLocation();

            log.info("Loading configuration from file " + configLocation);
            try(InputStream is = new FileInputStream(configLocation.toFile())) {
                properties = new Properties();
                properties.load(is);

                applicationMode = getProperty("applicationMode");
            }
        }
    }

    public static Path findConfigLocation() throws IOException {
        for(Path configLocation : DEFAULT_CONFIG_FILE_LOCATIONS) {
            if(Files.exists(configLocation)) {
                return configLocation;
            }
        }
        throw new IOException("No config found. Searched locations: " + Arrays.asList(DEFAULT_CONFIG_FILE_LOCATIONS));
    }

    public String getProperty(String key) throws IOException {
        return Optional.ofNullable(properties.getProperty(key))
                .orElseThrow(() -> new IOException("Property '" + key + "' not found in configuration"));
    }

    public int getPropertyInt(String key) throws IOException {
        return Integer.parseInt(getProperty(key));
    }
}
