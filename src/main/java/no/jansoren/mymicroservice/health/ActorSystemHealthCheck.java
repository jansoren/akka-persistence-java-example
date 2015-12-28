package no.jansoren.mymicroservice.health;

import com.codahale.metrics.health.HealthCheck;

public class ActorSystemHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
