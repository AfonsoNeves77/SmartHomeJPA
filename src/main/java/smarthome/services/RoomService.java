package smarthome.services;

import smarthome.domain.room.Room;
import smarthome.vo.roomvo.RoomDimensionsVO;
import smarthome.vo.roomvo.RoomFloorVO;
import smarthome.vo.roomvo.RoomNameVO;

import java.util.List;

public interface RoomService {
    List<Room> findAll();
    boolean addRoom(RoomNameVO roomNameVO, RoomFloorVO roomFloorVO, RoomDimensionsVO roomDimensionsVO);
}
