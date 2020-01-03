package com.shtramak.springpetclinic.repository;

import com.shtramak.springpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Optional<Owner> findByLastName(String lastName);

    List<Owner> findAllByLastName(String lastName);
}
