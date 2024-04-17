package IntegrationTests;

import smarthome.controller.GetListOfDevicesCTRL;
import smarthome.domain.device.Device;
import smarthome.domain.device.DeviceFactoryImpl;
import smarthome.domain.room.Room;
import smarthome.domain.room.RoomFactoryImpl;
import smarthome.dto.DeviceDTO;
import smarthome.dto.RoomDTO;
import smarthome.repository.DeviceRepositoryMem;
import smarthome.repository.RoomRepositoryMem;
import smarthome.services.DeviceServiceImpl;
import smarthome.vo.devicevo.DeviceModelVO;
import smarthome.vo.devicevo.DeviceNameVO;
import smarthome.vo.housevo.HouseIDVO;
import smarthome.vo.roomvo.*;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

class GetListOfDevicesCTRLTest {

    /**
     * Test case to verify that when the RoomService parameter is null,
     * creating a GetListOfDevicesCTRL controller throws an IllegalArgumentException
     * with the expected error message.
     * The test arranges for a null RoomService, then tries to create a
     * GetListOfDevicesCTRL controller with this null parameter. It expects
     * an IllegalArgumentException to be thrown with the message "Invalid parameters."
     * @throws IllegalArgumentException if RoomService parameter is null
     */
    @Test
    void whenRoomServiceIsNull_thenThrowsIllegalArgumentException(){
        //Arrange
        String expected = "Invalid parameters.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new GetListOfDevicesCTRL(null));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test case to verify that when valid parameters are provided,
     * creating a GetListOfDevicesCTRL controller with the specified
     * DeviceService returns a non-null object.
     * The test sets up a memory repository, device repository, factory,
     * and service for devices. It then creates a GetListOfDevicesCTRL
     * controller with the device service. The test asserts that the
     * controller object is not null, indicating successful instantiation.
     */
    @Test
    void whenParametersValid_thenReturnsObject(){
        //Arrange
        RoomRepositoryMem roomRepositoryMem = new RoomRepositoryMem();
        DeviceRepositoryMem deviceRepository = new DeviceRepositoryMem();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        DeviceServiceImpl deviceService = new DeviceServiceImpl(roomRepositoryMem,deviceFactory,deviceRepository);

        //Act
        GetListOfDevicesCTRL getListOfDevicesCTRL = new GetListOfDevicesCTRL(deviceService);

        //Assert
        assertNotNull(getListOfDevicesCTRL);
    }

    /**
     * Test case to verify that when a room does not exist (not found),
     * calling getListOfDevices() on the GetListOfDevicesCTRL controller
     * with a RoomDTO representing that room returns an empty list of DeviceDTO.
     * The test sets up a RoomDTO for a room that does not exist in the memory repository,
     * then creates a new GetListOfDevicesCTRL controller with a DeviceService.
     * When calling getListOfDevices() with the non-existent room, it asserts
     * that the returned list of DeviceDTO objects is empty.
     */
    @Test
    void whenRoomDoesNotExist_thenReturnsAnEmptyList() {
        //Arrange
        RoomDTO roomDTO = new RoomDTO(UUID.randomUUID().toString(),"room1",2,3,3,2,UUID.randomUUID().toString());

        RoomRepositoryMem roomRepositoryMem = new RoomRepositoryMem();
        DeviceRepositoryMem deviceRepository = new DeviceRepositoryMem();
        DeviceFactoryImpl deviceFactory = new DeviceFactoryImpl();
        DeviceServiceImpl deviceService = new DeviceServiceImpl(roomRepositoryMem,deviceFactory,deviceRepository);

        GetListOfDevicesCTRL getListOfDevicesCTRL = new GetListOfDevicesCTRL(deviceService);

        List<DeviceDTO> expected = new ArrayList<>();
        //Act
        List<DeviceDTO> result = getListOfDevicesCTRL.getListOfDevices(roomDTO);

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test case to verify that when a room has no devices,
     * calling getListOfDevices() on the GetListOfDevicesCTRL controller
     * with the RoomDTO representing that room returns an empty list of DeviceDTO.
     * The test sets up a room with specific dimensions and properties but no devices,
     * then creates a GetListOfDevicesCTRL controller with a DeviceService.
     * When calling getListOfDevices() with this room, it asserts that the returned
     * list of DeviceDTO objects is empty.
     */
    @Test
    void ifRoomHasNoDevices_whenGetListOfDevicesIsCalled_thenReturnsEmptyList() {
        //Arrange
        RoomRepositoryMem roomRepositoryMem = new RoomRepositoryMem();

        double roomWidth = 10.0;
        RoomWidthVO roomWidthVO = new RoomWidthVO(roomWidth);
        double roomLength = 10.0;
        RoomLengthVO roomLengthVO = new RoomLengthVO(roomLength);
        double roomHeight = 10.0;
        RoomHeightVO roomHeightVO = new RoomHeightVO(roomHeight);
        RoomDimensionsVO roomDimensionsVO = new RoomDimensionsVO(roomLengthVO, roomWidthVO, roomHeightVO);

        String roomName = "room1";
        RoomNameVO roomNameVO = new RoomNameVO(roomName);
        int roomFloor = 1;
        RoomFloorVO roomFloorVO = new RoomFloorVO(roomFloor);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());

        Room room = new Room (roomNameVO, roomFloorVO, roomDimensionsVO, houseID);
        roomRepositoryMem.save(room);

        String roomID = room.getId().getID();
        String houseIDVO = "124e4567-e89b-12d3-a456-426614174000";

        RoomDTO roomDTO = new RoomDTO(roomID,roomName,roomFloor,roomHeight,roomLength,roomWidth,houseIDVO);

        DeviceFactoryImpl deviceFactoryImpl = new DeviceFactoryImpl();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(roomRepositoryMem, deviceFactoryImpl, deviceRepositoryMem);

        GetListOfDevicesCTRL getListOfDevicesCTRL = new GetListOfDevicesCTRL(deviceServiceImpl);

        //Act

        List<DeviceDTO> deviceDTOList = getListOfDevicesCTRL.getListOfDevices(roomDTO);

        //Assert
        assertTrue(deviceDTOList.isEmpty());
    }

    /**
     * Test case to verify that when a room has one device,
     * calling getListOfDevices() on the GetListOfDevicesCTRL controller
     * with the RoomDTO representing that room returns a list containing
     * the DeviceDTO for that device.
     * The test sets up a room with specific dimensions and properties,
     * then adds a single device to that room. It creates a GetListOfDevicesCTRL
     * controller with a DeviceService. When calling getListOfDevices() with
     * the room, it asserts that the returned list of DeviceDTO objects contains
     * the details of the device added to the room.
     */
    @Test
    void ifRoomHasOneDevice_whenGetListOfDevicesIsCalled_thenReturnsListOfDevicesDTO() {
        //Arrange
        RoomFactoryImpl roomFactoryImpl = new RoomFactoryImpl();
        RoomRepositoryMem roomRepositoryMem = new RoomRepositoryMem();

        RoomWidthVO roomWidthVO = new RoomWidthVO(10.0);
        RoomLengthVO roomLengthVO = new RoomLengthVO(10.0);
        RoomHeightVO roomHeightVO = new RoomHeightVO(10.0);
        RoomDimensionsVO roomDimensionsVO = new RoomDimensionsVO(roomLengthVO, roomWidthVO, roomHeightVO);

        RoomNameVO roomNameVO = new RoomNameVO("room1");
        RoomFloorVO roomFloorVO = new RoomFloorVO(1);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());

        Room room = roomFactoryImpl.createRoom(roomNameVO, roomFloorVO, roomDimensionsVO, houseID);
        roomRepositoryMem.save(room);


        String roomName = "room1";
        int roomFloor = 1;
        double roomWidth = 10.0;
        double roomLength = 10.0;
        double roomHeight = 10.0;
        String expectedRoomID = room.getId().getID();
        String houseIDVO = "124e4567-e89b-12d3-a456-426614174000";
        RoomDTO roomDTO = new RoomDTO(expectedRoomID,roomName,roomFloor,roomHeight,roomLength,roomWidth,houseIDVO);

        RoomIDVO roomIDVO = room.getId();

        String expectedDeviceName = "Fridge";
        DeviceNameVO deviceNameVO = new DeviceNameVO(expectedDeviceName);
        String expectedDeviceModel = "123QWE";
        DeviceModelVO deviceModelVO = new DeviceModelVO(expectedDeviceModel);

        DeviceFactoryImpl deviceFactoryImpl = new DeviceFactoryImpl();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();

        Device device = deviceFactoryImpl.createDevice(deviceNameVO, deviceModelVO, roomIDVO);
        deviceRepositoryMem.save(device);

        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(roomRepositoryMem, deviceFactoryImpl, deviceRepositoryMem);

        GetListOfDevicesCTRL getListOfDevicesCTRL = new GetListOfDevicesCTRL(deviceServiceImpl);

        int expectedSize = 1;
        List<DeviceDTO> deviceDTOList = getListOfDevicesCTRL.getListOfDevices(roomDTO);

        //Act
        String resultName = deviceDTOList.get(0).getDeviceName();
        String resultModel = deviceDTOList.get(0).getDeviceModel();
        String resultRoomID = deviceDTOList.get(0).getRoomID();
        int resultSize = deviceDTOList.size();
        String resultStatus = deviceDTOList.get(0).getDeviceStatus();

        //Assert
        assertEquals(expectedDeviceName, resultName);
        assertEquals(expectedDeviceModel, resultModel);
        assertEquals(expectedRoomID, resultRoomID);
        assertEquals(expectedSize, resultSize);
        assertEquals("true", resultStatus);
        }

    /**
     * Test case to verify that when a room has two devices,
     * calling getListOfDevices() on the GetListOfDevicesCTRL controller
     * with the RoomDTO representing that room returns a list containing
     * the DeviceDTOs for both devices.
     * The test sets up a room with specific dimensions and properties,
     * then adds two devices to that room. It creates a GetListOfDevicesCTRL
     * controller with a DeviceService. When calling getListOfDevices() with
     * the room, it asserts that the returned list of DeviceDTO objects contains
     * the details of both devices added to the room, including device names,
     * models, statuses, and IDs.
     */
    @Test
    void ifRoomHasTwoDevices_whenGetListOfDevicesIsCalled_thenReturnsListOfDevicesDTO() {
        //Arrange
        RoomRepositoryMem roomRepositoryMem = new RoomRepositoryMem();

        RoomWidthVO roomWidthVO = new RoomWidthVO(10.0);
        RoomLengthVO roomLengthVO = new RoomLengthVO(10.0);
        RoomHeightVO roomHeightVO = new RoomHeightVO(10.0);
        RoomDimensionsVO roomDimensionsVO = new RoomDimensionsVO(roomLengthVO, roomWidthVO, roomHeightVO);

        RoomNameVO roomNameVO = new RoomNameVO("quarto");
        RoomFloorVO roomFloorVO = new RoomFloorVO(1);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());

        Room room =  new Room(roomNameVO, roomFloorVO, roomDimensionsVO, houseID);
        roomRepositoryMem.save(room);

        String roomName = "quarto";
        int roomFloor = 1;
        double roomWidth = 10.0;
        double roomLength = 10.0;
        double roomHeight = 10.0;
        String roomID = room.getId().getID();
        String houseIDVO = "124e4567-e89b-12d3-a456-426614174000";
        RoomDTO roomDTO = new RoomDTO(roomID,roomName,roomFloor,roomHeight,roomLength,roomWidth,houseIDVO);

        RoomIDVO roomIDVO = new RoomIDVO(UUID.fromString(roomID));

        String expectedName1 = "Fridge";
        DeviceNameVO deviceName1VO = new DeviceNameVO(expectedName1);
        String expectedModel1 = "123QWE";
        DeviceModelVO deviceModel1VO = new DeviceModelVO(expectedModel1);

        String expectedName2 = "AirConditioner";
        DeviceNameVO deviceNameVO2 = new DeviceNameVO(expectedName2);
        String expectedModel2 = "456QWE";
        DeviceModelVO deviceModelVO2 = new DeviceModelVO(expectedModel2);

        DeviceFactoryImpl deviceFactoryImpl = new DeviceFactoryImpl();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();

        Device device = new Device(deviceName1VO, deviceModel1VO, roomIDVO);
        Device device2 = new Device(deviceNameVO2,deviceModelVO2,roomIDVO);
        deviceRepositoryMem.save(device);
        deviceRepositoryMem.save(device2);

        String expectedID1 = device.getId().getID();
        String expectedID2 = device2.getId().getID();

        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(roomRepositoryMem, deviceFactoryImpl, deviceRepositoryMem);

        GetListOfDevicesCTRL getListOfDevicesCTRL = new GetListOfDevicesCTRL(deviceServiceImpl);

        int expectedSize = 2;

        //Act
        List<DeviceDTO> deviceDTOList = getListOfDevicesCTRL.getListOfDevices(roomDTO);
        String resultName = deviceDTOList.get(0).getDeviceName();
        String resultModel = deviceDTOList.get(0).getDeviceModel();
        String resultStatus = deviceDTOList.get(0).getDeviceStatus();
        String resultDeviceID = deviceDTOList.get(0).getDeviceID();

        String resultName2 = deviceDTOList.get(1).getDeviceName();
        String resultModel2 = deviceDTOList.get(1).getDeviceModel();
        String resultStatus2 = deviceDTOList.get(1).getDeviceStatus();
        String resultDeviceID2 = deviceDTOList.get(1).getDeviceID();

        //Assert
        assertEquals(expectedName1, resultName);
        assertEquals(expectedModel1, resultModel);
        assertEquals("true", resultStatus);
        assertEquals(expectedID1, resultDeviceID);

        assertEquals(expectedName2, resultName2);
        assertEquals(expectedModel2, resultModel2);
        assertEquals("true", resultStatus2);
        assertEquals(expectedID2, resultDeviceID2);

        assertEquals(expectedSize, deviceDTOList.size());
    }
}