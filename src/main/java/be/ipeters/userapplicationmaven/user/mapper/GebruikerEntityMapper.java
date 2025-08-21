package be.ipeters.userapplicationmaven.user.mapper;

import be.ipeters.userapplicationmaven.user.entities.GebruikerJpaEntity;
import be.ipeters.userapplicationmaven.user.model.Gebruiker;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("GebruikerEntityMapper")
@Mapper(componentModel = "spring")
public interface GebruikerEntityMapper {
    // Define mapping methods here, e.g.:
     GebruikerJpaEntity modelToJpaEntity(Gebruiker gebruiker);
     Gebruiker jpaEntityToModel(GebruikerJpaEntity gebruikerJpaEntity);

    void updateJpaEntityFromModel(Gebruiker gebruiker, @MappingTarget GebruikerJpaEntity gebruikerJpaEntity);


    // Add any additional mapping methods as needed
}
