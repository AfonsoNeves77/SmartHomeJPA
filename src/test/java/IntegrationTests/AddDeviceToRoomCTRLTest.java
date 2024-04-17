package IntegrationTests;

import smarthome.controller.AddDeviceToRoomCTRL;
import smarthome.domain.device.Device;
import smarthome.domain.device.DeviceFactoryImpl;
import smarthome.domain.room.Room;
import smarthome.dto.DeviceDTO;
import smarthome.dto.RoomDTO;
import smarthome.repository.DeviceRepositoryMem;
import smarthome.repository.RoomRepositoryMem;
import smarthome.services.DeviceServiceImpl;
import smarthome.vo.housevo.HouseIDVO;
import smarthome.vo.roomvo.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * NOTE: RoomDTO and DeviceDTO attributes were not accessed in the controller tests, since all Mappers ensure they cannot
 * be invalid and used as so. Exceptions would be thrown and caught when appropriate.
 */
class AddDeviceToRoomCTRLTest {

    /**
     * Test method to verify that when valid data is provided to the AddDeviceToRoomCTRL's addDeviceToRoom method,
     * it returns true, indicating successful addition of the device to the room.
     * This test ensures that the controller correctly adds a new device to a room when valid data is provided.
     */
    @Test
    void givenValidData_WhenAddDeviceToRoom_ThenShouldReturnTrue(){
        // Arrange
        // arranges Room repository, creates Room, saves Room to repository
        RoomRepositoryMem roomRepositoryMem = new RoomRepositoryMem();

        RoomNameVO name = new RoomNameVO("Office");
        RoomFloorVO floor = new RoomFloorVO(2);
        RoomWidthVO width = new RoomWidthVO(3.5);
        RoomLengthVO length = new RoomLengthVO(3);
        RoomHeightVO height = new RoomHeightVO(2);
        RoomDimensionsVO dimensions = new RoomDimensionsVO(length, width, height);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());
        Room room = new Room (name,floor,dimensions,houseID);

        roomRepositoryMem.save(room);

        // arranges Device Service
        DeviceFactoryImpl deviceFactoryImpl = new DeviceFactoryImpl();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(roomRepositoryMem, deviceFactoryImpl, deviceRepositoryMem);

        // arranges RoomDTO. String IDs must be obtained from the previously instantiated Room and HouseIDVO objects.
        String roomId = room.getId().getID();
        String houseId = room.getHouseID().getID();
        RoomDTO roomDTO = new RoomDTO(roomId,"Office",2,3.5,3,2,houseId);

        // arranges DeviceDTO
        String deviceName = "Top Load Washing Machine";
        String deviceModel = "Samsung WA50R5400AW";
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, deviceModel);

        AddDeviceToRoomCTRL ctrl = new AddDeviceToRoomCTRL(deviceServiceImpl);

        int expectedDevicesInRepo = 1;


        // Act
        // Main operation:
        boolean operationResult = ctrl.addDeviceToRoom(roomDTO, deviceDTO);

        // get the number of devices in the repository
        List<Device> deviceList = deviceRepositoryMem.findByRoomID(room.getId());
        int actualDevicesInRepo = deviceList.size();

        // get the device name of the saved Device
        String devNameResult = deviceList.get(0).getDeviceName().getValue();

        // get the device model of the saved Device
        String devModelResult = deviceList.get(0).getDeviceModel().getValue();


        // Assert
        assertTrue(operationResult);
        assertEquals(expectedDevicesInRepo, actualDevicesInRepo);
        assertEquals(deviceName, devNameResult);
        assertEquals(deviceModel, devModelResult);
    }

    /**
     * Test method to verify that when a null device service is provided to the constructor of AddDeviceToRoomCTRL,
     * it throws an IllegalArgumentException.
     * This test ensures that the controller handles the case of a null device service being passed correctly.
     */
    @Test
    void givenNullDeviceService_constructorThrowsIllegalArgumentException(){
        // Arrange
        String expected = "Invalid services provided";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            new AddDeviceToRoomCTRL(null));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }


    /**
     * Test method to verify that when a null RoomDTO is passed to the addDeviceToRoom method in AddDeviceToRoomCTRL,
     * it returns false, indicating that the addition of the device to the room was unsuccessful.
     * This ensures that the controller behaves as expected when a null RoomDTO is provided.
     */
    @Test
    void givenNullRoomDTO_WhenAddDeviceToRoom_ThenShouldReturnFalse(){
        // Arrange
        // arranges Room repository, creates Room, saves Room to repository
        RoomRepositoryMem roomRepositoryMem = new RoomRepositoryMem();

        RoomNameVO name = new RoomNameVO("Office");
        RoomFloorVO floor = new RoomFloorVO(2);
        RoomWidthVO width = new RoomWidthVO(3.5);
        RoomLengthVO length = new RoomLengthVO(3);
        RoomHeightVO height = new RoomHeightVO(2);
        RoomDimensionsVO dimensions = new RoomDimensionsVO(length, width, height);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());
        Room room = new Room (name,floor,dimensions,houseID);

        roomRepositoryMem.save(room);

        // arranges Device Service
        DeviceFactoryImpl deviceFactoryImpl = new DeviceFactoryImpl();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(roomRepositoryMem, deviceFactoryImpl, deviceRepositoryMem);

        // arranges DeviceDTO
        String deviceName = "Top Load Washing Machine";
        String deviceModel = "Samsung WA50R5400AW";
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, deviceModel);

        AddDeviceToRoomCTRL ctrl = new AddDeviceToRoomCTRL(deviceServiceImpl);

        // Act
        boolean result = ctrl.addDeviceToRoom(null, deviceDTO);

        // Assert
        assertFalse(result);
    }

    /**
     * Test method to verify that when a null DeviceDTO is provided to the AddDeviceToRoomCTRL's addDeviceToRoom method,
     * it returns false, indicating a failure to add the device to the room.
     * This test ensures that the controller correctly handles a null DeviceDTO input.
     */
    @Test
    void givenNullDeviceDTO_WhenAddDeviceToRoom_ThenShouldReturnFalse(){
        // Arrange
        // arranges Room repository, creates Room, saves Room to repository
        RoomRepositoryMem roomRepositoryMem = new RoomRepositoryMem();

        RoomNameVO name = new RoomNameVO("Office");
        RoomFloorVO floor = new RoomFloorVO(2);
        RoomWidthVO width = new RoomWidthVO(3.5);
        RoomLengthVO length = new RoomLengthVO(3);
        RoomHeightVO height = new RoomHeightVO(2);
        RoomDimensionsVO dimensions = new RoomDimensionsVO(length, width, height);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());
        Room room = new Room (name,floor,dimensions,houseID);

        roomRepositoryMem.save(room);

        // arranges Device service
        DeviceFactoryImpl deviceFactoryImpl = new DeviceFactoryImpl();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(roomRepositoryMem, deviceFactoryImpl, deviceRepositoryMem);

        // arranges RoomDTO. String IDs must be obtained from the previously instantiated Room and HouseIDVO objects.
        String roomId = room.getId().getID();
        String houseId = room.getHouseID().getID();
        RoomDTO roomDTO = new RoomDTO(roomId,"Office",2,3.5,3,2,houseId);

        AddDeviceToRoomCTRL ctrl = new AddDeviceToRoomCTRL(deviceServiceImpl);

        // Act
        boolean result = ctrl.addDeviceToRoom(roomDTO, null);

        // Assert
        assertFalse(result);
    }

    /**
     * This test ensures that the addDeviceToRoom() method returns false if the information within the RoomDTO does not result
     * in a RoomIDVO object. When giving a wrong UUID format, an IllegalArgumentException is thrown.
     */
    @Test
    void givenRoomDTOWithInvalidIdFormat_WhenAddDeviceToRoom_ThenShouldReturnFalse(){
        // Arrange
        // arranges Room repository, creates Room, saves Room to repository
        RoomRepositoryMem roomRepositoryMem = new RoomRepositoryMem();

        RoomNameVO name = new RoomNameVO("Office");
        RoomFloorVO floor = new RoomFloorVO(2);
        RoomWidthVO width = new RoomWidthVO(3.5);
        RoomLengthVO length = new RoomLengthVO(3);
        RoomHeightVO height = new RoomHeightVO(2);
        RoomDimensionsVO dimensions = new RoomDimensionsVO(length, width, height);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());
        Room room = new Room (name,floor,dimensions,houseID);

        roomRepositoryMem.save(room);

        // arranges Device service
        DeviceFactoryImpl deviceFactoryImpl = new DeviceFactoryImpl();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(roomRepositoryMem, deviceFactoryImpl, deviceRepositoryMem);

        // arranges RoomDTO. House ID must be obtained from the previously instantiated HouseIDVO object.
        String houseId = room.getHouseID().getID();
        RoomDTO roomDTO = new RoomDTO("I will fail","Office",2,3.5,3,2,houseId);

        // arranges DeviceDTO
        String deviceName = "Top Load Washing Machine";
        String deviceModel = "Samsung WA50R5400AW";
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, deviceModel);

        AddDeviceToRoomCTRL ctrl = new AddDeviceToRoomCTRL(deviceServiceImpl);

        // Act
        boolean result = ctrl.addDeviceToRoom(roomDTO, deviceDTO);

        // Assert
        assertFalse(result);
    }

    /**
     * This test ensures that the addDeviceToRoom() method returns false if the information within the RoomDTO does not result
     * in a RoomIDVO object that corresponds to a key in the repository (does not exist).
     */
    @Test
    void givenRoomDTOWithAnIdNotPresentInRoomRepository_WhenAddDeviceToRoom_ThenShouldReturnFalse(){
        // Arrange
        // arranges Room repository, creates Room, saves Room to repository
        RoomRepositoryMem roomRepositoryMem = new RoomRepositoryMem();

        RoomNameVO name = new RoomNameVO("Office");
        RoomFloorVO floor = new RoomFloorVO(2);
        RoomWidthVO width = new RoomWidthVO(3.5);
        RoomLengthVO length = new RoomLengthVO(3);
        RoomHeightVO height = new RoomHeightVO(2);
        RoomDimensionsVO dimensions = new RoomDimensionsVO(length, width, height);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());
        Room room = new Room (name,floor,dimensions,houseID);

        roomRepositoryMem.save(room);

        // arranges Device Service
        DeviceFactoryImpl deviceFactoryImpl = new DeviceFactoryImpl();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(roomRepositoryMem, deviceFactoryImpl, deviceRepositoryMem);

        // arranges RoomDTO. House ID must be obtained from the previously instantiated HouseIDVO object.
        String houseId = room.getHouseID().getID();
        RoomDTO roomDTO = new RoomDTO("d1c6bfb3-8d1e-4e5c-a4fd-6b1dfc7dc8f9","Office",2,3.5,3,2,houseId);

        // arranges DeviceDTO
        String deviceName = "Top Load Washing Machine";
        String deviceModel = "Samsung WA50R5400AW";
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, deviceModel);

        AddDeviceToRoomCTRL ctrl = new AddDeviceToRoomCTRL(deviceServiceImpl);

        // Act
        boolean result = ctrl.addDeviceToRoom(roomDTO, deviceDTO);

        // Assert
        assertFalse(result);
    }


    /**
     * This test ensures the method addDeviceToRoom() returns true even if the Device to be added has a similar name and model.
     * This indicates that a brand new DeviceIDVO is being created on Device instantiation. The same DeviceDTO is introduced twice
     * in the system and added to the same Room.
     */
    @Test
    void whenAddingDevicesWithSameNameAndModel_ThenShouldReturnTrue(){
        // Arrange
        // arranges Room repository, creates Room, saves Room to repository
        RoomRepositoryMem roomRepositoryMem = new RoomRepositoryMem();

        RoomNameVO name = new RoomNameVO("Office");
        RoomFloorVO floor = new RoomFloorVO(2);
        RoomWidthVO width = new RoomWidthVO(3.5);
        RoomLengthVO length = new RoomLengthVO(3);
        RoomHeightVO height = new RoomHeightVO(2);
        RoomDimensionsVO dimensions = new RoomDimensionsVO(length, width, height);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());
        Room room = new Room (name,floor,dimensions,houseID);

        roomRepositoryMem.save(room);

        // arranges Device Service
        DeviceFactoryImpl deviceFactoryImpl = new DeviceFactoryImpl();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(roomRepositoryMem, deviceFactoryImpl, deviceRepositoryMem);

        // arranges RoomDTO. String IDs must be obtained from the previously instantiated Room and HouseIDVO objects.
        String roomId = room.getId().getID();
        String houseId = room.getHouseID().getID();
        RoomDTO roomDTO = new RoomDTO(roomId,"Office",2,3.5,3,2,houseId);

        // arranges DeviceDTO
        String deviceName = "Top Load Washing Machine";
        String deviceModel = "Samsung WA50R5400AW";
        DeviceDTO deviceDTO = new DeviceDTO(deviceName,deviceModel);

        AddDeviceToRoomCTRL ctrl = new AddDeviceToRoomCTRL(deviceServiceImpl);

        ctrl.addDeviceToRoom(roomDTO,deviceDTO);

        // Act
        boolean result = ctrl.addDeviceToRoom(roomDTO,deviceDTO);

        // Assert
        assertTrue(result);
    }

    /**
     * This test ensures that when the addDeviceToRoom() method is utilized, the new device can be accessed through the repository,
     * and its name is the same as the defined name, meaning the Device was actually saved.
     */
    @Test
    void givenValidData_DeviceIsAdded_WhenFindByRoomIDFollowedByGetDeviceName_ExpectedDeviceNameIsReturned(){
        // Arrange
        // arranges Room repository, creates Room, saves Room to repository
        RoomRepositoryMem roomRepositoryMem = new RoomRepositoryMem();

        RoomNameVO name = new RoomNameVO("Office");
        RoomFloorVO floor = new RoomFloorVO(2);
        RoomWidthVO width = new RoomWidthVO(3.5);
        RoomLengthVO length = new RoomLengthVO(3);
        RoomHeightVO height = new RoomHeightVO(2);
        RoomDimensionsVO dimensions = new RoomDimensionsVO(length, width, height);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());
        Room room = new Room (name,floor,dimensions,houseID);

        roomRepositoryMem.save(room);

        // arranges Device Service
        DeviceFactoryImpl deviceFactoryImpl = new DeviceFactoryImpl();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(roomRepositoryMem, deviceFactoryImpl, deviceRepositoryMem);

        // arranges RoomDTO. String IDs must be obtained from the previously instantiated Room and HouseIDVO objects.
        String roomId = room.getId().getID();
        String houseId = room.getHouseID().getID();
        RoomDTO roomDTO = new RoomDTO(roomId,"Office",2,3.5,3,2,houseId);

        // arranges DeviceDTO
        String deviceName = "Top Load Washing Machine";
        String deviceModel = "Samsung WA50R5400AW";
        DeviceDTO deviceDTO = new DeviceDTO(deviceName,deviceModel);

        AddDeviceToRoomCTRL ctrl = new AddDeviceToRoomCTRL(deviceServiceImpl);

        ctrl.addDeviceToRoom(roomDTO, deviceDTO);

        RoomIDVO roomIdVO = room.getId();

        List<Device> resultList = deviceRepositoryMem.findByRoomID(roomIdVO);
        int expectedSize = 1;

        // Act
        String resultName = resultList.get(0).getDeviceName().getValue();
        int listSize = resultList.size();

        // Assert
        assertEquals(deviceName,resultName);
        assertEquals(expectedSize, listSize);
    }
}