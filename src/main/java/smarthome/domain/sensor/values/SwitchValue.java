package smarthome.domain.sensor.values;

import smarthome.vo.ValueObject;

public class SwitchValue implements ValueObject<String> {
    private final String switchValue;

    public SwitchValue(String value) {
        if (!isReadingValid(value)) {
            throw new IllegalArgumentException("Invalid switch value");
        }
        this.switchValue = value;
    }

    public String getValue() {
        return this.switchValue;
    }

    private boolean isReadingValid(String switchValue) {
        if (switchValue == null) return false;
        return switchValue.trim().equalsIgnoreCase("On") || switchValue.trim().equalsIgnoreCase("Off");
    }
}
