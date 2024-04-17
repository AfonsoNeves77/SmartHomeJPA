package SmartHomeTest.dtoTest;

import smarthome.dto.LocationDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationDTOTest {

    /**
     * Verifies that a location returns its expected door.
     */
    @Test
    void createLocationDTO_WhenGetDoor_ThenShouldReturnDoorAsString(){
        //Arrange
        String expectedDoor = "123";
        String street = "Rua qualquer";
        String city = "Porto";
        String country = "Portugal";
        String postalCode = "4400-200";
        double latitude = 41;
        double longitude = 8;
        LocationDTO locationDTO = new LocationDTO(expectedDoor,street,city,country,postalCode,latitude,longitude);
        //Act
        String resultDoor = locationDTO.getDoor();

        //Assert
        assertEquals(expectedDoor, resultDoor);
    }

    /**
     * Verifies that a location returns its expected street.
     */
    @Test
    void createLocationDTO_WhenGetStreet_ThenShouldReturnStreet(){
        //Arrange
        String door = "123";
        String expectedStreet = "Rua qualquer";
        String city = "Porto";
        String country = "Portugal";
        String postalCode = "4400-200";
        double latitude = 41;
        double longitude = 8;
        LocationDTO locationDTO = new LocationDTO(door,expectedStreet,city,country,postalCode,latitude,longitude);
        //Act
        String resultStreet = locationDTO.getStreet();

        //Assert
        assertEquals(expectedStreet, resultStreet);
    }

    /**
     * Verifies that a location returns its expected city.
     */
    @Test
    void createLocationDTO_WhenGetCity_ThenShouldReturnCity(){
        //Arrange
        String door = "123";
        String street = "Rua qualquer";
        String expectedCity = "Porto";
        String country = "Portugal";
        String postalCode = "4400-200";
        double latitude = 41;
        double longitude = 8;
        LocationDTO locationDTO = new LocationDTO(door,street,expectedCity,country,postalCode,latitude,longitude);
        //Act
        String resultCity = locationDTO.getCity();

        //Assert
        assertEquals(expectedCity, resultCity);
    }

    /**
     * Verifies that a location returns its expected country.
     */
    @Test
    void createLocationDTO_WhenGetCountry_ThenShouldReturnCountry(){
        //Arrange
        String door = "123";
        String street = "Rua qualquer";
        String city = "Porto";
        String expectedCountry = "Portugal";
        String postalCode = "4400-200";
        double latitude = 41;
        double longitude = 8;
        LocationDTO locationDTO = new LocationDTO(door,street,city,expectedCountry,postalCode,latitude,longitude);
        //Act
        String resultCountry = locationDTO.getCountry();

        //Assert
        assertEquals(expectedCountry, resultCountry);
    }

    /**
     * Verifies that a location returns its expected postalCode.
     */
    @Test
    void createLocationDTO_WhenGetPostalCode_ThenShouldReturnPostalCode(){
        //Arrange
        String door = "123";
        String street = "Rua qualquer";
        String city = "Porto";
        String country = "Portugal";
        String expectedPostalCode = "4400-200";
        double latitude = 41;
        double longitude = 8;
        LocationDTO locationDTO = new LocationDTO(door,street,city,country,expectedPostalCode,latitude,longitude);
        //Act
        String resultPostalCode = locationDTO.getPostalCode();

        //Assert
        assertEquals(expectedPostalCode, resultPostalCode);
    }

    /**
     * Verifies that a location returns its expected latitude.
     */
    @Test
    void createLocationDTO_WhenGetLatitude_ThenShouldReturnLatitude(){
        //Arrange
        String door = "123";
        String street = "Rua qualquer";
        String city = "Porto";
        String country = "Portugal";
        String postalCode = "4400-200";
        double expectedLatitude = 41;
        double longitude = 8;
        LocationDTO locationDTO = new LocationDTO(door,street,city,country,postalCode,expectedLatitude,longitude);
        //Act
        double resultLatitude= locationDTO.getLatitude();

        //Assert
        assertEquals(expectedLatitude, resultLatitude);
    }

    /**
     * Verifies that a location returns its expected longitude.
     */
    @Test
    void createLocationDTO_WhenGetLongitude_ThenShouldReturnLongitude(){
        //Arrange
        String door = "123";
        String street = "Rua qualquer";
        String city = "Porto";
        String country = "Portugal";
        String postalCode = "4400-200";
        double latitude = 41;
        double expectedLongitude = 8;
        LocationDTO locationDTO = new LocationDTO(door,street,city,country,postalCode,latitude,expectedLongitude);
        //Act
        double resultLongitude= locationDTO.getLongitude();

        //Assert
        assertEquals(expectedLongitude, resultLongitude);
    }
}