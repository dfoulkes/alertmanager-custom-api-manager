package model;

import java.util.ArrayList;
import java.util.Map;

public record AlertManagerRequest(
        String receiver,
        String status,
        ArrayList<Alert> alerts,
        Map<String, String> groupLabels,
        Map<String, String> commonLabels,
        Map<String, String> commonAnnotations,
        String externalLabels,
        String version,
        String groupKey,
        int truncatedAlerts
) {}