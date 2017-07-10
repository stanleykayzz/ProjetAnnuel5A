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
    private int idPartyRoom;
    private float price;

    @JoinColumn(name = "SERVICES_HOTEL")
    @OneToMany
    private List<ServicesHotel> servicesHotel;


    public FestiveRoom() {
    }

    public FestiveRoom(float price) {
        this.price = price;
    }

    public FestiveRoom(float price, List<ServicesHotel> servicesHotel) {
        this.price = price;
        this.servicesHotel = servicesHotel;
    }

    public float getPrice() {
        return price;
    }

    public int getIdPartyRoom() {
        return idPartyRoom;
    }
}
