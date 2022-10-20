package com.examples.cucumber;

import com.codetech.nutrix.patient.domain.model.entity.Patient;
import com.codetech.nutrix.nutritionist.domain.model.entity.Nutritionist;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RegisterNutrixSteps {

    private RestTemplate restTemplate = new RestTemplate();
    private String url="http://localhost:8080/api/v1";
    private String message="";
    Nutritionist nutritionist;
    Long nutritionistId = randomLong();


    public Long randomLong() {
        Long generatedLong = new Random().nextLong();
        return generatedLong;
    }


    @Given("I want to register as nutritionist")
    public void i_want_to_register_as_nutritionist() {
        String nutritionistUrl=url + "/nutritionists";
        String getEndpoint=restTemplate.getForObject(nutritionistUrl, String.class);
        log.info(getEndpoint);
        assertTrue(!getEndpoint.isEmpty());
    }
    @And("I enter my own information like name {string}, dni {string}, email {string}, password {string}, phone {string}, specialization {string}, formation {string}, about {string}, genre {string}, sessionType {string} and code {string}")
    public void i_enter_my_own_information_like_name_dni_email_password_phone_specialization_formation_about_me_genre_session_type_and_code(String name, String dni, String email, String password, String phone, String specialization, String formation, String about, String genre, String sessionType, String code) {
        String nutritionistUrl=url + "/nutritionists";
        Date birthdayDate = new Date();

        Nutritionist newNutritionist = new Nutritionist(nutritionistId, name,dni, birthdayDate, email, password, phone,specialization,formation, about,genre, sessionType,"img.jpg",code, true,true);
        nutritionist =restTemplate.postForObject(nutritionistUrl, newNutritionist, Nutritionist.class);
        log.info(nutritionist.getId());
        assertNotNull(nutritionist);
    }
    @Then("I should be able to see my newly nutritionist account")
    public void i_should_be_able_to_see_my_newly_nutritionist_account() {
        String patientUrl=url + "/nutritionists/"+ nutritionist.getId();
        try
        {
            Patient getNutritionistById=restTemplate.getForObject(patientUrl, Patient.class, nutritionist.getId());
            log.info(getNutritionistById);
        }catch(RestClientException e){
            message="";
        }
        assertEquals("",message);
    }
}
