package smarthome.services;

import smarthome.domain.actuator.Actuator;
import smarthome.domain.device.Device;
import smarthome.domain.sensor.Sensor;
import smarthome.repository.ActuatorRepository;
import smarthome.repository.DeviceRepository;
import smarthome.repository.SensorRepository;
import smarthome.vo.devicevo.DeviceIDVO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QueryServiceImpl implements QueryService{
    private final SensorRepository sensorRepository;
    private final DeviceRepository deviceRepository;
    private final ActuatorRepository actuatorRepository;

    /**
     * Constructor for memory query service. It receives a device repository, sensor repository and actuator repository
     * implementations.
     * @param deviceRepository Device repository implementation
     * @param sensorRepository Sensor repository implementation
     * @param actuatorRepository actuator repository implementation
     */
    public QueryServiceImpl(DeviceRepository deviceRepository, SensorRepository sensorRepository, ActuatorRepository actuatorRepository){
        if(!validParams(deviceRepository,sensorRepository,actuatorRepository)){
            throw new IllegalArgumentException("Invalid parameters");
        }
        this.deviceRepository = deviceRepository;
        this.sensorRepository = sensorRepository;
        this.actuatorRepository = actuatorRepository;
    }

    /**
     * This method obtains a list of devices by functionality. The return format is: String (related to the type) as key,
     * and a list of Device objects as values.
     * @return Map String to List of Devices
     */
    public Map<String, List<Device>> getListOfDeviceByFunctionality(){
        LinkedHashMap<String,List<DeviceIDVO>> sensorMap = getMapDeviceIDBySensorType();
        LinkedHashMap<String,List<DeviceIDVO>> actuatorMap = getMapDeviceIDBySensorAndActuatorType();
        LinkedHashMap<String,List<DeviceIDVO>> sensorActuatorMap = mergeMaps(sensorMap,actuatorMap);
        return getDevices(sensorActuatorMap);
    }

    /**
     * This method leverages the sensor repository implementation to retrieve a comprehensive list of sensors. Subsequently,
     * it iterates through each sensor object, extracting their sensorTypeID and deviceID. These sensorTypeIDs are then mapped
     * as keys in a map structure. For each unique sensorTypeID, a corresponding list of DeviceIDs is created, ensuring avoidance
     * of duplicate deviceIDs per sensorTypeID. The method's return value is of type Map, where each key corresponds to a sensorTypeID,
     * and its value is a list of DeviceIDVO objects representing the devices associated with that sensorTypeID.
     */
    private LinkedHashMap<String,List<DeviceIDVO>> getMapDeviceIDBySensorType(){
        Iterable<Sensor> sensorList = sensorRepository.findAll();
        LinkedHashMap<String,List<DeviceIDVO>> map = new LinkedHashMap<>();
        for (Sensor sensor : sensorList){
            String type = sensor.getSensorTypeID().getID();
            DeviceIDVO deviceID = sensor.getDeviceID();
            updateMap(map,type,deviceID);
        }
        return map;
    }

    /**
     * This method utilizes the actuator repository implementation to retrieve a complete list of actuators. It subsequently
     * iterates through each actuator object, extracting their ctuatorTypeID and deviceID. These actuatorTypeIDs are then
     * mapped as keys in a map structure. For each unique actuatorTypeID, a corresponding list of DeviceIDs is created,
     * ensuring avoidance of duplicate deviceIDs per actuatorTypeID. The method's return value is a Map where each key
     * represents an actuatorTypeID, and its value is a list of DeviceIDVO objects each representing a device associated
     * with that actuatorTypeID.
     */
    private LinkedHashMap<String,List<DeviceIDVO>> getMapDeviceIDBySensorAndActuatorType(){
        Iterable<Actuator> actuatorList = actuatorRepository.findAll();
        LinkedHashMap<String,List<DeviceIDVO>> map = new LinkedHashMap<>();
        for (Actuator actuator : actuatorList){
            String type = actuator.getActuatorTypeID().getID();
            DeviceIDVO deviceID = actuator.getDeviceID();
            updateMap(map,type,deviceID);
        }
        return map;
    }

    /**
     * This method receives two hashmaps with the same key value types and returns a merged map.
     * @param map1 First map
     * @param map2 Second map
     * @return Map containing the merged key+value pairs of each map
     */
    private LinkedHashMap<String,List<DeviceIDVO>> mergeMaps (LinkedHashMap<String,List<DeviceIDVO>> map1, LinkedHashMap<String,List<DeviceIDVO>> map2){
        LinkedHashMap<String,List<DeviceIDVO>> newMap = new LinkedHashMap<>();
        newMap.putAll(map1);
        newMap.putAll(map2);
        return newMap;
    }
    /**
     * This method takes a Map with keys of type String and values of type List of DeviceIDVO. It then employs the
     * deviceRepository recursively to associate each DeviceIDVO object with its corresponding Device object. The resultant
     * Map mirrors the structure of the input, with Device objects replacing the original DeviceIDVO objects at each
     * corresponding key.
     * @param map Map <String,List<DeviceIDVO>>
     * @return LinkedHashMap with keys of type String and values of type List containing Device objects.
     */
    private LinkedHashMap<String, List<Device>> getDevices (Map<String, List<DeviceIDVO>> map) {
        LinkedHashMap<String, List<Device>> newMap = new LinkedHashMap<>();
        for (String key : map.keySet()) {
            List<DeviceIDVO> idList = map.get(key);
            List<Device> deviceList = new ArrayList<>();
            for (DeviceIDVO id : idList) {
                Device device = this.deviceRepository.findById(id);
                deviceList.add(device);
            }
            newMap.put(key, deviceList);
        }
        return newMap;
    }

    /**
     * Updates a LinkedHashMap with keys of type String and values of type List containing DeviceIDVO objects.
     * If the map already contains the specified type as a key, the provided DeviceIDVO object is added to the corresponding list,
     * if it's not already present. If the type is not found in the map, a new entry is created with the type as the key and
     * a new List containing the provided DeviceIDVO object.
     * @param map The LinkedHashMap to update.
     * @param type The type (String) representing the key in the map.
     * @param deviceID The DeviceIDVO object to add to the map.
     * @return The updated LinkedHashMap with keys of type String and values of type List containing DeviceIDVO objects.
     */
    private LinkedHashMap<String,List<DeviceIDVO>> updateMap (LinkedHashMap<String,List<DeviceIDVO>> map, String type, DeviceIDVO deviceID){
        if (map.containsKey(type)){
            List<DeviceIDVO> devicesPerKey = map.get(type);
            if(!devicesPerKey.contains(deviceID)){
                devicesPerKey.add(deviceID);
            }
        } else {
            List<DeviceIDVO> devicesPerKey = new ArrayList<>();
            devicesPerKey.add(deviceID);
            map.put(type,devicesPerKey);
        }
        return map;
    }

    /**
     * Checks if the provided parameters are all non-null.
     * @param params Variable number of parameters of type Object to check.
     * @return true if all parameters are non-null, false otherwise.
     */
    private boolean validParams (Object... params){
        for (Object param : params){
            if (param == null){
                return false;
            }
        }
        return true;
    }
}
