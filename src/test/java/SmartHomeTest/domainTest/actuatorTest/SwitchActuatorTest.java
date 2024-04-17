package SmartHomeTest.domainTest.actuatorTest;

import smarthome.domain.actuator.externalservice.SimHardwareAct;
import smarthome.domain.actuator.SwitchActuator;
import smarthome.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.vo.actuatorvo.ActuatorIDVO;
import smarthome.vo.actuatorvo.ActuatorNameVO;
import smarthome.vo.devicevo.DeviceIDVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SwitchActuatorTest {

    /**
     * The following tests verify that if any of the parameters are null, Switch Actuator instantiation should throw an Illegal Argument Exception with
     * the  message "Invalid Parameters".
     * Regarding test syntax it´s used a reusable assertion method assertThrowsIllegalArgumentExceptionWithNullParameter()
     * for the similar nature of the following tests.
     */


    private void assertThrowsIllegalArgumentExceptionWithNullParameter(ActuatorNameVO actuatorName, ActuatorTypeIDVO actuatorTypeID, DeviceIDVO deviceIDVO) {
        //Arrange
        String expected = "Invalid Parameters";

        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SwitchActuator(actuatorName,actuatorTypeID,deviceIDVO));
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     *The following test verifies that if ActuatorNameVO parameter is null then Switch Actuator instantiation should throw an Illegal Argument Exception with
      the message "Invalid Parameters".
     */

    @Test
    void whenNullActuatorName_thenThrowsIllegalArgumentException() {
        //Arrange
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);

        //Assert
        assertThrowsIllegalArgumentExceptionWithNullParameter(null,actuatorTypeID,deviceIDVO);
    }

    /**
     The following test verifies that if the ActuatorTypeIDVO parameter is null then Switch Actuator instantiation should throw an Illegal Argument Exception with
     the message "Invalid Parameters".
     */

    @Test
    void whenNullActuatorType_thenThrowsIllegalArgumentException() {
        //Arrange
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);

        //Assert
        assertThrowsIllegalArgumentExceptionWithNullParameter(actuatorName,null,deviceIDVO);
    }


    /**
     The following test verifies that if the DeviceID parameter is null then Switch Actuator instantiation should throw an Illegal Argument Exception with
     the message "Invalid Parameters".
     */
    @Test
    void whenNullDeviceID_thenThrowsIllegalArgumentException() {
        //Arrange
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);

        //Assert
        assertThrowsIllegalArgumentExceptionWithNullParameter(actuatorName,actuatorTypeID,null);
    }

    /**
     * This test verifies that switchLoad() function is successful (returns true) when the command execution in SimHardwareAct
     * is successful (returns true);
     * It isolates all Actuators collaborators, and it conditions the behavior of the executeCommandSim method of SimHardwareAct to
     * return true when invoked.
     * It then initializes a SwitchActuator object using the mocked objects and executes the switchLoad method on it. After execution, it asserts
     * that the result is true.
     * These tests have an additional assertion that verifies the number of instances created for ActuatorIDVO ensuring that the number
     * of mocked constructions of this class objects match the expected.
     */

    @Test
    void whenSwitchLoadAndExecuteCommandReturnsTrue_ShouldReturnTrue(){
        //Arrange
        int expectedIDDoublesSize = 1;

        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);

        SimHardwareAct simHardwareAct = mock(SimHardwareAct.class);
        when(simHardwareAct.executeCommandSim()).thenReturn(true);

        try(MockedConstruction<ActuatorIDVO> actuatorIDMockedConstruction = mockConstruction(ActuatorIDVO.class)){
            //Act
            SwitchActuator actuator = new SwitchActuator(actuatorName,actuatorTypeID,deviceIDVO);
            boolean result = actuator.switchLoad(simHardwareAct);

            //Assert
            assertTrue(result);
            int actuatorIDDoubles = actuatorIDMockedConstruction.constructed().size();
            assertEquals(actuatorIDDoubles,expectedIDDoublesSize);
        }
    }

    /**
     * This test verifies that switchLoad() function fails(returns false) when the command execution in SimHardwareAct
     * also fails (returns false);
     * It isolates all Actuator's collaborators, and it conditions the behavior of the executeCommandSim method of SimHardwareAct to
     * return false when invoked.
     * It then initializes a SwitchActuator object using the mocked objects and executes the switchLoad method on it. After execution, it asserts
     * that the result is false.
     * These tests have an additional assertion that verifies the number of instances created for ActuatorIDVO ensuring that the number
     * of mocked constructions of this class objects match the expected.
     */


    @Test
    void whenSwitchLoadAndExecuteCommandReturnsFalse_ShouldReturnFalse(){
        //Arrange
        int expectedIDDoublesSize = 1;

        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);

        SimHardwareAct simHardwareAct = mock(SimHardwareAct.class);
        when(simHardwareAct.executeCommandSim()).thenReturn(false);

        try(MockedConstruction<ActuatorIDVO> actuatorIDMockedConstruction = mockConstruction(ActuatorIDVO.class)){

            //Act
            SwitchActuator actuator = new SwitchActuator(actuatorName,actuatorTypeID,deviceIDVO);
            boolean result = actuator.switchLoad(simHardwareAct);

            //Assert
            assertFalse(result);
            int actuatorIDDoubles = actuatorIDMockedConstruction.constructed().size();
            assertEquals(actuatorIDDoubles,expectedIDDoublesSize);
        }
    }



    /**
     * This test verifies that the correct ActuatorID value object is returned when getID() is invoked
     * in the SwitchActuator class.
     * It isolates all of Actuator's collaborators and compares the object returned by the previously referred method
     * with the doubled ActuatorID.
     * As in previous tests, it´s being used Mocked Construction to intercept ActuatorID instantiation during SwitchActuator instantiation.
     * By this interception the "real" ActuatorID object is being substituted with a double.
     * The assertion is made by getting the doubled object from MockedConstruction.constructed() list and compare it with the object
     * returned in the getID() operation. The references must match because the objects must be the same.
     * This assertion gives us certainty that the intercepted object is the object being return (as it should)

     * These tests have an additional assertion that verifies the number of instances created for ActuatorIDVO and ActuatorStatusVO,
     * ensuring that the number of mocked constructions of these objects match the expected.
     */

    @Test
    void getID_ShouldReturnCorrectSwitchActuatorIDObject(){
        //Arrange
        int expectedIDDoublesSize = 1;
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);

        try(MockedConstruction<ActuatorIDVO> actuatorIDMockedConstruction = mockConstruction(ActuatorIDVO.class)
        ){
            //Act
            SwitchActuator actuator = new SwitchActuator(actuatorName,actuatorTypeID,deviceIDVO);
            ActuatorIDVO result = actuator.getId();

            //Assert
            ActuatorIDVO expected = actuatorIDMockedConstruction.constructed().get(0);
            assertEquals(expected,result);
            int actuatorIDDoubles = actuatorIDMockedConstruction.constructed().size();
            assertEquals(actuatorIDDoubles,expectedIDDoublesSize);
        }
    }

    /**
     * This test validates that getActuatorName successfully returns actuatorNameVo object
     */
    @Test
    void getActuatorName_returnsActuatorNameVO(){
        // Arrange
        ActuatorNameVO name = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO type = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SwitchActuator actuator = new SwitchActuator(name,type,deviceID);
        // Act
        ActuatorNameVO result = actuator.getActuatorName();
        // Assert
        assertEquals(name,result);
    }

    /**
     * This test validates that getActuatorName successfully returns ActuatorTypeIDVO object
     */
    @Test
    void getActuatorTypeID_returnsActuatorTypeIDVO(){
        // Arrange
        ActuatorNameVO name = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO type = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SwitchActuator actuator = new SwitchActuator(name,type,deviceID);
        // Act
        ActuatorTypeIDVO result = actuator.getActuatorTypeID();
        // Assert
        assertEquals(type,result);
    }

    /**
     * This test validates that getActuatorName successfully returns DeviceIDVO object
     */
    @Test
    void getActuatorTypeID_returnsDeviceIDVO(){
        // Arrange
        ActuatorNameVO name = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO type = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SwitchActuator actuator = new SwitchActuator(name,type,deviceID);
        // Act
        DeviceIDVO result = actuator.getDeviceID();
        // Assert
        assertEquals(deviceID,result);
    }
}
