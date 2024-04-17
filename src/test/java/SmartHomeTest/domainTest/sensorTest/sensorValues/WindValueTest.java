package SmartHomeTest.domainTest.sensorTest.sensorValues;

import smarthome.domain.sensor.values.WindValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindValueTest {

    /**
     * Test case to verify that constructor throws IllegalArgumentException when reading is null.
     */
    @Test
    void givenNullReading_ConstructorThrowsIllegalArgumentException() {
        // Arrange
        String expected = "Invalid reading";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new WindValue(null));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test case to verify that constructor throws IllegalArgumentException when reading format is invalid.
     * By invalid format in this scenario we mean a reading without regex ":" that will prevent a correct split
     * and throw an IllegalArgument Exception.
     */
    @Test
    void whenInvalidFormatReading_ShouldThrowIllegalArgumentException() {
        // Arrange
        String reading = "22N";
        String expected = "Invalid reading";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new WindValue(reading));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test case to verify that the `WindValue` constructor throws an `IllegalArgumentException`
     * when an invalid format reading (e.g., "0N") is provided at the lower border (0).
     * It ensures that the constructor correctly handles invalid format readings.
     */
    @Test
    void whenInvalidFormatReading_ShouldThrowIllegalArgumentException_BorderCaseLower() {
        // Arrange
        String reading = "0N";
        String expected = "Invalid reading";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new WindValue(reading));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test case to verify that constructor throws IllegalArgumentException when wind speed is invalid.
     * By invalid format in this scenario we mean a reading with an invalid wind speed format that will prevent the parse to integer
     * and throw an IllegalArgument Exception.
     */
    @Test
    void whenInvalidWindSpeed_ShouldThrowIllegalArgumentException() {
        // Arrange
        String reading = "3K:N";
        String expected = "Invalid reading";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new WindValue(reading));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test case to verify that constructor throws an IllegalArgumentException when wind direction is invalid.
     * By invalid format in this scenario we mean a reading with an invalid wind direction that does not match with any one
     * defined in Wind Value class, throwing an IllegalArgument Exception.
     */
    @Test
    void whenInvalidWindDirection_ShouldThrowIllegalArgumentException() {
        // Arrange
        String reading = "33:J";
        String expected = "Invalid reading";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new WindValue(reading));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test case to verify that constructor throws IllegalArgumentException when wind speed is negative.

     */
    @Test
    void whenNegativeWindSpeed_ShouldThrowIllegalArgumentException() {
        // Arrange
        String reading = "-58:J";
        String expected = "Invalid reading";

        // Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new WindValue(reading));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test case to verify that getValue() method returns an array with correct wind speed and direction.
     * It´s created an array with the expected values and the assertion is made by matching it with the resulting getValue() one.
     */
    @Test
    void getValue_ShouldReturnArrayWithReadingValues() {
        // Arrange
        String reading = "58:N";
        String[] expected = new String[2];
        expected[0] = "58";
        expected[1] = "N";
        WindValue windValue = new WindValue(reading);

        // Act
        String[] result = windValue.getValue();

        // Assert
        assertArrayEquals(expected, result);
    }

    /**
     * Test case to verify that getValue() method returns an array with wind direction in uppercase.
     * It is passed a reading with a valid lower case direction and checked if that direction is correctly converted to upper case
     */
    @Test
    void getValue_WhenLowerCaseDirection_ShouldReturnArrayWithUpperCaseDirection() {
        // Arrange
        String reading = "78:s";
        String[] expected = new String[2];
        expected[0] = "78";
        expected[1] = "S";
        WindValue windValue = new WindValue(reading);

        // Act
        String[] result = windValue.getValue();

        // Assert
        assertArrayEquals(expected, result);
    }
}
