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
    //Локатор для вопроса о цене
    private final By price = By.id("accordion__heading-0");
    //Локатор для вопроса о нескольких самокатах
    private final By moreScooters = By.id("accordion__heading-1");
    //Локатор для вопроса о расчёте времени аренды
    private final By howLong = By.id("accordion__heading-2");
    //Локатор для вопроса о доставке скутара в день заказа
    private final By scooterToday = By.id("accordion__heading-3");
    //Локатор для вопроса о продлении аренды
    private final By moreTimeOfRent = By.id("accordion__heading-4");
    //Локато для вопроса о зарядке для самоката
    private final By powerForScooter = By.id("accordion__heading-5");
    //Локатор для вопроса можно ли отменить заказ
    private final By chancelScooter = By.id("accordion__heading-6");
    //Локатор для вопроса о доставке за мкад
    private final By notInMoscow = By.id("accordion__heading-7");
    //Локатор для ответа на вопрос о цене
    private final By answerPrice = By.id("accordion__panel-0");
    //Локатор для ответа на вопрос нескольких самокатах
    private final By answerMoreScooters = By.id("accordion__panel-1");
    //Локатор для ответа на вопрос о расчёте времени аренды
    private final By answerHowLong = By.id("accordion__panel-2");
    //Локатор для ответа на вопрос о доставке самоката в день заказа
    private final By answerScooterToday = By.id("accordion__panel-3");
    //Локатор для ответа на вопрос о продлении аренды самоката
    private final By answerMoreTimeOfRent = By.id("accordion__panel-4");
    //Локатор для ответа на вопроспривизут ли зарядку
    private final By answerPowerForScooter = By.id("accordion__panel-5");
    //Локатор для ответа на вопрос можно ли отменить заказ
    private final By answerChancelScooter = By.id("accordion__panel-6");
    //Локатор для ответа на вопрос доставляетсяли за мкад
    private final By answerNotInMoscow = By.id("accordion__panel-7");
    public HomeFourPart(WebDriver driver){
        this.driver = driver;
    }

    //Метод для скрола к FAQ
    public void scrollToFAQ(){
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(mainQuestionText));
        WebElement element = driver.findElement(mainQuestionText);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //Метод для получения текста о цене
    public String getTextOfPrice(){
        scrollToFAQ();
        driver.findElement(price).click();
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(answerPrice));
        return driver.findElement(answerPrice).getText();
    }

    //Метод для получения текста о колличестве арендованных скутеров
    public String getTextOfMoreScooters(){
        scrollToFAQ();
        driver.findElement(moreScooters).click();
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(answerMoreScooters));
        return driver.findElement(answerMoreScooters).getText();
    }

    //Метод для получения текста о расчёте времени
    public String getTextOfHowLong(){
        scrollToFAQ();
        driver.findElement(howLong).click();
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(answerHowLong));
        return driver.findElement(answerHowLong).getText();
    }

    //
    public String getTextOfScooterToday(){
        scrollToFAQ();
        driver.findElement(scooterToday).click();
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(answerScooterToday));
        return driver.findElement(answerScooterToday).getText();
    }

    public String getTextOfMoreTimeOfRent(){
        scrollToFAQ();
        driver.findElement(moreTimeOfRent).click();
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(answerMoreTimeOfRent));
        return driver.findElement(answerMoreTimeOfRent).getText();
    }

    public String getTextOfPowerForScooter(){
        scrollToFAQ();
        driver.findElement(powerForScooter).click();
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(answerPowerForScooter));
        return driver.findElement(answerPowerForScooter).getText();
    }

    public String getTextOfChancelScooter(){
        scrollToFAQ();
        driver.findElement(chancelScooter).click();
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(answerChancelScooter));
        return driver.findElement(answerChancelScooter).getText();
    }

    public String getTextOfNotInMoscow(){
        scrollToFAQ();
        driver.findElement(notInMoscow).click();
        new WebDriverWait(driver, 30)
                .until(ExpectedConditions.visibilityOfElementLocated(answerNotInMoscow));
        return driver.findElement(answerNotInMoscow).getText();
    }



    //Метод для выбоа метода в зависимости от id. Извиняюсь за тавтологию
    public String getTextOfFAQ(int id) {
        if (id==0){
            return getTextOfPrice();
        } else if (id==1){
            return getTextOfMoreScooters();
        } else if (id==2){
            return getTextOfHowLong();
        } else if (id==3){
            return getTextOfScooterToday();
        } else if (id==4){
            return getTextOfMoreTimeOfRent();
        } else if (id==5){
            return getTextOfPowerForScooter();
        } else if (id==6){
            return getTextOfChancelScooter();
        } else if (id==7){
            return getTextOfNotInMoscow();
        }else{
            return "Такого id не существует";
        }

    }
}