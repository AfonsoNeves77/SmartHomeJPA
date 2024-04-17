package SmartHomeTest.servicesTest;

import smarthome.domain.sensortype.SensorTypeFactoryImpl;
import smarthome.repository.SensorTypeRepositoryMem;
import smarthome.services.SensorTypeServiceImpl;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SensorTypeServiceImplTest {
    /**
     * This test ensures that, when given a null path, the constructor throws and IllegalArgumentException
     */
    @Test
    void whenGivenANullPath_ConstructorThrowsIllegalArgument(){
        // Arrange
        SensorTypeRepositoryMem repository = mock(SensorTypeRepositoryMem.class);
        SensorTypeFactoryImpl factory = mock(SensorTypeFactoryImpl.class);
        String expected = "Invalid parameters";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorTypeServiceImpl(repository,factory,null));
        String result = exception.getMessage();
        // Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that, when given an invalid path, the constructor throws and IllegalArgumentException
     */
    @Test
    void whenGivenAnInvalidPath_ConstructorThrowsIllegalArgument(){
        // Arrange
        String path = "I will fail";
        SensorTypeRepositoryMem repository = mock(SensorTypeRepositoryMem.class);
        SensorTypeFactoryImpl factory = mock(SensorTypeFactoryImpl.class);
        String expected = "Invalid path";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorTypeServiceImpl(repository,factory,path));
        String result = exception.getMessage();
        // Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that, when given an invalid path, the constructor throws and IllegalArgumentException
     */
    @Test
    void whenGivenABlankPath_ConstructorThrowsIllegalArgument(){
        // Arrange
        String path = " ";
        SensorTypeRepositoryMem repository = mock(SensorTypeRepositoryMem.class);
        SensorTypeFactoryImpl factory = mock(SensorTypeFactoryImpl.class);
        String expected = "Invalid path";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorTypeServiceImpl(repository,factory,path));
        String result = exception.getMessage();
        // Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that, when given a null repository, the constructor throws and IllegalArgumentException
     */
    @Test
    void whenGivenAnInvalidRepository_ConstructorThrowsIllegalArgument(){
        // Arrange
        String expected = "Invalid parameters";
        SensorTypeFactoryImpl factory = mock(SensorTypeFactoryImpl.class);
        String path = "sensor.properties";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorTypeServiceImpl(null,factory,path));
        String result = exception.getMessage();
        // Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that, when given a null factory, the constructor throws and IllegalArgumentException
     */
    @Test
    void whenGivenAnInvalidFactory_ConstructorThrowsIllegalArgument(){
        // Arrange
        String expected = "Invalid parameters";
        SensorTypeRepositoryMem repository = mock(SensorTypeRepositoryMem.class);
        String path = "sensor.properties";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorTypeServiceImpl(repository,null,path));
        String result = exception.getMessage();
        // Assert
        assertEquals(expected,result);
    }
}
