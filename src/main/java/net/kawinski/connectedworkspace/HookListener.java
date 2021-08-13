package net.kawinski.connectedworkspace;

import lombok.extern.slf4j.Slf4j;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
public class HookListener implements NativeMouseInputListener {
    private final Server server;

    public HookListener(Server server) {
        this.server = server;
    }

    public void registerHooks() throws NativeHookException {
        GlobalScreen.registerNativeHook();

        // Add the appropriate listeners.
        GlobalScreen.addNativeMouseListener(this);
        GlobalScreen.addNativeMouseMotionListener(this);
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
        log.info("Mouse Moved: " + e.getX() + ", " + e.getY());
    }

    public void nativeMouseDragged(NativeMouseEvent e) {
        log.info("Mouse Dragged: " + e.getX() + ", " + e.getY());
    }

}
