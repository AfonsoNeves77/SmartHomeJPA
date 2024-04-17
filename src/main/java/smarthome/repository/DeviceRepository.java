package smarthome.repository;

import smarthome.domain.device.Device;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.roomvo.RoomIDVO;

import java.util.List;

public interface DeviceRepository extends Repository<DeviceIDVO, Device>{
    List<Device> findByRoomID(RoomIDVO roomID);
}
