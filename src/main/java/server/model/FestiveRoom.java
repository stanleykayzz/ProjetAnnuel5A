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
    private String tokenClient;
    private ServicesHotel servicesHotel;


    public FestiveRoom() {
    }


    public FestiveRoom(String event, int numberChairs, int numberTables, String tokenClient) {
        this.event = event;
        this.tokenClient = tokenClient;
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


    public String getTokenClient() {
        return tokenClient;
    }

    public void setTokenClient(String tokenClient) {
        this.tokenClient = tokenClient;
    }


}
