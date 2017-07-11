package server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import server.model.Enum.AccreditationUers;
import server.model.Enum.ClientStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table (name = "CLIENT")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLIENT_ID")
    private int id;

    @Column(name = "NAME")
    @NotEmpty(message = "A client must have a name")
    private String name;

    @Column(name = "FIRSTNAME")
    @NotEmpty(message = "A client must have a first name")
    private String firstName;

    @Column(name = "SEXE")
    private int sexe;

    @Column(name = "BIRTHDAY")
    private Date birthday;

    @Column(name = "EMAIL", unique = true)
    @NotEmpty(message = "A person must have an Email")
    private String email;

    @Column(name = "PHONE")
    @NotEmpty(message = "A person must have an Phone")
    private String phone;

    @Column(name = "COUNTRY")
    @NotEmpty(message = "A person must have a Country")
    private String country;

    @Column(name = "CITY")
    @NotEmpty(message = "A person must have a City")
    private String city;

    @Column(name = "ADDRESSE")
    @NotEmpty(message = "A person must have an Address")
    private String address;

    @Column(name = "POSTAL_CODE")
    @NotEmpty(message = "A person must have an Postal_code")
    private String postalCode;

    @Column(name = "PASSWORD")
    @NotNull
    private String password;

    @Column(name = "STATUS")
    private int status;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "TOKEN_DATE")
    private Date tokenDate;

    @Column(name = "CODE")
    private String code;

    @Column(name = "ACCREDITATION")
    private String accreditation;

    @Column(name = "STATUT_ACTIF")
    private ClientStatus clientStatus;

}
