package com.shtramak.springpetclinic.service.map;

import com.shtramak.springpetclinic.model.Speciality;
import com.shtramak.springpetclinic.service.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class SpecialityServiceMap extends AbstractMapService<Speciality, Long> implements SpecialityService {
}
