package net.kawinski.connectedworkspace.configuration;

import lombok.Getter;

import java.io.IOException;

public class ServerConfig {

    private final Config config;

//    @Getter
//    private final String bindAddress;

    @Getter
    private final int serverPort;

    public ServerConfig(Config config) throws IOException {
        this.config = config;
//        this.bindAddress = config.getProperty("bindAddress");
        this.serverPort = config.getPropertyInt("serverPort");
    }

}
