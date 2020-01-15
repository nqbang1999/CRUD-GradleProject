package com.codegym.controllers;

import com.codegym.model.TypeProduct;
import com.codegym.service.TypeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Optional;


@Controller
public class TypeProductController {
    @Autowired
    private TypeProductService typeProductService;

    @GetMapping(value = "/create-type")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("type-product/create");
        modelAndView.addObject("typeProduct", new TypeProduct());
        return modelAndView;
    }

    @PostMapping(value = "/create-type")
    public String createTypeProduct(@Validated @ModelAttribute TypeProduct typeProduct, RedirectAttributes redirect) {
        typeProductService.save(typeProduct);
        redirect.addFlashAttribute("message", "created type product successfully");
        return "redirect:/create-type";
    }
}
