package com.shtramak.springpetclinic.controller;

import com.shtramak.springpetclinic.model.Owner;
import com.shtramak.springpetclinic.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {
    private final OwnerService service;

    @GetMapping
    public String listOwners(Owner owner, BindingResult result, Model model) {
        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName().isBlank()) {
            model.addAttribute("owners", service.findAll());
            return "owners/ownersList";
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
            return "owners/ownersList";
        }
    }

    @GetMapping("/{id}")
    public ModelAndView ownerById(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
        modelAndView.addObject(service.findById(id).orElseThrow(NoSuchElementException::new)); // todo Exception Handling
        return modelAndView;
    }

    @GetMapping("/find")
    public String initFindFirst(Model model) {
        model.addAttribute("owner", new Owner());
        return "owners/findOwners";
    }
}
