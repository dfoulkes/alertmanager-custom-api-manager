package uk.co.foulkes.observers;

import model.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;
import uk.co.foulkes.events.AlertManagerEvent;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class CriticalEventChannel implements Channel<AlertManagerEvent> {

    private static final String HIGH_TEMP = "HighTemperature";
    private static final String SERVERITY = "severity";
    private static final String CRITICAL = "critical";
    private static final String ALERT_NAME = "alertname";
    private static final Logger logger = LoggerFactory.getLogger(CriticalEventChannel.class);

    private String currentState = "";
    private Set<String> alertsManaged = new HashSet<String>();


    public CriticalEventChannel() {
        alertsManaged.add(HIGH_TEMP);
    }

    @Override
    public boolean isObservingEvent(ApplicationEvent event) {
        if (event == null) {
            logger.error("Event in {} is null, unable to validate if observable", getName());
            return false;
        }
        return event instanceof AlertManagerEvent && eventIsCriticalSeverity((AlertManagerEvent) event);
    }

    private boolean eventIsCriticalSeverity(AlertManagerEvent event) {
        return event.getRequest().alerts().stream()
                .filter(x -> x.labels() != null)
                .filter(x -> x.labels().get(ALERT_NAME) != null)
                .anyMatch(x -> x.labels().get(SERVERITY).contains(CRITICAL));
    }

    private boolean isAlertNameInSet(String alertName) {
        if (!alertsManaged.contains(alertName)) {
            logger.info("Alert name {} is not managed by this Critical Alert Channel {managed:false}", alertName);
            return false;
        }
        return true;
    }

    @Override
    public void update(AlertManagerEvent event) {
        if (event == null || event.getRequest() == null) {return;}

            Optional<Alert> found = event.getRequest().alerts().stream()
                                   .filter(x -> x.labels() !=null)
                                   .filter(x -> x.labels().get(ALERT_NAME) != null)
                                   .filter(x -> isAlertNameInSet(x.labels().get(ALERT_NAME)))
                    .findFirst();
            if (found.isPresent()) {
                logger.info("SUCCESS !!! we've capture the critical event. High temperature detected!");
                return;
            } else {
                logger.debug("No critical event detected");
            }
            this.currentState = event.getMessage();
    }

    @Override
    public String getCurrentState() {
        return currentState;
    }

    @Override
    public String getName() {
        return "CriticalEventChannel";
    }
}
