package tr.gov.gib.taskman.dao.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import tr.gov.gib.taskman.dao.model.common.BaseModel;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Users",uniqueConstraints = {
        @UniqueConstraint(name = "UniqueUsername", columnNames = {"username"}),
        @UniqueConstraint(name = "UniqueEmail", columnNames = {"email"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Where(clause = "isdeleted='false'")
@SQLDelete(sql = "UPDATE Users SET isdeleted = true WHERE id = ? and version = ?")
public class User  extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(name = "Username",unique = true)
    private String username;
    @NonNull
    @Column(name = "Pwd")
    private String password;
    // private byte[] passwordSalt;
    @NonNull
    @Column(name = "Email",unique = true)
    private String email;
    @Column(name = "FirstName")
    private String firstname;
    @Column(name = "LastName")
    private String lastname;
    @Column(name = "PhoneNum")
    private String phonenum;
    @Column(name = "Picture")
    private byte[] picture;

    @Column(name = "ForgotPasswordGuid")
    private UUID forgotPasswordGuid;

    @Temporal(TemporalType.DATE)
    @Column(name = "ForgotPasswordValidDate")
    private Date forgotPasswordValidDate;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "USER_ROLES", joinColumns = {
            @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID") })
    private Collection<Role> roles;


    public User(Long id, String username, String password, String email, String firstname, String lastname,
                String phonenum) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenum = phonenum;
    }
}
