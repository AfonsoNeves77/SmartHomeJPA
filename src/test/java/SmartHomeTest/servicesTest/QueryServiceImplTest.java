package SmartHomeTest.servicesTest;

import org.junit.jupiter.api.Test;
import smarthome.repository.ActuatorRepositoryMem;
import smarthome.repository.DeviceRepositoryMem;
import smarthome.repository.SensorRepositoryMem;
import smarthome.services.QueryServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class QueryServiceImplTest {

    /**
     * Test to verify that the constructor of MemQueryService
     * throws an IllegalArgumentException when instantiated with a null device repository.
     */
    @Test
    void givenInvalidDeviceRepository_QueryConstructorThrowsException_UnableToInstantiateQueryService(){
        // Arrange
        SensorRepositoryMem sensorRepository = mock(SensorRepositoryMem.class);
        ActuatorRepositoryMem actuatorRepository = mock(ActuatorRepositoryMem.class);
        String expected = "Invalid parameters";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new QueryServiceImpl(null,sensorRepository,actuatorRepository));
        String result = exception.getMessage();
        // Assert
        assertEquals(expected,result);
    }

    /**
     * Test to verify that the constructor of MemQueryService
     * throws an IllegalArgumentException when instantiated with a null sensor repository.
     */
    @Test
    void givenInvalidSensorRepository_QueryConstructorThrowsException_UnableToInstantiateQueryService(){
        // Arrange
        DeviceRepositoryMem deviceRepository = mock(DeviceRepositoryMem.class);
        ActuatorRepositoryMem actuatorRepository = mock(ActuatorRepositoryMem.class);
        String expected = "Invalid parameters";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new QueryServiceImpl(deviceRepository,null,actuatorRepository));
        String result = exception.getMessage();
        // Assert
        assertEquals(expected,result);
    }

    /**
     * Test to verify that the constructor of MemQueryService
     * throws an IllegalArgumentException when instantiated with a null actuator repository.
     */
    @Test
    void givenInvalidActuatorRepository_QueryConstructorThrowsException_UnableToInstantiateQueryService(){
        // Arrange
        DeviceRepositoryMem deviceRepository = mock(DeviceRepositoryMem.class);
        SensorRepositoryMem sensorRepository = mock(SensorRepositoryMem.class);
        String expected = "Invalid parameters";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new QueryServiceImpl(deviceRepository,sensorRepository,null));
        String result = exception.getMessage();
        // Assert
        assertEquals(expected,result);
    }
}
