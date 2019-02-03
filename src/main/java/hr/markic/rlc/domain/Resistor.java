package hr.markic.rlc.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Domain class used to store the data related to resistor elements.
 */
@Document(collection ="resistors")
public class Resistor extends BaseElement {

    Integer volumen;

    public Integer getVolumen() {
        return volumen;
    }

    public void setVolumen(Integer volumen) {
        this.volumen = volumen;
    }
}
