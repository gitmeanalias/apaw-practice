package es.upm.miw.apaw_practice.adapters.rest.shop;

import es.upm.miw.apaw_practice.adapters.rest.LexicalAnalyzer;
import es.upm.miw.apaw_practice.domain.models.shop.Article;
import es.upm.miw.apaw_practice.domain.models.shop.ArticleCreation;
import es.upm.miw.apaw_practice.domain.models.shop.ArticlePriceUpdating;
import es.upm.miw.apaw_practice.domain.services.shop.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(ArticleResource.ARTICLES)
public class ArticleResource {
    static final String ARTICLES = "/shop/articles";

    static final String SEARCH = "/search";
    static final String ID_ID = "/{id}";
    static final String DESCRIPTION = "/description";

    private ArticleService articleService;

    @Autowired
    public ArticleResource(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public Article create(@RequestBody ArticleCreation articleCreation) {
        return this.articleService.create(articleCreation);
    }

    @PatchMapping
    public void updatePrices(@RequestBody List<ArticlePriceUpdating> articlePriceUpdatingList) {
        this.articleService.updatePrices(articlePriceUpdatingList);
    }

    @GetMapping(SEARCH)
    public Stream<Article> findByProviderAndPriceGreaterThan(@RequestParam String q) {
        String provider = new LexicalAnalyzer().extractWithAssure(q, "provider");
        BigDecimal price = new LexicalAnalyzer().extractWithAssure(q, "price", BigDecimal::new);
        return this.articleService.findByProviderAndPriceGreaterThan(provider, price);
    }

    @GetMapping
    public Stream<BasicArticleDto> redAll() {
        return this.articleService.readAll()
                .map(BasicArticleDto::new);
    }

    @PutMapping(ID_ID + DESCRIPTION)
    public Article updateDescription(@PathVariable String id, @RequestBody DescriptionDto descriptionDto) {
        return this.articleService.updateDescription(id, descriptionDto.getDescription());
    }

}
