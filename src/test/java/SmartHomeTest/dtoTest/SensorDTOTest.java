package SmartHomeTest.dtoTest;

import smarthome.dto.SensorDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SensorDTO is currently used only to receive external information in DTO format, containing only the intended sensor
 * name, when there is an attempt to add a new sensor to the system. Since the information comes from the outside, anything
 * is expected. DTO can contain invalid information for the system, but should be the system to handle it. Given this,
 * 3 test scenarios are presented below, where two of them create DTOs that contain invalid information for the system.
 */
class SensorDTOTest {
    /**
     * Verifies that a SensorDTO is created with a valid sensor name for the system. Value returned when getSensorName()
     * method is invoked in the DTO corresponding to the expected one.
     */
    @Test
    void givenValidStringSensorName_SensorDTOIsCreated_WhenGetSensorName_ShouldReturnExpectedSensorName(){
        //Arrange
        String sensorName = "Vapor pressure sensor";
        SensorDTO dto = new SensorDTO(sensorName);

        //Act
        String result = dto.getSensorName();

        //
        assertEquals(sensorName, result);
    }

    /**
     * Verifies that a SensorDTO is created with an invalid sensor name for the system (null). When getSensorName()
     * method is invoked in the DTO, a null string is returned.
     */
    @Test
    void givenNullStringSensorName_SensorDTOIsCreated_WhenGetSensorName_ShouldReturnNull(){
        //Arrange
        SensorDTO dto = new SensorDTO(null);

        //Act
        String result = dto.getSensorName();

        //
        assertNull(result);
    }

    /**
     * Verifies that a SensorDTO is created with an invalid sensor name for the system (blank). When getSensorName()
     * method is invoked in the DTO, a blank string is returned.
     */
    @Test
    void givenBlankStringSensorName_SensorDTOIsCreated_WhenGetSensorName_ShouldBlankString(){
        //Arrange
        String sensorName = "   ";
        SensorDTO dto = new SensorDTO(sensorName);

        //Act
        String result = dto.getSensorName();

        //
        assertEquals(sensorName, result);
    }
}