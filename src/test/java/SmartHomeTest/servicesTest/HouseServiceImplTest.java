package SmartHomeTest.servicesTest;

import smarthome.domain.house.HouseFactoryImpl;
import smarthome.repository.HouseRepositoryMem;
import smarthome.services.HouseServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for HouseService
 */

class HouseServiceImplTest {

    /**
     * Test case to check if IllegalArgumentException is thrown when HouseRepository is null
     */

    @Test
    void givenNullHouseRepository_whenHouseServiceIsCreated_thenThrowIllegalArgumentException() {
//        Arrange
        HouseFactoryImpl v1HouseFactory = new HouseFactoryImpl();
        String expectedMessage = "Invalid parameters";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new HouseServiceImpl(null, v1HouseFactory));
//        Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test case to check if IllegalArgumentException is thrown when HouseFactory is null
     */

    @Test
    void givenNullHouseFactory_whenHouseServiceIsCreated_thenThrowIllegalArgumentException() {
//        Arrange
        HouseRepositoryMem memHouseRepository = new HouseRepositoryMem();
        String expectedMessage = "Invalid parameters";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new HouseServiceImpl(memHouseRepository, null));
//        Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
