package net.kawinski.connectedworkspace.nettyexamples;

import io.netty.buffer.ByteBuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * Handles a server-side channel.
 */
@Slf4j
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msgObj) { // (2)
        ByteBuf in = (ByteBuf) msgObj;
        try {
//            while (in.isReadable()) { // (1)
//                System.out.print((char) in.readByte());
//                System.out.flush();
//            }
            String msg = in.toString(StandardCharsets.UTF_8);
            log.info("Received msg: " + msg);
            System.out.flush();

            ctx.write(msg); // (1)
            ctx.flush(); // (2)
        } finally {
            ReferenceCountUtil.release(msgObj); // (2)
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}