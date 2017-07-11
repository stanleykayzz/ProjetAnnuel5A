package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.model.Article;
import server.repository.ArticleRepository;
import server.repository.ClientRepository;
import server.service.DateService;
import server.service.client.ClientService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by ileossa on 09/07/2017.
 */
@RestController
@RequestMapping("/api/article")
public class ArticleController {

    private ArticleRepository articleRepository;
    private ClientRepository clientRepository;
    private ClientService clientService;
    private DateService dateService;

    @Autowired
    public ArticleController(ArticleRepository articleRepository, ClientRepository clientRepository, ClientService clientService, DateService dateService) {
        this.articleRepository = articleRepository;
        this.clientRepository = clientRepository;
        this.clientService = clientService;
        this.dateService = dateService;
    }

    @RequestMapping(method = POST)
    @ResponseStatus(value = OK)
    public void newArticle(@RequestParam(value="token") String tokenClient,
                           @RequestBody Article article){
        if(clientService.isAdministator(tokenClient)) {
            article.setWriteDate(dateService.currentLocalTime());
            articleRepository.saveAndFlush(article);
        }
    }


    @RequestMapping(method = PUT)
    @ResponseStatus(value = OK)
    public void  updateArticle(@RequestParam(value="token")String tokenClient,
                               @RequestBody Article article){
        if(clientService.isAdministator(tokenClient)) {
            if(articleRepository.findAllById(article.getId()).isEmpty()){
                article.setWriteDate(dateService.currentLocalTime());
                articleRepository.saveAndFlush(article);
            }

        }
    }


    @RequestMapping(method = DELETE)
    @ResponseStatus(value = OK)
    public void deleteArticle(@RequestParam(value="token")String tokenClient,
                              @RequestParam(value = "id") int articleId){
        if(clientService.isAdministator(tokenClient)) {
            if(articleRepository.findAllById(articleId).isEmpty()){
                articleRepository.delete(articleId);
            }
        }
    }


    @RequestMapping(method = GET)
    @ResponseStatus(value = OK)
    public List<Article> getList(@RequestParam(value = "token")String tokenClient){
        if(clientService.isAdministator(tokenClient)) {
            return articleRepository.findAll();
        }
        return new ArrayList<Article>();
    }
}
