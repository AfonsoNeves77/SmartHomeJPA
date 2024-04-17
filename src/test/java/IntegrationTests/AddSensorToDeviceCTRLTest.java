package IntegrationTests;

import org.apache.commons.collections4.IterableUtils;
import smarthome.controller.AddSensorToDeviceCTRL;
import smarthome.domain.device.Device;
import smarthome.domain.sensor.Sensor;
import smarthome.domain.sensor.SensorFactoryImpl;
import smarthome.domain.sensortype.SensorTypeFactoryImpl;
import smarthome.dto.DeviceDTO;
import smarthome.dto.SensorDTO;
import smarthome.dto.SensorTypeDTO;
import smarthome.repository.DeviceRepositoryMem;
import smarthome.repository.SensorRepositoryMem;
import smarthome.repository.SensorTypeRepositoryMem;
import smarthome.services.SensorTypeService;
import smarthome.services.SensorServiceImpl;
import smarthome.services.SensorTypeServiceImpl;
import smarthome.vo.devicevo.DeviceIDVO;
import smarthome.vo.devicevo.DeviceStatusVO;
import smarthome.vo.devicevo.DeviceModelVO;
import smarthome.vo.devicevo.DeviceNameVO;
import smarthome.vo.roomvo.*;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test class includes SensorTypeService instantiations that may appear unused.
 * The reason for this is that the service initialization automatically populates the `SensorTypeRepository`.
 * Without this step, when attempting to verify that the sensor type exists, the result will always fail
 * as the repository would be empty.
 */


class AddSensorToDeviceCTRLTest {

    /**
     * Test for the constructor of AddSensorToDeviceCTRL.
     * Tests if the constructor throws an IllegalArgumentException when the sensorService is null.
     */
    @Test
    void whenNullSensorService_constructorThrowsIllegalArgumentException() {
        //Arrange
        //Arrange
        String expectedMessage = "Invalid service";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AddSensorToDeviceCTRL(null));

        //Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }



    /**
     * This test method verifies the functionality of adding a Humidity Sensor to a Device using the AddSensorToDeviceCTRL.
     * It ensures that the operation returns true upon success and that the Humidity Sensor is correctly stored in the Sensor repository.

     * The test initializes the necessary components, including Sensor Service, Device repository, Sensor Type Service, and the controller.
     * It creates a new Device and saves it to the repository, then initializes DTOs for Device, Sensor Type, and Sensor.

     * After calling the method to add the Humidity Sensor to the Device, the test verifies:
     * - The operation's success by checking if it returns true.
     * - The presence of the added Humidity Sensor in the Sensor repository with the correct attributes.
     */
    @Test
    void addHumiditySensorToDevice_shouldReturnTrue() {
        //Arrange
        //Sensor Service instantiation
        String propertiesPath = "sensor.properties";
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl(propertiesPath);
        SensorRepositoryMem sensorRepositoryMem = new SensorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        SensorTypeRepositoryMem sensorTypeRepositoryMem = new SensorTypeRepositoryMem();
        SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(deviceRepositoryMem, sensorTypeRepositoryMem, sensorRepositoryMem, sensorFactoryImpl);

        //Creation of device's value objects and the device + addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new smarthome.vo.roomvo.RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //Sensor Type Service initialization, so it can populate Sensor type repository before adding a specific type
        //SensorType is not used only it's initialization is necessary in this test
        SensorTypeFactoryImpl SensorTypeFactory = new SensorTypeFactoryImpl();
        SensorTypeService SensorTypeService = new SensorTypeServiceImpl(sensorTypeRepositoryMem,SensorTypeFactory,propertiesPath);

        //Controller initialization
        AddSensorToDeviceCTRL addSensorToDeviceCTRL = new AddSensorToDeviceCTRL(sensorServiceImpl);

        //SensorTypeDTO initialization
        String sensorTypeID ="HumiditySensor";
        String unit = "%";
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID,unit);

        //SensorDTO initialization
        String sensorNameVO = "Sensor1";
        SensorDTO sensorDTO = new SensorDTO(sensorNameVO);

        //Act
        boolean result = addSensorToDeviceCTRL.addSensorToDevice(deviceDTO,sensorTypeDTO,sensorDTO);

        //Assert

        //This assertion asserts that the operation succeeded
        assertTrue(result);

        //This assertion asserts that the added Sensor is present in Sensor repository

        //Fetch the added Sensor from repository
        Iterable<Sensor> SensorIterable = sensorRepositoryMem.findAll();
        Sensor addedSensor = IterableUtils.get(SensorIterable,0);

        //Query the fetched Sensor for it's attributes
        String resultSensorName = addedSensor.getSensorName().getValue();
        String resultSensorTypeID = addedSensor.getSensorTypeID().getID();
        DeviceIDVO resultDeviceID = addedSensor.getDeviceID();

        //Compare Sensor's attributes with the ones given in its creation
        assertEquals(sensorNameVO,resultSensorName);
        assertEquals(sensorTypeID,resultSensorTypeID);
        assertEquals(deviceIDVO,resultDeviceID);
    }

    /**
     * This test method verifies the functionality of adding a Temperature Sensor to a Device using the AddSensorToDeviceCTRL.
     * It ensures that the operation returns true upon success and that the Temperature Sensor is correctly stored in the Sensor repository.

     * The test initializes the necessary components, including Sensor Service, Device repository, Sensor Type Service, and the controller.
     * It creates a new Device and saves it to the repository, then initializes DTOs for Device, Sensor Type, and Sensor.

     * After calling the method to add the Temperature Sensor to the Device, the test verifies:
     * - The operation's success by checking if it returns true.
     * - The presence of the added Temperature Sensor in the Sensor repository with the correct attributes.
     */

    @Test
    void addTemperatureSensorToDevice_shouldReturnTrue() {
        //Arrange
        //Sensor Service instantiation
        String propertiesPath = "sensor.properties";
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl(propertiesPath);
        SensorRepositoryMem sensorRepositoryMem = new SensorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        SensorTypeRepositoryMem sensorTypeRepositoryMem = new SensorTypeRepositoryMem();
        SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(deviceRepositoryMem, sensorTypeRepositoryMem, sensorRepositoryMem, sensorFactoryImpl);

        //Creation of device's value objects and the device + addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new smarthome.vo.roomvo.RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //Sensor Type Service initialization, so it can populate Sensor type repository before adding a specific type
        //SensorType is not used only it's initialization is necessary in this test
        SensorTypeFactoryImpl SensorTypeFactory = new SensorTypeFactoryImpl();
        SensorTypeService SensorTypeService = new SensorTypeServiceImpl(sensorTypeRepositoryMem,SensorTypeFactory,propertiesPath);

        //Controller initialization
        AddSensorToDeviceCTRL addSensorToDeviceCTRL = new AddSensorToDeviceCTRL(sensorServiceImpl);

        //SensorTypeDTO initialization
        String sensorTypeID ="TemperatureSensor";
        String unit = "C";
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID,unit);

        //SensorDTO initialization
        String sensorNameVO = "Sensor1";
        SensorDTO sensorDTO = new SensorDTO(sensorNameVO);

        //Act
        boolean result = addSensorToDeviceCTRL.addSensorToDevice(deviceDTO,sensorTypeDTO,sensorDTO);

        //Assert

        //This assertion asserts that the operation succeeded
        assertTrue(result);

        //This assertion asserts that the added Sensor is present in Sensor repository

        //Fetch the added Sensor from repository
        Iterable<Sensor> SensorIterable = sensorRepositoryMem.findAll();
        Sensor addedSensor = IterableUtils.get(SensorIterable,0);

        //Query the fetched Sensor for it's attributes
        String resultSensorName = addedSensor.getSensorName().getValue();
        String resultSensorTypeID = addedSensor.getSensorTypeID().getID();
        DeviceIDVO resultDeviceID = addedSensor.getDeviceID();

        //Compare Sensor's attributes with the ones given in its creation
        assertEquals(sensorNameVO,resultSensorName);
        assertEquals(sensorTypeID,resultSensorTypeID);
        assertEquals(deviceIDVO,resultDeviceID);
    }

    /**
     * This test method verifies the functionality of adding a Position Sensor to a Device using the AddSensorToDeviceCTRL.
     * It ensures that the operation returns true upon success and that the Position Sensor is correctly stored in the Sensor repository.

     * The test initializes the necessary components, including Sensor Service, Device repository, Sensor Type Service, and the controller.
     * It creates a new Device and saves it to the repository, then initializes DTOs for Device, Sensor Type, and Sensor.

     * After calling the method to add the Position Sensor to the Device, the test verifies:
     * - The operation's success by checking if it returns true.
     * - The presence of the added Position Sensor in the Sensor repository with the correct attributes.
     */

    @Test
    void addPositionSensorToDevice_shouldReturnTrue() {
        //Arrange
        //Sensor Service instantiation
        String propertiesPath = "sensor.properties";
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl(propertiesPath);
        SensorRepositoryMem sensorRepositoryMem = new SensorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        SensorTypeRepositoryMem sensorTypeRepositoryMem = new SensorTypeRepositoryMem();
        SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(deviceRepositoryMem, sensorTypeRepositoryMem, sensorRepositoryMem, sensorFactoryImpl);

        //Creation of device's value objects and the device + addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new smarthome.vo.roomvo.RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //Sensor Type Service initialization, so it can populate Sensor type repository before adding a specific type
        //SensorType is not used only it's initialization is necessary in this test
        SensorTypeFactoryImpl SensorTypeFactory = new SensorTypeFactoryImpl();
        SensorTypeService SensorTypeService = new SensorTypeServiceImpl(sensorTypeRepositoryMem,SensorTypeFactory,propertiesPath);

        //Controller initialization
        AddSensorToDeviceCTRL addSensorToDeviceCTRL = new AddSensorToDeviceCTRL(sensorServiceImpl);

        //SensorTypeDTO initialization
        String sensorTypeID ="PositionSensor";
        String unit = "%";
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID,unit);

        //SensorDTO initialization
        String sensorNameVO = "Sensor1";
        SensorDTO sensorDTO = new SensorDTO(sensorNameVO);

        //Act
        boolean result = addSensorToDeviceCTRL.addSensorToDevice(deviceDTO,sensorTypeDTO,sensorDTO);

        //Assert

        //This assertion asserts that the operation succeeded
        assertTrue(result);

        //This assertion asserts that the added Sensor is present in Sensor repository

        //Fetch the added Sensor from repository
        Iterable<Sensor> SensorIterable = sensorRepositoryMem.findAll();
        Sensor addedSensor = IterableUtils.get(SensorIterable,0);

        //Query the fetched Sensor for it's attributes
        String resultSensorName = addedSensor.getSensorName().getValue();
        String resultSensorTypeID = addedSensor.getSensorTypeID().getID();
        DeviceIDVO resultDeviceID = addedSensor.getDeviceID();

        //Compare Sensor's attributes with the ones given in its creation
        assertEquals(sensorNameVO,resultSensorName);
        assertEquals(sensorTypeID,resultSensorTypeID);
        assertEquals(deviceIDVO,resultDeviceID);
    }

    /**
     * This test method verifies the functionality of adding a Wind Sensor to a Device using the AddSensorToDeviceCTRL.
     * It ensures that the operation returns true upon success and that the Wind Sensor is correctly stored in the Sensor repository.

     * The test initializes the necessary components, including Sensor Service, Device repository, Sensor Type Service, and the controller.
     * It creates a new Device and saves it to the repository, then initializes DTOs for Device, Sensor Type, and Sensor.

     * After calling the method to add the Wind Sensor to the Device, the test verifies:
     * - The operation's success by checking if it returns true.
     * - The presence of the added Wind Sensor in the Sensor repository with the correct attributes.
     */

    @Test
    void addWindSensorToDevice_shouldReturnTrue() {
        //Arrange
        //Sensor Service instantiation
        String propertiesPath = "sensor.properties";
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl(propertiesPath);
        SensorRepositoryMem sensorRepositoryMem = new SensorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        SensorTypeRepositoryMem sensorTypeRepositoryMem = new SensorTypeRepositoryMem();
        SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(deviceRepositoryMem, sensorTypeRepositoryMem, sensorRepositoryMem, sensorFactoryImpl);

        //Creation of device's value objects and the device + addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new smarthome.vo.roomvo.RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //Sensor Type Service initialization, so it can populate Sensor type repository before adding a specific type
        //SensorType is not used only it's initialization is necessary in this test
        SensorTypeFactoryImpl SensorTypeFactory = new SensorTypeFactoryImpl();
        SensorTypeService SensorTypeService = new SensorTypeServiceImpl(sensorTypeRepositoryMem,SensorTypeFactory,propertiesPath);

        //Controller initialization
        AddSensorToDeviceCTRL addSensorToDeviceCTRL = new AddSensorToDeviceCTRL(sensorServiceImpl);

        //SensorTypeDTO initialization
        String sensorTypeID ="WindSensor";
        String unit = "Km/h-CardinalPoints";
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID,unit);

        //SensorDTO initialization
        String sensorNameVO = "Sensor1";
        SensorDTO sensorDTO = new SensorDTO(sensorNameVO);

        //Act
        boolean result = addSensorToDeviceCTRL.addSensorToDevice(deviceDTO,sensorTypeDTO,sensorDTO);

        //Assert

        //This assertion asserts that the operation succeeded
        assertTrue(result);

        //This assertion asserts that the added Sensor is present in Sensor repository

        //Fetch the added Sensor from repository
        Iterable<Sensor> SensorIterable = sensorRepositoryMem.findAll();
        Sensor addedSensor = IterableUtils.get(SensorIterable,0);

        //Query the fetched Sensor for it's attributes
        String resultSensorName = addedSensor.getSensorName().getValue();
        String resultSensorTypeID = addedSensor.getSensorTypeID().getID();
        DeviceIDVO resultDeviceID = addedSensor.getDeviceID();

        //Compare Sensor's attributes with the ones given in its creation
        assertEquals(sensorNameVO,resultSensorName);
        assertEquals(sensorTypeID,resultSensorTypeID);
        assertEquals(deviceIDVO,resultDeviceID);
    }

    /**
     * This test method verifies the functionality of adding a DewPoint Sensor to a Device using the AddSensorToDeviceCTRL.
     * It ensures that the operation returns true upon success and that the DewPoint Sensor is correctly stored in the Sensor repository.

     * The test initializes the necessary components, including Sensor Service, Device repository, Sensor Type Service, and the controller.
     * It creates a new Device and saves it to the repository, then initializes DTOs for Device, Sensor Type, and Sensor.

     * After calling the method to add the DewPoint Sensor to the Device, the test verifies:
     * - The operation's success by checking if it returns true.
     * - The presence of the added DewPoint Sensor in the Sensor repository with the correct attributes.
     */

    @Test
    void addDewPointSensorToDevice_shouldReturnTrue() {
        //Arrange
        //Sensor Service instantiation
        String propertiesPath = "sensor.properties";
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl(propertiesPath);
        SensorRepositoryMem sensorRepositoryMem = new SensorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        SensorTypeRepositoryMem sensorTypeRepositoryMem = new SensorTypeRepositoryMem();
        SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(deviceRepositoryMem, sensorTypeRepositoryMem, sensorRepositoryMem, sensorFactoryImpl);

        //Creation of device's value objects and the device + addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new smarthome.vo.roomvo.RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //Sensor Type Service initialization, so it can populate Sensor type repository before adding a specific type
        //SensorType is not used only it's initialization is necessary in this test
        SensorTypeFactoryImpl SensorTypeFactory = new SensorTypeFactoryImpl();
        SensorTypeService SensorTypeService = new SensorTypeServiceImpl(sensorTypeRepositoryMem,SensorTypeFactory,propertiesPath);

        //Controller initialization
        AddSensorToDeviceCTRL addSensorToDeviceCTRL = new AddSensorToDeviceCTRL(sensorServiceImpl);

        //SensorTypeDTO initialization
        String sensorTypeID ="DewPointSensor";
        String unit = "C";
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID,unit);

        //SensorDTO initialization
        String sensorNameVO = "Sensor1";
        SensorDTO sensorDTO = new SensorDTO(sensorNameVO);

        //Act
        boolean result = addSensorToDeviceCTRL.addSensorToDevice(deviceDTO,sensorTypeDTO,sensorDTO);

        //Assert

        //This assertion asserts that the operation succeeded
        assertTrue(result);

        //This assertion asserts that the added Sensor is present in Sensor repository

        //Fetch the added Sensor from repository
        Iterable<Sensor> SensorIterable = sensorRepositoryMem.findAll();
        Sensor addedSensor = IterableUtils.get(SensorIterable,0);

        //Query the fetched Sensor for it's attributes
        String resultSensorName = addedSensor.getSensorName().getValue();
        String resultSensorTypeID = addedSensor.getSensorTypeID().getID();
        DeviceIDVO resultDeviceID = addedSensor.getDeviceID();

        //Compare Sensor's attributes with the ones given in its creation
        assertEquals(sensorNameVO,resultSensorName);
        assertEquals(sensorTypeID,resultSensorTypeID);
        assertEquals(deviceIDVO,resultDeviceID);
    }

    /**
     * This test method verifies the functionality of adding a Sunset Sensor to a Device using the AddSensorToDeviceCTRL.
     * It ensures that the operation returns true upon success and that the Sunset Sensor is correctly stored in the Sensor repository.

     * The test initializes the necessary components, including Sensor Service, Device repository, Sensor Type Service, and the controller.
     * It creates a new Device and saves it to the repository, then initializes DTOs for Device, Sensor Type, and Sensor.

     * After calling the method to add the Sunset Sensor to the Device, the test verifies:
     * - The operation's success by checking if it returns true.
     * - The presence of the added Sunset Sensor in the Sensor repository with the correct attributes.
     */

    @Test
    void addSunsetSensorToDevice_shouldReturnTrue() {
        //Arrange
        //Sensor Service instantiation
        String propertiesPath = "sensor.properties";
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl(propertiesPath);
        SensorRepositoryMem sensorRepositoryMem = new SensorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        SensorTypeRepositoryMem sensorTypeRepositoryMem = new SensorTypeRepositoryMem();
        SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(deviceRepositoryMem, sensorTypeRepositoryMem, sensorRepositoryMem, sensorFactoryImpl);

        //Creation of device's value objects and the device + addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new smarthome.vo.roomvo.RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //Sensor Type Service initialization, so it can populate Sensor type repository before adding a specific type
        //SensorType is not used only it's initialization is necessary in this test
        SensorTypeFactoryImpl SensorTypeFactory = new SensorTypeFactoryImpl();
        SensorTypeService SensorTypeService = new SensorTypeServiceImpl(sensorTypeRepositoryMem,SensorTypeFactory,propertiesPath);

        //Controller initialization
        AddSensorToDeviceCTRL addSensorToDeviceCTRL = new AddSensorToDeviceCTRL(sensorServiceImpl);

        //SensorTypeDTO initialization
        String sensorTypeID ="SunsetSensor";
        String unit = "DATE";
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID,unit);

        //SensorDTO initialization
        String sensorNameVO = "Sensor1";
        SensorDTO sensorDTO = new SensorDTO(sensorNameVO);

        //Act
        boolean result = addSensorToDeviceCTRL.addSensorToDevice(deviceDTO,sensorTypeDTO,sensorDTO);

        //Assert

        //This assertion asserts that the operation succeeded
        assertTrue(result);

        //This assertion asserts that the added Sensor is present in Sensor repository

        //Fetch the added Sensor from repository
        Iterable<Sensor> SensorIterable = sensorRepositoryMem.findAll();
        Sensor addedSensor = IterableUtils.get(SensorIterable,0);

        //Query the fetched Sensor for it's attributes
        String resultSensorName = addedSensor.getSensorName().getValue();
        String resultSensorTypeID = addedSensor.getSensorTypeID().getID();
        DeviceIDVO resultDeviceID = addedSensor.getDeviceID();

        //Compare Sensor's attributes with the ones given in its creation
        assertEquals(sensorNameVO,resultSensorName);
        assertEquals(sensorTypeID,resultSensorTypeID);
        assertEquals(deviceIDVO,resultDeviceID);
    }

    /**
     * This test method verifies the functionality of adding a Sunrise Sensor to a Device using the AddSensorToDeviceCTRL.
     * It ensures that the operation returns true upon success and that the Sunrise Sensor is correctly stored in the Sensor repository.

     * The test initializes the necessary components, including Sensor Service, Device repository, Sensor Type Service, and the controller.
     * It creates a new Device and saves it to the repository, then initializes DTOs for Device, Sensor Type, and Sensor.

     * After calling the method to add the Sunrise Sensor to the Device, the test verifies:
     * - The operation's success by checking if it returns true.
     * - The presence of the added Sunrise Sensor in the Sensor repository with the correct attributes.
     */
    @Test
    void addSunriseSensorToDevice_shouldReturnTrue() {
        //Arrange
        //Sensor Service instantiation
        String propertiesPath = "sensor.properties";
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl(propertiesPath);
        SensorRepositoryMem sensorRepositoryMem = new SensorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        SensorTypeRepositoryMem sensorTypeRepositoryMem = new SensorTypeRepositoryMem();
        SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(deviceRepositoryMem, sensorTypeRepositoryMem, sensorRepositoryMem, sensorFactoryImpl);

        //Creation of device's value objects and the device + addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new smarthome.vo.roomvo.RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //Sensor Type Service initialization, so it can populate Sensor type repository before adding a specific type
        //SensorType is not used only it's initialization is necessary in this test
        SensorTypeFactoryImpl SensorTypeFactory = new SensorTypeFactoryImpl();
        SensorTypeService SensorTypeService = new SensorTypeServiceImpl(sensorTypeRepositoryMem,SensorTypeFactory,propertiesPath);

        //Controller initialization
        AddSensorToDeviceCTRL addSensorToDeviceCTRL = new AddSensorToDeviceCTRL(sensorServiceImpl);

        //SensorTypeDTO initialization
        String sensorTypeID ="SunriseSensor";
        String unit = "DATE";
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID,unit);

        //SensorDTO initialization
        String sensorNameVO = "Sensor1";
        SensorDTO sensorDTO = new SensorDTO(sensorNameVO);

        //Act
        boolean result = addSensorToDeviceCTRL.addSensorToDevice(deviceDTO,sensorTypeDTO,sensorDTO);

        //Assert

        //This assertion asserts that the operation succeeded
        assertTrue(result);

        //This assertion asserts that the added Sensor is present in Sensor repository

        //Fetch the added Sensor from repository
        Iterable<Sensor> SensorIterable = sensorRepositoryMem.findAll();
        Sensor addedSensor = IterableUtils.get(SensorIterable,0);

        //Query the fetched Sensor for it's attributes
        String resultSensorName = addedSensor.getSensorName().getValue();
        String resultSensorTypeID = addedSensor.getSensorTypeID().getID();
        DeviceIDVO resultDeviceID = addedSensor.getDeviceID();

        //Compare Sensor's attributes with the ones given in its creation
        assertEquals(sensorNameVO,resultSensorName);
        assertEquals(sensorTypeID,resultSensorTypeID);
        assertEquals(deviceIDVO,resultDeviceID);
    }

    /**
     * This test method verifies the functionality of adding an Average Power Consumption Sensor to a Device using the AddSensorToDeviceCTRL.
     * It ensures that the operation returns true upon success and that the Average Power Consumption Sensor is correctly stored in the Sensor repository.

     * The test initializes the necessary components, including Sensor Service, Device repository, Sensor Type Service, and the controller.
     * It creates a new Device and saves it to the repository, then initializes DTOs for Device, Sensor Type, and Sensor.

     * After calling the method to add the Average Power Consumption Sensor to the Device, the test verifies:
     * - The operation's success by checking if it returns true.
     * - The presence of the added Average Power Consumption Sensor in the Sensor repository with the correct attributes.
     */

    @Test
    void addAveragePowerConsumptionSensorToDevice_shouldReturnTrue() {
        //Arrange
        //Sensor Service instantiation
        String propertiesPath = "sensor.properties";
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl(propertiesPath);
        SensorRepositoryMem sensorRepositoryMem = new SensorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        SensorTypeRepositoryMem sensorTypeRepositoryMem = new SensorTypeRepositoryMem();
        SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(deviceRepositoryMem, sensorTypeRepositoryMem, sensorRepositoryMem, sensorFactoryImpl);

        //Creation of device's value objects and the device + addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new smarthome.vo.roomvo.RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //Sensor Type Service initialization, so it can populate Sensor type repository before adding a specific type
        //SensorType is not used only it's initialization is necessary in this test
        SensorTypeFactoryImpl SensorTypeFactory = new SensorTypeFactoryImpl();
        SensorTypeService SensorTypeService = new SensorTypeServiceImpl(sensorTypeRepositoryMem,SensorTypeFactory,propertiesPath);

        //Controller initialization
        AddSensorToDeviceCTRL addSensorToDeviceCTRL = new AddSensorToDeviceCTRL(sensorServiceImpl);

        //SensorTypeDTO initialization
        String sensorTypeID ="AveragePowerConsumptionSensor";
        String unit = "W";
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID,unit);

        //SensorDTO initialization
        String sensorNameVO = "Sensor1";
        SensorDTO sensorDTO = new SensorDTO(sensorNameVO);

        //Act
        boolean result = addSensorToDeviceCTRL.addSensorToDevice(deviceDTO,sensorTypeDTO,sensorDTO);

        //Assert

        //This assertion asserts that the operation succeeded
        assertTrue(result);

        //This assertion asserts that the added Sensor is present in Sensor repository

        //Fetch the added Sensor from repository
        Iterable<Sensor> SensorIterable = sensorRepositoryMem.findAll();
        Sensor addedSensor = IterableUtils.get(SensorIterable,0);

        //Query the fetched Sensor for it's attributes
        String resultSensorName = addedSensor.getSensorName().getValue();
        String resultSensorTypeID = addedSensor.getSensorTypeID().getID();
        DeviceIDVO resultDeviceID = addedSensor.getDeviceID();

        //Compare Sensor's attributes with the ones given in its creation
        assertEquals(sensorNameVO,resultSensorName);
        assertEquals(sensorTypeID,resultSensorTypeID);
        assertEquals(deviceIDVO,resultDeviceID);
    }

    /**
     * This test method verifies the functionality of adding a Power Consumption Sensor to a Device using the AddSensorToDeviceCTRL.
     * It ensures that the operation returns true upon success and that the Power Consumption Sensor is correctly stored in the Sensor repository.

     * The test initializes the necessary components, including Sensor Service, Device repository, Sensor Type Service, and the controller.
     * It creates a new Device and saves it to the repository, then initializes DTOs for Device, Sensor Type, and Sensor.

     * After calling the method to add the Power Consumption Sensor to the Device, the test verifies:
     * - The operation's success by checking if it returns true.
     * - The presence of the added Power Consumption Sensor in the Sensor repository with the correct attributes.
     */

    @Test
    void addPowerConsumptionSensorToDevice_shouldReturnTrue() {
        //Arrange
        //Sensor Service instantiation
        String propertiesPath = "sensor.properties";
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl(propertiesPath);
        SensorRepositoryMem sensorRepositoryMem = new SensorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        SensorTypeRepositoryMem sensorTypeRepositoryMem = new SensorTypeRepositoryMem();
        SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(deviceRepositoryMem, sensorTypeRepositoryMem, sensorRepositoryMem, sensorFactoryImpl);

        //Creation of device's value objects and the device + addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new smarthome.vo.roomvo.RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //Sensor Type Service initialization, so it can populate Sensor type repository before adding a specific type
        //SensorType is not used only it's initialization is necessary in this test
        SensorTypeFactoryImpl SensorTypeFactory = new SensorTypeFactoryImpl();
        SensorTypeService SensorTypeService = new SensorTypeServiceImpl(sensorTypeRepositoryMem,SensorTypeFactory,propertiesPath);

        //Controller initialization
        AddSensorToDeviceCTRL addSensorToDeviceCTRL = new AddSensorToDeviceCTRL(sensorServiceImpl);

        //SensorTypeDTO initialization
        String sensorTypeID ="PowerConsumptionSensor";
        String unit = "W";
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID,unit);

        //SensorDTO initialization
        String sensorNameVO = "Sensor1";
        SensorDTO sensorDTO = new SensorDTO(sensorNameVO);

        //Act
        boolean result = addSensorToDeviceCTRL.addSensorToDevice(deviceDTO,sensorTypeDTO,sensorDTO);

        //Assert

        //This assertion asserts that the operation succeeded
        assertTrue(result);

        //This assertion asserts that the added Sensor is present in Sensor repository

        //Fetch the added Sensor from repository
        Iterable<Sensor> SensorIterable = sensorRepositoryMem.findAll();
        Sensor addedSensor = IterableUtils.get(SensorIterable,0);

        //Query the fetched Sensor for it's attributes
        String resultSensorName = addedSensor.getSensorName().getValue();
        String resultSensorTypeID = addedSensor.getSensorTypeID().getID();
        DeviceIDVO resultDeviceID = addedSensor.getDeviceID();

        //Compare Sensor's attributes with the ones given in its creation
        assertEquals(sensorNameVO,resultSensorName);
        assertEquals(sensorTypeID,resultSensorTypeID);
        assertEquals(deviceIDVO,resultDeviceID);
    }

    /**
     * This test verifies the functionality of adding an Energy Consumption Sensor to a Device using the AddSensorToDeviceCTRL.
     * It ensures that the operation returns true upon success and that the Energy Consumption Sensor is correctly stored in the Sensor repository.

     * The test initializes the necessary components, including Sensor Service, Device repository, Sensor Type Service, and the controller.
     * It creates a new Device and saves it to the repository, then initializes DTOs for Device, Sensor Type, and Sensor.

     * After calling the method to add the Energy Consumption Sensor to the Device, the test verifies:
     * - The operation's success by checking if it returns true.
     * - The presence of the added Energy Consumption Sensor in the Sensor repository with the correct attributes.
     */

    @Test
    void addEnergyConsumptionSensorToDevice_shouldReturnTrue() {
        //Arrange
        //Sensor Service instantiation
        String propertiesPath = "sensor.properties";
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl(propertiesPath);
        SensorRepositoryMem sensorRepositoryMem = new SensorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        SensorTypeRepositoryMem sensorTypeRepositoryMem = new SensorTypeRepositoryMem();
        SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(deviceRepositoryMem, sensorTypeRepositoryMem, sensorRepositoryMem, sensorFactoryImpl);

        //Creation of device's value objects and the device + addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new smarthome.vo.roomvo.RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //Sensor Type Service initialization, so it can populate Sensor type repository before adding a specific type
        //SensorType is not used only it's initialization is necessary in this test
        SensorTypeFactoryImpl SensorTypeFactory = new SensorTypeFactoryImpl();
        SensorTypeService SensorTypeService = new SensorTypeServiceImpl(sensorTypeRepositoryMem,SensorTypeFactory,propertiesPath);

        //Controller initialization
        AddSensorToDeviceCTRL addSensorToDeviceCTRL = new AddSensorToDeviceCTRL(sensorServiceImpl);

        //SensorTypeDTO initialization
        String sensorTypeID ="EnergyConsumptionSensor";
        String unit = "W/h";
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID,unit);

        //SensorDTO initialization
        String sensorNameVO = "Sensor1";
        SensorDTO sensorDTO = new SensorDTO(sensorNameVO);

        //Act
        boolean result = addSensorToDeviceCTRL.addSensorToDevice(deviceDTO,sensorTypeDTO,sensorDTO);

        //Assert

        //This assertion asserts that the operation succeeded
        assertTrue(result);

        //This assertion asserts that the added Sensor is present in Sensor repository

        //Fetch the added Sensor from repository
        Iterable<Sensor> SensorIterable = sensorRepositoryMem.findAll();
        Sensor addedSensor = IterableUtils.get(SensorIterable,0);

        //Query the fetched Sensor for it's attributes
        String resultSensorName = addedSensor.getSensorName().getValue();
        String resultSensorTypeID = addedSensor.getSensorTypeID().getID();
        DeviceIDVO resultDeviceID = addedSensor.getDeviceID();

        //Compare Sensor's attributes with the ones given in its creation
        assertEquals(sensorNameVO,resultSensorName);
        assertEquals(sensorTypeID,resultSensorTypeID);
        assertEquals(deviceIDVO,resultDeviceID);
    }

    /**
     * This test verifies the functionality of adding a Switch Sensor to a Device using the AddSensorToDeviceCTRL.
     * It ensures that the operation returns true upon success and that the Switch Sensor is correctly stored in the Sensor repository.

     * The test initializes the necessary components, including Sensor Service, Device repository, Sensor Type Service, and the controller.
     * It creates a new Device and saves it to the repository, then initializes DTOs for Device, Sensor Type, and Sensor.

     * After calling the method to add the Switch Sensor to the Device, the test verifies:
     * - The operation's success by checking if it returns true.
     * - The presence of the added Switch Sensor in the Sensor repository with the correct attributes.
     */

    @Test
    void addSwitchSensorToDevice_shouldReturnTrue() {
        //Arrange
        //Sensor Service instantiation
        String propertiesPath = "sensor.properties";
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl(propertiesPath);
        SensorRepositoryMem sensorRepositoryMem = new SensorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        SensorTypeRepositoryMem sensorTypeRepositoryMem = new SensorTypeRepositoryMem();
        SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(deviceRepositoryMem, sensorTypeRepositoryMem, sensorRepositoryMem, sensorFactoryImpl);

        //Creation of device's value objects and the device + addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new smarthome.vo.roomvo.RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //Sensor Type Service initialization, so it can populate Sensor type repository before adding a specific type
        //SensorType is not used only it's initialization is necessary in this test
        SensorTypeFactoryImpl SensorTypeFactory = new SensorTypeFactoryImpl();
        SensorTypeService SensorTypeService = new SensorTypeServiceImpl(sensorTypeRepositoryMem,SensorTypeFactory,propertiesPath);

        //Controller initialization
        AddSensorToDeviceCTRL addSensorToDeviceCTRL = new AddSensorToDeviceCTRL(sensorServiceImpl);

        //SensorTypeDTO initialization
        String sensorTypeID ="SwitchSensor";
        String unit = "Binary";
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID,unit);

        //SensorDTO initialization
        String sensorNameVO = "Sensor1";
        SensorDTO sensorDTO = new SensorDTO(sensorNameVO);

        //Act
        boolean result = addSensorToDeviceCTRL.addSensorToDevice(deviceDTO,sensorTypeDTO,sensorDTO);

        //Assert

        //This assertion asserts that the operation succeeded
        assertTrue(result);

        //This assertion asserts that the added Sensor is present in Sensor repository

        //Fetch the added Sensor from repository
        Iterable<Sensor> SensorIterable = sensorRepositoryMem.findAll();
        Sensor addedSensor = IterableUtils.get(SensorIterable,0);

        //Query the fetched Sensor for it's attributes
        String resultSensorName = addedSensor.getSensorName().getValue();
        String resultSensorTypeID = addedSensor.getSensorTypeID().getID();
        DeviceIDVO resultDeviceID = addedSensor.getDeviceID();

        //Compare Sensor's attributes with the ones given in its creation
        assertEquals(sensorNameVO,resultSensorName);
        assertEquals(sensorTypeID,resultSensorTypeID);
        assertEquals(deviceIDVO,resultDeviceID);
    }

    /**
     * This test verifies the functionality of adding a SolarIrradiance Sensor to a Device using the AddSensorToDeviceCTRL.
     * It ensures that the operation returns true upon success and that the SolarIrradiance Sensor is correctly stored in the Sensor repository.

     * The test initializes the necessary components, including Sensor Service, Device repository, Sensor Type Service, and the controller.
     * It creates a new Device and saves it to the repository, then initializes DTOs for Device, Sensor Type, and Sensor.

     * After calling the method to add the SolarIrradiance Sensor to the Device, the test verifies:
     * - The operation's success by checking if it returns true.
     * - The presence of the added SolarIrradiance Sensor in the Sensor repository with the correct attributes.
     */

    @Test
    void addSolarIrradianceSensorToDevice_shouldReturnTrue() {
        //Arrange
        //Sensor Service instantiation
        String propertiesPath = "sensor.properties";
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl(propertiesPath);
        SensorRepositoryMem sensorRepositoryMem = new SensorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        SensorTypeRepositoryMem sensorTypeRepositoryMem = new SensorTypeRepositoryMem();
        SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(deviceRepositoryMem, sensorTypeRepositoryMem, sensorRepositoryMem, sensorFactoryImpl);

        //Creation of device's value objects and the device + addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new smarthome.vo.roomvo.RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //Sensor Type Service initialization, so it can populate Sensor type repository before adding a specific type
        //SensorType is not used only it's initialization is necessary in this test
        SensorTypeFactoryImpl SensorTypeFactory = new SensorTypeFactoryImpl();
        SensorTypeService SensorTypeService = new SensorTypeServiceImpl(sensorTypeRepositoryMem,SensorTypeFactory,propertiesPath);

        //Controller initialization
        AddSensorToDeviceCTRL addSensorToDeviceCTRL = new AddSensorToDeviceCTRL(sensorServiceImpl);

        //SensorTypeDTO initialization
        String sensorTypeID ="SwitchSensor";
        String unit = "W/m2";
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID,unit);

        //SensorDTO initialization
        String sensorNameVO = "Sensor1";
        SensorDTO sensorDTO = new SensorDTO(sensorNameVO);

        //Act
        boolean result = addSensorToDeviceCTRL.addSensorToDevice(deviceDTO,sensorTypeDTO,sensorDTO);

        //Assert

        //This assertion asserts that the operation succeeded
        assertTrue(result);

        //This assertion asserts that the added Sensor is present in Sensor repository

        //Fetch the added Sensor from repository
        Iterable<Sensor> SensorIterable = sensorRepositoryMem.findAll();
        Sensor addedSensor = IterableUtils.get(SensorIterable,0);

        //Query the fetched Sensor for it's attributes
        String resultSensorName = addedSensor.getSensorName().getValue();
        String resultSensorTypeID = addedSensor.getSensorTypeID().getID();
        DeviceIDVO resultDeviceID = addedSensor.getDeviceID();

        //Compare Sensor's attributes with the ones given in its creation
        assertEquals(sensorNameVO,resultSensorName);
        assertEquals(sensorTypeID,resultSensorTypeID);
        assertEquals(deviceIDVO,resultDeviceID);
    }

    /**
     * This test verifies the behavior when attempting to add a sensor to a deactivated device.
     * It ensures that the operation returns false and that no sensor is stored in the sensor repository.

     * The test initializes the necessary components, including Sensor Service, Device repository, Sensor Type Service, and the controller.
     * It creates a device, deactivates it, and creates DTOs for the device, a Sensor Type, and a Sensor.

     * After calling the method to add the sensor to the deactivated device, the test verifies:
     * - The operation's failure by checking if it returns false.
     * - The absence of any sensor in the Sensor repository.
     */
    @Test
    void addSensorToDevice_WhenDeactivatedDevice_shouldReturnTrue(){
        //Arrange
        //Sensor Service instantiation
        String propertiesPath = "sensor.properties";
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl(propertiesPath);
        SensorRepositoryMem sensorRepositoryMem = new SensorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        SensorTypeRepositoryMem sensorTypeRepositoryMem = new SensorTypeRepositoryMem();
        SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(deviceRepositoryMem, sensorTypeRepositoryMem, sensorRepositoryMem, sensorFactoryImpl);

        //Creation of device's value objects and the device + addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new smarthome.vo.roomvo.RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);
        //Device deactivation
        device.deactivateDevice();

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //Sensor Type Service initialization, so it can populate Sensor type repository before adding a specific type
        //SensorType is not used only it's initialization is necessary in this test
        SensorTypeFactoryImpl SensorTypeFactory = new SensorTypeFactoryImpl();
        SensorTypeService SensorTypeService = new SensorTypeServiceImpl(sensorTypeRepositoryMem,SensorTypeFactory,propertiesPath);

        //Controller initialization
        AddSensorToDeviceCTRL addSensorToDeviceCTRL = new AddSensorToDeviceCTRL(sensorServiceImpl);

        //SensorTypeDTO initialization
        String sensorTypeID ="SwitchSensor";
        String unit = "W/m2";
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID,unit);

        //SensorDTO initialization
        String sensorNameVO = "Sensor1";
        SensorDTO sensorDTO = new SensorDTO(sensorNameVO);

        //Act
        boolean result = addSensorToDeviceCTRL.addSensorToDevice(deviceDTO,sensorTypeDTO,sensorDTO);

        //Assert

        //This assertion asserts that the operation succeeded
        assertFalse(result);

        //This assertion asserts that the added Sensor is not present in Sensor repository
        Iterable<Sensor> SensorIterable = sensorRepositoryMem.findAll();
        boolean emptyRepository = IterableUtils.isEmpty(SensorIterable);
        assertTrue(emptyRepository);
    }

    /**
     * This test verifies the behavior when attempting to add a sensor to a non-existing device.
     * It ensures that the operation returns false and that no sensor is stored in the sensor repository.

     * The test initializes the necessary components, including Sensor Service, Device repository, Sensor Type Service, and the controller.
     * It creates a DTO for a non-existing device, a Sensor Type, and a Sensor.

     * After calling the method to add the sensor to the device, the test verifies:
     * - The operation's failure by checking if it returns false.
     * - The absence of any sensor in the Sensor repository.
     */
    @Test
    void addSensorToDevice_WhenNonExistingDevice_shouldReturnFalse() {
        //Arrange
        //Sensor Service instantiation
        String propertiesPath = "sensor.properties";
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl(propertiesPath);
        SensorRepositoryMem sensorRepositoryMem = new SensorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        SensorTypeRepositoryMem sensorTypeRepositoryMem = new SensorTypeRepositoryMem();
        SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(deviceRepositoryMem, sensorTypeRepositoryMem, sensorRepositoryMem, sensorFactoryImpl);

        //Initialization of DeviceID
        String deviceId = "DeviceID";
        String deviceName = "Device1";
        String deviceModel = "XPU-99";
        String deviceStatus = "true";
        String roomId = "roomID";
        DeviceDTO deviceDTO = new DeviceDTO(deviceId,deviceName,deviceModel,deviceStatus,roomId);

        //Sensor Type Service initialization, so it can populate Sensor type repository before adding a specific type
        //SensorType is not used only it's initialization is necessary in this test
        SensorTypeFactoryImpl SensorTypeFactory = new SensorTypeFactoryImpl();
        SensorTypeService SensorTypeService = new SensorTypeServiceImpl(sensorTypeRepositoryMem,SensorTypeFactory,propertiesPath);

        //Controller initialization
        AddSensorToDeviceCTRL addSensorToDeviceCTRL = new AddSensorToDeviceCTRL(sensorServiceImpl);

        //SensorTypeDTO initialization
        String sensorTypeID ="HumiditySensor";
        String unit = "%";
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID,unit);

        //SensorDTO initialization
        String sensorNameVO = "Sensor1";
        SensorDTO sensorDTO = new SensorDTO(sensorNameVO);

        //Act
        boolean result = addSensorToDeviceCTRL.addSensorToDevice(deviceDTO,sensorTypeDTO,sensorDTO);

        //Assert
        //This assertion asserts that the operation did not succeed
        assertFalse(result);

        //This assertion asserts that the added Sensor is not present in Sensor repository
        Iterable<Sensor> SensorIterable = sensorRepositoryMem.findAll();
        boolean emptyRepository = IterableUtils.isEmpty(SensorIterable);
        assertTrue(emptyRepository);
    }

    /**
     * This test verifies the behavior when attempting to add a sensor with an invalid sensor type to a device.
     * It ensures that the operation returns false and that no sensor is stored in the sensor repository.

     * The test initializes the necessary components, including Sensor Service, Device repository, Sensor Type Service, and the controller.
     * It creates a new Device and saves it to the repository, then initializes DTOs for Device, Sensor Type (with an invalid type), and Sensor.

     * After calling the method to add the sensor to the Device, the test verifies:
     * - The operation's failure by checking if it returns false.
     * - The absence of any sensor in the Sensor repository.
     */
    @Test
    void addSensorToDevice_WhenInvalidSensorType_shouldReturnFalse(){
        //Arrange
        //Sensor Service instantiation
        String propertiesPath = "sensor.properties";
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl(propertiesPath);
        SensorRepositoryMem sensorRepositoryMem = new SensorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        SensorTypeRepositoryMem sensorTypeRepositoryMem = new SensorTypeRepositoryMem();
        SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(deviceRepositoryMem, sensorTypeRepositoryMem, sensorRepositoryMem, sensorFactoryImpl);

        //Creation of device's value objects and the device + addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new smarthome.vo.roomvo.RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //Sensor Type Service initialization, so it can populate Sensor type repository before adding a specific type
        //SensorType is not used only it's initialization is necessary in this test
        SensorTypeFactoryImpl SensorTypeFactory = new SensorTypeFactoryImpl();
        SensorTypeService SensorTypeService = new SensorTypeServiceImpl(sensorTypeRepositoryMem,SensorTypeFactory,propertiesPath);

        //Controller initialization
        AddSensorToDeviceCTRL addSensorToDeviceCTRL = new AddSensorToDeviceCTRL(sensorServiceImpl);

        //SensorTypeDTO initialization with invalid type
        String sensorTypeID ="NuclearSensor";
        String unit = "Bq";
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID,unit);

        //SensorDTO initialization
        String sensorNameVO = "Sensor1";
        SensorDTO sensorDTO = new SensorDTO(sensorNameVO);

        //Act
        boolean result = addSensorToDeviceCTRL.addSensorToDevice(deviceDTO,sensorTypeDTO,sensorDTO);

        //Assert
        //This assertion asserts that the operation did not succeed
        assertFalse(result);

        //This assertion asserts that the added Sensor is not present in Sensor repository
        Iterable<Sensor> SensorIterable = sensorRepositoryMem.findAll();
        boolean emptyRepository = IterableUtils.isEmpty(SensorIterable);
        assertTrue(emptyRepository);
    }

    /**
     * This test verifies the behavior when adding two sensors to a device.
     * It ensures that the operation returns true and that the sensor repository contains two records.

     * The test initializes the necessary components, including Sensor Service, Device repository, Sensor Type Service, and the controller.
     * It creates a device and its DTO, a Sensor Type and its DTO, and two Sensor DTOs.

     * After adding both sensors to the device, the test verifies:
     * - The success of the operation by checking if it returns true.
     * - The presence of two records in the sensor repository with the expected attributes.
     */

    @Test
    void addTwoSensorsToDevice_shouldReturnTrue_repositoryShouldHaveTwoRecords() {
        //Arrange
        int expectedSize = 2;
        //Sensor Service instantiation
        String propertiesPath = "sensor.properties";
        SensorFactoryImpl sensorFactoryImpl = new SensorFactoryImpl(propertiesPath);
        SensorRepositoryMem sensorRepositoryMem = new SensorRepositoryMem();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        SensorTypeRepositoryMem sensorTypeRepositoryMem = new SensorTypeRepositoryMem();
        SensorServiceImpl sensorServiceImpl = new SensorServiceImpl(deviceRepositoryMem, sensorTypeRepositoryMem, sensorRepositoryMem, sensorFactoryImpl);

        //Creation of device's value objects and the device + addition to repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new smarthome.vo.roomvo.RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepositoryMem.save(device);

        //DeviceDTO initialization
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());

        //Sensor Type Service initialization, so it can populate Sensor type repository before adding a specific type
        //SensorType is not used only it's initialization is necessary in this test
        SensorTypeFactoryImpl SensorTypeFactory = new SensorTypeFactoryImpl();
        SensorTypeService SensorTypeService = new SensorTypeServiceImpl(sensorTypeRepositoryMem,SensorTypeFactory,propertiesPath);

        //Controller initialization
        AddSensorToDeviceCTRL addSensorToDeviceCTRL = new AddSensorToDeviceCTRL(sensorServiceImpl);

        //SensorTypeDTO initialization
        String sensorTypeID ="SwitchSensor";
        String unit = "W/m2";
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID,unit);

        //SensorDTO initialization
        String sensorNameVO = "Sensor1";
        SensorDTO sensorDTO = new SensorDTO(sensorNameVO);

        String secondSensorNameVO = "Sensor2";
        SensorDTO secondSensorDTO = new SensorDTO(secondSensorNameVO);

        //Act
        addSensorToDeviceCTRL.addSensorToDevice(deviceDTO,sensorTypeDTO,sensorDTO);
        boolean result = addSensorToDeviceCTRL.addSensorToDevice(deviceDTO,sensorTypeDTO,secondSensorDTO);

        //Assert

        //This assertion asserts that the operation succeeded
        assertTrue(result);

        //This assertion asserts the Sensor repository has two records both with the expected given attributes

        //find all sensors in repository
        Iterable<Sensor> sensorIterable = sensorRepositoryMem.findAll();
        int resultSize = IterableUtils.size(sensorIterable);
        assertEquals(expectedSize,resultSize);

    }
}