package smarthome.dto;

public class SensorDTO {

    /*
    Since there is no current need to externalize any sensor data, no other attributes and methods where added for the
    moment.
     */

    private String sensorName;

    /**
     * Constructor for SensorDTO.
     * Use scenario: to receive external information when a new sensor is being added to the system. The only data sent
     * in respect to the sensor is its name.
     * @param sensorName sensor name
     */
    public SensorDTO(String sensorName){
        this.sensorName = sensorName;
    }

    /**
     * Simple getter method to retrieve sensor name.
     * @return sensor name
     */
    public String getSensorName(){
        return this.sensorName;
    }
}
