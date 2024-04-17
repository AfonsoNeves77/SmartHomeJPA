package SmartHomeTest.domainTest.sensorTest;

import smarthome.domain.sensor.externalservices.SimHardware;
import smarthome.domain.sensor.AveragePowerConsumptionSensor;
import smarthome.domain.sensor.values.AveragePowerConsumptionValue;
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

/**
 * Test class for the AveragePowerConsumptionSensor
 */

class AveragePowerConsumptionSensorTest {

    /**
     * Test to check if the constructor throws an exception when the sensor name is null
     */

    @Test
    void givenNullName_whenCreatingSensor_thenThrowException() {
//        Arrange
        SensorTypeIDVO sensorTypeDouble = mock(SensorTypeIDVO.class);
        DeviceIDVO deviceIDDouble = mock(DeviceIDVO.class);
        String expected = "Parameters cannot be null";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new AveragePowerConsumptionSensor(null, deviceIDDouble, sensorTypeDouble));
//        Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     * Test to check if the constructor throws an exception when the sensor type is null
     */

    @Test
    void givenNullSensorType_whenCreatingSensor_thenThrowException() {
//        Arrange
        SensorNameVO sensorNameDouble = mock(SensorNameVO.class);
        DeviceIDVO deviceIDDouble = mock(DeviceIDVO.class);
        String expected = "Parameters cannot be null";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new AveragePowerConsumptionSensor(sensorNameDouble, deviceIDDouble, null));
//        Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     * Test to check if the constructor throws an exception when the device ID is null
     */

    @Test
    void givenNullDeviceID_whenCreatingSensor_thenThrowException() {
//        Arrange
        SensorNameVO sensorNameDouble = mock(SensorNameVO.class);
        SensorTypeIDVO sensorTypeDouble = mock(SensorTypeIDVO.class);
        String expected = "Parameters cannot be null";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new AveragePowerConsumptionSensor(sensorNameDouble, null, sensorTypeDouble));
//        Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     * Test to check if the getReading method throws an exception when the SimHardware is null
     */

    @Test
    void givenNullSimHardware_whenGetReading_thenThrowException() {
//        Arrange
        SensorNameVO sensorNameDouble = mock(SensorNameVO.class);
        SensorTypeIDVO sensorTypeDouble = mock(SensorTypeIDVO.class);
        DeviceIDVO deviceIDDouble = mock(DeviceIDVO.class);
        String initialDate = "01-01-2021 00:00:00";
        String finalDate = "01-01-2021 00:00:00";
        String expected = "Invalid external service";
        AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorNameDouble, deviceIDDouble, sensorTypeDouble);
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                sensor.getReading(initialDate, finalDate, null));
//        Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     * Test to check if the getReading method throws an exception when the date is invalid
     */

    @Test
    void givenInvalidDate_whenGetReading_thenThrowException() {
//        Arrange
        SensorNameVO sensorNameDouble = mock(SensorNameVO.class);
        SensorTypeIDVO sensorTypeDouble = mock(SensorTypeIDVO.class);
        DeviceIDVO deviceIDDouble = mock(DeviceIDVO.class);
        String initialDate = "15-12-2020 14:15:45";
        String finalDate = "16/12/2020 14:15:45";
        SimHardware simHardware = mock(SimHardware.class);
        String expected = "Invalid date";
        AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorNameDouble, deviceIDDouble, sensorTypeDouble);
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                sensor.getReading(initialDate, finalDate, simHardware));
//        Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     * Test to check if the getReading method returns the expected value
     * It also checks if the AveragePowerConsumptionValue constructor is called
     * and if the getValue method is called
     * It also checks if the created instances of AveragePowerConsumptionValue is 1
     */

    @Test
    void givenValidSimHardware_whenGetReading_thenReturnValue() {
//        Arrange
        SensorNameVO sensorNameDouble = mock(SensorNameVO.class);
        SensorTypeIDVO sensorTypeDouble = mock(SensorTypeIDVO.class);
        DeviceIDVO deviceIDDouble = mock(DeviceIDVO.class);
        String initialDate = "15-12-2020 14:15:45";
        String finalDate = "16-12-2020 14:15:45";
        AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorNameDouble, deviceIDDouble, sensorTypeDouble);
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue(initialDate, finalDate)).thenReturn("100");
        int expected = 100;
        int expectedSize = 1;

        try (MockedConstruction<AveragePowerConsumptionValue> averagePowerConsumptionValueMock = mockConstruction(AveragePowerConsumptionValue.class,
                (mock, context) -> when(mock.getValue()).thenReturn(100))) {

//        Act
            int result = sensor.getReading(initialDate, finalDate, simHardware).getValue();
//        Assert
            List<AveragePowerConsumptionValue> createdInstances = averagePowerConsumptionValueMock.constructed();
            assertEquals(expected, result);
            assertEquals(expectedSize, createdInstances.size());
        }
    }

    /**
     * This test ensures that the method getSensorName returns the correct value.
     * It also ensures that the method creates a new instance of SensorIDVO.
     */

    @Test
    void givenGetSensorName_whenGetSensorName_thenReturnSensorNameAsString() {
//        Arrange
        String name = "Average Power Consumption Sensor";
        SensorNameVO sensorName = mock(SensorNameVO.class);
        when(sensorName.getValue()).thenReturn(name);
        SensorTypeIDVO sensorTypeDouble = mock(SensorTypeIDVO.class);
        DeviceIDVO deviceIDDouble = mock(DeviceIDVO.class);
        int expectedSize = 1;

        try (MockedConstruction<SensorIDVO> sensorIDVOMock = mockConstruction(SensorIDVO.class)) {
            AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorName, deviceIDDouble, sensorTypeDouble);
//        Act
            String result = sensor.getSensorName().getValue();
//        Assert
            List<SensorIDVO> createdInstances = sensorIDVOMock.constructed();
            assertEquals(name, result);
            assertEquals(expectedSize, createdInstances.size());
        }
    }

    /**
     * This test ensures that the method getSensorTypeID returns the correct value.
     * It also ensures that the method creates a new instance of SensorIDVO.
     */

    @Test
    void givenGetSensorTypeID_whenGetSensorTypeID_thenReturnSensorTypeIDAsString() {
//        Arrange
        String sensorTypeID = "AveragePowerConsumptionSensor";
        SensorNameVO sensorNameDouble = mock(SensorNameVO.class);
        SensorTypeIDVO sensorTypeIDVO = mock(SensorTypeIDVO.class);
        when(sensorTypeIDVO.getID()).thenReturn(sensorTypeID);
        DeviceIDVO deviceIDDouble = mock(DeviceIDVO.class);
        int expectedSize = 1;

        try (MockedConstruction<SensorIDVO> sensorIDVOMock = mockConstruction(SensorIDVO.class)) {
            AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorNameDouble, deviceIDDouble, sensorTypeIDVO);
//        Act
            String result = sensor.getSensorTypeID().getID();
//        Assert
            List<SensorIDVO> createdInstances = sensorIDVOMock.constructed();
            assertEquals(sensorTypeID, result);
            assertEquals(expectedSize, createdInstances.size());
        }
    }

    /**
     * This test ensures that the method getDeviceID returns the correct value.
     * It also ensures that the method creates a new instance of SensorIDVO.
     */

    @Test
    void givenGetDeviceID_whenGetDeviceID_thenReturnDeviceIDAsString() {
//        Arrange
        String deviceID = "DeviceID";
        SensorNameVO sensorNameDouble = mock(SensorNameVO.class);
        SensorTypeIDVO sensorTypeDouble = mock(SensorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        when(deviceIDVO.getID()).thenReturn(deviceID);
        int expectedSize = 1;

        try (MockedConstruction<SensorIDVO> sensorIDVOMock = mockConstruction(SensorIDVO.class)) {
            AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorNameDouble, deviceIDVO, sensorTypeDouble);
//        Act
            String result = sensor.getDeviceID().getID();
//        Assert
            List<SensorIDVO> createdInstances = sensorIDVOMock.constructed();
            assertEquals(deviceID, result);
            assertEquals(expectedSize, createdInstances.size());
        }
    }

    /**
     * This test ensures that the method getId returns the correct value.
     * It also ensures that the method creates a new instance of SensorIDVO.
     */

    @Test
    void givenGetId_whenGetId_thenReturnSensorIDAsString() {
//        Arrange
        String sensorID = "123";
        SensorNameVO sensorNameDouble = mock(SensorNameVO.class);
        SensorTypeIDVO sensorTypeDouble = mock(SensorTypeIDVO.class);
        DeviceIDVO deviceIDDouble = mock(DeviceIDVO.class);
        int expectedSize = 1;

        try (MockedConstruction<SensorIDVO> sensorIDVOMock = mockConstruction(SensorIDVO.class, (mock, context) ->
                when(mock.getID()).thenReturn(sensorID))) {
            AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorNameDouble, deviceIDDouble, sensorTypeDouble);
//        Act
            String result = sensor.getId().getID();
//        Assert
            List<SensorIDVO> createdInstances = sensorIDVOMock.constructed();
            assertEquals(sensorID, result);
            assertEquals(expectedSize, createdInstances.size());
        }
    }
}
