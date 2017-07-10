package server.model;

import lombok.Getter;
import lombok.Setter;
import server.model.Enum.Reason;
import server.model.Enum.Statut;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ileossa on 05/04/2017.
 */
@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idBook;

    @Column(nullable = true)
    private Date dateBook;
    private Date dateStart;
    private Date dateEnd;
    private float price;
    private String payementMode;
    private int idPartyRoom;
    private String idClient;
    private boolean sendEvaluation;
    private int idRoomForClient;
    private int rate;
    private Reason reason;
    private Statut statut;

    @JoinColumn(name = "ROOM_ID")
    private int roomId;

    @JoinColumn(name = "FESTIVE_ROOM_ID")
    private int festiveRoomId;

    @JoinColumn(name = "SERVICES_ID")
    private int serviceHotelId;

    @JoinColumn(name = "TABLE_RESTAURANT_ID")
    private int tableRestaurantiD;



    //Use by interface repository
    public Booking() {
    }

    public Booking(Date dateBook, Date dateStart, Date dateEnd, float price, String payementMode, int idPartyRoom, String idClient, Reason reason, Statut statut) {
        this.dateBook = dateBook;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.price = price;
        this.payementMode = payementMode;
        this.idPartyRoom = idPartyRoom;
        this.idClient = idClient;
        this.reason = reason;
        this.statut = statut;
        this.idRoomForClient = -1;
        this.sendEvaluation = true;
        this.rate = -1;
    }

    public Booking(Date dateBook, Date dateStart, Date dateEnd, float price, String payementMode, int idPartyRoom, String idClient, int idRoomForClient, Reason reason, Statut statut) {
        this.dateBook = dateBook;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.price = price;
        this.payementMode = payementMode;
        this.idPartyRoom = idPartyRoom;
        this.idClient = idClient;
        this.idRoomForClient = idRoomForClient;
        this.reason = reason;
        this.statut = statut;
        this.sendEvaluation = true;
        this.rate = -1;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
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

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }

    public boolean isSendEvaluation() {
        return sendEvaluation;
    }

    public void setSendEvaluation(boolean sendEvaluation) {
        this.sendEvaluation = sendEvaluation;
    }

    public int getIdRoomForClient() {
        return idRoomForClient;
    }

    public void setIdRoomForClient(int idRoomForClient) {
        this.idRoomForClient = idRoomForClient;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Booking booking = (Booking) o;

        if (idBook != booking.idBook) return false;
        if (Float.compare(booking.price, price) != 0) return false;
        if (idPartyRoom != booking.idPartyRoom) return false;
        if (idClient != booking.idClient) return false;
        if (dateBook != null ? !dateBook.equals(booking.dateBook) : booking.dateBook != null) return false;
        if (dateStart != null ? !dateStart.equals(booking.dateStart) : booking.dateStart != null) return false;
        if (dateEnd != null ? !dateEnd.equals(booking.dateEnd) : booking.dateEnd != null) return false;
        return payementMode != null ? payementMode.equals(booking.payementMode) : booking.payementMode == null;
    }


    public Booking check(Object o){
        if (this == o) return (Booking) o;
        if (o == null || getClass() != o.getClass()) return (Booking) o;

        Booking booking = (Booking) o;

        if (idBook != booking.idBook) setIdBook(booking.idBook);
        if (Float.compare(booking.price, price) != 0) setPrice(booking.price);
        if (idPartyRoom != booking.idPartyRoom) setIdPartyRoom(booking.idPartyRoom);
        if (idClient != booking.idClient) setIdClient(booking.idClient);
        if (dateBook != null ? !dateBook.equals(booking.dateBook) : booking.dateBook != null) setDateBook(booking.dateBook);
        if (dateStart != null ? !dateStart.equals(booking.dateStart) : booking.dateStart != null) setDateStart(booking.dateStart);
        if (dateEnd != null ? !dateEnd.equals(booking.dateEnd) : booking.dateEnd != null) setDateEnd(booking.dateEnd);
        if( payementMode != null ? payementMode.equals(booking.payementMode) : booking.payementMode != null) setPayementMode(booking.payementMode);
        return this;
    }


}
