package smarthome.domain.sensor;

import smarthome.domain.DomainEntity;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.sensortype.SensorTypeIDVO;
import smarthome.vo.sensorvo.SensorNameVO;

public interface Sensor extends DomainEntity {
    SensorTypeIDVO getSensorTypeID();
    DeviceIDVO getDeviceID();
    SensorNameVO getSensorName();
}
