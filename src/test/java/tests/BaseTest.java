package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {


    @BeforeAll
    static void setUp(){
        RestAssured.baseURI = "https://www.petshop.ru";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
