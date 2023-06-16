package ru.yandex.practicum.scooter.page.object;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.hamcrest.CoreMatchers.is;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class RentScooterTest {
    private static WebDriver driver;
    //Локатор который будет менять по какой из двух кнопок открывается форма аренды
    private final String orderButton;
    //Значение имени
    private final String firstName;
    //Значение фамилии
    private final String secondName;
    //Значение улицы
    private final String streetName;
    //Значение станции
    private final String metroStation;
    //Значение метро
    private final String phoneNumber;
    //Значение даты доставки
    private final String data;
    //Значение цвета
    private final String colour;
    //Значение коментария курьеру
    private final String comment;

    public RentScooterTest(String orderButton, String firstName, String secondName, String streetName, String metroStation, String phoneNumber, String data, String colour, String comment) {
        this.orderButton = orderButton;
        this.firstName = firstName;
        this.secondName = secondName;
        this.streetName = streetName;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.data = data;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getText() {
        return new Object[][] {
                //Общий тест что всё работает при обычных вариантах
                { "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]","Олег", "Олегович", "Маршал 1", "Щукинская", "89001234567", "25.07.2023", "black", "Больше самокатов богу самокатов"},
                { "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]","Олег", "Олегович", "Маршал 1", "Щукинская", "89001234567", "25.07.2023", "grey", "Нет"},
                { "//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button","Олег", "Олегович", "Маршал 1", "Щукинская", "89001234567", "25.07.2023", "black", "Еретики"},
                { "//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button","Олег", "Олегович", "Маршал 1", "Щукинская", "89001234567", "25.07.2023", "grey", "Непонял"},
                //Проверяем латиницу в имени
                {  "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]", "Sergey", "Сергей", "Питерская 12", "Сокольники", "89001234567","25.07.2023", "black", "Наш бог в Пицце"},
                {  "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]", "Sergey", "Сергей", "Питерская 12", "Сокольники", "89001234567","25.07.2023", "grey", "Пицца это зло спота"},
                { "//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button","Sergey", "Сергей", "Питерская 12", "Сокольники", "89001234567","25.07.2023", "black", "Я хочу пиццы"},
                { "//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button","Sergey", "Сергей", "Питерская 12", "Сокольники", "89001234567","25.07.2023", "grey", "Больше самокатов богу самокатов"},
                //Проверяем латиницу в Фамилииstatus
                { "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]","Вячеслав", "Monkey", "Московская 10", "Киевская", "89001234567", "25.07.2023", "black", "Больше пиццы богу пиццы" },
                { "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]","Вячеслав", "Monkey", "Московская 10", "Киевская", "89001234567", "25.07.2023", "grey", "Да начнётсярелигиозная вояна во имя пиццы и самоката" },
                { "//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button","Вячеслав", "Monkey", "Московская 10", "Киевская", "89001234567", "25.07.2023", "black", "Да начнётся религиозная вояна во имя пиццы и самоката" },
                { "//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button","Вячеслав", "Monkey", "Московская 10", "Киевская", "89001234567", "25.07.2023", "grey", "А дальш мне лень придумывать, по этому до тестов комментариев будет комментаий из первой параметризации" },
                // Проверяем латиницу в улице
                { "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]","Анастасья", "Петрова", "Northern 10", "Шелепиха", "89001234567", "25.07.2023", "black", "Больше самокатов богу самокатов" },
                { "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]","Анастасья", "Петрова", "Northern 10", "Шелепиха", "89001234567", "25.07.2023", "grey", "Больше самокатов богу самокатов" },
                { "//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button","Анастасья", "Петрова", "Northern 10", "Шелепиха", "89001234567", "25.07.2023", "black", "Больше самокатов богу самокатов" },
                { "//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button","Анастасья", "Петрова", "Northern 10", "Шелепиха", "89001234567", "25.07.2023", "grey", "Больше самокатов богу самокатов" },
                // Проверяем латиницу в станции метро/Несушествующая станция метро
                { "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]","Алеся", "Ивановна", "Казанская 10", "Underground", "89001234567", "25.07.2023", "black", "Больше самокатов богу самокатов" },
                { "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]","Алеся", "Ивановна", "Казанская 10", "Underground", "89001234567", "25.07.2023", "grey", "Больше самокатов богу самокатов" },
                { "//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button","Алеся", "Ивановна", "Казанская 10", "Underground", "89001234567", "25.07.2023", "black", "Больше самокатов богу самокатов" },
                { "//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button","Алеся", "Ивановна", "Казанская 10", "Underground", "89001234567", "25.07.2023", "grey", "Больше самокатов богу самокатов" },
                // Проверяем несуществующий номер
                { "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]","Алеся", "Ивановна", "Казанская 10", "Барикадная", "+71234567890", "25.07.2023", "black", "Больше самокатов богу самокатов" },
                { "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]","Алеся", "Ивановна", "Казанская 10", "Барикадная", "+71234567890", "25.07.2023", "grey", "Больше самокатов богу самокатов" },
                { "//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button","Алеся", "Ивановна", "Казанская 10", "Барикадная", "+71234567890", "25.07.2023", "black", "Больше самокатов богу самокатов" },
                { "//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button","Алеся", "Ивановна", "Казанская 10", "Барикадная", "+71234567890", "25.07.2023", "grey", "Больше самокатов богу самокатов" },
                // Проверяем прошедшую дату
                { "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]","Алеся", "Ивановна", "Казанская 10", "Менделеевская", "89001234567", "25.07.1999", "black", "Больше самокатов богу самокатов" },
                { "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]","Алеся", "Ивановна", "Казанская 10", "Менделеевская", "89001234567", "25.07.1999", "grey", "Больше самокатов богу самокатов" },
                { "//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button","Алеся", "Ивановна", "Казанская 10", "Менделеевская", "89001234567", "25.07.1999", "black", "Больше самокатов богу самокатов" },
                { "//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button","Алеся", "Ивановна", "Казанская 10", "Менделеевская", "89001234567", "25.07.1999", "grey", "Больше самокатов богу самокатов" },
                // Проверяем комментарий на латинице
                { "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]","Алеся", "Ивановна", "Казанская 10", "Савёловская", "89001234567", "25.07.2023", "black", "Some comment" },
                { "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]","Алеся", "Ивановна", "Казанская 10", "Савёловская", "89001234567", "25.07.2023", "grey", "Some comment" },
                { "//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button","Алеся", "Ивановна", "Казанская 10", "Савёловская", "89001234567", "25.07.2023", "black", "Some comment" },
                { "//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button","Алеся", "Ивановна", "Казанская 10", "Underground", "89001234567", "25.07.2023", "grey", "Some comment" },
        };
    }

    @Test
    public void checkActivity() {
        //WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        RentScooter objRentScooter = new RentScooter(driver);
        objRentScooter.clickSignInButton();
        objRentScooter.startRent(orderButton);
        objRentScooter.firstFormForRent(firstName, secondName, streetName, metroStation, phoneNumber);
        objRentScooter.secondFormForRent(data, colour, comment);
        String result = objRentScooter.finalFormForRent();
        MatcherAssert.assertThat("Заказ не оформлен", result, is("Посмотреть статус"));
    }


    @After
    public void teardown() {
        driver.quit();
    }
}