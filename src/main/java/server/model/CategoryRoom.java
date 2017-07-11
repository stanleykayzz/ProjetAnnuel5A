package server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.AUTO;

/**
 * Created by ileossa on 02/07/2017.
 */
@Builder
@AllArgsConstructor
@Entity
@Table(name = "CATEGORY_NAME")
public class CategoryRoom {

    @Id
    @GeneratedValue(strategy = AUTO)
    private int id;

    private String name;
    private double costByNight;
    private String description;
    private String picturePath;


    public CategoryRoom() {
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
