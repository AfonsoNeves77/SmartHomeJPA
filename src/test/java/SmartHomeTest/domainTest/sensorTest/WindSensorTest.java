package SmartHomeTest.domainTest.sensorTest;
import smarthome.domain.sensor.WindSensor;
import smarthome.domain.sensor.externalservices.SimHardware;
import smarthome.domain.sensor.values.WindValue;
import smarthome.vo.ValueObject;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.sensortype.SensorTypeIDVO;
import smarthome.vo.sensorvo.SensorIDVO;
import smarthome.vo.sensorvo.SensorNameVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class WindSensorTest {

    /**
     The following test verifies that if SensorNameVO parameter is null then Wind Sensor instantiation should throw an Illegal Argument Exception with
     the message "Invalid Parameters". All collaborators until the condition responsible for exception throwing are mocked and injected in the constructor.
     */

    @Test
    void givenNullNameVO_ConstructorThrowsIllegalArgumentException() {
        // Arrange
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        String expected = "Invalid parameters";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new WindSensor(null, deviceID, sensorTypeID));

        //Assert
        String result = exception.getMessage();
        assertEquals(expected,result);
    }


    /**
     The following test verifies that if DeviceIDVO parameter is null then Wind Sensor instantiation should throw an Illegal Argument Exception with
     the message "Invalid Parameters". All collaborators until the condition responsible for exception throwing are mocked and injected in the constructor.
     */

    @Test
    void givenNullDeviceIDVO_ConstructorThrowsIllegalArgumentException() {
        // Arrange
        SensorNameVO sensorNameVO = mock(SensorNameVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        String expected = "Invalid parameters";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new WindSensor(sensorNameVO, null, sensorTypeID));

        //Assert
        String result = exception.getMessage();
        assertEquals(expected,result);
    }


    /**
     The following test verifies that if SensorTypeIDVO parameter is null then Wind Sensor instantiation should throw an Illegal Argument Exception with
     the message "Invalid Parameters". All collaborators until the condition responsible for exception throwing are mocked and injected in the constructor.
     */

    @Test
    void givenNullSensorTypeIDVO_ConstructorThrowsIllegalArgumentException() {
        // Arrange
        SensorNameVO sensorNameVO = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        String expected = "Invalid parameters";

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new WindSensor(sensorNameVO, deviceID, null));

        //Assert
        String result = exception.getMessage();
        assertEquals(expected,result);
    }

    /**
     * This test verifies that getReading() throws an Illegal Argument Exception if the injected SimHardware object is null.
     * It isolates all WindSensor collaborators until it´s reached the operation that throws the exception with the following
     * message "Invalid SimHardware".
     * It´s used a mocked construction to double sensorIDVO that is initialized when WindSensor is instantiated.
     * These tests have an additional assertion that verifies the number of instances created for SensorIDVO
     * ensuring proper isolation and that the number of mocked constructions of this object match the expected.
     */

    @Test
    void getReading_WhenSimHardwareIsNull_ShouldThrowIllegalArgumentException(){
        // Arrange
        int expectedSensorIDMockedConstructionsSize = 1;
        SensorNameVO sensorNameVO = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        String expected = "Invalid SimHardware";

        try(MockedConstruction<SensorIDVO> sensorIDVOMockedConstruction = mockConstruction(SensorIDVO.class)){
            WindSensor windSensor = new WindSensor(sensorNameVO,deviceID,sensorTypeID);

            //Act + Assert
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    windSensor.getReading(null));

            //Assert

            String result = exception.getMessage();
            assertEquals(expected,result);
            int resultSensorIDMockedConstructionsSize = sensorIDVOMockedConstruction.constructed().size();
            assertEquals(expectedSensorIDMockedConstructionsSize,resultSensorIDMockedConstructionsSize);
        }
    }

    /**
     * This test verifies that getReading() propagates an Illegal Argument Exception (message -> "Invalid reading") if the injected
     * SimHardware returns an invalid reading.
     * SimHardware is doubled and is conditioned to deliver an invalid reading.
     * When that reading is passed to WindValue, the constructor should throw the previously referred exception. As getReading() does not catch
     * it, it should be propagated.
     * It is used a mocked construction to double sensorIDVO that is initialized when WindSensor is instantiated.
     * These tests have an additional assertion that verifies the number of instances created for SensorIDVO
     * ensuring proper isolation and that the number of mocked constructions of this object match the expected.


     Note: In this test scenario, WindValue is not being doubled; instead, the real object is being used, and the assertion is made
     considering its actual behavior.
     Due to technical reasons, this approach had to be followed for this particular test scenario. When attempting to double the wind value
     using a mocked construction and forcing it to throw an IllegalArgumentException, Mockito "wraps" that exception with a Mockito Exception,
     which invalidates the assertion for the expected IllegalArgumentException.
     */

    @Test
    void getReading_WhenSimHardwareReadingIsInvalid_ShouldThrowIllegalArgumentException(){
        // Arrange
        String expected = "Invalid reading";
        int expectedSensorIDMockedConstructionsSize = 1;
        SensorNameVO sensorNameVO = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);

        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("33:K");
        try(MockedConstruction<SensorIDVO> sensorIDVOMockedConstruction = mockConstruction(SensorIDVO.class)){

            WindSensor windSensor = new WindSensor(sensorNameVO,deviceID,sensorTypeID);

            //Act + Assert
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    windSensor.getReading(simHardware));

            //Assert
            String result = exception.getMessage();
            assertEquals(expected,result);
            int resultSensorIDMockedConstructionsSize = sensorIDVOMockedConstruction.constructed().size();
            assertEquals(expectedSensorIDMockedConstructionsSize,resultSensorIDMockedConstructionsSize);
        }
    }

    /**

     This test verifies that the getReading() method returns the expected WindValue if its instantiation is successful.
     It isolates all collaborators of WindSensor, utilizing a mocked construction to double the SensorIDVO object,
     which is initialized when WindSensor is instantiated, and the WindValue object, which is initialized in the
     getReading() method.
     The assertion is made by comparing the resulting WindValue object returned by getReading() with the first object
     of the mocked constructor.
     Additionally, these tests include an assertion that verifies the number of instances created for SensorIDVO and WindValue,
     ensuring proper isolation and that the number of mocked constructions of these objects match the expected value.
     */

    @Test
    void getReading_ShouldReturnCorrectWindValue(){
        // Arrange
        int expectedSensorIDMockedConstructionsSize = 1;
        int expectedWindValueMockedConstructionsSize = 1;
        SensorNameVO sensorNameVO = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);

        SimHardware simHardware = mock(SimHardware.class);

        try(MockedConstruction<SensorIDVO> sensorIDVOMockedConstruction = mockConstruction(SensorIDVO.class);
            MockedConstruction<WindValue> windValueMockedConstruction = mockConstruction(WindValue.class)){

            WindSensor windSensor = new WindSensor(sensorNameVO,deviceID,sensorTypeID);

            //Act
            ValueObject<String[]> result = windSensor.getReading(simHardware);

            //Assert
            ValueObject<String[]> expected = windValueMockedConstruction.constructed().get(0);
            assertEquals(expected,result);
            int resultSensorIDMockedConstructionsSize = sensorIDVOMockedConstruction.constructed().size();
            assertEquals(expectedSensorIDMockedConstructionsSize,resultSensorIDMockedConstructionsSize);
            int resultWindValueMockedConstructionsSize = windValueMockedConstruction.constructed().size();
            assertEquals(expectedWindValueMockedConstructionsSize,resultWindValueMockedConstructionsSize);

        }
    }

    /**
     * Test case to verify that getNameVO() returns the correct SensorNameVO object.
     * It isolates all collaborators of WindSensor.
     * These tests have an additional assertion that verifies the number of instances created for SensorIDVO
     * ensuring proper isolation and that the number of mocked constructions of this object match the expected.
     */
    @Test
    void getName_ShouldReturnCorrectNameValueObject() {
        // Arrange
        int expectedSensorIDMockedConstructionsSize = 1;
        SensorNameVO expected = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);

        try (MockedConstruction<SensorIDVO> sensorIDVOMockedConstruction = mockConstruction(SensorIDVO.class)) {

            WindSensor windSensor = new WindSensor(expected, deviceID, sensorTypeID);

            // Act
            SensorNameVO result = windSensor.getSensorName();

            // Assert
            assertEquals(expected, result);
            int resultSensorIDMockedConstructionsSize = sensorIDVOMockedConstruction.constructed().size();
            assertEquals(expectedSensorIDMockedConstructionsSize, resultSensorIDMockedConstructionsSize);
        }
    }

    /**
     * Test case to verify that getSensorTypeID() returns the correct SensorTypeVO object.
     * It isolates all collaborators of WindSensor.
     * These tests have an additional assertion that verifies the number of instances created for SensorIDVO
     * ensuring proper isolation and that the number of mocked constructions of this object match the expected.
     */
    @Test
    void getSensorTypeID_ShouldReturnCorrectSensorTypeIDValueObject() {
        // Arrange
        int expectedSensorIDMockedConstructionsSize = 1;
        SensorNameVO sensorNameVO = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO expected = mock(SensorTypeIDVO.class);

        try (MockedConstruction<SensorIDVO> sensorIDVOMockedConstruction = mockConstruction(SensorIDVO.class)) {

            WindSensor windSensor = new WindSensor(sensorNameVO, deviceID, expected);

            // Act
            SensorTypeIDVO result = windSensor.getSensorTypeID();

            // Assert
            assertEquals(expected, result);
            int resultSensorIDMockedConstructionsSize = sensorIDVOMockedConstruction.constructed().size();
            assertEquals(expectedSensorIDMockedConstructionsSize, resultSensorIDMockedConstructionsSize);
        }
    }

    /**
     * Test case to verify that getID() returns the correct SensorIDVO object.
     * It isolates all collaborators of WindSensor.
     * The assertion is made by comparing the resulting SensorIDVO object returned by getID() with the first object
     * of the mocked constructor.
     * These tests have an additional assertion that verifies the number of instances created for SensorIDVO
     * ensuring proper isolation and that the number of mocked constructions of this object match the expected.
     */
    @Test
    void getSensorID_ShouldReturnCorrectSensorIDValueObject() {
        // Arrange
        int expectedSensorIDMockedConstructionsSize = 1;
        SensorNameVO sensorNameVO = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);

        try (MockedConstruction<SensorIDVO> sensorIDVOMockedConstruction = mockConstruction(SensorIDVO.class)) {

            WindSensor windSensor = new WindSensor(sensorNameVO, deviceID, sensorTypeID);

            // Act
            SensorIDVO result = windSensor.getId();

            // Assert
            SensorIDVO sensorIDVO = sensorIDVOMockedConstruction.constructed().get(0);
            assertEquals(sensorIDVO, result);
            int resultSensorIDMockedConstructionsSize = sensorIDVOMockedConstruction.constructed().size();
            assertEquals(expectedSensorIDMockedConstructionsSize, resultSensorIDMockedConstructionsSize);
        }
    }

    /**
     * Test case to verify that getDeviceID() returns the correct DeviceIDVO object.
     * It isolates all collaborators of WindSensor.
     * These tests have an additional assertion that verifies the number of instances created for SensorIDVO
     * ensuring proper isolation and that the number of mocked constructions of this object match the expected.
     */
    @Test
    void getDeviceID_ShouldReturnCorrectDeviceIDValueObject() {
        // Arrange
        int expectedSensorIDMockedConstructionsSize = 1;
        SensorNameVO sensorNameVO = mock(SensorNameVO.class);
        DeviceIDVO expected = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);

        try (MockedConstruction<SensorIDVO> sensorIDVOMockedConstruction = mockConstruction(SensorIDVO.class)) {

            WindSensor windSensor = new WindSensor(sensorNameVO, expected, sensorTypeID);

            // Act
            DeviceIDVO result = windSensor.getDeviceID();

            // Assert
            assertEquals(expected, result);
            int resultSensorIDMockedConstructionsSize = sensorIDVOMockedConstruction.constructed().size();
            assertEquals(expectedSensorIDMockedConstructionsSize, resultSensorIDMockedConstructionsSize);
        }
    }

}
