package smarthome.repository;

import smarthome.domain.house.House;
import smarthome.vo.housevo.HouseIDVO;

public interface HouseRepository extends Repository<HouseIDVO, House> {
    House getFirstHouse();
    HouseIDVO getFirstHouseIDVO();
}
