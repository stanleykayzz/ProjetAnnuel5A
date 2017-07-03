package server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by ileossa on 28/06/2017.
 */
@Entity
public class Article {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int idArticle;

    private String title;
    private String content;
    private String pathFile;

    public Article() {
    }

    public Article(String title, String content, String pathFile) {
        this.title = title;
        this.content = content;
        this.pathFile = pathFile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }
}
