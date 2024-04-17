package SmartHomeTest.domainTest.sensorTest.sensorValues;

import smarthome.domain.sensor.values.EnergyConsumptionValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnergyConsumptionValueTest {

    /**
     * Tests that if reading is null, then an InstantiationException is thrown
     */
    @Test
    void whenReadingIsNull_thenThrowsInstantiationException(){
        //Arrange
        String reading = null;
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new EnergyConsumptionValue(reading));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Tests that if reading is empty, then an InstantiationException is thrown
     */
    @Test
    void whenReadingIsEmpty_thenThrowsInstantiationException(){
        //Arrange
        String reading = " ";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new EnergyConsumptionValue(reading));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Tests that if reading is in invalid format, then an InstantiationException is thrown
     */
    @Test
    void whenReadingIsInInvalidFormat_thenThrowsInstantiationException(){
        //Arrange
        String reading = "this will fail";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new EnergyConsumptionValue(reading));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Tests that if reading is negative, then an InstantiationException is thrown
     */
    @Test
    void whenReadingIsNegative_thenThrowsInstantiationException(){
        //Arrange
        String reading = "-1";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new EnergyConsumptionValue(reading));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Tests that if reading is valid, then it is successfully returned
     */
    @Test
    void whenReadingIsValid_thenItIsReturned() throws InstantiationException {
        //Arrange
        String reading = "1";
        int expected = 1;

        //Act
        EnergyConsumptionValue energyConsumptionValue = new EnergyConsumptionValue(reading);
        int result = energyConsumptionValue.getValue();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test case to verify the behavior of the `EnergyConsumptionValue` constructor
     * when a valid reading is provided at the lower border (0). It ensures that the constructor
     * correctly creates an instance with the provided reading, which should be 0.
     */
    @Test
    void whenReadingIsValid_thenItIsReturned_BorderCaseLower() throws InstantiationException {
        //Arrange
        String reading = "0";
        int expected = 0;

        //Act
        EnergyConsumptionValue energyConsumptionValue = new EnergyConsumptionValue(reading);
        int result = energyConsumptionValue.getValue();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Tests that if reading is valid, then it is successfully returned as String
     */
    @Test
    void whenReadingIsValid_thenItIsReturnedAsString() throws InstantiationException {
        //Arrange
        String reading = "1";
        String expected = "1";

        //Act
        EnergyConsumptionValue energyConsumptionValue = new EnergyConsumptionValue(reading);
        String result = energyConsumptionValue.getValueAsString();

        //Assert
        assertEquals(expected,result);
    }
}