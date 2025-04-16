package uk.co.foulkes.observers;

import org.springframework.context.ApplicationEvent;

public interface Channel<T> {
    boolean isObservingEvent(ApplicationEvent event);
    void update(T message);
    String getCurrentState();
}

