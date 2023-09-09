package com.demo.pointschallenge.pointschallenge.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
@Component
public class HealthCheckService  implements HealthIndicator{

    @Override
    public Health health() {

        if(isRequiredServiceUp()){
            return Health.up()
                    .withDetail("message","All required servcies are up")
                    .build();
        }else{
            return Health.down()
                    .withDetail("message","All required servcies are not available")
                    .build();
        }
    }

    private boolean isRequiredServiceUp(){
        return false; // this can be an external service | database health or messageing service availablity
    }
    
}
