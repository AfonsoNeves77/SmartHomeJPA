package SmartHomeTest.voTest.actuatorVOTest;

import smarthome.vo.actuatorvo.ActuatorStatusVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ActuatorStatusVOTest {


    /**
     * Test case to verify that when an ActuatorStatusVO is instantiated with true as status,
     * the getValue method should return true.
     * This test creates an ActuatorStatusVO object with a true value and verifies that the getValue method
     * returns true as expected.
     */

    @Test
    void whenActuatorStatusInstantiatedWithTrue_getValueShouldReturnTrue(){
        //Arrange
        boolean status = true;

        //Act
        ActuatorStatusVO actuatorStatusVO = new ActuatorStatusVO(status);
        boolean result = actuatorStatusVO.getValue();

        //Assert
        assertTrue(result);
    }

    /**
     * Test case to verify that when an ActuatorStatusVO is instantiated with false as status,
     * the getValue method should return false.
     * This test creates an ActuatorStatusVO object with a false value and verifies that the getValue method
     * returns false as expected.
     */

    @Test
    void whenActuatorStatusInstantiatedWithFalse_getValueShouldReturnFalse(){
        //Arrange
        boolean status = false;

        //Act
        ActuatorStatusVO actuatorStatusVO = new ActuatorStatusVO(status);
        boolean result = actuatorStatusVO.getValue();

        //Assert
        assertFalse(result);
    }


}
