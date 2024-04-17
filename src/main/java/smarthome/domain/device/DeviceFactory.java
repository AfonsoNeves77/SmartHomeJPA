package smarthome.domain.device;

import smarthome.vo.devicevo.DeviceModelVO;
import smarthome.vo.devicevo.DeviceNameVO;
import smarthome.vo.roomvo.RoomIDVO;

public interface DeviceFactory {
    Device createDevice(DeviceNameVO deviceName, DeviceModelVO deviceModel, RoomIDVO roomID);
}
