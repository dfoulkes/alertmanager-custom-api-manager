package uk.co.foulkes.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import net.logstash.logback.encoder.LogstashEncoder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.nio.charset.StandardCharsets;

public class LogEncoder extends LogstashEncoder {

    @Override
    public byte[] encode(ILoggingEvent event) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            byte[] encodedBytes = super.encode(event);
            String jsonString = new String(encodedBytes, StandardCharsets.UTF_8);
            JsonNode root = mapper.readTree(jsonString);
//          ((ObjectNode) root).put("customField", "customValue");
            ((ObjectNode) root).put("message", root.get("message").asText());
            String jsonWithNewline = mapper.writeValueAsString(root) + System.lineSeparator();
            return jsonWithNewline.getBytes(StandardCharsets.UTF_8);

        } catch (Exception e) {
            return super.encode(event); // Return the original encoding on error
        }
    }
}