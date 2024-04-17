package SmartHomeTest.voTest.actuatorVOTest;

import smarthome.vo.actuatorvo.DecimalSettingsVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecimalSettingsVOTest {
    /**
     * Verifies that a DecimalSettingsVO cannot be created with a null lower limit.
     */
    @Test
    void givenNullLowerLimit_WhenVOIsInstantiated_ThenShouldThrowIllegalArgumentException(){
        //Arrange
        String upperLimit = "7.8";
        String precision = "0.1";
        String expected = "Invalid actuator settings";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DecimalSettingsVO(null, upperLimit, precision));
        String result = exception.getMessage();
        //Arrange
        assertEquals(expected, result);
    }

    /**
     * Verifies that a DecimalSettingsVO cannot be created with a null upper limit.
     */
    @Test
    void givenNullUpperLimit_WhenVOIsInstantiated_ThenShouldThrowIllegalArgumentException(){
        //Arrange
        String lowerLimit = "6.5";
        String precision = "0.1";
        String expected = "Invalid actuator settings";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DecimalSettingsVO(lowerLimit, null, precision));
        String result = exception.getMessage();
        //Arrange
        assertEquals(expected, result);
    }

    /**
     * Verifies that a DecimalSettingsVO cannot be created with a null precision.
     */
    @Test
    void givenNullPrecision_WhenVOIsInstantiated_ThenShouldThrowIllegalArgumentException(){
        //Arrange
        String lowerLimit = "6.5";
        String upperLimit = "7.8";
        String expected = "Invalid actuator settings";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DecimalSettingsVO(lowerLimit, upperLimit, null));
        String result = exception.getMessage();
        //Arrange
        assertEquals(expected, result);
    }

    /**
     * Verifies that a DecimalSettingsVO cannot be created with a lower limit higher than the upper limit.
     */
    @Test
    void givenLowerLimitHigherThanUpper_WhenVOIsInstantiated_ThenShouldThrowIllegalArgumentException(){
        //Arrange
        String lowerLimit = "8.5";
        String upperLimit = "7.8";
        String precision = "0.1";
        String expected = "Invalid actuator settings";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DecimalSettingsVO(lowerLimit, upperLimit, precision));
        String result = exception.getMessage();
        //Arrange
        assertEquals(expected, result);
    }

    /**
     * Verifies that a DecimalSettingsVO cannot be created with a lower limit equal to the upper limit.
     */
    @Test
    void givenLowerLimitEqualToUpper_WhenVOIsInstantiated_ThenShouldThrowIllegalArgumentException(){
        //Arrange
        String lowerLimit = "8.5";
        String upperLimit = "8.5";
        String precision = "0.1";
        String expected = "Invalid actuator settings";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DecimalSettingsVO(lowerLimit, upperLimit, precision));
        String result = exception.getMessage();
        //Arrange
        assertEquals(expected, result);
    }

    /**
     * Verifies that a DecimalSettingsVO cannot be created with lower and upper limits with different precisions.
     */
    @Test
    void givenLimitsWithDifferentPrecisions_WhenVOIsInstantiated_ThenShouldThrowIllegalArgumentException(){
        //Arrange
        String lowerLimit = "6.55";
        String upperLimit = "7.8";
        String precision = "0.1";
        String expected = "Invalid actuator settings";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DecimalSettingsVO(lowerLimit, upperLimit, precision));
        String result = exception.getMessage();
        //Arrange
        assertEquals(expected, result);
    }

    /**
     * Verifies that a DecimalSettingsVO cannot be created with a precision equal to zero.
     */
    @Test
    void givenPrecisionEqualToZero_WhenVOIsInstantiated_ThenShouldThrowIllegalArgumentException(){
        //Arrange
        String lowerLimit = "6.55";
        String upperLimit = "7.83";
        String precision = "0";
        String expected = "Invalid actuator settings";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DecimalSettingsVO(lowerLimit, upperLimit, precision));
        String result = exception.getMessage();
        //Arrange
        assertEquals(expected, result);
    }

    /**
     * Verifies that a DecimalSettingsVO cannot be created with a precision lower than zero.
     */
    @Test
    void givenPrecisionLowerThanZero_WhenVOIsInstantiated_ThenShouldThrowIllegalArgumentException(){
        //Arrange
        String lowerLimit = "6.55";
        String upperLimit = "7.83";
        String precision = "-0.02";
        String expected = "Invalid actuator settings";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DecimalSettingsVO(lowerLimit, upperLimit, precision));
        String result = exception.getMessage();
        //Arrange
        assertEquals(expected, result);
    }

    /**
     * Verifies that a DecimalSettingsVO cannot be created with a precision higher than one.
     */
    @Test
    void givenPrecisionHigherThanOne_WhenVOIsInstantiated_ThenShouldThrowIllegalArgumentException(){
        //Arrange
        String lowerLimit = "6.55";
        String upperLimit = "7.83";
        String precision = "1.54";
        String expected = "Invalid actuator settings";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DecimalSettingsVO(lowerLimit, upperLimit, precision));
        String result = exception.getMessage();
        //Arrange
        assertEquals(expected, result);
    }

    /**
     * Verifies that a DecimalSettingsVO cannot be created with a precision equal to one.
     */
    @Test
    void givenPrecisionEqualToOne_WhenVOIsInstantiated_ThenShouldThrowIllegalArgumentException(){
        //Arrange
        String lowerLimit = "6.55";
        String upperLimit = "7.83";
        String precision = "1";
        String expected = "Invalid actuator settings";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DecimalSettingsVO(lowerLimit, upperLimit, precision));
        String result = exception.getMessage();
        //Arrange
        assertEquals(expected, result);
    }

    /**
     * Verifies that a DecimalSettingsVO cannot be created with if limits are defined with a higher precision than the
     * defined.
     */
    @Test
    void givenLimitsWithHigherPrecisionThanDefinedPrecision_WhenVOIsInstantiated_ThenShouldThrowIllegalArgumentException(){
        //Arrange
        String lowerLimit = "6.555";
        String upperLimit = "7.835";
        String precision = "0.01";
        String expected = "Invalid actuator settings";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DecimalSettingsVO(lowerLimit, upperLimit, precision));
        String result = exception.getMessage();
        //Arrange
        assertEquals(expected, result);
    }

    /**
     * Verifies that is possible to create a DecimalSettingsVO where the precision of the set lower and upper limits
     * is lower than the defined precision.
     */
    @Test
    void givenLimitsWithLowerDefinedPrecisionThanActualPrecision_VOIsCreated_WhenGetValue_ThenShouldReturnSettingsArray(){
        //Arrange
        String lowerLimit = "6.5";
        String upperLimit = "7.5";
        String precision = "0.001";
        Double[] expected = {6.5, 7.5, 0.001};
        DecimalSettingsVO settingsVO = new DecimalSettingsVO(lowerLimit, upperLimit, precision);
        //Act
        Double[] result = settingsVO.getValue();
        //Arrange
        assertArrayEquals(expected, result);
    }

    /**
     * Verifies that a DecimalSettingsVO is created when upper and lower limits are valid and their precision matches the
     * defined precision.
     */
    @Test
    void givenLimitsRespectingDefinedPrecision_VOIsCreated_WhenGetValue_ThenShouldReturnSettingsArray(){
        //Arrange
        String lowerLimit = "6.555";
        String upperLimit = "7.835";
        String precision = "0.555";
        Double[] expected = {6.555, 7.835, 0.555};
        DecimalSettingsVO settingsVO = new DecimalSettingsVO(lowerLimit, upperLimit, precision);
        //Act
        Double[] result = settingsVO.getValue();
        //Arrange
        assertArrayEquals(expected, result);
    }

}