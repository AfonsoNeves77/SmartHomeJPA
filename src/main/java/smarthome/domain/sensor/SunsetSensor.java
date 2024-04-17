package smarthome.domain.sensor;

import smarthome.domain.DomainID;
import smarthome.domain.sensor.externalservices.SunTimeServices;
import smarthome.domain.sensor.values.SunTimeValue;
import smarthome.vo.ValueObject;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.sensortype.SensorTypeIDVO;
import smarthome.vo.sensorvo.SensorIDVO;
import smarthome.vo.sensorvo.SensorNameVO;

import java.time.ZonedDateTime;
import java.util.UUID;

public class SunsetSensor implements Sensor {

    private final SensorIDVO sensorID;
    private SensorNameVO sensorName;
    private final SensorTypeIDVO sensorTypeID;
    private final DeviceIDVO deviceID;

    /**
     * Constructor for SunsetSensor. It validates the received VOs and creates a new SensorIDVO utilizing the UUID
     * library. Received VOs and SensorID are encapsulated within the object.
     *
     * @param sensorName   Sensor name
     * @param deviceID     Device ID
     * @param sensorTypeID SensorType ID
     */

    public SunsetSensor(SensorNameVO sensorName, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID) {
        if (areParamsNull(sensorName, deviceID, sensorTypeID)) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        this.sensorName = sensorName;
        this.deviceID = deviceID;
        this.sensorTypeID = sensorTypeID;
        this.sensorID = new SensorIDVO(UUID.randomUUID());
    }

    /**
     * Method to validate if the received parameters are null. It receives a SensorNameVO, SensorTypeIDVO and DeviceIDVO
     * and returns a boolean.
     *
     * @param sensorName   Sensor name
     * @param sensorTypeID SensorType ID
     * @param deviceID     Device ID
     * @return boolean
     */

    private boolean areParamsNull(SensorNameVO sensorName, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID) {
        return sensorName == null || deviceID == null || sensorTypeID == null;
    }

    /**
     * Obtains the sunset time. It receives a data, gps location and SunTimeCalculator external service (validating it
     * against null). Returns a SunTimeValue object as ValueObject<ZoneDateTime>
     *
     * @param date              Date
     * @param gpsLocation       Geolocation
     * @param sunTimeCalculator SunTimeCalculator external service
     * @return SunTimeValue object
     */

    public ValueObject<ZonedDateTime> getSunsetTime(String date, String gpsLocation, SunTimeServices sunTimeCalculator) {
        if (sunTimeCalculator == null) {
            throw new IllegalArgumentException("Invalid external service");
        }
        ZonedDateTime sunsetTime = sunTimeCalculator.computeSunset(date, gpsLocation);
        return new SunTimeValue(sunsetTime);
    }

    /**
     * Simple getter method
     *
     * @return The encapsulated VO for Name;
     */
    @Override
    public SensorNameVO getSensorName() {
        return sensorName;
    }

    /**
     * Simple getter method
     *
     * @return The encapsulated VO for SensorTypeID;
     */
    @Override
    public SensorTypeIDVO getSensorTypeID() {
        return sensorTypeID;
    }

    /**
     * Simple getter method
     *
     * @return The encapsulated VO for DeviceID;
     */
    @Override
    public DeviceIDVO getDeviceID() {
        return deviceID;
    }

    /**
     * Simple getter method
     * It's an override from DomainEntity interface
     *
     * @return The encapsulated VO for SensorID;
     */

    @Override
    public DomainID getId() {
        return this.sensorID;
    }
}
