package es.upm.miw.apaw_practice.adapters.mongodb.adCampaign.entities;

import es.upm.miw.apaw_practice.domain.models.adCampaign.AdCampaign;
import es.upm.miw.apaw_practice.domain.models.adCampaign.Likes;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LikesEntity {
    @Id
    private String id;
    private String name;
    private String description;

    public LikesEntity() {
    }

    public LikesEntity(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Likes toLikes() {
        Likes likes = new Likes();
        BeanUtils.copyProperties(this, likes);
        return likes;
    }


    @Override
    public String toString() {
        return "LikesEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
