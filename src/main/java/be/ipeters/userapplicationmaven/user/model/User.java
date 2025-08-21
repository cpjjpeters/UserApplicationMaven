package be.ipeters.userapplicationmaven.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/* carlpeters created on 14/08/2025 inside the package - be.ipeters.userapplicationmaven.user.model */
@Data
@NoArgsConstructor
public class User {
    private Long entityId;
    private String journeyId;
    private Long version;
    private Boolean finalized;
    private String actorId;
    private String userIdentifier;
    private String userStatus;
    private String userEmail;
    private String userFirstName;
    private String userLastName;
    private String userFullName;
    private String userLanguage;
    private String MoralCustomerId;


}
