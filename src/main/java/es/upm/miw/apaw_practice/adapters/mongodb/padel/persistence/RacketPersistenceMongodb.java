package es.upm.miw.apaw_practice.adapters.mongodb.padel.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.padel.daos.RacketRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.padel.entities.RacketEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.padel.Racket;
import es.upm.miw.apaw_practice.domain.persistence_ports.padel.RacketPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository("RacketPersistence")
public class RacketPersistenceMongodb implements RacketPersistence {
    private RacketRepository racketRepository;

    @Autowired
    public RacketPersistenceMongodb(RacketRepository racketRepository) {
        this.racketRepository = racketRepository;
    }

    public Racket update(Racket racket){
        RacketEntity racketEntity = this.racketRepository.findById(racket.getId())
                .orElseThrow(()-> new NotFoundException("Racket id:" + racket.getId()));

        racketEntity.fromRacket(racket);
        return this.racketRepository
                .save(racketEntity)
                .toRacket();
    }

    @Override
    public Stream<Racket> readByBrand(String brand) {
        if(this.racketRepository.findAllByBrand(brand).isEmpty()){
            throw new NotFoundException("Racket brand: " + brand);
        }
        return this.racketRepository.findAllByBrand(brand)
                .stream()
                .map(RacketEntity::toRacket);
    }

    @Override
    public void deleteById(String id) {
        this.racketRepository.deleteById(id);
    }
}