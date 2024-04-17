package smarthome.dto;

public class ActuatorTypeDTO {

    private String actuatorTypeID;

    /**
     * Constructor for ActuatorTypeDTO
     *
     * @param actuatorTypeID as String
     */
    public ActuatorTypeDTO(String actuatorTypeID) {
        this.actuatorTypeID = actuatorTypeID;
    }

    /**
     * Get the actuatorTypeID
     *
     * @return actuatorTypeID as String
     */
    public String getActuatorTypeID() {
        return actuatorTypeID;
    }
}
