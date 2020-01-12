package com.shtramak.springpetclinic.service.datajpa;

import com.shtramak.springpetclinic.model.Owner;
import com.shtramak.springpetclinic.repository.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerServiceJpaTest {
    @Mock
    OwnerRepository repository;
    @InjectMocks
    OwnerServiceJpa service;
    @Captor
    ArgumentCaptor<Owner> captor;

    @Test
    void findByLastNameWhenOwnerExistsReturnsListWithOwners() {
        String lastName = "Doe";
        Owner owner = new Owner();
        owner.setLastName(lastName);
        List<Owner> owners = List.of(owner);
        when(repository.findAllByLastNameLike("%" + lastName + "%")).thenReturn(owners);
        List<Owner> result = service.findAllByLastNameLike(lastName);
        assertEquals(owners, result);
    }

    @Test
    void findByLastNameWhenOwnerNotExistsReturnsEmptyList() {
        List<Owner> emptyList = Collections.emptyList();
        when(repository.findAllByLastNameLike(any())).thenReturn(emptyList);
        List<Owner> result = service.findAllByLastNameLike("anyName");
        assertEquals(emptyList, result);
    }

    @Test
    void findByIdWhenOwnerExistsReturnsOptionalWithOwner() {
        Long id = 1L;
        Owner owner = new Owner();
        when(repository.findById(id)).thenReturn(Optional.of(owner));
        Optional<Owner> result = service.findById(id);
        verifyNoMoreInteractions(repository);
        assertEquals(owner, result.orElseThrow());
    }

    @Test
    void findByIdWhenOwnerNotExistsReturnsEmptyOptional() {
        when(repository.findById(any())).thenReturn(Optional.empty());
        Optional<Owner> result = service.findById(1L);
        verifyNoMoreInteractions(repository);
        assertEquals(Optional.empty(), result);
    }

    @Test
    void findAllWhenOwnersExistsReturnsSetOfOwners() {
        Set<Owner> owners = Stream.generate(Owner::new).limit(3).collect(Collectors.toSet());
        when(repository.findAll()).thenReturn(owners);
        Set<Owner> result = service.findAll();
        verifyNoMoreInteractions(repository);
        assertEquals(owners, result);
    }

    @Test
    void findAllWhenOwnersNotExistsReturnsEmptySet() {
        when(repository.findAll()).thenReturn(new HashSet<>());
        Set<Owner> result = service.findAll();
        verifyNoMoreInteractions(repository);
        assertTrue(result.isEmpty());
    }

    @Test
    void saveNewOwner() {
        Owner owner = new Owner();
        service.save(owner);
        verify(repository).save(captor.capture());
        verifyNoMoreInteractions(repository);
        assertEquals(owner, captor.getValue());
    }

    @Test
    void deleteOwner() {
        Owner owner = new Owner();
        service.delete(owner);
        verify(repository).delete(captor.capture());
        verifyNoMoreInteractions(repository);
        assertEquals(owner, captor.getValue());
    }

    @Test
    void deleteById() {
        Long id = 1L;
        service.deleteById(id);
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        verify(repository).deleteById(captor.capture());
        verifyNoMoreInteractions(repository);
        assertEquals(id, captor.getValue());
    }
}
