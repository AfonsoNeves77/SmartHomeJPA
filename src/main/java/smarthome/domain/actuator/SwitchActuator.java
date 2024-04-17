package smarthome.domain.actuator;

import smarthome.domain.actuator.externalservice.ActuatorExternalService;
import smarthome.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.vo.actuatorvo.ActuatorIDVO;
import smarthome.vo.actuatorvo.ActuatorNameVO;
import smarthome.vo.devicevo.DeviceIDVO;

import java.util.UUID;


/**
 * Represents a switch actuator that switches a
 load ON(true)/OFF(false).
 * This class implements both the DomainEntity and Actuator interfaces.
 */
public class SwitchActuator implements Actuator {

    private final ActuatorIDVO actuatorID;
    private ActuatorNameVO actuatorName;
    private final ActuatorTypeIDVO actuatorTypeID;
    private final DeviceIDVO deviceIDVO;

    /**
     * Constructs a new SwitchActuator with the provided actuatorName, actuatorTypeID, and deviceIDVO.
     * Throws an IllegalArgumentException if any of the provided parameters is null.
     * Internally it generates it´s ID using a UUID and injecting that identifier in a ActuatorIDVO object.
     *
     * @param actuatorName   The name of the actuator.
     * @param actuatorTypeID The type ID of the actuator.
     * @param deviceIDVO     The ID of the device associated with the actuator.
     * @throws IllegalArgumentException If any of the provided parameters is null.
     */
    public SwitchActuator(ActuatorNameVO actuatorName, ActuatorTypeIDVO actuatorTypeID, DeviceIDVO deviceIDVO) {
        if (!parametersAreValid(actuatorName, actuatorTypeID, deviceIDVO)) {
            throw new IllegalArgumentException("Invalid Parameters");
        }
        this.actuatorID = new ActuatorIDVO(UUID.randomUUID());
        this.actuatorName = actuatorName;
        this.actuatorTypeID = actuatorTypeID;
        this.deviceIDVO = deviceIDVO;
    }

    /**
     * Checks if the provided actuator parameters are valid.
     *
     * @param actuatorName   The name of the actuator.
     * @param actuatorTypeID The type ID of the actuator.
     * @param deviceIDVO     The ID of the device associated with the actuator.
     * @return true if all parameters are not null, false otherwise.
     */
    private boolean parametersAreValid(ActuatorNameVO actuatorName, ActuatorTypeIDVO actuatorTypeID, DeviceIDVO deviceIDVO) {
        return actuatorName != null && actuatorTypeID != null && deviceIDVO != null;
    }

    /**
     * Switches the load using the provided SimHardwareAct.
     * SimHardwareAct is an abstraction that represents the external entity or "piece" of hardware this SwitchActuator is going
     * to act upon.
     * This function calls a method in SimHardwareAct class that executes the load switching command, this operation returns a boolean
     * considering if the command was (or not) successfully executed. switchLoad() will return the output of that function.
     *
     * @param simHardwareAct The SimHardwareAct to use for executing the command.
     * @return true if the command was executed successfully, false otherwise.
     */
    public boolean switchLoad(ActuatorExternalService simHardwareAct) {
        return simHardwareAct.executeCommandSim();
    }

    /**
     * Retrieves the ID of the actuator.
     *
     * @return The ActuatorIDVO representing the ID of the actuator.
     */
    @Override
    public ActuatorIDVO getId() {
        return this.actuatorID;
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

