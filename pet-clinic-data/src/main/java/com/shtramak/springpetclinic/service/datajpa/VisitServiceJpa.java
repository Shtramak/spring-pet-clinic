package com.shtramak.springpetclinic.service.datajpa;

import com.shtramak.springpetclinic.model.Visit;
import com.shtramak.springpetclinic.repository.VisitRepository;
import com.shtramak.springpetclinic.service.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("spring-data")
public class VisitServiceJpa implements VisitService {
    private final VisitRepository repository;

    public VisitServiceJpa(VisitRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> visits = new HashSet<>();
        repository.findAll().forEach(visits::add);
        return visits;
    }

    @Override
    public Optional<Visit> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Visit save(Visit visit) {
        return repository.save(visit);
    }

    @Override
    public void delete(Visit visit) {
        repository.delete(visit);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
