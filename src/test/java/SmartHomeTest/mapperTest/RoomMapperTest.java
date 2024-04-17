package SmartHomeTest.mapperTest;

import smarthome.domain.room.Room;
import smarthome.dto.RoomDTO;
import smarthome.mapper.RoomMapper;
import smarthome.vo.housevo.HouseIDVO;
import smarthome.vo.roomvo.RoomDimensionsVO;
import smarthome.vo.roomvo.RoomFloorVO;
import smarthome.vo.roomvo.RoomIDVO;
import smarthome.vo.roomvo.RoomNameVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RoomMapperTest {

    private RoomDTO roomDTO;
    private String roomID;
    private String roomName;
    private int floor;
    private double roomHeight, roomLength, roomWidth;
    private String houseID;


    /**
     * Common setup for the following tests.
     * Creates a RoomDTO object with the same values for each test.
     */
    @BeforeEach
    void setUp() {
        // Common setup for multiple tests
        roomID = "123e4567-e89b-12d3-a456-426655440000";
        roomName = "BedRoom";
        floor = 2;
        roomHeight = 2.5;
        roomLength = 5.6;
        roomWidth = 3.2;
        houseID = "00000000-0000-0000-0000-000000000000";
        roomDTO = new RoomDTO(roomID, roomName, floor, roomHeight, roomLength, roomWidth, houseID);
    }


    /**
     * Test to verify that the RoomNameVO object is created correctly.
     */
    @Test
    void whenCreateRoomNameVOisCalled_RoomNameVOisRetrieved() throws InstantiationException {
        // Arrange
        String expected = "BedRoom";

        // Act
        RoomNameVO result = RoomMapper.createRoomNameVO(roomDTO);

        // Assert
        assertEquals(expected, result.getValue());
    }


    /**
     * Test to verify that when the RoomDTO object is null, an InstantiationException is
     * thrown when creating a RoomNameVO object.
     */
    @Test
    void whenCreateRoomNameVOisCalledWithNullEntry_ThrowsInstantiationException() {
        // Arrange
        String expectedMessage = "RoomDTO is invalid";

        // Act
        Exception e = assertThrows(InstantiationException.class, () -> RoomMapper.createRoomNameVO(null));

        // Assert
        assertEquals(expectedMessage, e.getMessage());
    }

    /**
     * Test to verify that the RoomFloorVO object is created correctly.
     */
    @Test
    void whenCreateRoomFloorVOisCalled_RoomFloorVOisRetrieved() throws InstantiationException {
        // Arrange
        int expected = 2;

        // Act
        RoomFloorVO result = RoomMapper.createRoomFloorVO(roomDTO);

        // Assert
        assertEquals(expected, result.getValue());
    }

    /**
     * Test to verify that when the RoomDTO object is null, an InstantiationException is thrown
     * when creating a RoomFloorVO object.
     */
    @Test
    void whenCreateRoomFloorVOisCalledWithNullEntry_ThrowsInstantiationException() {
        // Arrange
        String expectedMessage = "RoomDTO is invalid";

        // Act
        Exception e = assertThrows(InstantiationException.class, () -> RoomMapper.createRoomFloorVO(null));

        // Assert
        assertEquals(expectedMessage, e.getMessage());
    }


    /**
     * Test to verify that the RoomDimensionsVO object is created correctly.
     */
    @Test
    void whenCreateRoomDimensionsVOisCalled_RoomDimensionsVOisRetrieved() throws InstantiationException {
        // Arrange
        double expectedLength = 5.6;
        double expectedWidth = 3.2;
        double expectedHeight = 2.5;

        // Act
        RoomDimensionsVO result = RoomMapper.createRoomDimensionsVO(roomDTO);

        // Assert
        assertEquals(expectedLength, result.getRoomLength());
        assertEquals(expectedWidth, result.getRoomWidth());
        assertEquals(expectedHeight, result.getRoomHeight());
    }

    /**
     * Test to verify that when the RoomDTO object is null, an InstantiationException is thrown
     * when creating a RoomDimensionsVO object.
     */
    @Test
    void whenCreateRoomDimensionsVOisCalledWithNullEntry_ThrowsInstantiationException() {
        // Arrange
        String expectedMessage = "RoomDTO is invalid";

        // Act
        Exception e = assertThrows(InstantiationException.class, () -> RoomMapper.createRoomDimensionsVO(null));

        // Assert
        assertEquals(expectedMessage, e.getMessage());
    }


    /**
     * Test to verify that when the RoomDTO object has invalid values, an InstantiationException is
     * thrown when creating a RoomDimensionsVO object.
     */
    @Test
    void whenCreateRoomDimensionsVOisCalledWithInvalidDTOValues_ThrowsInstantiationException() {
        // Arrange
        String roomID1 = "123e4567-e89b-12d3-a456-426655440000";
        String roomName1 = "BedRoom";
        int floor1 = 2;
        double roomHeight1 = -1;
        double roomLength1 = 0;
        double roomWidth1 = 0;
        String houseID1 = "00000000-0000-0000-0000-000000000000";
        RoomDTO room1DTO = new RoomDTO(roomID1, roomName1, floor1, roomHeight1, roomLength1, roomWidth1, houseID1);
        String expectedMessage = "RoomDTO is invalid";

        // Act
        Exception e = assertThrows(InstantiationException.class, () -> RoomMapper.createRoomDimensionsVO(room1DTO));

        // Assert
        assertEquals(expectedMessage, e.getMessage());
    }

    /**
     * Test to verify that when the RoomDTO object has invalid values, an InstantiationException is
     * thrown when creating a RoomDimensionsVO object.
     */
    @Test
    void whenCreateRoomDimensionsVOisCalledWithInvalidBorderDTOValues_ThrowsInstantiationException() {
        // Arrange
        String roomID1 = "123e4567-e89b-12d3-a456-426655440000";
        String roomName1 = "BedRoom";
        int floor1 = 2;
        double roomHeight1 = 0;
        double roomLength1 = -1;
        double roomWidth1 = -1;
        String houseID1 = "00000000-0000-0000-0000-000000000000";
        RoomDTO room1DTO = new RoomDTO(roomID1, roomName1, floor1, roomHeight1, roomLength1, roomWidth1, houseID1);
        String expectedMessage = "RoomDTO is invalid";

        // Act
        Exception e = assertThrows(InstantiationException.class, () -> RoomMapper.createRoomDimensionsVO(room1DTO));

        // Assert
        assertEquals(expectedMessage, e.getMessage());
    }

    /**
     * Test to verify that when the RoomDTO object has border values, The object is created
     */
    @Test
    void whenCreateRoomDimensionsVOisCalledWithBorderDTOValues_ThrowsInstantiationException() throws InstantiationException {
        // Arrange
        String roomID1 = "123e4567-e89b-12d3-a456-426655440000";
        String roomName1 = "BedRoom";
        int floor1 = 2;
        double roomHeight1 = 0;
        double roomLength1 = 1;
        double roomWidth1 = 1;
        String houseID1 = "00000000-0000-0000-0000-000000000000";
        RoomDTO room1DTO = new RoomDTO(roomID1, roomName1, floor1, roomHeight1, roomLength1, roomWidth1, houseID1);

        // Act
        RoomDimensionsVO dimensionsVO = RoomMapper.createRoomDimensionsVO(room1DTO);

        // Assert
        assertNotNull(dimensionsVO);
    }




    /**
     * Test to verify that the RoomIDVO object is created correctly.
     */
    @Test
    void whenCreateRoomIDVOisCalled_RoomIDVOisRetrieved() throws InstantiationException {
        // Arrange
        String expected = "123e4567-e89b-12d3-a456-426655440000";

        // Act
        RoomIDVO result = RoomMapper.createRoomIDVO(roomDTO);

        // Assert
        assertEquals(expected, result.getID());
    }



    /**
     * Test to verify that when the RoomDTO object is null, an InstantiationException is thrown
     * when creating a RoomIDVO object.
     */
    @Test
    void whenCreateRoomIDVOisCalledWithNullEntry_ThrowsInstantiationException() {
        // Arrange
        String expectedMessage = "RoomDTO is invalid";

        // Act
        Exception e = assertThrows(InstantiationException.class, () -> RoomMapper.createRoomIDVO(null));

        // Assert
        assertEquals(expectedMessage, e.getMessage());
    }


    /**
     * Test to verify that the HouseIDVO object is created correctly.
     */
    @Test
    void whenCreateHouseIDVOisCalled_HouseIDVOisRetrieved() throws InstantiationException {
        // Arrange
        String expected = "00000000-0000-0000-0000-000000000000";

        // Act
        HouseIDVO result = RoomMapper.createHouseIDVO(roomDTO);

        // Assert
        assertEquals(expected, result.getID());
    }


    /**
     * Test to verify that when the RoomDTO object is null, an InstantiationException is thrown when
     * creating a HouseIDVO object.
     */
    @Test
    void whenCreateHouseIDVOisCalledWithNullEntry_ThrowsInstantiationException() {
        // Arrange
        String expectedMessage = "RoomDTO is invalid";

        // Act
        Exception e = assertThrows(InstantiationException.class, () -> RoomMapper.createHouseIDVO(null));

        // Assert
        assertEquals(expectedMessage, e.getMessage());
    }


    /**
     * Test to verify that a list of Room objects of size 1 is correctly converted
     * to a list of RoomDTO objects with the same size
     */
    @Test
    void whenDomainToDTOisCalled_ThenExpectedListOfRoomsDTOExpectedSizeIsOf1(){
        // Arrange
        RoomNameVO nameDouble = mock(RoomNameVO.class);
        RoomFloorVO floorDouble = mock(RoomFloorVO.class);
        RoomDimensionsVO roomDimensionsDouble = mock(RoomDimensionsVO.class);
        HouseIDVO houseIDDouble = mock(HouseIDVO.class);
        RoomIDVO roomIDDouble = mock(RoomIDVO.class);

        Room roomDouble = mock(Room.class);
        when(roomDouble.getFloor()).thenReturn(floorDouble);
        when(roomDouble.getRoomDimensions()).thenReturn(roomDimensionsDouble);
        when(roomDouble.getRoomName()).thenReturn(nameDouble);
        when(roomDouble.getHouseID()).thenReturn(houseIDDouble);
        when(roomDouble.getId()).thenReturn(roomIDDouble);

        List<Room> listOfRooms = new ArrayList<>();
        listOfRooms.add(roomDouble);
        int expectedSizeOfList = 1;

        //Act
        List<RoomDTO> listOfRoomsDTO = RoomMapper.domainToDTO(listOfRooms);

        //Assert
        assertEquals(expectedSizeOfList, listOfRoomsDTO.size());
    }


    /**
     * Test to verify that a list of Room objects of size 2 is correctly converted
     * to a list of RoomDTO objects with the same size
     */
    @Test
    void whenDomainToDTOisCalled_ThenExpectedListOfRoomsDTOExpectedSizeIsOf2(){
        // Arrange

            // Mock dependencies inside Room.
        RoomNameVO nameDouble = mock(RoomNameVO.class);
        RoomFloorVO floorDouble = mock(RoomFloorVO.class);
        RoomDimensionsVO roomDimensionsDouble = mock(RoomDimensionsVO.class);
        HouseIDVO houseIDDouble = mock(HouseIDVO.class);
        RoomIDVO roomIDDouble = mock(RoomIDVO.class);
        RoomNameVO nameDouble2 = mock(RoomNameVO.class);
        RoomFloorVO floorDouble2 = mock(RoomFloorVO.class);
        RoomDimensionsVO roomDimensionsDouble2 = mock(RoomDimensionsVO.class);
        HouseIDVO houseIDDouble2 = mock(HouseIDVO.class);
        RoomIDVO roomIDDouble2 = mock(RoomIDVO.class);

            // Mock One Room and stub to return mocked VOs.
        Room roomDouble = mock(Room.class);
        when(roomDouble.getFloor()).thenReturn(floorDouble);
        when(roomDouble.getRoomDimensions()).thenReturn(roomDimensionsDouble);
        when(roomDouble.getRoomName()).thenReturn(nameDouble);
        when(roomDouble.getHouseID()).thenReturn(houseIDDouble);
        when(roomDouble.getId()).thenReturn(roomIDDouble);

            // Mock Another Room and stub to return mocked VOs.
        Room roomDouble2 = mock(Room.class);
        when(roomDouble2.getFloor()).thenReturn(floorDouble2);
        when(roomDouble2.getRoomDimensions()).thenReturn(roomDimensionsDouble2);
        when(roomDouble2.getRoomName()).thenReturn(nameDouble2);
        when(roomDouble2.getHouseID()).thenReturn(houseIDDouble2);
        when(roomDouble2.getId()).thenReturn(roomIDDouble2);

            // Create a list of Rooms and add the mocked Rooms.
        List<Room> listOfRooms = new ArrayList<>();
        listOfRooms.add(roomDouble);
        listOfRooms.add(roomDouble2);
        int expectedSizeOfList = 2;

        //Act
        List<RoomDTO> listOfRoomsDTO = RoomMapper.domainToDTO(listOfRooms);

        //Assert
        assertEquals(expectedSizeOfList, listOfRoomsDTO.size());
    }


    /**
     * Test to verify that the values from the RoomDTO object match the values from the Room object.
     * First, mock it mocks the dependencies inside Room and stub the methods to return specific values.
     * Then, mocks the Room object and stubs to return the mocked VO's.
     * After that, calls the domainToDTO method and checks if the values match between the
     * first RoomDTO object of the output's list and the only Room object given.
     */
    @Test
    void whenDomainToDTOisCalled_thenValuesFromRoomDTOObjectMatchValuesFromRoomObject() {

        // Arrange

            // Mock dependencies inside Room
        RoomFloorVO mockRoomFloorVO = mock(RoomFloorVO.class);
        RoomDimensionsVO mockRoomDimensionsVO = mock(RoomDimensionsVO.class);
        RoomNameVO mockRoomNameVO = mock(RoomNameVO.class);
        RoomIDVO mockRoomIDVO = mock(RoomIDVO.class);
        HouseIDVO mockHouseIDVO = mock(HouseIDVO.class);

            // Arrange stubbing for the methods to return specific values
        when(mockRoomFloorVO.getValue()).thenReturn(2);
        when(mockRoomDimensionsVO.getRoomLength()).thenReturn(5.6);
        when(mockRoomDimensionsVO.getRoomWidth()).thenReturn(3.2);
        when(mockRoomDimensionsVO.getRoomHeight()).thenReturn(2.5);
        when(mockRoomNameVO.getValue()).thenReturn("BedRoom");
        when(mockRoomIDVO.getID()).thenReturn("123e4567-e89b-12d3-a456-426655440000");
        when(mockHouseIDVO.getID()).thenReturn("00000000-0000-0000-0000-000000000000");

            //  Mock Room object and stub to return mocked VO's.
        Room roomDouble = mock(Room.class);
        when(roomDouble.getFloor()).thenReturn(mockRoomFloorVO);
        when(roomDouble.getRoomDimensions()).thenReturn(mockRoomDimensionsVO);
        when(roomDouble.getRoomName()).thenReturn(mockRoomNameVO);
        when(roomDouble.getId()).thenReturn(mockRoomIDVO);
        when(roomDouble.getHouseID()).thenReturn(mockHouseIDVO);

            // Expected values are in BeforeEach Setup

        // Act
        List<RoomDTO> result = RoomMapper.domainToDTO(List.of(roomDouble));

        // Assert
        assertEquals(roomName, result.get(0).getRoomName());
        assertEquals(floor, result.get(0).getFloor());
        assertEquals(roomLength, result.get(0).getRoomLength());
        assertEquals(roomWidth, result.get(0).getRoomWidth());
        assertEquals(roomHeight, result.get(0).getRoomHeight());
        assertEquals(roomID, result.get(0).getId());
        assertEquals(houseID, result.get(0).getHouseID());
    }
}
