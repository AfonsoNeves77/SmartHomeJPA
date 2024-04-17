package SmartHomeTest.servicesTest;

import smarthome.domain.sensor.SensorFactoryImpl;
import smarthome.repository.DeviceRepositoryMem;
import smarthome.repository.SensorRepositoryMem;
import smarthome.repository.SensorTypeRepositoryMem;
import smarthome.services.SensorServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SensorServiceImplTest {

    /**
     * This test ensures that the constructor throws an Illegal Argument exception when receiving an invalid DeviceRepository
     */
    @Test
    void whenGivenAnInvalidDeviceRepository_ConstructorThrowsIllegalArgument(){
        // Arrange
        SensorTypeRepositoryMem sensorTypeRepositoryMem = mock(SensorTypeRepositoryMem.class);
        SensorRepositoryMem sensorRepositoryMem = mock(SensorRepositoryMem.class);
        SensorFactoryImpl factory = mock(SensorFactoryImpl.class);
        String expected = "Invalid repository";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorServiceImpl(null, sensorTypeRepositoryMem,sensorRepositoryMem,factory));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that the constructor throws an Illegal Argument exception when receiving an invalid SensorTypeRepository
     */
    @Test
    void whenGivenAnInvalidSensorTypeRepository_ConstructorThrowsIllegalArgument(){
        // Arrange
        DeviceRepositoryMem deviceRepository = mock(DeviceRepositoryMem.class);
        SensorRepositoryMem sensorRepositoryMem = mock(SensorRepositoryMem.class);
        SensorFactoryImpl factory = mock(SensorFactoryImpl.class);
        String expected = "Invalid repository";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorServiceImpl(deviceRepository, null,sensorRepositoryMem,factory));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that the constructor throws an Illegal Argument exception when receiving an invalid SensorRepository
     */
    @Test
    void whenGivenAnInvalidSensorRepository_ConstructorThrowsIllegalArgument(){
        // Arrange
        DeviceRepositoryMem deviceRepository = mock(DeviceRepositoryMem.class);
        SensorTypeRepositoryMem sensorTypeRepositoryMem = mock(SensorTypeRepositoryMem.class);
        SensorFactoryImpl factory = mock(SensorFactoryImpl.class);
        String expected = "Invalid repository";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorServiceImpl(deviceRepository, sensorTypeRepositoryMem,null,factory));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that the constructor throws an Illegal Argument exception when receiving an invalid Factory
     */
    @Test
    void whenGivenAnInvalidFactory_ConstructorThrowsIllegalArgument(){
        // Arrange
        DeviceRepositoryMem deviceRepository = mock(DeviceRepositoryMem.class);
        SensorTypeRepositoryMem sensorTypeRepositoryMem = mock(SensorTypeRepositoryMem.class);
        SensorRepositoryMem sensorRepositoryMem = mock(SensorRepositoryMem.class);
        String expected = "Invalid repository";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorServiceImpl(deviceRepository, sensorTypeRepositoryMem,sensorRepositoryMem,null));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }
}
