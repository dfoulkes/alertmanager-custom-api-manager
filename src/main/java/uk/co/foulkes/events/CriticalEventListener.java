package uk.co.foulkes.events;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import uk.co.foulkes.observers.MessageHandler;

@Component
public class CriticalEventListener implements ApplicationListener<AlertManagerEvent> {

    private final MessageHandler messageHandler;

    @Autowired
    public CriticalEventListener(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public void onApplicationEvent(AlertManagerEvent event) {
        messageHandler.notifyObservers(event);
    }
}
