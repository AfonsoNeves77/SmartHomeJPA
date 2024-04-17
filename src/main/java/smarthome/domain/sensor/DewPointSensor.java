package smarthome.domain.sensor;

import smarthome.domain.sensor.externalservices.SensorExternalServices;
import smarthome.domain.sensor.values.DewPointValue;
import smarthome.vo.ValueObject;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.sensortype.SensorTypeIDVO;
import smarthome.vo.sensorvo.SensorIDVO;
import smarthome.vo.sensorvo.SensorNameVO;

import java.util.UUID;

public class DewPointSensor implements Sensor {

    private final SensorIDVO sensorID;
    private SensorNameVO sensorName;
    private final SensorTypeIDVO sensorTypeID;
    private final DeviceIDVO deviceID;

    /**
     * Constructor for DewPointSensor, it validates the receiving VOs and creates a new SensorIDVO utilizing the UUID
     * library. Received VOs and SensorID are encapsulated within the object.
     * @param sensorName Sensor name
     * @param deviceID Device ID
     * @param sensorTypeID SensorType ID
     */
    public DewPointSensor(SensorNameVO sensorName, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID) {
        if (areParamsNull(sensorName,deviceID,sensorTypeID)){
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        this.sensorName = sensorName;
        this.deviceID = deviceID;
        this.sensorTypeID = sensorTypeID;
        this.sensorID = new SensorIDVO(UUID.randomUUID());
    }
    /**
     * Obtains the dewPoint value. It receives a SimHardware external service (validating it)
     *
     * @param simHardware SimHardware external service
     * @return dewPointValue object
     * @throws InstantiationException If value unable to be created
     */
    public ValueObject<Double> getReading(SensorExternalServices simHardware) throws InstantiationException {
        if (simHardware == null) throw new IllegalArgumentException("Invalid external service");
        String simValue = simHardware.getValue();
        return new DewPointValue(simValue);
    }

    /**
     * Simple getter method
     * @return The encapsulated VO for Name;
     */
    public SensorNameVO getSensorName(){
        return this.sensorName;
    }

    /**
     * Simple getter method
     * @return The encapsulated VO for SensorTypeID;
     */
    public SensorTypeIDVO getSensorTypeID(){
        return this.sensorTypeID;
    }

    /**
     * Simple getter method
     * @return The encapsulated VO for DeviceID;
     */
    public DeviceIDVO getDeviceID(){
        return this.deviceID;
    }

    /**
     * Simple getter method, implemented from EntityDomain interface
     * @return The encapsulated VO for SensorID;
     */
    @Override
    public SensorIDVO getId() {
        return this.sensorID;
    }

    /**
     * Validates any number of object params against null
     * @param params Any object param
     * @return True or false
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