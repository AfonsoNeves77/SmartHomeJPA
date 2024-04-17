package smarthome.mapper;

import smarthome.dto.ActuatorDTO;
import smarthome.vo.actuatorvo.Settings;
import smarthome.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.vo.actuatorvo.ActuatorNameVO;
import smarthome.vo.actuatorvo.DecimalSettingsVO;
import smarthome.vo.actuatorvo.IntegerSettingsVO;
import smarthome.vo.devicevo.DeviceIDVO;

import java.util.UUID;

/**
 * Mapper class for Actuator
 * It contains methods to create ActuatorIDVO, ActuatorNameVO, ActuatorTypeIDVO and DeviceIDVO objects from ActuatorDTO objects.
 * It also contains a method to convert a list of domain Actuator objects to a list of ActuatorDTO objects.
 */

public class ActuatorMapper {

    private static final String ERRORMESSAGE = "ActuatorDTO cannot be null.";

    /**
     * Method to create an ActuatorNameVO object from an ActuatorDTO object.
     * It throws an IllegalArgumentException if the ActuatorDTO object is null.
     *
     * @param actuatorDTO ActuatorDTO object
     * @return ActuatorNameVO object
     */

    public static ActuatorNameVO createActuatorNameVO(ActuatorDTO actuatorDTO) {
        if (actuatorDTO == null) {
            throw new IllegalArgumentException(ERRORMESSAGE);
        } else {
            return new ActuatorNameVO(actuatorDTO.getActuatorName());
        }
    }

    public static Settings createSettingsVO(ActuatorDTO actuatorDTO) {
        String lowerLimit = actuatorDTO.getLowerLimit();
        String upperLimit = actuatorDTO.getUpperLimit();
        String precision = actuatorDTO.getPrecision();
        try {
            if (precision == null && lowerLimit != null && upperLimit != null) {
                return new IntegerSettingsVO(lowerLimit, upperLimit);
            }
            if (precision != null && lowerLimit != null && upperLimit != null) {
                return new DecimalSettingsVO(lowerLimit, upperLimit, precision);
            }
        } catch (IllegalArgumentException e) {
            return null;
        }
        return null;
    }

    /**
     * Method to create an ActuatorTypeIDVO object from an ActuatorDTO object.
     * It throws an IllegalArgumentException if the ActuatorDTO object is null.
     *
     * @param actuatorDTO ActuatorDTO object
     * @return ActuatorTypeIDVO object
     */

    public static ActuatorTypeIDVO createActuatorTypeIDVO(ActuatorDTO actuatorDTO) {
        if (actuatorDTO == null) {
            throw new IllegalArgumentException(ERRORMESSAGE);
        } else {
            return new ActuatorTypeIDVO(actuatorDTO.getActuatorType());
        }
    }

        /**
     * Method to create a DeviceIDVO object from an ActuatorDTO object.
     * It throws an IllegalArgumentException if the ActuatorDTO object is null.
     *
     * @param actuatorDTO ActuatorDTO object
     * @return DeviceIDVO object
     */

    public static DeviceIDVO createDeviceIDVO(ActuatorDTO actuatorDTO) {
        if (actuatorDTO == null) {
            throw new IllegalArgumentException(ERRORMESSAGE);
        } else {
            UUID deviceID = UUID.fromString(actuatorDTO.getDeviceID());
            return new DeviceIDVO(deviceID);
        }
    }
}