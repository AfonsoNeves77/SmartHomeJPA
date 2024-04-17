package smarthome.mapper;


import smarthome.dto.SensorDTO;
import smarthome.vo.sensorvo.SensorNameVO;

public class SensorMapper {

    /*
    Since there is no current need to externalize any sensor data, no conversion from domain objects to DTO is needed.
    The only data management required takes place when there is an attempt to add a new sensor to the system. The information
    arrives in a sensorDTO format, containing the intended sensor name. The mapper uses the received DTO to extract its
    data and create a proper SensorName Value Object. No other attributes and methods were added for the moment.
     */

    /**
     * Creates a SensorName Value Object given a SensorDTO.
     * @param sensorDTO sensorDTO containing a sensor name in String format
     * @return a SensorName Value Object
     * @throws IllegalArgumentException If the received DTO is null.
     */
    public static SensorNameVO createSensorNameVO(SensorDTO sensorDTO){
        if(sensorDTO == null)
            throw new IllegalArgumentException("Invalid DTO, Value Object cannot be created");

        String sensorName = sensorDTO.getSensorName();
        return new SensorNameVO(sensorName);
    }

}
