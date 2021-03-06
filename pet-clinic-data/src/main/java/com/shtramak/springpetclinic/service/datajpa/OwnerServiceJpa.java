package com.shtramak.springpetclinic.service.datajpa;

import com.shtramak.springpetclinic.model.Owner;
import com.shtramak.springpetclinic.repository.OwnerRepository;
import com.shtramak.springpetclinic.service.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("spring-data")
public class OwnerServiceJpa implements OwnerService {
    private final OwnerRepository repository;

    public OwnerServiceJpa(OwnerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return repository.findAllByLastNameLike("%" + lastName + "%");
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        repository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Owner save(Owner owner) {
        return repository.save(owner);
    }

    @Override
    public void delete(Owner owner) {
        repository.delete(owner);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
