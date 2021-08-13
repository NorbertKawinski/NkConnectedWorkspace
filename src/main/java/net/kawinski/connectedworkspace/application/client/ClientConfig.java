package net.kawinski.connectedworkspace.application.client;

import lombok.Getter;
import net.kawinski.connectedworkspace.application.Config;

import java.io.IOException;

public class ClientConfig {

    private final Config config;

    @Getter
    private final String serverHost;

    @Getter
    private final int serverPort;

    public ClientConfig(Config config) throws IOException {
        this.config = config;
        this.serverHost = config.getProperty("serverHost");
        this.serverPort = config.getPropertyInt("serverPort");
    }
}
