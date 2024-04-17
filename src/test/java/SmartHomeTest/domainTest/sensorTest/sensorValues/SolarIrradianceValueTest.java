package SmartHomeTest.domainTest.sensorTest.sensorValues;

import smarthome.domain.sensor.values.SolarIrradianceValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolarIrradianceValueTest {

    /**
     * Success Scenario:
     * Verifies that when reading equals 0, a SolarIrradianceValue is created and encapsulates an integer equal to 0.
     */
    @Test
    void givenAReadingEqualToZero_whenGetValueIsInvoked_ThenShouldReturnZero(){
        //Arrange
        String reading = "0";
        int expected = 0;
        SolarIrradianceValue value = new SolarIrradianceValue(reading);

        //Act
        int result = value.getValue();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Success Scenario:
     * Verifies that when reading is higher than 0, a SolarIrradianceValue is created and encapsulates an integer
     * equal to the parsed reading value (in this case, 999).
     */
    @Test
    void givenAValidReadingHigherThanZero_whenGetValueIsInvoked_ThenShouldReturnTheCorrectIntegerValue(){
        //Arrange
        String reading = "999";
        int expected = 999;
        SolarIrradianceValue value = new SolarIrradianceValue(reading);

        //Act
        int result = value.getValue();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that when reading is negative, a SolarIrradianceValue is not created and an IllegalArgumentException is thrown.
     */
    @Test
    void givenANegativeReading_ThenShouldThrowIllegalArgumentException(){
        //Arrange
        String reading = "-700";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SolarIrradianceValue(reading);
        });
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that when reading is null, a SolarIrradianceValue is not created and an IllegalArgumentException is thrown.
     */
    @Test
    void givenANullReading_ThenShouldThrowIllegalArgumentException(){
        //Arrange
        String reading = null;
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SolarIrradianceValue(reading);
        });
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that when reading is blank, a SolarIrradianceValue is not created and an IllegalArgumentException is thrown.
     */
    @Test
    void givenABlankReading_ThenShouldThrowIllegalArgumentException(){
        //Arrange
        String reading = "";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SolarIrradianceValue(reading);
        });
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that when reading is a double, a SolarIrradianceValue is not created and an IllegalArgumentException is thrown.
     */
    @Test
    void givenAReadingWithDoubleNumbers_ThenShouldThrowIllegalArgumentException(){
        //Arrange
        String reading = "250.6";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SolarIrradianceValue(reading);
        });
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that when reading is not a number but some sort of word/phrase, a SolarIrradianceValue
     * is not created and an IllegalArgumentException is thrown.
     */
    @Test
    void givenAReadingWithADescription_ThenShouldThrowIllegalArgumentException(){
        //Arrange
        String reading = "Result = 700";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SolarIrradianceValue(reading);
        });
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }
}