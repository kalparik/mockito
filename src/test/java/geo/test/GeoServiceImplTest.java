package geo.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

public class GeoServiceImplTest {
    GeoServiceImpl sut;

    @BeforeEach
    public void beforeEach() {
        System.out.println("BeforeEach");
        sut = new GeoServiceImpl();
    }

    @AfterEach
    public void afterEach() {
        System.out.println("AfterEach");
        sut = null;
    }

    public static Stream<Arguments> byIpTest() {
        return Stream.of(
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.0.00.00", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.00.000.000", new Location("New York", Country.USA, null, 0))
        );
    }

    @ParameterizedTest
    @MethodSource
    public void byIpTest(String ip, Location expected) {
        // arrange

        //act
        Location result = sut.byIp(ip);
        //assert
        Assertions.assertEquals(expected, result);

    }

    @ParameterizedTest
    @MethodSource("byIpTest")
    public void byIpTest_without_override_equals(String ip, Location expected) {
        // arrange

        //act
        Location result = sut.byIp(ip);
        //assert
        Assertions.assertEquals(expected.getCity(), result.getCity());
        Assertions.assertEquals(expected.getCountry(), result.getCountry());
        Assertions.assertEquals(expected.getStreet(), result.getStreet());
        Assertions.assertEquals(expected.getBuiling(), result.getBuiling());

    }

    @Test
    public void byCoordinatesTest() {
        // arrange
        double latitude = 0, longitude = 0;
        //act

        //assert
        Assertions.assertThrows(RuntimeException.class, () -> sut.byCoordinates(latitude, longitude));
    }
}
