package uk.co.foulkes.observers;

import model.Alert;
import model.AlertManagerRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import uk.co.foulkes.events.AlertManagerEvent;
import uk.co.foulkes.utils.TestUtils;
class CriticalEventChannelTest {


    @Test
    void testGetName() {
        CriticalEventChannel channel = new CriticalEventChannel();
        assertEquals("CriticalEventChannel", channel.getName());
    }

    @Test
    void testIsObservingEvent() {
        TestUtils utils = new TestUtils();
        CriticalEventChannel channel = new CriticalEventChannel();
        AlertManagerEvent event = new AlertManagerEvent(this, utils.dummyRequest());
        assertTrue(channel.isObservingEvent(event));
    }

    @Test
    void testIsObservingNullEvent() {
        CriticalEventChannel channel = new CriticalEventChannel();
        assertFalse(channel.isObservingEvent(null));
    }
}