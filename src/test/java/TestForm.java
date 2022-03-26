import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;


import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class TestForm {

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browserSize = "1920x1080";

//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("enableVNC", true);
//        capabilities.setCapability("enableVideo", true);
//        Configuration.browserCapabilities = capabilities;
//
//        String login = System.getProperty("login");
//        String password = System.getProperty("password");
//        Configuration.remote = "https://" + login + ":" + password + "@" + System.getProperty("remoteBrowser");
        Configuration.baseUrl = "https://demoqa.com";
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }

    @Test
    void testPracticeForm() {
        step("Открываем тестовую форму", () -> {
            open("/automation-practice-form");
        });
        step("Вводим основную информацию", () -> {
            $("#firstName").setValue("Boris");
            $("#lastName").setValue("Britva");
            $("#userEmail").setValue("borisbritva@gmail.com");
            $("#userNumber").setValue("8999818907");
            $(byText("Male")).click();
            $("#dateOfBirthInput").click();
            $("[class=react-datepicker__month-select]").selectOptionByValue("8");
            $("[class=react-datepicker__year-select").selectOption("1990");
            $(".react-datepicker__day--013").click();
            $("#subjectsInput").setValue("Math").pressEnter();
            $(byText("Sports")).click();
        });
        step("Вводим место проживания", () -> {
            $("#react-select-3-input").setValue("Uttar Pradesh").pressEnter();
            $("#react-select-4-input").setValue("Agra").pressEnter();
            $x("//textarea[@placeholder='Current Address']").setValue("USSR");
        });
        step("Проверяем, то что поля last name, first name, user number заполнены", () -> {
            $("#firstName").shouldHave(value("Boris"));
            $("#lastName").shouldHave(value("Britva"));
            $("#userNumber").shouldHave(value("8999818907"));
        });

    }
}
