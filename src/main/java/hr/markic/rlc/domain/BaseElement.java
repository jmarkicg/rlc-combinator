package hr.markic.rlc.domain;

import org.bson.types.ObjectId;

import javax.persistence.Id;

/**
 * Common base domain element used to store the data related to RLC elements.
 */
public class BaseElement {
    @Id
    public ObjectId _id;

    public String type;
    public Double value;
    public String description;
    public Integer numItems;

    // Constructors
    public BaseElement() {}

    public BaseElement(ObjectId _id, String type, Double value, String description, Integer numItems) {
        this._id = _id;
        this.type = type;
        this.description = description;
        this.numItems = numItems;
    }

    // ObjectId needs to be converted to string
    public String get_id() { return _id.toHexString(); }
    public void set_id(ObjectId _id) { this._id = _id; }

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
}
