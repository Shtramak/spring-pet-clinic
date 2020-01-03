package com.shtramak.springpetclinic.service.datajpa;

import com.shtramak.springpetclinic.model.Pet;
import com.shtramak.springpetclinic.repository.PetRepository;
import com.shtramak.springpetclinic.service.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("spring-data")
public class PetServiceJpa implements PetService {
    private final PetRepository repository;

    public PetServiceJpa(PetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> pets = new HashSet<>();
        repository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Optional<Pet> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Pet save(Pet pet) {
        return repository.save(pet);
    }

    @Override
    public void delete(Pet pet) {
        repository.delete(pet);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
