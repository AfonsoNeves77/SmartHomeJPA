package SmartHomeTest.voTest.actuatorTypeVOTest;

import smarthome.vo.actuatortype.ActuatorTypeIDVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActuatorTypeIDVOTest {
    @Test
    void testValidParameters_returnsActuatorTypeIDVO() {
        String type = "switchActuator";
        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO(type);
        assertNotNull(actuatorTypeIDVO);
    }

    @Test
    void testNullActuatorTypeID_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ActuatorTypeIDVO(null));
    }

    @Test
    void testEmptyActuatorTypeID_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ActuatorTypeIDVO(" "));
    }


    @Test
    void testGetActuatorTypeID_returnsActuatorTypeID() {
        String expected = "switchActuator";
        String type = "switchActuator";
        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO(type);
        assertEquals(expected, actuatorTypeIDVO.getID());
    }
}