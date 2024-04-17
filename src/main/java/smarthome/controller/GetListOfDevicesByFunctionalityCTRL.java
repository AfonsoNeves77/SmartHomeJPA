package smarthome.controller;

import smarthome.domain.device.Device;
import smarthome.dto.DeviceDTO;
import smarthome.mapper.DeviceMapper;
import smarthome.services.QueryService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GetListOfDevicesByFunctionalityCTRL {
    private final QueryService queryService;

    /**
     * Constructs an instance of GetListOfDevicesByFunctionalityCTRL with the provided QueryService dependency.
     * This constructor initializes the GetListOfDevicesByFunctionalityCTRL with the QueryService used to retrieve devices
     * based on functionality.
     * It ensures that the provided QueryService is not null and throws an IllegalArgumentException if it is.
     * @param queryService The QueryService used to retrieve devices based on functionality.
     * @throws IllegalArgumentException if the queryService parameter is null. This exception is thrown to indicate an
     * invalid or missing service.
     */
    public GetListOfDevicesByFunctionalityCTRL(QueryService queryService){
        if (queryService == null){
            throw new IllegalArgumentException("Invalid service");
        }
        this.queryService = queryService;
    }

    /**
     * Retrieves a Map of Devices grouped by functionality.
     * This method calls the getListOfDeviceByFunctionality method of the QueryService to retrieve a Map of Devices
     * grouped by their functionality. It then maps the retrieved Device objects to DeviceDTOs using the DeviceMapper.
     * @return A Map where each key represents a functionality and the value is a list of DeviceDTOs associated with that
     * functionality. It returns an empty Map if no Devices are found or if there is an error during mapping.
     */
    public LinkedHashMap<String, List<DeviceDTO>> getListOfDevicesByFunctionality(){
        Map<String, List<Device>> map = queryService.getListOfDeviceByFunctionality();
        return DeviceMapper.domainToDTO(map);
    }
}

