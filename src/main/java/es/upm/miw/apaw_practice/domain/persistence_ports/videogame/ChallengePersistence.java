package es.upm.miw.apaw_practice.domain.persistence_ports.videogame;

import es.upm.miw.apaw_practice.domain.models.videogame.Challenge;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ChallengePersistence {

    Stream<Challenge> readAll();

    Challenge readById(String id);

    Challenge update(Challenge challenge);
}
