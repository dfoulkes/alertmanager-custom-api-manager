package uk.co.foulkes.observers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);
    private final List<Channel<ApplicationEvent>> channels = new ArrayList<>();

    @Autowired
    public MessageHandler(CriticalEventChannel criticalIssueEvent) {
        addObserver(criticalIssueEvent);
    }

    public void addObserver(Channel channel) {
        channels.add(channel);
    }

    public void removeObserver(Channel<ApplicationEvent> channel) {
        channels.remove(channel);
    }

    public void notifyObservers(ApplicationEvent message) {
        channels.stream()
                .filter(channel -> channel != null && channel.isObservingEvent(message))
                .forEach(channel -> {
                    try {
                        channel.update(message);
                    } catch (Exception e) {
                        logger.error("Wasn't able to process the event on channel: {} we got the following error {}", channel.getName(), e.getMessage());
                    }
                });
    }
}
