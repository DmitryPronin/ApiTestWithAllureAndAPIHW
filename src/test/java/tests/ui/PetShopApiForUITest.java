package tests.ui;

import cartSteps.CartSteps;
import io.restassured.response.Response;
import model.Cart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import tests.BaseTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.FileUtils.readIdCartFromFile;
import static utils.FileUtils.writeIdCartToFile;

public class PetShopApiForUITest extends BaseTest {
    private final String cookie = "route=c89734fddd3807762176c479d95db98f; PHPSESSID=971b7ba7285765122bab090622172d96; BITRIX_SM_SALE_UID=1111330590; rrpvid=94; geo_city_id=a%3A2%3A%7Bs%3A7%3A%22city_id%22%3Bi%3A9%3Bs%3A13%3A%22is_determined%22%3Bb%3A1%3B%7D; showEcoSystem=true; confirmedCity=true";
    private final CartSteps cartSteps = new CartSteps();

    @DisplayName("Добавление товара в корзину")
    @Test
    @Order(1)
    void addToCard() throws IOException {
        Response response = cartSteps.addToCart("209753", cookie);

        assertThat(response.statusCode())
                .as("statusCode must be equal to 200").isEqualTo(200);

        Cart cart = response.as(Cart.class);

        int idCart = cart.getCartItems().get(0).getId();

        assertThat(idCart)
                .as("idCart must be not null").isNotNull();

        writeIdCartToFile("src/test/resources/tempData/idCart.txt", idCart);
    }

    @DisplayName("Удаление товара из корзины")
    @Test
    @Order(2)
    void removeFromCard() throws IOException {
        String idCart = readIdCartFromFile("src/test/resources/tempData/idCart.txt");

        Response response = cartSteps.removeFromCart(String.valueOf(idCart), cookie);
        assertThat(response.statusCode())
                .as("statusCode must be to \"200\"").isEqualTo(200);

        Cart cart = response.as(Cart.class);
        assertThat(cart.getCartItems().size())
                .as("getCartItems size must be equal to \"0\"").isEqualTo(0);
    }
}
