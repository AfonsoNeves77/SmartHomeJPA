package SmartHomeTest.mapperTest;

import smarthome.dto.SensorDTO;
import smarthome.mapper.SensorMapper;
import smarthome.vo.sensorvo.SensorNameVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SensorMapperTest {

    /**
     * Verifies when a valid DTO is received (not null), SensorNameVO is created, and when getValue() is invoked,
     * the expected sensor name in a string format is retrieved.
     * SensorMapper Class has two collaborators in this scenario, SensorDTO and SensorNameVO classes. To properly isolate
     * the class under test (SensorMapper), a double of the SensorDTO class is made as well as a MockedConstruction of the
     * SensorNameVO Class, in order to condition its behaviour when a SensorNameVO is created. It is also verified that
     * one exact Value Object is created.
     */
    @Test
    void givenAValidDto_SensorNameVOIsCreated_WhenGetValueIsInvoked_ThenShouldReturnTheExpectedSensorName(){
        //Arrange
        SensorDTO doubleDto = mock(SensorDTO.class);
        when(doubleDto.getSensorName()).thenReturn("Swimming pool temperature sensor");
        String expected = "Swimming pool temperature sensor";
        int valuesListExpectedSize = 1;

        try(MockedConstruction<SensorNameVO> mockedVO = mockConstruction(SensorNameVO.class,(mock,context) ->
                when(mock.getValue()).thenReturn(expected)))
        {
            SensorNameVO valueObject = SensorMapper.createSensorNameVO(doubleDto);
            List<SensorNameVO> valuesList = mockedVO.constructed();

            //Act
            String result = valueObject.getValue();

            //Assert
            assertEquals(expected, result);
            assertEquals(valuesListExpectedSize, valuesList.size());
        }

    }

    /**
     * Verifies when an invalid DTO is received (null), SensorNameVO is not created and an IllegalArgumentException is thrown,
     * by the Mapper.
     * SensorMapper Class has only one collaborator in this scenario, which is the SensorDTO class. To properly isolate the
     * class under test (SensorMapper), a double of the SensorDTO class is made. There is no need to have a MockedConstruction
     * of the SensorNameVO Class since the code breaks before that interaction.
     */
    @Test
    void givenANullDto_WhenCreateSensorNameVOIsInvoked_ThenShouldThrowIllegalArgumentException(){
        //Arrange
        String expected = "Invalid DTO, Value Object cannot be created";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> SensorMapper.createSensorNameVO(null));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies when a DTO containing invalid information is received (null name), SensorNameVO is not created, and an
     * IllegalArgumentException is thrown, by the SensorNameVO Class.
     * SensorMapper Class has two collaborators in this scenario, SensorDTO and SensorNameVO classes. To properly isolate
     * the class under test (SensorMapper), a double of the SensorDTO class is made. A MockedConstruction of the SensorNameVO Class
     * should be made as well, however, in this particular situation, the constructor of the SensorNameVO object throws
     * an exception, because it seems Mockito does not allow throwing exceptions through mock objects. A partial isolation
     * test scenario is considered.
     */
    @Test
    void givenADtoWithNullSensorName_WhenCreateSensorNameVOIsInvoked_ThenShouldThrowIllegalArgumentException(){
        //Arrange
        SensorDTO doubleDto = mock(SensorDTO.class);
        when(doubleDto.getSensorName()).thenReturn(null);
        String expected = "Invalid parameters.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> SensorMapper.createSensorNameVO(doubleDto));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }


    /**
     * Verifies when a DTO containing invalid information is received (blank name), SensorNameVO is not created, and an
     * IllegalArgumentException is thrown, by the SensorNameVO Class.
     * The same scenario is verified as in the test to create a SensorNameVO object containing a null string name.
     * A partial isolation test scenario is considered.
     */
    @Test
    void givenADtoWithBlankSensorName_WhenCreateSensorNameVOIsInvoked_ThenShouldThrowIllegalArgumentException(){
        //Arrange
        SensorDTO doubleDto = mock(SensorDTO.class);
        when(doubleDto.getSensorName()).thenReturn("   ");
        String expected = "Invalid parameters.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> SensorMapper.createSensorNameVO(doubleDto));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }
}