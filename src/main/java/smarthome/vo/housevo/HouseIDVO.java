package smarthome.vo.housevo;

import jakarta.persistence.Embeddable;
import smarthome.domain.DomainID;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a value object for House ID.
 */

@Embeddable
public class HouseIDVO implements DomainID {

    //Final !!!!

    private UUID identifier;

    /**
     * Constructs a HouseIDVO object with the given identifier.
     *
     * @throws IllegalArgumentException If the identifier is null.
     */

    protected HouseIDVO() {

    }
    public HouseIDVO(UUID identifier){
        if(identifier == null){
            throw new IllegalArgumentException("Invalid Identifier");
        }
        this.identifier = identifier;
    }



    /**
     * Gets the string representation of the house identifier.
     *
     * @return The string representation of the house identifier.
     */
    @Override
    public String getID() {
        return this.identifier.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HouseIDVO)) return false;
        HouseIDVO houseIDVO = (HouseIDVO) o;
        return Objects.equals(identifier, houseIDVO.identifier);
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }
}
