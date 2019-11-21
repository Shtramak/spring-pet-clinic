package com.shtramak.springpetclinic.service.map;

import com.shtramak.springpetclinic.model.Pet;
import com.shtramak.springpetclinic.service.PetService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {
    @PostConstruct
    private void init() {
        map = new ConcurrentHashMap<>();
    }
}
