package IntegrationTests;

import org.junit.jupiter.api.Test;
import smarthome.controller.GetListOfSensorTypesCTRL;
import smarthome.domain.sensortype.SensorTypeFactoryImpl;
import smarthome.dto.SensorTypeDTO;
import smarthome.repository.SensorTypeRepositoryMem;
import smarthome.services.SensorTypeServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetListOfSensorTypesCTRLTest {

    /**
     * Test for the constructor of GetListOfSensorTypesCTRL.
     * Tests if the constructor throws an IllegalArgumentException when the sensorTypeService is null.
     */
    @Test
    void whenNullSensorTypeService_constructorThrowsIllegalArgumentException() {
        //Arrange
        String expectedMessage = "Invalid parameters.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GetListOfSensorTypesCTRL(null));

        //Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test for the getListOfSensorTypes method of AddSensorToDeviceCTRL.
     * Tests if the method returns a list of SensorTypeDTO of all the available sensor types.
     * The expected size of the list is 12, which corresponds to the number of sensor types in the sensor.properties file.
     * The first sensor type in the list is HumiditySensor.
     * The method should return a list of SensorTypeDTO with a size of 12 and the first sensor type id should be
     * HumiditySensor.
     */
    @Test
    void whenGetListOfSensorTypes_thenReturnListOfSensorTypeDTO() {
        //Arrange
        int expectedSize = 12;
        String expectedSensorTypeID = "HumiditySensor";
        String path = "sensor.properties";
        SensorTypeRepositoryMem sensorTypeRepositoryMem = new SensorTypeRepositoryMem();
        SensorTypeFactoryImpl sensorTypeFactory = new SensorTypeFactoryImpl();
        SensorTypeServiceImpl sensorTypeService = new SensorTypeServiceImpl(sensorTypeRepositoryMem, sensorTypeFactory, path);

        GetListOfSensorTypesCTRL getListOfSensorTypesCTRL = new GetListOfSensorTypesCTRL(sensorTypeService);

        //Act
        List<SensorTypeDTO> result = getListOfSensorTypesCTRL.getListOfSensorTypes();

        //Assert
        assertEquals(expectedSize, result.size());
        assertEquals(expectedSensorTypeID, result.get(0).getSensorTypeID());
    }

}