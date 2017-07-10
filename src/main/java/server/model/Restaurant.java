package server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by ileossa on 05/04/2017.
 */
@Entity
@Getter
@Setter
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TABLE_RESTAURANT_ID")
    private int idTable;
    private String type;
    private int nbClients;

    public Restaurant() {
    }

    public Restaurant(String type, int nbClients) {
        this.type = type;
        this.nbClients = nbClients;
    }

    public int getIdTable() {
        return idTable;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getNbClients() {
        return nbClients;
    }

    public void setNbClients(int nbClients) {
        this.nbClients = nbClients;
    }

    public Restaurant check(Object o) {
        if (this == o) return (Restaurant) o;
        if (o == null || getClass() != o.getClass()) return (Restaurant) o;

        Restaurant that = (Restaurant) o;

        if (nbClients != that.nbClients) setNbClients(that.nbClients);
        if (type.equals(that.type)) setType(that.type);
        return this;
    }


}
