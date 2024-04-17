package SmartHomeTest.domainTest.sensorTest.sensorValues;

import smarthome.domain.sensor.values.PowerConsumptionValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PowerConsumptionValueTest {

    /**
     * Test for PowerConsumptionValue
     * Given a negative power consumption value, when the constructor is called, then an IllegalArgumentException is
     * thrown
     */
    @Test
    void constructor_throwsIllegalArgumentExceptionIfPowerConsumptionValueIsNegative() {
        //Arrange
        int powerConsumptionValue = -1;
        String expected = "Invalid power consumption value";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new PowerConsumptionValue(powerConsumptionValue));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for PowerConsumptionValue
     * Given a positive power consumption value, when the getValue method is called, then the value is returned
     */
    @Test
    void getValue_returnsSuccessfullyWhenPowerConsumptionValueIsPositive() {
        //Arrange
        int powerConsumptionValue = 1;
        int expected = 1;
        PowerConsumptionValue value = new PowerConsumptionValue(powerConsumptionValue);
        //Act
        int result = value.getValue();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for PowerConsumptionValue
     * Given a zero power consumption value, when the getValue method is called, then the value is returned
     */
    @Test
    void getValue_returnsSuccessfullyWhenPowerConsumptionValueIsZero() {
        //Arrange
        int powerConsumptionValue = 0;
        int expected = 0;
        PowerConsumptionValue value = new PowerConsumptionValue(powerConsumptionValue);
        //Act
        int result = value.getValue();
        //Assert
        assertEquals(expected, result);
    }

}