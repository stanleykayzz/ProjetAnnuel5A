package server.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
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
    private Date birsthday;

    @Column(name = "Email", unique = true)
    @NotEmpty(message = "A person must have an Email")
    private String email;

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
}
