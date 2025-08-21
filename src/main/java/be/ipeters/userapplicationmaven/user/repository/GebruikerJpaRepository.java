package be.ipeters.userapplicationmaven.user.repository;

import aj.org.objectweb.asm.commons.Remapper;
import be.ipeters.userapplicationmaven.user.entities.GebruikerJpaEntity;
import be.ipeters.userapplicationmaven.user.entities.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GebruikerJpaRepository extends JpaRepository<GebruikerJpaEntity, Long> {
}
