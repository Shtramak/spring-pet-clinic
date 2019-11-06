package com.shtramak.springpetclinic.service;

import com.shtramak.springpetclinic.model.Pet;

import java.util.Optional;
import java.util.Set;

public interface PetService {
    Optional<Pet> findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
