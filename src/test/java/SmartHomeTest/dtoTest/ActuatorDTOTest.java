package SmartHomeTest.dtoTest;

import smarthome.dto.ActuatorDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ActuatorDTO test class
 */

class ActuatorDTOTest {

    /**
     * Test to verify that the ActuatorDTO object is created correctly.
     */

    @Test
    void givenCorrectParameters_WhenCreateActuatorDTO_ThenReturnActuatorDTO() {
//        Arrange
        String expectedActuatorName = "Smart Thermostat";
        String actuatorType = "Thermostat";
        String deviceID = "frt3-567p-32za";
//        Act
        ActuatorDTO actuatorDTO = new ActuatorDTO(expectedActuatorName, actuatorType, deviceID);
//        Assert
        assertEquals(expectedActuatorName, actuatorDTO.getActuatorName());
        assertEquals(actuatorType, actuatorDTO.getActuatorType());
        assertEquals(deviceID, actuatorDTO.getDeviceID());
    }

    /**
     * Test to verify that the ActuatorDTO object is created correctly with limits.
     */

    @Test
    void givenCorrectParametersWithLimits_WhenCreateActuatorDTO_ThenReturnActuatorDTOWithLimits() {
//        Arrange
        String expectedActuatorName = "Smart Thermostat";
        String actuatorType = "Thermostat";
        String deviceID = "frt3-567p-32za";
        String lowerLimit = "10";
        String upperLimit = "30";
//        Act
        ActuatorDTO actuatorDTO = new ActuatorDTO(expectedActuatorName, actuatorType, deviceID, lowerLimit, upperLimit);
//        Assert
        assertEquals(expectedActuatorName, actuatorDTO.getActuatorName());
        assertEquals(actuatorType, actuatorDTO.getActuatorType());
        assertEquals(deviceID, actuatorDTO.getDeviceID());
        assertEquals(lowerLimit, actuatorDTO.getLowerLimit());
        assertEquals(upperLimit, actuatorDTO.getUpperLimit());
    }

    /**
     * Test to verify that the ActuatorDTO object is created correctly with limits and precision.
     */

    @Test
    void givenCorrectParametersWithLimitsAndPrecision_WhenCreateActuatorDTO_ThenReturnActuatorDTOWithLimitsAndPrecision() {
//        Arrange
        String expectedActuatorName = "Smart Thermostat";
        String actuatorType = "Thermostat";
        String deviceID = "frt3-567p-32za";
        String lowerLimit = "10.3";
        String upperLimit = "30.4";
        String precision = "0.1";
//        Act
        ActuatorDTO actuatorDTO = new ActuatorDTO(expectedActuatorName, actuatorType, deviceID, lowerLimit, upperLimit, precision);
//        Assert
        assertEquals(expectedActuatorName, actuatorDTO.getActuatorName());
        assertEquals(actuatorType, actuatorDTO.getActuatorType());
        assertEquals(deviceID, actuatorDTO.getDeviceID());
        assertEquals(lowerLimit, actuatorDTO.getLowerLimit());
        assertEquals(upperLimit, actuatorDTO.getUpperLimit());
        assertEquals(precision, actuatorDTO.getPrecision());
    }

    /**
     * Test to verify that the actuator name is retrieved correctly.
     */

    @Test
    void givenActuatorDTO_WhenGetActuatorName_ThenReturnActuatorName() {
//        Arrange
        String expectedActuatorName = "Smart Thermostat";
        String actuatorType = "Thermostat";
        String deviceID = "frt3-567p-32za";
        ActuatorDTO actuatorDTO = new ActuatorDTO(expectedActuatorName, actuatorType, deviceID);
//        Act
        String resultActuatorName = actuatorDTO.getActuatorName();
//        Assert
        assertEquals(expectedActuatorName, resultActuatorName);
    }

    /**
     * Test to verify that the actuator type is retrieved correctly.
     */

    @Test
    void givenActuatorDTO_WhenGetActuatorType_ThenReturnActuatorType() {
//        Arrange
        String actuatorName = "Smart Thermostat";
        String expectedActuatorType = "Thermostat";
        String deviceID = "frt3-567p-32za";
        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorName, expectedActuatorType, deviceID);
//        Act
        String resultActuatorType = actuatorDTO.getActuatorType();
//        Assert
        assertEquals(expectedActuatorType, resultActuatorType);
    }

    /**
     * Test to verify that the device ID is retrieved correctly.
     */

    @Test
    void givenActuatorDTO_WhenGetDeviceID_ThenReturnDeviceID() {
//        Arrange
        String actuatorName = "Smart Thermostat";
        String actuatorType = "Thermostat";
        String expectedDeviceID = "frt3-567p-32za";
        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorName, actuatorType, expectedDeviceID);
//        Act
        String resultDeviceID = actuatorDTO.getDeviceID();
//        Assert
        assertEquals(expectedDeviceID, resultDeviceID);
    }
}
