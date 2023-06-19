package ru.yandex.practicum.scooter.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RentScooter {
    private final WebDriver driver;
    //Локаторы для кнопок заказать
    private By startTop = By.xpath("//div/button[@class='Button_Button__ra12g']");
    private By startMid = By.xpath("//div/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //Локатор для закрытия окна куки. В моём случаии это окно мешало отработать 2 кнопки "Заказать"
    private By cookiesButton = By.xpath(".//button[@class='App_CookieButton__3cvqF']");
    //Локатор Имени заказчика
    private By uerName = By.xpath(".//input[@placeholder='* Имя']");
    //Локатор Фамилии заказчика
    private By userSurname = By.xpath(".//input[@placeholder='* Фамилия']");
    //Локатор адреса доставки заказчику
    private By deliveryAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //Локатор Станции метро доставки заказчику
    private By undergroundSelector = By.className("select-search__input");
    //Локатор телефона заказчика
    private By phoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Локатор первой кнопки далее в форме заказа
    private By nextOne = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //Локатор кнопки назад на втором этапе заполнения фомы
    private By backOne = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM Button_Inverted__3IF-i']");
    //Локатор дата привоза самоката
    private By rentalDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //Локатор для открытия поля ввода срока аренды
    private By timeOfRent = By.className("Dropdown-placeholder");
    //Локаторы для выборы даты. Я посчитал что удобнее будет выбирать сдесь нужные вариант, а не раздувать параметризированный тест
    private By howManyTimeOfRent = By.xpath("//div[contains(text(),'сутки')]"); // 1 сутки
    /*
    private By howManyTimeOfRent = By.xpath("//div[contains(text(),'двое суток')]"); // 2 суток
    private By howManyTimeOfRent = By.xpath("//div[contains(text(),'трое суток')]"); // 3 суток
    private By howManyTimeOfRent = By.xpath("//div[contains(text(),'четверо суток')]"); // 4 суток
    private By howManyTimeOfRent = By.xpath("//div[contains(text(),'пятеро суток')]"); // 5 суток
    private By howManyTimeOfRent = By.xpath("//div[contains(text(),'шестеро суток')]"); // 6 суток
    private By howManyTimeOfRent = By.xpath("//div[contains(text(),'семеро суток')]"); // 7 суток
*/
    //Локаторы для  чёрного цвета самоката
    private By colourBlack = By.id("black");
    //Локаторы для серого самоката
    private By colourGrey = By.id("grey");

    //Локатор для комментария курьеру
    private By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //Локатор кнопки заказать на втором этапе формы оформления заказа
    private By nextTwo = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //Локатор кнопки да на экране Хотите оформить заказ
    private By nextThree = By.xpath(".//button[text()='Да']");
    //Локатор кнопки назад на экране Хотите оформить заказ
    private By backTwo = By.xpath(".//button[text()='Нет']");
    //Локатор кнопки посмотреть статус заказа. Далее будет использоваться для проверк и корректности отработки теста
    private By status = By.xpath(".//button[text()='Посмотреть статус']");
    public RentScooter(WebDriver driver){
        this.driver = driver;
    }
    //Метод для двух кнопок открытия формы оформления заказа.
    public void startRent(int numberOfButton){
        if (numberOfButton == 1){
            new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(startTop));
            driver.findElement(startTop).click();
        }else {
            new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(startMid));
            driver.findElement(startMid).click();
        }
    }

    //Метод для выбора одного из цветов
    public void colorSelection(int numberOfColor){
        if (numberOfColor == 1){
            new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(colourBlack));
            driver.findElement(colourBlack).click();
        }else {
            new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(colourGrey));
            driver.findElement(colourGrey).click();
        }
    }

    //Метод для закрытия окна куки
    public void clickSignInButton() {
        driver.findElement(cookiesButton).click();
    }
    //Метод для заполнения первой части формы оформления заказа
    public void firstFormForRent(String changeName, String changeSurname, String changeAddress, String changeUnderground, String changePhone){
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(nextOne));
        driver.findElement(uerName).sendKeys(changeName);
        driver.findElement(userSurname).sendKeys(changeSurname);
        driver.findElement(deliveryAddress).sendKeys(changeAddress);
        driver.findElement(undergroundSelector).sendKeys(changeUnderground, Keys.ARROW_DOWN, Keys.ENTER);
        driver.findElement(phoneNumber).sendKeys(changePhone);
        driver.findElement(nextOne).click();
    }
    //Метод для заполнения второй части формы оформления заказа
    public void secondFormForRent(String changeRentalDate, int numberOfColor, String comments){
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(backOne));
        driver.findElement(backOne).click(); //Проверяем что кнопка назад работает
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(nextOne));
        driver.findElement(nextOne).click();
        driver.findElement(rentalDate).sendKeys(changeRentalDate, Keys.ARROW_DOWN, Keys.ENTER);
        driver.findElement(timeOfRent).click();
        driver.findElement(howManyTimeOfRent).click();
        colorSelection(numberOfColor);
        driver.findElement(comment).sendKeys(comments);
        driver.findElement(nextTwo).click();
    }
    //Метод который подтверждает оформление заказа и смотрит открылось ли окно статуса заказа
    public String  finalFormForRent(){
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(backTwo));
        driver.findElement(backTwo).click(); //Проверяем что кнопка назад работает
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(nextTwo));
        driver.findElement(nextTwo).click();
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(nextThree));
        driver.findElement(nextThree).click();
        return driver.findElement(status).getText();
    }
}
