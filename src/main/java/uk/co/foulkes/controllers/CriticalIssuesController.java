package uk.co.foulkes.controllers;


import model.AlertManagerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.foulkes.events.AlertManagerEvent;

@RestController
@RequestMapping("/api")
public class CriticalIssuesController {

      private final ApplicationEventPublisher eventPublisher;
      private static final String SEVERITY = "severity";
      private static final String FIRING = "firing";
      private static final String CRITICAL = "critical";
      @Autowired
      public CriticalIssuesController(ApplicationEventPublisher eventPublisher) {
          this.eventPublisher = eventPublisher;
      }

    @PostMapping("/critical-issues")
    public String criticalIssues(@RequestBody AlertManagerRequest alertManagerRequest) {
        if (alertManagerRequest == null) {
            return "No alert manager request provided";
        }
        if (alertManagerRequest.status() == null || alertManagerRequest.status().isEmpty()) {
            return "No status provided in the alert manager request";
        }
        if (alertManagerRequest.commonLabels() == null || alertManagerRequest.commonLabels().isEmpty()) {
            return "no common labels provided in the alert manager request";
        }
        if(alertManagerRequest.commonLabels().get(SEVERITY) == null || alertManagerRequest.commonLabels().get(SEVERITY).isEmpty()) {
            return "no severity provided in the alert manager request";
        }
        if (CRITICAL.equalsIgnoreCase(alertManagerRequest.commonLabels().get(SEVERITY)) &&  FIRING.equalsIgnoreCase(alertManagerRequest.status())) {
            eventPublisher.publishEvent(new AlertManagerEvent(this, alertManagerRequest));
        }

        return "Critical Issues";
    }

}
