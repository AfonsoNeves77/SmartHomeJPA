package smarthome.domain.sensortype;

import smarthome.vo.sensortype.UnitVO;
import smarthome.vo.sensortype.SensorTypeIDVO;

public class SensorTypeFactoryImpl implements SensorTypeFactory {

    /**
     * Creates a SensorType with the specified parameters, that are all Value-Objects.
     * @param sensorTypeIDVO The ID of the sensor type.
     * @param unitVO The unit of the sensor type.
     * @return  The SensorType created.
     */
    public SensorType createSensorType(SensorTypeIDVO sensorTypeIDVO, UnitVO unitVO){
        try{
            return new SensorType(sensorTypeIDVO, unitVO);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
