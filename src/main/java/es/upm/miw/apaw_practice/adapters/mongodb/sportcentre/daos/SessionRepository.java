package es.upm.miw.apaw_practice.adapters.mongodb.sportcentre.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.sportcentre.entities.SessionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SessionRepository extends MongoRepository<SessionEntity, String>{

}
