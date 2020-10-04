package es.upm.miw.apaw_practice.adapters.rest.factory;

import es.upm.miw.apaw_practice.domain.models.factory.Machine;
import es.upm.miw.apaw_practice.domain.services.factory.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(MachineResource.MACHINES)
public class MachineResource {

    static final String MACHINES = "/factory/machines";
    static final String ID_ID = "/{id}";
    static final String STATUS = "/status";

    private MachineService machineService;

    @Autowired
    public MachineResource(MachineService machineService) {
        this.machineService = machineService;
    }

    @PutMapping(ID_ID + STATUS)
    public Machine updateStatus(@PathVariable String id, @RequestBody StatusDto isActive) {
        return this.machineService.updateStatus(id, isActive.getIsActive());
    }

    @GetMapping
    public Stream<Machine> readAll() {
        return this.machineService.readAll();
    }
}