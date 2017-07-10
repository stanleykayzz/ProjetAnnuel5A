package server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by ileossa on 03/07/2017.
 */
@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "building")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_building")
    private int idBuilding;

    private String nameBuild;

    public Building() {
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
