package com.shtramak.springpetclinic.service.map;

import com.shtramak.springpetclinic.model.Vet;
import com.shtramak.springpetclinic.service.VetService;
import org.springframework.stereotype.Service;

@Service
public class VetServiceMap extends AbstractMapService<Vet,Long>  implements VetService {
    @Override
    public Vet save(Vet vet) {
        return super.saveOrUpdate(vet.getId(), vet);
    }
}
