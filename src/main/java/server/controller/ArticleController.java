package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.model.Article;
import server.repository.ArticleRepository;
import server.repository.ClientRepository;
import server.service.ClientService;

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

    @Autowired
    public ArticleController(ArticleRepository articleRepository, ClientRepository clientRepository, ClientService clientService) {
        this.articleRepository = articleRepository;
        this.clientRepository = clientRepository;
        this.clientService = clientService;
    }

    @RequestMapping(method = POST)
    @ResponseStatus(value = OK)
    public void newArticle(@RequestParam(value="token") String tokenClient,
                           @RequestBody Article article){
        if(clientService.isAuthorized(tokenClient)) {
            articleRepository.saveAndFlush(article);
        }
    }


    @RequestMapping(method = PUT)
    @ResponseStatus(value = OK)
    public void  updateArticle(@RequestParam(value="token")String tokenClient,
                               @RequestBody Article article){
        if(clientService.isAuthorized(tokenClient)) {
            if(articleRepository.findAllByIdArticle(article.getIdArticle()).isEmpty()){
                articleRepository.saveAndFlush(article);
            }

        }
    }


    @RequestMapping(method = DELETE)
    @ResponseStatus(value = OK)
    public void deleteArticle(@RequestParam(value="token")String tokenClient,
                              @RequestParam(value = "id") int articleId){
        if(clientService.isAuthorized(tokenClient)) {
            if(articleRepository.findAllByIdArticle(articleId).isEmpty()){
                articleRepository.delete(articleId);
            }
        }
    }


    @RequestMapping(method = GET)
    @ResponseStatus(value = OK)
    public List<Article> getList(@RequestParam(value = "token")String tokenClient){
        if(clientService.isAuthorized(tokenClient)) {
            return articleRepository.findAll();
        }
        return new ArrayList<Article>();
    }
}
