package net.kawinski.connectedworkspace.application.server;

import lombok.Getter;
import net.kawinski.connectedworkspace.application.Config;

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
