package SmartHomeTest.domainTest.sensorTest;

import smarthome.domain.sensor.SunriseSensor;
import smarthome.domain.sensor.externalservices.SunTimeCalculator;
import smarthome.domain.sensor.values.SunTimeValue;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.sensortype.SensorTypeIDVO;
import smarthome.vo.sensorvo.SensorIDVO;
import smarthome.vo.sensorvo.SensorNameVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SunriseSensorTest {

    /**
     * This test ensures the constructor throws an Illegal Argument Exception when given a null VO. This test also
     * attempts to create a mocked construction of the sensorID, however as can be observed, the mocked construction
     * does not get to be created as it does not reach that stage.
     */
    @Test
    void givenNullNameVO_ThrowsIllegalArgument(){
        // Arrange
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        String expected = "Parameters cannot be null";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            // Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new SunriseSensor(null, deviceID, sensorTypeID));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(0,listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test ensures the constructor throws an Illegal Argument Exception when given a null VO. This test also
     * attempts to create a mocked construction of the sensorID, however as can be observed, the mocked construction
     * does not get to be created as it does not reach that stage.
     */
    @Test
    void givenNullDeviceID_ThrowsIllegalArgument(){
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        String expected = "Parameters cannot be null";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            // Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new SunriseSensor(sensorName, null, sensorTypeID));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(0,listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test ensures the constructor throws an Illegal Argument Exception when given a null VO. This test also
     * attempts to create a mocked construction of the sensorID, however as can be observed, the mocked construction
     * does not get to be created as it does not reach that stage.
     */
    @Test
    void givenNullSensorTypeID_ThrowsIllegalArgument(){
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        String expected = "Parameters cannot be null";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            // Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new SunriseSensor(sensorName, deviceID, null));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(0,listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test ensures the constructor throws an Illegal Argument Exception when given a null VO. This test also
     * attempts to create a mocked construction of the sensorID, however as can be observed, the mocked construction
     * does not get to be created as it does not reach that stage.
     */
    @Test
    void givenNullSunTimeCalculator_ThrowsIllegalArgument(){
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        SunriseSensor sensor = new SunriseSensor(sensorName,deviceID,sensorTypeID);

        String date = "2024-03-20";
        String gpsLocation = "88.1579 : 77.6291";

        String expected = "Invalid external service";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            // Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    sensor.getSunriseTime(date, gpsLocation, null));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(0,listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test ensures the method getSunriseTime returns an object of the type ZonedDateTimeDouble, which, when called
     * with getValue and toString, returns the encapsulated value.
     */
    @Test
    void givenValidParameters_ReturnsSunRiseValueToString(){
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        SunTimeCalculator sunTimeCalculator = mock(SunTimeCalculator.class);
        SunriseSensor sensor = new SunriseSensor(sensorName,deviceID,sensorTypeID);

        String date = "2024-03-20";
        String gpsLocation = "88.1579 : 77.6291";

        ZonedDateTime zonedDateTimeDouble = mock(ZonedDateTime.class);
        when(zonedDateTimeDouble.toString()).thenReturn("2024-03-06T18:31:47Z[Europe/Lisbon]");

        String expected = "2024-03-06T18:31:47Z[Europe/Lisbon]";

        try (MockedConstruction<SunTimeValue> mockedConstruction = mockConstruction(SunTimeValue.class, (mock, context)
                -> when(mock.getValue()).thenReturn(zonedDateTimeDouble))) {

            // Act
            String result = sensor.getSunriseTime(date, gpsLocation,sunTimeCalculator).getValue().toString();
            List<SunTimeValue> listOfMockedSunTimeValue = mockedConstruction.constructed();

            // Assert
            assertEquals(expected,result);
            assertEquals(1,listOfMockedSunTimeValue.size());
        }
    }

    /**
     * This test ensures the encapsulated VO's value is accessbile by calling getSensorName() : VO, then calling toString
     * on the same VO;
     */
    @Test
    void callingGetSensorNameToString_ReturnsNameVoAsString() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        when(sensorName.toString()).thenReturn("Test");

        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);

        String expected = "Test";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)){

            SunriseSensor sensor = new SunriseSensor(sensorName, deviceID, sensorTypeID);

            // Act
            String result = sensor.getSensorName().toString();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected,result);
            assertEquals(1,listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test ensures the encapsulated VO's value is accessbile by calling getSensorTypeID() : VO, then calling getID
     * again on the same VO to receive its value as String;
     */
    @Test
    void callingGetSensorTypeIDToString_ReturnsSensorTypeIDAsString() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);

        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        when(sensorTypeID.getID()).thenReturn("Test");

        String expected = "Test";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)){

            SunriseSensor sensor = new SunriseSensor(sensorName, deviceID, sensorTypeID);

            // Act
            String result = sensor.getSensorTypeID().getID();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected,result);
            assertEquals(1,listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test ensures the encapsulated VO's value is accessbile by calling getDeviceID() : VO, then calling getID
     * again on the same VO to receive its value as String;
     */
    @Test
    void callingGetDeviceIDWithgetID_ReturnsDeviceIDAsString() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);

        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        when(deviceID.getID()).thenReturn("Test");

        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);


        String expected = "Test";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)){

            SunriseSensor sensor = new SunriseSensor(sensorName, deviceID, sensorTypeID);

            // Act
            String result = sensor.getDeviceID().getID();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected,result);
            assertEquals(1,listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test ensures the encapsulated VO's value is accessbile by calling getID() : VO, then calling getID again
     * on the same VO to receive its value as String;
     */
    @Test
    void callingGetIDToGetSensorIDVOThenCallingGetIDAgainToGetString_ReturnsSensorIDAsString() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);

        String expected = "Test";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class, (mock, context) ->
                when(mock.getID()).thenReturn(expected))) {

            SunriseSensor sensor = new SunriseSensor(sensorName, deviceID, sensorTypeID);
            // Act
            String result = sensor.getId().getID();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected,result);
            assertEquals(1,listOfMockedSensorIDVO.size());
        }
    }
}
