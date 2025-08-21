package be.ipeters.userapplicationmaven.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/* carlpeters created on 14/08/2025 inside the package - be.ipeters.userapplicationmaven.user.model */
@Data
@NoArgsConstructor
public class Gebruiker {
    private Long id;
    private String name;
    private String email;

}
