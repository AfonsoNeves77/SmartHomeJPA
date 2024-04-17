package smarthome.services;

import smarthome.domain.sensortype.SensorType;
import smarthome.vo.sensortype.SensorTypeIDVO;

import java.util.List;

public interface SensorTypeService {
    List<SensorType> getListOfSensorTypes();
    boolean sensorTypeExists (SensorTypeIDVO sensorTypeID);
}
