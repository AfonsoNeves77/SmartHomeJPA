package SmartHomeTest.domainTest.sensorTest.sensorValues;

import smarthome.domain.sensor.values.SwitchValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwitchValueTest {
    @Test
    void constructor_throwsInstantiationExceptionIfReadingNull() {
        //Arrange
        String reading = null;
        String expected = "Invalid switch value";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SwitchValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void constructor_throwsInstantiationExceptionIfReadingEmpty() {
        //Arrange
        String reading = " ";
        String expected = "Invalid switch value";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SwitchValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void constructor_throwsInstantiationExceptionIfReadingNotOnNorOffNorNull() {
        //Arrange
        String reading = "this will fail because it is not On or Off";
        String expected = "Invalid switch value";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SwitchValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }


    @Test
    void getValue_returnsSuccessfullyWhenReadingIsOn() {
        //Arrange
        String reading = "On";
        String expected = "On";
        //Act
        SwitchValue value = new SwitchValue(reading);
        //Assert
        assertEquals(expected, value.getValue());
    }

    @Test
    void getValue_returnsSuccessfullyWhenReadingIsOff() {
        //Arrange
        String reading = "Off";
        String expected = "Off";
        //Act
        SwitchValue value = new SwitchValue(reading);
        //Assert
        assertEquals(expected, value.getValue());
    }

    @Test
    void getValueAsString_returnsSuccessfullyWhenReadingIsOff() {
        //Arrange
        String reading = "Off";
        String expected = "Off";
        //Act
        SwitchValue value = new SwitchValue(reading);
        //Assert
        assertEquals(expected, value.getValue());
    }

    @Test
    void getValueAsString_returnsSuccessfullyWhenReadingIsOn() {
        //Arrange
        String reading = "On";
        String expected = "On";
        //Act
        SwitchValue value = new SwitchValue(reading);
        //Assert
        assertEquals(expected, value.getValue());
    }
}