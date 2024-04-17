package smarthome.domain.sensortype;

import smarthome.vo.sensortype.UnitVO;
import smarthome.vo.sensortype.SensorTypeIDVO;

public interface SensorTypeFactory {
    SensorType createSensorType(SensorTypeIDVO sensorTypeIDVO, UnitVO unitVO);
}
