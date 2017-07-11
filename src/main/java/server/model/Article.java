package server.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ileossa on 28/06/2017.
 */
@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "article")
public class Article {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    private String title;
    private String content;
    private String pathFile;
    private Date writeDate;

    public Article() {
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

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }
}
