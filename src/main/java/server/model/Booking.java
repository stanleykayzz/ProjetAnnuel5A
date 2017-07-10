package server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import server.model.Enum.Reason;
import server.model.Enum.Statut;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by ileossa on 05/04/2017.
 */
@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "BOOKING")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = true)
    private Date dateBook;
    private Date dateStart;
    private Date dateEnd;
    private float price;
    private String payementMode;
    private String idClient;
    private boolean sendEvaluation;
    private int rate;
    private Reason reason;
    private Statut statut;

    @JoinColumn(name = "BOOKED_ROOMS")
    @ManyToMany
    private List<Room> rooms;

    @JoinColumn(name = "BOOKED_FESTIVE_ROOM")
    @ManyToMany
    private List<FestiveRoom> festiveRoomId;

    @JoinColumn(name = "BOOKED_SERVICES_HOTEL")
    @ManyToMany
    private List<ServicesHotel> serviceHotelId;

    @JoinColumn(name = "BOOKED_TABLE_RESTAURANT")
    @ManyToMany
    private List<Restaurant> tableRestaurantiD;

    @JoinColumn(name = "BOOKED_CLIENT_INFOS")
    @ManyToOne
    private Client clientInfos;



    //Use by interface repository
    public Booking() {
    }



    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

        if (id != booking.id) return false;
        if (Float.compare(booking.price, price) != 0) return false;
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

        if (id != booking.id) setId(booking.id);
        if (Float.compare(booking.price, price) != 0) setPrice(booking.price);
        if (idClient != booking.idClient) setIdClient(booking.idClient);
        if (dateBook != null ? !dateBook.equals(booking.dateBook) : booking.dateBook != null) setDateBook(booking.dateBook);
        if (dateStart != null ? !dateStart.equals(booking.dateStart) : booking.dateStart != null) setDateStart(booking.dateStart);
        if (dateEnd != null ? !dateEnd.equals(booking.dateEnd) : booking.dateEnd != null) setDateEnd(booking.dateEnd);
        if( payementMode != null ? payementMode.equals(booking.payementMode) : booking.payementMode != null) setPayementMode(booking.payementMode);
        return this;
    }


}
