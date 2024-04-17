package smarthome.services;

import smarthome.domain.device.Device;

import java.util.List;
import java.util.Map;

public interface QueryService {
    Map<String, List<Device>> getListOfDeviceByFunctionality();
}
