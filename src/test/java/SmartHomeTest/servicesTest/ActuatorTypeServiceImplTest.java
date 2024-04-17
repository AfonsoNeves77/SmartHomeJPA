package SmartHomeTest.servicesTest;

import smarthome.domain.actuatortype.ActuatorTypeFactoryImpl;
import smarthome.repository.ActuatorTypeRepositoryMem;
import smarthome.services.ActuatorTypeServiceImpl;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ActuatorTypeServiceImplTest {


    /**
     * Test to check if the ActuatorTypeService constructor
     * throws an IllegalArgumentException when given a null repository.
     */
    @Test
    void whenGivenANullRepository_throwsIllegalArgument(){
        // Arrange
        String expected = "Invalid repository";
        ActuatorTypeFactoryImpl v1ActuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorTypeServiceImpl(null, v1ActuatorTypeFactoryDouble, "filepath"));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }


    /**
     * Test to check if the ActuatorTypeService constructor
     * throws an IllegalArgumentException when given a null factory.
     */
    @Test
    void whenGivenANullFactory_throwsIllegalArgument(){
        // Arrange
        String expected = "Invalid repository";
        ActuatorTypeRepositoryMem memActuatorTypeRepositoryDouble = mock(ActuatorTypeRepositoryMem.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorTypeServiceImpl(memActuatorTypeRepositoryDouble, null, "filepath"));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }


    /**
     * Test to check if the ActuatorTypeService constructor
     * throws an IllegalArgumentException when given a null filepath.
     */
    @Test
    void whenGivenANullFilepath_throwsIllegalArgument(){
        // Arrange
        String expected = "Invalid repository";
        ActuatorTypeRepositoryMem memActuatorTypeRepositoryDouble = mock(ActuatorTypeRepositoryMem.class);
        ActuatorTypeFactoryImpl v1ActuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);


        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorTypeServiceImpl(memActuatorTypeRepositoryDouble, v1ActuatorTypeFactoryDouble, null));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }


    /**
     * Test to check if the ActuatorTypeService constructor
     * throws an IllegalArgumentException when given an empty repository.
     */
    @Test
    void whenGivenAnEmptyFilepath_throwsIllegalArgument(){
        // Arrange
        String expected = "Invalid repository";
        ActuatorTypeRepositoryMem memActuatorTypeRepositoryDouble = mock(ActuatorTypeRepositoryMem.class);
        ActuatorTypeFactoryImpl v1ActuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
        String filepath = " ";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorTypeServiceImpl(memActuatorTypeRepositoryDouble, v1ActuatorTypeFactoryDouble, filepath));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }


    /**
     * Test to check if the ActuatorTypeService constructor
     * throws an IllegalArgumentException when given a wrong repository.
     */
    @Test
    void whenGivenWrongFilepath_throwsIllegalArgument(){
        // Arrange
        String expected = "Filepath non existent";
        ActuatorTypeRepositoryMem memActuatorTypeRepositoryDouble = mock(ActuatorTypeRepositoryMem.class);
        ActuatorTypeFactoryImpl v1ActuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
        String filepath = "WrongFilePath";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorTypeServiceImpl(memActuatorTypeRepositoryDouble, v1ActuatorTypeFactoryDouble, filepath));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }
}
