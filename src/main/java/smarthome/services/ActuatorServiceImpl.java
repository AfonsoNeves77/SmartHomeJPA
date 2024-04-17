package smarthome.services;

import smarthome.domain.actuator.Actuator;
import smarthome.domain.actuator.ActuatorFactory;
import smarthome.domain.device.Device;
import smarthome.repository.ActuatorRepository;
import smarthome.repository.ActuatorTypeRepository;
import smarthome.repository.DeviceRepository;
import smarthome.vo.actuatorvo.Settings;
import smarthome.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.vo.actuatorvo.ActuatorNameVO;
import smarthome.vo.devicevo.DeviceIDVO;

public class ActuatorServiceImpl implements ActuatorService{
    private final DeviceRepository deviceRepository;
    private final ActuatorTypeRepository actuatorTypeRepository;
    private final ActuatorFactory actuatorFactory;
    private final ActuatorRepository actuatorRepository;

    /**
     * Constructs an instance of V1ActuatorService with the provided dependencies.
     * @param actuatorFactory   The factory responsible for creating Actuators.
     * @param actuatorRepository The repository storing and retrieving Actuators.
     * @throws IllegalArgumentException if either factoryActuator or ActuatorRepository is null.
     */

    public ActuatorServiceImpl(DeviceRepository deviceRepository, ActuatorTypeRepository actuatorTypeRepository, ActuatorFactory actuatorFactory, ActuatorRepository actuatorRepository){
        if(!validParams(deviceRepository, actuatorTypeRepository, actuatorFactory, actuatorRepository)){
            throw new IllegalArgumentException("Invalid parameters");
        }
        this.deviceRepository = deviceRepository;
        this.actuatorTypeRepository = actuatorTypeRepository;
        this.actuatorFactory = actuatorFactory;
        this.actuatorRepository = actuatorRepository;
    }

    /**
     * This method is responsible for creating and persisting an actuator instance. It orchestrates the process by
     * interfacing with the actuator factory to create a new actuator object. The newly created actuator is then passed to the
     * actuator repository for storage. The method ultimately returns the result of the save operation from the actuator repository.
     * @param actuatorNameVO actuatorNameVO object
     * @param actuatorTypeIDVO actuatorTypeIDVO object
     * @param deviceIDVO deviceIDVO object
     * @param settings settings object
     * @return True or false
     */
    public boolean addActuator(ActuatorNameVO actuatorNameVO, ActuatorTypeIDVO actuatorTypeIDVO, DeviceIDVO deviceIDVO, Settings settings){
        if (isDeviceActive(deviceIDVO) && isActuatorTypePresent(actuatorTypeIDVO)){
            try {
                Actuator newActuator = actuatorFactory.createActuator(actuatorNameVO,actuatorTypeIDVO,deviceIDVO,settings);
                return actuatorRepository.save(newActuator);
            } catch (IllegalArgumentException e){
                return false;
            }
        }
        return false;
    }

    /**
     * Checks the status of the Device with the provided DeviceIDVO to determine if it is active.
     * This method queries the DeviceRepository to retrieve the Device based on the given ID.
     * If the Device is found and marked as active, it returns true, indicating the Device is active.
     * If the Device is not found or is not marked as active, it returns false.
     * @param deviceIDVO The Value Object representing the ID of the Device to check.
     * @return true if the Device is active, false otherwise or if the Device is not found.
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
     * Checks if an ActuatorType with the provided ActuatorTypeIDVO is present in the system.
     * This method queries the ActuatorTypeRepository to determine the presence of the ActuatorType.
     * @param actuatorTypeIDVO The Value Object representing the ID of the ActuatorType to check.
     * @return true if the ActuatorType is present in the system, false otherwise. It returns false if the ActuatorType
     * is not found or if the repository is unable to provide the information.
     */
    private boolean isActuatorTypePresent(ActuatorTypeIDVO actuatorTypeIDVO){
        return actuatorTypeRepository.isPresent(actuatorTypeIDVO);
    }

    /**
     * Checks if all parameters passed to the method are non-null.
     * This method ensures that all parameters provided to it are not null before proceeding with further operations.
     * While this function may not be considered type-safe, it is always used after ensuring type safety
     * by type-checking the variables of the calling method (compiler).
     *
     * @param params The parameters to be checked for nullity.
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
