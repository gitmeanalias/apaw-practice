package es.upm.miw.apaw_practice.adapters.mongodb.music.persistence;


import es.upm.miw.apaw_practice.adapters.mongodb.music.daos.StyleRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.music.entities.StyleEntity;
import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.music.Style;
import es.upm.miw.apaw_practice.domain.persistence_ports.music.StylePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StylePersistenceMangodb implements StylePersistence {
    private final StyleRepository styleRepository;

    @Autowired
    public StylePersistenceMangodb(StyleRepository styleRepository){
        this.styleRepository = styleRepository;
    }

    public void assertNameNotExist(String name) {
        this.styleRepository
                .findByName(name)
                .ifPresent(article ->{
                    throw new ConflictException("Name exist:" + name);
                });
    }

    @Override
    public Style create(Style style){
        this.assertNameNotExist(style.getName());
        return this.styleRepository
                .save(StyleEntity.builder(style.getName()).description(style.getDescription()).build())
                .toStyle();
    }

    @Override
    public Style update(Style style){
        StyleEntity styleEntity = this.styleRepository
                .findById(style.getId())
                .orElseThrow(() -> new NotFoundException("Style id:" + style.getId()));
        styleEntity.fromStyle(style);
        return this.styleRepository
                .save(styleEntity)
                .toStyle();
    }
    @Override
    public Style readByName(String name){
        return this.styleRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("Style name" + name))
                .toStyle();
    }

}
