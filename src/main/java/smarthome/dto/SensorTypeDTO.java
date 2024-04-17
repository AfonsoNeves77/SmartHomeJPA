package smarthome.dto;

public class SensorTypeDTO {
    private String sensorTypeID;
    private String unit;

    /**
     * Constructor for SensorTypeDTO
     *
     * @param sensorTypeID as String
     * @param unit as String
     */
    public SensorTypeDTO(String sensorTypeID, String unit) {
        this.sensorTypeID = sensorTypeID;
        this.unit = unit;
    }

    /**
     * Get the sensorTypeID
     *
     * @return sensorTypeID as String
     */
    public String getSensorTypeID() {
        return sensorTypeID;
    }
    /**
     * Get the unit
     *
     * @return unit as String
     */
    public String getUnit() {
        return unit;
    }
}