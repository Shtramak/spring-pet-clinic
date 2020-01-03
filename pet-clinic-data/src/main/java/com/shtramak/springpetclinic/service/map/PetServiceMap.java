package com.shtramak.springpetclinic.service.map;

import com.shtramak.springpetclinic.model.Pet;
import com.shtramak.springpetclinic.service.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default","map"})
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
}
