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
    public class HomeFourPartTest{
        private static  WebDriver driver;
        //Переменная в который храниться текст каждого ответа FAQ
        private final String textOfFAQ;
        //Id по которому будет вызываться нужный метод
        private final int idWay;
        public HomeFourPartTest(String textOfFAQ,  int idWay) {
            this.textOfFAQ = textOfFAQ;
            this.idWay = idWay;
        }

        @Parameterized.Parameters(name = "Соответствие текста. Тестовые данные: {0} {1}")
        public static Object[][] getText() {
            return new Object[][] {
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", 0},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", 1},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", 2},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.", 3},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", 4},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", 5},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", 6},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.", 7},
                    /*
                    * Так как не было исходного ТЗ по тексту FAQ исходные данные брал с сайта. Это конечно не правильно, но что поделать
                    */
            };
        }
        @Before
        public void startBrowser(){
            //WebDriverManager.chromedriver().setup();
            WebDriverManager.firefoxdriver().setup();
            //driver = new ChromeDriver();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get("https://qa-scooter.praktikum-services.ru/");
    }
        @Test
        public void checkActivity() {
            HomeFourPart objHomeFourPart = new HomeFourPart(driver);
            String result = objHomeFourPart.getTextOfFAQ(idWay);
            MatcherAssert.assertThat("Текст неверен", result, is(textOfFAQ));
        }
        @After
            public void teardown() {
            driver.quit();
        }
    }