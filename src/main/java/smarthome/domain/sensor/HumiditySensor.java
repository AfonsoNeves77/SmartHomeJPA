package smarthome.domain.sensor;

import smarthome.domain.sensor.externalservices.SensorExternalServices;
import smarthome.domain.sensor.values.HumidityValue;
import smarthome.vo.ValueObject;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.sensortype.SensorTypeIDVO;
import smarthome.vo.sensorvo.SensorIDVO;
import smarthome.vo.sensorvo.SensorNameVO;

import java.util.UUID;

public class HumiditySensor implements Sensor {

    private SensorNameVO sensorName;
    private final DeviceIDVO deviceID;
    private final SensorTypeIDVO sensorTypeID;
    private final SensorIDVO sensorID;

    /**
     * Constructor for humidity sensor. It can only be instantiated if parameters are not null.
     * It then generates a random UUID for the sensor ID.
     * @param sensorName Sensor name
     * @param deviceID DeviceID
     * @param sensorTypeID SensorTypeID
     * @throws IllegalArgumentException If parameters out of bounds.
     */
    public HumiditySensor(SensorNameVO sensorName, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID) {
        if (areParamsNull(sensorName,deviceID,sensorTypeID)){
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        this.sensorName = sensorName;
        this.deviceID = deviceID;
        this.sensorTypeID = sensorTypeID;
        this.sensorID = new SensorIDVO(UUID.randomUUID());
    }

    /**
     * This method gets the reading from the external service and returns it as a ValueObject.
     * It first checks if the external service is null, if so throws IllegalArgumentException.
     * Then it gets the reading from the simhardware in String, returning it as a HumidityValue.
     * @param simHardware External service that provides the reading.
     * @return ValueObject with the reading.
     * @throws InstantiationException If parameters out of bounds.
     */
    public ValueObject<Integer> getReading(SensorExternalServices simHardware) throws InstantiationException {
        if(areParamsNull(simHardware)){
            throw new IllegalArgumentException("Invalid external service");
        }
        String simValue = simHardware.getValue();
        return new HumidityValue(simValue);
    }

    /**
     * This method returns the sensor name.
     * @return SensorNameVO with the sensor name.
     */
    @Override
    public SensorNameVO getSensorName() {
        return sensorName;
    }

    /**
     * This method returns the device ID.
     * @return DeviceIDVO with the device ID.
     */
    @Override
    public DeviceIDVO getDeviceID() {
        return deviceID;
    }

    /**
     * This method returns the sensor type ID.
     * @return SensorTypeIDVO with the sensor type ID.
     */
    @Override
    public SensorTypeIDVO getSensorTypeID() {
        return sensorTypeID;
    }

    /**
     * This method returns the sensor ID.
     * @return SensorIDVO with the sensor ID.
     */
    @Override
    public SensorIDVO getId() {
        return this.sensorID;
    }

    /**
     * This method checks if the parameters are null, being used both in the constructor and in the getReading method.
     * If any parametera are null, it returns true. Otherwise, it returns false.
     * @param params Object parameters
     * @return boolean
     */
    private boolean areParamsNull(Object... params){
        for (Object param : params){
            if (param == null){
                return true;
            }
        }
        return false;
    }
}
