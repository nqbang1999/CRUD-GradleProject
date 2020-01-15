package com.codegym.controllers;

import com.codegym.model.Product;
import com.codegym.model.TypeProduct;
import com.codegym.service.ProductService;
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
public class ProductController {
    @Autowired
    private TypeProductService typeProductService;

    @Autowired
    private ProductService productService;

    @ModelAttribute("typeProducts")
    private Page<TypeProduct> typeProducts(@PageableDefault(size = 5, sort = "id") Pageable pageable){
        return typeProductService.findAll(pageable);
    }

    @GetMapping(value = "/create-product")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping(value = "/create-product")
    public String createProduct(@Validated @ModelAttribute Product product,
                                BindingResult bindingResult,
                                RedirectAttributes redirect) {
        if (bindingResult.hasFieldErrors()) {
            return "redirect:/create-product";
        }
        productService.save(product);
        redirect.addFlashAttribute("message", "created product successfully");
        return "redirect:/create-product";
    }

    @GetMapping(value = "/home")
    public ModelAndView listProduct(@RequestParam Optional<String> search,
                                    @PageableDefault(size = 3, sort = "id") Pageable pageable) {
        Page<Product> products;
        if (search.isPresent()) {
            products = productService.findAllByName(search.get(), pageable);
        } else {
            products = productService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("product/home");
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping(value = "/edit-product/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("product/edit");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping(value = "/edit-product")
    public ModelAndView editProduct(@Validated @ModelAttribute Product product) {
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("product/edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "edited successfully");
        return modelAndView;
    }

    @GetMapping(value = "/delete-product/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Product product = productService.findById(id);
        ModelAndView modelAndView = new ModelAndView("product/delete");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping(value = "/delete-product")
    public String deleteProduct(@ModelAttribute Product product, RedirectAttributes redirect) {
        productService.remove(product.getId());
        return "redirect:/home";
    }









}
