package tests.ui;

import cartsteps.CartSteps;
import io.restassured.response.Response;
import model.Cart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import java.io.IOException;
import java.util.Map;

import static data.Uri.ADD_TO_CART;
import static data.Uri.DELETE_FROM_CART;
import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.FileUtils.readIdCartFromFile;
import static utils.FileUtils.writeIdCartToFile;

public class PetShopApiForUITest extends BaseTest {
    private static String cookie = "";
    private static final CartSteps cartSteps = new CartSteps();

    @DisplayName("Добавление товара в корзину")
    @Test
    void addToCard() throws IOException {
        String body = "{\"productId\":{productId},\"cartElementId\":0,\"quantity\":1}"
                .replace("{productId}", "209753");

        Response response = given()
                .filter(withCustomTemplates())
                .contentType("application/json;charset=UTF-8")
                .body(body)
                .cookie("cookie", cookie)
                .log().all()
                .when()
                .post(ADD_TO_CART)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();

        Map<String, String> allCookies = response.getCookies();

        allCookies.forEach((key, value) -> cookie = cookie + key + "=" + value + "; ");

        Cart cart = response.as(Cart.class);

        int idCart = cart.getCartItems().get(0).getId();

        assertThat(idCart)
                .as("idCart must be not null").isNotNull();

        writeIdCartToFile("src/test/resources/tempData/idCart.txt", idCart);
    }

    @DisplayName("Удаление товара из корзины")
    @Test
    void removeFromCard() throws IOException {
        Response response = cartSteps.addToCart("209753", cookie);
        assertThat(response.statusCode()).isEqualTo(200);

        String idCart = readIdCartFromFile("src/test/resources/tempData/idCart.txt");

        String uri = DELETE_FROM_CART
                .replace("{idCard}", idCart);
        System.out.println(uri);

        Response responseDeleteFromCart = given()
                .filter(withCustomTemplates())
                .contentType("application/json;charset=UTF-8")
                .cookie("cookie", cookie)
                .when()
                .log().all()
                .delete(uri)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response();

        Cart cart = responseDeleteFromCart.as(Cart.class);

        assertThat(cart.getCartItems().size())
                .as("getCartItems size must be equal to \"0\"").isEqualTo(0);
    }
}
