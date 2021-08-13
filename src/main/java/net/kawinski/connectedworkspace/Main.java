/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package net.kawinski.connectedworkspace;

import lombok.extern.slf4j.Slf4j;
import net.kawinski.connectedworkspace.application.client.Client;
import net.kawinski.connectedworkspace.application.server.Server;
import net.kawinski.connectedworkspace.application.client.ClientConfig;
import net.kawinski.connectedworkspace.application.Config;
import net.kawinski.connectedworkspace.application.server.ServerConfig;
import java.io.IOException;

@Slf4j
public class Main {

    // TODO: Clipboard https://stackoverflow.com/questions/7105778/get-readable-text-only-from-clipboard
    public static void main(String[] args) throws Exception {
        log.info("Hello world!");

        Config config = new Config();
        String appMode = config.getApplicationMode();
        switch(appMode) {
            case "cs":
                startBothClientAndServer(config);
                break;
            case "client":
                startAsClient(config);
                break;
            case "server":
                startAsServer(config);
                break;
            default:
                throw new IOException("Invalid application mode: '" + appMode + "'");
        }
    }

    private static void startBothClientAndServer(Config config) throws InterruptedException {
        new Thread(() -> {
            try {
                Thread.currentThread().setName("Server");
                startAsServer(config);
            } catch (Exception ex) {
                log.error("Critical server error", ex);
            }
        }).start();
        Thread.sleep(250);
        new Thread(() -> {
            try {
                Thread.currentThread().setName("Client");
                startAsClient(config);
            } catch (Exception ex) {
                log.error("Critical client error", ex);
            }
        }).start();
    }

    private static void startAsClient(Config config) throws Exception {
        ClientConfig clientConfig = new ClientConfig(config);
        new Client(clientConfig).run();
    }

    private static void startAsServer(Config config) throws Exception {
        ServerConfig serverConfig = new ServerConfig(config);
        Server server = new Server(serverConfig);

//        HookListener hookListener = new HookListener(server);
//        hookListener.registerHooks();

        server.run();
    }

}
