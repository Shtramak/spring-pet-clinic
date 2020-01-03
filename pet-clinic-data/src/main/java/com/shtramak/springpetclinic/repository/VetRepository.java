package com.shtramak.springpetclinic.repository;

import com.shtramak.springpetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
