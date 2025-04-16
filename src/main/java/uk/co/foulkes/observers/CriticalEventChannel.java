package uk.co.foulkes.observers;

import model.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;
import uk.co.foulkes.events.AlertManagerEvent;

import java.util.Optional;

@Component
public class CriticalEventChannel implements Channel<AlertManagerEvent> {

    private static final String HIGH_TEMP = "HighTemperature";
    private static final String ALERT_NAME = "alertname";
    private static final Logger logger = LoggerFactory.getLogger(CriticalEventChannel.class);

    private String currentState = "";


    @Override
    public boolean isObservingEvent(ApplicationEvent event) {
        if (event == null) {
            return false;
        }
        return event instanceof AlertManagerEvent;
    }

    @Override
    public void update(AlertManagerEvent event) {
            Optional<Alert> found = event.getRequest().alerts().stream().filter(x -> x.labels().get(ALERT_NAME).contains(HIGH_TEMP)).findFirst();
            if (found.isPresent()) {
                logger.info("SUCCESS !!! we've capture the critical event. High temperature detected!");
                return;
            }
            this.currentState = event.getMessage();
    }

    @Override
    public String getCurrentState() {
        return currentState;
    }


}
