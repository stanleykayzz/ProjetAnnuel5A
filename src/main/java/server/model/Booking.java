package server.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ileossa on 05/04/2017.
 */
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idBook;

    @Column(nullable = true)
    private Date dateBook;
    private Date dateStart;
    private Date dateEnd;
    private int peopleNumber;
    private float price;
    private String payementMode;
    private int idPartyRoom;
    private int idClient;

    //Use by interface repository
    public Booking() {
    }


    public Booking(Date dateBook, Date dateStart, Date dateEnd, int peopleNumber, float price, String payementMode, int idPartyRoom, int idClient) {
        this.dateBook = dateBook;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.peopleNumber = peopleNumber;
        this.price = price;
        this.payementMode = payementMode;
        this.idPartyRoom = idPartyRoom;
        this.idClient = idClient;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public Date getDateBook() {
        return dateBook;
    }

    public void setDateBook(Date dateBook) {
        this.dateBook = dateBook;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(int peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPayementMode() {
        return payementMode;
    }

    public void setPayementMode(String payementMode) {
        this.payementMode = payementMode;
    }

    public int getIdPartyRoom() {
        return idPartyRoom;
    }

    public void setIdPartyRoom(int idPartyRoom) {
        this.idPartyRoom = idPartyRoom;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }


    @Override
    public String toString() {
        return "Booking{" +
                "idBook=" + idBook +
                ", dateBook=" + dateBook +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", peopleNumber=" + peopleNumber +
                ", price=" + price +
                ", payementMode='" + payementMode + '\'' +
                ", idPartyRoom=" + idPartyRoom +
                ", idClient=" + idClient +
                '}';
    }
}
