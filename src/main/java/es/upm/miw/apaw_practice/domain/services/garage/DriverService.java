package es.upm.miw.apaw_practice.domain.services.garage;

import es.upm.miw.apaw_practice.domain.models.garage.Driver;
import es.upm.miw.apaw_practice.domain.models.garage.DriverCreation;
import es.upm.miw.apaw_practice.domain.persistence_ports.garage.DriverPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    DriverPersistence driverPersistence;

    @Autowired
    public DriverService(DriverPersistence driverPersistence) {
        this.driverPersistence = driverPersistence;
    }

    public Driver create(DriverCreation driverCreation) {
        return this.driverPersistence.create(driverCreation);
    }

    public void delete(String dni) {
        this.driverPersistence.delete(dni);
    }

    public List<String> findMechanicNamesByDriverName(String driverName) {
        return this.driverPersistence.findMechanicNamesByDriverName(driverName);
    }

}
