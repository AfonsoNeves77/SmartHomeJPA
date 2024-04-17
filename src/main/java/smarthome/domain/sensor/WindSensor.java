package smarthome.domain.sensor;

import smarthome.domain.sensor.externalservices.SensorExternalServices;
import smarthome.domain.sensor.values.WindValue;
import smarthome.vo.ValueObject;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.sensortype.SensorTypeIDVO;
import smarthome.vo.sensorvo.SensorIDVO;
import smarthome.vo.sensorvo.SensorNameVO;

import java.util.UUID;

/**
 * A class representing a wind sensor.
 * Implements the Sensor interface that extends EntityDomain interface, so it has to offer an implementation
 * for all methods defined in DomainID interface.
 */
public class WindSensor implements Sensor {

    private final SensorIDVO sensorIDVO;
    private SensorNameVO nameVO;
    private final DeviceIDVO deviceIDVO;
    private final SensorTypeIDVO sensorTypeIDVO;

    /**
     * Constructs a WindSensor with the specified parameters.
     * @param nameVO The name value object of the sensor.
     * @param deviceIDVO The device ID value object of the sensor.
     * @param sensorTypeIDVO The sensor type ID value object of the sensor.
     * @throws IllegalArgumentException if any of the parameters are null.
     */
    public WindSensor(SensorNameVO nameVO, DeviceIDVO deviceIDVO, SensorTypeIDVO sensorTypeIDVO) {
        if (!parametersAreValid(nameVO, deviceIDVO, sensorTypeIDVO)) {
            throw new IllegalArgumentException("Invalid parameters");
        }
        this.sensorIDVO = new SensorIDVO(UUID.randomUUID());
        this.nameVO = nameVO;
        this.sensorTypeIDVO = sensorTypeIDVO;
        this.deviceIDVO = deviceIDVO;
    }

    private boolean parametersAreValid(SensorNameVO nameVO, DeviceIDVO deviceIDVO, SensorTypeIDVO sensorTypeIDVO) {
        return nameVO != null && deviceIDVO != null && sensorTypeIDVO != null;
    }

    /**
     * Retrieves the reading from the simulated hardware. This hardware allows getting a value (an outside information)
     * that is incorporated and validated in this specific sensor value (WindValue).
     * This wind value is parametrized with String[], so the consumer of this method should be
     * able to receive this type of data.
     * @param simHardware The simulated hardware providing the reading.
     * @return The wind value object containing the reading.
     * @throws IllegalArgumentException if the simHardware parameter is null.
     */
    public ValueObject<String[]> getReading(SensorExternalServices simHardware) {
        if (simHardware == null) {
            throw new IllegalArgumentException("Invalid SimHardware");
        }

        String simValue = simHardware.getValue();

        return new WindValue(simValue);
    }

    /**
     * Retrieves the name value object of the sensor.
     * @return The name value object.
     */
    @Override
    public SensorNameVO getSensorName() {
        return nameVO;
    }

    /**
     * Retrieves the device ID value object of the sensor.
     * @return The device ID value object.
     */
    @Override
    public DeviceIDVO getDeviceID() {
        return deviceIDVO;
    }

    /**
     * Retrieves the sensor type ID value object of the sensor.
     * @return The sensor type ID value object.
     */
    @Override
    public SensorTypeIDVO getSensorTypeID() {
        return sensorTypeIDVO;
    }


    /**
     * Retrieves the sensor ID value object of the sensor.
     * @return The sensor ID value object.
     */
    @Override
    public SensorIDVO getId() {
        return this.sensorIDVO;
    }
}
