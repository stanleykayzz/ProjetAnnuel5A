package server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import server.model.Enum.Reason;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

/**
 * Created by ileossa on 03/07/2017.
 */
@Entity
@Builder
@AllArgsConstructor
@Table(name = "NEWS_LETTER")
public class NewsLetter {

    @Id
    @GeneratedValue(strategy = AUTO)
    private int idNewsLetter;

    private long idClient;
    private boolean sendNewsLetter;
    private String description;
    private Reason reason;


    public NewsLetter() {
        this.sendNewsLetter = true;
    }


    public int getIdNewsLetter() {
        return idNewsLetter;
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
        this.idClient = idClient;
    }

    public boolean isSendNewsLetter() {
        return sendNewsLetter;
    }

    public void setSendNewsLetter(boolean sendNewsLetter) {
        this.sendNewsLetter = sendNewsLetter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
