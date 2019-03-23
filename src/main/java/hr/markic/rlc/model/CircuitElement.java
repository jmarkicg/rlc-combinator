package hr.markic.rlc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.markic.rlc.enums.CircuitEnum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CircuitElement implements Serializable {

    @JsonIgnore
    CircuitElement parent;

    //defined if element contains subelements
    CircuitEnum circuitType;
    List<CircuitElement> elementList;
    Double sumValue;

    //defined if this is actually an element
    Double elemValue;
    Integer index;

    Integer itemsTotal;

    public Integer addToElementList(CircuitElement elem){
        if (elementList == null){
            elementList = new ArrayList<>();
        }
        elementList.add(elem);
        return elementList.indexOf(elem);
    }

    /**
     * Returns true if last element in this circuit is type of Element.
     * @return
     */
    public Integer getElementListCount(){
        if  (elementList != null) {
            return elementList.size();
        }else {
            return 0;
        }
    }

    public Integer getElemTypeCount(){
        if  (getElementListCount() > 0) {
            int count = 0;
            for (CircuitElement element : elementList) {
                CircuitEnum circType = element.getCircuitType();
                if (circType != null && circType.equals(CircuitEnum.ELEMENT)){
                    count++;
                }
            }
            return count;
        }
        return 0;
    }

    public Boolean isLastElemElementType(){
        if  (getElementListCount() > 0) {
            int size = elementList.size();
            CircuitEnum circType = elementList.get(getElementListCount() - 1).getCircuitType();
            if (circType != null && circType.equals(CircuitEnum.ELEMENT)){
                return true;
            }
        }
        return false;
    }

    public CircuitEnum getCircuitType() {
        return circuitType;
    }

    public void setCircuitType(CircuitEnum circuitType) {
        this.circuitType = circuitType;
    }

    public Double getSumValue() {
        return sumValue;
    }

    public void setSumValue(Double sumValue) {
        this.sumValue = sumValue;
    }

    public Double getElemValue() {
        return elemValue;
    }

    public void setElemValue(Double elemValue) {
        this.elemValue = elemValue;
    }

    public List<CircuitElement> getElementList() {
        return elementList;
    }

    public void setElementList(List<CircuitElement> elementList) {
        this.elementList = elementList;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public CircuitElement getParent() {
        return parent;
    }

    public void setParent(CircuitElement parent) {
        this.parent = parent;
    }

    public Integer getItemsTotal() {
        return itemsTotal;
    }

    public void setItemsTotal(Integer itemsTotal) {
        this.itemsTotal = itemsTotal;
    }
}
