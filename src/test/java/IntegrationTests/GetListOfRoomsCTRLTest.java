package IntegrationTests;

import smarthome.controller.GetListOfRoomsCTRL;
import smarthome.domain.room.Room;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.dto.RoomDTO;
import smarthome.repository.HouseRepositoryMem;
import smarthome.repository.RoomRepositoryMem;
import smarthome.services.RoomServiceImpl;
import smarthome.vo.housevo.HouseIDVO;
import smarthome.vo.roomvo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GetListOfRoomsCTRLTest {
    RoomFactoryImpl roomFactory;
    RoomServiceImpl roomService;
    GetListOfRoomsCTRL controller;
    RoomRepositoryMem roomRepositoryMem;

    /**
     * Set up method for initializing necessary objects before each test.
     * Initializes a Memory House Repository, a V1 House Factory, and a V1 House Service
     * for testing purposes. Also initializes a Memory Room Repository, a V1 Room Factory,
     * and a V1 Room Service with the previously created House Service. Finally, creates
     * a GetListOfRoomsCTRL controller for testing the Get List of Rooms functionality.
     */
    @BeforeEach
    void setUp() {
        HouseRepositoryMem houseRepository = new HouseRepositoryMem();
        this.roomRepositoryMem = new RoomRepositoryMem();
        this.roomFactory = new RoomFactoryImpl();
        this.roomService = new RoomServiceImpl(houseRepository, roomRepositoryMem,roomFactory);
        this.controller = new GetListOfRoomsCTRL(roomService);
    }

    /**
     * Test case to verify that creating a GetListOfRoomsCTRL with a null RoomService
     * throws an IllegalArgumentException with the expected error message.
     */
    @Test
    void whenRoomServiceIsNull_thenThrowException() {
        //Arrange
        String expected = "RoomService cannot be null.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GetListOfRoomsCTRL(null));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test case to verify that when a house has no rooms calling getListOfRooms() on the GetListOfRoomsCTRL controller
     * returns an empty list of RoomDTO objects.
     */
    @Test
    void whenHouseHasNoRooms_thenReturnEmptyList() {
        // Arrange
        int expected = 0;

        //Act
        List<RoomDTO> list = controller.getListOfRooms();
        int result = list.size();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test case to verify that when a house has one room, calling getListOfRooms()
     * on the GetListOfRoomsCTRL controller returns a list containing that room's details.
     * The test sets up a house with a single room of specific dimensions and properties,
     * saves it to a memory repository, then retrieves the list of rooms from the controller.
     * It then checks that the returned list contains the expected room details such as name,
     * floor, dimensions, and house ID.
     */
    @Test
    void whenHouseHasOneRoom_thenReturnListWithOneRoom() {
        // Arrange
        double expectedWidth = 3.5;
        RoomWidthVO roomWidth = new RoomWidthVO(expectedWidth);

        double expectedLength = 3;
        RoomLengthVO roomLength = new RoomLengthVO(expectedLength);

        double expectedHeight = 2;
        RoomHeightVO roomHeight = new RoomHeightVO(expectedHeight);

        RoomDimensionsVO roomDimensions = new RoomDimensionsVO(roomLength, roomWidth, roomHeight);

        String expectedName = "Office";
        RoomNameVO roomName = new RoomNameVO(expectedName);

        int expectedFloor = 2;
        RoomFloorVO roomFloor = new RoomFloorVO(expectedFloor);

        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());
        String expectedHouseID = houseID.getID();

        Room room = roomFactory.createRoom(roomName, roomFloor, roomDimensions, houseID);
        roomRepositoryMem.save(room);

        List<RoomDTO> list = controller.getListOfRooms();

        // Act
        String resultName = list.get(0).getRoomName();
        String resultHouseID = list.get(0).getHouseID();
        int resultFloor = list.get(0).getFloor();
        double resultWidth = list.get(0).getRoomWidth();
        double resultLength = list.get(0).getRoomLength();
        double resultHeight = list.get(0).getRoomHeight();

        // Assert
        assertEquals(expectedName, resultName);
        assertEquals(expectedFloor, resultFloor);
        assertEquals(expectedHouseID, resultHouseID);
        assertEquals(expectedWidth, resultWidth);
        assertEquals(expectedLength, resultLength);
        assertEquals(expectedHeight, resultHeight);
    }

    /**
     * Test case to verify that when a house has multiple rooms, calling getListOfRooms()
     * on the GetListOfRoomsCTRL controller returns the details of the second room.
     * The test sets up a house with two rooms: a "Living Room" and a "Kitchen" with specific
     * dimensions and properties. These rooms are saved to a memory repository, then the list
     * of rooms is retrieved from the controller. The test then checks that the details of the
     * second room in the list match the expected values such as name, floor, dimensions, and
     * house ID.
     */
    @Test
    void whenHouseHasMultipleRooms_thenReturnNameOfSecondRoom() {
        // Arrange
        RoomNameVO roomName1 = new RoomNameVO("Living Room");
        RoomFloorVO roomFloor1 = new RoomFloorVO(1);

        double expectedWidth2 = 3.5;
        RoomWidthVO roomWidth2 = new RoomWidthVO(expectedWidth2);

        double expectedLength2 = 3;
        RoomLengthVO roomLength2 = new RoomLengthVO(expectedLength2);

        double expectedHeight2 = 2;
        RoomHeightVO roomHeight2 = new RoomHeightVO(expectedHeight2);

        RoomDimensionsVO roomDimensions = new RoomDimensionsVO(roomLength2, roomWidth2, roomHeight2);

        String expectedName2 = "Kitchen";
        RoomNameVO roomName2 = new RoomNameVO(expectedName2);

        int expectedFloor2 = 2;
        RoomFloorVO roomFloor2 = new RoomFloorVO(expectedFloor2);

        HouseIDVO houseID2 = new HouseIDVO(UUID.randomUUID());
        String expectedHouseID2 = houseID2.getID();

        Room room1 = roomFactory.createRoom(roomName1, roomFloor1, roomDimensions, houseID2);
        Room room2 = roomFactory.createRoom(roomName2, roomFloor2, roomDimensions, houseID2);
        roomRepositoryMem.save(room1);
        roomRepositoryMem.save(room2);

        List<RoomDTO> list = controller.getListOfRooms();

        // Act
        String resultName = list.get(1).getRoomName();
        String resultHouseID = list.get(1).getHouseID();
        int resultFloor = list.get(1).getFloor();
        double resultWidth = list.get(1).getRoomWidth();
        double resultLength = list.get(1).getRoomLength();
        double resultHeight = list.get(1).getRoomHeight();

        // Assert
        assertEquals(expectedName2, resultName);
        assertEquals(expectedFloor2, resultFloor);
        assertEquals(expectedHouseID2, resultHouseID);
        assertEquals(expectedWidth2, resultWidth);
        assertEquals(expectedLength2, resultLength);
        assertEquals(expectedHeight2, resultHeight);
    }
}