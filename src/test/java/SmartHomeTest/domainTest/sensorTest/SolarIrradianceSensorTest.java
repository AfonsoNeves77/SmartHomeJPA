package SmartHomeTest.domainTest.sensorTest;

import smarthome.domain.sensor.externalservices.SimHardware;
import smarthome.domain.sensor.SolarIrradianceSensor;
import smarthome.domain.sensor.values.SolarIrradianceValue;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.sensortype.SensorTypeIDVO;
import smarthome.vo.sensorvo.SensorIDVO;
import smarthome.vo.sensorvo.SensorNameVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * When needed, to isolate the SolarIrradianceSensor Class, doubles of the following Classes were created:
 * SensorNameVO, DeviceIDVO, SensorTypeIDVO, SensorIDVO, SimHardware and SolarIrradianceValue.
 */
class SolarIrradianceSensorTest {
    /**
     * Successful scenario:
     * Sensor is created.
     * Verifies that the expected sensorID value object is retrieved and a single MockedConstruction of SensorIDVO is created.
     * (Please refer to the header of this Class for more information)
     */
    @Test
    void givenValidParameters_SensorIsCreated_WhenGetIdIsInvoked_ThenShouldReturnCorrectSensorIdVO(){
        //Arrange
        SensorNameVO nameDouble = mock(SensorNameVO.class);
        DeviceIDVO deviceIdDouble = mock(DeviceIDVO.class);
        SensorTypeIDVO typeDouble = mock(SensorTypeIDVO.class);
        int idListExpectedSize = 1;

        try(MockedConstruction<SensorIDVO> mockedSensorId = mockConstruction(SensorIDVO.class))
            {

            SolarIrradianceSensor sensor = new SolarIrradianceSensor(nameDouble, deviceIdDouble, typeDouble);
            List<SensorIDVO> idList = mockedSensorId.constructed();

            //Act
            SensorIDVO result = (SensorIDVO) sensor.getId();

            //Assert
            assertEquals(idList.get(0), result);
            assertEquals(idListExpectedSize, idList.size());
        }
    }

    /**
     Successful scenario:
     * Sensor is created. Then getReading() method is invoked and a SolarIrradianceValue is created.
     * The behaviour of the SimHardware Class is conditioned to return a specific valid reading.
     * It is verified that a single MockedConstruction of both SensorIDVO and SolarIrradianceValue is achieved.
     * The integer value returned corresponds to the expected.
     * (Please refer to the header of this Class for more information)
     */
    @Test
    void givenValidHardware_whenGetReadingIsInvoked_ThenShouldReturnSolarIrradianceValue() {
        //Arrange
        SensorNameVO nameDouble = mock(SensorNameVO.class);
        DeviceIDVO deviceIdDouble = mock(DeviceIDVO.class);
        SensorTypeIDVO typeDouble = mock(SensorTypeIDVO.class);
        SimHardware hardwareDouble = mock(SimHardware.class);
        when(hardwareDouble.getValue()).thenReturn("200");
        int expectedIntValue = 200;
        int idListExpectedSize = 1;
        int valueListExpectedSize = 1;

        try (MockedConstruction<SensorIDVO> mockedSensorId = mockConstruction(SensorIDVO.class);
             MockedConstruction<SolarIrradianceValue> mockedValue = mockConstruction(SolarIrradianceValue.class,(mock,context)
                     -> when(mock.getValue()).thenReturn(200)))
        {
            SolarIrradianceSensor sensor = new SolarIrradianceSensor(nameDouble, deviceIdDouble, typeDouble);
            List<SensorIDVO> idList = mockedSensorId.constructed();
            List<SolarIrradianceValue> valueList = mockedValue.constructed();

            //Act
            int result = sensor.getReading(hardwareDouble).getValue();

            //Assert
            assertEquals(expectedIntValue, result);
            assertEquals(idListExpectedSize, idList.size());
            assertEquals(valueListExpectedSize, valueList.size());
        }
    }

    /**
     * Sensor is created. Then getReading() method is invoked with an invalid hardware.
     * The method throws an IllegalArgumentException.
     * It is verified that a single MockedConstruction of SensorIDVO is achieved.
     * (Please refer to the header of this Class for more information)
     */
    @Test
    void givenInvalidHardware_whenGetReadingIsInvoked_ThenShouldThrowIllegalArgumentException() {
        //Arrange
        SensorNameVO nameDouble = mock(SensorNameVO.class);
        DeviceIDVO deviceIdDouble = mock(DeviceIDVO.class);
        SensorTypeIDVO typeDouble = mock(SensorTypeIDVO.class);
        String expected = "Invalid hardware";
        int idListExpectedSize = 1;

        try (MockedConstruction<SensorIDVO> mockedSensorId = mockConstruction(SensorIDVO.class))
        {
            SolarIrradianceSensor sensor = new SolarIrradianceSensor(nameDouble, deviceIdDouble, typeDouble);
            List<SensorIDVO> idList = mockedSensorId.constructed();

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () -> sensor.getReading(null));
            String result = exception.getMessage();

            //Assert
            assertEquals(expected, result);
            assertEquals(idListExpectedSize, idList.size());
        }
    }

    /**
     * Sensor is created. Then getReading() method is invoked with a valid hardware. However, a SolarIrradianceValue
     * is not created since reading is invalid.
     * The behaviour of the SimHardware Class is conditioned to return an invalid reading.
     * It is verified that a single MockedConstruction of SensorIDVO is achieved.
     * (Please refer to the header of this Class for more information)
     */
    @Test
    void givenValidHardwareButInvalidReading_whenGetReadingIsInvoked_ThenShouldThrowIllegalArgumentException() {
        //Arrange
        SensorNameVO nameDouble = mock(SensorNameVO.class);
        DeviceIDVO deviceIdDouble = mock(DeviceIDVO.class);
        SensorTypeIDVO typeDouble = mock(SensorTypeIDVO.class);
        SimHardware hardwareDouble = mock(SimHardware.class);
        when(hardwareDouble.getValue()).thenReturn("19.r.20");
        String expected = "Invalid reading";
        int idListExpectedSize = 1;

        try (MockedConstruction<SensorIDVO> mockedSensorId = mockConstruction(SensorIDVO.class))
        {
            SolarIrradianceSensor sensor = new SolarIrradianceSensor(nameDouble, deviceIdDouble, typeDouble);
            List<SensorIDVO> idList = mockedSensorId.constructed();

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () -> sensor.getReading(hardwareDouble));
            String result = exception.getMessage();

            //Assert
            assertEquals(expected, result);
            assertEquals(idListExpectedSize, idList.size());
        }
    }

    /**
     * Successful scenario:
     * Sensor is created.
     * Verifies that the expected sensorName value object is retrieved and a single MockedConstruction of SensorIDVO is created.
     * (Please refer to the header of this Class for more information)
     */
    @Test
    void givenValidParameters_SensorIsCreated_WhenGetSensorNameIsInvoked_ThenShouldReturnCorrectSensorNameVO(){
        //Arrange
        SensorNameVO nameDouble = mock(SensorNameVO.class);
        DeviceIDVO deviceIdDouble = mock(DeviceIDVO.class);
        SensorTypeIDVO typeDouble = mock(SensorTypeIDVO.class);
        int idListExpectedSize = 1;

        try(MockedConstruction<SensorIDVO> mockedSensorId = mockConstruction(SensorIDVO.class))
        {

            SolarIrradianceSensor sensor = new SolarIrradianceSensor(nameDouble, deviceIdDouble, typeDouble);
            List<SensorIDVO> idList = mockedSensorId.constructed();

            //Act
            SensorNameVO result = sensor.getSensorName();

            //Assert
            assertEquals(nameDouble, result);
            assertEquals(idListExpectedSize, idList.size());
        }
    }

    /**
     * Successful scenario:
     * Sensor is created.
     * Verifies that the expected sensorTypeID value object is retrieved and a single MockedConstruction of SensorIDVO is created.
     * (Please refer to the header of this Class for more information)
     */
    @Test
    void givenValidParameters_SensorIsCreated_WhenGetSensorTypeIdIsInvoked_ThenShouldReturnCorrectSensorTypeIDVO() {
        //Arrange
        SensorNameVO nameDouble = mock(SensorNameVO.class);
        DeviceIDVO deviceIdDouble = mock(DeviceIDVO.class);
        SensorTypeIDVO typeDouble = mock(SensorTypeIDVO.class);
        int idListExpectedSize = 1;

        try (MockedConstruction<SensorIDVO> mockedSensorId = mockConstruction(SensorIDVO.class)) {

            SolarIrradianceSensor sensor = new SolarIrradianceSensor(nameDouble, deviceIdDouble, typeDouble);
            List<SensorIDVO> idList = mockedSensorId.constructed();

            //Act
            SensorTypeIDVO result = sensor.getSensorTypeID();

            //Assert
            assertEquals(typeDouble, result);
            assertEquals(idListExpectedSize, idList.size());
        }
    }

    /**
     * Successful scenario:
     * Sensor is created.
     * Verifies that the expected deviceID value object is retrieved and a single MockedConstruction of SensorIDVO is created.
     * (Please refer to the header of this Class for more information)
     */
    @Test
    void givenValidParameters_SensorIsCreated_WhenGetDeviceIDIsInvoked_ThenShouldReturnCorrectDeviceIDVO() {
        //Arrange
        SensorNameVO nameDouble = mock(SensorNameVO.class);
        DeviceIDVO deviceIdDouble = mock(DeviceIDVO.class);
        SensorTypeIDVO typeDouble = mock(SensorTypeIDVO.class);
        int idListExpectedSize = 1;

        try (MockedConstruction<SensorIDVO> mockedSensorId = mockConstruction(SensorIDVO.class)) {

            SolarIrradianceSensor sensor = new SolarIrradianceSensor(nameDouble, deviceIdDouble, typeDouble);
            List<SensorIDVO> idList = mockedSensorId.constructed();

            //Act
            DeviceIDVO result = sensor.getDeviceID();

            //Assert
            assertEquals(deviceIdDouble, result);
            assertEquals(idListExpectedSize, idList.size());
        }
    }

    /**
     * Verifies that when a null name is given to instantiate the sensor, an IllegalArgumentException is thrown.
     * Sensor is not created.
     * (Please refer to the header of this Class for more information)
     */
    @Test
    void givenNullSensorName_ThenShouldThrowAnIllegalArgumentException() {
        //Arrange
        DeviceIDVO deviceIdDouble = mock(DeviceIDVO.class);
        SensorTypeIDVO typeDouble = mock(SensorTypeIDVO.class);
        String expected = "Sensor parameters cannot be null";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SolarIrradianceSensor(null, deviceIdDouble, typeDouble));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that when a null deviceID is given to instantiate the sensor, an IllegalArgumentException is thrown.
     * Sensor is not created.
     * (Please refer to the header of this Class for more information)
     */
    @Test
    void givenNullDeviceID_ThenShouldThrowAnIllegalArgumentException() {
        //Arrange
        SensorNameVO nameDouble = mock(SensorNameVO.class);
        SensorTypeIDVO typeDouble = mock(SensorTypeIDVO.class);
        String expected = "Sensor parameters cannot be null";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SolarIrradianceSensor(nameDouble, null, typeDouble));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that when a null sensorTypeID is given to instantiate the sensor, an IllegalArgumentException is thrown.
     * Sensor is not created.
     * (Please refer to the header of this Class for more information)
     */
    @Test
    void givenNullSensorTypeID_ThenShouldThrowAnIllegalArgumentException() {
        //Arrange
        SensorNameVO nameDouble = mock(SensorNameVO.class);
        DeviceIDVO deviceIdDouble = mock(DeviceIDVO.class);
        String expected = "Sensor parameters cannot be null";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SolarIrradianceSensor(nameDouble, deviceIdDouble, null));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }
}