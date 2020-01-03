package com.shtramak.springpetclinic.service.datajpa;

import com.shtramak.springpetclinic.model.Vet;
import com.shtramak.springpetclinic.repository.VetRepository;
import com.shtramak.springpetclinic.service.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("spring-data")
public class VetServiceJpa implements VetService {
    private final VetRepository repository;

    public VetServiceJpa(VetRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Vet> findAll() {
        HashSet<Vet> vets = new HashSet<>();
        repository.findAll().forEach(vets::add);
        return vets;
    }

    @Override
    public Optional<Vet> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Vet save(Vet vet) {
        return repository.save(vet);
    }

    @Override
    public void delete(Vet vet) {
        repository.delete(vet);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
