package server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by ileossa on 18/06/2017.
 */
@Entity
public class ServicesHotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idServicesHotel;
    private String name;
    private String type;
    private float price;
    private String comment;

    public ServicesHotel() {
    }

    public ServicesHotel(String name, String type, float price, String comment) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.comment = comment;
    }


    public int getIdServicesHotel() {
        return idServicesHotel;
    }

    public void setIdServicesHotel(int idServicesHotel) {
        this.idServicesHotel = idServicesHotel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ServicesHotel{" +
                "idServicesHotel=" + idServicesHotel +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", costByNight=" + price +
                ", comment='" + comment + '\'' +
                '}';
    }
}
