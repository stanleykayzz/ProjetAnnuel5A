package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.model.Article;

import java.util.List;

/**
 * Created by ileossa on 28/06/2017.
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    /***
     *  Return Article model is exist in dataBase, by Id
     * @param idArticle
     * @return
     */
    List<Article> findAllById(int idArticle);
}
