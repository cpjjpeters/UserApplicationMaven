package be.ipeters.userapplicationmaven.user.repository;

import be.ipeters.userapplicationmaven.user.entities.UserJpaEntity;
import be.ipeters.userapplicationmaven.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {
    List<UserJpaEntity> findByActorId(String actorId);
}
