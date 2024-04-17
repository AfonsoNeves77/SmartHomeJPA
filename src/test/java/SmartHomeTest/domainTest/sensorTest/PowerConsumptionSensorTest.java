package SmartHomeTest.domainTest.sensorTest;

import smarthome.domain.sensor.PowerConsumptionSensor;
import smarthome.domain.sensor.externalservices.SimHardware;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.sensortype.SensorTypeIDVO;
import smarthome.vo.sensorvo.SensorIDVO;
import smarthome.vo.sensorvo.SensorNameVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PowerConsumptionSensorTest {


    /**
     * Test for PowerConsumptionSensor
     * Given a null SensorNameVO, when the constructor is called, then an IllegalArgumentException is thrown
     */
    @Test
    void givenNullNameVO_throwsIllegalArgumentException() {
        //Arrange
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        String expected = "Parameters cannot be null";

        try(MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)){
            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () -> new PowerConsumptionSensor(null, deviceID, sensorTypeID));

            //Assert
            String result = exception.getMessage();
            List<SensorIDVO> createdInstances = mockedConstruction.constructed();
            assertEquals(expected, result);
            assertEquals(0, createdInstances.size());
        }
    }


    /**
     * Test for PowerConsumptionSensor
     * Given a null DeviceIDVO, when the constructor is called, then an IllegalArgumentException is thrown
     */
    @Test
    void givenNullDeviceID_throwsIllegalArgumentException() {
        //Arrange
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        SensorNameVO sensorName = mock(SensorNameVO.class);
        String expected = "Parameters cannot be null";

        try(MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)){
            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () -> new PowerConsumptionSensor(sensorName, null, sensorTypeID));

            //Assert
            String result = exception.getMessage();
            List<SensorIDVO> createdInstances = mockedConstruction.constructed();
            assertEquals(expected, result);
            assertEquals(0, createdInstances.size());
        }
    }


    /**
     * Test for PowerConsumptionSensor
     * Given a null SensorTypeIDVO, when the constructor is called, then an IllegalArgumentException is thrown
     */
    @Test
    void givenNullSensorTypeID_throwsIllegalArgumentException() {
        //Arrange
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorNameVO sensorName = mock(SensorNameVO.class);
        String expected = "Parameters cannot be null";

        try(MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)){
            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () -> new PowerConsumptionSensor(sensorName, deviceID, null));

            //Assert
            String result = exception.getMessage();
            List<SensorIDVO> createdInstances = mockedConstruction.constructed();
            assertEquals(expected, result);
            assertEquals(0, createdInstances.size());
        }
    }


    /**
     * Test for PowerConsumptionSensor
     * Given a null SimHardware, when getPowerConsumption is called, then an IllegalArgumentException is thrown
     */
    @Test
    void givenNullSimHardware_throwsIllegalArgumentException() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        PowerConsumptionSensor sensor = new PowerConsumptionSensor(sensorName, deviceID, sensorTypeID);
        String expected = "Invalid external service";

        try(MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)){
            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () -> sensor.getPowerConsumption(null));

            //Assert
            String result = exception.getMessage();
            List<SensorIDVO> createdInstances = mockedConstruction.constructed();
            assertEquals(expected, result);
            assertEquals(0, createdInstances.size());
        }
    }


    /**
     * Test for PowerConsumptionSensor
     * This test ensures the encapsulated VO's value is accessbile by calling getSensorName() : VO, then calling toString
     * on the same VO to get the name as a string
     */
    @Test
    void callingGetSensorNameToString_ReturnsNameVoAsString() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        when(sensorName.toString()).thenReturn("Test");

        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);

        String expected = "Test";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            PowerConsumptionSensor sensor = new PowerConsumptionSensor(sensorName, deviceID, sensorTypeID);

            // Act
            String result = sensor.getSensorName().toString();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }


    /**
     * Test for PowerConsumptionSensor
     * This test ensures the encapsulated VO's value is accessbile by calling getSensorTypeID() : VO, then calling toString
     * on the same VO to get the ID as a string
     */
    @Test
    void callingGetSensorTypeIDToString_ReturnsSensorTypeIDAsString() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);

        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        when(sensorTypeID.getID()).thenReturn("Test");

        String expected = "Test";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            PowerConsumptionSensor sensor = new PowerConsumptionSensor(sensorName, deviceID, sensorTypeID);

            // Act
            String result = sensor.getSensorTypeID().getID();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }


    /**
     * Test for PowerConsumptionSensor
     * This test ensures the encapsulated VO's value is accessbile by calling getDeviceID() : VO, then calling toString
     * on the same VO to get the device ID as a string
     */
    @Test
    void callingGetDeviceIDWithgetID_ReturnsDeviceIDAsString() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);

        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        when(deviceID.getID()).thenReturn("Test");

        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);


        String expected = "Test";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            PowerConsumptionSensor sensor = new PowerConsumptionSensor(sensorName, deviceID, sensorTypeID);

            // Act
            String result = sensor.getDeviceID().getID();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }


    /**
     * Test for PowerConsumptionSensor
     * This test ensures the encapsulated VO's value is accessbile by calling getId() : VO, then calling toString
     * on the same VO to get the ID as a string
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

            PowerConsumptionSensor sensor = new PowerConsumptionSensor(sensorName, deviceID, sensorTypeID);
            // Act
            String result = sensor.getId().getID();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }


    /**
     * Test for PowerConsumptionSensor
     * This test ensures the method getPowerConsumption returns the encapsulated value as a string
     */
    @Test
    void givenValidParameters_ReturnsPowerConsumptionValueToString() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        SimHardware simHardware = mock(SimHardware.class);
        PowerConsumptionSensor sensor = new PowerConsumptionSensor(sensorName, deviceID, sensorTypeID);
        String reading = "1";
        String expected = "1";
        when(simHardware.getValue()).thenReturn(reading);
        //Act
        String result = sensor.getPowerConsumption(simHardware).getValue().toString();
        //Assert
        assertEquals(expected, result);

    }

}