package smarthome.services;

import smarthome.vo.housevo.LocationVO;

public interface HouseService {
    boolean addHouse(LocationVO locationVO);
    boolean updateLocation(LocationVO locationVO);
}
