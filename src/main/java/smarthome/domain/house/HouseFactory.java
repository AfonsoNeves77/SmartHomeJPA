package smarthome.domain.house;

import smarthome.vo.housevo.LocationVO;

public interface HouseFactory {
    House createHouse(LocationVO locationVO);
}
