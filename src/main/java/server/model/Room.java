package server.model;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

/**
 * Created by ileossa on 05/04/2017.
 */
@Entity
@Table(name = "ROOM")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private int number;
    private int price;
    private int idCategory;

    @OneToOne
    @JoinColumn(name = "NAME_BUILDING")
    private Building idBuilding;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "CATEGORY_ID_ROOM")
    private CategoryRoom categoryRoom;


    public Room() {
    }

    public Room(String name, int number, int price, int idCategory, Building idBuilding, CategoryRoom categoryRoom) {
        this.name = name;
        this.number = number;
        this.price = price;
        this.idCategory = idCategory;
        this.idBuilding = idBuilding;
        this.categoryRoom = categoryRoom;
    }

    public CategoryRoom getCategoryRoom() {
        return categoryRoom;
    }

    public void setCategoryRoom(CategoryRoom categoryRoom) {
        this.categoryRoom = categoryRoom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Building getIdBuilding() {
        return idBuilding;
    }

    public void setIdBuilding(Building idBuilding) {
        this.idBuilding = idBuilding;
    }


}
