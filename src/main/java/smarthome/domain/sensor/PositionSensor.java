package smarthome.domain.sensor;

import smarthome.domain.sensor.externalservices.SensorExternalServices;
import smarthome.domain.sensor.values.PositionValue;
import smarthome.vo.ValueObject;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.sensortype.SensorTypeIDVO;
import smarthome.vo.sensorvo.SensorIDVO;
import smarthome.vo.sensorvo.SensorNameVO;

import java.util.UUID;

import static java.lang.Integer.parseInt;

/**
 * PositionSensor is a domain entity that represents a sensor that measures the position/value in a scale.
 */
public class PositionSensor implements Sensor {

    private SensorNameVO sensorName;
    private final DeviceIDVO deviceID;
    private final SensorTypeIDVO sensorType;
    private final SensorIDVO sensorID;


    /**
     * Constructor for PositionSensor, it validates the receiving VOs and generates a new SensorIDVO utilizing the UUID.
     * @param sensorName The name of the sensor.
     * @param deviceID The ID of the device to which the sensor is attached.
     * @param sensorType The type of the sensor.
     */
    public PositionSensor (SensorNameVO sensorName, DeviceIDVO deviceID, SensorTypeIDVO sensorType){
        if(!areParamsValid(sensorName, deviceID, sensorType)){
            throw new IllegalArgumentException("Invalid Parameters");
        } else {
            this.sensorName = sensorName;
            this.deviceID = deviceID;
            this.sensorType = sensorType;
            this.sensorID = new SensorIDVO(UUID.randomUUID());
        }
    }

    /**
     * Obtains the reading from the sensor. It receives
     * a SimHardware external service (validating it against null).
     * @param simHardware The external service that provides the sensor reading.
     * @return A PositionValue object as ValueObject<Integer>.
     * @throws InstantiationException If the external service is null.
     */
    public ValueObject<Integer> getReading(SensorExternalServices simHardware) throws InstantiationException {
        if (simHardware == null) throw new IllegalArgumentException("Invalid external service");
        int simValue = parseInt(simHardware.getValue());
        return new PositionValue(simValue);
    }

    /**
     * Getter method for the sensor ID.
     * @return  The sensor ID.
     */
    public SensorIDVO getId(){
        return this.sensorID;
    }

    /**
     * Getter method for the sensor name.
     * @return  The sensor name.
     */
    @Override
    public SensorNameVO getSensorName(){
        return this.sensorName;
    }

    /**
     * Getter method for the device ID.
     * @return  The device ID.
     */
    @Override
    public DeviceIDVO getDeviceID(){
        return this.deviceID;
    }

    /**
     * Getter method for the sensor type.
     * @return  The sensor type.
     */
    @Override
    public SensorTypeIDVO getSensorTypeID(){
        return this.sensorType;
    }


    /**
     * Method to validate the parameters of the constructor.
     * @param sensorName The name of the sensor.
     * @param deviceID The ID of the device to which the sensor is attached.
     * @param sensorType The type of the sensor.
     * @return True if the parameters are valid, false otherwise.
     */
    private boolean areParamsValid (SensorNameVO sensorName, DeviceIDVO deviceID, SensorTypeIDVO sensorType){
        return sensorName != null && deviceID != null && sensorType != null;
    }

}
