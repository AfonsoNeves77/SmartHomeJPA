package smarthome.domain.house;

import smarthome.vo.housevo.LocationVO;

/**
 * Factory class for the House Entity.
 */

public class HouseFactoryImpl implements HouseFactory{

    /**
     * Method that creates a House Entity with the given locationVO.
     *
     * @param locationVO The locationVO of the house.
     * @return The House Entity if the locationVO is valid, null otherwise.
     */

    public House createHouse(LocationVO locationVO) {
        try {
            return new House(locationVO);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
