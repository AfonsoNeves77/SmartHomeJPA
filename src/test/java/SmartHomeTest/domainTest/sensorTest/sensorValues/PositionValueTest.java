package SmartHomeTest.domainTest.sensorTest.sensorValues;

import smarthome.domain.sensor.values.PositionValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PositionValueTest {

    /**
     * Given a reading below 0, the constructor throws IllegalArgumentException.
     */
    @Test
    void constructor_throwsInstantiationExceptionIfReadingIsBelow0() {
        //Arrange
        int reading = -1;
        String expected = "Invalid Position Value, has to be between 0 and 100";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new PositionValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     *  Given a reading above 100, the constructor throws IllegalArgumentException.
     */
    @Test
    void constructor_throwsInstantiationExceptionIfReadingIsAbove100() {
        //Arrange
        int reading = 101;
        String expected = "Invalid Position Value, has to be between 0 and 100";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new PositionValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Given a reading within 0 and 100, the constructor creates a PositionValue object and the value
     * is successfully retrieved through the getValue method.
     */
    @Test
    void getValue_returnsSuccessfullyWhenReadingIsWithin0And100() {
        //Arrange
        int reading = 20;
        int expected = 20;
        //Act
        PositionValue value = new PositionValue(reading);
        //Assert
        assertEquals(expected, value.getValue());
    }

    /**
     * Test case to verify the behavior of the `getValue` method in the `PositionValue` class
     * when the reading is at the lower border (0). It ensures that the `getValue` method
     * correctly returns 0 when the reading is 0.
     */
    @Test
    void getValue_returnsSuccessfullyWhenReadingIsWithin0And100_BorderCaseLower() {
        //Arrange
        int reading = 0;
        int expected = 0;
        //Act
        PositionValue value = new PositionValue(reading);
        //Assert
        assertEquals(expected, value.getValue());
    }

    /**
     * Test case to verify the behavior of the `getValue` method in the `PositionValue` class
     * when the reading is at the upper border (100). It ensures that the `getValue` method
     * correctly returns 100 when the reading is 100.
     */
    @Test
    void getValue_returnsSuccessfullyWhenReadingIsWithin0And100_BorderCaseUpper() {
        //Arrange
        int reading = 100;
        int expected = 100;
        //Act
        PositionValue value = new PositionValue(reading);
        //Assert
        assertEquals(expected, value.getValue());
    }
}
