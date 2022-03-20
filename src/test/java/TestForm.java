import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestForm {

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browserSize = "1920x1080";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote="https://user1:1234@selenoid.autotests.cloud/wd/hub";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void testPracticeForm() {
        open("/automation-practice-form");
        $("#firstName").setValue("Boris");
        $("#lastName").setValue("Britva");
        $("#userEmail").setValue("borisbritva@gmail.com");
        $("#userNumber").setValue("8999818907");
        $(byText("Male")).click();
        $(byText("Sports")).click();
        $("#subjectsInput").setValue("Math").pressEnter();
        $("#react-select-3-input").setValue("Uttar Pradesh").pressEnter();
        $("#react-select-4-input").setValue("Agra").pressEnter();
        $x("//textarea[@placeholder='Current Address']").setValue("USSR");
        $("#dateOfBirthInput").click();
        $("[class=react-datepicker__month-select]").selectOptionByValue("8");
        $("[class=react-datepicker__year-select").selectOption("1990");
        $(".react-datepicker__day--013").click();
        $("#uploadPicture").uploadFile(new File(("src/test/resources/Pictest.png")));
    }
}
