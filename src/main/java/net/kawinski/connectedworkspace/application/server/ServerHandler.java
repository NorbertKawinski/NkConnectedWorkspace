package net.kawinski.connectedworkspace.application.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
//        ChannelFuture f1 = ctx.writeAndFlush(new MouseMove(15, 45));
//        ChannelFuture f2 = ctx.writeAndFlush(new MouseMove(13, 105));
//        ChannelFuture f3 = ctx.writeAndFlush(new MouseMove(11, 34));
//        ChannelFuture f4 = ctx.writeAndFlush(new MouseMove(7, 16));
//        f4.addListener(ChannelFutureListener.CLOSE);
        log.info("Channel active");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("Lost connection to the client");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("channelRead(" + msg + ")");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}