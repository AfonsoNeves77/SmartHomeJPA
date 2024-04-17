package smarthome.services;

import smarthome.domain.actuatortype.ActuatorType;
import smarthome.vo.actuatortype.ActuatorTypeIDVO;

import java.util.List;

public interface ActuatorTypeService {
    List<ActuatorType> getListOfActuatorTypes();
    boolean actuatorTypeExists (ActuatorTypeIDVO actuatorTypeIDVO);
}
