package smarthome.domain.actuator;

import smarthome.vo.actuatorvo.Settings;
import smarthome.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.vo.actuatorvo.ActuatorNameVO;
import smarthome.vo.devicevo.DeviceIDVO;

public interface ActuatorFactory {
    Actuator createActuator(ActuatorNameVO actuatorName, ActuatorTypeIDVO actuatorTypeID, DeviceIDVO deviceID, Settings settings);
}
