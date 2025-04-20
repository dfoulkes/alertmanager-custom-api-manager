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
        if (preflightChecks(alertManagerRequest) && isCritical(alertManagerRequest)) {
            eventPublisher.publishEvent(new AlertManagerEvent(this, alertManagerRequest));
        }
        return "Critical Issues";
    }

    private boolean preflightChecks(AlertManagerRequest alertManagerRequest) {
        return alertManagerRequest != null && alertManagerRequest.status() != null
                && !alertManagerRequest.status().isEmpty() && alertManagerRequest.commonLabels() != null
                && !alertManagerRequest.commonLabels().isEmpty();
    }

    private boolean isCritical(AlertManagerRequest alertManagerRequest) {
        return CRITICAL.equalsIgnoreCase(alertManagerRequest.commonLabels().get(SEVERITY))
                && FIRING.equalsIgnoreCase(alertManagerRequest.status());
    }
}
