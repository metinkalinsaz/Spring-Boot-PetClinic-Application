package com.javaegitimleri.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javaegitimleri.petclinic.service.PetClinicService;

@Controller
public class PetClinicController {

    @Autowired
    private PetClinicService petClinicService;

    @RequestMapping(value = {"/","/index.html"}, method = RequestMethod.POST)
    public  ModelAndView index(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/pcs")
    @ResponseBody
    public String welcome() {
        return "Welcome to PetClinic World!";
    }

    @RequestMapping("/owners")
    public ModelAndView getOwners() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("owners", petClinicService.findOwners());
        mav.setViewName("owners");
        return mav;
    }
}