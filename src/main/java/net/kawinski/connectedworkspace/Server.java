/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package net.kawinski.connectedworkspace;

import lombok.extern.slf4j.Slf4j;
import net.kawinski.connectedworkspace.configuration.ServerConfig;
import net.kawinski.connectedworkspace.utils.Utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

@Slf4j
public class Server {

    private final ServerConfig config;

    public Server(ServerConfig config) {
        this.config = config;
    }

// TODO: Clipboard https://stackoverflow.com/questions/7105778/get-readable-text-only-from-clipboard
// TODO: Quick test for laptop: Send "right mouse click" every one second

    private static final String POISON_PILL = "POISON_PILL";

    public void run() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(/*"localhost", */config.getServerPort()));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer buffer = ByteBuffer.allocate(256);

        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();

                if (key.isAcceptable()) {
                    register(selector, serverSocket);
                }

                if (key.isReadable()) {
                    onMessageReady(buffer, key);
                }
                iter.remove();
            }
        }
    }

    private static void register(Selector selector, ServerSocketChannel serverSocket) throws IOException {
        log.info("Accepting new client");
        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }

    private static void onMessageReady(ByteBuffer buffer, SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        String message = Utils.read(client, buffer);
        log.info("Received message from client: " + message);

        if (message.equals(POISON_PILL)) {
            client.close();
            log.info("Closing client connection");
        } else {
            Utils.write(client, buffer, new Date().toString());
        }
    }

    public void sendMessage() {
        
    }



}