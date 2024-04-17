package smarthome.mapper;

import smarthome.domain.device.Device;
import smarthome.dto.DeviceDTO;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.devicevo.DeviceModelVO;
import smarthome.vo.devicevo.DeviceNameVO;

import java.util.*;

public class DeviceMapper {

    private static final String ERRORMESSAGE = "DeviceDTO cannot be null.";

    /**
     * Creates a new Device object from the provided DeviceDTO object.
     * Validates the provided DeviceDTO object to ensure it is not null
     * Then ensures the device name is valid, meaning not null, empty or blank.
     * @param deviceDTO the DeviceDTO object to be converted to a Device object
     * @return a new Device object created from the provided DeviceDTO object
     * @throws IllegalArgumentException if the provided DeviceDTO object is null
     */
    public static DeviceNameVO createDeviceName(DeviceDTO deviceDTO) {
        if (deviceDTO == null) {
            throw new IllegalArgumentException(ERRORMESSAGE);
        }
        String deviceName = deviceDTO.getDeviceName();
        if (!isDeviceNameValid(deviceName)) {
            throw new IllegalArgumentException("Invalid device name.");
        }
        return new DeviceNameVO(deviceName);
    }

   /**
     * Creates a new Device object from the provided DeviceDTO object.
     * Validates the provided DeviceDTO object to ensure it is not null
     * Then ensures the device model is valid, meaning not null, empty or blank.
     * @param deviceDTO the DeviceDTO object to be converted to a Device object
     * @return a new Device object created from the provided DeviceDTO object
     * @throws IllegalArgumentException if the provided DeviceDTO object is null
     */
    public static DeviceModelVO createDeviceModel(DeviceDTO deviceDTO) {
        if (deviceDTO == null) {
            throw new IllegalArgumentException(ERRORMESSAGE);
        }
        String deviceModel = deviceDTO.getDeviceModel();
        if (!isDeviceModelValid(deviceModel)) {
            throw new IllegalArgumentException("Invalid device model.");
        }
        return new DeviceModelVO(deviceModel);
    }

    /**
     * Creates a new Device object from the provided DeviceDTO object.
     * Validates the provided DeviceDTO object to ensure it is not null
     * Then ensures the device ID is valid, meaning not null, empty or blank.
     * Then, instantiates a new UUID object from the provided device ID string.
     * @param deviceDTO the DeviceDTO object to be converted to a Device object
     * @return a new Device object created from the provided DeviceDTO object
     * @throws IllegalArgumentException if the provided DeviceDTO object is null
     */
    public static DeviceIDVO createDeviceID(DeviceDTO deviceDTO) {
        if (deviceDTO == null) {
            throw new IllegalArgumentException(ERRORMESSAGE);
        }
        String deviceID = deviceDTO.getDeviceID();
        if (!isDeviceIDValid(deviceID)) {
            throw new IllegalArgumentException("Invalid device ID.");
        }
        UUID id = UUID.fromString(deviceID);
        return new DeviceIDVO(id);
    }

    /**
     * Converts a list of Device domain objects to a list of DeviceDTO.
     * This method iterates through the provided list of Device domain objects, extracts relevant information
     * such as device name, model, room ID, status, and ID, and creates a new list of DeviceDTOs with this information.
     * @param deviceList The list of Device domain objects to be converted.
     * @return A new list of DeviceDTO Data Transfer Objects representing the converted devices.
     */
    public static List<DeviceDTO> domainToDTO(List<Device> deviceList) {
        List<DeviceDTO> deviceDTOList = new ArrayList<>();
        for (Device device : deviceList) {
            String name = device.getDeviceName().getValue();
            String model = device.getDeviceModel().getValue();
            String roomID = device.getRoomID().getID();
            String status = device.getDeviceStatus().getValue().toString();
            String deviceID = device.getId().getID();

            DeviceDTO deviceDTO = new DeviceDTO(deviceID, name, model, status, roomID);

            deviceDTOList.add(deviceDTO);
        }
        return deviceDTOList;
    }


    /**
     * Validates the device name.
     * @param deviceName the device name to be validated
     * @return true if the device name is valid, false otherwise
     */
    private static boolean isDeviceNameValid(String deviceName) {
        if (deviceName == null || deviceName.isEmpty()) {
            return false;
        }
        return !deviceName.trim().isEmpty();
    }

    /**
     * Validates the device model.
     * @param deviceModel the device model to be validated
     * @return true if the device model is valid, false otherwise
     */
    private static boolean isDeviceModelValid(String deviceModel) {
        if (deviceModel == null || deviceModel.isEmpty()) {
            return false;
        }
        return !deviceModel.trim().isEmpty();
    }

    /**
     * Validates the device ID.
     * @param deviceID the device ID to be validated
     * @return true if the device ID is valid, false otherwise
     */
    private static boolean isDeviceIDValid(String deviceID) {
        if (deviceID == null || deviceID.isEmpty()) {
            return false;
        }
        return !deviceID.trim().isEmpty();
    }

    /**
     * Converts a Map of Device domain objects to a Map of DeviceDTO Data.
     * This method iterates through the provided Map of Device domain objects, converts each list of devices
     * to DeviceDTOs using the domainToDTO method, and creates a new Map with the converted DeviceDTO lists.
     * @param map The Map where each key represents a functionality and the value is a list of Device domain objects.
     * @return A new Map where each key represents a functionality and the value is a list of DeviceDTO Data Transfer Objects.
     */
    public static LinkedHashMap<String,List<DeviceDTO>> domainToDTO(Map<String, List<Device>> map) {
        LinkedHashMap<String,List<DeviceDTO>> newMap = new LinkedHashMap<>();
        for (String key : map.keySet()) {
            List<Device> deviceList = map.get(key);
            List<DeviceDTO> deviceDTOList = domainToDTO(deviceList);
            newMap.put(key,deviceDTOList);
        }
        return newMap;
    }
}
