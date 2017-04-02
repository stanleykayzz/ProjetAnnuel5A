package server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "client")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_client")
    private Long clientId;

    @Column(name = "Name")
    @NotEmpty(message = "A client must have a name")
    private String name;

    @Column(name = "Firstname")
    @NotEmpty(message = "A client must have a first name")
    private String firstName;

    @Column(name = "Birthday")
    private Date birthday;

    @Column(name = "Email", unique = true)
    @NotEmpty(message = "A person must have an Email")
    private String email;

    @Column(name = "Phone")
    @NotEmpty(message = "A person must have an Phone")
    private String phone;

    @Column(name = "Country")
    @NotEmpty(message = "A person must have a Country")
    private String country;

    @Column(name = "City")
    @NotEmpty(message = "A person must have a City")
    private String city;

    @Column(name = "Address")
    @NotEmpty(message = "A person must have an Address")
    private String address;

    @Column(name = "Postal_code")
    @NotEmpty(message = "A person must have an Postal_code")
    private String postalCode;

    @Column(name = "Password")
    @NotNull
    private String password;

    @Column(name = "Token")
    private String token;

    @Column(name = "Token_date")
    private Date tokenDate;


    //--------- GETTERS / SETTERS ---------\\


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenDate() {
        return tokenDate;
    }

    public void setTokenDate(Date tokenDate) {
        this.tokenDate = tokenDate;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
