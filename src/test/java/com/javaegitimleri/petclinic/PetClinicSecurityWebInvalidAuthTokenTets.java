package com.javaegitimleri.petclinic;

import com.javaegitimleri.petclinic.service.PetClinicService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(value = "spring.profiles.active=dev")
public class PetClinicSecurityWebInvalidAuthTokenTets {


    @Autowired
    PetClinicService petClinicService;

//    Yetkisi yeterli olmama durumunu test ediyoruz.AccessDeniedException aldıgımızda dogru çalışmakta oldugunu görüyoruz.
    @Before
    public void setUp(){
        TestingAuthenticationToken testingAuthenticationToken=new TestingAuthenticationToken("user","secret,","ROLE_XXX");
        SecurityContextHolder.getContext().setAuthentication(testingAuthenticationToken);
    }

    @After
    public void tearDown(){
        SecurityContextHolder.clearContext();
    }

    @Test(expected = AccessDeniedException.class)
    public void testFindOwners(){
        petClinicService.findOwners();
    }
}

