package SmartHomeTest.domainTest.actuatorTest;

import smarthome.domain.actuator.externalservice.SimHardwareAct;
import smarthome.domain.actuator.RollerBlindActuator;
import smarthome.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.vo.actuatorvo.ActuatorIDVO;
import smarthome.vo.actuatorvo.ActuatorNameVO;
import smarthome.vo.devicevo.DeviceIDVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RollerBlindActuatorTest {


    /**
     * Test if the constructor throws an IllegalArgumentException when the actuatorName is null.
     * The expected result is an IllegalArgumentException with the message "Invalid Parameters".
     */
    @Test
    void whenNullActuatorName_ThenThrowIllegalArgumentException(){
        //Arrange
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        String expected = "Invalid Parameters";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new RollerBlindActuator(null, actuatorTypeID, deviceIDVO));
        //Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }


    /**
     * Test if the constructor throws an IllegalArgumentException when the actuatorTypeID is null.
     * The expected result is an IllegalArgumentException with the message "Invalid Parameters".
     */
    @Test
    void whenNullActuatorTypeID_ThenThrowIllegalArgumentException(){
        //Arrange
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        String expected = "Invalid Parameters";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new RollerBlindActuator(actuatorName, null, deviceIDVO));
        //Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }


    /**
     * Test if the constructor throws an IllegalArgumentException when the deviceID is null.
     * The expected result is an IllegalArgumentException with the message "Invalid Parameters".
     */
    @Test
    void whenNullDeviceID_ThenThrowIllegalArgumentException(){
        //Arrange
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        String expected = "Invalid Parameters";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new RollerBlindActuator(actuatorName, actuatorTypeID, null));
        //Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }


    /**
     * This test verifies that the executeCommand method returns true when the position is valid (between 0 and 100) and
     * the command execution in the SimHardwareAct object is successful.
     * It isolates all Actuators collaborators, and it conditions the behavior of the executeCommandSim method of
     * SimHardwareAct to return true when invoked.
     * It then constructs a RollerBlindActuator object using the mocked objects and executes the switchLoad method on it.
     * After execution, it asserts that the result is true.
     * These tests have an additional assertion that verifies the number of instances created for ActuatorIDVO,
     * ensuring that the number of mocked constructions of these objects match the expected.
     */
    @Test
    void whenValidPositionAndExecuteCommand_ThenReturnTrue(){
        //Arrange
        int expectedIdDoubleSize = 1;
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        SimHardwareAct simHardwareAct = mock(SimHardwareAct.class);
        when(simHardwareAct.executeIntegerCommandSim(50)).thenReturn(true);


        try(MockedConstruction<ActuatorIDVO> actuatorIDVOMockedConstruction = mockConstruction(ActuatorIDVO.class)){
            //Act
            RollerBlindActuator rollerBlindActuator = new RollerBlindActuator(actuatorName, actuatorTypeID, deviceIDVO);
            boolean result = rollerBlindActuator.executeCommand(simHardwareAct, 50);
            //Assert
            assertTrue(result);
            assertEquals(expectedIdDoubleSize, actuatorIDVOMockedConstruction.constructed().size());
        }

    }


    /**
     * This test verifies that the executeCommand method returns false when the position is invalid (over 100) and
     * the command execution in the SimHardwareAct object is unsuccessful.
     * It isolates all Actuators collaborators, and it conditions the behavior of the executeCommandSim method of
     * SimHardwareAct to return false when invoked.
     * It then constructs a RollerBlindActuator object using the mocked objects and executes the switchLoad method on it.
     * After execution, it asserts that the result is false.
     * These tests have an additional assertion that verifies the number of instances created for ActuatorIDVO,
     * ensuring that the number of mocked constructions of these objects match the expected.
     */
    @Test
    void whenInvalidPositionOver100AndExecuteCommand_ThenReturnFalse(){
        //Arrange
        int expectedIdDoubleSize = 1;
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        SimHardwareAct simHardwareAct = mock(SimHardwareAct.class);
        when(simHardwareAct.executeIntegerCommandSim(101)).thenReturn(true);
        try(MockedConstruction<ActuatorIDVO> actuatorIDVOMockedConstruction = mockConstruction(ActuatorIDVO.class)){
            //Act
            RollerBlindActuator rollerBlindActuator = new RollerBlindActuator(actuatorName, actuatorTypeID, deviceIDVO);
            boolean result = rollerBlindActuator.executeCommand(simHardwareAct, 101);
            //Assert
            assertFalse(result);
            assertEquals(expectedIdDoubleSize, actuatorIDVOMockedConstruction.constructed().size());
        }
    }


    /**
     * This test verifies that the executeCommand method returns false when the position is invalid (under 0) and
     * the command execution in the SimHardwareAct object is unsuccessful.
     * It isolates all Actuators collaborators, and it conditions the behavior of the executeCommandSim method of
     * SimHardwareAct to return false when invoked.
     * It then constructs a RollerBlindActuator object using the mocked objects and executes the switchLoad method on it.
     * After execution, it asserts that the result is false.
     * These tests have an additional assertion that verifies the number of instances created for ActuatorIDVO,
     * ensuring that the number of mocked constructions of these objects match the expected.
     */
    @Test
    void whenInvalidPositionUnder0AndExecuteCommand_ThenReturnFalse(){
        //Arrange
        int expectedIdDoubleSize = 1;
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        SimHardwareAct simHardwareAct = mock(SimHardwareAct.class);
        when(simHardwareAct.executeIntegerCommandSim(-1)).thenReturn(true);
        try(MockedConstruction<ActuatorIDVO> actuatorIDVOMockedConstruction = mockConstruction(ActuatorIDVO.class)){
            //Act
            RollerBlindActuator rollerBlindActuator = new RollerBlindActuator(actuatorName, actuatorTypeID, deviceIDVO);
            boolean result = rollerBlindActuator.executeCommand(simHardwareAct, -1);
            //Assert
            assertFalse(result);
            assertEquals(expectedIdDoubleSize, actuatorIDVOMockedConstruction.constructed().size());
        }
    }


    /**
     * This test verifies that the executeCommand method returns true when the position is 0 and
     * the command execution in the SimHardwareAct object is successful.
     * It isolates all Actuators collaborators, and it conditions the behavior of the executeCommandSim method of
     * SimHardwareAct to return true when invoked.
     * It then constructs a RollerBlindActuator object using the mocked objects and executes the switchLoad method on it.
     * After execution, it asserts that the result is true.
     * These tests have an additional assertion that verifies the number of instances created for ActuatorIDVO,
     * ensuring that the number of mocked constructions of these objects match the expected.
     */
    @Test
    void whenValidPositionEquals0AndExecuteCommand_ThenReturnTrue(){
        //Arrange
        int expectedIdDoubleSize = 1;
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        SimHardwareAct simHardwareAct = mock(SimHardwareAct.class);
        when(simHardwareAct.executeIntegerCommandSim(0)).thenReturn(true);
        try(MockedConstruction<ActuatorIDVO> actuatorIDVOMockedConstruction = mockConstruction(ActuatorIDVO.class)){
            //Act
            RollerBlindActuator rollerBlindActuator = new RollerBlindActuator(actuatorName, actuatorTypeID, deviceIDVO);
            boolean result = rollerBlindActuator.executeCommand(simHardwareAct, 0);
            //Assert
            assertTrue(result);
            assertEquals(expectedIdDoubleSize, actuatorIDVOMockedConstruction.constructed().size());
        }
    }


    /**
     * This test verifies that the executeCommand method returns true when the position is 100 and
     * the command execution in the SimHardwareAct object is successful.
     * It isolates all Actuators collaborators, and it conditions the behavior of the executeCommandSim method of
     * SimHardwareAct to return true when invoked.
     * It then constructs a RollerBlindActuator object using the mocked objects and executes the switchLoad method on it.
     * After execution, it asserts that the result is true.
     * These tests have an additional assertion that verifies the number of instances created for ActuatorIDVO,
     * ensuring that the number of mocked constructions of these objects match the expected.
     */
    @Test
    void whenValidPositionEquals100AndExecuteCommand_ThenReturnTrue(){
        //Arrange
        int expectedIdDoubleSize = 1;
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        SimHardwareAct simHardwareAct = mock(SimHardwareAct.class);
        when(simHardwareAct.executeIntegerCommandSim(100)).thenReturn(true);
        try(MockedConstruction<ActuatorIDVO> actuatorIDVOMockedConstruction = mockConstruction(ActuatorIDVO.class)){
            //Act
            RollerBlindActuator rollerBlindActuator = new RollerBlindActuator(actuatorName, actuatorTypeID, deviceIDVO);
            boolean result = rollerBlindActuator.executeCommand(simHardwareAct, 100);
            //Assert
            assertTrue(result);
            assertEquals(expectedIdDoubleSize, actuatorIDVOMockedConstruction.constructed().size());
        }
    }

    /**
     * This test verifies that the executeCommand method returns false when the position is valid (between 0 and 100),
     * but the command execution in the SimHardwareAct object fails.
     * It isolates all Actuators collaborators and conditions the behavior of the executeCommandSim method of
     * SimHardwareAct to return false when invoked.
     * It then constructs a RollerBlindActuator object using the mocked objects and executes the executeCommand method on it.
     * After execution, it asserts that the result is false.
     * These tests have an additional assertion that verifies the number of instances created for ActuatorIDVO,
     * ensuring that the number of mocked constructions of these objects match the expected.
     */
    @Test
    void whenValidPositionAndExecuteCommandFails_ThenReturnFalse() {
        // Arrange
        int expectedIdDoubleSize = 1;
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        SimHardwareAct simHardwareAct = mock(SimHardwareAct.class);
        when(simHardwareAct.executeIntegerCommandSim(50)).thenReturn(false);

        try (MockedConstruction<ActuatorIDVO> actuatorIDVOMockedConstruction = mockConstruction(ActuatorIDVO.class)) {
            // Act
            RollerBlindActuator rollerBlindActuator = new RollerBlindActuator(actuatorName, actuatorTypeID, deviceIDVO);
            boolean result = rollerBlindActuator.executeCommand(simHardwareAct, 50);
            // Assert
            assertFalse(result);
            assertEquals(expectedIdDoubleSize, actuatorIDVOMockedConstruction.constructed().size());
        }
    }



    /**
     * This test verifies that the correct ActuatorID value object is returned when the getId() method is called.
     * It isolates all Actuators collaborators and compares the object returned by the previously referred method
     * with the doubled ActuatorID.
     * As in previous tests, it´s being used Mocked Construction to intercept ActuatorID instantiation during
     * RollerBlindActuator instantiation.
     * By this interception the "real" ActuatorID object is being substituted with a double.
     * The assertion is made by getting the doubled object from MockedConstruction.constructed() list and compare it with the object
     * returned in the getID() operation. The references must match because the objects must be the same.
     * This assertion gives us certainty that the intercepted object is the object being return (as it should)
     * These tests have an additional assertion that verifies the number of instances created for ActuatorIDVO,
     * ensuring that the number of mocked constructions of these objects match the expected.
     */
    @Test
    void whenGetId_ThenReturnActuatorIDObject(){
        //Arrange
        int expectedIdDoubleSize = 1;
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        SimHardwareAct simHardwareAct = mock(SimHardwareAct.class);
        when(simHardwareAct.executeIntegerCommandSim(50)).thenReturn(true);

        try(MockedConstruction<ActuatorIDVO> actuatorIDVOMockedConstruction = mockConstruction(ActuatorIDVO.class)){
            //Act
            RollerBlindActuator rollerBlindActuator = new RollerBlindActuator(actuatorName, actuatorTypeID, deviceIDVO);
            ActuatorIDVO result = rollerBlindActuator.getId();
            //Assert
            ActuatorIDVO expected = actuatorIDVOMockedConstruction.constructed().get(0);
            assertEquals(expected, result);
            assertEquals(expectedIdDoubleSize, actuatorIDVOMockedConstruction.constructed().size());
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
        RollerBlindActuator actuator = new RollerBlindActuator(name,type,deviceID);
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
        RollerBlindActuator actuator = new RollerBlindActuator(name,type,deviceID);
        // Act
        ActuatorTypeIDVO result = actuator.getActuatorTypeID();
        // Assert
        assertEquals(type,result);
    }

    /**
     * This test validates that getDeviceID successfully returns DeviceIDVO object
     */
    @Test
    void getDeviceID_returnsDeviceIDVO(){
        // Arrange
        ActuatorNameVO name = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO type = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        RollerBlindActuator actuator = new RollerBlindActuator(name,type,deviceID);
        // Act
        DeviceIDVO result = actuator.getDeviceID();
        // Assert
        assertEquals(deviceID,result);
    }

}