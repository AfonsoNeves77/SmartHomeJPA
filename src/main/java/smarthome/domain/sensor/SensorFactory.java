package smarthome.domain.sensor;

import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.sensortype.SensorTypeIDVO;
import smarthome.vo.sensorvo.SensorNameVO;

public interface SensorFactory {
    Sensor createSensor (SensorNameVO sensorName, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID);
}
