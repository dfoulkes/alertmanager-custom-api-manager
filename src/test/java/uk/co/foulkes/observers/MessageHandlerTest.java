package uk.co.foulkes.observers;

import org.junit.jupiter.api.Test;
import uk.co.foulkes.events.AlertManagerEvent;
import uk.co.foulkes.events.TestableEvent;

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
        AlertManagerEvent event = new AlertManagerEvent(this, "Test message");
        messageHandler.notifyObservers(event);
        assertEquals("Test message", criticalEventChannel.getCurrentState());
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
        AlertManagerEvent event = new AlertManagerEvent(this, "Test message");
        messageHandler.notifyObservers(event);
        assertEquals("", criticalEventChannel.getCurrentState());
    }
}