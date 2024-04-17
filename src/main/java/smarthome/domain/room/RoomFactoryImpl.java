package smarthome.domain.room;

import smarthome.vo.housevo.HouseIDVO;
import smarthome.vo.roomvo.RoomDimensionsVO;
import smarthome.vo.roomvo.RoomFloorVO;
import smarthome.vo.roomvo.RoomIDVO;
import smarthome.vo.roomvo.RoomNameVO;

public class RoomFactoryImpl implements RoomFactory{

    /**
     * Creates a Room with the specified parameters, that are all Value-Objects.
     * @param roomName The name of the room.
     * @param floor The floor on which the room is located.
     * @param roomDimensions The dimensions of the room.
     * @param houseID The ID of the house to which the room belongs.
     * @return  The Room created.
     */

    public Room createRoom(RoomNameVO roomName, RoomFloorVO floor, RoomDimensionsVO roomDimensions, HouseIDVO houseID){
        return new Room(roomName, floor, roomDimensions, houseID);
    }

    @Override
    public Room createRoom(RoomIDVO roomIDVO, RoomNameVO roomName, RoomFloorVO floor, RoomDimensionsVO roomDimensions, HouseIDVO houseID) {
        return new Room(roomIDVO,roomName, floor, roomDimensions, houseID);
    }
}
