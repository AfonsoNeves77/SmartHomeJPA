package smarthome.dto;

public class DeviceDTO {
    private String deviceID;
    private String deviceName;
    private String deviceModel;
    private String deviceStatus;
    private String roomID;


    /**
     * Constructor for DeviceDTO.
     * Use scenario: to receive external information when a new device is being added to the system.
     * @param deviceName desired device name
     * @param deviceModel desired device model
     */
    public DeviceDTO(String deviceName, String deviceModel) {
        this.deviceName = deviceName;
        this.deviceModel = deviceModel;
    }

    /**
     * Constructor for DeviceDTO.
     * Use scenario: to enable to convert Domain objects to DTOs (expose information).
     * @param deviceID device ID in string format
     * @param deviceName device name
     * @param deviceModel device model
     * @param deviceStatus device status in string format
     * @param roomID device location in string format
     */
    public DeviceDTO(String deviceID, String deviceName, String deviceModel, String deviceStatus, String roomID) {
        this(deviceName, deviceModel);
        this.deviceID = deviceID;
        this.deviceStatus = deviceStatus;
        this.roomID = roomID;
    }

    /**
     * Simple getter method to retrieve device ID.
     * @return device ID in string format
     */
    public String getDeviceID() {
        return deviceID;
    }

    /**
     * Simple getter method to retrieve device name.
     * @return device name
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * Simple getter method to retrieve device model.
     * @return device model
     */
    public String getDeviceModel() {
        return deviceModel;
    }

    /**
     * Simple getter method to retrieve device status.
     * @return device status in string format
     */
    public String getDeviceStatus() {
        return deviceStatus;
    }

    /**
     * Simple getter method to retrieve device location (room ID).
     * @return device location, which corresponds to the room ID in string format
     */
    public String getRoomID() {
        return roomID;
    }
}
