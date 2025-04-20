package uk.co.foulkes.observers;

import org.springframework.context.ApplicationEvent;
import uk.co.foulkes.events.AlertManagerEvent;

public interface Channel<AlertManagerEvent> {
    boolean isObservingEvent(ApplicationEvent event);
    void update(AlertManagerEvent message);
    String getCurrentState();
    String getName();
}

