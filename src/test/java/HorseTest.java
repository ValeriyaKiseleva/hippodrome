import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HorseTest {

    @Test
    void horseShouldThrowExceptionWhenNameISNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 5, 5));
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 5));
    }

    @Test
    void horseShouldThrowThisTextExceptionWhenNameIsNull() {
        RuntimeException exception1 = assertThrows
                (IllegalArgumentException.class, () -> new Horse(null, 5, 5));
        assertEquals("Name cannot be null.", exception1.getMessage());

        RuntimeException exception2 = assertThrows
                (IllegalArgumentException.class, () -> new Horse(null, 5));
        assertEquals("Name cannot be null.", exception2.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            " ",
            "\t",
            "\n"
    })
    void horseShouldThrowExceptionWhenNameISEmptyOrBlank(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 5, 5));
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 5));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            " ",
            "\t",
            "\n"
    })
    void horseShouldThrowThisTextExceptionWhenNameISEmptyOrBlank(String name) {
        RuntimeException exception1 = assertThrows
                (IllegalArgumentException.class, () -> new Horse(name, 5, 5));
        assertEquals("Name cannot be blank.", exception1.getMessage());

        RuntimeException exception2 = assertThrows
                (IllegalArgumentException.class, () -> new Horse(name, 5));
        assertEquals("Name cannot be blank.", exception2.getMessage());
    }


    @Test
    void horseShouldThrowExceptionWhenSpeedISNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("SomeName", -5, 5));
        assertThrows(IllegalArgumentException.class, () -> new Horse("SomeName", -5));
    }

    @Test
    void horseShouldThrowThisTextExceptionWhenSpeedISNegative() {
        RuntimeException exception1 = assertThrows
                (IllegalArgumentException.class, () -> new Horse("SomeName", -5, 5));
        assertEquals("Speed cannot be negative.", exception1.getMessage());

        RuntimeException exception2 = assertThrows
                (IllegalArgumentException.class, () -> new Horse("SomeName", -5));
        assertEquals("Speed cannot be negative.", exception2.getMessage());
    }

    @Test
    void horseShouldThrowExceptionWhenDistanceISNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("SomeName", 5, -5));
    }

    @Test
    void horseShouldThrowThisTextExceptionWhenDistanceISNegative() {
        RuntimeException exception1 = assertThrows
                (IllegalArgumentException.class, () -> new Horse("SomeName", 5, -5));
        assertEquals("Distance cannot be negative.", exception1.getMessage());
    }

    @Test
    void getNameShouldReturnFirstParamOfConstructor() {
        Horse horse = new Horse("SomeName", 5, 10);
        assertEquals("SomeName", horse.getName());
    }

    @Test
    void getSpeedShouldReturnSecondParamOfConstructor() {
        Horse horse = new Horse("SomeName", 5, 10);
        assertEquals(5, horse.getSpeed());
    }

    @Test
    void getDistanceShouldReturnThirdParamOfConstructor() {
        Horse horse = new Horse("SomeName", 5, 10);
        assertEquals(10, horse.getDistance());
    }

    @Test
    void getDistanceShouldReturnZeroWhenConstructorWithTwoParams() {
        Horse horse = new Horse("SomeName", 5);
        assertEquals(0, horse.getDistance());
    }


    @Test
    void moveShouldCallGetRandomDoubleWithRightParams() {
        try (MockedStatic mockStatic = Mockito.mockStatic(Horse.class)) {
            new Horse("SomeName", 5).move();

            mockStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));

        }
    }



    @Spy
    Horse someHorse = new Horse("SomeName", 5, 10);

    @ParameterizedTest
    @ValueSource(doubles = {
            2.0,
            5.0,
            0.5,
            1.9,
            0.0
    })
    void moveShouldReturnDistanceWhenGetRandomIs(double valueRandomDouble) {
        try (MockedStatic mockStatic = Mockito.mockStatic(Horse.class)) {
            Mockito.when(Horse.getRandomDouble(0.2, 0.9)).thenReturn(valueRandomDouble);

            someHorse.move();
            double distance = 10 + 5 * valueRandomDouble;

            assertEquals(distance, someHorse.getDistance());

        }


    }


}