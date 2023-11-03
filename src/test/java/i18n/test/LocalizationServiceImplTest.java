package i18n.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

import static ru.netology.entity.Country.*;

public class LocalizationServiceImplTest {
    LocalizationService sut;

    @BeforeEach
    public void beforeEach() {
        System.out.println("BeforeEach");
        sut = new LocalizationServiceImpl();
    }

    @AfterEach
    public void afterEach() {
        System.out.println("AfterEach");
        sut = null;
    }

    public static Stream<Arguments> localeTest() {
        return Stream.of(
                Arguments.of(RUSSIA, "Добро пожаловать"),
                Arguments.of(USA, "Welcome"),
                Arguments.of(GERMANY, "Welcome"),
                Arguments.of(BRAZIL, "Welcome")
        );
    }

    @ParameterizedTest
    @MethodSource
    public void localeTest(Country country, String expected) {
        // arrange

        //act
        String result = sut.locale(country);
        //assert
        Assertions.assertEquals(expected, result);
    }
}
