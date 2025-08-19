package be.ipeters.userapplicationmaven.user.entities;

import be.ipeters.userapplicationmaven.user.enumer.UserLanguage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Table(name = "GEBRUIKERS")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class UserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long entityId;
    @Column(unique = true)
    private String journeyId;
    @Version
    private Long version;
    @Column
    private Boolean finalized;
    @Column
    private String actorId;
    //    @BusinessKey
    @Column
    private String userIdentifier;
    @Column
    private String userStatus;
//    @Column
//    @Enumerated(EnumType.STRING)
//    private UserCategory userCategory;
    @Column
    private String userLastName;
    @Column
    private String userFirstName;
    @Column
    private String userFullName;
    @Column
    @Enumerated(EnumType.STRING)
    private UserLanguage userLanguage;
    @Column
    private String userEmail;
    @Column
    private String MoralCustomerId;

//    @Column
//    private String userEmailValidated;
//    @Column
//    private LocalDate userBirthDate;
//    @Column
//    private String userBirthPlace;
    //    @lombok.ToString.Exclude
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    private UserContactInformationEntity userContactInformation;
//    @Column
//    private String password;
//    @Column
//    private Boolean skipTwoFactorAuthentication;
//    @Column
//    private String activeOrganisationIdentifier;
    //    @Column(length = 17, scale = 10, precision = 38)
//    private BigDecimal numberFailedLoginAttempts;
//    @Column(columnDefinition = "datetime2")
//    private ZonedDateTime lastSuccessfulLoginTimestamp;
//    @Column(columnDefinition = "datetime2")
//    private ZonedDateTime userDisableTimestamp;
//    @Column
//    private Boolean userLoggedIn;
//    @Column(columnDefinition = "datetime2")
//    private ZonedDateTime creationDateTime;

//    private final static long serialVersionUID = -8815982300088333701L;

}