package server.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by ileossa on 28/06/2017.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "article")
public class Article {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int idArticle;

    private String title;
    private String content;
    private String pathFile;


}
