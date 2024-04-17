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

    //Not mandatory to create anotations for each column

    @Id
    private String id;
    @Column(name = "custom_name")
    private String name;
    private int floor;
    private double length;
    private double width;
    private double height;
    @Embedded
    private HouseIDVO houseID;
    @Version
    private Long version;

    public RoomDataModel() {
    }

    public RoomDataModel(Room room) {
        this.id = room.getId().getID();
        this.name = room.getRoomName().getValue();
        this.floor = room.getFloor().getValue();
        this.height = room.getRoomDimensions().getRoomHeight();
        this.width = room.getRoomDimensions().getRoomWidth();
        this.length = room.getRoomDimensions().getRoomLength();
        this.houseID = room.getHouseID();
    }

    static public Room toDomain(RoomFactory roomFactory, RoomDataModel roomDataModel) {

        RoomIDVO roomIDVO = new RoomIDVO(UUID.fromString(roomDataModel.id));
        RoomNameVO roomNameVO = new RoomNameVO(roomDataModel.name);
        RoomFloorVO roomFloorVO = new RoomFloorVO(roomDataModel.floor);

        RoomLengthVO roomLengthVO = new RoomLengthVO(roomDataModel.length);
        RoomWidthVO roomWidthVO = new RoomWidthVO(roomDataModel.width);
        RoomHeightVO roomHeightVO = new RoomHeightVO(roomDataModel.height);

        RoomDimensionsVO roomDimensionsVO = new RoomDimensionsVO(roomLengthVO,roomWidthVO,roomHeightVO);
        HouseIDVO houseIDVO = roomDataModel.houseID;

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
