package es.upm.miw.apaw_practice.adapters.rest.garage;

import es.upm.miw.apaw_practice.domain.services.garage.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(VehicleGarageResource.VEHICLES)
public class VehicleGarageResource {
    static final String VEHICLES = "/garage/vehicles";

    private VehicleService vehicleService;

    @Autowired
    public VehicleGarageResource(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PatchMapping
    public void updateEstimatedBudget(@RequestBody List<VehicleEstimatedBudgetUpdating> estimatedBudgetUpdatingList){
        this.vehicleService.updateEstimatedBudget(estimatedBudgetUpdatingList);
    }

}
