package com.shtramak.springpetclinic.service.map;

import com.shtramak.springpetclinic.model.Pet;
import com.shtramak.springpetclinic.service.PetService;
import org.springframework.stereotype.Service;

@Service
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
    @Override
    public Pet save(Pet pet) {
        super.saveOrUpdate(pet.getId(), pet);
        return pet;
    }
}
