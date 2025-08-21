package be.ipeters.userapplicationmaven.user.services;

import be.ipeters.userapplicationmaven.user.model.Gebruiker;

import java.util.List;

/* carlpeters created on 14/08/2025 inside the package - be.ipeters.userapplicationmaven.user.services */
public interface GebruikerService {


    Gebruiker save(Gebruiker gebruiker);
    List<Gebruiker> findAll();
    void deleteById(Long id);
    Gebruiker findById(Long id);
}
