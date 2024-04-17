package SmartHomeTest.dtoTest;

import smarthome.dto.ActuatorTypeDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActuatorTypeDTOTest {
    @Test
    void givenType_ThenReturnActuatorTypeDTO() {
        String type = "type";
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(type);
        assertEquals(type, actuatorTypeDTO.getActuatorTypeID());
    }
}