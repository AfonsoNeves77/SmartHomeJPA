package SmartHomeTest.domainTest.sensorTest.externalServicesTest;


import smarthome.domain.sensor.externalservices.SunTimeCalculator;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SunTimeCalculatorTest {

    /**
     * This test ensures that, given valid date and gps, computeSunset returns a Zoned Date Time object, which
     * is then accessible by using toString()
     */
    @Test
    void givenValidDateAndGPs_computeSunsetSuccessfullyCreatesZonedDateTimeObjectAccessibleByToString()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = "2024-03-06";
        String coordinates = "41.1579 : -8.6291";
        String expected = "2024-03-06T18:31:47Z[UTC]";
        //Act
        ZonedDateTime resultZoneDateTime = sunTimeCalculator.computeSunset(date, coordinates);
        String result = resultZoneDateTime.toString();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * This test validates that when given an invalid date, computeSunset returns a null Zoned Date Time object.
     */
    @Test
    void givenInvalidDate_computeSunsetReturnsNull()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String coordinates = "41.1579 : -8.6291";
        //Act
        ZonedDateTime result = sunTimeCalculator.computeSunset(null, coordinates);
        //Assert
        assertNull(result);
    }

    /**
     * This test validates that when given an invalid date, computeSunset returns a null Zoned Date Time object.
     */
    @Test
    void givenBlankDate_computeSunsetReturnsNull()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = " ";
        String coordinates = "41.1579 : -8.6291";
        //Act
        ZonedDateTime result = sunTimeCalculator.computeSunset(date, coordinates);
        //Assert
        assertNull(result);
    }

    /**
     * This test validates that when given an invalid date, computeSunset returns a null Zoned Date Time object.
     */
    @Test
    void givenWrongFormatDate_computeSunsetReturnsNull()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = "2024/13/06";
        String coordinates = "41.1579 : -8.6291";
        //Act
        ZonedDateTime result = sunTimeCalculator.computeSunset(date, coordinates);
        //Assert
        assertNull(result);
    }

    /**
     * This test validates that when given an invalid date, computeSunset returns a null Zoned Date Time object.
     */
    @Test
    void givenImpossibleDate_computeSunsetReturnsNull()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = "2024-15-06";
        String coordinates = "41.1579 : -8.6291";
        //Act
        ZonedDateTime result = sunTimeCalculator.computeSunset(date, coordinates);
        //Assert
        assertNull(result);
    }

    /**
     * This test validates that when given an invalid gpsLocation, computeSunset returns a null Zoned Date Time object.
     */
    @Test
    void givenInvalidGPS_computeSunsetReturnsNull()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = "2024-03-06";
        //Act
        ZonedDateTime result = sunTimeCalculator.computeSunset(date, null);
        //Assert
        assertNull(result);
    }

    /**
     * This test validates that when given an invalid gpsLocation, computeSunset returns a null Zoned Date Time object.
     */
    @Test
    void givenBlankGPS_computeSunsetReturnsNull()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = "2024-03-06";
        String coordinates = " ";
        //Act
        ZonedDateTime result = sunTimeCalculator.computeSunset(date, coordinates);
        //Assert
        assertNull(result);
    }

    /**
     * This test validates that when given an invalid gpsLocation, computeSunset returns a null Zoned Date Time object.
     */
    @Test
    void givenWrongFormatGPS_computeSunsetReturnsNull()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = "2024-03-06";
        String coordinates = "41.1579/-8.6291";
        //Act
        ZonedDateTime result = sunTimeCalculator.computeSunset(date, coordinates);
        //Assert
        assertNull(result);
    }

    /**
     * This test validates that when given an invalid gpsLocation, computeSunset returns a null Zoned Date Time object.
     */
    @Test
    void givenImpossibleGPS_computeSunsetReturnsNull()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = "2024-03-06";
        String coordinates = "59000.1579 : -8.6291";
        //Act
        ZonedDateTime result = sunTimeCalculator.computeSunset(date, coordinates);
        //Assert
        assertNull(result);
    }

    /**
     * This test ensures that, given valid date and gps, computeSunrise returns a Zoned Date Time object, which
     * is then accessible by using toString()
     */
    @Test
    void givenValidDateAndGPs_ComputeSunriseSuccessfullyCreatesZonedDateTimeObjectAccessibleByToString()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = "2024-03-06";
        String coordinates = "41.1579 : -8.6291";
        String expected = "2024-03-06T07:00:19Z[UTC]";
        //Act
        ZonedDateTime resultZoneDateTime = sunTimeCalculator.computeSunrise(date, coordinates);
        String result = resultZoneDateTime.toString();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * This test validates that when given an invalid date, computeRise returns a null Zoned Date Time object.
     */
    @Test
    void givenInvalidDate_computeSunriseReturnsNull()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String coordinates = "41.1579 : -8.6291";
        //Act
        ZonedDateTime result = sunTimeCalculator.computeSunrise(null, coordinates);
        //Assert
        assertNull(result);
    }

    /**
     * This test validates that when given a blank date, computeSunrise returns a null Zoned Date Time object.
     */
    @Test
    void givenBlankDate_computeSunriseReturnsNull()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = " ";
        String coordinates = "41.1579 : -8.6291";
        //Act
        ZonedDateTime result = sunTimeCalculator.computeSunrise(date, coordinates);
        //Assert
        assertNull(result);
    }

    /**
     * This test validates that when given an invalid date, computeSunrise returns a null Zoned Date Time object.
     */
    @Test
    void givenWrongFormatDate_computeSunriseReturnsNull()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = "2024/13/06";
        String coordinates = "41.1579 : -8.6291";
        //Act
        ZonedDateTime result = sunTimeCalculator.computeSunrise(date, coordinates);
        //Assert
        assertNull(result);
    }

    /**
     * This test validates that when given an invalid date, computeSunrise returns a null Zoned Date Time object.
     */
    @Test
    void givenImpossibleDate_computeSunriseReturnsNull()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = "2024-15-06";
        String coordinates = "41.1579 : -8.6291";
        //Act
        ZonedDateTime result = sunTimeCalculator.computeSunrise(date, coordinates);
        //Assert
        assertNull(result);
    }

    /**
     * This test validates that when given an invalid gpsLocation, computeSunrise returns a null Zoned Date Time object.
     */
    @Test
    void givenInvalidGPS_computeSunriseReturnsNull()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = "2024-03-06";
        //Act
        ZonedDateTime result = sunTimeCalculator.computeSunrise(date, null);
        //Assert
        assertNull(result);
    }

    /**
     * This test validates that when given an invalid gpsLocation, computeSunrise returns a null Zoned Date Time object.
     */
    @Test
    void givenBlankGPS_computeSunriseReturnsNull()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = "2024-03-06";
        String coordinates = " ";
        //Act
        ZonedDateTime result = sunTimeCalculator.computeSunrise(date, coordinates);
        //Assert
        assertNull(result);
    }

    /**
     * This test validates that when given an invalid gpsLocation, computeSunrise returns a null Zoned Date Time object.
     */
    @Test
    void givenWrongFormatGPS_computeSunriseReturnsNull()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = "2024-03-06";
        String coordinates = "41.1579/-8.6291";
        //Act
        ZonedDateTime result = sunTimeCalculator.computeSunrise(date, coordinates);
        //Assert
        assertNull(result);
    }

    /**
     * This test validates that when given an invalid gpsLocation, computeSunrise returns a null Zoned Date Time object.
     */
    @Test
    void givenImpossibleGPS_computeSunriseReturnsNull()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = "2024-03-06";
        String coordinates = "59000.1579 : -8.6291";
        //Act
        ZonedDateTime result = sunTimeCalculator.computeSunrise(date, coordinates);
        //Assert
        assertNull(result);
    }
}
