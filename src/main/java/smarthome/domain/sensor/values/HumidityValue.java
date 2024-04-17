package smarthome.domain.sensor.values;

import smarthome.vo.ValueObject;

import static java.lang.Integer.parseInt;

public class HumidityValue implements ValueObject<Integer> {
    private final int primitiveValue;

    /**
     * Constructor for humidity value. It can only be instantiated if the receiving parameter is within
     * the specified interval.
     * @param reading Value received by an external source, in line for conversion.
     * @throws InstantiationException If parameters out of bounds.
     */
    public HumidityValue(String reading) throws InstantiationException {
        if(!isReadingValid(reading)){
            throw new InstantiationException("Invalid reading");
        }
        this.primitiveValue = parseInt(reading);
    }

    /**
     * Returns the encapsulated primitive value, maintaining its type.
     * @return Encapsulated primitive value.
     */
    @Override
    public Integer getValue() {
        return this.primitiveValue;
    }

    /**
     * Verifies if the reading is valid after parsing from String to Integer.
     * @param reading Reading value
     * @return True or false
     */
    private boolean isReadingValid(String reading) {
        int valueOfReading;
        try {
            valueOfReading = parseInt(reading);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return valueOfReading >= 0 && valueOfReading <= 100;
    }


}
