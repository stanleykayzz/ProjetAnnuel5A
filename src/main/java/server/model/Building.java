package server.model;

import javax.persistence.*;

/**
 * Created by ileossa on 03/07/2017.
 */
@Entity
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "NAME_BUILDING")
    private int idBuilding;


    private String nameBuild;

    public Building() {
    }

    public Building(String nameBuild) {
        this.nameBuild = nameBuild;
    }

    public String getNameBuild() {
        return nameBuild;
    }

    public void setNameBuild(String nameBuild) {
        this.nameBuild = nameBuild;
    }

    public int getIdBuilding() {
        return idBuilding;
    }

    public void setIdBuilding(int idBuilding) {
        this.idBuilding = idBuilding;
    }
}
