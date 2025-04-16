package uk.co.foulkes.observers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class MessageHandler {

    @Autowired
    public MessageHandler(CriticalEventChannel criticalIssueEvent) {
        addObserver(criticalIssueEvent);
    }

    private List<Channel> channels = new ArrayList<Channel>();

    public void addObserver(Channel channel) {
        channels.add(channel);
    }

    public void removeObserver(Channel channel) {
        channels.remove(channel);
    }

    public void notifyObservers(ApplicationEvent message) {
        channels.stream()
                .filter(channel -> channel != null && channel.isObservingEvent(message))
                .forEach(channel -> channel.update(message));
    }
}
