package server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.model.NewsLetter;

import java.util.List;

/**
 * Created by ileossa on 03/07/2017.
 */
@Repository
public interface NewsLetterRepository extends JpaRepository<NewsLetter, Integer>{

        /***
         * find all object if send news letter equals boolean passed
         * @param b
         * @return
         */
        List<NewsLetter> findAllBySendNewsLetterEquals(Boolean b);

        /**
         * find and return news letter if id == param
         * @param idClient
         * @return
         */
        NewsLetter findNewsLetterById(long idClient);

}
