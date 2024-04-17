package IntegrationTests;

import smarthome.controller.ConfigureLocationCTRL;
import smarthome.domain.house.House;
import smarthome.domain.house.HouseFactoryImpl;
import smarthome.dto.LocationDTO;
import smarthome.repository.HouseRepositoryMem;
import smarthome.services.HouseServiceImpl;
import smarthome.vo.housevo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ConfigureLocationCTRL class
 */

class ConfigureLocationCTRLTest {

    private HouseRepositoryMem houseRepositoryMem;
    private HouseServiceImpl houseServiceImpl;
    /**
     * Method that initializes the house repository, house factory, house service, door, street, city, country,
     * postal code, latitude, longitude and location data transfer object
     */

    @BeforeEach
    void setUp() {
        houseRepositoryMem = new HouseRepositoryMem();
        HouseFactoryImpl houseFactoryImpl = new HouseFactoryImpl();
        houseServiceImpl = new HouseServiceImpl(houseRepositoryMem, houseFactoryImpl);
    }

    /**
     * Method that tests the creation of a ConfigureLocationCTRL object
     * The test checks if the object is created successfully
     */

    @Test
    void givenNullHouseService_whenCreateConfigureLocationCTRL_thenThrowIllegalArgumentException() {
//        Arrange
        houseServiceImpl = null;
//        Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ConfigureLocationCTRL(houseServiceImpl));
    }

    /**
     * Method that tests the update of the location of the house
     * The test checks if the location data transfer object is null and returns false
     */

    @Test
    void givenNullLocationDTO_whenUpdateLocation_thenReturnFalse() {
//        Arrange
        ConfigureLocationCTRL configureLocationCTRL = new ConfigureLocationCTRL(houseServiceImpl);
//        Act
        boolean result = configureLocationCTRL.updateLocation(null);
//        Assert
        assertFalse(result);
    }

    /**
     * Test method to verify that when a valid LocationDTO is provided to update a location,
     * the updateLocation method in the ConfigureLocationCTRL returns true,
     * indicating a successful update of the house's location.
     * This test also verifies that the updated location values match the expected values.
     */
    @Test
    void givenValidLocationDTO_whenUpdateLocation_thenReturnTrue() {
//        Arrange
        String expectedDoorNumber = "2";
        String expectedStreetName = "Street2";
        String expectedCityName = "City2";
        String expectedCountryName = "France";
        String expectedPostalCodeName = "FR-1234-567";

        DoorVO door = new DoorVO(expectedDoorNumber);
        StreetVO street = new StreetVO(expectedStreetName);
        CityVO city = new CityVO(expectedCityName);
        CountryVO country = new CountryVO(expectedCountryName);
        PostalCodeVO postalCode = new PostalCodeVO(expectedPostalCodeName);
        AddressVO addressVO = new AddressVO(door, street, city, country, postalCode);

        double expectedLatitudeValue = 77.777;
        double expectedLongitudeValue = -89.999;

        LatitudeVO latitude = new LatitudeVO(expectedLatitudeValue);
        LongitudeVO longitude = new LongitudeVO(expectedLongitudeValue);
        GpsVO gpsVO = new GpsVO(latitude, longitude);

        LocationVO locationVO = new LocationVO(addressVO, gpsVO);

        LocationDTO locationDTO1 = new LocationDTO(expectedDoorNumber,expectedStreetName,expectedCityName,expectedCountryName,expectedPostalCodeName,expectedLatitudeValue,expectedLongitudeValue);

        houseServiceImpl.addHouse(locationVO);

        House house = houseRepositoryMem.getFirstHouse();

        ConfigureLocationCTRL configureLocationCTRL = new ConfigureLocationCTRL(houseServiceImpl);

        LocationVO resultLocation = house.getLocation();
//        Act
        boolean result = configureLocationCTRL.updateLocation(locationDTO1);
        String resultDoorNumber = resultLocation.getDoor();
        String resultStreetName = resultLocation.getStreet();
        String resultCountry = resultLocation.getCountry();
        String resultCity = resultLocation.getCity();
        String resultPostalCode = resultLocation.getPostalCode();
        double resultLatitude = resultLocation.getLatitude();
        double resultLongitude = resultLocation.getLongitude();

//        Assert
        assertTrue(result);
        assertEquals(expectedDoorNumber,resultDoorNumber);
        assertEquals(expectedStreetName,resultStreetName);
        assertEquals(expectedCountryName,resultCountry);
        assertEquals(expectedCityName,resultCity);
        assertEquals(expectedPostalCodeName,resultPostalCode);
        assertEquals(expectedLatitudeValue,resultLatitude);
        assertEquals(expectedLongitudeValue,resultLongitude);
    }

    /**
     * Method that tests the update of the location of the house when one of the values passed to the LocationDTO is invalid
     * The test checks if the location data transfer object has a null door number and returns false
     */

    @Test
    void givenNullDoorNumber_whenUpdateLocation_thenReturnFalse() {
//        Arrange
        String streetName = "Street2";
        String cityName = "City2";
        String countryName = "France";
        String postalCodeName = "FR-1234-567";
        double latitudeValue = 77.777;
        double longitudeValue = -89.999;
        LocationDTO secondLocationDTO = new LocationDTO(null, streetName, cityName, countryName, postalCodeName, latitudeValue, longitudeValue);
        ConfigureLocationCTRL configureLocationCTRL = new ConfigureLocationCTRL(houseServiceImpl);
//        Act
        boolean result = configureLocationCTRL.updateLocation(secondLocationDTO);
//        Assert
        assertFalse(result);
    }

    /**
     * Method that tests the update of the location of the house when one of the values passed to the LocationDTO is invalid
     * The test checks if the location data transfer object has a null street name and returns false
     */

    @Test
    void givenNullStreetName_whenUpdateLocation_thenReturnFalse() {
//        Arrange
        String doorNumber = "2";
        String cityName = "City2";
        String countryName = "France";
        String postalCodeName = "FR-1234-567";
        double latitudeValue = 77.777;
        double longitudeValue = -89.999;
        LocationDTO secondLocationDTO = new LocationDTO(doorNumber, null, cityName, countryName, postalCodeName, latitudeValue, longitudeValue);
        ConfigureLocationCTRL configureLocationCTRL = new ConfigureLocationCTRL(houseServiceImpl);
//        Act
        boolean result = configureLocationCTRL.updateLocation(secondLocationDTO);
//        Assert
        assertFalse(result);
    }

    /**
     * Method that tests the update of the location of the house when one of the values passed to the LocationDTO is invalid
     * The test checks if the location data transfer object has a null city name and returns false
     */

    @Test
    void givenNullCityName_whenUpdateLocation_thenReturnFalse() {
//        Arrange
        String doorNumber = "2";
        String streetName = "Street2";
        String countryName = "France";
        String postalCodeName = "FR-1234-567";
        double latitudeValue = 77.777;
        double longitudeValue = -89.999;
        LocationDTO secondLocationDTO = new LocationDTO(doorNumber, streetName, null, countryName, postalCodeName, latitudeValue, longitudeValue);
        ConfigureLocationCTRL configureLocationCTRL = new ConfigureLocationCTRL(houseServiceImpl);
//        Act
        boolean result = configureLocationCTRL.updateLocation(secondLocationDTO);
//        Assert
        assertFalse(result);
    }

    /**
     * Method that tests the update of the location of the house when one of the values passed to the LocationDTO is invalid
     * The test checks if the location data transfer object has a null country name and returns false
     */

    @Test
    void givenNullCountryName_whenUpdateLocation_thenReturnFalse() {
//        Arrange
        String doorNumber = "2";
        String streetName = "Street2";
        String cityName = "City2";
        String postalCodeName = "FR-1234-567";
        double latitudeValue = 77.777;
        double longitudeValue = -89.999;
        LocationDTO secondLocationDTO = new LocationDTO(doorNumber, streetName, cityName, null, postalCodeName, latitudeValue, longitudeValue);
        ConfigureLocationCTRL configureLocationCTRL = new ConfigureLocationCTRL(houseServiceImpl);
//        Act
        boolean result = configureLocationCTRL.updateLocation(secondLocationDTO);
//        Assert
        assertFalse(result);
    }

    /**
     * Method that tests the update of the location of the house when one of the values passed to the LocationDTO is invalid
     * The test checks if the location data transfer object has a null postal code name and returns false
     */

    @Test
    void givenNullPostalCodeName_whenUpdateLocation_thenReturnFalse() {
//        Arrange
        String doorNumber = "2";
        String streetName = "Street2";
        String cityName = "City2";
        String countryName = "France";
        double latitudeValue = 77.777;
        double longitudeValue = -89.999;
        LocationDTO secondLocationDTO = new LocationDTO(doorNumber, streetName, cityName, countryName, null, latitudeValue, longitudeValue);
        ConfigureLocationCTRL configureLocationCTRL = new ConfigureLocationCTRL(houseServiceImpl);
//        Act
        boolean result = configureLocationCTRL.updateLocation(secondLocationDTO);
//        Assert
        assertFalse(result);
    }

    /**
     * Method that tests the update of the location of the house when one of the values passed to the LocationDTO is invalid
     * The test checks if the location data transfer object has an invalid latitude value and returns false
     */

    @Test
    void givenInvalidLatitudeValue_whenUpdateLocation_thenReturnFalse() {
//        Arrange
        String doorNumber = "2";
        String streetName = "Street2";
        String cityName = "City2";
        String countryName = "France";
        String postalCodeName = "FR-1234-567";
        double latitudeValue = -100.54321;
        double longitudeValue = -89.999;
        LocationDTO secondLocationDTO = new LocationDTO(doorNumber, streetName, cityName, countryName, postalCodeName, latitudeValue, longitudeValue);
        ConfigureLocationCTRL configureLocationCTRL = new ConfigureLocationCTRL(houseServiceImpl);
//        Act
        boolean result = configureLocationCTRL.updateLocation(secondLocationDTO);
//        Assert
        assertFalse(result);
    }

    /**
     * Method that tests the update of the location of the house when one of the values passed to the LocationDTO is invalid
     * The test checks if the location data transfer object has an invalid longitude value and returns false
     */

    @Test
    void givenInvalidLongitudeValue_whenUpdateLocation_thenReturnFalse() {
//        Arrange
        String doorNumber = "2";
        String streetName = "Street2";
        String cityName = "City2";
        String countryName = "France";
        String postalCodeName = "FR-1234-567";
        double latitudeValue = 77.777;
        double longitudeValue = -200.54321;
        LocationDTO secondLocationDTO = new LocationDTO(doorNumber, streetName, cityName, countryName, postalCodeName, latitudeValue, longitudeValue);
        ConfigureLocationCTRL configureLocationCTRL = new ConfigureLocationCTRL(houseServiceImpl);
//        Act
        boolean result = configureLocationCTRL.updateLocation(secondLocationDTO);
//        Assert
        assertFalse(result);
    }

    /**
     * Method that tests the update of the location of the house when all the values passed to the LocationDTO are invalid
     * The test checks if the location data transfer object has all null or invalid values and returns false
     */

    @Test
    void givenLocationDTOWithAllNullOrInvalidValues_whenUpdateLocation_thenReturnFalse() {
//        Arrange
        double latitudeValue = -100.54321;
        double longitudeValue = -200.54321;
        LocationDTO secondLocationDTO = new LocationDTO(null, null, null, null, null, latitudeValue, longitudeValue);
        ConfigureLocationCTRL configureLocationCTRL = new ConfigureLocationCTRL(houseServiceImpl);
//        Act
        boolean result = configureLocationCTRL.updateLocation(secondLocationDTO);
//        Assert
        assertFalse(result);
    }
}
