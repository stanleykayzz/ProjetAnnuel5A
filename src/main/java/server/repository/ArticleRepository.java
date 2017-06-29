package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.model.Article;

/**
 * Created by ileossa on 28/06/2017.
 */
public interface ArticleRepository extends JpaRepository<Article, Integer> {
}
