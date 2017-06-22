package server.model;

import javax.persistence.*;

/**
 * Created by ileossa on 05/04/2017.
 */
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idRoom;
    private String name;
    private String building;
    private int number;
    private int idClient;



    public Room() {
    }

    public Room(String name, String building, int number, int idClient, Client client) {
        this.name = name;
        this.building = building;
        this.number = number;
        this.idClient = idClient;
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


    @Override
    public String toString() {
        return "Room{" +
                "idRoom=" + idRoom +
                ", name='" + name + '\'' +
                ", building='" + building + '\'' +
                ", number=" + number +
                ", idClient=" + idClient +
                '}';
    }
}