package SmartHomeTest.domainTest.sensorTest;

import smarthome.domain.sensor.EnergyConsumptionSensor;
import smarthome.domain.sensor.externalservices.SimHardware;
import smarthome.domain.sensor.values.EnergyConsumptionValue;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.sensortype.SensorTypeIDVO;
import smarthome.vo.sensorvo.SensorIDVO;
import smarthome.vo.sensorvo.SensorNameVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnergyConsumptionSensorTest {
    /**
     * Test for the constructor of EnergyConsumptionSensor when the SensorNameVO is null.
     * It verifies that when a null SensorNameVO is provided, the constructor throws an IllegalArgumentException.
     */
    @Test
    void whenSensorNameIsNull_thenExceptionIsThrown() {
        //Arrange
        SensorNameVO sensorName = null;
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        String expected = "Invalid parameters.";
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new EnergyConsumptionSensor(sensorName, deviceID, sensorType));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(0, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * Test for the constructor of EnergyConsumptionSensor when the SensorNameVO is empty.
     * It verifies that when an empty SensorNameVO is provided, the constructor throws an IllegalArgumentException.
     */
    @Test
    void whenSensorNameIsEmpty_thenExceptionIsThrown() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = null;
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        String expected = "Invalid parameters.";
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new EnergyConsumptionSensor(sensorName, deviceID, sensorType));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(0, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * Test for the constructor of EnergyConsumptionSensor when the SensorTypeIDVO is null.
     * It verifies that when a null SensorTypeIDVO is provided, the constructor throws an IllegalArgumentException.
     */
    @Test
    void whenSensorTypeIsNull_thenExceptionIsThrown() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = null;
        String expected = "Invalid parameters.";
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new EnergyConsumptionSensor(sensorName, deviceID, sensorType));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(0, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * Test for EnergyConsumptionSensor.
     * Given a valid SensorNameVO, DeviceIDVO, and SensorTypeIDVO,
     * when the constructor is called, then the sensor is created.
     */
    @Test
    void whenSensorIsSuccessfullyCreated_thenReturnsSensorName() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            //Act
            EnergyConsumptionSensor energyConsumptionSensor = new EnergyConsumptionSensor(sensorName, deviceID, sensorType);
            SensorNameVO result = energyConsumptionSensor.getSensorName();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(sensorName, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * Test for EnergyConsumptionSensor
     * Given a valid SensorNameVO, DeviceIDVO and SensorTypeIDVO, when the constructor is called, then the sensor is created
     */
    @Test
    void whenSensorIsSuccessfullyCreated_thenReturnsSensorID() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            //Act
            EnergyConsumptionSensor energyConsumptionSensor = new EnergyConsumptionSensor(sensorName, deviceID, sensorType);
            SensorIDVO result = energyConsumptionSensor.getId();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertNotNull(result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * Test for EnergyConsumptionSensor
     * Given a valid SensorNameVO, DeviceIDVO and SensorTypeIDVO, when the constructor is called, then the sensor is created
     */
    @Test
    void whenSensorIsSuccessfullyCreated_thenReturnsSensorType() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            //Act
            EnergyConsumptionSensor energyConsumptionSensor = new EnergyConsumptionSensor(sensorName, deviceID, sensorType);
            SensorTypeIDVO result = energyConsumptionSensor.getSensorTypeID();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(sensorType, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * Test for EnergyConsumptionSensor
     * Given a valid SensorNameVO, DeviceIDVO and SensorTypeIDVO, when the constructor is called, then the sensor is created
     */
    @Test
    void whenSensorIsSuccessfullyCreated_thenReturnsDeviceID() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            //Act
            EnergyConsumptionSensor energyConsumptionSensor = new EnergyConsumptionSensor(sensorName, deviceID, sensorType);
            DeviceIDVO result = energyConsumptionSensor.getDeviceID();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(deviceID, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * Tests that if simHardware is null, then an IllegalArgumentException is thrown
     */
    @Test
    void whenSimHardwareIsNull_thenExceptionIsThrown() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        SimHardware simHardware = null;
        String initialDate = "15-12-2020 14:15:45";
        String finalDate = "16-12-2020 14:15:45";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {
            EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, deviceID, sensorType);
            String expected = "Invalid external service";

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    sensor.getReading(initialDate, finalDate, simHardware));
            String result = exception.getMessage();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * Test that if initialDate is after finalDate, then an IllegalArgumentException is thrown
     */
    @Test
    void whenInitialDateIsAfterFinalDate_thenExceptionIsThrown() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        SimHardware simHardware = mock(SimHardware.class);
        String initialDate = "17-12-2020 14:15:45";
        String finalDate = "16-12-2020 14:15:45";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {
            EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, deviceID, sensorType);
            String expected = "Invalid date";

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    sensor.getReading(initialDate, finalDate, simHardware));
            String result = exception.getMessage();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * Test that if initialDate is after currentDate, then an IllegalArgumentException is thrown

     */
    @Test
    void whenInitialDateIsAfterCurrentDate_thenExceptionIsThrown() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        SimHardware simHardware = mock(SimHardware.class);
        String initialDate = "17-12-2050 14:15:45";
        String finalDate = "16-12-2070 14:15:45";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {
            EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, deviceID, sensorType);
            String expected = "Invalid date";

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    sensor.getReading(initialDate, finalDate, simHardware));
            String result = exception.getMessage();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * Test that if finalDate is after currentDate, then an IllegalArgumentException is thrown
     */
    @Test
    void whenFinalDateIsAfterCurrentDate_thenExceptionIsThrown() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        SimHardware simHardware = mock(SimHardware.class);
        String initialDate = "17-12-2020 14:15:45";
        String finalDate = "16-12-2070 14:15:45";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {
            EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, deviceID, sensorType);
            String expected = "Invalid date";

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    sensor.getReading(initialDate, finalDate, simHardware));
            String result = exception.getMessage();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * Test that if initialDate format is invalid, then an IllegalArgumentException is thrown
     */
    @Test
    void whenInitialDateFormatIsInvalid_thenExceptionIsThrown() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        SimHardware simHardware = mock(SimHardware.class);
        String initialDate = "17-12-2020 14:15:45:89";
        String finalDate = "16-12-2020 14:15:45";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {
            EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, deviceID, sensorType);
            String expected = "Invalid date";

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    sensor.getReading(initialDate, finalDate, simHardware));
            String result = exception.getMessage();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * Test that if finalDate format is invalid, then an IllegalArgumentException is thrown
     */
    @Test
    void whenFinalDateFormatIsInvalid_thenExceptionIsThrown() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        SimHardware simHardware = mock(SimHardware.class);
        String initialDate = "17-12-2020 14:15:45";
        String finalDate = "16-12-2020 14:15:45:89";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {
            EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, deviceID, sensorType);
            String expected = "Invalid date";

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    sensor.getReading(initialDate, finalDate, simHardware));
            String result = exception.getMessage();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test verifies if the getReading method returns a EnergyConsumptionValue with the value set at 50.
     * First, all the constructor's parameters are mocked and the energyConsumption sensor is created.
     * Then, the expected value is set to "25" and the EnergyConsumptionValue constructor is mocked to return the expected value.
     * After that, the getReading method is called and turned to a String.
     * Meanwhile, a list of EnergyConsumptionValue is created to store the mocked EnergyConsumptionValue.
     * And create mockedConstruction for SensorIDVO.
     * Finally, the expected value is compared to the result and the size of the list is checked.
     */
    @Test
    void whenReadingIsCorrect_thenReturnsValueSuccessfully() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        String expected = "25";
        String initialDate = "15-12-2020 14:15:45";
        String finalDate = "16-12-2020 14:15:45";

        try (MockedConstruction<EnergyConsumptionValue> energyConsumptionValueDouble = mockConstruction(EnergyConsumptionValue.class, (mock, context)
                -> when(mock.getValueAsString()).thenReturn("25"));
             MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, deviceID, sensorType);

            //Act
            EnergyConsumptionValue actualEnergyConsumptionValue = (EnergyConsumptionValue) sensor.getReading(initialDate, finalDate, simHardware);
            List<EnergyConsumptionValue> constructedEnergyConsumptionValues = energyConsumptionValueDouble.constructed();
            String actualEnergyConsumptionValueString = actualEnergyConsumptionValue.getValueAsString();

            //Assert
            assertEquals(expected, actualEnergyConsumptionValueString);
            assertEquals(1, constructedEnergyConsumptionValues.size());
        }
    }
    }