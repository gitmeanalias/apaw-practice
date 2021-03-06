package es.upm.miw.apaw_practice.domain.services.museum;

import es.upm.miw.apaw_practice.domain.models.museum.Paint;
import es.upm.miw.apaw_practice.domain.persistence_ports.museum.PaintPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class PaintService {

    private PaintPersistence paintPersistence;

    @Autowired
    public PaintService( PaintPersistence paintPersistence){
        this.paintPersistence = paintPersistence;
    }

    public Paint create (Paint paint){
        return this.paintPersistence.create(paint);
    }

    public void delete(String id) {
        this.paintPersistence.deleteById(id);
    }

    public Stream<Paint> findPaintCollectionByArtRestorerSurname(String artRestorerSurname) {
        return this.paintPersistence.findPaintCollectionByArtRestorerSurname(artRestorerSurname);
    }

}
