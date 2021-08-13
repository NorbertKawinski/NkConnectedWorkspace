package net.kawinski.connectedworkspace.application.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import net.kawinski.connectedworkspace.protocol.Message;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private final MessageExecutor messageExecutor;

    public ClientHandler(MessageExecutor messageExecutor) {
        this.messageExecutor = messageExecutor;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("Lost connection to the server");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msgObj) {
        Message msg = (Message) msgObj;
        log.info(msg.toString());
        messageExecutor.execute(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}