package com.shtramak.springpetclinic.service.map;

import com.shtramak.springpetclinic.model.Vet;
import com.shtramak.springpetclinic.service.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default","map"})
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
}
