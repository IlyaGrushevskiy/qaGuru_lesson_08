package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import steps.BaseSteps;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.AttachmentsHelper.*;

public class TestBase {

    @BeforeAll
    static void setup() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.startMaximized = true;

        //if(System.getProperty("remote_driver") != null) {
            // config for Java + Selenide
            //DesiredCapabilities capabilities = new DesiredCapabilities();
            //capabilities.setCapability("enableVNC", true);
            //capabilities.setCapability("enableVideo", true);
            //Configuration.browserCapabilities = capabilities;
            //Configuration.remote = System.getProperty("remote_driver");
            Configuration.remote = "https://user1:1234@selenoid.autotests.cloud:4444/wd/hub/";

            // config for Java + Selenium
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("browserName", "chrome");
//        capabilities.setCapability("browserVersion", "87.0");
//        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
//                "enableVNC", true,
//                "enableVideo", true
//        ));
//        RemoteWebDriver driver = new RemoteWebDriver(
//                URI.create("http://selenoid:4444/wd/hub").toURL(),
//                capabilities
//        );
        //}
    }

    @AfterEach
    public void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());
        if(System.getProperty("video_storage") != null)
            attachVideo();
        closeWebDriver();
    }

    public BaseSteps baseSteps = new BaseSteps();

    private static final String BASE_URL = "http://github.com";
    private static final String ISSUES = "Issues";
    private static final String REPOSITORY = "IlyaGrushevskiy/qaGuru_lesson_05";
    private static final String ISSUE_NUMBER = "#1";

    @Test
    @Owner("grushevskiy")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("test"), @Tag("critical")})
    @Link(name = "Base URL", value = BASE_URL)
    @Feature("Issues")
    @Story("Поск Issue")
    @DisplayName("Поиск Issue по номеру в репозитории")
    public void issueSearchTest() {
        baseSteps.openMainPage();
        baseSteps.searchRepository(REPOSITORY);
        baseSteps.openRepositoryFromSearch(REPOSITORY);
        baseSteps.openIssue(ISSUES);
        baseSteps.checkIssue(ISSUE_NUMBER);
    }

}
