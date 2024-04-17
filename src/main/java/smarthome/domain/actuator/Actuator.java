package smarthome.domain.actuator;

import smarthome.domain.DomainEntity;
import smarthome.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.vo.actuatorvo.ActuatorNameVO;
import smarthome.vo.devicevo.DeviceIDVO;

public interface Actuator extends DomainEntity {
    ActuatorTypeIDVO getActuatorTypeID();
    DeviceIDVO getDeviceID();
    ActuatorNameVO getActuatorName();
}
