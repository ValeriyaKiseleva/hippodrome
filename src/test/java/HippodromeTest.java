import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

class HippodromeTest {

    @Test
    void hippodromeShouldThrowExceptionWhenNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void hippodromeShouldThrowThisTextExceptionWhenNull() {
        RuntimeException exception =
                assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void hippodromeShouldThrowExceptionWhenListIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void hippodromeShouldThrowThisTextExceptionWhenListIsEmpty() {
        RuntimeException exception =
                assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesShouldReturnSameListWhichIsInConstructor() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 31; i++ ) {
            horses.add(new Horse("HorseNumber" + i, i, 2+i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }


    @Test
    void moveShouldCallMethodMoveForEachHorse() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++ ) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse oneHorse : horses) {
            Mockito.verify(oneHorse).move();
        }
    }

    @Test
    void getWinnerShouldReturnHorseWithBiggestDistance() {
        //List<Horse> horses = new ArrayList<>();
        Horse horse1 = new Horse("HorseNumber1", 5, 13);
        Horse horse2 = new Horse("HorseNumber2", 5, 18);
        Horse horse3 = new Horse("HorseNumber3", 5, 15);
        Hippodrome hippodrome = new Hippodrome(Arrays.asList(horse1, horse2, horse3));

        assertEquals(horse2, hippodrome.getWinner());


    }

}