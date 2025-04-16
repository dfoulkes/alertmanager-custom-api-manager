package uk.co.foulkes.observers;

import model.Alert;
import model.AlertManagerRequest;
import org.junit.jupiter.api.Test;
import uk.co.foulkes.events.AlertManagerEvent;
import uk.co.foulkes.events.TestableEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageHandlerTest {
    // Test cases for MessageHandler class
    // 1. Test if the MessageHandler correctly identifies the event type
    // 2. Test if the MessageHandler correctly processes the event
    // 3. Test if the MessageHandler handles null events gracefully
    // 4. Test if the MessageHandler handles unsupported event types gracefully
    // 5. Test if the MessageHandler correctly updates its state after processing an event
    @Test
    void testFiringCriticalEventToMessageQueue() {
        CriticalEventChannel criticalEventChannel = new CriticalEventChannel();
        MessageHandler messageHandler = new MessageHandler(criticalEventChannel);
        AlertManagerEvent event = new AlertManagerEvent(this, dummyRequest());
        messageHandler.notifyObservers(event);
        assertEquals("firing", criticalEventChannel.getCurrentState());
    }

    @Test
    void testFiringNullEventToMessageQueue() {
        CriticalEventChannel criticalEventChannel = new CriticalEventChannel();
        MessageHandler messageHandler = new MessageHandler(criticalEventChannel);
        AlertManagerEvent event = null;
        messageHandler.notifyObservers(event);
        assertEquals("", criticalEventChannel.getCurrentState());
    }

    @Test
    void testFiringUnsupportedEventToMessageQueue() {
        CriticalEventChannel criticalEventChannel = new CriticalEventChannel();
        MessageHandler messageHandler = new MessageHandler(criticalEventChannel);
        TestableEvent event = new TestableEvent(this) {};
        messageHandler.notifyObservers(event);
        assertEquals("", criticalEventChannel.getCurrentState());
    }

    @Test
    void testFiringEventToMessageQueueWithNoObservers() {
        CriticalEventChannel criticalEventChannel = new CriticalEventChannel();
        MessageHandler messageHandler = new MessageHandler(null);
        AlertManagerEvent event = new AlertManagerEvent(this, dummyRequest());
        messageHandler.notifyObservers(event);
        assertEquals("", criticalEventChannel.getCurrentState());
    }

    AlertManagerRequest dummyRequest() {
        return new AlertManagerRequest(
                "Test",
                "firing",
                getDummyAlerts(),
                getDummyLabels(),
                getDummyLabels(),
                getDummyLabels(),
                "Test commonLabels",
                "Test commonAnnotations",
                "",
                0
        );
    }

    private Map<String, String> getDummyLabels() {
        Map<String, String> labels = new HashMap<>();
        labels.put("alertName", "HighTemp");
        return labels;
    }

    private ArrayList<Alert> getDummyAlerts() {
        ArrayList<Alert> alerts = new ArrayList<>();
        Alert alert = new Alert(
                "Test status",
                getDummyLabels(),
                getDummyLabels(),
                "Test groupLabels",
                "Test commonLabels",
                "Test commonAnnotations",
                null
        );
        alerts.add(alert);
        return alerts;
    }
}