package server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by ileossa on 05/04/2017.
 */
@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTable;
    private String name;
    private int placeNumber;
    private int idClient;

    public Restaurant() {
    }

    public Restaurant(String name, int placeNumber, int idClient) {
        this.name = name;
        this.placeNumber = placeNumber;
        this.idClient = idClient;
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "idTable=" + idTable +
                ", name='" + name + '\'' +
                ", placeNumber=" + placeNumber +
                ", idClient=" + idClient +
                '}';
    }


    public Restaurant check(Object o) {
        if (this == o) return (Restaurant) o;
        if (o == null || getClass() != o.getClass()) return (Restaurant) o;

        Restaurant that = (Restaurant) o;

        if (idTable != that.idTable) setIdTable(that.idTable);
        if (placeNumber != that.placeNumber) setPlaceNumber(that.placeNumber);
        if (idClient != that.idClient) setIdClient(that.idClient);
        if (name.equals(that.name)) setName(that.name);
        return this;
    }


}
