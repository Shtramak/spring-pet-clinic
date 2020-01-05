package com.shtramak.springpetclinic.service.map;

import com.shtramak.springpetclinic.model.Visit;
import com.shtramak.springpetclinic.service.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class VisitServiceMap extends AbstractMapService<Visit, Long> implements VisitService {
}
