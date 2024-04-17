package smarthome.vo.actuatorvo;

import smarthome.vo.ValueObject;

public class ActuatorStatusVO implements ValueObject<Boolean> {

    private final boolean actuatorStatus;

    /**
     * Constructor for ActuatorStatusVO. No validations required.
     * @param actuatorStatus Device Status
     */
    public ActuatorStatusVO (boolean actuatorStatus) {
        this.actuatorStatus = actuatorStatus;
    }

    /**
     * Simple getter method
     * @return Encapsulated value as a Wrapper class Boolean
     */
    @Override
    public Boolean getValue() {
        return this.actuatorStatus;
    }


}
