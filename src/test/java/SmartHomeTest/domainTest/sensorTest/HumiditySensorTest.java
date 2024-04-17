package SmartHomeTest.domainTest.sensorTest;



import smarthome.domain.sensor.HumiditySensor;
import smarthome.domain.sensor.externalservices.SimHardware;
import smarthome.domain.sensor.values.HumidityValue;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.sensortype.SensorTypeIDVO;
import smarthome.vo.sensorvo.SensorIDVO;
import smarthome.vo.sensorvo.SensorNameVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HumiditySensorTest {

    /**
     * This test verifies if the constructor throws an IllegalArgumentException when the sensor name is null.
     * First, the sensor name is set to null, then the expected message is set to "Parameters cannot be null".
     * Then, the constructor is called with the sensor name as a parameter, and the exception is thrown.
     * After that, the exception message is compared to the expected message.
     */
    @Test
    void whenSensorNameIsNull_throwsIllegalArgumentException() {
        //Arrange
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        String expected = "Parameters cannot be null";
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new HumiditySensor(null, deviceID, sensorTypeID));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(0, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test verifies if the constructor throws an IllegalArgumentException when the device ID is null.
     * First, the device ID is set to null, then the expected message is set to "Parameters cannot be null".
     * Then, the constructor is called with the device ID as a parameter, and the exception is thrown.
     * After that, the exception message is compared to the expected message.
     */
    @Test
    void whenDeviceIDIsNull_throwsIllegalArgumentException() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        String expected = "Parameters cannot be null";
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new HumiditySensor(sensorName, null, sensorTypeID));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(0, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test verifies if the constructor throws an IllegalArgumentException when the sensor type ID is null.
     * First, the sensor type ID is set to null, then the expected message is set to "Parameters cannot be null".
     * Then, the constructor is called with the sensor type ID as a parameter, and the exception is thrown.
     * After that, the exception message is compared to the expected message.
     */
    @Test
    void whenSensorTypeIDIsNull_throwsIllegalArgumentException() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        String expected = "Parameters cannot be null";
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new HumiditySensor(sensorName, deviceID, null));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(0, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test verifies if the Sensor ID is returned as a String when the getID method is called.
     * First, the parameters are mocked, then the expected value is set to "Test".
     * Then, the SensorIDVO constructor is mocked to return the expected value.
     * After that, the HumiditySensor constructor is called with the mocked parameters.
     * Then, the getID method is called, calling the SensorIDVO getID method, which returns a String.
     * Afterward, a list of SensorIDVO is created to store the mocked SensorIDVO.
     * Finally, the expected SensorID is compared to the result, and the size of the list is checked.
     */
    @Test
    void whenCallingGetIDToGetSensorIDVO_andCallingGetIDAgainToGetString_thenReturnsSensorIDAsString() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);

        String expected = "Test";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class, (mock, context) ->
                when(mock.getID()).thenReturn(expected))) {

            HumiditySensor sensor = new HumiditySensor(sensorName, deviceID, sensorTypeID);

            // Act
            String result = sensor.getId().getID();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected,result);
            assertEquals(1,listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test verifies if the constructor throws an IllegalArgumentException when the external service is null.
     * First, all the constructor's parameters are mocked and the external service is set to null.
     * Then, the sensorID is mocked and the sensor is created, to which follows the expected message, set to "Invalid external service".
     * Afterward, an exception is thrown when the getReading method is called, and the exception message stored.
     * After that, the list of SensorIDVO is created to store the mocked SensorIDVO.
     * Finally, the exception message is compared to the expected message and the size of the list checked.
     */
    @Test
    void whenSimHardwareIsNull_throwsIllegalArgumentException() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            HumiditySensor humiditySensor = new HumiditySensor(sensorName, deviceID, sensorTypeID);
            String expected = "Invalid external service";

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    humiditySensor.getReading(null));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test verifies if the getReading method returns a HumidityValue with the value set at 50.
     * First, all the constructor's parameters are mocked and the humidity sensor is created.
     * Then, the expected value is set to "50" and the HumidityValue constructor is mocked to return the expected value.
     * After that, the getReading method is called and turned to a String.
     * Meanwhile, a list of HumidityValue is created to store the mocked HumidityValue.
     * Finally, the expected value is compared to the result and the size of the list checked.
     */
    @Test
    void whenGetReadingCalled_ReturnsValue() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        HumiditySensor sensor = new HumiditySensor(sensorName, deviceID, sensorType);

        String expected = "50";


        try (MockedConstruction<HumidityValue> mockedConstruction = mockConstruction(HumidityValue.class, (mock, context)
                -> when(mock.getValue()).thenReturn(50))) {

            //Act
            HumidityValue value = (HumidityValue) sensor.getReading(simHardware);
            String result = value.getValue().toString();

            List<HumidityValue> listOfMockedHumidityValue = mockedConstruction.constructed();


            //Assert
            assertEquals(1, listOfMockedHumidityValue.size());
            assertEquals(expected, result);
        }
    }

    /**
     * This test verifies if the getSensorName method returns the sensor name.
     * First, the sensor name is mocked and the expected value is set to "Sensor".
     * Then, all the other constructor's parameters are mocked.
     * A string is set to the expected name and the SensorIDVO constructor mocked.
     * The humidity sensor is created and the getSensorName method called, being converted to String.
     * After that, a list of SensorIDVO is created to store the mocked SensorIDVO.
     * Finally, the expected value is compared to the result and the size of the list checked.
     */
    @Test
    void whenGetSensorNameCalled_ReturnsSensorName() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        when(sensorName.toString()).thenReturn("Sensor");
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        String expected = "Sensor";

        try(MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            HumiditySensor humiditySensor = new HumiditySensor(sensorName,deviceID,sensorTypeID);

            //Act
            String result = humiditySensor.getSensorName().toString();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(expected,result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test verifies if the getDeviceID method returns the device ID.
     * First, the sensor name is mocked, as well as the other constructor's parameters.
     * In this case, the device ID is mocked and the expected value is set to "XPTO".
     * The expected string is set to "XPTO" and the SensorIDVO constructor mocked.
     * Afterward, the humidity sensor is created and the getDeviceID method called, being converted to String.
     * Meanwhile, a list of SensorIDVO is created to store the mocked SensorIDVO.
     * Finally, the expected value is compared to the result and the size of the list checked.
     */
    @Test
    void whenGetDeviceIDCalled_ReturnsDeviceID() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        when(deviceID.toString()).thenReturn("XPTO");
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        String expected = "XPTO";

        try(MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            HumiditySensor humiditySensor = new HumiditySensor(sensorName,deviceID,sensorTypeID);

            //Act
            String result = humiditySensor.getDeviceID().toString();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(expected,result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test verifies if the getSensorTypeID method returns the sensor type ID.
     * First, the sensor name is mocked, as well as the other constructor's parameters.
     * In this case, the sensor type ID is mocked and the expected value is set to "XPTO".
     * The expected string is set to "XPTO" and the SensorIDVO constructor mocked.
     * Afterward, the humidity sensor is created and the getSensorTypeID method called, being converted to String.
     * Meanwhile, a list of SensorIDVO is created to store the mocked SensorIDVO.
     * Finally, the expected value is compared to the result and the size of the list checked.
     */
    @Test
    void whenGetSensorTypeCalled_ReturnsDeviceID() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        when(sensorTypeID.toString()).thenReturn("XPTO");
        String expected = "XPTO";

        try(MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            HumiditySensor humiditySensor = new HumiditySensor(sensorName,deviceID,sensorTypeID);

            //Act
            String result = humiditySensor.getSensorTypeID().toString();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(expected,result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }
}



