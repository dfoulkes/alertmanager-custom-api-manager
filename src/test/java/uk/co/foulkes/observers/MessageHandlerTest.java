package uk.co.foulkes.observers;

import model.Alert;
import model.AlertManagerRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uk.co.foulkes.events.AlertManagerEvent;
import uk.co.foulkes.events.TestableEvent;
import uk.co.foulkes.utils.TestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageHandlerTest {


    @Test
    void testFiringCriticalEventToMessageQueue() {
        TestUtils utils = new TestUtils();
        CriticalEventChannel criticalEventChannel = new CriticalEventChannel();
        MessageHandler messageHandler = new MessageHandler(criticalEventChannel);
        AlertManagerEvent event = new AlertManagerEvent(this, utils.dummyRequest());
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
        TestUtils utils = new TestUtils();
        CriticalEventChannel criticalEventChannel = new CriticalEventChannel();
        MessageHandler messageHandler = new MessageHandler(null);
        AlertManagerEvent event = new AlertManagerEvent(this, utils.dummyRequest());
        messageHandler.notifyObservers(event);
        assertEquals("", criticalEventChannel.getCurrentState());
    }


}