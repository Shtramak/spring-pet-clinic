package com.shtramak.springpetclinic.service.map;

import com.shtramak.springpetclinic.model.Owner;
import com.shtramak.springpetclinic.service.OwnerService;

import java.util.Optional;

public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    @Override
    public Optional<Owner> findByLastName(String lastName) {
        return map.values()
                .stream()
                .filter(owner -> owner.getLastName().equals(lastName))
                .findAny();
    }

    @Override
    public Owner save(Owner owner) {
        return super.saveOrUpdate(owner.getId(), owner);
    }
}
