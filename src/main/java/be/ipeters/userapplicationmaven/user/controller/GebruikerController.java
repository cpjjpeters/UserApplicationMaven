package be.ipeters.userapplicationmaven.user.controller;

import be.ipeters.userapplicationmaven.user.mapper.GebruikerEntityMapper;
import be.ipeters.userapplicationmaven.user.model.Gebruiker;
import be.ipeters.userapplicationmaven.user.services.GebruikerService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/* carlpeters created on 07/04/2025 inside the package - com.dotcapital.gebruiker.controller */
@Transactional
@RestController
@RequestMapping(value="/gebruikers")
@Validated
@Slf4j
@AllArgsConstructor
public class GebruikerController {

    private final GebruikerService gebruikerService;
    private final GebruikerEntityMapper gebruikerEntityMapper;



    @PostMapping(value="/save")
    public ResponseEntity<Gebruiker> save(@Validated @RequestBody Gebruiker gebruiker) {
        log.debug("create gebruiker or re-activate gebruiker  {}", gebruiker);
        // Check if the gebruiker already exists by actorId
//        boolean isExistingUser = CheckExistingUser(gebruiker);
        Gebruiker savedUser = gebruikerService.save(gebruiker);
        log.debug("saved gebruiker {}", savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

 

    @GetMapping(value = "/all")
    public ResponseEntity<List<Gebruiker>> getAll() {
        log.debug("get all gebruikers");
        List<Gebruiker> users = gebruikerService.findAll();
        if (users.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Gebruiker> findById(   @PathVariable @Min(value = 1, message = "Id must be greater than 0") Long id) {
        log.debug("findById {}", id);
            return ResponseEntity.ok(this.gebruikerService.findById(id));
        }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @Min(value = 1, message = "Id must be greater than 0") Long id) {
        log.debug("deleteById {}", id);
        this.gebruikerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="/put/{id}") //replace completely
    public ResponseEntity<Gebruiker> update(
            @PathVariable(name="id") Long id,
            @RequestBody Gebruiker updateUserRequest) {  // no id in request body, only in path variable!
        log.debug("update gebruiker {}", updateUserRequest);
        var gebruiker = this.gebruikerService.findById(id);
        if (gebruiker == null) {
            return ResponseEntity.notFound().build();
        }
//        gebruikerService.save(updateUserRequest);
        return ResponseEntity.ok(gebruikerService.save(updateUserRequest));

    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Gebruiker> partiallyUpdateUser(@PathVariable("id") Long id, @RequestBody Gebruiker gebruiker) {
        log.debug("Partially updating gebruiker with id {}: {}", id, gebruiker);
        gebruiker.setId(id);
        Gebruiker existingUser = gebruikerService.findById(gebruiker.getId());
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        // Update only non-null fields
        if (gebruiker.getId() != null) {
            existingUser.setId(gebruiker.getId() );
        }
        if(gebruiker.getName() != null) {
            existingUser.setName(gebruiker.getName());
        }
        if(gebruiker.getEmail() != null) {
            existingUser.setEmail(gebruiker.getEmail());
        }

        Gebruiker updatedUser = gebruikerService.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }



}
