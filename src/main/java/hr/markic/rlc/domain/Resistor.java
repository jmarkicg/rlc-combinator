package hr.markic.rlc.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Domain class used to store the data related to resistor elements.
 */
@Document(collection ="resistors")
public class Resistor extends BaseElement {

    Float volume;

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }
}
