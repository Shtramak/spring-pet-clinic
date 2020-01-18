package com.shtramak.springpetclinic.controller;

import com.shtramak.springpetclinic.model.Owner;
import com.shtramak.springpetclinic.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Controller
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {
    private static final String CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private static final String FIND_OWNERS = "owners/findOwners";
    private static final String OWNERS_LIST = "owners/ownersList";
    public static final String OWNER_DETAILS = "owners/ownerDetails";
    private final OwnerService service;

    @GetMapping
    public String listOwners(Owner owner, BindingResult result, Model model) {
        if (hasBlankLastName(owner)) {
            model.addAttribute("owners", service.findAll());
            return OWNERS_LIST;
        }

        List<Owner> results = service.findAllByLastNameLike(owner.getLastName());

        if (results.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("owners", results);
            return OWNERS_LIST;
        }
    }

    private boolean hasBlankLastName(Owner owner) {
        return owner.getLastName() == null || "".equals(owner.getLastName());
    }

    @GetMapping("/{id}")
    public ModelAndView ownerById(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView(OWNER_DETAILS);
        modelAndView.addObject(service.findById(id).orElseThrow(NoSuchElementException::new)); // todo Exception Handling
        return modelAndView;
    }

    @GetMapping("/find")
    public String initFindFirst(Model model) {
        model.addAttribute("owner", new Owner());
        return FIND_OWNERS;
    }

    @GetMapping("/new")
    public ModelAndView initCreateOrUpdate(Model model) {
        ModelAndView modelAndView = new ModelAndView(CREATE_OR_UPDATE_OWNER_FORM);
        modelAndView.addObject(new Owner());
        return modelAndView;
    }

    @PostMapping("/new")
    public String create(@Valid Owner owner, Model model) {
        Owner savedOwner = service.save(owner);
        model.addAttribute("owner", savedOwner);
        return "redirect:/owners/" + savedOwner.getId();
    }

    @GetMapping("/{id}/edit")
    public String initUpdateForm(@PathVariable("id") Long ownerId, Model model) {
        Owner savedOwner = service.findById(ownerId).orElseThrow();
        model.addAttribute("owner", savedOwner);
        return CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @Valid Owner owner, Model model) {
        if (!Objects.equals(id, owner.getId())) {
            throw new RuntimeException("Id of owner doesn't match with path variable");
        }
        Owner updatedOwner = service.save(owner);
        model.addAttribute("owner", updatedOwner);
        return "redirect:/owners/" + id;
    }
}
