package SmartHomeTest.domainTest.sensorTest;

import smarthome.domain.sensor.PositionSensor;
import smarthome.domain.sensor.externalservices.SimHardware;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.sensortype.SensorTypeIDVO;
import smarthome.vo.sensorvo.SensorIDVO;
import smarthome.vo.sensorvo.SensorNameVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PositionSensorTest {

    /**
     * Given a null SensorNameVO, the constructor throws IllegalArgumentException.
     */
    @Test
    void givenNullSensorNameVO_ThrowsIllegalArgument() {
        // Arrange
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        String expected = "Invalid Parameters";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            // Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new PositionSensor(null, deviceID, sensorTypeID));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(0, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * Given a null DeviceIDVO, the constructor throws IllegalArgumentException.
     */
    @Test
    void givenNullDeviceID_ThrowsIllegalArgument() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        String expected = "Invalid Parameters";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            // Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new PositionSensor(sensorName, null, sensorTypeID));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(0, listOfMockedSensorIDVO.size());
        }
    }

    /**
     *  Given a null SensorTypeIDVO, the constructor throws IllegalArgumentException.
     */
    @Test
    void givenNullSensorTypeID_ThrowsIllegalArgument() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        String expected = "Invalid Parameters";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            // Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new PositionSensor(sensorName, deviceID, null));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(0, listOfMockedSensorIDVO.size());
        }
    }

    /**
     *  When GetSensorName method is called, Then Returns Name.
     */
    @Test
    void whenGetSensorNameMethodIsCalled_ThenReturnsName() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        when(sensorName.toString()).thenReturn("Test");

        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);

        String expected = "Test";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            PositionSensor sensor = new PositionSensor(sensorName, deviceID, sensorTypeID);

            // Act
            String result = sensor.getSensorName().toString();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     *  When GetSensorType method is called, Then Returns SensorTypeID.
     */
    @Test
    void whenGetSensorTypeMethodIsCalled_ThenReturnsSensorTypeID() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);

        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        when(sensorTypeID.getID()).thenReturn("Test");

        String expected = "Test";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            PositionSensor sensor = new PositionSensor(sensorName, deviceID, sensorTypeID);

            // Act
            String result = sensor.getSensorTypeID().getID();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }


    /**
     *  When GetDeviceID method is called, Then Returns DeviceID.
     */
    @Test
    void whenGetDeviceIDMethodIsCalled_thenReturnsDeviceID() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);

        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        when(deviceID.getID()).thenReturn("Test");

        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);


        String expected = "Test";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            PositionSensor sensor = new PositionSensor(sensorName, deviceID, sensorTypeID);

            // Act
            String result = sensor.getDeviceID().getID();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }


    /**
     *  When GetID method is called, Then Returns SensorID.
     */
    @Test
    void whenGetIDToGetSensorIDVOThenCallingGetIDAgainToGetStringAreCalled_ThenReturnsSensorIDAsString() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);

        String expected = "Test";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class, (mock, context) ->
                when(mock.getID()).thenReturn(expected))) {

            PositionSensor sensor = new PositionSensor(sensorName, deviceID, sensorTypeID);
            // Act
            String result = sensor.getId().getID();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * Given a null SimHardware, the getReading method throws IllegalArgumentException.
     */
    @Test
    void givenNullSimHardware_ThenThrowsIllegalArgument() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        PositionSensor sensor = new PositionSensor(sensorName, deviceID, sensorTypeID);
        int expected = 0;
        String expected2 = "Invalid external service";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            // Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    sensor.getReading(null));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, listOfMockedSensorIDVO.size());
            assertEquals(expected2, result);
        }
    }

    /**
     * Given a valid SimHardware, the getReading method returns PositionValue.
     * @throws InstantiationException If the external service is null.
     */
    @Test
    void whenGivenValidParameters_ThenReturnsPositionValueInteger() throws InstantiationException {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        SimHardware simHardware = mock(SimHardware.class);
        PositionSensor sensor = new PositionSensor(sensorName, deviceID, sensorTypeID);

        String reading = "5";
        int expected = 5;

        when(simHardware.getValue()).thenReturn(reading);

        // Act
        int result = sensor.getReading(simHardware).getValue();

        // Assert
        assertEquals(expected, result);
    }
}
