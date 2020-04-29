package es.upm.miw.apaw_practice.business.shop;

import es.upm.miw.apaw_practice.data.shop.daos.TagRepository;
import es.upm.miw.apaw_practice.data.shop.dtos.TagDto;
import es.upm.miw.apaw_practice.rest.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public TagDto read(String id) {
        return new TagDto(this.tagRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tag id: " + id))
        );
    }

    public void delete(String id) {
        this.tagRepository.deleteById(id);
    }
}