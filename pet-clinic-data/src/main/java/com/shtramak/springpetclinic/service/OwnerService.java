package com.shtramak.springpetclinic.service;

import com.shtramak.springpetclinic.model.Owner;

import java.util.Optional;
import java.util.Set;

public interface OwnerService {
    Optional<Owner> findByLastName(String lastName);

    Optional<Owner> findById(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();
}
