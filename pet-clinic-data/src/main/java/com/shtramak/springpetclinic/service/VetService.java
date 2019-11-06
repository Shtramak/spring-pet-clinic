package com.shtramak.springpetclinic.service;

import com.shtramak.springpetclinic.model.Vet;

import java.util.Optional;
import java.util.Set;

public interface VetService {
    Optional<Vet> findById(Long id);

    Vet save(Vet pet);

    Set<Vet> findAll();
}
