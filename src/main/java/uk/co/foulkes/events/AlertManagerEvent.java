package uk.co.foulkes.events;

import model.AlertManagerRequest;
import org.springframework.context.ApplicationEvent;

public final class AlertManagerEvent extends ApplicationEvent {

    private final AlertManagerRequest request;

    public AlertManagerEvent(Object source, AlertManagerRequest request) {
        super(source);
        this.request = request;
    }

    public AlertManagerRequest getRequest() {
        return request;
    }

    public String getMessage() {
        return request.status();
    }
}
