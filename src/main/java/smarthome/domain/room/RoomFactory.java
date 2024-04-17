package smarthome.domain.room;

import smarthome.vo.housevo.HouseIDVO;
import smarthome.vo.roomvo.RoomDimensionsVO;
import smarthome.vo.roomvo.RoomFloorVO;
import smarthome.vo.roomvo.RoomIDVO;
import smarthome.vo.roomvo.RoomNameVO;

public interface RoomFactory {
    Room createRoom(RoomNameVO roomName, RoomFloorVO floor, RoomDimensionsVO roomDimensions, HouseIDVO houseID);

    Room createRoom(RoomIDVO roomIDVO,RoomNameVO roomName, RoomFloorVO floor, RoomDimensionsVO roomDimensions, HouseIDVO houseID);
}
