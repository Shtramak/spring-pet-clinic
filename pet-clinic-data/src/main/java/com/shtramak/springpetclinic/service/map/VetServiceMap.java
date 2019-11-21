package com.shtramak.springpetclinic.service.map;

import com.shtramak.springpetclinic.model.Vet;
import com.shtramak.springpetclinic.service.VetService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
    @PostConstruct
    private void init() {
        map = new ConcurrentHashMap<>();
    }
}
