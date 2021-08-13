package net.kawinski.connectedworkspace.application.server;

import lombok.extern.slf4j.Slf4j;
import net.kawinski.connectedworkspace.application.server.Server;
import net.kawinski.connectedworkspace.protocol.Message;
import net.kawinski.connectedworkspace.protocol.messages.MouseMove;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
public class HookListener implements NativeMouseInputListener {
    private final Server server;

    private int lastX, lastY;

    public HookListener(Server server) {
        this.server = server;
    }

    public void registerHooks() throws NativeHookException {
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);

        GlobalScreen.registerNativeHook();

        // Add the appropriate listeners.
        GlobalScreen.addNativeMouseListener(this);
        GlobalScreen.addNativeMouseMotionListener(this);

        // TODO: Initialize lastX, lastY to current screen coords
    }

    public void nativeMouseClicked(NativeMouseEvent e) {
        log.info("Mouse Clicked: " + e.getClickCount());
    }

    public void nativeMousePressed(NativeMouseEvent e) {
        log.info("Mouse Pressed: " + e.getButton());
    }

    public void nativeMouseReleased(NativeMouseEvent e) {
        log.info("Mouse Released: " + e.getButton());
    }

    public void nativeMouseMoved(NativeMouseEvent e) {
        int dx = e.getX() - lastX;
        int dy = e.getY() - lastY;
        lastX = e.getX();
        lastY = e.getY();
        log.info("Mouse Moved by: " + dx + ", " + dy + " --> " + e.getX() + ", " + e.getY());
        Message message = new MouseMove(dx, dy);
        server.send(message);
    }

    public void nativeMouseDragged(NativeMouseEvent e) {
        log.info("Mouse Dragged: " + e.getX() + ", " + e.getY());
    }

}
