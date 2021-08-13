package net.kawinski.connectedworkspace.nettyexamples;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import net.kawinski.connectedworkspace.protocol.Message;
import net.kawinski.connectedworkspace.protocol.MessageType;
import net.kawinski.connectedworkspace.protocol.messages.MouseMove;

import java.util.List;

public class MessageDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        MessageType type = MessageType.byValue(in.readInt())
                .get(); // TODO: drop if wrong? Also check magic start/end?
        Message decoded = decode(type, in);
        out.add(decoded);
    }

    private Message decode(MessageType type, ByteBuf in) {
        switch (type) {
            case MOUSE_MOVE: {
                int dx = in.readInt();
                int dy = in.readInt();
                return new MouseMove(dx, dy);
            }
            case MOUSE_PRESS:
                break;
            case KEY_PRESS:
                break;
            case KEY_MODIFIER:
                break;
            case GET_CLIPBOARD:
                break;
            case SET_CLIPBOARD:
                break;
        }
        throw new IllegalStateException("Undecodable message for type " + type);
    }
}
