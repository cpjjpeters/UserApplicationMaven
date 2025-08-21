package be.ipeters.userapplicationmaven.user.services;

import be.ipeters.userapplicationmaven.user.entities.GebruikerJpaEntity;
import be.ipeters.userapplicationmaven.user.mapper.GebruikerEntityMapper;
import be.ipeters.userapplicationmaven.user.model.Gebruiker;
import be.ipeters.userapplicationmaven.user.repository.GebruikerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
/* carlpeters created on 21/08/2025 inside the package - be.ipeters.userapplicationmaven.user.services */
@Slf4j
@Service
@RequiredArgsConstructor
public class GebruikerServiceImpl implements GebruikerService {

    private final GebruikerJpaRepository gebruikerJpaRepository;
    private final GebruikerEntityMapper gebruikerEntityMapper;

    @Override
    public Gebruiker save(Gebruiker gebruiker) {
        log.debug("Saving gebruiker: {}", gebruiker);
        if (gebruiker.getId() != null) {
            GebruikerJpaEntity existing = gebruikerJpaRepository.findById(gebruiker.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Gebruiker with id " + gebruiker.getId() + " does not exist."));
            gebruikerEntityMapper.updateJpaEntityFromModel(gebruiker, existing);   // update fields
            GebruikerJpaEntity savedEntity = gebruikerJpaRepository.save(existing);
            return gebruikerEntityMapper.jpaEntityToModel(savedEntity);
        } else {
            GebruikerJpaEntity newEntity = gebruikerEntityMapper.modelToJpaEntity(gebruiker);
            GebruikerJpaEntity savedEntity = gebruikerJpaRepository.save(newEntity);
            return gebruikerEntityMapper.jpaEntityToModel(savedEntity);
        }
    }

    @Override
    public List<Gebruiker> findAll() {
        log.debug("Finding all gebruikers");
        return gebruikerJpaRepository.findAll()
                .stream()
                .map(gebruikerEntityMapper::jpaEntityToModel)
                .toList();
    }


    @Override
    public void deleteById(Long id) {
        log.debug("Deleting gebruiker by id: {}", id);
        if (!gebruikerJpaRepository.existsById(id)) {
            throw new IllegalArgumentException("" + "Gebruiker with id " + id + " does not exist.");
        }
        gebruikerJpaRepository.deleteById(id);
        log.debug("Deleted gebruiker with id: {}", id);

    }

    @Override
    public Gebruiker findById(Long id) {
    log.debug("Finding gebruiker by id: {}", id);
        return gebruikerJpaRepository.findById(id)
                .map(gebruikerEntityMapper::jpaEntityToModel)
                .orElse(null);
    }
}
