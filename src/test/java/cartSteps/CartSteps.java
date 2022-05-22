package cartSteps;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static data.Uri.ADD_TO_CART;
import static data.Uri.DELETE_FROM_CART;
import static io.restassured.RestAssured.given;
import static tests.BaseTest.BASE_URL;

public class CartSteps {

    @Step("Добавление в корзину")
    public Response addToCart(String productId, String cookie){
        String body = "{\"productId\":{productId},\"cartElementId\":0,\"quantity\":1}"
                .replace("{productId}", productId);

        return given()
                .contentType("application/json;charset=UTF-8")
                .body(body)
                .cookie("cookie", cookie)
                .log().all()
                .when()
                .post(BASE_URL + ADD_TO_CART)
                .then()
                .log().all()
                .extract().response();
    }

    public Response removeFromCart(String idCard, String cookie) {
        String uri = DELETE_FROM_CART
                .replace("{idCard}", idCard);
        System.out.println(uri);

        return given()
                .contentType("application/json;charset=UTF-8")
                .cookie("cookie", cookie)
                .when()
                .log().all()
                .delete(BASE_URL + uri)
                .then()
                .log().all()
                .extract().response();
    }
}
