package smarthome.domain.sensor.values;

import smarthome.vo.ValueObject;

public class PowerConsumptionValue implements ValueObject<Integer> {

    /**
     * The integer value representing the power consumption.
     */
    private final int powerConsumption;

    /**
     * Constructor for PowerConsumptionValue class.
     * @param powerConsumption int value to be stored. Must be greater than or equal to 0.
     * @throws IllegalArgumentException if powerConsumptionValue is negative.
     */
    public PowerConsumptionValue(int powerConsumption) {
        if (powerConsumption < 0) {
            throw new IllegalArgumentException("Invalid power consumption value");
        }
        this.powerConsumption = powerConsumption;
    }

    /**
     * Retrieves the integer value stored in this PowerConsumptionValue.
     * @return The integer value representing the power consumption.
     */
    @Override
    public Integer getValue() {
        return this.powerConsumption;
    }
}
