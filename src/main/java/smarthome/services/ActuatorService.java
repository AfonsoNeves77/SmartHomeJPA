package smarthome.services;

import smarthome.vo.actuatorvo.Settings;
import smarthome.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.vo.actuatorvo.ActuatorNameVO;
import smarthome.vo.devicevo.DeviceIDVO;

public interface ActuatorService {
    boolean addActuator(ActuatorNameVO actuatorNameVO, ActuatorTypeIDVO actuatorTypeIDVO, DeviceIDVO deviceIDVO, Settings settings);
}
