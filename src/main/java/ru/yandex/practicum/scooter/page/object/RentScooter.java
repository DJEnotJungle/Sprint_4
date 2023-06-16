package ru.yandex.practicum.scooter.page.object;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RentScooter {
    private final WebDriver driver;
    //Локатор для закрытия окна куки. В моём случаии это окно мешало отработать 2 кнопки "Заказать"
    private By cookiesButton = By.xpath(".//button[@class='App_CookieButton__3cvqF']");
    //Локатор Имени заказчика
    private By uerName = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/input");
    //Локатор Фамилии заказчика
    private By userSurname = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/input");
    //Локатор адреса доставки заказчику
    private By deliveryAddress = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[3]/input");
    //Локатор Станции метро доставки заказчику
    private By undergroundSelector = By.className("select-search__input");
    //Локатор телефона заказчика
    private By phoneNumber = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[5]/input");
    //Локатор первой кнопки далее в форме заказа
    private By nextOne = By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button");
    //Локатор кнопки назад на втором этапе заполнения фомы
    private By backOne = By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button[1]");
    //Локатор дата привоза самоката
    private By rentalDate = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div[1]/div/input");
    //Локатор для открытия поля ввода срока аренды
    private By timeOfRent = By.className("Dropdown-placeholder");
    //Локаторы для выборы даты. Я посчитал что удобнее будет выбирать сдесь нужные вариант, а не раздувать параметризированный тест
    private By howManyTimeOfRent = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[2]/div[1]"); // 1 сутки
    /*
    private By howManyTimeOfRent = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[2]/div[2]"); // 2 суток
    private By howManyTimeOfRent = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[2]/div[3]"); // 3 суток
    private By howManyTimeOfRent = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[2]/div[4]"); // 4 суток
    private By howManyTimeOfRent = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[2]/div[5]"); // 5 суток
    private By howManyTimeOfRent = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[2]/div[6]"); // 6 суток
    private By howManyTimeOfRent = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[2]/div[7]"); // 7 суток
*/
    //Локатор для комментария курьеру
    private By comment = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/input");
    //Локатор кнопки заказать на втором этапе формы оформления заказа
    private By nextTwo = By.xpath("//*[@id=\"root\"]/div/div[2]/div[3]/button[2]");
    //Локатор кнопки да на экране Хотите оформить заказ
    private By nextThree = By.xpath("//*[@id=\"root\"]/div/div[2]/div[5]/div[2]/button[2]");
    //Локатор кнопки назад на экране Хотите оформить заказ
    private By backTwo = By.xpath("//*[@id=\"root\"]/div/div[2]/div[5]/div[2]/button[1]");
    //Локатор кнопки посмотреть статус заказа. Далее будет использоваться для проверк и корректности отработки теста
    private By status = By.xpath("/html/body/div/div/div[2]/div[5]/div[2]/button");
    public RentScooter(WebDriver driver){
        this.driver = driver;
    }
    //Метод для двух кнопок открытия формы оформления заказа.
    public void startRent(String button){
        new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(button)));
        driver.findElement(By.xpath(button)).click();
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
    public void secondFormForRent(String changeRentalDate, String changeColor, String comments){
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(backOne));
        driver.findElement(backOne).click(); //Проверяем что кнопка назад работает
        new WebDriverWait(driver, 3).until(ExpectedConditions.elementToBeClickable(nextOne));
        driver.findElement(nextOne).click();
        driver.findElement(rentalDate).sendKeys(changeRentalDate, Keys.ARROW_DOWN, Keys.ENTER);
        driver.findElement(timeOfRent).click();
        driver.findElement(howManyTimeOfRent).click();
        driver.findElement(By.id(changeColor)).click();
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
}//
