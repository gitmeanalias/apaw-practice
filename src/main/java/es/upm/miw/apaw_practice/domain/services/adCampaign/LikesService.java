package es.upm.miw.apaw_practice.domain.services.adCampaign;

import es.upm.miw.apaw_practice.domain.models.adCampaign.Likes;
import es.upm.miw.apaw_practice.domain.persistence_ports.adCampaign.LikesPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikesService {

    private LikesPersistence likesPersistence;

    @Autowired
    public LikesService(LikesPersistence likesPersistence) {
        this.likesPersistence = likesPersistence;
    }

    public Likes create(Likes likes) {
        return this.likesPersistence.create(likes);
    }
}
