package server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

/**
 * Created by ileossa on 05/04/2017.
 */
@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ROOM")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int number;
    private int price;

    @JoinColumn(name = "NAME_BUILDING")
    @ManyToOne
    private Building idBuilding;

    @JoinColumn(name = "CATEGORY_ID_ROOM")
    @ManyToOne
    private CategoryRoom categoryRoom;


    public Room() {
    }

}
