package hr.markic.rlc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

/**
 * Common base domain element used to store the data related to RLC elements.
 */
@MappedSuperclass
public abstract class BaseElement {

    @Column
    public String type;

    @Column
    public Double value;

    @Column
    public String description;

    @Column
    public Integer numItems;

    @JsonIgnore
    @ManyToOne
    public User owner;

    // Constructors
    public BaseElement() {}

    public BaseElement(User owner, String type, Double value, String description, Integer numItems) {
        this.owner = owner;
        this.type = type;
        this.description = description;
        this.numItems = numItems;
        this.value = value;
    }

    public abstract Long getId();

    public abstract void setId(Long id);

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumItems() {
        return numItems;
    }

    public void setNumItems(Integer numItems) {
        this.numItems = numItems;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
