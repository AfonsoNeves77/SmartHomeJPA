package SmartHomeTest.domainTest.roomTest;

import smarthome.domain.room.Room;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.vo.housevo.HouseIDVO;
import smarthome.vo.roomvo.RoomDimensionsVO;
import smarthome.vo.roomvo.RoomFloorVO;
import smarthome.vo.roomvo.RoomNameVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RoomFactoryImplTest {

    /**
     * Tests that when the createRoom method in RoomFactory is called, then an object should be created.
     */
    @Test
    void whenConstructorInvoked_ThenMockObjectShouldBeCreated()  {
        //arrange
        RoomNameVO nameDouble = mock(RoomNameVO.class);
        RoomFloorVO floorDouble = mock(RoomFloorVO.class);
        RoomDimensionsVO dimensionsDouble = mock(RoomDimensionsVO.class);
        HouseIDVO houseIdDouble = mock(HouseIDVO.class);

        try(MockedConstruction<Room> roomDouble = mockConstruction(Room.class,(mock, context)->
            when(mock.getRoomName()).thenReturn(nameDouble))) {

            RoomFactoryImpl factoryRoom = new RoomFactoryImpl();

            // act
            Room room = factoryRoom.createRoom(nameDouble, floorDouble, dimensionsDouble, houseIdDouble);

            // assert
            List<Room> rooms = roomDouble.constructed();

            assertEquals(1, rooms.size());
            assertEquals(nameDouble, room.getRoomName());
        }
    }

       /*
    SYSTEM UNDER TEST: FACTORY + Room
    A double of all the other collaborators is done (essentially the required value objects to create the room).
     */

    /**
     * Tests that when the createRoom method in RoomFactory is called with invalid (null)
     * parameters, then an IllegalArgumentException should be propagated.
     */
    @Test
    void createRoom_WhenInvalidParameters_ShouldPropagateIllegalArgumentException()  {
        //Arrange
        String expected = "Invalid Parameters";
        RoomFloorVO floorDouble = mock(RoomFloorVO.class);
        RoomDimensionsVO dimensionsDouble = mock(RoomDimensionsVO.class);
        HouseIDVO houseIdDouble = mock(HouseIDVO.class);
        RoomFactoryImpl roomFactoryImpl = new RoomFactoryImpl();

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> roomFactoryImpl.createRoom(null,floorDouble,dimensionsDouble,houseIdDouble));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Tests that when the createRoom method in RoomFactory is called with valid
     * parameters, then a Room with the passed value objects should be returned.
     */
    @Test
    void createRoom_WhenValidParameters_ShouldReturnCorrectRoom()  {
        //Arrange
        RoomNameVO expectedName = mock(RoomNameVO.class);
        RoomFloorVO expectedFloor = mock(RoomFloorVO.class);
        RoomDimensionsVO expectedDimensions = mock(RoomDimensionsVO.class);
        HouseIDVO expectedHouseId = mock(HouseIDVO.class);
        RoomFactoryImpl roomFactoryImpl = new RoomFactoryImpl();

        //Act
        Room room = roomFactoryImpl.createRoom(expectedName,expectedFloor,expectedDimensions,expectedHouseId);
        RoomNameVO resultName = room.getRoomName();
        RoomFloorVO resultFloor = room.getFloor();
        RoomDimensionsVO resultDimensions = room.getRoomDimensions();
        HouseIDVO resultHouseID = room.getHouseID();
        //Assert
        assertEquals(expectedName,resultName);
        assertEquals(expectedFloor,resultFloor);
        assertEquals(expectedDimensions,resultDimensions);
        assertEquals(expectedHouseId,resultHouseID);
    }

}
