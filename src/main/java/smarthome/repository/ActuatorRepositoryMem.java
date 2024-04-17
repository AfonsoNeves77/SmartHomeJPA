package smarthome.repository;

import smarthome.domain.actuator.Actuator;
import smarthome.vo.actuatorvo.ActuatorIDVO;

import java.util.LinkedHashMap;

public class ActuatorRepositoryMem implements ActuatorRepository{

    private final LinkedHashMap<ActuatorIDVO, Actuator> actuatorMap = new LinkedHashMap<>();

    /**
     * Saves a new actuator in the repository.
     * This method is mandatory, implemented from Repository interface.
     * @param actuator Actuator to be saved
     * @return True if actuator is valid and does not already exist in the repository
     */
    @Override
    public boolean save(Actuator actuator) {
        if(validActuator(actuator)){
            this.actuatorMap.put((ActuatorIDVO) actuator.getId(), actuator);
            return true;
        }
        return false;
    }

    /**
     * Retrieves all Actuators (Values) stored in the actuatorsMap.
     * This method is mandatory, implemented from Repository interface.
     * @return An iterable collection of Actuator objects
     */
    @Override
    public Iterable<Actuator> findAll() {
        return actuatorMap.values();
    }

    /**
     * Retrieves the Actuator (Value) that corresponds to the requested ID (Key) from the actuatorMap.
     * This method is mandatory, implemented from Repository interface.
     * @param id Actuator ID (Key)
     * @return Actuator Object
     */
    @Override
    public Actuator findById(ActuatorIDVO id) {
        return actuatorMap.get(id);
    }

    /**
     * Verifies whether there is an entry in the actuatorsMap for the requested ID (Key).
     * This method is mandatory, implemented from Repository interface.
     * @param id Actuator ID (Key)
     * @return True if there is already an entry for the requested ID, otherwise false
     */
    @Override
    public boolean isPresent(ActuatorIDVO id) {
        return actuatorMap.containsKey(id);
    }

    /**
     * Verifies if Actuator is valid to be previously saved.
     * @param actuator Actuator to verify
     * @return True if Actuator is not null and if it is not already stored in the repository
     */
    private boolean validActuator(Actuator actuator){
        if(actuator == null)
            return false;
        return !isPresent((ActuatorIDVO) actuator.getId());
    }
}
