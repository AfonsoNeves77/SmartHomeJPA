package SmartHomeTest.dtoTest;

import smarthome.dto.DeviceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DeviceDTOTest {

    private DeviceDTO completeDto;
    private DeviceDTO partialDto;

    /**
     * Set up to be made before each test.
     * A DeviceDTO is created with the both available constructors. One object has all the parameters of domain Device
     * objects (complete) and the another only has the deviceName and deviceModel (partial).
     * All tests are run considering both DeviceDTO objects, and asserts to their attributes versus the expected result
     * are made (one attribute per test).
     */
    @BeforeEach
    public void setUp(){
        String deviceID = "frt3-567p-32gf";
        String completeDevName = "Smart Camera";
        String completeDevModel = "Arlo Ultra 2 4K UHD Security Camera";
        String deviceStatus = "true";
        String roomID = "f4t3-jk7p-5mgf";
        this.completeDto = new DeviceDTO(deviceID, completeDevName, completeDevModel, deviceStatus, roomID);

        String partialDevName = "Robot Vacuum";
        String partialDevModel = "Ecovacs Deebot OZMO 950";
        this.partialDto = new DeviceDTO(partialDevName, partialDevModel);
    }

    /**
     * Verifies that a complete device returns its expected ID and a partial device has no ID (is null).
     */
    @Test
    void createDeviceDTO_WhenGetDeviceID_ThenShouldReturnDeviceIDAsString(){
        //Arrange
        String expectedCompleteDto = "frt3-567p-32gf";
        //Act
        String resultCompleteDto = completeDto.getDeviceID();
        String resultPartialDto = partialDto.getDeviceID();
        //Assert
        assertEquals(expectedCompleteDto, resultCompleteDto);
        assertNull(resultPartialDto);
    }

    /**
     * Verifies that both complete and partial devices return their expected names.
     */
    @Test
    void createDeviceDTO_WhenGetDeviceName_ThenShouldReturnDeviceName(){
        //Arrange
        String expectedCompleteDto = "Smart Camera";
        String expectedPartialDto = "Robot Vacuum";
        //Act
        String resultCompleteDto = completeDto.getDeviceName();
        String resultPartialDto = partialDto.getDeviceName();
        //Assert
        assertEquals(expectedCompleteDto, resultCompleteDto);
        assertEquals(expectedPartialDto, resultPartialDto);
    }

    /**
     * Verifies that both complete and partial devices return their expected models.
     */
    @Test
    void createDeviceDTO_WhenGetDeviceModel_ThenShouldReturnDeviceModel(){
        //Arrange
        String expectedCompleteDto = "Arlo Ultra 2 4K UHD Security Camera";
        String expectedPartialDto = "Ecovacs Deebot OZMO 950";
        //Act
        String resultCompleteDto = completeDto.getDeviceModel();
        String resultPartialDto = partialDto.getDeviceModel();
        //Assert
        assertEquals(expectedCompleteDto, resultCompleteDto);
        assertEquals(expectedPartialDto, resultPartialDto);
    }

    /**
     * Verifies that a complete device returns its expected status and a partial device has no status (is null).
     */
    @Test
    void createDeviceDTO_WhenGetDeviceStatus_ThenShouldReturnStatusAsString(){
        //Arrange
        String expectedCompleteDto = "true";
        //Act
        String resultCompleteDto = completeDto.getDeviceStatus();
        String resultPartialDto = partialDto.getDeviceStatus();
        //Assert
        assertEquals(expectedCompleteDto, resultCompleteDto);
        assertNull(resultPartialDto);
    }

    /**
     * Verifies that a complete device returns its expected status and a partial device has no associated location
     * (which means room ID is null).
     */
    @Test
    void createDeviceDTO_WhenGetRoomID_ThenShouldReturnRoomIDAsString(){
        //Arrange
        String expectedCompleteDto = "f4t3-jk7p-5mgf";
        //Act
        String resultCompleteDto = completeDto.getRoomID();
        String resultPartialDto = partialDto.getRoomID();
        //Assert
        assertEquals(expectedCompleteDto, resultCompleteDto);
        assertNull(resultPartialDto);
    }
}
