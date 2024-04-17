package smarthome.domain.actuator;

import smarthome.domain.DomainEntity;
import smarthome.domain.actuator.externalservice.ActuatorExternalService;
import smarthome.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.vo.actuatorvo.ActuatorIDVO;
import smarthome.vo.actuatorvo.ActuatorNameVO;
import smarthome.vo.devicevo.DeviceIDVO;

import java.util.UUID;

public class RollerBlindActuator implements DomainEntity, Actuator{

    private final ActuatorIDVO actuatorID;
    private final ActuatorTypeIDVO actuatorTypeID;
    private final DeviceIDVO deviceIDVO;
    private ActuatorNameVO actuatorName;

    /**
     * Constructs a new RollerBlindActuator with the provided actuatorName, actuatorTypeID, and deviceIDVO. Throws an
     * IllegalArgumentException if any of the provided parameters is null.
     * Internally it generates its ID using a UUID and injecting that identifier in a ActuatorIDVO object.
     * @param actuatorName The name of the actuator.
     * @param actuatorTypeID The type ID of the actuator.
     * @param deviceIDVO The ID of the device associated with the actuator.
     * @throws IllegalArgumentException If any of the provided parameters is null.
     */
    public RollerBlindActuator(ActuatorNameVO actuatorName, ActuatorTypeIDVO actuatorTypeID, DeviceIDVO deviceIDVO) {
        if (!parametersAreValid(actuatorName, actuatorTypeID, deviceIDVO)) {
            throw new IllegalArgumentException("Invalid Parameters");
        }
        this.actuatorID = new ActuatorIDVO(UUID.randomUUID());
        this.actuatorTypeID = actuatorTypeID;
        this.deviceIDVO = deviceIDVO;
        this.actuatorName = actuatorName;
    }


    /**
     * Executes the command to move the roller blind to the specified position. The position must be between 0 and 100.
     * @param simHardwareAct The hardware simulator.
     * @param position The position to move the roller blind to.
     * @return true if the command was executed successfully, false otherwise.
     */
    public boolean executeCommand(ActuatorExternalService simHardwareAct, int position){
        if (!validateCommand(position)) {
            return false;
        }
        return simHardwareAct.executeIntegerCommandSim(position);
    }


    /**
     * Simple getter method for the actuator ID.
     * @return The actuator ID.
     */
    @Override
    public ActuatorIDVO getId() {
        return this.actuatorID;
    }


    /**
     * Checks if the provided actuator parameters are valid.
     * @param actuatorName The name of the actuator.
     * @param actuatorTypeID The type ID of the actuator.
     * @param deviceIDVO The ID of the device associated with the actuator.
     * @return true if all parameters are not null, false otherwise.
     */
    private boolean parametersAreValid(ActuatorNameVO actuatorName, ActuatorTypeIDVO actuatorTypeID, DeviceIDVO deviceIDVO) {
        return actuatorName != null && actuatorTypeID != null && deviceIDVO != null;
    }


    /**
     * Validates the command to move the roller blind to the specified position.
     * @param position The position to move the roller blind to.
     * @return true if the position is between 0 and 100, false otherwise.
     */
    private boolean validateCommand(int position) {
        return position >= 0 && position <= 100;
    }

    /**
     * Simple getter method.
     * @return ActuatorTypeIDVO object
     */
    @Override
    public ActuatorTypeIDVO getActuatorTypeID() {
        return this.actuatorTypeID;
    }

    /**
     * Simple getter method.
     * @return DeviceIDVO object
     */
    @Override
    public DeviceIDVO getDeviceID() {
        return this.deviceIDVO;
    }

    /**
     * Simple getter method.
     * @return ActuatorName object
     */
    @Override
    public ActuatorNameVO getActuatorName() {
        return this.actuatorName;
    }
}
