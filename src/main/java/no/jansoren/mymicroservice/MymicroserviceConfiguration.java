package no.jansoren.mymicroservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class MymicroserviceConfiguration extends Configuration {

    @NotEmpty
    private String actorSystemName = MymicroserviceApplication.APPLICATION_NAME;

    @NotEmpty
    private String persistenceActorName;

    @NotEmpty
    private String applicationPersistenceId;

    @JsonProperty
    public String getActorSystemName() {
        return actorSystemName;
    }

    @JsonProperty
    public void setActorSystemName(String actorSystemName) {
        this.actorSystemName = actorSystemName;
    }

    @JsonProperty
    public String getPersistenceActorName() {
        return persistenceActorName;
    }

    @JsonProperty
    public void setPersistenceActorName(String persistenceActorName) {
        this.persistenceActorName = persistenceActorName;
    }

    @JsonProperty
    public String getApplicationPersistenceId() {
        return applicationPersistenceId;
    }

    @JsonProperty
    public void setApplicationPersistenceId(String applicationPersistenceId) {
        this.applicationPersistenceId = applicationPersistenceId;
    }
}
