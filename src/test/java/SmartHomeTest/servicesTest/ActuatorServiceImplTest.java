package SmartHomeTest.servicesTest;

import smarthome.domain.actuator.*;
import smarthome.repository.ActuatorRepository;
import smarthome.repository.ActuatorTypeRepository;
import smarthome.repository.DeviceRepository;
import smarthome.services.ActuatorServiceImpl;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ActuatorServiceImplTest {

    /**
     * The following tests verify that if any of the parameters are null, ActuatorService instantiation should throw an Illegal Argument Exception with
     * the  message "Invalid Parameters".
     * Regarding test syntax it´s used a reusable assertion method assertThrowsIllegalArgumentExceptionWithNullParameter()
     * for the similar nature of the following tests.
     * @param deviceRepository Device Repository
     * @param actuatorTypeRepository Actuator type repository
     * @param factoryActuator actuator factory
     * @param memActuatorRepository actuator repository
     */
    private void assertThrowsIllegalArgumentExceptionWithNullParameter(DeviceRepository deviceRepository, ActuatorTypeRepository actuatorTypeRepository,ActuatorFactory factoryActuator, ActuatorRepository memActuatorRepository) {
        //Arrange
        String expected = "Invalid parameters";

        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorServiceImpl(deviceRepository,actuatorTypeRepository,factoryActuator, memActuatorRepository));
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     *The following test verifies that if DeviceRepository parameter is null then ActuatorService instantiation should throw an Illegal Argument Exception with
     the message "Invalid Parameters".
     */
    @Test
    void whenNullDeviceRepository_thenThrowsIllegalArgumentException() {
        //Arrange
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorTypeRepository actuatorTypeRepository = mock(ActuatorTypeRepository.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);

        //Assert
        assertThrowsIllegalArgumentExceptionWithNullParameter(null,actuatorTypeRepository,actuatorFactory,actuatorRepository);
    }

    /**
     *The following test verifies that if ActuatorTypeRepository parameter is null then ActuatorService instantiation should throw an Illegal Argument Exception with
     the message "Invalid Parameters".
     */
    @Test
    void whenNullActuatorTypeRepository_thenThrowsIllegalArgumentException() {
        //Arrange
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);

        //Assert
        assertThrowsIllegalArgumentExceptionWithNullParameter(deviceRepository,null,actuatorFactory,actuatorRepository);
    }

    /**
     *The following test verifies that if ActuatorFactory parameter is null then ActuatorService instantiation should throw an Illegal Argument Exception with
     the message "Invalid Parameters".
     */
    @Test
    void whenNullActuatorFactory_thenThrowsIllegalArgumentException() {
        //Arrange
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        ActuatorTypeRepository actuatorTypeRepository = mock(ActuatorTypeRepository.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);

        //Assert
        assertThrowsIllegalArgumentExceptionWithNullParameter(deviceRepository,actuatorTypeRepository,null,actuatorRepository);
    }

    /**
     *The following test verifies that if ActuatorRepository parameter is null then ActuatorService instantiation should throw an Illegal Argument Exception with
     the message "Invalid Parameters".
     */
    @Test
    void whenNullActuatorRepository_thenThrowsIllegalArgumentException() {
        //Arrange
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        ActuatorTypeRepository actuatorTypeRepository = mock(ActuatorTypeRepository.class);

        //Assert
        assertThrowsIllegalArgumentExceptionWithNullParameter(deviceRepository,actuatorTypeRepository,actuatorFactory,null);
    }
}
