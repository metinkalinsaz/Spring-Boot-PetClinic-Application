package com.javaegitimleri.petclinic;

import com.javaegitimleri.petclinic.model.Owner;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class PetClinicRestControllerTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = restTemplate.withBasicAuth("user2","secret");

    }


    @Test
    public void testUpdateOwner() {
        RestTemplate restTemplate = new RestTemplate();
        Owner owner = restTemplate.getForObject("http://localhost:8080/rest/owner/4", Owner.class);

        MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Emre"));

        owner.setFirstName("Emre emre");
        restTemplate.put("http://localhost:8080/rest/owner/4", owner);
        owner = restTemplate.getForObject("http://localhost:8080/rest/owner/4", Owner.class);

        MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Emre emre"));
    }

    @Test
    public void testDeleteOwner() {
        restTemplate.delete("http://localhost:8080/rest/owner/4");

//        ResponseEntity<Void> responseEntity=restTemplate.exchange("http://localhost:8080/rest/owner/4", HttpMethod.DELETE,null,Void.class);
        try {
            restTemplate.getForEntity("http://localhost:8080/rest/owner/4", Owner.class);
            Assert.fail("should have not returned owner");

        } catch (HttpClientErrorException ex) {
            MatcherAssert.assertThat(ex.getStatusCode().value(), Matchers.equalTo(404));
        }

    }


    @Test
    public void testGetOwnerById() {
        ResponseEntity<Owner> response = restTemplate.getForEntity("http://localhost:8080/rest/owner/1", Owner.class);

        MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
//        MatcherAssert.assertThat(response.getBody().getLastName(), Matchers.equalTo("Kalinsaz"));
    }

    @Test
    public void testGetOwnersByLastName() {

        ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:8080/rest/owner?ln=Kalinsaz", List.class);

        MatcherAssert.assertThat(responseEntity.getStatusCodeValue(), Matchers.equalTo(200));
        List<Map<String, String>> body = responseEntity.getBody();
        List<String> firstName = body.stream().map(e -> e.get("firstName")).collect(Collectors.toList());
        MatcherAssert.assertThat(firstName, Matchers.containsInAnyOrder("Metin", "İsa", "Bayindir"));
    }

    @Test
    public void testGetOwner() {
        ResponseEntity<List> listResponseEntity = restTemplate.getForEntity("http://localhost:8080/rest/owners", List.class);

        MatcherAssert.assertThat(listResponseEntity.getStatusCodeValue(), Matchers.equalTo(200));
        List<Map<String, String>> maps = listResponseEntity.getBody();
        List<String> firstName = maps.stream().map(e -> e.get("firstName")).collect(Collectors.toList());
        MatcherAssert.assertThat(firstName, Matchers.containsInAnyOrder("Metin", "İsa", "Sercan", "Emre"));


    }
}
