package uk.co.foulkes.utils;

import model.Alert;
import model.AlertManagerRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestUtils {

    public AlertManagerRequest dummyRequest() {
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
