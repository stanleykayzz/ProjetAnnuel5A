package server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by ileossa on 05/04/2017.
 */
@Entity
@Builder
@AllArgsConstructor
@Getter
@Setter
@Table(name = "RESTAURANT")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String cateringService;
    private int nbClients;

    public Restaurant() {
    }



    public int getId() {
        return id;
    }

    public String getCateringService() {
        return cateringService;
    }

    public void setCateringService(String cateringService) {
        this.cateringService = cateringService;
    }


    public int getNbClients() {
        return nbClients;
    }

    public void setNbClients(int nbClients) {
        this.nbClients = nbClients;
    }

    public Restaurant check(Object o) {
        if (this == o) return (Restaurant) o;
        if (o == null || getClass() != o.getClass()) return (Restaurant) o;

        Restaurant that = (Restaurant) o;

        if (nbClients != that.nbClients) setNbClients(that.nbClients);
        if (cateringService.equals(that.cateringService)) setCateringService(that.cateringService);
        return this;
    }


}
