package be.ipeters.userapplicationmaven.user.mapper;

/* carlpeters created on 28/07/2025 inside the package - com.dotcapital.user.mapper */

import be.ipeters.userapplicationmaven.user.entities.UserJpaEntity;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("UserEntityMapper")
@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    UserJpaEntity modelToJpaEntity(be.ipeters.userapplicationmaven.user.model.User user);
    be.ipeters.userapplicationmaven.user.model.User jpaEntityToModel(UserJpaEntity userJpaEntity);
}
