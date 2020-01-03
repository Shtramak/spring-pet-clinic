package com.shtramak.springpetclinic.service.datajpa;

import com.shtramak.springpetclinic.model.PetType;
import com.shtramak.springpetclinic.repository.PetTypeRepository;
import com.shtramak.springpetclinic.service.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("spring-data")
public class PetTypeServiceJpa implements PetTypeService {
    private final PetTypeRepository repository;

    public PetTypeServiceJpa(PetTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<PetType> findAll() {
        HashSet<PetType> petTypes = new HashSet<>();
        repository.findAll().forEach(petTypes::add);
        return petTypes;
    }

    @Override
    public Optional<PetType> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public PetType save(PetType petType) {
        return repository.save(petType);
    }

    @Override
    public void delete(PetType petType) {
        repository.delete(petType);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
