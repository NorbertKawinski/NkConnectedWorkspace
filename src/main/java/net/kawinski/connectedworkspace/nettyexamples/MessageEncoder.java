package net.kawinski.connectedworkspace.nettyexamples;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.kawinski.connectedworkspace.protocol.Message;
import net.kawinski.connectedworkspace.protocol.messages.MouseMove;

public class MessageEncoder extends MessageToByteEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msgBase, ByteBuf out) {
        out.writeInt(msgBase.getType().getValue());
        encode(out, msgBase);
    }

    private void encode(ByteBuf out, Message msgBase) {
        switch(msgBase.getType()) {
            case MOUSE_MOVE: {
                MouseMove msg = (MouseMove) msgBase;
                out.writeInt(msg.getDx());
                out.writeInt(msg.getDy());
                break;
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
    }
}