package com.shtramak.springpetclinic.service.map;

import com.shtramak.springpetclinic.model.Vet;
import org.springframework.stereotype.Service;

@Service
public class VetServiceMap extends AbstractMapService<Vet,Long> {
    @Override
    public Vet save(Vet vet) {
        return super.saveOrUpdate(vet.getId(), vet);
    }
}
