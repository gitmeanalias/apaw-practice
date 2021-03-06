package es.upm.miw.apaw_practice.adapters.rest.property;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.property.PropertyCompany;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
public class PropertyCompanyResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateNotFound() {
        PropertyCompany company = new PropertyCompany("Wakanda Property Company");
        this.webTestClient
                .put()
                .uri(PropertyCompanyResource.COMPANIES + PropertyCompanyResource.NAME,"abc")
                .body(BodyInserters.fromValue(company))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdate() {
        PropertyCompany propertyCompany = this.webTestClient
                .get()
                .uri(PropertyCompanyResource.COMPANIES)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(PropertyCompany.class)
                .value(Assertions::assertNotNull)
                .returnResult()
                .getResponseBody()
                .get(0);

        PropertyCompany company = new PropertyCompany("Lancdon Property Company");
        this.webTestClient
                .put()
                .uri(PropertyCompanyResource.COMPANIES + PropertyCompanyResource.NAME, propertyCompany.getAddress(), propertyCompany.getDescription())
                .body(BodyInserters.fromValue(company))
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(PropertyCompany.class)
                .value(Assertions::assertNotNull);

        assertEquals("Lancdon Property Company", this.webTestClient
                .get()
                .uri(PropertyCompanyResource.COMPANIES)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(PropertyCompany.class)
                .returnResult()
                .getResponseBody()
                .get(0)
                .getName());
    }
}