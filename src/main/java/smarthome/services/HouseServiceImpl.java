package smarthome.services;

import smarthome.domain.house.House;
import smarthome.domain.house.HouseFactory;
import smarthome.repository.HouseRepository;
import smarthome.vo.housevo.LocationVO;


/**
 * Service class for House. It contains methods to create a new House, update the location of the House and get the first House ID.
 * It throws an IllegalArgumentException if the HouseRepository or HouseFactory objects are null.
 */

public class HouseServiceImpl implements HouseService{

    private final HouseRepository houseRepository;
    private final HouseFactory houseFactory;

    /**
     * Constructor for HouseService class.
     * It throws an IllegalArgumentException if the HouseRepository or HouseFactory objects are null.
     * It initializes the HouseRepository and HouseFactory objects.
     * @param houseRepository HouseRepository object
     * @param houseFactory HouseFactory object
     */
    public HouseServiceImpl(HouseRepository houseRepository, HouseFactory houseFactory) {
        if (houseRepository == null || houseFactory == null) {
            throw new IllegalArgumentException("Invalid parameters");
        } else {
            this.houseRepository = houseRepository;
            this.houseFactory = houseFactory;
        }
    }

    /**
     * Method to create a new House object.
     * It throws an IllegalArgumentException if the LocationVO object is null.
     * It calls the createHouse method of the HouseFactory object to create a new House object.
     * @param locationVO LocationVO object
     * @return House object
     */
    public boolean addHouse(LocationVO locationVO) {
        if(locationVO == null){
            throw new IllegalArgumentException("Invalid location");
        }
        try{
            House house = houseFactory.createHouse(locationVO);
            return houseRepository.save(house);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Method to update the location of the House.
     * It throws an IllegalArgumentException if the LocationVO object is null.
     * It calls the configureLocation method of the House object to update the location.
     * @param locationVO LocationVO object
     * @return boolean
     */
    public boolean updateLocation(LocationVO locationVO) {
        if (locationVO == null) {
            throw new IllegalArgumentException("Invalid location");
        }
        House house = getFirstHouse();
        if (house == null) {
            return false;
        } else {
            return house.configureLocation(locationVO);
        }
    }

    /**
     * Method to get the first House object.
     * It calls the getFirstHouse method of the HouseRepository object to get the first House object.
     * @return House object
     */
    private House getFirstHouse() {
        return houseRepository.getFirstHouse();
    }
}