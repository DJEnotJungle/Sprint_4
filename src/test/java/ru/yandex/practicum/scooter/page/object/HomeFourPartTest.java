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
    public class HomeFourPartTest {
        private static  WebDriver driver;
        //Переменная в который храниться текст каждого ответа FAQ
        private final String textOfFAQ;
        //Id локатора по котому развернётся модуль с вопросом
        private final String answerOfFAQ;
        //Id локатора для снятия ответа FAQ
        private final String questionOfFAQ;
        public HomeFourPartTest(String textOfFAQ,  String answerOfFAQ, String questionOfFAQ) {
            this.textOfFAQ = textOfFAQ;
            this.answerOfFAQ = answerOfFAQ;
            this.questionOfFAQ = questionOfFAQ;
        }

        @Parameterized.Parameters
        public static Object[][] getText() {
            return new Object[][] {
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой.", "accordion__panel-0", "accordion__heading-0"},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", "accordion__panel-1", "accordion__heading-1"},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", "accordion__panel-2", "accordion__heading-2"},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее.", "accordion__panel-3", "accordion__heading-3"},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", "accordion__panel-4", "accordion__heading-4"},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", "accordion__panel-5", "accordion__heading-5"},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", "accordion__panel-6", "accordion__heading-6"},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области.", "accordion__panel-7", "accordion__heading-7"},
                    /*
                    * Так как не было исходного ТЗ по тексту FAQ исходные данные брал с сайта. Это конечно не правильно, но что поделать
                    */
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
            HomeFourPart objHomeFourPart = new HomeFourPart(driver);
            String result = objHomeFourPart.getTextOfFAQ(answerOfFAQ, questionOfFAQ);
            MatcherAssert.assertThat("Текст неверен", result, is(textOfFAQ));
        }


        @After
        public void teardown() {
            driver.quit();
        }
}