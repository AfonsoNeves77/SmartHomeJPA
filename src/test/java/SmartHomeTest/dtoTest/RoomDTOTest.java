package SmartHomeTest.dtoTest;

import smarthome.dto.RoomDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomDTOTest {

    private RoomDTO roomDTO;

    /**
     * Set up to be made before each test.
     * A RoomDTO is created with the available constructor. The object has all the parameters of domain Room
     * objects.
     * All tests are run considering the RoomDTO object, and asserts to their attributes versus the expected result
     * are made (one attribute per test).
     */
    @BeforeEach
    public void setUp(){
        String roomID = "1";
        String roomName = "Living Room";
        int floor = 1;
        double roomHeight = 3.0;
        double roomLength = 5.0;
        double roomWidth = 4.0;
        String houseID = "1";
        this.roomDTO = new RoomDTO(roomID, roomName, floor, roomHeight, roomLength, roomWidth, houseID);
    }

    /**
     * Verifies that a room returns its expected roomID.
     */
    @Test
    void createRoomDTO_WhenGetRoomID_ThenShouldReturnRoomIDAsString(){
        //Arrange
        String expectedRoomID = "1";

        //Act
        String resultRoomID = roomDTO.getId();

        //Assert
        assertEquals(expectedRoomID, resultRoomID);
    }

    /**
     * Verifies that a room returns its expected roomName.
     */
    @Test
    void createRoomDTO_WhenGetRoomName_ThenShouldReturnRoomName(){
        //Arrange
        String expectedRoomName = "Living Room";

        //Act
        String resultRoomName = roomDTO.getRoomName();

        //Assert
        assertEquals(expectedRoomName, resultRoomName);
    }

    /**
     * Verifies that a room returns its expected floor.
     */
    @Test
    void createRoomDTO_WhenGetFloor_ThenShouldReturnFloorAsInt(){
        //Arrange
        int expectedFloor = 1;

        //Act
        int resultFloor = roomDTO.getFloor();

        //Assert
        assertEquals(expectedFloor, resultFloor);
    }

    /**
     * Verifies that a room returns its expected roomHeight.
     */
    @Test
    void createRoomDTO_WhenGetRoomHeight_ThenShouldReturnRoomHeightAsDouble(){
        //Arrange
        double expectedRoomHeight = 3.0;

        //Act
        double resultRoomHeight = roomDTO.getRoomHeight();

        //Assert
        assertEquals(expectedRoomHeight, resultRoomHeight);
    }

    /**
     * Verifies that a room returns its expected roomLength.
     */
    @Test
    void createRoomDTO_WhenGetRoomLength_ThenShouldReturnRoomLengthAsDouble(){
        //Arrange
        double expectedRoomLength = 5.0;

        //Act
        double resultRoomLength = roomDTO.getRoomLength();

        //Assert
        assertEquals(expectedRoomLength, resultRoomLength);
    }

    /**
     * Verifies that a room returns its expected roomWidth.
     */
    @Test
    void createRoomDTO_WhenGetRoomWidth_ThenShouldReturnRoomWidthAsDouble(){
        //Arrange
        double expectedRoomWidth = 4.0;

        //Act
        double resultRoomWidth = roomDTO.getRoomWidth();

        //Assert
        assertEquals(expectedRoomWidth, resultRoomWidth);
    }

    /**
     * Verifies that a room returns its expected houseID.
     */
    @Test
    void createRoomDTO_WhenGetHouseID_ThenShouldReturnHouseIDAsString(){
        //Arrange
        String expectedHouseID = "1";

        //Act
        String resultHouseID = roomDTO.getHouseID();

        //Assert
        assertEquals(expectedHouseID, resultHouseID);
    }
}