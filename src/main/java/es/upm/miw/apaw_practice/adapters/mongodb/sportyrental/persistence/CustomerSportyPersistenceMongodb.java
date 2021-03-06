package es.upm.miw.apaw_practice.adapters.mongodb.sportyrental.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.sportyrental.daos.CustomerSportyRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.sportyrental.daos.ReservationSportyRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.sportyrental.entities.CategorySportyEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.sportyrental.entities.CustomerSportyEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.sportyrental.entities.ReservationSportyEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.sportyrental.CustomerSporty;
import es.upm.miw.apaw_practice.domain.persistence_ports.sportyrental.CustomerSportyPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("customerSportyPersistence")
public class CustomerSportyPersistenceMongodb implements CustomerSportyPersistence {

    private CustomerSportyRepository customerSportyRepository;
    private ReservationSportyRepository reservationSportyRepository;

    @Autowired
    public CustomerSportyPersistenceMongodb(CustomerSportyRepository customerSportyRepository, ReservationSportyRepository reservationSportyRepository) {
        this.customerSportyRepository = customerSportyRepository;
        this.reservationSportyRepository = reservationSportyRepository;
    }

    @Override
    public Stream<CustomerSporty> readAll() {
        return this.customerSportyRepository.findAll().stream().map(CustomerSportyEntity::convertToCustomerSporty);
    }

    @Override
    public void deleteByDni(String dni) {
        CustomerSportyEntity customerSportyEntity = this.customerSportyRepository.findByDni(dni)
                .orElseThrow(() -> new NotFoundException("CustomerSporty dni: " + dni));
        this.customerSportyRepository.delete(customerSportyEntity);
    }

    @Override
    public Stream<String> readDescriptionsCategoryByCustomerName(String name) {
        return this.reservationSportyRepository.findAll().stream().filter(reservationSportyEntity -> reservationSportyEntity.getCustomerSportyEntities().stream().
                anyMatch(customerSportyEntity -> customerSportyEntity.getName().equals(name)))
                .map(ReservationSportyEntity::getCategorySportyEntity).map(CategorySportyEntity::getDescription).distinct().sorted();
    }
}
