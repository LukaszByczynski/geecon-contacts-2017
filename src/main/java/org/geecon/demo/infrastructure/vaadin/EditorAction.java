package org.geecon.demo.infrastructure.vaadin;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class EditorAction {
    private String sessionId;
    private UUID id;
    private ActionType actionType;
}
