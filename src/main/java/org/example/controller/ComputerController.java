package org.example.controller;


import org.example.model.Computer;
import org.example.service.IComputerService;
import org.example.service.ITypeService;
import org.example.service.impl.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/computers")
public class ComputerController {
    private final IComputerService computerService;
    private final ITypeService typeService;


    @Autowired
    public ComputerController(IComputerService computerService, ITypeService typeService) {
        this.computerService = computerService;
        this.typeService = typeService;

    }


    @GetMapping("")
    public ModelAndView showList(@PageableDefault(value = 5) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("computer/index");
        modelAndView.addObject("computers", computerService.findAllPageAndSort(pageable));
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("computer/delete");
        modelAndView.addObject("computer", computerService.findById(id).get());
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        computerService.delete(id);
        return "redirect:/computers";
    }
    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("computer/create");
        modelAndView.addObject("computer", new Computer());
        modelAndView.addObject("types",typeService.findAll());

        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@Validated @ModelAttribute Computer computer , BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("computer/create");
            modelAndView.addObject("types",typeService.findAll());
            return modelAndView;
        }
        try {
            computerService.save(computer);
        }catch (IllegalArgumentException e){
            bindingResult.rejectValue("code","Không thể thêm thông tin máy",e.getMessage());
            ModelAndView modelAndView = new ModelAndView("computer/create");
            modelAndView.addObject("types",typeService.findAll());
            return modelAndView;
        }
        return new ModelAndView("redirect:/computers");
    }

    @GetMapping("/update/{id}")
    public ModelAndView showFormUpdate(@PathVariable Long id) {
        Computer computer = computerService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("computer/update");
        modelAndView.addObject("computer", computer);
        modelAndView.addObject("types",typeService.findAll());

        return modelAndView;
    }

    @PostMapping("/update/{id}")
    public ModelAndView updateProduct(@Validated @ModelAttribute Computer computer , BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("computer/update");
            modelAndView.addObject("types",typeService.findAll());
            return modelAndView;
        }
        try {
            computerService.save(computer);
        }catch (IllegalArgumentException e){
            bindingResult.rejectValue("code","Không thể sửa thông tin máy này",e.getMessage());
            ModelAndView modelAndView = new ModelAndView("computer/update");
            modelAndView.addObject("types",typeService.findAll());
            return modelAndView;
        }
        return new ModelAndView("redirect:/computers");
    }

    @GetMapping("/search")
    public ModelAndView showSearch(@RequestParam String s , @PageableDefault(value = 5) Pageable pageable) {
        Page<Computer> computers;
        if (s.isEmpty()) {
            computers = computerService.findAllPageAndSort(pageable);
        } else {
            computers =computerService.findByNameContainingPageAndSort(s , pageable);
        }
        ModelAndView modelAndView = new ModelAndView("computer/search");
        modelAndView.addObject("computers", computers);
        modelAndView.addObject("s",s);
        return modelAndView;
    }
}