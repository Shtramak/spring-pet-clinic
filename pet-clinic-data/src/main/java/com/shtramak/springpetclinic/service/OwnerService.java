package com.shtramak.springpetclinic.service;

import com.shtramak.springpetclinic.model.Owner;

import java.util.Optional;

public interface OwnerService extends CrudService<Owner, Long> {
    Optional<Owner> findByLastName(String lastName);
}
