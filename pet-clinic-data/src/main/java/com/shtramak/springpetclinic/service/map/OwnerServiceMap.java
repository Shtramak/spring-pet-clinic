package com.shtramak.springpetclinic.service.map;

import com.shtramak.springpetclinic.model.Owner;
import com.shtramak.springpetclinic.service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {
    @Override
    public Optional<Owner> findByLastName(String lastName) {
        return map.values()
                .stream()
                .filter(owner -> owner.getLastName().equals(lastName))
                .findAny();
    }
}
