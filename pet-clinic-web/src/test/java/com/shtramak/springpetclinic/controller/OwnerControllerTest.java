package com.shtramak.springpetclinic.controller;

import com.shtramak.springpetclinic.model.Owner;
import com.shtramak.springpetclinic.service.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
    public static final String CREATE_OR_UPDATE_OWNER_FORM = "createOrUpdateOwnerForm";
    @Mock
    private OwnerService service;

    @InjectMocks
    private OwnerController controller;

    @Captor
    ArgumentCaptor<Owner> captor;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
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
                .andExpect(model().attribute("owner", equalTo(owner)));
    }

    @Test
    void initFindFirst() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"));

        verifyZeroInteractions(service);
    }

    @Test
    void listOwners() throws Exception {
        Set<Owner> owners = Stream.generate(Owner::new).limit(3).collect(toSet());
        when(service.findAll()).thenReturn(owners);
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("owners", equalTo(owners)));
    }

    @Test
    void processFindFormReturnMany() throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setLastName("lastName");
        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setLastName("lastName");
        when(service.findAllByLastNameLike(anyString())).thenReturn(List.of(owner1, owner2));
        mockMvc.perform(get("/owners").flashAttr("owner", owner1))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {
        long id = 1L;
        Owner owner = new Owner();
        owner.setId(id);
        when(service.findAllByLastNameLike(anyString())).thenReturn(List.of(owner));

        mockMvc.perform(get("/owners").param("lastName", "lastName"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/" + id));
    }

    @Test
    void initCreateFormReturnsFromViewName() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/" + CREATE_OR_UPDATE_OWNER_FORM))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void createNewOwnerOnSubmit() throws Exception {
        long savedId = 1L;
        Owner owner = new Owner();
        owner.setFirstName("John");
        owner.setLastName("Doe");
        owner.setId(savedId);
        when(service.save(any())).thenReturn(owner);
        mockMvc.perform(post("/owners/new").flashAttr("owner", owner))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/" + savedId))
                .andExpect(model().attribute("owner", equalTo(owner)));
        verify(service, times(1)).save(any());
        verifyNoMoreInteractions(service);
    }

    @Test
    void initUpdateFormReturnsFormViewName() throws Exception {
        Long id = 1L;
        Owner savedOwner = new Owner();
        savedOwner.setId(id);
        when(service.findById(id)).thenReturn(Optional.of(savedOwner));
        mockMvc.perform(get(String.format("/owners/%d/edit", id)))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/createOrUpdateOwnerForm"))
                .andExpect(model().attribute("owner", equalTo(savedOwner)));
        verify(service, times(1)).findById(id);
        verifyNoMoreInteractions(service);
    }

    @Test
    void updateUpdateOwnerOnSubmit() throws Exception {
        Long id = 1L;
        Owner owner = new Owner();
        owner.setFirstName("John");
        owner.setLastName("Doe");
        owner.setId(id);
        when(service.save(any())).thenReturn(owner);
        mockMvc.perform(post(String.format("/owners/%d/edit", id)).flashAttr("owner", owner))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/" + id))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attribute("owner", equalTo(owner)));
        verify(service, times(1)).save(owner);
        verifyNoMoreInteractions(service);
    }
}
