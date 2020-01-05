package com.shtramak.springpetclinic.service.map;

import com.shtramak.springpetclinic.model.PetType;
import com.shtramak.springpetclinic.service.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService {
}
