package hr.markic.rlc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Domain class used to store the data related to resistor elements.
 */
@Entity
@Table(name = "resistors")
public class Resistor extends BaseElement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column
    Float volume;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }
}
