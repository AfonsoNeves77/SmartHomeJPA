package smarthome.domain.room;


import jakarta.persistence.*;
import smarthome.vo.housevo.HouseIDVO;
import smarthome.vo.roomvo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Room")
public class RoomDataModel {

    @Id
    private String id;
    private String name;
    private String floor;
    private String length;
    private String width;
    private String height;
    private String houseID;
    @Version
    private Long version;

    public RoomDataModel() {
    }

    public RoomDataModel(Room room) {
        this.id = room.getId().getID();
        this.name = room.getRoomName().getValue();
        this.floor = room.getFloor().getValue().toString();
        this.height = room.getRoomDimensions().getRoomHeight().toString();
        this.width = room.getRoomDimensions().getRoomWidth().toString();
        this.length = room.getRoomDimensions().getRoomLength().toString();
        this.houseID = room.getHouseID().getID();
    }

    static public Room toDomain(RoomFactory roomFactory, RoomDataModel roomDataModel) {

        RoomIDVO roomIDVO = new RoomIDVO(UUID.fromString(roomDataModel.id));
        RoomNameVO roomNameVO = new RoomNameVO(roomDataModel.name);
        RoomFloorVO roomFloorVO = new RoomFloorVO(Integer.parseInt(roomDataModel.floor));

        RoomLengthVO roomLengthVO = new RoomLengthVO(Double.parseDouble(roomDataModel.length));
        RoomWidthVO roomWidthVO = new RoomWidthVO(Double.parseDouble(roomDataModel.width));
        RoomHeightVO roomHeightVO = new RoomHeightVO(Double.parseDouble(roomDataModel.height));

        RoomDimensionsVO roomDimensionsVO = new RoomDimensionsVO(roomLengthVO,roomWidthVO,roomHeightVO);
        HouseIDVO houseIDVO = new HouseIDVO(UUID.fromString(roomDataModel.houseID));

        return roomFactory.createRoom(roomIDVO,roomNameVO,roomFloorVO,roomDimensionsVO,houseIDVO);
    }

    static public Iterable<Room> toDomain(RoomFactory roomFactory, Iterable<RoomDataModel> roomDataModelList) {

       List<Room> rooms = new ArrayList<>();
       roomDataModelList.forEach(roomDataModel -> {
           Room room = toDomain(roomFactory, roomDataModel);
           rooms.add(room);
       });

       return rooms;
    }

    public boolean updateFromDomain(Room room)
    {
        //Just the name as an example
        this.name = room.getRoomName().getValue();
        return true;
    }

}
