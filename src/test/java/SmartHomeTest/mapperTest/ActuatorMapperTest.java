package SmartHomeTest.mapperTest;

import smarthome.dto.ActuatorDTO;
import smarthome.mapper.ActuatorMapper;
import smarthome.vo.actuatorvo.Settings;
import smarthome.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.vo.actuatorvo.ActuatorNameVO;
import smarthome.vo.actuatorvo.DecimalSettingsVO;
import smarthome.vo.actuatorvo.IntegerSettingsVO;
import smarthome.vo.devicevo.DeviceIDVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * ActuatorMapper test class
 */

class ActuatorMapperTest {
    /**
     * Test to verify that an exception is thrown when the ActuatorDTO object is null.
     * Should throw an IllegalArgumentException with the message "Invalid DTO, ActuatorDTO cannot be null" since it fails to create an ActuatorNameVO object.
     */
    @Test
    void givenNullActuatorDTO_WhenCreateActuatorNameVO_ThenThrowException() {
//        Arrange
        String expectedMessage = "ActuatorDTO cannot be null.";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ActuatorMapper.createActuatorNameVO(null));
//        Assert
        String resultingMessage = exception.getMessage();
        assertEquals(expectedMessage, resultingMessage);
    }

    /**
     * Test to verify that an exception is thrown when the ActuatorDTO object is null.
     * Should throw an IllegalArgumentException with the message "Invalid DTO, ActuatorDTO cannot be null" since it fails to create an ActuatorTypeIDVO object.
     */

    @Test
    void givenNullActuatorDTO_WhenCreateActuatorTypeIDVO_ThenThrowException() {
//        Arrange
        String expectedMessage = "ActuatorDTO cannot be null.";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ActuatorMapper.createActuatorTypeIDVO(null));
//        Assert
        String resultingMessage = exception.getMessage();
        assertEquals(expectedMessage, resultingMessage);
    }

    /**
     * Test to verify that an exception is thrown when the ActuatorDTO object is null.
     * Should throw an IllegalArgumentException with the message "Invalid DTO, ActuatorDTO cannot be null" since it fails to create a DeviceIDVO object.
     */

    @Test
    void givenNullActuatorDTO_WhenCreateDeviceIDVO_ThenThrowException() {
//        Arrange
        String expectedMessage = "ActuatorDTO cannot be null.";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ActuatorMapper.createDeviceIDVO(null));
//        Assert
        String resultingMessage = exception.getMessage();
        assertEquals(expectedMessage, resultingMessage);
    }

    /**
     * Test to verify that an ActuatorNameVO object is created correctly.
     */

    @Test
    void givenActuatorDTO_WhenCreateActuatorNameVO_ThenReturnActuatorNameVO() {
//        Arrange
        String expectedActuatorName = "Smart Thermostat";
        String actuatorType = "Thermostat";
        String deviceID = "frt3-567p-32za";
        ActuatorDTO actuatorDTO = new ActuatorDTO(expectedActuatorName, actuatorType, deviceID);
//        Act
        ActuatorNameVO actuatorNameVO = ActuatorMapper.createActuatorNameVO(actuatorDTO);
//        Assert
        assertEquals(expectedActuatorName, actuatorNameVO.getValue());
    }

    /**
     * Test to verify that an ActuatorTypeIDVO object is created correctly.
     */

    @Test
    void givenActuatorDTO_WhenCreateActuatorTypeIDVO_ThenReturnActuatorTypeIDVO() {
//        Arrange
        String expectedActuatorName = "Smart Thermostat";
        String actuatorType = "Thermostat";
        String deviceID = "frt3-567p-32za";
        ActuatorDTO actuatorDTO = new ActuatorDTO(expectedActuatorName, actuatorType, deviceID);
//        Act
        ActuatorTypeIDVO actuatorTypeIDVO = ActuatorMapper.createActuatorTypeIDVO(actuatorDTO);
//        Assert
        assertEquals(actuatorType, actuatorTypeIDVO.getID());
    }

    /**
     * Test to verify that a DeviceIDVO object is created correctly.
     */

    @Test
    void givenActuatorDTO_WhenCreateDeviceIDVO_ThenReturnDeviceIDVO() {
//        Arrange
        String expectedActuatorName = "Smart Thermostat";
        String actuatorType = "Thermostat";
        String deviceID = "123e4567-e89b-12d3-a456-111111111111";
        ActuatorDTO actuatorDTO = new ActuatorDTO(expectedActuatorName, actuatorType, deviceID);
//        Act
        DeviceIDVO deviceIDVO = ActuatorMapper.createDeviceIDVO(actuatorDTO);
//        Assert
        assertEquals(deviceID, deviceIDVO.getID());
    }

    /**
     * Test to verify that an IntegerSettingsVO object is created correctly when the ActuatorDTO object has limits.
     * It creates an IntegerSettingsVO object with the lower and upper limits. Then uses a getter to retrieve the values.
     * The expected values are the lower and upper limits in a string format.
     */

    @Test
    void givenActuatorDTOWithLimits_WhenCreateSettingsVO_ThenReturnIntegerSettingsVO() {
//        Arrange
        String expectedActuatorName = "Smart Thermostat";
        String actuatorType = "Thermostat";
        String deviceID = "frt3-567p-32za";
        String lowerLimit = "10";
        String upperLimit = "30";
        ActuatorDTO actuatorDTO = new ActuatorDTO(expectedActuatorName, actuatorType, deviceID, lowerLimit, upperLimit);
//        Act
        Settings integerSettingsVO = ActuatorMapper.createSettingsVO(actuatorDTO);
//        Assert
        Object[] expectedValues = integerSettingsVO.getValue();
        assertEquals(lowerLimit, expectedValues[0].toString());
        assertEquals(upperLimit, expectedValues[1].toString());
    }

    /**
     * Test to verify that a DecimalSettingsVO object is created correctly when the ActuatorDTO object has limits and precision.
     * It creates a DecimalSettingsVO object with the lower and upper limits and precision. Then uses a getter to retrieve the values.
     * The expected values are the lower limit, upper limit and precision in a string format.
     */

    @Test
    void givenActuatorDTOWithLimitsAndPrecision_WhenCreateSettingsVO_ThenReturnDecimalSettingsVO() {
//        Arrange
        String expectedActuatorName = "Smart Thermostat";
        String actuatorType = "Thermostat";
        String deviceID = "frt3-567p-32za";
        String lowerLimit = "10.3";
        String upperLimit = "30.2";
        String precision = "0.1";
        ActuatorDTO actuatorDTO = new ActuatorDTO(expectedActuatorName, actuatorType, deviceID, lowerLimit, upperLimit, precision);
//        Act
        Settings decimalSettingsVO = ActuatorMapper.createSettingsVO(actuatorDTO);
//        Assert
        Object[] expectedValues = decimalSettingsVO.getValue();
        assertEquals(lowerLimit, expectedValues[0].toString());
        assertEquals(upperLimit, expectedValues[1].toString());
        assertEquals(precision, expectedValues[2].toString());
    }

    /**
     * Test to verify that a null object is returned when the ActuatorDTO object has null limits.
     */

    @Test
    void givenActuatorDTONullLimits_WhenCreateSettingsVO_ThenReturnNullObject() {
//        Arrange
        String expectedActuatorName = "Smart Thermostat";
        String actuatorType = "Thermostat";
        String deviceID = "frt3-567p-32za";
        ActuatorDTO actuatorDTO = new ActuatorDTO(expectedActuatorName, actuatorType, deviceID);
//        Act
        Settings settingsVO = ActuatorMapper.createSettingsVO(actuatorDTO);
//        Assert
        assertNull(settingsVO);
    }

    /**
     * Verifies when a valid DTO is received (not null), ActuatorNameVO is created, and when getValue() is called on it,
     * the expected actuator name in a string format is retrieved.
     * ActuatorMapper Class has two collaborators in this scenario, ActuatorDTO and ActuatorNameVO classes. To isolate
     * the class under test (ActuatorMapper), a double of the ActuatorDTO class is made as well as a MockedConstruction of the
     * ActuatorNameVO Class, in order to condition its behaviour when a ActuatorNameVO is created.
     * It also verifies the number of times the ActuatorNameVO constructor is called.
     */

    @Test
    void givenActuatorDTOMock_WhenCreateActuatorNameVO_ThenReturnActuatorNameVO() {
//        Arrange
        String expectedActuatorName = "Smart Thermostat";
        int expectedListSize = 1;
        ActuatorDTO actuatorDTODouble = mock(ActuatorDTO.class);
        when(actuatorDTODouble.getActuatorName()).thenReturn(expectedActuatorName);

        try (MockedConstruction<ActuatorNameVO> mockedActuatorNameVO = mockConstruction(ActuatorNameVO.class, (mock, context)
                -> when(mock.getValue()).thenReturn("Smart Thermostat"))) {
//            Act
            ActuatorNameVO actuatorNameVO = ActuatorMapper.createActuatorNameVO(actuatorDTODouble);
//            Assert
            List<ActuatorNameVO> listOfMockedActuatorNameVO = mockedActuatorNameVO.constructed();
            assertEquals(expectedListSize, listOfMockedActuatorNameVO.size());
            assertEquals(expectedActuatorName, actuatorNameVO.getValue());
        }
    }

    /**
     * Verifies when a valid DTO is received (not null), ActuatorTypeIDVO is created, and when getID() is called on it,
     * the expected actuator type in a string format is retrieved.
     * ActuatorMapper Class has two collaborators in this scenario, ActuatorDTO and ActuatorTypeIDVO classes. To isolate
     * the class under test (ActuatorMapper), a double of the ActuatorDTO class is made as well as a MockedConstruction of the
     * ActuatorTypeIDVO Class, in order to condition its behaviour when a ActuatorTypeIDVO is created.
     * It also verifies the number of times the ActuatorTypeIDVO constructor is called.
     */

    @Test
    void givenActuatorDTOMock_WhenCreateActuatorTypeIDVO_ThenReturnActuatorTypeIDVO() {
//        Arrange
        String actuatorType = "Thermostat";
        int expectedListSize = 1;
        ActuatorDTO actuatorDTODouble = mock(ActuatorDTO.class);
        when(actuatorDTODouble.getActuatorType()).thenReturn(actuatorType);

        try (MockedConstruction<ActuatorTypeIDVO> mockedActuatorTypeIDVO = mockConstruction(ActuatorTypeIDVO.class, (mock, context)
                -> when(mock.getID()).thenReturn("Thermostat"))) {
//            Act
            ActuatorTypeIDVO actuatorTypeIDVO = ActuatorMapper.createActuatorTypeIDVO(actuatorDTODouble);
//            Assert
            List<ActuatorTypeIDVO> listOfMockedActuatorTypeIDVO = mockedActuatorTypeIDVO.constructed();
            assertEquals(expectedListSize, listOfMockedActuatorTypeIDVO.size());
            assertEquals(actuatorType, actuatorTypeIDVO.getID());
        }
    }

    /**
     * Verifies when a valid DTO is received (not null), DeviceIDVO is created, and when getID() is called on it,
     * the expected device ID in a string format is retrieved.
     * ActuatorMapper Class has two collaborators in this scenario, ActuatorDTO and DeviceIDVO classes. To isolate
     * the class under test (ActuatorMapper), a double of the ActuatorDTO class is made as well as a MockedConstruction of the
     * DeviceIDVO Class, in order to condition its behaviour when a DeviceIDVO is created.
     * It also verifies the number of times the DeviceIDVO constructor is called.
     */

    @Test
    void givenActuatorDTOMock_WhenCreateDeviceIDVO_ThenReturnDeviceIDVO() {
//        Arrange
        String deviceID = "123e4567-e89b-12d3-a456-111111111111";
        int expectedListSize = 1;
        ActuatorDTO actuatorDTODouble = mock(ActuatorDTO.class);
        when(actuatorDTODouble.getDeviceID()).thenReturn(deviceID);

        try (MockedConstruction<DeviceIDVO> mockedDeviceIDVO = mockConstruction(DeviceIDVO.class, (mock, context) ->
                when(mock.getID()).thenReturn("123e4567-e89b-12d3-a456-111111111111"))) {
//            Act
            DeviceIDVO deviceIDVO = ActuatorMapper.createDeviceIDVO(actuatorDTODouble);
//            Assert
            List<DeviceIDVO> listOfMockedDeviceIDVO = mockedDeviceIDVO.constructed();
            assertEquals(expectedListSize, listOfMockedDeviceIDVO.size());
            assertEquals(deviceID, deviceIDVO.getID());
        }
    }

/**
     * Verifies when a valid DTO is received (not null), IntegerSettingsVO is created, and when getValue() is called on it,
     * the expected lower and upper limits in a string format are retrieved.
     * ActuatorMapper Class has two collaborators in this scenario, ActuatorDTO and IntegerSettingsVO classes. To isolate
     * the class under test (ActuatorMapper), a double of the ActuatorDTO class is made as well as a MockedConstruction of the
     * IntegerSettingsVO Class, in order to condition its behaviour when a IntegerSettingsVO is created.
     * It also verifies the number of times the IntegerSettingsVO constructor is called.
     */

    @Test
    void givenActuatorDTOMock_WhenCreateSettingsVOWithLimits_ThenReturnIntegerSettingsVO() {
//        Arrange
        String lowerLimit = "10";
        String upperLimit = "30";
        int expectedListSize = 1;
        ActuatorDTO actuatorDTODouble = mock(ActuatorDTO.class);
        when(actuatorDTODouble.getLowerLimit()).thenReturn(lowerLimit);
        when(actuatorDTODouble.getUpperLimit()).thenReturn(upperLimit);

        try (MockedConstruction<IntegerSettingsVO> mockedIntegerSettingsVO = mockConstruction(IntegerSettingsVO.class, (mock, context) ->
                when(mock.getValue()).thenReturn(new Integer[]{10, 30}))) {
//            Act
            Settings integerSettingsVO = ActuatorMapper.createSettingsVO(actuatorDTODouble);
//            Assert
            List<IntegerSettingsVO> listOfMockedIntegerSettingsVO = mockedIntegerSettingsVO.constructed();
            Object[] expectedValues = integerSettingsVO.getValue();
            assertEquals(expectedListSize, listOfMockedIntegerSettingsVO.size());
            assertEquals(lowerLimit, expectedValues[0].toString());
            assertEquals(upperLimit, expectedValues[1].toString());
        }
    }

    /**
     * Verifies when a valid DTO is received (not null), DecimalSettingsVO is created, and when getValue() is called on it,
     * the expected lower limit, upper limit and precision in a string format are retrieved.
     * ActuatorMapper Class has two collaborators in this scenario, ActuatorDTO and DecimalSettingsVO classes. To isolate
     * the class under test (ActuatorMapper), a double of the ActuatorDTO class is made as well as a MockedConstruction of the
     * DecimalSettingsVO Class, in order to condition its behaviour when a DecimalSettingsVO is created.
     * It also verifies the number of times the DecimalSettingsVO constructor is called.
     */

    @Test
    void givenActuatorDTOMock_WhenCreateSettingsVOWithLimitsAndPrecision_ThenReturnDecimalSettingsVO() {
//        Arrange
        String lowerLimit = "10.3";
        String upperLimit = "30.2";
        String precision = "0.1";
        int expectedListSize = 1;
        ActuatorDTO actuatorDTODouble = mock(ActuatorDTO.class);
        when(actuatorDTODouble.getLowerLimit()).thenReturn(lowerLimit);
        when(actuatorDTODouble.getUpperLimit()).thenReturn(upperLimit);
        when(actuatorDTODouble.getPrecision()).thenReturn(precision);

        try (MockedConstruction<DecimalSettingsVO> mockedDecimalSettingsVO = mockConstruction(DecimalSettingsVO.class, (mock, context) ->
                when(mock.getValue()).thenReturn(new Double[]{10.3, 30.2, 0.1}))) {
//            Act
            Settings decimalSettingsVO = ActuatorMapper.createSettingsVO(actuatorDTODouble);
//            Assert
            List<DecimalSettingsVO> listOfMockedDecimalSettingsVO = mockedDecimalSettingsVO.constructed();
            Object[] expectedValues = decimalSettingsVO.getValue();
            assertEquals(expectedListSize, listOfMockedDecimalSettingsVO.size());
            assertEquals(lowerLimit, expectedValues[0].toString());
            assertEquals(upperLimit, expectedValues[1].toString());
            assertEquals(precision, expectedValues[2].toString());
        }
    }
}