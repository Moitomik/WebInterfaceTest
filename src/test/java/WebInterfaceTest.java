import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebInterfaceTest {
    @Test
    @DisplayName("Should send a request if all fields are filled in correctly")
    void shouldSubmitRequest() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Василий Васильев");
        form.$("[data-test-id=phone] input").setValue("+79265550011");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
        Thread.sleep(5000);
    }

    @Test
    @DisplayName("Should not send a request if error occurs")
    void shouldIgnoreRequest() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Василий Васильев");
        form.$("[data-test-id=phone] input").setValue("+792655500111");
        form.$("[data-test-id=agreement]").click();
        form.$(By.className("button")).click();
        $(By.className("input__sub")).shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        Thread.sleep(5000);
    }
}