package smarthome;

import smarthome.domain.room.Room;
import smarthome.domain.room.RoomDataModel;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.repository.HouseRepository;
import smarthome.repository.HouseRepositoryMem;
import smarthome.repository.jpa.RoomRepositoryJPAImpl;
import smarthome.services.RoomService;
import smarthome.services.RoomServiceImpl;
import smarthome.vo.housevo.HouseIDVO;
import smarthome.vo.roomvo.*;

import java.util.UUID;

;

public class Main {

        public static void main(String[] args) {

                String roomName = "bedRoom";
                int floor = 2;
                double roomHeight = 2.2;
                double roomLength = 4.5;
                double roomWidth = 5.0;

                RoomNameVO roomNameVO = new RoomNameVO(roomName);
                RoomFloorVO roomFloorVO = new RoomFloorVO(floor);

                RoomLengthVO roomLengthVO = new RoomLengthVO(roomLength);
                RoomWidthVO roomWidthVO = new RoomWidthVO(roomWidth);
                RoomHeightVO roomHeightVO = new RoomHeightVO(roomHeight);

                RoomDimensionsVO roomDimensionsVO = new RoomDimensionsVO(roomLengthVO,roomWidthVO,roomHeightVO);
                HouseIDVO houseIDVO = new HouseIDVO(UUID.randomUUID());

                RoomFactoryImpl roomFactory = new RoomFactoryImpl();

                Room room = roomFactory.createRoom(roomNameVO,roomFloorVO,roomDimensionsVO,houseIDVO);
                Room room1 = roomFactory.createRoom(new RoomNameVO("SearchRoom"),roomFloorVO,roomDimensionsVO,houseIDVO);
                Room room2 = roomFactory.createRoom(new RoomNameVO("Room3"),roomFloorVO,roomDimensionsVO,houseIDVO);

                RoomDataModel roomDataModel = new RoomDataModel(room);

                System.out.println(roomDataModel);

                System.out.println(RoomDataModel.toDomain(roomFactory,roomDataModel).getRoomDimensions().getRoomHeight());

                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7

                RoomRepositoryJPAImpl roomRepositoryJPA = new RoomRepositoryJPAImpl(roomFactory);

                System.out.println(roomRepositoryJPA.save(room));
                System.out.println(roomRepositoryJPA.save(room1));
                System.out.println(roomRepositoryJPA.save(room2));

                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                System.out.println(roomRepositoryJPA.findAll().toString());

                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////7

                RoomIDVO roomIDVO = room1.getId();
                System.out.println(roomIDVO.getID());
                System.out.println(roomRepositoryJPA.findById(roomIDVO));


                ///////////////////////////////////////////////////////////////////////////////////////////////////////

                System.out.println("Aqui deve tar true");

                System.out.println(roomRepositoryJPA.isPresent(roomIDVO));

                System.out.println("Aqui deve dar false");

                RoomIDVO randomRoomID = new RoomIDVO(UUID.randomUUID());
                System.out.println(roomRepositoryJPA.isPresent(randomRoomID));


                ////////////////////////////////////////////////////////////////////////////////////////

                HouseRepository houseRepository = new HouseRepositoryMem();
                RoomServiceImpl roomService = new RoomServiceImpl(houseRepository,roomRepositoryJPA,roomFactory);

                RoomNameVO roomNameVOChanged = new RoomNameVO("NewName");
                RoomNameVO resultRoomName = roomService.updateName(roomIDVO,roomNameVOChanged);

                System.out.println(resultRoomName.getValue());

                /////////////////////////////////////////////////////////////////////////////////////








        }
}
