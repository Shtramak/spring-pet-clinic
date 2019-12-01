package com.shtramak.springpetclinic.service.map;

import com.shtramak.springpetclinic.model.PetType;
import com.shtramak.springpetclinic.service.PetTypeService;
import org.springframework.stereotype.Service;

@Service
public class PetTypeServiceImpl extends AbstractMapService<PetType, Long> implements PetTypeService {
}
