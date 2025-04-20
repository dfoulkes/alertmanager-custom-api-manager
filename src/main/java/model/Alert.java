package model;

import java.util.Map;

public record Alert(
    String status,
    Map<String, String> labels,
    Map<String, String> annotations,
    String startsAt,
    String endsAt,
    String generatorURL,
    String fingerprint
) {
}
