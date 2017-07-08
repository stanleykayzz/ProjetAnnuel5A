package server.model;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

/**
 * Created by ileossa on 05/04/2017.
 */
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_room")
    private int idRoom;

    private String name;
    private String building;
    private int number;
    private int idClient;
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

    public Room(String name, String building, int number, int idClient, int price, Building idBuilding) {
        this.name = name;
        this.building = building;
        this.number = number;
        this.idClient = idClient;
        this.price = price;
        this.idBuilding = idBuilding;
    }

    public CategoryRoom getCategoryRoom() {
        return categoryRoom;
    }

    public void setCategoryRoom(CategoryRoom categoryRoom) {
        this.categoryRoom = categoryRoom;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
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

    @Override
    public String toString() {
        return "Room{" +
                "idRoom=" + idRoom +
                ", name='" + name + '\'' +
                ", building='" + building + '\'' +
                ", number=" + number +
                ", idClient=" + idClient +
                ", costByNight=" + price +
                '}';
    }
}
