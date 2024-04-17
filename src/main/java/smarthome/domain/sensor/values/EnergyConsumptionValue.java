package smarthome.domain.sensor.values;

import smarthome.vo.ValueObject;

import static java.lang.Integer.parseInt;

public class EnergyConsumptionValue implements ValueObject<Integer> {
    private final int primitiveValue;

    /**
     * Constructor for EnergyConsumptionValue
     * @param reading Reading value
     * @throws InstantiationException If reading invalid
     */
    public EnergyConsumptionValue(String reading) throws InstantiationException {
        if(!validateReading(reading)){
            throw new InstantiationException("Invalid reading");
        }
        this.primitiveValue = parseInt(reading);
    }

    /**
     * Method to validate the reading
     * @param reading Reading value
     * @return boolean
     */
    private boolean validateReading(String reading) {
        double valueOfReading;
        try {
            valueOfReading = parseInt(reading);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return valueOfReading >= 0;
    }

    public Integer getValue() {
        return this.primitiveValue;
    }

    public String getValueAsString() {
        return primitiveValue + "";
    }
}