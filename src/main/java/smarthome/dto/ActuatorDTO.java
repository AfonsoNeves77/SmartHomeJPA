package smarthome.dto;

/**
 * Data Transfer Object that contains the actuator ID, name, type and device ID.
 * It is used to transfer data between the Actuator class and the ActuatorMapper class.
 * It is also contains the lower limit, upper limit and precision of the actuator for specific Actuators.
 */

public class ActuatorDTO {

    private String actuatorName;
    private String actuatorType;
    private String deviceID;
    private String lowerLimit;
    private String upperLimit;
    private String precision;

    /**
     * Constructor for the ActuatorDTO class.
     * It initializes the actuator name, type and device ID. Used to create an Actuator.
     * @param actuatorName The name of the actuator.
     * @param actuatorType The type of the actuator.
     * @param deviceID The ID of the device.
     */

    public ActuatorDTO(String actuatorName, String actuatorType, String deviceID) {
        this.actuatorName = actuatorName;
        this.actuatorType = actuatorType;
        this.deviceID = deviceID;
    }

    /**
     * Constructor for the ActuatorDTO class.
     * It initializes the actuator name, type, device ID, lower limit and upper limit. Used to create an Actuator that receives limits.
     * @param actuatorName The name of the actuator.
     * @param actuatorType The type of the actuator.
     * @param deviceID The ID of the device.
     * @param lowerLimit The lower limit of the actuator.
     * @param upperLimit The upper limit of the actuator.
     */

    public ActuatorDTO(String actuatorName, String actuatorType, String deviceID, String lowerLimit, String upperLimit) {
        this.actuatorName = actuatorName;
        this.actuatorType = actuatorType;
        this.deviceID = deviceID;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    /**
     * Constructor for the ActuatorDTO class.
     * It initializes the actuator name, type, device ID, lower limit, upper limit and precision. Used to create an Actuator that receives limits and precision.
     * @param actuatorName The name of the actuator.
     * @param actuatorType The type of the actuator.
     * @param deviceID The ID of the device.
     * @param lowerLimit The lower limit of the actuator.
     * @param upperLimit The upper limit of the actuator.
     * @param precision The precision of the actuator.
     */

    public ActuatorDTO(String actuatorName, String actuatorType, String deviceID, String lowerLimit, String upperLimit, String precision) {
        this.actuatorName = actuatorName;
        this.actuatorType = actuatorType;
        this.deviceID = deviceID;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.precision = precision;
    }


    /**
     * Method to get the actuator name.
     *
     * @return The name of the actuator.
     */

    public String getActuatorName() {
        return actuatorName;
    }

    /**
     * Method to get the actuator type.
     *
     * @return The type of the actuator.
     */

    public String getActuatorType() {
        return actuatorType;
    }

    /**
     * Method to get the device ID.
     *
     * @return The ID of the device.
     */

    public String getDeviceID() {
        return deviceID;
    }

    /**
     * Method to get the lower limit.
     *
     * @return The lower limit of the actuator.
     */

    public String getLowerLimit() {
        return lowerLimit;
    }

    /**
     * Method to get the upper limit.
     *
     * @return The upper limit of the actuator.
     */

    public String getUpperLimit() {
        return upperLimit;
    }

    /**
     * Method to get the precision.
     *
     * @return The precision of the actuator.
     */

    public String getPrecision() {
        return precision;
    }
}
