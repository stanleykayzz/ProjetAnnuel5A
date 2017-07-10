package server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ileossa on 05/04/2017.
 */
@Builder
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "FESTIVE_ROOM")
public class FestiveRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public void setIdPartyRoom(int idPartyRoom) {
        this.idPartyRoom = idPartyRoom;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
