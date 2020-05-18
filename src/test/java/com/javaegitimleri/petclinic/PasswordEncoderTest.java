package com.javaegitimleri.petclinic;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {

    @Test
    public void generateEncodedPasswords() {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println("{bcrypt}" + bCryptPasswordEncoder.encode("secret"));
        System.out.println("{bcrypt}" + bCryptPasswordEncoder.encode("secret"));
        System.out.println("{bcrypt}" + bCryptPasswordEncoder.encode("secret"));

    }
}
