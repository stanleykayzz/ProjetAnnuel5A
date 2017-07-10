package server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ileossa on 05/04/2017.
 */
@Entity
@Getter
@Setter
@Table(name = "festive_room")
public class FestiveRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FESTIVE_ROOM_ID")
    private int idPartyRoom;
    private float price;


    public FestiveRoom() {
    }

    public FestiveRoom(float price) {
        this.price = price;
    }


    public int getIdPartyRoom() {
        return idPartyRoom;
    }


    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
