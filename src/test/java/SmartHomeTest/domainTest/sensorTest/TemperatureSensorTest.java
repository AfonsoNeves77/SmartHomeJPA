package SmartHomeTest.domainTest.sensorTest;

import smarthome.domain.sensor.values.TemperatureValue;
import smarthome.domain.sensor.externalservices.SimHardware;

import smarthome.domain.sensor.TemperatureSensor;
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

class TemperatureSensorTest {
    /**
     * Test if the constructor throws an exception when the name is null.
     */
    @Test
    void whenSensorNameIsNull_thenExceptionIsThrown() {
        //Arrange
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        String expected = "Invalid parameters.";
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new TemperatureSensor(null, deviceID, sensorType));
        String result = exception.getMessage();

        List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

        //Assert
        assertEquals(expected, result);
        assertEquals(0, listOfMockedSensorIDVO.size());
    }
    }

    /**
     * Test if the constructor throws an exception when the name is empty.
     */
    @Test
    void whenSensorNameIsEmpty_thenExceptionIsThrown() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        String expected = "Invalid parameters.";
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new TemperatureSensor(sensorName, null, sensorType));
        String result = exception.getMessage();

        List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

        //Assert
        assertEquals(expected, result);
        assertEquals(0, listOfMockedSensorIDVO.size());
    }
}

    /**
     * Test if the constructor throws an exception when the sensorType is null.
     */
        @Test
        void whenSensorTypeIsNull_thenExceptionIsThrown() {
            //Arrange
            SensorNameVO sensorName = mock(SensorNameVO.class);
            DeviceIDVO deviceID = mock(DeviceIDVO.class);
            String expected = "Invalid parameters.";
            try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new TemperatureSensor(sensorName, deviceID, null));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(0, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * Test if the constructor returns the correct sensor name.
     */
    @Test
    void whenSensorIsSuccessfullyCreated_thenReturnsSensorName(){
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        when(sensorName.toString()).thenReturn("Sensor1");
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        String expected = "Sensor1";
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {
            TemperatureSensor sensor = new TemperatureSensor(sensorName, deviceID, sensorType);

        //Act
        String result = sensor.getSensorName().toString();
        List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

        //Assert
        assertEquals(expected, result);
        assertEquals(1, listOfMockedSensorIDVO.size());
    }
}

    /**
     * Test if the constructor returns the correct sensor ID.
     */
    @Test
    void whenSensorIsSuccessfullyCreated_thenReturnsSensorID() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);

        String expected = "08";
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class, (mock, context) ->
                when(mock.toString()).thenReturn("08"))) {
            TemperatureSensor sensor = new TemperatureSensor(sensorName, deviceID, sensorType);

            //Act
            String result = sensor.getId().toString();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(1, listOfMockedSensorIDVO.size());
            assertEquals(expected, result);
        }
    }

    /**
     * Test if the correct sensor type is returned, after the sensor is created.
     */
    @Test
    void whenSensorIsSuccessfullyCreated_thenReturnsSensorType() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        when(sensorType.toString()).thenReturn("08");
        String expected = "08";
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {
            TemperatureSensor sensor = new TemperatureSensor(sensorName, deviceID, sensorType);
        //Act
        String result = sensor.getSensorTypeID().toString();
        List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

        //Assert
        assertEquals(expected, result);
        assertEquals(1, listOfMockedSensorIDVO.size());
    }
}

    /**
     * Test if the correct device ID is returned, after the sensor is created.
     */
    @Test
    void whenSensorIsSuccessfullyCreated_thenReturnsDeviceID() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        when(deviceID.toString()).thenReturn("08");
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        String expected = "08";
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {
            TemperatureSensor sensor = new TemperatureSensor(sensorName, deviceID, sensorType);

        //Act
        String result = sensor.getDeviceID().toString();
        List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

        //Assert
        assertEquals(expected, result);
        assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * Test if the constructor throws an exception when the hardware is null.
     */
    @Test
    void whenSimHardwareIsNull_ThrowsIllegalArgumentException()
    {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {
            TemperatureSensor sensor = new TemperatureSensor(sensorName, deviceID, sensorType);
            String expected = "Invalid parameters.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                sensor.getReading(null));
        String result = exception.getMessage();
        List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

        //Assert
        assertEquals(expected, result);
        assertEquals(1, listOfMockedSensorIDVO.size());

        }
}

    /**
     * This test verifies if the getReading method returns a TemperatureValue with the value set at 50.
     * First, all the constructor's parameters are mocked and the temperature sensor is created.
     * Then, the expected value is set to "25" and the TemperatureValue constructor is mocked to return the expected value.
     * After that, the getReading method is called and turned to a String.
     * Meanwhile, a list of TemperatureValue is created to store the mocked TemperatureValue.
     * And create mockedConstruction for SensorIDVO.
     * Finally, the expected value is compared to the result and the size of the list is checked.
     */
    @Test
    void whenReadingIsCorrect_thenReturnsValueSuccessfully() {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        String expected = "36.1";

        try (MockedConstruction<TemperatureValue> temperatureValueDouble = mockConstruction(TemperatureValue.class, (mock, context) -> {
            when(mock.getValue()).thenReturn(36.1);
            when(mock.toString()).thenReturn("36.1");
        });
             MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            TemperatureSensor sensor = new TemperatureSensor(sensorName, deviceID, sensorType);

            //Act
            TemperatureValue actualTemperatureValue = (TemperatureValue) sensor.getReading(simHardware);
            List<TemperatureValue> constructedTemperatureValues = temperatureValueDouble.constructed();
            String actualTemperatureValueString = actualTemperatureValue.toString();

            //Assert
            assertEquals(expected, actualTemperatureValueString);
            assertEquals(1, constructedTemperatureValues.size());
        }
    }
}