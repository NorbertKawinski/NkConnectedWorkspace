package net.kawinski.connectedworkspace.protocol;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class Message {

    @Getter
    private final MessageType type;

}
