package es.upm.miw.apaw_practice.adapters.mongodb.music.daos;


import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.music.entities.StyleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class StyleEntityRepositoryIT {

    @Autowired
    private StyleRepository styleRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.styleRepository.findAll().stream()
                .anyMatch(style ->
                        style.getId() != null &&
                                "POP".equals(style.getName())&&
                                "Pop is a genre of popular music that originated in its modern form during the mid-1950s in the United States and the United Kingdom.".equals(style.getDescription())
        ));
    }


    @Test
    void textFindByName(){
        assertTrue(this.styleRepository.findByName("POP").isPresent());
        StyleEntity style = this.styleRepository.findByName("POP").get();
        assertEquals("Pop is a genre of popular music that originated in its modern form during the mid-1950s in the United States and the United Kingdom.", style.getDescription());
    }
}
