package com.shtramak.springpetclinic.repository;

import com.shtramak.springpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
