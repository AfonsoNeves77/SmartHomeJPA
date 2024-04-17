package smarthome.domain.house;

import smarthome.domain.DomainEntity;
import smarthome.vo.housevo.HouseIDVO;
import smarthome.vo.housevo.LocationVO;

import java.util.UUID;

public class House  implements DomainEntity {
    private final HouseIDVO houseID;
    private LocationVO location;

    /**
     * Constructor for HouseEntity.
     * @param locationVO The locationVO of the house.
     */
    public House(LocationVO locationVO) {
        if (locationVO == null) {
            throw new IllegalArgumentException("Invalid House Entity parameters.");
        }
        this.houseID = new HouseIDVO(UUID.randomUUID());
        this.location = locationVO;
    }

    /**
     * Configures the location of the house.
     * @param locationVO The location of the house.
     * @return True if the location is valid and was configured, false otherwise.
     */
    public boolean configureLocation(LocationVO locationVO) {
        if (locationVO == null) {
            return false;
        }
        this.location = locationVO;
        return true;
    }

    public LocationVO getLocation() {
        return this.location;
    }

    @Override
    public HouseIDVO getId() {
        return this.houseID;
    }
}