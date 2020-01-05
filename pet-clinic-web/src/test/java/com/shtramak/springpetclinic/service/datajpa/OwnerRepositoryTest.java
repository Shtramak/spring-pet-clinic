package com.shtramak.springpetclinic.service.datajpa;

import com.shtramak.springpetclinic.model.Owner;
import com.shtramak.springpetclinic.repository.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class OwnerRepositoryTest {
    @Autowired
    private OwnerRepository repository;

    @Test
    void findAllOwnersByPetName() {
        List<Owner> owners = repository.findAllOwnersByPetName("Doggy");
        assertFalse(owners.isEmpty());
        assertEquals(1, owners.size());
        assertEquals("John", owners.stream().map(Owner::getFirstName).findAny().orElseThrow());
    }

    @Test
    void findByLastName() {
        Optional<Owner> owner = repository.findByLastName("Poe");
        assertTrue(owner.isPresent());
    }
}
