package smarthome.services;

import smarthome.domain.device.Device;
import smarthome.domain.sensor.Sensor;
import smarthome.domain.sensor.SensorFactory;
import smarthome.repository.DeviceRepository;
import smarthome.repository.SensorRepository;
import smarthome.repository.SensorTypeRepository;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.sensortype.SensorTypeIDVO;
import smarthome.vo.sensorvo.SensorNameVO;

public class SensorServiceImpl implements SensorService{
    private final DeviceRepository deviceRepository;
    private final SensorTypeRepository sensorTypeRepository;
    private final SensorRepository sensorRepository;
    private final SensorFactory sensorFactory;

    /**
     * Constructor for the SensorService class. It receives a DeviceRepository to validate device IDs, a SensorTypeRepository
     * to validate sensor type IDs, a SensorFactory to create sensor objects and a SensorRepository to save and manage sensor objects.
     * @param sensorRepository Sensor Repository Object
     * @param sensorFactory Sensor Factory Object
     */
    public SensorServiceImpl(DeviceRepository deviceRepository, SensorTypeRepository sensorTypeRepository, SensorRepository sensorRepository, SensorFactory sensorFactory) {
        if (areParamsNull(sensorRepository, sensorTypeRepository, sensorFactory, deviceRepository)){
            throw new IllegalArgumentException("Invalid repository");
        }
        this.sensorTypeRepository = sensorTypeRepository;
        this.deviceRepository = deviceRepository;
        this.sensorRepository = sensorRepository;
        this.sensorFactory = sensorFactory;
    }

    /**
     * This method is responsible for creating and persisting a sensor instance. It orchestrates the process by
     * interfacing with the sensor factory to create a new sensor object. The newly created sensor is then passed to the
     * sensor repository for storage. The method ultimately returns the result of the save operation from the sensor repository.
     * @param sensorName SensorNameIDVO object
     * @param deviceIDVO DeviceIDVO object
     * @param sensorTypeIDVO SensorTypeIDVO object
     * @return True or false
     */
    public boolean addSensor(SensorNameVO sensorName, DeviceIDVO deviceIDVO, SensorTypeIDVO sensorTypeIDVO){
        if(isDeviceActive(deviceIDVO) && isSensorTypePresent(sensorTypeIDVO)){
            try{
                Sensor newSensor = sensorFactory.createSensor(sensorName,deviceIDVO,sensorTypeIDVO);
                return sensorRepository.save(newSensor);
            } catch (IllegalArgumentException e){
                return false;
            }
        }
        return false;
    }

    /**
     * Checks if the Device with the provided DeviceIDVO is active.
     * This method retrieves the Device from the system using the DeviceIDVO.
     * @param deviceIDVO The Value Object representing the ID of the Device to check.
     * @return true if the Device is active, false otherwise. It returns false if the Device is not found or if the
     * repository is unable to provide the information.
     */
    private boolean isDeviceActive(DeviceIDVO deviceIDVO){
         try{
             Device device = deviceRepository.findById(deviceIDVO);
             return device.isActive();
         } catch (NullPointerException e){
             return false;
         }
    }

    /**
     * Checks if a SensorType with the provided SensorTypeIDVO is present in the system.
     * This method queries the SensorTypeRepository to determine the presence of the specified SensorType.
     * @param sensorTypeIDVO The Value Object representing the ID of the SensorType to check.
     * @return true if the SensorType is present in the system, false otherwise. It returns false if the SensorType is
     * not found or if the repository is unable to provide the information.
     */
    private boolean isSensorTypePresent (SensorTypeIDVO sensorTypeIDVO){
        return sensorTypeRepository.isPresent(sensorTypeIDVO);
    }


    /**
     * Receives object parameters and verifies they are null
     * @param params Any object parameter.
     * @return True or false
     */
    private boolean areParamsNull (Object... params){
        for (Object param : params){
            if (param == null){
                return true;
            }
        }
        return false;
    }
}
