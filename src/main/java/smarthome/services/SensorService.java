package smarthome.services;

import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.sensortype.SensorTypeIDVO;
import smarthome.vo.sensorvo.SensorNameVO;

public interface SensorService {
    boolean addSensor(SensorNameVO sensorName, DeviceIDVO deviceIDVO, SensorTypeIDVO sensorTypeIDVO);
}
