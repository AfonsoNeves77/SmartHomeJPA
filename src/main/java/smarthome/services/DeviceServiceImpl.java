package smarthome.services;

import smarthome.domain.device.Device;
import smarthome.domain.device.DeviceFactory;
import smarthome.repository.DeviceRepository;
import smarthome.repository.RoomRepository;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.devicevo.DeviceModelVO;
import smarthome.vo.devicevo.DeviceNameVO;
import smarthome.vo.roomvo.RoomIDVO;

import java.util.List;

/**
 * Service class responsible for managing devices in the smart home domain (create,save,checking for activation status and retrieving a list of devices in a room).
 */

public class DeviceServiceImpl implements DeviceService{

    private final RoomRepository roomRepository;
    private final DeviceFactory deviceFactory;
    private final DeviceRepository deviceRepository;

    /**
     * Constructs an instance of V1DeviceService with the provided dependencies.
     * This constructor initializes the V1DeviceService with the necessary repositories and factory.
     * It ensures that the provided parameters are valid and throws an IllegalArgumentException if any are null.
     * @param deviceFactory   The factory responsible for creating devices.
     * @param deviceRepository The repository storing and retrieving devices.
     * @throws IllegalArgumentException if either factoryDevice or deviceRepository is null.
     */

    public DeviceServiceImpl(RoomRepository roomRepository, DeviceFactory deviceFactory, DeviceRepository deviceRepository){
        if(!validParams(roomRepository,deviceFactory, deviceRepository)){
            throw new IllegalArgumentException("Invalid parameters");
        }
        this.roomRepository = roomRepository;
        this.deviceFactory = deviceFactory;
        this.deviceRepository = deviceRepository;
    }

    /**
     * Adds a new Device with the provided details to the system.
     * This method first checks if the specified Room, identified by roomIDVO, is present in the system.
     * If the Room is present, it creates a new Device using the deviceFactory with the provided DeviceNameVO
     * and DeviceModelVO for the name and model of the Device, respectively.
     * The newly created Device is then saved to the system using the deviceRepository.
     * @param deviceNameVO The Value Object representing the name of the Device to add.
     * @param deviceModelVO The Value Object representing the model of the Device to add.
     * @param roomIDVO The Value Object representing the ID of the Room where the Device is to be added.
     * @return true if the Device is successfully added to the system, false otherwise.
     * It returns false if the specified Room is not found in the system or if there is an error
     * creating or saving the Device.
     */
    public boolean addDevice(DeviceNameVO deviceNameVO, DeviceModelVO deviceModelVO, RoomIDVO roomIDVO){
        if (roomRepository.isPresent(roomIDVO)){
            try{
                Device newDevice = deviceFactory.createDevice(deviceNameVO,deviceModelVO,roomIDVO);
                return deviceRepository.save(newDevice);
            } catch (IllegalArgumentException e){
                return false;
            }
        }
        return false;
    }

    /**
     * Deactivates the device with the given ID.
     * @param deviceIDVO The ID of the device to be deactivated.
     * @return The method returns the resulting device status after the operation was performed.
     */

    public boolean deactivateDevice(DeviceIDVO deviceIDVO){
        Device device =  this.deviceRepository.findById(deviceIDVO);
        if (device != null){
            device.deactivateDevice();
            return device.isActive();
        }
        return false;
    }

    /**
     * Retrieves the list of devices located in the room identified by the provided RoomIDVO.
     * This method queries the system to fetch all devices associated with the specified room.
     * @param roomIDVO The Value Object representing the ID of the room to retrieve devices from.
     * @return The list of devices located in the specified room.
     *         It returns an empty list if no devices are found or if the specified room does not exist.
     */
    public List<Device> getListOfDevicesInARoom(RoomIDVO roomIDVO){
        return this.deviceRepository.findByRoomID(roomIDVO);
    }

    /**
     * Checks if all parameters passed to the method are non-null.
     * This method ensures that all parameters provided to it are not null before proceeding with further operations.
     * While this function may not be considered type-safe, it is always used after ensuring type safety
     * by type-checking the variables of the calling method (compiler).
     *
     * @param params The parameters to be checked for nullity.
     * @return true if all parameters are non-null, false otherwise.
     */

    private boolean validParams (Object... params){
        for (Object param : params){
            if (param == null){
                return false;
            }
        }
        return true;
    }
}
