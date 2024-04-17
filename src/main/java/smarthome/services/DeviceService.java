package smarthome.services;

import smarthome.domain.device.Device;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.devicevo.DeviceModelVO;
import smarthome.vo.devicevo.DeviceNameVO;
import smarthome.vo.roomvo.RoomIDVO;

import java.util.List;

public interface DeviceService {
    boolean addDevice(DeviceNameVO deviceNameVO, DeviceModelVO deviceModelVO, RoomIDVO roomIDVO);
    boolean deactivateDevice(DeviceIDVO deviceIDVO);
    List<Device> getListOfDevicesInARoom(RoomIDVO roomIDVO);
}
