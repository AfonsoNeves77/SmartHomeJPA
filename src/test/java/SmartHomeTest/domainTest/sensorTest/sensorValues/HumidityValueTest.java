package SmartHomeTest.domainTest.sensorTest.sensorValues;

import smarthome.domain.sensor.values.HumidityValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HumidityValueTest {

    /**
     * This test verifies if the constructor throws an InstantiationException when the reading is null.
     * First, the reading is set to null, then the expected message is set to "Invalid reading".
     * Then, the constructor is called with the reading as a parameter, and the exception is thrown.
     * After that, the exception message is attributed to String to be compared to the expected message.
     * @throws InstantiationException
     */
    @Test
    void whenReadingIsNull_throwsInstantiationException() throws InstantiationException {
        //Arrange
        String reading = null;
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new HumidityValue(reading));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * This test verifies if the constructor throws an InstantiationException when the reading is empty.
     * First, the reading is set to " ", then the expected message is set to "Invalid reading".
     * Then, the constructor is called with the reading as a parameter, and the exception is thrown.
     * After that, the exception message is attributed to String to be compared to the expected message.
     * @throws InstantiationException
     */
    @Test
    void whenReadingIsEmpty_throwsInstantiationException() throws InstantiationException {
        //Arrange
        String reading = " ";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new HumidityValue(reading));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * This test verifies if the constructor throws an InstantiationException when the reading is not parsable.
     * First, the reading is set to "this will fail", then the expected message is set to "Invalid reading".
     * Then, the constructor is called with the reading as a parameter, and the exception is thrown.
     * After that, the exception message is attributed to String to be compared to the expected message.
     * @throws InstantiationException
     */
    @Test
    void whenReadingNotParsable_throwsInstantiationException() throws InstantiationException {
        //Arrange
        String reading = "this will fail";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new HumidityValue(reading));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * This test verifies if the constructor throws an InstantiationException when the reading is above bounds.
     * First, the reading is set to "101", then the expected message is set to "Invalid reading".
     * Then, the constructor is called with the reading as a parameter, and the exception is thrown.
     * After that, the exception message is attributed to String to be compared to the expected message.
     * @throws InstantiationException
     */
    @Test
    void whenReadingAboveBounds_throwsInstantiationException() throws InstantiationException {
        //Arrange
        String reading = "101";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new HumidityValue(reading));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * This test verifies if the constructor throws an InstantiationException when the reading is below bounds.
     * First, the reading is set to "-1", then the expected message is set to "Invalid reading".
     * Then, the constructor is called with the reading as a parameter, and the exception is thrown.
     * After that, the exception message is attributed to String to be compared to the expected message.
     * @throws InstantiationException
     */
    @Test
    void whenReadingBelowBounds_throwsInstantiationException() throws InstantiationException {
        //Arrange
        String reading = "-1";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new HumidityValue(reading));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * This test verifies if the constructor returns the correct value when the reading is on the lower bound.
     * First, the reading is set to "0", then the expected value is set to 0.
     * Then, the constructor is called with the reading as a parameter, and the value is stored.
     * After that, the value is compared to the expected value using getValue method.
     * @throws InstantiationException
     */
    @Test
    void whenReadingOnLowerBound_thenValueIsReturned() throws InstantiationException {
        //Arrange
        String reading = "0";
        int expected = 0;

        //Act
        HumidityValue value = new HumidityValue(reading);

        //Assert
        assertEquals(expected,value.getValue());
    }

    /**
     * This test verifies if the constructor returns the correct value when the reading is on the upper bound.
     * First, the reading is set to "100", then the expected value is set to 100.
     * Then, the constructor is called with the reading as a parameter, and the value is stored.
     * After that, the value is compared to the expected value using getValue method.
     * @throws InstantiationException
     */
    @Test
    void whenReadingOnUpperBound_thenValueIsReturned() throws InstantiationException {
        //Arrange
        String reading = "100";
        int expected = 100;

        //Act
        HumidityValue value = new HumidityValue(reading);

        //Assert
        assertEquals(expected,value.getValue());
    }

    /**
     * This test verifies if the constructor returns the correct value when the reading is within bounds and the getValue method is called.
     * First, the reading is set to "3", then the expected value is set to 3.
     * Then, the constructor is called with the reading as a parameter, and the value is stored.
     * After that, the value is compared to the expected value using getValue method.
     * @throws InstantiationException
     */
    @Test
    void whenGetValueCalledWithValidValue_thenValueIsReturned() throws InstantiationException {
        //Arrange
        String doubleValue = "3";
        int expected = 3;

        //Act
        HumidityValue value = new HumidityValue(doubleValue);

        //Assert
        assertEquals(expected,value.getValue());
    }
}