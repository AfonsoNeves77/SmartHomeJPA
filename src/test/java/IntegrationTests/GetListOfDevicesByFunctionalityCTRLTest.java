package IntegrationTests;

import smarthome.controller.GetListOfDevicesByFunctionalityCTRL;
import smarthome.domain.actuator.RollerBlindActuator;
import smarthome.domain.actuator.SwitchActuator;
import smarthome.domain.device.Device;
import smarthome.domain.sensor.HumiditySensor;
import smarthome.domain.sensor.SunriseSensor;
import smarthome.dto.DeviceDTO;
import smarthome.repository.*;
import smarthome.services.*;
import smarthome.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.vo.actuatorvo.ActuatorNameVO;
import smarthome.vo.devicevo.DeviceModelVO;
import smarthome.vo.devicevo.DeviceNameVO;
import smarthome.vo.roomvo.RoomIDVO;
import smarthome.vo.sensortype.SensorTypeIDVO;
import smarthome.vo.sensorvo.SensorNameVO;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetListOfDevicesByFunctionalityCTRLTest {

    /**
     * Test to verify that the constructor of GetListOfDevicesByFunctionalityCTRL
     * throws an IllegalArgumentException when instantiated with a null service.
     */
    @Test
    void whenInvalidQueryService_ConstructorThrowsException(){
        // Arrange
        String expected = "Invalid service";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new GetListOfDevicesByFunctionalityCTRL(null));
        String result = exception.getMessage();
        // Assert
        assertEquals(expected,result);
    }

    /**
     * Test method to verify the behavior of the GetListOfDevicesByFunctionalityCTRL's
     * getListOfDevicesByFunctionality method.
     * This test ensures that devices are properly grouped by their functionality types
     * and returned as lists of DeviceDTO objects.
     */
    @Test
    void getListOfDevicesByFunctionalityTest_whenSuccessCase_DeliversAStrToDeviceDTOList() {
        // Arrange

            // Device creation - RoomVO is created independently for brevity
        DeviceNameVO name1 = new DeviceNameVO("name1");
        DeviceModelVO model1 = new DeviceModelVO("model1");
        RoomIDVO room1 = new RoomIDVO(UUID.randomUUID());
        Device device1 = new Device(name1,model1,room1);

        DeviceNameVO name2 = new DeviceNameVO("name2");
        DeviceModelVO model2 = new DeviceModelVO("model2");
        RoomIDVO room2 = new RoomIDVO(UUID.randomUUID());
        Device device2 = new Device(name2,model2,room2);

        DeviceNameVO name3 = new DeviceNameVO("name3");
        DeviceModelVO model3 = new DeviceModelVO("model3");
        RoomIDVO room3 = new RoomIDVO(UUID.randomUUID());
        Device device3 = new Device(name3,model3,room3);

        DeviceNameVO name4 = new DeviceNameVO("name4");
        DeviceModelVO model4 = new DeviceModelVO("model4");
        RoomIDVO room4 = new RoomIDVO(UUID.randomUUID());
        Device device4 = new Device(name4,model4,room4);

            // Device repository instantiation + save
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        deviceRepositoryMem.save(device1);
        deviceRepositoryMem.save(device2);
        deviceRepositoryMem.save(device3);
        deviceRepositoryMem.save(device4);

            // Device1 sensors + actuators
        SensorNameVO sensorName1 = new SensorNameVO("sensor1");
        SensorTypeIDVO senType1 = new SensorTypeIDVO("SunriseSensor");
        SunriseSensor sensor1 = new SunriseSensor(sensorName1,device1.getId(),senType1);

        SensorNameVO sensorName2 = new SensorNameVO("sensor2");
        SensorTypeIDVO senType2 = new SensorTypeIDVO("SwitchSensor");
        SunriseSensor sensor2 = new SunriseSensor(sensorName2,device1.getId(),senType2);

            // Device2 sensors + actuators
        SensorNameVO sensorName3 = new SensorNameVO("sensor3");
        SunriseSensor sensor3 = new SunriseSensor(sensorName3,device2.getId(),senType1);

        ActuatorNameVO actuatorName1 = new ActuatorNameVO("actuator1");
        ActuatorTypeIDVO actType1 = new ActuatorTypeIDVO("SwitchActuator");
        SwitchActuator actuator1 = new SwitchActuator(actuatorName1,actType1,device2.getId());

        ActuatorNameVO actuatorName2 = new ActuatorNameVO("actuator2"); // Same type and device as actuator1
        SwitchActuator actuator2 = new SwitchActuator(actuatorName2,actType1,device2.getId());

            // Device3 sensors + actuators
        SensorNameVO sensorName4 = new SensorNameVO("sensor4");
        SensorTypeIDVO senType4 = new SensorTypeIDVO("SunsetSensor");
        SunriseSensor sensor4 = new SunriseSensor(sensorName4,device3.getId(),senType4);

        ActuatorNameVO actuatorName3 = new ActuatorNameVO("actuator3");
        ActuatorTypeIDVO actType2 = new ActuatorTypeIDVO("RollerBlindActuator");
        RollerBlindActuator actuator3 = new RollerBlindActuator(actuatorName3,actType2,device3.getId());

            // Device4 sensor
        SensorNameVO sensorName5 = new SensorNameVO("sensor5");
        SensorTypeIDVO senType5 = new SensorTypeIDVO("HumiditySensor");
        HumiditySensor sensor5 = new HumiditySensor(sensorName5,device4.getId(),senType5);

            // Sensor Repository instantiation + save
        SensorRepositoryMem sensorRepositoryMem = new SensorRepositoryMem();
        sensorRepositoryMem.save(sensor1);
        sensorRepositoryMem.save(sensor2);
        sensorRepositoryMem.save(sensor3);
        sensorRepositoryMem.save(sensor4);
        sensorRepositoryMem.save(sensor5);

            // Actuator Repository instantiation + save
        ActuatorRepositoryMem actuatorRepositoryMem = new ActuatorRepositoryMem();
        actuatorRepositoryMem.save(actuator1);
        actuatorRepositoryMem.save(actuator2);
        actuatorRepositoryMem.save(actuator3);

            // Service instantiation
        QueryServiceImpl service = new QueryServiceImpl(deviceRepositoryMem, sensorRepositoryMem, actuatorRepositoryMem);

            // CTRL instantiation and getListOfDevices call to create resultMap
        GetListOfDevicesByFunctionalityCTRL ctrl = new GetListOfDevicesByFunctionalityCTRL(service);

        LinkedHashMap<String, List<DeviceDTO>> resultMap = ctrl.getListOfDevicesByFunctionality();

            // Expected keys
        List<String> expectedKeys = new ArrayList<>();
        expectedKeys.add("SunriseSensor");
        expectedKeys.add("SwitchSensor");
        expectedKeys.add("SunsetSensor");
        expectedKeys.add("HumiditySensor");
        expectedKeys.add("SwitchActuator");
        expectedKeys.add("RollerBlindActuator");

          List<String> expectedFirstKeyDTOnames = new ArrayList<>();
          expectedFirstKeyDTOnames.add("name1");
          expectedFirstKeyDTOnames.add("name2");

          List<String> expectedSecondKeyDTOnames = new ArrayList<>();
          expectedSecondKeyDTOnames.add("name1");

          List<String> expectedThirdKeyDTOnames = new ArrayList<>();
          expectedThirdKeyDTOnames.add("name3");

          List<String> expectedFourthKeyDTOnames = new ArrayList<>();
          expectedFourthKeyDTOnames.add("name4");

          List<String> expectedFifthKeyDTOnames = new ArrayList<>();
          expectedFifthKeyDTOnames.add("name2");

          List<String> expectedSixthKeyDTOnames = new ArrayList<>();
          expectedSixthKeyDTOnames.add("name3");


        // Act

            // prep to assert1 - testing key strings
        List<String> resultKeys = new ArrayList<>(resultMap.keySet());

            // prep to assert2 - testing each individual DTO name by key list
        List<String> resultFirstKeyDTOnames = new ArrayList<>();
        for (DeviceDTO dto : resultMap.get("SunriseSensor")){
            resultFirstKeyDTOnames.add(dto.getDeviceName());
        }

        List<String> resultSecondKeyDTOnames = new ArrayList<>();
        for (DeviceDTO dto : resultMap.get("SwitchSensor")){
            resultSecondKeyDTOnames.add(dto.getDeviceName());
        }

        List<String> resultThirdKeyDTOnames = new ArrayList<>();
        for (DeviceDTO dto : resultMap.get("SunsetSensor")){
            resultThirdKeyDTOnames.add(dto.getDeviceName());
        }

        List<String> resultFourthKeyDTOnames = new ArrayList<>();
        for (DeviceDTO dto : resultMap.get("HumiditySensor")){
            resultFourthKeyDTOnames.add(dto.getDeviceName());
        }

        List<String> resultFifthKeyDTOnames = new ArrayList<>();
        for (DeviceDTO dto : resultMap.get("SwitchActuator")){
            resultFifthKeyDTOnames.add(dto.getDeviceName());
        }

        List<String> resultSixthKeyDTOnames = new ArrayList<>();
        for (DeviceDTO dto : resultMap.get("RollerBlindActuator")){
            resultSixthKeyDTOnames.add(dto.getDeviceName());
        }

        // Assert
        assertEquals(expectedKeys,resultKeys);

        assertEquals(expectedFirstKeyDTOnames,resultFirstKeyDTOnames);
        assertEquals(expectedSecondKeyDTOnames,resultSecondKeyDTOnames);
        assertEquals(expectedThirdKeyDTOnames,resultThirdKeyDTOnames);
        assertEquals(expectedFourthKeyDTOnames,resultFourthKeyDTOnames);
        assertEquals(expectedFifthKeyDTOnames,resultFifthKeyDTOnames);
        assertEquals(expectedSixthKeyDTOnames,resultSixthKeyDTOnames);
    }

    /**
     * Test method to verify that when no devices are present in the system,
     * the getListOfDevicesByFunctionality method returns an empty map.
     */
    @Test
    void whenNoDevicesArePresent_returnsEmptyList(){
        // Arrange
        DeviceRepositoryMem deviceRepository = new DeviceRepositoryMem();
        SensorRepositoryMem sensorRepository = new SensorRepositoryMem();
        ActuatorRepositoryMem actuatorRepository = new ActuatorRepositoryMem();
        QueryServiceImpl queryService = new QueryServiceImpl(deviceRepository,sensorRepository,actuatorRepository);
        GetListOfDevicesByFunctionalityCTRL controller = new GetListOfDevicesByFunctionalityCTRL(queryService);
        Map<String, List<DeviceDTO>> expected = new LinkedHashMap<>();
        // Act
        Map<String, List<DeviceDTO>> result = controller.getListOfDevicesByFunctionality();
        // Assert
        assertEquals(expected,result);
    }
}
