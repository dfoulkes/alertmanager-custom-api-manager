package uk.co.foulkes.criticalissuerestservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "uk.co.foulkes")
public class CriticalIssueRestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CriticalIssueRestServiceApplication.class, args);
    }

}
