package server.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

/**
 * Created by ileossa on 03/07/2017.
 */
@Entity
public class NewsLetter {

    @Id
    @GeneratedValue(strategy = AUTO)
    private int idNewsLetter;

    private long idClient;

    private boolean sendNewsLetter;


    public NewsLetter() {
        this.sendNewsLetter = true;
    }

    public NewsLetter(boolean sendNewsLetter) {
        this.sendNewsLetter = sendNewsLetter;
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
}
