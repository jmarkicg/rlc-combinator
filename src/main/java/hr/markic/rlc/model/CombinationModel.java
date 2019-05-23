package hr.markic.rlc.model;

public class CombinationModel {
    String combString;
    String combEquation;
    String combStringValue;
    String combEquationVaule;
    CircuitElement combinationElements;
    Double value;
    int numItems;
    double errorPercentage;

    public void setCombinationElements(CircuitElement combinationElements) {
        this.combinationElements = combinationElements;
    }

    public String getCombString() {
        return combString;
    }

    public void setCombString(String combString) {
        this.combString = combString;
    }

    public String getCombEquation() {
        return combEquation;
    }

    public void setCombEquation(String combEquation) {
        this.combEquation = combEquation;
    }

    public double getErrorPercentage() {
        return errorPercentage;
    }

    public void setErrorPercentage(Double errorPercentage) {
        this.errorPercentage = errorPercentage;
    }

    public String getCombStringValue() {
        return combStringValue;
    }

    public void setCombStringValue(String combStringValue) {
        this.combStringValue = combStringValue;
    }

    public String getCombEquationVaule() {
        return combEquationVaule;
    }

    public void setCombEquationVaule(String combEquationVaule) {
        this.combEquationVaule = combEquationVaule;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public int getNumItems() {
        return numItems;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }
}
