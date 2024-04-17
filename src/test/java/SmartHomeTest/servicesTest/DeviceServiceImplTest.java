package SmartHomeTest.servicesTest;

import smarthome.domain.device.DeviceFactoryImpl;
import smarthome.repository.DeviceRepositoryMem;
import smarthome.repository.RoomRepositoryMem;
import smarthome.services.DeviceServiceImpl;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DeviceServiceImplTest {

    private void assertThrowsIllegalArgumentExceptionWithNullParameter(RoomRepositoryMem roomRepositoryMem, DeviceFactoryImpl factoryDevice, DeviceRepositoryMem memDeviceRepository) {
        //Arrange
        String expected = "Invalid parameters";

        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DeviceServiceImpl(roomRepositoryMem,factoryDevice, memDeviceRepository));
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     *The following test verifies that if DeviceFactory parameter is null then DeviceService instantiation should throw an Illegal Argument Exception with
     the message "Invalid Parameters".
     */

    @Test
    void whenNullDeviceFactory_thenThrowsIllegalArgumentException() {
        //Arrange
        RoomRepositoryMem roomRepositoryMem = mock(RoomRepositoryMem.class);
        DeviceRepositoryMem memDeviceRepository = mock(DeviceRepositoryMem.class);

        //Assert
        assertThrowsIllegalArgumentExceptionWithNullParameter(roomRepositoryMem, null, memDeviceRepository);
    }

    /**
     *The following test verifies that if RoomRepository parameter is null then DeviceService instantiation should throw an Illegal Argument Exception with
     the message "Invalid Parameters".
     */

    @Test
    void whenNullRoomRepository_thenThrowsIllegalArgumentException() {
        //Arrange
        DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);
        DeviceRepositoryMem memDeviceRepository = mock(DeviceRepositoryMem.class);

        //Assert
        assertThrowsIllegalArgumentExceptionWithNullParameter(null, deviceFactory, memDeviceRepository);
    }

    /**
     *The following test verifies that if DeviceRepository parameter is null then DeviceService instantiation should throw an Illegal Argument Exception with
     the message "Invalid Parameters".
     */

    @Test
    void whenNullDeviceRepository_thenThrowsIllegalArgumentException() {
        //Arrange
        RoomRepositoryMem roomRepositoryMem = mock(RoomRepositoryMem.class);
        DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);

        //Assert
        assertThrowsIllegalArgumentExceptionWithNullParameter(roomRepositoryMem, deviceFactory, null);
    }
}
