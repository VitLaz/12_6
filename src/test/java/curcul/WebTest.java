package curcul;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.sun.jdi.connect.Connector;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WebTest {

    @ValueSource(strings= {
            "ужасы аркхема",
            "Второй"

    })
    @ParameterizedTest (name = "Проверка поиска по слову {0}")
    void hbSerchTest (String testData) {
        Selenide.open("https://hobbygames.ru/");
        $(".input").setValue(testData).pressEnter();
        $$(".name-desc")
                .find(Condition.text(testData))
                .shouldBe(visible);
    }

    @CsvSource(value = {
            "ужасы аркхема|Ужас Аркхэма. Карточная игра",
            "Второй|Второй шанс. Сокровища майя"
    },
    delimiter = '|')
    @ParameterizedTest (name = "Проверка поиска по слову {0}, ожидаем результат: {1}")
    void hbSerchCSVTest (String testData, String testResult) {
        Selenide.open("https://hobbygames.ru/");
        $(".input").setValue(testData).pressEnter();
        $$(".name-desc")
                .find(Condition.text(testResult))
                .has(text(testData));
    }

    static Stream<Arguments> hdSerchMSTest() {
        return Stream.of(
                Arguments.of("ужасы аркхема", "Ужас Аркхэма. Карточная игра"),
                Arguments.of("Второй","Второй шанс. Сокровища майя")
        );
    }

    @MethodSource("hdSerchMSTest")
    @ParameterizedTest
    void hbSerchMSTest (String testData, String testResult) {
        Selenide.open("https://hobbygames.ru/");
        $(".input").setValue(testData).pressEnter();
        $$(".name-desc")
                .find(Condition.text(testResult))
                .has(text(testData));
    }

}
