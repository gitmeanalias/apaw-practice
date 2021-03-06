package es.upm.miw.apaw_practice.domain.persistence_ports.airport;

import es.upm.miw.apaw_practice.domain.models.airport.Flight;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface FlightPersistence {

    Stream<Flight> readAll();

    Flight readById(String id);

    Flight update(Flight flight);
}
