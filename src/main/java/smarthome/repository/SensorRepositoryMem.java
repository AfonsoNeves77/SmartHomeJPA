package smarthome.repository;

import smarthome.domain.sensor.Sensor;
import smarthome.vo.sensorvo.SensorIDVO;

import java.util.LinkedHashMap;

public class SensorRepositoryMem implements SensorRepository {

    private final LinkedHashMap<SensorIDVO, Sensor> sensorMap = new LinkedHashMap<>();

    /**
     * @param sensor Entity to be saved
     * @return True or false
     */
    @Override
    public boolean save(Sensor sensor) {
        if(sensor == null || sensor.getId() == null || isPresent((SensorIDVO) sensor.getId())){
            return false;
        }
        sensorMap.put((SensorIDVO) sensor.getId(), sensor);
        return true;
    }

    /**
     * This method extracts all instances of Sensor that were previously saved.
     * @return An interable with the previously saved Sensor classes
     */
    @Override
    public Iterable<Sensor> findAll() {
        return sensorMap.values();
    }

    /**
     * This method returns an instance of Sensor, given a matching SensorIDVO. If ID is not a present key, returns null
     * @param id SensorIDVO object
     * @return Returns the Sensor object if key found, or null otherwise
     */
    @Override
    public Sensor findById(SensorIDVO id) {
        if(!isPresent(id)){
            return null;
        }
        return sensorMap.get(id);
    }

    /**
     * Verifies if there is a matching sensorIDVO as key
     * @param id SensorIDVO object
     * @return True or false
     */
    @Override
    public boolean isPresent(SensorIDVO id) {
        return sensorMap.containsKey(id);
    }
}
