package es.upm.miw.apaw_practice.adapters.rest.kitchen;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.kitchen.Chef;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.hamcrest.core.IsEqual.equalTo;

@RestTestConfig
public class ChefResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdate() {
        ChefDto chefDto = new ChefDto("44411122F", 3);
        this.webTestClient
                .put()
                .uri(ChefResource.CHEF + ChefResource.DNI + ChefResource.RECIPES_FINISHED, chefDto.getDni())
                .body(BodyInserters.fromValue(chefDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Chef.class)
                .value(chef -> chef.getRecipesFinished(), equalTo(3));
    }


    @Test
    void testUpdateDoesNotExist() {
        this.webTestClient
                .put()
                .uri(ChefResource.CHEF + ChefResource.DNI + ChefResource.RECIPES_FINISHED, "kk")
                .body(BodyInserters.fromValue(new ChefDto("44411122F", 3)))
                .exchange()
                .expectStatus().isNotFound();
    }
}
