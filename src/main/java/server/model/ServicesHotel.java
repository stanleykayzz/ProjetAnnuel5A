package server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by ileossa on 18/06/2017.
 */
@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "services_hotel")
public class ServicesHotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private float price;
    private int quantity;


    public ServicesHotel() {
    }


}
