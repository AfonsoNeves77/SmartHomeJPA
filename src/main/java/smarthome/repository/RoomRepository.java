package smarthome.repository;

import smarthome.domain.room.Room;
import smarthome.vo.roomvo.RoomIDVO;

public interface RoomRepository extends Repository<RoomIDVO, Room>{

   boolean updateRoom(Room room);


}
