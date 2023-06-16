package ru.yandex.practicum.scooter.page.object;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomeFourPart {
    private final WebDriver driver;
    //Локатор для скрола до раздела Вопросы о важном
    private final By mainQuestionText = By.xpath("//div[text()='Вопросы о важном']");
    public HomeFourPart(WebDriver driver){
        this.driver = driver;
    }

    //Метод раскрываюзщий модули и сверяющий текст FAQ
    public String getTextOfFAQ(String answerId, String questionId) {
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(mainQuestionText));
        WebElement element = driver.findElement(mainQuestionText);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(By.id(questionId)).click();
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(answerId)));
        return driver.findElement(By.id(answerId)).getText();
    }
}