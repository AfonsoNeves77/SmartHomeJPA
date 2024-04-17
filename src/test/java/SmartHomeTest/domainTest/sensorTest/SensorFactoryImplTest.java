package SmartHomeTest.domainTest.sensorTest;

import smarthome.domain.sensor.*;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.sensortype.SensorTypeIDVO;
import smarthome.vo.sensorvo.SensorNameVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SensorFactoryImplTest {

    /**
     * This test ensures that when given an invalid path. The constructor throws an exception.
     */
    @Test
    void givenEmptyPath_constructorThrowsIllegalArgument(){
        // Arrange
        String path = " ";
        String expected = "Error reading file";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class,() ->
                new SensorFactoryImpl(path));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that when given an invalid path. The constructor throws an exception.
     */
    @Test
    void givenNullPath_constructorThrowsIllegalArgument(){
        // Arrange
        String expected = "Error reading file";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class,() ->
                new SensorFactoryImpl(null));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that when given an invalid path. The constructor throws an exception.
     */
    @Test
    void givenInvalidPath_constructorThrowsIllegalArgument(){
        // Arrange
        String path = "Invalid path";
        String expected = "Error reading file";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class,() ->
                new SensorFactoryImpl(path));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }

    /**
     * Validates the method createSensor, when all prerequisites are met, successfully returns the specified sensor object.
     */
    @Test
    void givenCorrectVOs_createSensorReturnsASensorObject(){
        // Arrange
        String path = "sensor.properties";
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        when(sensorType.getID()).thenReturn("SunriseSensor");

        try(MockedConstruction<SunriseSensor> mockedConstruction = mockConstruction(SunriseSensor.class)) {
            SensorFactoryImpl factory = new SensorFactoryImpl(path);

            // Act
            SunriseSensor sensorResult = (SunriseSensor) factory.createSensor(sensorName,deviceID,sensorType);
            SunriseSensor sensorExpected = mockedConstruction.constructed().get(0);

            // Assert
            assertEquals(sensorExpected,sensorResult);
        }
    }

    /**
     * This test ensures createSensor returns null when given a null SensorNameVo. It also ensures there was no
     * instance of Sunrise Sensor created, as the method returns null before reaching the creation phase.
     */
    @Test
    void givenNullSensorNameVO_createSensorReturnsNull(){
        // Arrange
        String path = "sensor.properties";
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);

        try(MockedConstruction<SunriseSensor> mockedConstruction = mockConstruction(SunriseSensor.class)) {
            SensorFactoryImpl factory = new SensorFactoryImpl(path);

            // Act
            SunriseSensor sensorResult = (SunriseSensor) factory.createSensor(null,deviceID,sensorType);
            int numberMockedConstructions = mockedConstruction.constructed().size();

            // Assert
            assertNull(sensorResult);
            assertEquals(0,numberMockedConstructions);
        }
    }

    /**
     * This test ensures createSensor returns null when given a null DeviceIDVO. It also ensures there was no
     * instance of Sunrise Sensor created, as the method returns null before reaching the creation phase.
     */
    @Test
    void givenNullDeviceIDVO_createSensorReturnsNull(){
        // Arrange
        String path = "sensor.properties";
        SensorNameVO sensorName = mock(SensorNameVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);

        try(MockedConstruction<SunriseSensor> mockedConstruction = mockConstruction(SunriseSensor.class)) {
            SensorFactoryImpl factory = new SensorFactoryImpl(path);

            // Act
            SunriseSensor sensorResult = (SunriseSensor) factory.createSensor(sensorName,null,sensorType);
            int numberMockedConstructions = mockedConstruction.constructed().size();

            // Assert
            assertNull(sensorResult);
            assertEquals(0,numberMockedConstructions);
        }
    }

    /**
     * This test ensures createSensor returns null when given a null SensorTypeIDVO. It also ensures there was no
     * instance of Sunrise Sensor created, as the method returns null before reaching the creation phase.
     */
    @Test
    void givenNullSensorTypeID_createSensorReturnsNull(){
        // Arrange
        String path = "sensor.properties";
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        try(MockedConstruction<SunriseSensor> mockedConstruction = mockConstruction(SunriseSensor.class)) {
            SensorFactoryImpl factory = new SensorFactoryImpl(path);

            // Act
            SunriseSensor sensorResult = (SunriseSensor) factory.createSensor(sensorName,deviceID,null);
            int numberMockedConstructions = mockedConstruction.constructed().size();

            // Assert
            assertNull(sensorResult);
            assertEquals(0,numberMockedConstructions);
        }
    }

    /**
     * This test ensures createSensor returns null when a valid SensorTypeIDVO returns a null when called with getID().
     * It also ensures there was no instance of Sunrise Sensor created, as the method returns null before reaching the
     * creation phase.
     */
    @Test
    void givenSensorTypeIDVO_whenGetIDFromVOreturnsNull_createSensorReturnsNull(){
        // Arrange
        String path = "sensor.properties";
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        when(sensorType.getID()).thenReturn(null);

        try(MockedConstruction<SunriseSensor> mockedConstruction = mockConstruction(SunriseSensor.class)) {
            SensorFactoryImpl factory = new SensorFactoryImpl(path);

            // Act
            SunriseSensor sensorResult = (SunriseSensor) factory.createSensor(sensorName,deviceID,sensorType);
            int numberMockedConstructions = mockedConstruction.constructed().size();

            // Assert
            assertNull(sensorResult);
            assertEquals(0,numberMockedConstructions);
        }
    }

    /**
     * This test ensures createSensor returns null when a valid SensorTypeIDVO returns an invalid ID when called with getID().
     * It also ensures there was no instance of Sunrise Sensor created, as the method returns null before reaching the
     * creation phase.
     */
    @Test
    void givenSensorTypeIDVO_whenGetIDFromVOreturnsInvalidType_createSensorReturnsNull(){
        // Arrange
        String path = "sensor.properties";
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        when(sensorType.getID()).thenReturn("Nuclear");

        try(MockedConstruction<SunriseSensor> mockedConstruction = mockConstruction(SunriseSensor.class)) {
            SensorFactoryImpl factory = new SensorFactoryImpl(path);

            // Act
            SunriseSensor sensorResult = (SunriseSensor) factory.createSensor(sensorName,deviceID,sensorType);
            int numberMockedConstructions = mockedConstruction.constructed().size();

            // Assert
            assertNull(sensorResult);
            assertEquals(0,numberMockedConstructions);
        }
    }

    /*
    SYSTEM UNDER TEST: FACTORY + SENSOR
    A double of all the other collaborators is done (essentially the required value objects to create the sensor).
    */

    /**
     * Test method to verify the behavior of createSensor when provided with a null SensorNameVO.
     * This method tests whether the createSensor method of SensorFactory returns null when
     * a null SensorNameVO is passed as an argument.
     * Steps:
     * 1. Arrange: Set SensorNameVO to null and double DeviceIDVO and SensorTypeIDVO.
     * 2. Act: Call the createSensor method of SensorFactory with null SensorNameVO.
     * 3. Assert: Verify that the returned sensor is null.
     */

    @Test
    void createSensor_WhenNullSensorNameVO_shouldReturnNull(){
        //Arrange
        DeviceIDVO expectedDeviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO expectedSensorTypeID = mock(SensorTypeIDVO.class);
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl("sensor.properties");

        //Act
        Sensor sensor = sensorFactoryImpl.createSensor(null,expectedDeviceID,expectedSensorTypeID);

        //Assert
        assertNull(sensor);
    }

    /**
     * Test method to verify the behavior of createSensor when provided with a null DeviceIDVO.
     * This method tests whether the createSensor method of SensorFactory returns null when
     * a null DeviceIDVO is passed as an argument.
     */

    @Test
    void createSensor_WhenNullDeviceIDVO_shouldReturnNull(){
        //Arrange
        SensorNameVO expectedName = mock(SensorNameVO.class);
        SensorTypeIDVO expectedSensorTypeID = mock(SensorTypeIDVO.class);
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl("sensor.properties");

        //Act
        Sensor sensor = sensorFactoryImpl.createSensor(expectedName,null,expectedSensorTypeID);

        //Assert
        assertNull(sensor);
    }

    /**
     * Test method to verify the behavior of createSensor when provided with a null SensorTypeIDVO.
     * This method tests whether the createSensor method of SensorFactory returns null when
     * a null SensorTypeIDVO is passed as an argument.
     */

    @Test
    void createSensor_WhenNullSensorTypeIDVO_shouldReturnNull(){
        //Arrange
        SensorNameVO expectedName = mock(SensorNameVO.class);
        DeviceIDVO expectedDeviceID = mock(DeviceIDVO.class);
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl("sensor.properties");

        //Act
        Sensor sensor = sensorFactoryImpl.createSensor(expectedName,expectedDeviceID,null);

        //Assert
        assertNull(sensor);
    }

    /**
     * Test method to verify the behavior of createSensor when provided with a non permitted sensor type.
     * By non permitted we mean, a sensor type that is not present in sensor.properties.
     * This method tests whether the createSensor method of SensorFactory returns null when
     * a SensorTypeIDVO is non permitted.
     */

    @Test
    void createSensor_WhenNonPermittedSensorType_shouldReturnNull(){
        //Arrange
        SensorNameVO expectedName = mock(SensorNameVO.class);
        DeviceIDVO expectedDeviceID =  mock(DeviceIDVO.class);
        SensorTypeIDVO expectedSensorTypeID = mock(SensorTypeIDVO.class);
        when(expectedSensorTypeID.getID()).thenReturn("CO2Sensor");
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl("sensor.properties");

        //Act
        Sensor sensor = sensorFactoryImpl.createSensor(expectedName,expectedDeviceID,expectedSensorTypeID);

        //Assert
        assertNull(sensor);
    }

    /**
     * Test method to verify the behavior of createSensor when provided with a non permitted sensor type.
     * By non permitted we mean, a sensor type that is present sensor.properties but doesn't have an associated classpath.
     * This method tests whether the createSensor method of SensorFactory returns null when
     * a SensorTypeIDVO exists in the system but does not  have an associated classpath.
     */

    @Test
    void createSensor_whenSensorTypeWithoutAnAssociatedPath_shouldReturnNull(){
        //Arrange
        SensorNameVO expectedName = mock(SensorNameVO.class);
        DeviceIDVO expectedDeviceID =  mock(DeviceIDVO.class);
        SensorTypeIDVO expectedSensorTypeID = mock(SensorTypeIDVO.class);
        when(expectedSensorTypeID.getID()).thenReturn("NuclearSensor");
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl("sensor.properties");

        //Act
        Sensor sensor = sensorFactoryImpl.createSensor(expectedName,expectedDeviceID,expectedSensorTypeID);

        //Assert
        assertNull(sensor);
    }

    /**
     * Test method to verify the behavior of createSensor when provided with a non permitted sensor type.
     * By non permitted we mean, a sensor type that is present but the associated classpath is invalid (does not match a class).
     * This method tests whether the createSensor method of SensorFactory returns null when
     * a SensorTypeIDVO does not mach with a valid sensor class.
     */

    @Test
    void createSensor_whenSensorTypePathIsInvalid_shouldReturnNull(){
        //Arrange
        SensorNameVO expectedName = mock(SensorNameVO.class);
        DeviceIDVO expectedDeviceID =  mock(DeviceIDVO.class);
        SensorTypeIDVO expectedSensorTypeID = mock(SensorTypeIDVO.class);
        when(expectedSensorTypeID.getID()).thenReturn("RotationSensor");
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl("sensor.properties");

        //Act
        Sensor sensor = sensorFactoryImpl.createSensor(expectedName,expectedDeviceID,expectedSensorTypeID);

        //Assert
        assertNull(sensor);
    }


    /**
     * Test method to verify the creation of a TemperatureSensor object with valid value objects.
     * This method tests whether the TemperatureSensor object is correctly created by the SensorFactory class
     * when provided with valid SensorNameVO, DeviceIDVO, and SensorTypeIDVO instances.
     * Steps:
     * 1. Arrange: Double necessary value objects and create an instance of SensorFactory.
     * 2. Act: Call the createSensor method of SensorFactory to create a TemperatureSensor object.
     * 3. Assert: Verify that the created TemperatureSensor object has the expected attributes.
     */

    @Test
    void createTemperatureSensor_WhenValidVOs_shouldReturnCorrectSensor(){
        //Arrange
        SensorNameVO expectedName = mock(SensorNameVO.class);
        DeviceIDVO expectedDeviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO expectedSensorTypeID = mock(SensorTypeIDVO.class);
        when(expectedSensorTypeID.getID()).thenReturn("TemperatureSensor");
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl("sensor.properties");

        //Act
        TemperatureSensor temperatureSensor = (TemperatureSensor) sensorFactoryImpl.createSensor(expectedName, expectedDeviceID, expectedSensorTypeID);
        SensorNameVO resultName = temperatureSensor.getSensorName();
        DeviceIDVO resultDeviceID = temperatureSensor.getDeviceID();
        SensorTypeIDVO resultSensorTypeID = temperatureSensor.getSensorTypeID();

        //Assert
        assertEquals(expectedName,resultName);
        assertEquals(expectedDeviceID,resultDeviceID);
        assertEquals(expectedSensorTypeID,resultSensorTypeID);
    }

    /**
     * Test method to verify the creation of a HumiditySensor object with valid value objects.
     * This method tests whether the HumiditySensor object is correctly created by the SensorFactory class
     * when provided with valid SensorNameVO, DeviceIDVO, and SensorTypeIDVO instances.
     * Steps:
     * 1. Arrange: Double necessary value objects and create an instance of SensorFactory.
     * 2. Act: Call the createSensor method of SensorFactory to create a HumiditySensor object.
     * 3. Assert: Verify that the created HumiditySensor object has the expected attributes.
     */

    @Test
    void createHumiditySensor_WhenValidVOs_shouldReturnCorrectSensor(){
        //Arrange
        SensorNameVO expectedName = mock(SensorNameVO.class);
        DeviceIDVO expectedDeviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO expectedSensorTypeID = mock(SensorTypeIDVO.class);
        when(expectedSensorTypeID.getID()).thenReturn("HumiditySensor");
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl("sensor.properties");

        //Act
        HumiditySensor humiditySensor = (HumiditySensor) sensorFactoryImpl.createSensor(expectedName, expectedDeviceID, expectedSensorTypeID);
        SensorNameVO resultName = humiditySensor.getSensorName();
        DeviceIDVO resultDeviceID = humiditySensor.getDeviceID();
        SensorTypeIDVO resultSensorTypeID = humiditySensor.getSensorTypeID();

        //Assert
        assertEquals(expectedName,resultName);
        assertEquals(expectedDeviceID,resultDeviceID);
        assertEquals(expectedSensorTypeID,resultSensorTypeID);
    }

    /**
     * Test method to verify the creation of a PositionSensor object with valid value objects.
     * This method tests whether the PositionSensor object is correctly created by the SensorFactory class
     * when provided with valid SensorNameVO, DeviceIDVO, and SensorTypeIDVO instances.
     * Steps:
     * 1. Arrange: Double necessary value objects and create an instance of SensorFactory.
     * 2. Act: Call the createSensor method of SensorFactory to create a PositionSensor object.
     * 3. Assert: Verify that the created PositionSensor object has the expected attributes.
     */

    @Test
    void createPositionSensor_WhenValidVOs_shouldReturnCorrectSensor(){
        //Arrange
        SensorNameVO expectedName = mock(SensorNameVO.class);
        DeviceIDVO expectedDeviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO expectedSensorTypeID = mock(SensorTypeIDVO.class);
        when(expectedSensorTypeID.getID()).thenReturn("PositionSensor");
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl("sensor.properties");

        //Act
        PositionSensor positionSensor = (PositionSensor) sensorFactoryImpl.createSensor(expectedName, expectedDeviceID, expectedSensorTypeID);
        SensorNameVO resultName = positionSensor.getSensorName();
        DeviceIDVO resultDeviceID = positionSensor.getDeviceID();
        SensorTypeIDVO resultSensorTypeID = positionSensor.getSensorTypeID();

        //Assert
        assertEquals(expectedName,resultName);
        assertEquals(expectedDeviceID,resultDeviceID);
        assertEquals(expectedSensorTypeID,resultSensorTypeID);
    }

    /**
     * Test method to verify the creation of a WindSensor object with valid value objects.
     * This method tests whether the WindSensor object is correctly created by the SensorFactory class
     * when provided with valid SensorNameVO, DeviceIDVO, and SensorTypeIDVO instances.
     * Steps:
     * 1. Arrange: Double necessary value objects and create an instance of SensorFactory.
     * 2. Act: Call the createSensor method of SensorFactory to create a WindSensor object.
     * 3. Assert: Verify that the created WindSensor object has the expected attributes.
     */

    @Test
    void createWindSensor_WhenValidVOs_shouldReturnCorrectSensor(){
        //Arrange
        SensorNameVO expectedName = mock(SensorNameVO.class);
        DeviceIDVO expectedDeviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO expectedSensorTypeID = mock(SensorTypeIDVO.class);
        when(expectedSensorTypeID.getID()).thenReturn("WindSensor");
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl("sensor.properties");

        //Act
        WindSensor windSensor = (WindSensor) sensorFactoryImpl.createSensor(expectedName, expectedDeviceID, expectedSensorTypeID);
        SensorNameVO resultName = windSensor.getSensorName();
        DeviceIDVO resultDeviceID = windSensor.getDeviceID();
        SensorTypeIDVO resultSensorTypeID = windSensor.getSensorTypeID();

        //Assert
        assertEquals(expectedName,resultName);
        assertEquals(expectedDeviceID,resultDeviceID);
        assertEquals(expectedSensorTypeID,resultSensorTypeID);
    }

    /**
     * Test method to verify the creation of a DewPointSensor object with valid value objects.
     * This method tests whether the DewPointSensor object is correctly created by the SensorFactory class
     * when provided with valid SensorNameVO, DeviceIDVO, and SensorTypeIDVO instances.
     * Steps:
     * 1. Arrange: Double necessary value objects and create an instance of SensorFactory.
     * 2. Act: Call the createSensor method of SensorFactory to create a DewPointSensor object.
     * 3. Assert: Verify that the created DewPointSensor object has the expected attributes.
     */

    @Test
    void createDewPointSensor_WhenValidVOs_shouldReturnCorrectSensor(){
        //Arrange
        SensorNameVO expectedName = mock(SensorNameVO.class);
        DeviceIDVO expectedDeviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO expectedSensorTypeID = mock(SensorTypeIDVO.class);
        when(expectedSensorTypeID.getID()).thenReturn("DewPointSensor");
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl("sensor.properties");

        //Act
        DewPointSensor dewPointSensor = (DewPointSensor) sensorFactoryImpl.createSensor(expectedName, expectedDeviceID, expectedSensorTypeID);
        SensorNameVO resultName = dewPointSensor.getSensorName();
        DeviceIDVO resultDeviceID = dewPointSensor.getDeviceID();
        SensorTypeIDVO resultSensorTypeID = dewPointSensor.getSensorTypeID();

        //Assert
        assertEquals(expectedName,resultName);
        assertEquals(expectedDeviceID,resultDeviceID);
        assertEquals(expectedSensorTypeID,resultSensorTypeID);
    }

    /**
     * Test method to verify the creation of a SunsetSensor object with valid value objects.
     * This method tests whether the SunsetSensor object is correctly created by the SensorFactory class
     * when provided with valid SensorNameVO, DeviceIDVO, and SensorTypeIDVO instances.
     * Steps:
     * 1. Arrange: Double necessary value objects and create an instance of SensorFactory.
     * 2. Act: Call the createSensor method of SensorFactory to create a SunsetSensor object.
     * 3. Assert: Verify that the created SunsetSensor object has the expected attributes.
     */

    @Test
    void createSunsetSensor_WhenValidVOs_shouldReturnCorrectSensor(){
        //Arrange
        SensorNameVO expectedName = mock(SensorNameVO.class);
        DeviceIDVO expectedDeviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO expectedSensorTypeID = mock(SensorTypeIDVO.class);
        when(expectedSensorTypeID.getID()).thenReturn("SunsetSensor");
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl("sensor.properties");

        //Act
        SunsetSensor sunsetSensor = (SunsetSensor) sensorFactoryImpl.createSensor(expectedName, expectedDeviceID, expectedSensorTypeID);
        SensorNameVO resultName = sunsetSensor.getSensorName();
        DeviceIDVO resultDeviceID = sunsetSensor.getDeviceID();
        SensorTypeIDVO resultSensorTypeID = sunsetSensor.getSensorTypeID();

        //Assert
        assertEquals(expectedName,resultName);
        assertEquals(expectedDeviceID,resultDeviceID);
        assertEquals(expectedSensorTypeID,resultSensorTypeID);
    }

    /**
     * Test method to verify the creation of a SunriseSensor object with valid value objects.
     * This method tests whether the SunriseSensor object is correctly created by the SensorFactory class
     * when provided with valid SensorNameVO, DeviceIDVO, and SensorTypeIDVO instances.
     * Steps:
     * 1. Arrange: Double necessary value objects and create an instance of SensorFactory.
     * 2. Act: Call the createSensor method of SensorFactory to create a SunriseSensor object.
     * 3. Assert: Verify that the created SunriseSensor object has the expected attributes.
     */

    @Test
    void createSunriseSensor_WhenValidVOs_shouldReturnCorrectSensor(){
        //Arrange
        SensorNameVO expectedName = mock(SensorNameVO.class);
        DeviceIDVO expectedDeviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO expectedSensorTypeID = mock(SensorTypeIDVO.class);
        when(expectedSensorTypeID.getID()).thenReturn("SunriseSensor");
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl("sensor.properties");

        //Act
        SunriseSensor sunriseSensor = (SunriseSensor) sensorFactoryImpl.createSensor(expectedName, expectedDeviceID, expectedSensorTypeID);
        SensorNameVO resultName = sunriseSensor.getSensorName();
        DeviceIDVO resultDeviceID = sunriseSensor.getDeviceID();
        SensorTypeIDVO resultSensorTypeID = sunriseSensor.getSensorTypeID();

        //Assert
        assertEquals(expectedName,resultName);
        assertEquals(expectedDeviceID,resultDeviceID);
        assertEquals(expectedSensorTypeID,resultSensorTypeID);
    }

    /**
     * Test method to verify the creation of a AveragePowerConsumptionSensor object with valid value objects.
     * This method tests whether the AveragePowerConsumptionSensor object is correctly created by the SensorFactory class
     * when provided with valid SensorNameVO, DeviceIDVO, and SensorTypeIDVO instances.
     * Steps:
     * 1. Arrange: Double necessary value objects and create an instance of SensorFactory.
     * 2. Act: Call the createSensor method of SensorFactory to create a AveragePowerConsumptionSensor object.
     * 3. Assert: Verify that the created AveragePowerConsumptionSensor object has the expected attributes.
     */

    @Test
    void createAveragePowerConsumptionSensor_WhenValidVOs_shouldReturnCorrectSensor(){
        //Arrange
        SensorNameVO expectedName = mock(SensorNameVO.class);
        DeviceIDVO expectedDeviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO expectedSensorTypeID = mock(SensorTypeIDVO.class);
        when(expectedSensorTypeID.getID()).thenReturn("AveragePowerConsumptionSensor");
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl("sensor.properties");

        //Act
        AveragePowerConsumptionSensor averagePowerConsumptionSensor = (AveragePowerConsumptionSensor) sensorFactoryImpl.createSensor(expectedName, expectedDeviceID, expectedSensorTypeID);
        SensorNameVO resultName = averagePowerConsumptionSensor.getSensorName();
        DeviceIDVO resultDeviceID = averagePowerConsumptionSensor.getDeviceID();
        SensorTypeIDVO resultSensorTypeID = averagePowerConsumptionSensor.getSensorTypeID();

        //Assert
        assertEquals(expectedName,resultName);
        assertEquals(expectedDeviceID,resultDeviceID);
        assertEquals(expectedSensorTypeID,resultSensorTypeID);
    }

    /**
     * Test method to verify the creation of a PowerConsumptionSensor object with valid value objects.
     * This method tests whether the PowerConsumptionSensor object is correctly created by the SensorFactory class
     * when provided with valid SensorNameVO, DeviceIDVO, and SensorTypeIDVO instances.
     * Steps:
     * 1. Arrange: Double necessary value objects and create an instance of SensorFactory.
     * 2. Act: Call the createSensor method of SensorFactory to create a PowerConsumptionSensor object.
     * 3. Assert: Verify that the created PowerConsumptionSensor object has the expected attributes.
     */

    @Test
    void createPowerConsumptionSensor_WhenValidVOs_shouldReturnCorrectSensor(){
        //Arrange
        SensorNameVO expectedName = mock(SensorNameVO.class);
        DeviceIDVO expectedDeviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO expectedSensorTypeID = mock(SensorTypeIDVO.class);
        when(expectedSensorTypeID.getID()).thenReturn("PowerConsumptionSensor");
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl("sensor.properties");

        //Act
        PowerConsumptionSensor powerConsumptionSensor = (PowerConsumptionSensor) sensorFactoryImpl.createSensor(expectedName, expectedDeviceID, expectedSensorTypeID);
        SensorNameVO resultName = powerConsumptionSensor.getSensorName();
        DeviceIDVO resultDeviceID = powerConsumptionSensor.getDeviceID();
        SensorTypeIDVO resultSensorTypeID = powerConsumptionSensor.getSensorTypeID();

        //Assert
        assertEquals(expectedName,resultName);
        assertEquals(expectedDeviceID,resultDeviceID);
        assertEquals(expectedSensorTypeID,resultSensorTypeID);
    }

    /**
     * Test method to verify the creation of a SwitchSensor object with valid value objects.
     * This method tests whether the SwitchSensor object is correctly created by the SensorFactory class
     * when provided with valid SensorNameVO, DeviceIDVO, and SensorTypeIDVO instances.
     * Steps:
     * 1. Arrange: Double necessary value objects and create an instance of SensorFactory.
     * 2. Act: Call the createSensor method of SensorFactory to create a SwitchSensor object.
     * 3. Assert: Verify that the created SwitchSensor object has the expected attributes.
     */

    @Test
    void createSwitchSensor_WhenValidVOs_shouldReturnCorrectSensor(){
        //Arrange
        SensorNameVO expectedName = mock(SensorNameVO.class);
        DeviceIDVO expectedDeviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO expectedSensorTypeID = mock(SensorTypeIDVO.class);
        when(expectedSensorTypeID.getID()).thenReturn("SwitchSensor");
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl("sensor.properties");

        //Act
        SwitchSensor switchSensor = (SwitchSensor) sensorFactoryImpl.createSensor(expectedName, expectedDeviceID, expectedSensorTypeID);
        SensorNameVO resultName = switchSensor.getSensorName();
        DeviceIDVO resultDeviceID = switchSensor.getDeviceID();
        SensorTypeIDVO resultSensorTypeID = switchSensor.getSensorTypeID();

        //Assert
        assertEquals(expectedName,resultName);
        assertEquals(expectedDeviceID,resultDeviceID);
        assertEquals(expectedSensorTypeID,resultSensorTypeID);
    }

    /**
     * Test method to verify the creation of a SolarIrradianceSensor object with valid value objects.
     * This method tests whether the SolarIrradianceSensor object is correctly created by the SensorFactory class
     * when provided with valid SensorNameVO, DeviceIDVO, and SensorTypeIDVO instances.
     * Steps:
     * 1. Arrange: Double necessary value objects and create an instance of SensorFactory.
     * 2. Act: Call the createSensor method of SensorFactory to create a SolarIrradianceSensor object.
     * 3. Assert: Verify that the created SolarIrradianceSensor object has the expected attributes.
     */

    @Test
    void createSolarIrradianceSensor_WhenValidVOs_shouldReturnCorrectSensor(){
        //Arrange
        SensorNameVO expectedName = mock(SensorNameVO.class);
        DeviceIDVO expectedDeviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO expectedSensorTypeID = mock(SensorTypeIDVO.class);
        when(expectedSensorTypeID.getID()).thenReturn("SolarIrradianceSensor");
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl("sensor.properties");

        //Act
        SolarIrradianceSensor irradianceSensor = (SolarIrradianceSensor) sensorFactoryImpl.createSensor(expectedName, expectedDeviceID, expectedSensorTypeID);
        SensorNameVO resultName = irradianceSensor.getSensorName();
        DeviceIDVO resultDeviceID = irradianceSensor.getDeviceID();
        SensorTypeIDVO resultSensorTypeID = irradianceSensor.getSensorTypeID();

        //Assert
        assertEquals(expectedName,resultName);
        assertEquals(expectedDeviceID,resultDeviceID);
        assertEquals(expectedSensorTypeID,resultSensorTypeID);
    }
}
