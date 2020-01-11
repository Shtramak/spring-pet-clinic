package com.shtramak.springpetclinic.service;

import com.shtramak.springpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {
    List<Owner> findAllByLastName(String lastName);
}
