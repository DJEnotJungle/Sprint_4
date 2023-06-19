package ru.yandex.practicum.scooter.page.object;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
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
    //Переменная которая выбирает какая кнопка будет использвоаться: 1 - верхняя кнопка, любое другое значение - нижняя кнопка. Можно было описать через переменные со значениями Top Mid и проверять через иквелс, но это заняло бы больше времени, а оно поджимает
    private final int orderButton;
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
    //Переменная которая выбирает какой цвет будет использвоаться: 1 - чёрный, любое другое значение - серый. Можно было описать через переменные со значениями black grey и проверять через иквелс, но это заняло бы больше времени, а оно поджимает
    private final int colour;
    //Значение коментария курьеру
    private final String comment;

    public RentScooterTest(int orderButton, String firstName, String secondName, String streetName, String metroStation, String phoneNumber, String data, int colour, String comment) {
        this.orderButton = orderButton;
        this.firstName = firstName;
        this.secondName = secondName;
        this.streetName = streetName;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.data = data;
        this.colour=colour;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Перебор различных способох входа, допустимы значений: {0} {1} {2} {3} {4} {5} {6} {7} {8}")
    public static Object[][] getText() {
        return new Object[][] {
                //Общий тест что всё работает при обычных вариантах
                { 1, "Олег", "Олегович", "Маршал 1", "Щукинская", "89001234567", "25.07.2023", 1, "Больше самокатов богу самокатов"},
                { 1, "Олег", "Олегович", "Маршал 1", "Щукинская", "89001234567", "25.07.2023", 2, "Нет"},
                { 2, "Олег", "Олегович", "Маршал 1", "Щукинская", "89001234567", "25.07.2023", 1, "Еретики"},
                { 2, "Олег", "Олегович", "Маршал 1", "Щукинская", "89001234567", "25.07.2023", 2, "Непонял"},
                //Проверяем латиницу в имени
                { 1, "Sergey", "Сергей", "Питерская 12", "Сокольники", "89001234567","25.07.2023", 1, "Наш бог в Пицце"},
                { 1, "Sergey", "Сергей", "Питерская 12", "Сокольники", "89001234567","25.07.2023", 2, "Пицца это зло спота"},
                { 2, "Sergey", "Сергей", "Питерская 12", "Сокольники", "89001234567","25.07.2023", 1, "Я хочу пиццы"},
                { 2, "Sergey", "Сергей", "Питерская 12", "Сокольники", "89001234567","25.07.2023", 2, "Больше самокатов богу самокатов"},
                //Проверяем латиницу в Фамилии
                { 1, "Вячеслав", "Monkey", "Московская 10", "Киевская", "89001234567", "25.07.2023", 1, "Больше пиццы богу пиццы" },
                { 1, "Вячеслав", "Monkey", "Московская 10", "Киевская", "89001234567", "25.07.2023", 2, "Да начнётсярелигиозная вояна во имя пиццы и самоката" },
                { 2, "Вячеслав", "Monkey", "Московская 10", "Киевская", "89001234567", "25.07.2023", 1, "Да начнётся религиозная вояна во имя пиццы и самоката" },
                { 2, "Вячеслав", "Monkey", "Московская 10", "Киевская", "89001234567", "25.07.2023", 2, "А дальш мне лень придумывать, по этому до тестов комментариев будет комментаий из первой параметризации" },
                // Проверяем латиницу в улице
                { 1, "Анастасья", "Петрова", "Northern 10", "Шелепиха", "89001234567", "25.07.2023", 1, "Больше самокатов богу самокатов" },
                { 1, "Анастасья", "Петрова", "Northern 10", "Шелепиха", "89001234567", "25.07.2023", 2, "Больше самокатов богу самокатов" },
                { 2, "Анастасья", "Петрова", "Northern 10", "Шелепиха", "89001234567", "25.07.2023", 1, "Больше самокатов богу самокатов" },
                { 2, "Анастасья", "Петрова", "Northern 10", "Шелепиха", "89001234567", "25.07.2023", 2, "Больше самокатов богу самокатов" },
                // Проверяем латиницу в станции метро/Несушествующая станция метро
                { 1, "Алеся", "Ивановна", "Казанская 10", "Underground", "89001234567", "25.07.2023", 1, "Больше самокатов богу самокатов" },
                { 1, "Алеся", "Ивановна", "Казанская 10", "Underground", "89001234567", "25.07.2023", 2, "Больше самокатов богу самокатов" },
                { 2, "Алеся", "Ивановна", "Казанская 10", "Underground", "89001234567", "25.07.2023", 1, "Больше самокатов богу самокатов" },
                { 2, "Алеся", "Ивановна", "Казанская 10", "Underground", "89001234567", "25.07.2023", 2, "Больше самокатов богу самокатов" },
                // Проверяем несуществующий номер
                { 1, "Алеся", "Ивановна", "Казанская 10", "Барикадная", "+71234567890", "25.07.2023", 1, "Больше самокатов богу самокатов" },
                { 1, "Алеся", "Ивановна", "Казанская 10", "Барикадная", "+71234567890", "25.07.2023", 2, "Больше самокатов богу самокатов" },
                { 2, "Алеся", "Ивановна", "Казанская 10", "Барикадная", "+71234567890", "25.07.2023", 1, "Больше самокатов богу самокатов" },
                { 2, "Алеся", "Ивановна", "Казанская 10", "Барикадная", "+71234567890", "25.07.2023", 2, "Больше самокатов богу самокатов" },
                // Проверяем прошедшую дату
                { 1, "Алеся", "Ивановна", "Казанская 10", "Менделеевская", "89001234567", "25.07.1999", 1, "Больше самокатов богу самокатов" },
                { 1, "Алеся", "Ивановна", "Казанская 10", "Менделеевская", "89001234567", "25.07.1999", 2, "Больше самокатов богу самокатов" },
                { 2, "Алеся", "Ивановна", "Казанская 10", "Менделеевская", "89001234567", "25.07.1999", 1, "Больше самокатов богу самокатов" },
                { 2, "Алеся", "Ивановна", "Казанская 10", "Менделеевская", "89001234567", "25.07.1999", 2, "Больше самокатов богу самокатов" },
                // Проверяем комментарий на латинице
                { 1, "Алеся", "Ивановна", "Казанская 10", "Савёловская", "89001234567", "25.07.2023", 1, "Some comment" },
                { 1, "Алеся", "Ивановна", "Казанская 10", "Савёловская", "89001234567", "25.07.2023", 2, "Some comment" },
                { 2, "Алеся", "Ивановна", "Казанская 10", "Савёловская", "89001234567", "25.07.2023", 1, "Some comment" },
                { 2, "Алеся", "Ивановна", "Казанская 10", "Underground", "89001234567", "25.07.2023", 2, "Some comment" },
        };
    }

    @Before
    public void startBrowser(){
        WebDriverManager.chromedriver().setup();
        //WebDriverManager.firefoxdriver().setup();
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @Test
    public void checkActivity() {
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