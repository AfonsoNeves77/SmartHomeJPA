package IntegrationTests;

import org.apache.commons.collections4.IterableUtils;
import smarthome.controller.AddActuatorToDeviceCTRL;
import smarthome.domain.actuator.*;
import smarthome.domain.actuatortype.ActuatorTypeFactory;
import smarthome.domain.actuatortype.ActuatorTypeFactoryImpl;
import smarthome.domain.device.Device;
import smarthome.dto.ActuatorDTO;
import smarthome.dto.ActuatorTypeDTO;
import smarthome.dto.DeviceDTO;
import smarthome.repository.ActuatorRepositoryMem;
import smarthome.repository.ActuatorTypeRepositoryMem;
import smarthome.repository.DeviceRepositoryMem;
import smarthome.services.ActuatorTypeService;
import smarthome.services.ActuatorServiceImpl;
import smarthome.services.ActuatorTypeServiceImpl;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.devicevo.DeviceModelVO;
import smarthome.vo.devicevo.DeviceNameVO;
import smarthome.vo.devicevo.DeviceStatusVO;
import smarthome.vo.roomvo.*;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test class includes ActuatorTypeService instantiations that may appear unused.
 * The reason for this is that the service initialization automatically populates the `actuatorTypeRepository`.
 * Without this step, when attempting to verify that the actuator type exists, the result will always fail
 * as the repository would be empty.
 */

class AddActuatorToDeviceCTRLTest {

    /**
     * Test method to verify that when a null ActuatorService is provided to the AddActuatorToDeviceCTRL constructor,
     * it throws an IllegalArgumentException with the expected error message.
     * This test ensures that the controller correctly handles a null ActuatorService input.
     */
    @Test
    void whenNullActuatorService_constructorThrowsIllegalArgumentException() {
        //Arrange
        String expectedMessage = "Invalid service";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AddActuatorToDeviceCTRL(null));

        //Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test method to verify that when adding a Switch Actuator to a Device using the AddActuatorToDeviceCTRL,
     * the operation returns true upon success, and the Switch Actuator is correctly added to the Actuator repository.
     * This test initializes Actuator Service, creates a new Device and saves it to the Device repository.
     * It also initializes the Actuator Type Service to populate the Actuator Type repository,
     * initializes the AddActuatorToDeviceCTRL controller, and creates DTOs for the Device, Actuator Type, and Actuator.
     * The test then calls the addActuatorToDevice method on the controller with the ActuatorDTO,
     * ActuatorTypeDTO, and DeviceDTO, and asserts that the operation returns true, indicating success.
     * It further asserts that the added Switch Actuator is present in the Actuator repository with the correct attributes.
     */
    @Test
    void  whenAddSwitchActuatorToDevice_thenReturnTrue() throws ConfigurationException {
        //Arrange
        //Actuator Service instantiation
        ActuatorFactoryImpl actuatorFactoryImpl = new ActuatorFactoryImpl();
        ActuatorRepositoryMem actuatorRepositoryMem = new ActuatorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        ActuatorTypeRepositoryMem actuatorTypeRepositoryMem = new ActuatorTypeRepositoryMem();
        ActuatorServiceImpl actuatorServiceImpl = new ActuatorServiceImpl(deviceRepositoryMem, actuatorTypeRepositoryMem, actuatorFactoryImpl, actuatorRepositoryMem);

        //Creation of device and addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //Actuator Type Service initialization, so it can populate actuator type repository before adding a specific type
        String path = "actuator.properties";
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactoryImpl();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeServiceImpl(actuatorTypeRepositoryMem,actuatorTypeFactory,path);

        //Controller initialization
        AddActuatorToDeviceCTRL addActuatorToDeviceCTRL = new AddActuatorToDeviceCTRL(actuatorServiceImpl);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceIDVO.getID(), deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //ActuatorTypeDTO initialization
        String actuatorTypeID ="SwitchActuator";
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeID);

        //ActuatorDTO initialization
        String actuatorNameVO = "Actuator1";
        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorNameVO, actuatorTypeID, deviceID);

        //Act
        boolean result = addActuatorToDeviceCTRL.addActuatorToDevice(actuatorDTO, actuatorTypeDTO, deviceDTO);

        //Assert

        //This assertion asserts that the operation succeeded
        assertTrue(result);

        //This assertion asserts that the added switch actuator is present in actuator repository

        //Fetch the added switch actuator from repository
        Iterable<Actuator> actuatorIterable = actuatorRepositoryMem.findAll();
        SwitchActuator addedActuator = (SwitchActuator) IterableUtils.get(actuatorIterable,0);

        //Query the fetched switch actuator for it's attributes
        String resultName = addedActuator.getActuatorName().getValue();
        String resultTypeID = addedActuator.getActuatorTypeID().getID();
        DeviceIDVO resultDeviceID = addedActuator.getDeviceID();

        //Compare switch actuator attributes with the ones given in its creation
        assertEquals(actuatorNameVO,resultName);
        assertEquals(actuatorTypeID,resultTypeID);
        assertEquals(deviceIDVO,resultDeviceID);
    }

    /**
     * Test method to verify that when adding an Integer Value Actuator to a Device using the AddActuatorToDeviceCTRL,
     * the operation returns true upon success, and the Integer Value Actuator is correctly added to the Actuator repository
     * with the provided upper and lower limit settings.
     * This test initializes Actuator Service, creates a new Device and saves it to the Device repository.
     * It also initializes the Actuator Type Service to populate the Actuator Type repository,
     * initializes the AddActuatorToDeviceCTRL controller, and creates DTOs for the Device, Actuator Type, and Actuator.
     * The test then calls the addActuatorToDevice method on the controller with the ActuatorDTO,
     * ActuatorTypeDTO, and DeviceDTO, and asserts that the operation returns true, indicating success.
     * It further asserts that the added Integer Value Actuator is present in the Actuator repository with the correct attributes,
     * including the provided upper and lower limit settings.
     */
    @Test
    void addIntegerValueActuatorToDevice_WhenValidIntegerSettings_thenReturnTrue() throws ConfigurationException {
        //Arrange
        //Actuator Service instantiation
        ActuatorFactoryImpl actuatorFactoryImpl = new ActuatorFactoryImpl();
        ActuatorRepositoryMem actuatorRepositoryMem = new ActuatorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        ActuatorTypeRepositoryMem actuatorTypeRepositoryMem = new ActuatorTypeRepositoryMem();
        ActuatorServiceImpl actuatorServiceImpl = new ActuatorServiceImpl(deviceRepositoryMem, actuatorTypeRepositoryMem, actuatorFactoryImpl, actuatorRepositoryMem);

        //Creation of device and addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //Actuator Type Service initialization, so it can populate actuator type repository before adding a specific type
        String path = "actuator.properties";
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactoryImpl();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeServiceImpl(actuatorTypeRepositoryMem,actuatorTypeFactory,path);

        //Controller initialization
        AddActuatorToDeviceCTRL addActuatorToDeviceCTRL = new AddActuatorToDeviceCTRL(actuatorServiceImpl);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceIDVO.getID(), deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //ActuatorTypeDTO initialization
        String actuatorTypeID ="IntegerValueActuator";
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeID);

        //ActuatorDTO initialization
        String actuatorNameVO = "Actuator1";
        String upperLimit = "2";
        String downLimit = "1";

        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorNameVO, actuatorTypeID, deviceID,downLimit,upperLimit);

        //Act
        boolean result = addActuatorToDeviceCTRL.addActuatorToDevice(actuatorDTO, actuatorTypeDTO, deviceDTO);

        //Assert

        //This assertion asserts that the operation succeeded
        assertTrue(result);

        //This assertion asserts that the added Integer Value Actuator is present in actuator repository

        //Fetch the added Integer Value Actuator from repository
        Iterable<Actuator> actuatorIterable = actuatorRepositoryMem.findAll();
        IntegerValueActuator addedActuator = (IntegerValueActuator) IterableUtils.get(actuatorIterable,0);

        //Query the fetched Integer Value Actuator for it's attributes
        String resultName = addedActuator.getActuatorName().getValue();
        String resultTypeID = addedActuator.getActuatorTypeID().getID();
        DeviceIDVO resultDeviceID = addedActuator.getDeviceID();
        Integer[] resultIntegerSettings = addedActuator.getIntegerSettings().getValue();


        //Compare Integer Value Actuator attributes with the ones given in its creation
        assertEquals(actuatorNameVO,resultName);
        assertEquals(actuatorTypeID,resultTypeID);
        assertEquals(deviceIDVO,resultDeviceID);

        int upperLimitInt = Integer.parseInt(upperLimit);
        int downLimitInt = Integer.parseInt(downLimit);
        Integer[] expectedIntegerSettings = {downLimitInt,upperLimitInt,};

        assertArrayEquals(expectedIntegerSettings,resultIntegerSettings);
    }

    /**
     * Test method to verify that when adding a Decimal Value Actuator to a Device using the AddActuatorToDeviceCTRL,
     * the operation returns true upon success, and the Decimal Value Actuator is correctly added to the Actuator repository
     * with the provided upper and lower limit settings, as well as precision.
     * This test initializes Actuator Service, creates a new Device, and saves it to the Device repository.
     * It also initializes the Actuator Type Service to populate the Actuator Type repository,
     * initializes the AddActuatorToDeviceCTRL controller, and creates DTOs for the Device, Actuator Type, and Actuator.
     * The test then calls the addActuatorToDevice method on the controller with the ActuatorDTO,
     * ActuatorTypeDTO, and DeviceDTO, and asserts that the operation returns true, indicating success.
     * It further asserts that the added Decimal Value Actuator is present in the Actuator repository with the correct attributes,
     * including the provided upper and lower limit settings and precision.
     */
    @Test
    void addDecimalValueActuatorToDevice_WhenValidDecimalSettings_thenReturnTrue() throws ConfigurationException {
        //Arrange
        //Actuator Service instantiation
        ActuatorFactoryImpl actuatorFactoryImpl = new ActuatorFactoryImpl();
        ActuatorRepositoryMem actuatorRepositoryMem = new ActuatorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        ActuatorTypeRepositoryMem actuatorTypeRepositoryMem = new ActuatorTypeRepositoryMem();
        ActuatorServiceImpl actuatorServiceImpl = new ActuatorServiceImpl(deviceRepositoryMem, actuatorTypeRepositoryMem, actuatorFactoryImpl, actuatorRepositoryMem);

        //Creation of device and addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //Actuator Type Service initialization, so it can populate actuator type repository before adding a specific type
        String path = "actuator.properties";
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactoryImpl();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeServiceImpl(actuatorTypeRepositoryMem,actuatorTypeFactory,path);

        //Controller initialization
        AddActuatorToDeviceCTRL addActuatorToDeviceCTRL = new AddActuatorToDeviceCTRL(actuatorServiceImpl);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceIDVO.getID(), deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //ActuatorTypeDTO initialization
        String actuatorTypeID ="DecimalValueActuator";
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeID);

        //ActuatorDTO initialization
        String actuatorNameVO = "Actuator1";
        String upperLimit = "0.9";
        String downLimit = "0.1";
        String precision = "0.1";

        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorNameVO, actuatorTypeID, deviceID,downLimit,upperLimit,precision);

        //Act
        boolean result = addActuatorToDeviceCTRL.addActuatorToDevice(actuatorDTO, actuatorTypeDTO, deviceDTO);

        //Assert

        //This assertion asserts that the operation succeeded
        assertTrue(result);

        //This assertion asserts that the added Decimal Value Actuator is present in actuator repository

        //Fetch the added Integer Value Actuator from repository
        Iterable<Actuator> actuatorIterable = actuatorRepositoryMem.findAll();
        DecimalValueActuator addedActuator = (DecimalValueActuator) IterableUtils.get(actuatorIterable,0);

        //Query the fetched Decimal Value Actuator for it's attributes
        String resultName = addedActuator.getActuatorName().getValue();
        String resultTypeID = addedActuator.getActuatorTypeID().getID();
        DeviceIDVO resultDeviceID = addedActuator.getDeviceID();
        Double[] resultDoubleSettings = addedActuator.getDecimalSettings().getValue();


        //Compare Decimal Value Actuator attributes with the ones given in its creation
        assertEquals(actuatorNameVO,resultName);
        assertEquals(actuatorTypeID,resultTypeID);
        assertEquals(deviceIDVO,resultDeviceID);

        double upperLimitInt = Double.parseDouble(upperLimit);
        double downLimitInt = Double.parseDouble(downLimit);
        double precisionInt = Double.parseDouble(precision);
        Double[] expectedIntegerSettings = {downLimitInt,upperLimitInt,precisionInt};

        assertArrayEquals(expectedIntegerSettings,resultDoubleSettings);
    }

    /**
     * Test method to verify that when attempting to add a Decimal Value Actuator to a Device with a lower limit
     * that is bigger than the upper limit, the operation returns false and the Actuator repository remains empty.
     * This test initializes Actuator Service, creates a new Device, and saves it to the Device repository.
     * It also initializes the Actuator Type Service to populate the Actuator Type repository,
     * initializes the AddActuatorToDeviceCTRL controller, and creates DTOs for the Device, Actuator Type, and Actuator.
     * The test then calls the addActuatorToDevice method on the controller with the ActuatorDTO,
     * ActuatorTypeDTO, and DeviceDTO, and asserts that the operation returns false, indicating failure.
     * It further asserts that the Actuator repository remains empty, as the addition should not have succeeded
     * due to the lower limit being greater than the upper limit.
     */
    @Test
    void addDecimalValueActuatorToDevice_WhenLowerLimitBiggerThanUpperLimit_thenReturnFalse() throws ConfigurationException {
        //Arrange
        //Actuator Service instantiation
        ActuatorFactoryImpl actuatorFactoryImpl = new ActuatorFactoryImpl();
        ActuatorRepositoryMem actuatorRepositoryMem = new ActuatorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        ActuatorTypeRepositoryMem actuatorTypeRepositoryMem = new ActuatorTypeRepositoryMem();
        ActuatorServiceImpl actuatorServiceImpl = new ActuatorServiceImpl(deviceRepositoryMem, actuatorTypeRepositoryMem, actuatorFactoryImpl, actuatorRepositoryMem);

        //Creation of device and addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //Actuator Type Service initialization, so it can populate actuator type repository before adding a specific type
        String path = "actuator.properties";
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactoryImpl();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeServiceImpl(actuatorTypeRepositoryMem,actuatorTypeFactory,path);

        //Controller initialization
        AddActuatorToDeviceCTRL addActuatorToDeviceCTRL = new AddActuatorToDeviceCTRL(actuatorServiceImpl);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceIDVO.getID(), deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //ActuatorTypeDTO initialization
        String actuatorTypeID ="DecimalValueActuator";
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeID);

        //ActuatorDTO initialization with lower limit bigger than upper limit
        String actuatorNameVO = "Actuator1";
        String upperLimit = "0.5";
        String downLimit = "0.6";
        String precision = "0.1";

        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorNameVO, actuatorTypeID, deviceID,downLimit,upperLimit,precision);

        //Act
        boolean result = addActuatorToDeviceCTRL.addActuatorToDevice(actuatorDTO, actuatorTypeDTO, deviceDTO);

        //Assert

        //This assertion asserts that the operation did not succeed
        assertFalse(result);

        //This assertion asserts that actuator repository is empty
        Iterable<Actuator> actuatorIterable = actuatorRepositoryMem.findAll();
        boolean isRepoEmpty = IterableUtils.isEmpty(actuatorIterable);
        assertTrue(isRepoEmpty);
    }

    /**
     * Test method to verify that when attempting to add a Decimal Value Actuator to a Device with a negative precision,
     * the operation returns false and the Actuator repository remains empty.
     * This test initializes Actuator Service, creates a new Device, and saves it to the Device repository.
     * It also initializes the Actuator Type Service to populate the Actuator Type repository,
     * initializes the AddActuatorToDeviceCTRL controller, and creates DTOs for the Device, Actuator Type, and Actuator.
     * The test then calls the addActuatorToDevice method on the controller with the ActuatorDTO,
     * ActuatorTypeDTO, and DeviceDTO, and asserts that the operation returns false, indicating failure.
     * It further asserts that the Actuator repository remains empty, as the addition should not have succeeded
     * due to the negative precision.
     */
    @Test
    void addDecimalValueActuatorToDevice_WhenNegativePrecision_thenReturnFalse() throws ConfigurationException {
        //Arrange
        //Actuator Service instantiation
        ActuatorFactoryImpl actuatorFactoryImpl = new ActuatorFactoryImpl();
        ActuatorRepositoryMem actuatorRepositoryMem = new ActuatorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        ActuatorTypeRepositoryMem actuatorTypeRepositoryMem = new ActuatorTypeRepositoryMem();
        ActuatorServiceImpl actuatorServiceImpl = new ActuatorServiceImpl(deviceRepositoryMem, actuatorTypeRepositoryMem, actuatorFactoryImpl, actuatorRepositoryMem);

        //Creation of device and addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //Actuator Type Service initialization, so it can populate actuator type repository before adding a specific type
        String path = "actuator.properties";
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactoryImpl();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeServiceImpl(actuatorTypeRepositoryMem,actuatorTypeFactory,path);

        //Controller initialization
        AddActuatorToDeviceCTRL addActuatorToDeviceCTRL = new AddActuatorToDeviceCTRL(actuatorServiceImpl);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceIDVO.getID(), deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //ActuatorTypeDTO initialization
        String actuatorTypeID ="DecimalValueActuator";
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeID);

        //ActuatorDTO initialization with negative precision
        String actuatorNameVO = "Actuator1";
        String upperLimit = "0.5";
        String downLimit = "0.3";
        String precision = "-0.1";

        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorNameVO, actuatorTypeID, deviceID,upperLimit,downLimit,precision);

        //Act
        boolean result = addActuatorToDeviceCTRL.addActuatorToDevice(actuatorDTO, actuatorTypeDTO, deviceDTO);

        //Assert

        //This assertion asserts that the operation did not succeed
        assertFalse(result);

        //This assertion asserts that actuator repository is empty
        Iterable<Actuator> actuatorIterable = actuatorRepositoryMem.findAll();
        boolean isRepoEmpty = IterableUtils.isEmpty(actuatorIterable);
        assertTrue(isRepoEmpty);
    }

    /**
     * Test method to verify that when attempting to add a Decimal Value Actuator to a Device
     * with integer value settings (upper limit and down limit),
     * the operation returns false and the Actuator repository remains empty.
     * This test initializes Actuator Service, creates a new Device, and saves it to the Device repository.
     * It also initializes the Actuator Type Service to populate the Actuator Type repository,
     * initializes the AddActuatorToDeviceCTRL controller, and creates DTOs for the Device, Actuator Type, and Actuator.
     * The test then calls the addActuatorToDevice method on the controller with the ActuatorDTO,
     * ActuatorTypeDTO, and DeviceDTO, and asserts that the operation returns false, indicating failure.
     * It further asserts that the Actuator repository remains empty, as the addition should not have succeeded
     * due to the integer value settings provided for a Decimal Value Actuator.
     */
    @Test
    void addDecimalValueActuator_givenIntegerValueSettings_ShouldReturnFalse() throws ConfigurationException {

        //Arrange
        //Actuator Service instantiation
        ActuatorFactoryImpl actuatorFactoryImpl = new ActuatorFactoryImpl();
        ActuatorRepositoryMem actuatorRepositoryMem = new ActuatorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        ActuatorTypeRepositoryMem actuatorTypeRepositoryMem = new ActuatorTypeRepositoryMem();
        ActuatorServiceImpl actuatorServiceImpl = new ActuatorServiceImpl(deviceRepositoryMem, actuatorTypeRepositoryMem, actuatorFactoryImpl, actuatorRepositoryMem);

        //Creation of device and addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //Actuator Type Service initialization, so it can populate actuator type repository before adding a specific type
        String path = "actuator.properties";
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactoryImpl();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeServiceImpl(actuatorTypeRepositoryMem,actuatorTypeFactory,path);

        //Controller initialization
        AddActuatorToDeviceCTRL addActuatorToDeviceCTRL = new AddActuatorToDeviceCTRL(actuatorServiceImpl);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceIDVO.getID(), deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //ActuatorTypeDTO initialization
        String actuatorTypeID ="DecimalValueActuator";
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeID);

        //ActuatorDTO initialization
        String actuatorNameVO = "Actuator1";
        String upperLimit = "1";
        String downLimit = "3";

        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorNameVO, actuatorTypeID, deviceID,upperLimit,downLimit);

        Iterable<Actuator> actuatorIterable = actuatorRepositoryMem.findAll();
        boolean isRepoEmpty = IterableUtils.isEmpty(actuatorIterable);

        //Act
        boolean result = addActuatorToDeviceCTRL.addActuatorToDevice(actuatorDTO, actuatorTypeDTO, deviceDTO);

        // Assert
        assertTrue(isRepoEmpty); //This assertion verifies that actuator repository is empty
        assertFalse(result);
    }

    /**
     * Test method to verify that when attempting to add an Actuator to a Device that is inactive,
     * the operation returns false and the Actuator repository remains empty.
     * This test initializes Actuator Service, creates a new Device, deactivates it, and saves it to the Device repository.
     * It also initializes the Actuator Type Service to populate the Actuator Type repository,
     * initializes the AddActuatorToDeviceCTRL controller, and creates DTOs for the Device, Actuator Type, and Actuator.
     * The test then calls the addActuatorToDevice method on the controller with the ActuatorDTO,
     * ActuatorTypeDTO, and DeviceDTO, and asserts that the operation returns false, indicating failure.
     * It further asserts that the Actuator repository remains empty, as the addition should not have succeeded
     * due to the inactive status of the Device.
     */
    @Test
    void whenAddActuatorToDeviceWithInactiveStatus_thenReturnFalse() throws ConfigurationException {
        //Arrange
        //Actuator Service instantiation
        ActuatorFactoryImpl actuatorFactoryImpl = new ActuatorFactoryImpl();
        ActuatorRepositoryMem actuatorRepositoryMem = new ActuatorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        ActuatorTypeRepositoryMem actuatorTypeRepositoryMem = new ActuatorTypeRepositoryMem();
        ActuatorServiceImpl actuatorServiceImpl = new ActuatorServiceImpl(deviceRepositoryMem, actuatorTypeRepositoryMem, actuatorFactoryImpl, actuatorRepositoryMem);

        //Creation of device and addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        //DeactivateDevice
        device.deactivateDevice();
        deviceRepositoryMem.save(device);

        //Actuator Type Service initialization, so it can populate actuator type repository before adding a specific type
        String path = "actuator.properties";
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactoryImpl();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeServiceImpl(actuatorTypeRepositoryMem,actuatorTypeFactory,path);

        //Controller initialization
        AddActuatorToDeviceCTRL addActuatorToDeviceCTRL = new AddActuatorToDeviceCTRL(actuatorServiceImpl);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceIDVO.getID(), deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //ActuatorTypeDTO initialization
        String actuatorTypeID ="SwitchActuator";
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeID);

        //ActuatorDTO initialization

        String actuatorNameVO = "Actuator1";
        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorNameVO, actuatorTypeID, deviceID);

        //Act
        boolean result = addActuatorToDeviceCTRL.addActuatorToDevice(actuatorDTO, actuatorTypeDTO, deviceDTO);

        //Assert

        //This assertion asserts that the operation did not succeed
        assertFalse(result);

        //This assertion asserts that actuator repository is empty
        Iterable<Actuator> actuatorIterable = actuatorRepositoryMem.findAll();
        boolean isRepoEmpty = IterableUtils.isEmpty(actuatorIterable);
        assertTrue(isRepoEmpty);
    }

    /**
     * Test method to verify that when attempting to add an Actuator to a non-existent Device,
     * the operation returns false and the Actuator repository remains empty.
     * This test initializes Actuator Service and Actuator Type Service,
     * initializes the AddActuatorToDeviceCTRL controller, and creates a DTO for a non-existent Device.
     * It also creates DTOs for the Actuator Type and Actuator to be added.
     * The test then calls the addActuatorToDevice method on the controller with the ActuatorDTO,
     * ActuatorTypeDTO, and non-existent DeviceDTO, and asserts that the operation returns false, indicating failure.
     * It further asserts that the Actuator repository remains empty, as the addition should not have succeeded
     * due to the non-existence of the Device.
     */
    @Test
    void whenAddActuatorToDeviceNonExistent_thenReturnFalse() throws ConfigurationException {
        //Arrange
        //Actuator Service instantiation
        ActuatorFactoryImpl actuatorFactoryImpl = new ActuatorFactoryImpl();
        ActuatorRepositoryMem actuatorRepositoryMem = new ActuatorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        ActuatorTypeRepositoryMem actuatorTypeRepositoryMem = new ActuatorTypeRepositoryMem();
        ActuatorServiceImpl actuatorServiceImpl = new ActuatorServiceImpl(deviceRepositoryMem, actuatorTypeRepositoryMem, actuatorFactoryImpl, actuatorRepositoryMem);


        //Actuator Type Service initialization, so it can populate actuator type repository before adding a specific type
        String path = "actuator.properties";
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactoryImpl();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeServiceImpl(actuatorTypeRepositoryMem,actuatorTypeFactory,path);

        //Controller initialization
        AddActuatorToDeviceCTRL addActuatorToDeviceCTRL = new AddActuatorToDeviceCTRL(actuatorServiceImpl);

        //DeviceDTO initialization
        String deviceID = "DeviceID";
        String deviceName = "DeviceName";
        String deviceModel = "DeviceModel";
        String deviceStatus = "true";
        String roomId = "RoomId";
        DeviceDTO deviceDTO = new DeviceDTO(deviceID,deviceName,deviceModel,deviceStatus,roomId);

        //ActuatorTypeDTO initialization
        String actuatorTypeID ="SwitchActuator";
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeID);

        //ActuatorDTO initialization

        String actuatorNameVO = "Actuator1";
        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorNameVO, actuatorTypeID, deviceID);

        //Act
        boolean result = addActuatorToDeviceCTRL.addActuatorToDevice(actuatorDTO, actuatorTypeDTO, deviceDTO);

        //Assert

        //This assertion asserts that the operation did not succeed
        assertFalse(result);

        //This assertion asserts that actuator repository is empty
        Iterable<Actuator> actuatorIterable = actuatorRepositoryMem.findAll();
        boolean isRepoEmpty = IterableUtils.isEmpty(actuatorIterable);
        assertTrue(isRepoEmpty);
    }

    /**
     * Test method to verify that when attempting to add an Actuator with a non-existent Actuator Type,
     * the operation returns false and the Actuator repository remains empty.
     * This test initializes Actuator Service and Actuator Type Service,
     * initializes the AddActuatorToDeviceCTRL controller, and creates a Device and its DTO.
     * It also creates a DTO for an Actuator with a non-existent Actuator Type.
     * The test then calls the addActuatorToDevice method on the controller with the ActuatorDTO,
     * ActuatorTypeDTO, and DeviceDTO, and asserts that the operation returns false, indicating failure.
     * It further asserts that the Actuator repository remains empty, as the addition should not have succeeded
     * due to the Actuator Type not existing.
     */
    @Test
    void addActuatorToDevice_WhenActuatorTypeDoesNotExist_thenReturnFalse() throws ConfigurationException {
        //Arrange
        //Actuator Service instantiation
        ActuatorFactoryImpl actuatorFactoryImpl = new ActuatorFactoryImpl();
        ActuatorRepositoryMem actuatorRepositoryMem = new ActuatorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        ActuatorTypeRepositoryMem actuatorTypeRepositoryMem = new ActuatorTypeRepositoryMem();
        ActuatorServiceImpl actuatorServiceImpl = new ActuatorServiceImpl(deviceRepositoryMem, actuatorTypeRepositoryMem, actuatorFactoryImpl, actuatorRepositoryMem);

        //Creation of device and addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //Actuator Type Service initialization, so it can populate actuator type repository before adding a specific type
        String path = "actuator.properties";
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactoryImpl();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeServiceImpl(actuatorTypeRepositoryMem,actuatorTypeFactory,path);

        //Controller initialization
        AddActuatorToDeviceCTRL addActuatorToDeviceCTRL = new AddActuatorToDeviceCTRL(actuatorServiceImpl);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceIDVO.getID(), deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //ActuatorTypeDTO initialization
        String actuatorTypeID ="HydraulicActuator";
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeID);

        //ActuatorDTO initialization
        String actuatorNameVO = "Actuator1";
        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorNameVO, actuatorTypeID, deviceID);

        //Act
        boolean result = addActuatorToDeviceCTRL.addActuatorToDevice(actuatorDTO, actuatorTypeDTO, deviceDTO);

        //Assert

        //This assertion asserts that the operation did not succeed
        assertFalse(result);

        //This assertion asserts that actuator repository is empty
        Iterable<Actuator> actuatorIterable = actuatorRepositoryMem.findAll();
        boolean isRepoEmpty = IterableUtils.isEmpty(actuatorIterable);
        assertTrue(isRepoEmpty);
    }

    /**
     * Test method to verify that when attempting to add an Integer Value Actuator with decimal value settings,
     * the operation returns false and the Actuator repository remains empty.
     * This test initializes Actuator Service and Actuator Type Service,
     * initializes the AddActuatorToDeviceCTRL controller, and creates a Device and its DTO.
     * It also creates a DTO for an Integer Value Actuator with decimal value settings.
     * The test then calls the addActuatorToDevice method on the controller with the ActuatorDTO,
     * ActuatorTypeDTO, and DeviceDTO, and asserts that the operation returns false, indicating failure.
     * It further asserts that the Actuator repository remains empty, as the addition should not have succeeded
     * due to the settings being in decimal format for an Integer Value Actuator.
     */
    @Test
    void addIntegerValueActuator_givenDecimalValueSettings_ShouldReturnFalse() throws ConfigurationException {

        //Arrange
        //Actuator Service instantiation
        ActuatorFactoryImpl actuatorFactoryImpl = new ActuatorFactoryImpl();
        ActuatorRepositoryMem actuatorRepositoryMem = new ActuatorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        ActuatorTypeRepositoryMem actuatorTypeRepositoryMem = new ActuatorTypeRepositoryMem();
        ActuatorServiceImpl actuatorServiceImpl = new ActuatorServiceImpl(deviceRepositoryMem, actuatorTypeRepositoryMem, actuatorFactoryImpl, actuatorRepositoryMem);

        //Creation of device and addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //Actuator Type Service initialization, so it can populate actuator type repository before adding a specific type
        String path = "actuator.properties";
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactoryImpl();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeServiceImpl(actuatorTypeRepositoryMem,actuatorTypeFactory,path);

        //Controller initialization
        AddActuatorToDeviceCTRL addActuatorToDeviceCTRL = new AddActuatorToDeviceCTRL(actuatorServiceImpl);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceIDVO.getID(), deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //ActuatorTypeDTO initialization
        String actuatorTypeID ="IntegerValueActuator";
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeID);

        //ActuatorDTO initialization
        String actuatorNameVO = "Actuator1";
        String upperLimit = "0.9";
        String downLimit = "0.1";
        String precision = "0.1";

        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorNameVO, actuatorTypeID, deviceID,upperLimit,downLimit,precision);
        Iterable<Actuator> actuatorIterable = actuatorRepositoryMem.findAll();

        //Act
        boolean result = addActuatorToDeviceCTRL.addActuatorToDevice(actuatorDTO, actuatorTypeDTO, deviceDTO);
        boolean isRepoEmpty = IterableUtils.isEmpty(actuatorIterable);

        // Assert
        assertTrue(isRepoEmpty); //This assertion verifies that actuator repository is empty
        assertFalse(result);
    }
}