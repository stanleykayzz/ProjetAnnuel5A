package server.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

/**
 * Created by ileossa on 02/07/2017.
 */
@Entity
@Table(name = "category_room")
public class CategoryRoom {

    @Id
    @GeneratedValue(strategy = AUTO)
    private int idCategoryRoom;

    private String name;
    private double costByNight;
    private String description;
    private String picturePath;

    @OneToOne
    @JoinColumn(name = "id_room")
    private Room room;


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

    @OneToOne(mappedBy = "CategoryRoom")
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
