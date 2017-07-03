package server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

/**
 * Created by ileossa on 02/07/2017.
 */
@Entity
public class CategoryRoom {

    @Id
    @GeneratedValue(strategy = AUTO)
    int idCategoryRoom;

    String name;
    double costByNight;
    String description;
    String picturePath;


    public CategoryRoom() {
    }

    public CategoryRoom(String name, Long costByNight, String description, String picturePath) {
        this.name = name;
        this.costByNight = costByNight;
        this.description = description;
        this.picturePath = picturePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCostByNight() {
        return costByNight;
    }

    public void setCostByNight(double costByNight) {
        this.costByNight = costByNight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
