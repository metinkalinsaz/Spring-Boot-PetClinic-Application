package com.javaegitimleri.petclinic.web;

import com.javaegitimleri.petclinic.model.Owner;
import com.javaegitimleri.petclinic.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PetClinicEditOwnerController {

    @Autowired
    PetClinicService petClinicService;

    @RequestMapping(value = "/owners/update/{id}",method = RequestMethod.GET)
    public String loadOwner(@PathVariable Long id, ModelMap modelMap){
       Owner owner=petClinicService.findOwner(id);
       modelMap.put("owner",owner);
       return "editOwner";
    }

    @RequestMapping(value = "/owners/update/{id}",method = RequestMethod.POST)
    public String handleFormatSubmit(@ModelAttribute Owner owner){

        petClinicService.updateOwner(owner);
        return "redirect:/owners";
    }
}
