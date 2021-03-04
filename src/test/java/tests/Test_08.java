package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import steps.BaseSteps;

public class Test_08 {

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
