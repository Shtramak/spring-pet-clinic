package com.shtramak.springpetclinic.controller;

import com.shtramak.springpetclinic.service.VetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vets")
@RequiredArgsConstructor
public class VetController {
    private final VetService vetService;

    @GetMapping
    public String listVets(Model model) {
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }
}
