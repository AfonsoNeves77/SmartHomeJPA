package smarthome.domain.actuator;

import smarthome.domain.actuator.externalservice.ActuatorExternalService;
import smarthome.vo.actuatorvo.Settings;
import smarthome.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.vo.actuatorvo.ActuatorIDVO;
import smarthome.vo.actuatorvo.ActuatorNameVO;
import smarthome.vo.actuatorvo.IntegerSettingsVO;
import smarthome.vo.devicevo.DeviceIDVO;

import java.util.UUID;

public class IntegerValueActuator implements Actuator {
    private ActuatorNameVO actuatorName;
    private final ActuatorTypeIDVO actuatorType;
    private final DeviceIDVO deviceID;
    private final ActuatorIDVO actuatorID;
    private final IntegerSettingsVO integerSettings;

    /**
     * Constructs a new IntSetRangeActuator object with the specified actuator name, type, device ID and the settings interface.
     * Validates the provided parameters to ensure they are not null.
     * Tries to cast the settings interface to an IntegerSettingsVO object.
     * Assigns a unique actuator ID using a randomly generated UUID.
     */
    public IntegerValueActuator(ActuatorNameVO actuatorName, ActuatorTypeIDVO actuatorType, DeviceIDVO deviceID, Settings settings){
        if(areParamsNull(actuatorName, actuatorType, deviceID, settings)){
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        try{
            this.integerSettings = (IntegerSettingsVO) settings;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Invalid settings type");
        }
        this.actuatorName = actuatorName;
        this.actuatorType = actuatorType;
        this.deviceID = deviceID;
        this.actuatorID = new ActuatorIDVO(UUID.randomUUID());
    }

    /**
     * This method executes the command on the actuator.
     * To ensure the upper and lower limits have been already set, hence not null, the method throws an IllegalArgumentException if either are null.
     * However, being both null by default when the actuator is created, verifying if the upperLimit is null is in this case redundant.
     * It was chosen to keep the verification for consistency and to avoid potential future changes that could make the upperLimit null.
     * It then verifies if the provided value is within the limits, by use of a private method, and throws an IllegalArgumentException if not.
     * Furthermore, it verifies if the simHardwareAct is null and throws an IllegalArgumentException if so.
     * The simHardwareAct is then used to execute the command with the provided value and the value of the actuator is set, altering the object's state to its last definition.
     * The method returns true if the command is successfully executed, false otherwise.
     * @param simHardwareAct the hardware actuator to execute the command on
     * @param value the value to set the actuator to
     * @return String with feedback
     */
    public String executeCommand(ActuatorExternalService simHardwareAct, int value) {
        if (simHardwareAct == null) {
            return "Invalid hardware, could not execute command";
        }
        if (!isValueWithinLimits(value)) {
            return "Invalid value, could not execute command";
        }
        if (simHardwareAct.executeIntegerCommandSim(value)) {
            return "Value was set";
        }
        return "Error: Value was not set";
    }



    /**
     * This method verifies if a given value is within the range of the pre-established limits for the present state of the actuator.
     * @return true if the value is within the limits, false otherwise
     */
    private boolean isValueWithinLimits(int value){
        int lowerLimit = this.integerSettings.getValue()[0];
        int upperLimit = this.integerSettings.getValue()[1];
        return (value >= lowerLimit && value <= upperLimit);
    }

    /**
     * This method gets the actuator's ID.
     * @return the actuator ID object (ActuatorIDVO)
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
        return this.actuatorType;
    }

    /**
     * Simple getter method.
     * @return DeviceIDVO object
     */
    @Override
    public DeviceIDVO getDeviceID() {
        return this.deviceID;
    }

    /**
     * Simple getter method.
     * @return ActuatorName object
     */
    @Override
    public ActuatorNameVO getActuatorName() {
        return this.actuatorName;
    }

    /**
     * This method verifies if a given set of parameters are null.
     * @return true if any of the parameters are null, false otherwise
     */
    private boolean areParamsNull(Object... params){
        for (Object param : params){
            if (param == null){
                return true;
            }
        }
        return false;
    }

    public IntegerSettingsVO getIntegerSettings() {
        return integerSettings;
    }
}
