package net.kawinski.connectedworkspace.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Utils {

    public static String read(SocketChannel channel, ByteBuffer buffer) throws IOException {
        buffer.clear();
        channel.read(buffer);
        return StandardCharsets.UTF_8.decode(buffer).toString();
    }

    public static void write(SocketChannel channel, ByteBuffer buffer, String message) throws IOException {
        buffer.clear();
        buffer.put(message.getBytes(StandardCharsets.UTF_8));
        channel.write(buffer);
    }

}
