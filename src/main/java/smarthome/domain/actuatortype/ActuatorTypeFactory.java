package smarthome.domain.actuatortype;

import smarthome.vo.actuatortype.ActuatorTypeIDVO;

public interface ActuatorTypeFactory {
    ActuatorType createActuatorType(ActuatorTypeIDVO actuatorTypeIDVO);
}
