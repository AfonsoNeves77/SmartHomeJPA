package smarthome.domain.sensor;

import smarthome.domain.sensor.externalservices.SensorExternalServices;
import smarthome.domain.sensor.values.EnergyConsumptionValue;
import smarthome.vo.ValueObject;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.sensortype.SensorTypeIDVO;
import smarthome.vo.sensorvo.SensorIDVO;
import smarthome.vo.sensorvo.SensorNameVO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class EnergyConsumptionSensor implements Sensor {
    private SensorNameVO name;
    private final DeviceIDVO deviceID;
    private final SensorTypeIDVO sensorTypeID;
    private final SensorIDVO energyConsumptionSensorID;

    /**
     * Constructor for EnergyConsumptionSensor
     * @param name Sensor name
     * @param deviceID DeviceDIVO
     * @param sensorTypeID SensorTypeID
     */
    public EnergyConsumptionSensor(SensorNameVO name, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID) {
        if (!validateParameters(name, deviceID, sensorTypeID)) {
            throw new IllegalArgumentException("Invalid parameters.");
        }

        this.energyConsumptionSensorID = new SensorIDVO(UUID.randomUUID());
        this.name = name;
        this.sensorTypeID = sensorTypeID;
        this.deviceID = deviceID;
    }

    /**
     * Method to validate the parameters
     * @param name Sensor name
     * @param deviceID DeviceDIVO
     * @param sensorTypeID SensorTypeID
     * @return boolean
     */
    private boolean validateParameters(SensorNameVO name, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID) {
        return name != null && deviceID != null && sensorTypeID != null;
    }


    @Override
    public SensorIDVO getId() {
        return this.energyConsumptionSensorID;
    }

    @Override
    public SensorNameVO getSensorName() {
        return this.name;
    }

    @Override
    public SensorTypeIDVO getSensorTypeID() {
        return this.sensorTypeID;
    }

    @Override
    public DeviceIDVO getDeviceID() {
        return this.deviceID;
    }

    /**
     * Method to get the reading
     * @param initialDate Initial date
     * @param finalDate Final date
     * @param simHardware External Service
     * @return ValueObject<Integer>
     * @throws InstantiationException if reading invalid
     */
    public ValueObject<Integer> getReading(String initialDate, String finalDate, SensorExternalServices simHardware) throws InstantiationException {
        if (!validateDates(initialDate, finalDate)) {
            throw new IllegalArgumentException("Invalid date");
        }
        if (simHardware == null) {
            throw new IllegalArgumentException("Invalid external service");
        }
        String simValue = simHardware.getValue(initialDate, finalDate);
        return new EnergyConsumptionValue(simValue);
    }

    /**
     * Parses the initial date string into LocalDateTime object.
     *
     * @param initialDate Initial date string
     * @return LocalDateTime object representing the initial date
     */
    private LocalDateTime getInitialDateTime(String initialDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.parse(initialDate, formatter);

    }

    /**
     * Parses the final date string into LocalDateTime object.
     *
     * @param finalDate Final date string
     * @return LocalDateTime object representing the final date
     */
    private LocalDateTime getFinalDateTime(String finalDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.parse(finalDate, formatter);
    }

    /**
     * Validates the date format and checks if the initial date is before the final date and both are before the current time.
     *
     * @param initialDate Initial date string
     * @param finalDate   Final date string
     * @return true if the date format is valid and initial date is before final date and both are before the current time, false otherwise
     */
    private boolean validateDates(String initialDate, String finalDate) {
        LocalDateTime initialDateTime;
        LocalDateTime finalDateTime;
        try{
            initialDateTime = getInitialDateTime(initialDate);
            finalDateTime = getFinalDateTime(finalDate);
        } catch(DateTimeParseException e) {
            return false;
        }
        return initialDate != null && initialDateTime.isBefore(finalDateTime) && finalDateTime.isBefore(LocalDateTime.now());
    }
}