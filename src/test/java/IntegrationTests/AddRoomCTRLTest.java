package IntegrationTests;

import smarthome.controller.AddRoomCTRL;
import smarthome.domain.house.HouseFactoryImpl;
import smarthome.domain.room.Room;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.dto.RoomDTO;
import smarthome.repository.HouseRepositoryMem;
import smarthome.repository.RoomRepositoryMem;
import smarthome.services.HouseServiceImpl;
import smarthome.services.RoomServiceImpl;
import smarthome.vo.housevo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddRoomCTRLTest {

    /**
     * This test ensures that the constructor throws an IllegalArgumentException when provided a null room service.
     */
    @Test
    void givenInvalidService_constructorThrowsIllegalArgumentException() {
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new AddRoomCTRL(null));
    }

    /**
     * Test to check if the addRoom method returns true when a room is successfully added
     * when a valid RoomDTO is given as input.
     */
    @Test
    void whenAddRoomIsCalled_ReturnsTrueAndRoomIsCreatedAndAddedToTheRepository() {
        // Arrange

        // setting up the dependencies
        RoomRepositoryMem roomRepositoryMem = new RoomRepositoryMem();
        HouseRepositoryMem houseRepository = new HouseRepositoryMem();
        RoomFactoryImpl roomFactoryImpl = new RoomFactoryImpl();

        HouseFactoryImpl houseFactoryImpl = new HouseFactoryImpl();
        HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseRepository, houseFactoryImpl);

        String door = "1";
        DoorVO doorVO = new DoorVO(door);
        String street = "Rua de Santa Catarina";
        StreetVO streetVO = new StreetVO(street);
        String city = "Porto";
        CityVO cityVO = new CityVO(city);
        String country = "Portugal";
        CountryVO countryVO = new CountryVO(country);
        String postalCode = "PT-4000-009";
        PostalCodeVO postalCodeVO = new PostalCodeVO(postalCode);
        double latitude = 41.14961;
        LatitudeVO latitudeVO = new LatitudeVO(latitude);
        double longitude = -8.61099;
        LongitudeVO longitudeVO = new LongitudeVO(longitude);

        AddressVO addressVO = new AddressVO(doorVO, streetVO, cityVO, countryVO, postalCodeVO);
        GpsVO gspVO = new GpsVO(latitudeVO, longitudeVO);

        LocationVO locationVO = new LocationVO(addressVO, gspVO);

        houseServiceImpl.addHouse(locationVO);


        String roomName = "bedRoom";
        int floor = 2;
        double roomHeight = 2.2;
        double roomLength = 4.5;
        double roomWidth = 5.0;
        RoomDTO roomDTO = new RoomDTO(null, roomName,floor,roomHeight, roomLength, roomWidth, null);


        RoomServiceImpl roomServiceImpl = new RoomServiceImpl(houseRepository, roomRepositoryMem, roomFactoryImpl);

        AddRoomCTRL addRoomCTRL = new AddRoomCTRL(roomServiceImpl);

        // Act
        boolean result = addRoomCTRL.addRoom(roomDTO);

        // Assert
        assertTrue(result);
    }


    /**
     * Test to check if the roomRepository contains the room that was added when
     * the method addRoom from the controller is called and a valid RoomDTO
     * is given as input.
     */
    @Test
    void whenAddRoomIsCalled_RoomIsAddedToTheRepository() {
        // Arrange
        String roomName = "bedRoom";
        int floor = 2;
        double roomHeight = 2.2;
        double roomLength = 4.5;
        double roomWidth = 5.0;
        RoomDTO roomDTO = new RoomDTO(null, roomName,floor,roomHeight, roomLength, roomWidth, null);

        String door = "1";
        String street = "Rua de Santa Catarina";
        String city = "Porto";
        String country = "Portugal";
        String postalCode = "PT-4000-009";
        double latitude = 41.14961;
        double longitude = -8.61099;

        RoomRepositoryMem roomRepositoryMem = new RoomRepositoryMem();
        HouseRepositoryMem houseRepository = new HouseRepositoryMem();
        RoomFactoryImpl roomFactoryImpl = new RoomFactoryImpl();
        RoomServiceImpl roomServiceImpl = new RoomServiceImpl(houseRepository, roomRepositoryMem, roomFactoryImpl);
        HouseFactoryImpl houseFactoryImpl = new HouseFactoryImpl();
        HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseRepository, houseFactoryImpl);

        DoorVO doorVO = new DoorVO(door);
        StreetVO streetVO = new StreetVO(street);
        CityVO cityVO = new CityVO(city);
        CountryVO countryVO = new CountryVO(country);
        PostalCodeVO postalCodeVO = new PostalCodeVO(postalCode);

        LatitudeVO latitudeVO = new LatitudeVO(latitude);
        LongitudeVO longitudeVO = new LongitudeVO(longitude);

        AddressVO addressVO = new AddressVO(doorVO, streetVO, cityVO, countryVO, postalCodeVO);
        GpsVO gspVO = new GpsVO(latitudeVO, longitudeVO);
        LocationVO locationVO = new LocationVO(addressVO, gspVO);
        houseServiceImpl.addHouse(locationVO);

        AddRoomCTRL addRoomCTRL = new AddRoomCTRL(roomServiceImpl);

        // Act
        boolean result = addRoomCTRL.addRoom(roomDTO);
        Room room = roomRepositoryMem.findAll().iterator().next();
        String nameResult = room.getRoomName().getValue();
        int floorResult = room.getFloor().getValue();
        double widthResult = room.getRoomDimensions().getRoomWidth();
        double lengthResult = room.getRoomDimensions().getRoomLength();
        double heightResult = room.getRoomDimensions().getRoomHeight();

        // Assert
        assertTrue(result);
        assertEquals(roomName,nameResult);
        assertEquals(floor,floorResult);
        assertEquals(roomWidth,widthResult);
        assertEquals(roomLength,lengthResult);
        assertEquals(roomHeight,heightResult);
    }


    /**
     * Test to check if the addRoom method returns false when a null
     * RoomDTO is given as input.
     */
    @Test
    void whenAddRoomIsCalled_ReturnsFalseForNullRoomDTO() {
        // Arrange
        String door = "1";
        String street = "Rua de Santa Catarina";
        String city = "Porto";
        String country = "Portugal";
        String postalCode = "PT-4000-009";
        double latitude = 41.14961;
        double longitude = -8.61099;

        RoomRepositoryMem roomRepositoryMem = new RoomRepositoryMem();
        HouseRepositoryMem houseRepository = new HouseRepositoryMem();
        RoomFactoryImpl roomFactoryImpl = new RoomFactoryImpl();
        RoomServiceImpl roomServiceImpl = new RoomServiceImpl(houseRepository, roomRepositoryMem, roomFactoryImpl);
        HouseFactoryImpl houseFactoryImpl = new HouseFactoryImpl();
        HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseRepository, houseFactoryImpl);

        DoorVO doorVO = new DoorVO(door);
        StreetVO streetVO = new StreetVO(street);
        CityVO cityVO = new CityVO(city);
        CountryVO countryVO = new CountryVO(country);
        PostalCodeVO postalCodeVO = new PostalCodeVO(postalCode);

        LatitudeVO latitudeVO = new LatitudeVO(latitude);
        LongitudeVO longitudeVO = new LongitudeVO(longitude);

        AddressVO addressVO = new AddressVO(doorVO, streetVO, cityVO, countryVO, postalCodeVO);
        GpsVO gspVO = new GpsVO(latitudeVO, longitudeVO);
        LocationVO locationVO = new LocationVO(addressVO, gspVO);
        houseServiceImpl.addHouse(locationVO);

        AddRoomCTRL addRoomCTRL = new AddRoomCTRL(roomServiceImpl);

        // Act
        boolean result = addRoomCTRL.addRoom(null);

        // Assert
        assertFalse(result);
    }


    /**
     * Test to check if the addRoom method returns false when a null
     * RoomNameDTO is given as input.
     */
    @Test
    void whenAddRoomIsCalled_ReturnsFalseGivenNullRoomName()  {
        // Arrange
        int floor = 2;
        double roomHeight = 2.2;
        double roomLength = 4.5;
        double roomWidth = 5.0;
        RoomDTO roomDTO = new RoomDTO(null, null,floor,roomHeight, roomLength, roomWidth, null);

        String door = "1";
        String street = "Rua de Santa Catarina";
        String city = "Porto";
        String country = "Portugal";
        String postalCode = "PT-4000-009";
        double latitude = 41.14961;
        double longitude = -8.61099;

        RoomRepositoryMem roomRepositoryMem = new RoomRepositoryMem();
        HouseRepositoryMem houseRepository = new HouseRepositoryMem();
        RoomFactoryImpl roomFactoryImpl = new RoomFactoryImpl();
        RoomServiceImpl roomServiceImpl = new RoomServiceImpl(houseRepository, roomRepositoryMem, roomFactoryImpl);
        HouseFactoryImpl houseFactoryImpl = new HouseFactoryImpl();
        HouseServiceImpl houseServiceImpl = new HouseServiceImpl(houseRepository, houseFactoryImpl);

        DoorVO doorVO = new DoorVO(door);
        StreetVO streetVO = new StreetVO(street);
        CityVO cityVO = new CityVO(city);
        CountryVO countryVO = new CountryVO(country);
        PostalCodeVO postalCodeVO = new PostalCodeVO(postalCode);

        LatitudeVO latitudeVO = new LatitudeVO(latitude);
        LongitudeVO longitudeVO = new LongitudeVO(longitude);

        AddressVO addressVO = new AddressVO(doorVO, streetVO, cityVO, countryVO, postalCodeVO);
        GpsVO gspVO = new GpsVO(latitudeVO, longitudeVO);
        LocationVO locationVO = new LocationVO(addressVO, gspVO);
        houseServiceImpl.addHouse(locationVO);

        AddRoomCTRL addRoomCTRL = new AddRoomCTRL(roomServiceImpl);

        // Act
        boolean result = addRoomCTRL.addRoom(roomDTO);

        // Assert
        assertFalse(result);
    }

    /**
     * Test method to verify that when the addRoom method is called with a RoomDTO,
     * it returns false if there is no house present with the provided house ID.
     * This ensures that a room cannot be added to a non-existent house.
     */
    @Test
    void whenAddRoomIsCalled_ReturnsFalseIfNoHousePresent()  {
        // Arrange
        String name = "name";
        int floor = 2;
        double roomHeight = 2.2;
        double roomLength = 4.5;
        double roomWidth = 5.0;
        RoomDTO roomDTO = new RoomDTO(null, name,floor,roomHeight, roomLength, roomWidth, null);

        RoomRepositoryMem roomRepositoryMem = new RoomRepositoryMem();
        HouseRepositoryMem houseRepository = new HouseRepositoryMem();
        RoomFactoryImpl roomFactoryImpl = new RoomFactoryImpl();
        RoomServiceImpl roomServiceImpl = new RoomServiceImpl(houseRepository, roomRepositoryMem, roomFactoryImpl);

        AddRoomCTRL addRoomCTRL = new AddRoomCTRL(roomServiceImpl);

        // Act
        boolean result = addRoomCTRL.addRoom(roomDTO);

        // Assert
        assertFalse(result);
    }
}
