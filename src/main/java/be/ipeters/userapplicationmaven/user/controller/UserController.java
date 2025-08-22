package be.ipeters.userapplicationmaven.user.controller;

import be.ipeters.userapplicationmaven.user.mapper.UserEntityMapper;
import be.ipeters.userapplicationmaven.user.model.User;
import be.ipeters.userapplicationmaven.user.services.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.constraints.Min;
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

/* carlpeters created on 07/04/2025 inside the package - com.dotcapital.user.controller */
@Transactional
@RestController
@RequestMapping(value="/users")
@Validated
@Slf4j
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserEntityMapper userEntityMapper;



    @PostMapping(value="/create")
    public ResponseEntity<User> create(@Validated @RequestBody User user) {
        log.debug("create user or re-activate user  {}", user);
        // Check if the user already exists by actorId
//        boolean isExistingUser = CheckExistingUser(user);
        User savedUser = userService.save(user);
        log.debug("saved user {}", savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    private boolean CheckExistingUser(User user) {

        return userService.findByActorId(user.getActorId()).size() == 1;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> getAll() {
        log.debug("get all users");
        List<User> users = userService.findAll();
        if (users.isEmpty()) {
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> findById(   @PathVariable @Min(value = 1, message = "Id must be greater than 0") Long id) {
        log.debug("findById {}", id);
            return ResponseEntity.ok(this.userService.findById(id));
        }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @Min(value = 1, message = "Id must be greater than 0") Long id) {
        log.debug("deleteById {}", id);
        this.userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="/put/{id}") //replace completely
    public ResponseEntity<User> update(
            @PathVariable(name="id") Long id,
            @RequestBody User updateUserRequest) {
        log.debug("update user {}", updateUserRequest);
        var existing = this.userService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        // Ensure the update applies to the existing record rather than creating a new one
        updateUserRequest.setEntityId(id);
        return ResponseEntity.ok(userService.save(updateUserRequest));
    }


    @PatchMapping(value = "/{id}")
    public ResponseEntity<User> partiallyUpdateUser(@PathVariable("id") Long id, @RequestBody User user) {
        log.debug("Partially updating user with id {}: {}", id, user);
        User existingUser = userService.findById(id);

        // Update only non-null fields
        if (user.getUserStatus() != null) {
            existingUser.setUserStatus(user.getUserStatus());
        }
        if(user.getUserIdentifier() != null) {
            existingUser.setUserIdentifier(user.getUserIdentifier());
        }
        if(user.getUserEmail() != null) {
            existingUser.setUserEmail(user.getUserEmail());
        }
        if(user.getActorId() != null) {
            existingUser.setActorId(user.getActorId());
        }
        // Ignore client-provided version; JPA manages it via @Version
        if(user.getFinalized()!= null) {
            existingUser.setFinalized(user.getFinalized());
        }
        if(user.getUserFirstName() != null) {
            existingUser.setUserFirstName(user.getUserFirstName());
        }
        if(user.getUserLastName() != null) {
            existingUser.setUserLastName(user.getUserLastName());
        }
        if(user.getUserFullName() != null) {
            existingUser.setUserFullName(user.getUserFullName());
        }
        if(user.getUserStatus() != null) {
            existingUser.setUserStatus(user.getUserStatus());
        }
        if(user.getMoralCustomerId() != null) {
            existingUser.setMoralCustomerId(user.getMoralCustomerId());
        }

        if (user.getUserLanguage() != null) {
            existingUser.setUserLanguage(user.getUserLanguage());
        }

        User updatedUser = userService.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }



}
