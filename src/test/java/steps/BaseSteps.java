package steps;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BaseSteps {

    private static final String BASE_URL = "http://github.com";

    @Step("Открыта главная страница")
    public void openMainPage () {
        open(BASE_URL);
    }

    @Step ("Выполнен поиск репозитория")
    public void searchRepository (String searchText) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(searchText);
        $(".header-search-input").submit();
    }

    @Step ("Выполнен переход в репозиторий: {repositoryName}")
    public void openRepositoryFromSearch (String repositoryName) {
        $(By.linkText(repositoryName)).click();
    }

    @Step ("Выполнен переход в ISUUES - {issueName}")
    public void openIssue (String issueName) {
        $(withText(issueName)).click();
    }

    @Step ("Проверено наличие искомого ISSUE - {issueName}")
    public void checkIssue (String issueName) {
        $(withText(issueName)).should(Condition.exist);
    }

}
