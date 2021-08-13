package net.kawinski.connectedworkspace.protocol;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

public enum MessageType {
    MOUSE_MOVE(1),
    MOUSE_PRESS(2),
    KEY_PRESS(3),
    KEY_MODIFIER(4),
    GET_CLIPBOARD(5),
    SET_CLIPBOARD(6);

    @Getter
    private final int value;

    MessageType(int value) {
        this.value = value;
    }

    public static Optional<MessageType> byValue(int value) {
        return Arrays.stream(values())
                .filter(entry -> entry.value == value)
                .findFirst();
    }
}
