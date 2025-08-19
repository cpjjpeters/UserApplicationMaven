package be.ipeters.userapplicationmaven.user.services;

import be.ipeters.userapplicationmaven.user.model.User;

import java.util.List;

/* carlpeters created on 14/08/2025 inside the package - be.ipeters.userapplicationmaven.user.services */
public interface UserService {
    /**
     * Save or update a user
     * 
     * @param user the user to save
     * @param isExistingUser whether the user already exists
     * @return the saved user
     */
//    User save(User user, boolean isExistingUser);

    User save(User user);
    List<User> findAll();
    List<User> findByActorId(String actorId);
    void deleteById(Long id);
    User findById(Long id);
}
