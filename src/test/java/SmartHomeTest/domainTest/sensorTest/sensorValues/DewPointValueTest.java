package SmartHomeTest.domainTest.sensorTest.sensorValues;

import smarthome.domain.sensor.values.DewPointValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DewPointValueTest {
    @Test
    void constructor_throwsInstantiationExceptionIfReadingNull() {
        //Arrange
        String reading = null;
        String expected = "Invalid DewPoint reading";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DewPointValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void constructor_throwsInstantiationExceptionIfReadingEmpty() {
        //Arrange
        String reading = " ";
        String expected = "Invalid DewPoint reading";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DewPointValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getValue_returnsSuccessfullyWhenReadingIsValid() {
        //Arrange
        String reading = "5.0";
        double expected = 5.0;
        //Act
        DewPointValue value = new DewPointValue(reading);
        //Assert
        assertEquals(expected, value.getValue());
    }
    @Test
    void constructor_throwsInstantiationExceptionIfReadingUnableToParse(){
        //Arrange
        String reading = "this will fail";
        String expected = "Invalid DewPoint reading";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DewPointValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }
}

