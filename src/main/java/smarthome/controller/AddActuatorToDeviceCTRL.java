package smarthome.controller;

import smarthome.dto.ActuatorDTO;
import smarthome.dto.ActuatorTypeDTO;
import smarthome.dto.DeviceDTO;
import smarthome.mapper.ActuatorMapper;
import smarthome.mapper.ActuatorTypeMapper;
import smarthome.mapper.DeviceMapper;
import smarthome.services.ActuatorService;
import smarthome.vo.actuatorvo.Settings;
import smarthome.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.vo.actuatorvo.ActuatorNameVO;
import smarthome.vo.devicevo.DeviceIDVO;


import static java.util.Objects.isNull;

public class AddActuatorToDeviceCTRL {

    private final ActuatorService actuatorService;

    /**
     * Constructs an instance of AddActuatorToDeviceCTRL with the provided ActuatorService dependency.
     * This constructor initializes the AddActuatorToDeviceCTRL with the ActuatorService used to add actuators to devices.
     * It ensures that the provided ActuatorService is not null and throws an IllegalArgumentException if it is.
     * @param actuatorService The ActuatorService used to add actuators to devices.
     * @throws IllegalArgumentException if the actuatorService parameter is null. This exception is thrown to indicate
     * an invalid or missing service.
     */
    public AddActuatorToDeviceCTRL(ActuatorService actuatorService) {
        if (isNull(actuatorService)) {
            throw new IllegalArgumentException("Invalid service");
        }
        this.actuatorService = actuatorService;
    }

    /**
     * Adds an Actuator to the specified Device.
     * This method creates Value Objects (VOs) for ActuatorName, ActuatorTypeID, Settings, and DeviceID
     * using the provided ActuatorDTO, ActuatorTypeDTO, and DeviceDTO.
     * It then calls the addActuator method of the ActuatorService to add the Actuator to the Device.
     * @param actuatorDTO The Data Transfer Object (DTO) containing Actuator information.
     * @param actuatorTypeDTO The DTO containing ActuatorType information.
     * @param deviceDTO The DTO containing Device information.
     * @return true if the Actuator is successfully added to the Device, false otherwise. It returns false if any of the
     * provided DTOs are invalid or if there is an error adding the Actuator.
     */
    public boolean addActuatorToDevice(ActuatorDTO actuatorDTO, ActuatorTypeDTO actuatorTypeDTO, DeviceDTO deviceDTO) {
        try{
            ActuatorNameVO actuatorNameVO = ActuatorMapper.createActuatorNameVO(actuatorDTO);
            Settings settings = ActuatorMapper.createSettingsVO(actuatorDTO);
            ActuatorTypeIDVO actuatorTypeIDVO = ActuatorTypeMapper.createActuatorTypeIDVO(actuatorTypeDTO);
            DeviceIDVO deviceIDVO = DeviceMapper.createDeviceID(deviceDTO);
            return actuatorService.addActuator(actuatorNameVO,actuatorTypeIDVO,deviceIDVO,settings);
        } catch (IllegalArgumentException e){
            return false;
        }
    }
}
