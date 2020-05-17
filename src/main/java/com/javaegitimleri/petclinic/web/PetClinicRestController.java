package com.javaegitimleri.petclinic.web;

import com.javaegitimleri.petclinic.exception.InternalServerException;
import com.javaegitimleri.petclinic.exception.OwnerNotFoundException;
import com.javaegitimleri.petclinic.model.Owner;
import com.javaegitimleri.petclinic.service.PetClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class PetClinicRestController {

    @Autowired
    private PetClinicService petClinicService;


    @RequestMapping(method = RequestMethod.PUT, value = "/owner/{id}")
    public ResponseEntity<?> updateOwner(@PathVariable("id") Long id, @RequestBody Owner ownerRequest) {
        try {
            Owner owner = petClinicService.findOwner(id);
            owner.setFirstName(ownerRequest.getFirstName());
            owner.setLastName(ownerRequest.getLastName());
            petClinicService.updateOwner(owner);
            return ResponseEntity.ok().build();
        } catch (OwnerNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/owner/{id}")
    public ResponseEntity<?> deleteOwner(@PathVariable("id") Long id) {
        try {
            petClinicService.deleteOwner(id);
            return ResponseEntity.ok().build();
        } catch (OwnerNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalServerException(ex);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owners")
    public ResponseEntity<List<Owner>> getOwners() {
        List<Owner> owners = petClinicService.findOwners();
        return ResponseEntity.ok(owners);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owners", produces = "application/json")
    public ResponseEntity<?> getOwnersAsHateoasResource(@PathVariable("id") Long id) {

        try {
            Owner owner = petClinicService.findOwner(id);
            Link self = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id).withSelfRel();
            Link create = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/").withRel("create");
            Link update = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id).withRel("update");
            Link delete = ControllerLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id).withRel("delete");

            return ResponseEntity.ok(owner);
        } catch (OwnerNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owner/{id}")
    public ResponseEntity<?> getOwner(@PathVariable("id") Long id) {
        try {
            Owner owner = petClinicService.findOwner(id);
            return ResponseEntity.ok(owner);
        } catch (OwnerNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owner")
    public ResponseEntity<List<Owner>> getOwners(@RequestParam("ln") String lastName) {
        List<Owner> owners = petClinicService.findOwners(lastName);
        return ResponseEntity.ok(owners);
    }
}