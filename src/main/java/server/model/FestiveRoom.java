package server.model;

import javax.persistence.*;

/**
 * Created by ileossa on 05/04/2017.
 */
@Entity
@Table(name = "festive_room")
public class FestiveRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idPartyRoom;
    private String event;
    private int numberChairs;
    private int numberTables;
    private int idClient;


    public FestiveRoom() {
    }


    public FestiveRoom(String event, int numberChairs, int numberTables, int idClient) {
        this.event = event;
        this.numberChairs = numberChairs;
        this.numberTables = numberTables;
        this.idClient = idClient;
    }

    public int getIdPartyRoom() {
        return idPartyRoom;
    }

    public void setIdPartyRoom(int idPartyRoom) {
        this.idPartyRoom = idPartyRoom;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getNumberChairs() {
        return numberChairs;
    }

    public void setNumberChairs(int numberChairs) {
        this.numberChairs = numberChairs;
    }

    public int getNumberTables() {
        return numberTables;
    }

    public void setNumberTables(int numberTables) {
        this.numberTables = numberTables;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "FestiveRoom{" +
                "idPartyRoom=" + idPartyRoom +
                ", event='" + event + '\'' +
                ", numberChairs=" + numberChairs +
                ", numberTables=" + numberTables +
                ", idClient=" + idClient +
                '}';
    }
}
