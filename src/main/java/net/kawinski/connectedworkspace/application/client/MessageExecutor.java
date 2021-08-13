package net.kawinski.connectedworkspace.application.client;

import net.kawinski.connectedworkspace.protocol.Message;
import net.kawinski.connectedworkspace.protocol.messages.MouseMove;

import java.awt.*;

public class MessageExecutor {
    private final Robot robot;

    public MessageExecutor() throws AWTException {
        this.robot = new Robot();
    }

    public void execute(Message msg) {
        switch (msg.getType()) {
            case MOUSE_MOVE:
                execute((MouseMove) msg);
                break;
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

    private void execute(MouseMove msg) {
        Point pos = MouseInfo.getPointerInfo().getLocation();
        pos.move(msg.getDx(), msg.getDy());
        robot.mouseMove(pos.x, pos.y);
    }

}
