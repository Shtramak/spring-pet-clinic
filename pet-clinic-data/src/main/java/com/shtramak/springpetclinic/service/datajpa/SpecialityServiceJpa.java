package com.shtramak.springpetclinic.service.datajpa;

import com.shtramak.springpetclinic.model.Speciality;
import com.shtramak.springpetclinic.repository.SpecialityRepository;
import com.shtramak.springpetclinic.service.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("spring-data")
public class SpecialityServiceJpa implements SpecialityService {
    private final SpecialityRepository repository;

    public SpecialityServiceJpa(SpecialityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialities = new HashSet<>();
        repository.findAll().forEach(specialities::add);
        return specialities;
    }

    @Override
    public Optional<Speciality> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Speciality save(Speciality speciality) {
        return repository.save(speciality);
    }

    @Override
    public void delete(Speciality speciality) {
        repository.delete(speciality);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
