package server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by ileossa on 05/04/2017.
 */
@Entity
public class TableRestaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idTable;
    private String name;
    private int placeNumber;

    public TableRestaurant() {
    }

    public TableRestaurant(String name, int placeNumber) {
        this.name = name;
        this.placeNumber = placeNumber;
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

    @Override
    public String toString() {
        return "TableRestaurant{" +
                "idTable=" + idTable +
                ", name='" + name + '\'' +
                ", placeNumber=" + placeNumber +
                '}';
    }
}
