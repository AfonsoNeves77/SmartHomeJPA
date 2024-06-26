package smarthome.controller;

import smarthome.dto.DeviceDTO;
import smarthome.dto.SensorDTO;
import smarthome.dto.SensorTypeDTO;
import smarthome.mapper.DeviceMapper;
import smarthome.mapper.SensorMapper;
import smarthome.mapper.SensorTypeMapper;
import smarthome.services.SensorService;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.sensortype.SensorTypeIDVO;
import smarthome.vo.sensorvo.SensorNameVO;

public class AddSensorToDeviceCTRL {
    private final SensorService sensorService;

    /**
     * Constructor for AddSensorToDeviceCTRL. It takes a sensorService as parameter, validating before encapsulating.
     * @param sensorService SensorService object
     * @throws IllegalArgumentException if any of the parameters are null
     */
    public AddSensorToDeviceCTRL(SensorService sensorService) {
        if(sensorService == null){
            throw new IllegalArgumentException("Invalid service");
        }
        this.sensorService = sensorService;
    }

    /**
     * Method that adds a sensor to an existing device. It takes a deviceDTO, sensorTypeDTO and sensorDTO as parameters.
     * It then communicates with the SensorMapper, DeviceMapper and SensorTypeMapper to create the necessary VO objects.
     * It then communicates with the sensorTypeService and deviceService to verify if the sensor type and device are valid.
     * If the VOs are valid, it then uses the Sensor Service implementation to add a Sensor.
     * @param deviceDTO DeviceDTO object
     * @param sensorTypeDTO SensorTypeDTO object
     * @param sensorDTO SensorDTO object
     * @return True if the sensor was added successfully, false otherwise.
     */
    public boolean addSensorToDevice(DeviceDTO deviceDTO, SensorTypeDTO sensorTypeDTO, SensorDTO sensorDTO) {
        try{
            SensorNameVO sensorNameVO = SensorMapper.createSensorNameVO(sensorDTO);
            DeviceIDVO deviceIDVO = DeviceMapper.createDeviceID(deviceDTO);
            SensorTypeIDVO sensorTypeIDVO = SensorTypeMapper.createSensorTypeIDVO(sensorTypeDTO);
            return sensorService.addSensor(sensorNameVO,deviceIDVO,sensorTypeIDVO);
        } catch (IllegalArgumentException e){
            return false;
        }
    }
}
