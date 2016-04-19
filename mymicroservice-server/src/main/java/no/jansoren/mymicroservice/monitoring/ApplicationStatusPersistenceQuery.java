package no.jansoren.mymicroservice.monitoring;

import akka.actor.ExtendedActorSystem;
import akka.persistence.query.PersistenceQuery;

public class ApplicationStatusPersistenceQuery extends PersistenceQuery {

    public ApplicationStatusPersistenceQuery(ExtendedActorSystem system) {
        super(system);
    }
}
