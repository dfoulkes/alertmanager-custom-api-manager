package uk.co.foulkes.events;

import org.springframework.context.ApplicationEvent;

public class TestableEvent extends ApplicationEvent {

    public TestableEvent(Object source) {
        super(source);
    }

}
