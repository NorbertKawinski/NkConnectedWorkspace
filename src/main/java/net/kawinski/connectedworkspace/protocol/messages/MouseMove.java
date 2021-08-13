package net.kawinski.connectedworkspace.protocol.messages;

import lombok.Getter;
import lombok.ToString;
import net.kawinski.connectedworkspace.protocol.Message;
import net.kawinski.connectedworkspace.protocol.MessageType;

@ToString
public class MouseMove extends Message {

    @Getter
    private final int dx;

    @Getter
    private final int dy;

    public MouseMove(int dx, int dy) {
        super(MessageType.MOUSE_MOVE);
        this.dx = dx;
        this.dy = dy;
    }
}
