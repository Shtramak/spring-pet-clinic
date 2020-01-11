package com.shtramak.springpetclinic.controller;

import com.shtramak.springpetclinic.model.Owner;
import com.shtramak.springpetclinic.service.OwnerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
    @Mock
    private OwnerService service;

    @InjectMocks
    private OwnerController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void listOwners() throws Exception {
        Set<Owner> owners = Stream.generate(Owner::new).limit(3).collect(toSet());
        when(service.findAll()).thenReturn(owners);
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", Matchers.equalTo(owners)));
    }

    @Test
    void ownerDetail() throws Exception {
        long id = 1L;
        Owner owner = new Owner();
        owner.setId(id);
        Optional<Owner> optOwner = Optional.of(owner);
        when(service.findById(id)).thenReturn(optOwner);
        mockMvc.perform(get("/owners/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownerDetails"))
                .andExpect(model().attribute("owner", Matchers.equalTo(owner)));
    }
}
