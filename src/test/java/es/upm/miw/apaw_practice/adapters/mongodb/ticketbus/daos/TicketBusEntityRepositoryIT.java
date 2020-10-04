package es.upm.miw.apaw_practice.adapters.mongodb.ticketbus.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class TicketBusEntityRepositoryIT {

    @Autowired
    private TicketBusRepository ticketBusRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.ticketBusRepository.findAll().stream()
                .anyMatch(ticket ->
                        Integer.valueOf(11).equals(ticket.getSeat()) &&
                                ticket.getDepartureTime() == null &&
                                ticket.getArriveTime() == null &&
                                new BigDecimal("30.99").equals(ticket.getPrice()) &&
                                "89386661J".equals(ticket.getPassenger().getDocIdentify()) &&
                                "Juan".equals(ticket.getPassenger().getName()) &&
                                "Perez".equals(ticket.getPassenger().getFamilyName()) &&
                                "651112234".equals(ticket.getPassenger().getPhone()) &&
                                "jpz@upm.es".equals(ticket.getPassenger().getEmail()) &&
                                Boolean.FALSE.equals(ticket.getPassenger().getAccesibility())
                )
        );
    }

}