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

        List<NewsLetter> findAllBySendNewsLetterEquals(Boolean b);
        NewsLetter findNewsLetterByIdClientEquals(long idClient);

}
