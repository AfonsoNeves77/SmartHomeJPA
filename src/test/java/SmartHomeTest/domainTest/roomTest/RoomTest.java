package SmartHomeTest.domainTest.roomTest;

import smarthome.domain.DomainID;
import smarthome.domain.room.Room;
import smarthome.vo.housevo.HouseIDVO;
import smarthome.vo.roomvo.RoomIDVO;
import smarthome.vo.roomvo.RoomDimensionsVO;
import smarthome.vo.roomvo.RoomFloorVO;
import smarthome.vo.roomvo.RoomNameVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomTest {

    /**
     * This test verifies that the correct room name is returned when getRoomName() is invoked
     * in the Room class.
     * It isolates all of Room's collaborators and compares the object returned by the previously referred method
     * with roomNameDouble.
     * These tests also have an additional assertion that verifies if (as expected) one mocked Construction of RoomIDVO type
     * was created. This gives certainty that all collaborators of Room class are isolated;
     */

    @Test
    void getRoomName_ShouldReturnCorrectRoomName(){

        //Arrange
        int expectedMockedConstructions = 1;

        RoomNameVO expected = mock(RoomNameVO.class);
        RoomFloorVO floorDouble = mock(RoomFloorVO.class);
        RoomDimensionsVO roomDimensionsDouble = mock(RoomDimensionsVO.class);
        HouseIDVO houseIDDouble = mock(HouseIDVO.class);

        try(MockedConstruction<RoomIDVO> mockedConstruction = mockConstruction(RoomIDVO.class)) {

            //Act
            Room room = new Room(expected,floorDouble,roomDimensionsDouble,houseIDDouble);
            RoomNameVO result = room.getRoomName();

            //Assert
            assertEquals(expected, result);
            int resultMockedConstructions = mockedConstruction.constructed().size();
            assertEquals(expectedMockedConstructions, resultMockedConstructions);
        }
    }

    @Test
    void getRoomFloor_ShouldReturnCorrectRoomFloor(){

        //Arrange
        int expectedMockedConstructions = 1;

        RoomNameVO roomNameDouble = mock(RoomNameVO.class);
        RoomFloorVO expected = mock(RoomFloorVO.class);
        RoomDimensionsVO roomDimensionsDouble = mock(RoomDimensionsVO.class);
        HouseIDVO houseIDDouble = mock(HouseIDVO.class);

        try(MockedConstruction<RoomIDVO> mockedConstruction = mockConstruction(RoomIDVO.class)) {

            //Act
            Room room = new Room(roomNameDouble, expected,roomDimensionsDouble,houseIDDouble);
            RoomFloorVO result = room.getFloor();

            //Assert
            assertEquals(expected, result);
            int resultMockedConstructions = mockedConstruction.constructed().size();
            assertEquals(expectedMockedConstructions, resultMockedConstructions);
        }
    }

    @Test
    void getRoomDimensions_ShouldReturnCorrectRoomDimensions(){

        //Arrange
        int expectedMockedConstructions = 1;

        RoomNameVO roomNameDouble = mock(RoomNameVO.class);
        RoomFloorVO floorDouble = mock(RoomFloorVO.class);
        RoomDimensionsVO expected = mock(RoomDimensionsVO.class);
        HouseIDVO houseIDDouble = mock(HouseIDVO.class);

        try(MockedConstruction<RoomIDVO> mockedConstruction = mockConstruction(RoomIDVO.class)) {

            //Act
            Room room = new Room(roomNameDouble, floorDouble, expected,houseIDDouble);
            RoomDimensionsVO result = room.getRoomDimensions();

            //Assert
            assertEquals(expected, result);
            int resultMockedConstructions = mockedConstruction.constructed().size();
            assertEquals(expectedMockedConstructions, resultMockedConstructions);
        }

    }

    @Test
    void getHouseID_ShouldReturnCorrectHouseID(){

        //Arrange
        int expectedMockedConstructions = 1;

        RoomNameVO roomNameDouble = mock(RoomNameVO.class);
        RoomFloorVO floorDouble = mock(RoomFloorVO.class);
        RoomDimensionsVO roomDimensionsDouble = mock(RoomDimensionsVO.class);
        HouseIDVO expected = mock(HouseIDVO.class);

        try(MockedConstruction<RoomIDVO> mockedConstruction = mockConstruction(RoomIDVO.class)) {

            //Act
            Room room = new Room(roomNameDouble, floorDouble, roomDimensionsDouble, expected);
            HouseIDVO result = room.getHouseID();

            //Assert
            assertEquals(expected, result);
            int resultMockedConstructions = mockedConstruction.constructed().size();
            assertEquals(expectedMockedConstructions, resultMockedConstructions);
        }
    }

    /**
     * This test verifies that the correct room ID is returned when getId() is invoked
     * in the Room class.
     * It isolates all of Room's collaborators and compares the object returned by the previously referred method
     * with the doubled roomID.
     * As in previous tests, it´s being used Mocked Construction to intercept RoomID instantiation during Room's instantiation.
     * By this interception the "real" RoomID object is being substituted with a double.
     * The assertion is made by extracting the doubled object from MockedConstruction.constructed() list and compare it with the object
     * returned in the getID() operation.
     * This assertion gives us certainty that the intercepted object is the object being return (as it should)
     *
     */

    @Test
    void getRoomID_ShouldReturnCorrectRoomID(){

        //Arrange
        int expectedMockedConstructions = 1;

        RoomNameVO roomNameDouble = mock(RoomNameVO.class);
        RoomFloorVO floorDouble = mock(RoomFloorVO.class);
        RoomDimensionsVO roomDimensionsDouble = mock(RoomDimensionsVO.class);
        HouseIDVO houseIDDouble = mock(HouseIDVO.class);

        try(MockedConstruction<RoomIDVO> mockedConstruction = mockConstruction(RoomIDVO.class)) {

            //Act
            Room room = new Room(roomNameDouble, floorDouble, roomDimensionsDouble, houseIDDouble);
            DomainID result = room.getId();
            RoomIDVO expected = mockedConstruction.constructed().get(0);

            //Assert
            assertEquals(expected, result);
            int resultMockedConstructions = mockedConstruction.constructed().size();
            assertEquals(expectedMockedConstructions, resultMockedConstructions);
        }

    }

    /**
     * The following tests verify that if any of the parameters are null, room instantiation should throw an Illegal Argument Exception
     * @param roomName The name of the room.
     * @param floor The floor on which the room is located.
     * @param dimensions The dimensions of the room.
     *@param houseID The ID of the house to which the room belongs.
     *
     */

    private void assertThrowsWithNullParameter(RoomNameVO roomName, RoomFloorVO floor, RoomDimensionsVO dimensions, HouseIDVO houseID) {
        //Arrange
        String expected = "Invalid Parameters";

        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Room(roomName, floor, dimensions, houseID));
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    @Test
    void whenNullRoomName_thenThrowsIllegalArgumentException() {

        RoomFloorVO floorDouble = mock(RoomFloorVO.class);
        RoomDimensionsVO roomDimensionsDouble = mock(RoomDimensionsVO.class);
        HouseIDVO houseIDDouble = mock(HouseIDVO.class);
        //Assert
        assertThrowsWithNullParameter(null, floorDouble,roomDimensionsDouble, houseIDDouble);
    }

    @Test
    void whenNullFloor_thenThrowsIllegalArgumentException() {
        RoomNameVO roomNameDouble = mock(RoomNameVO.class);
        RoomDimensionsVO roomDimensionsDouble = mock(RoomDimensionsVO.class);
        HouseIDVO houseIDDouble = mock(HouseIDVO.class);
        //Assert
        assertThrowsWithNullParameter(roomNameDouble, null,roomDimensionsDouble, houseIDDouble);

    }

    @Test
    void whenNullRoomDimensions_thenThrowsIllegalArgumentException() {
        RoomNameVO roomNameDouble = mock(RoomNameVO.class);
        RoomFloorVO floorDouble = mock(RoomFloorVO.class);
        HouseIDVO houseIDDouble = mock(HouseIDVO.class);
        //Assert
        assertThrowsWithNullParameter(roomNameDouble, floorDouble,null, houseIDDouble);
    }

    @Test
    void whenNullHouseID_thenThrowsIllegalArgumentException() {
        RoomNameVO roomNameDouble = mock(RoomNameVO.class);
        RoomFloorVO floorDouble = mock(RoomFloorVO.class);
        RoomDimensionsVO roomDimensionsDouble = mock(RoomDimensionsVO.class);
        //Assert
        assertThrowsWithNullParameter(roomNameDouble, floorDouble,roomDimensionsDouble, null);
    }





}