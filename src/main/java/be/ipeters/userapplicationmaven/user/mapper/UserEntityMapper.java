package be.ipeters.userapplicationmaven.user.mapper;

/* carlpeters created on 28/07/2025 inside the package - com.dotcapital.user.mapper */

import be.ipeters.userapplicationmaven.user.entities.UserJpaEntity;
import be.ipeters.userapplicationmaven.user.enumer.UserLanguage;
import be.ipeters.userapplicationmaven.user.model.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("UserEntityMapper")
@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    /**
     * Maps a User model to a UserJpaEntity
     * Ensures all fields are properly mapped
     * 
     * @param user the User model to map
     * @return the mapped UserJpaEntity
     */
//    @Mapping(target = "userEmail", source = "userEmail")
//    @Mapping(target = "userFirstName", source = "userFirstName")
//    @Mapping(target = "userLastName", source = "userLastName")
//    @Mapping(target = "userFullName", source = "userFullName")
//    @Mapping(target = "userLanguage", source = "userLanguage", qualifiedByName = "stringToUserLanguage")
    UserJpaEntity modelToJpaEntity(User user);
    
    /**
     * Maps a UserJpaEntity to a User model
     * Ensures all fields are properly mapped
     * 
     * @param userJpaEntity the UserJpaEntity to map
     * @return the mapped User model
     */
//    @Mapping(target = "userEmail", source = "userEmail")
//    @Mapping(target = "userFirstName", source = "userFirstName")
//    @Mapping(target = "userLastName", source = "userLastName")
//    @Mapping(target = "userFullName", source = "userFullName")
//    @Mapping(target = "userLanguage", source = "userLanguage", qualifiedByName = "userLanguageToString")
    User jpaEntityToModel(UserJpaEntity userJpaEntity);

    void updateJpaEntityFromModel(User user, @MappingTarget UserJpaEntity existing);
    /**
     * Converts a String to UserLanguage enum
     */
    @Named("stringToUserLanguage")
    default UserLanguage stringToUserLanguage(String value) {
        if (value == null) {
            return null;
        }
        try {
            return UserLanguage.valueOf(value);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    /**
     * Converts a UserLanguage enum to String
     */
    @Named("userLanguageToString")
    default String userLanguageToString(UserLanguage value) {
        return value != null ? value.name() : null;
    }


    // Custom mapping methods removed as they're no longer needed
    // MapStruct is now correctly mapping all fields including MoralCustomerId
}
