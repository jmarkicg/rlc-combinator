package hr.markic.rlc.model;

public class CombinationModel {
    String combString;
    CircuitElement combinationElements;

    public void setCombinationElements(CircuitElement combinationElements) {
        this.combinationElements = combinationElements;
    }

    public String getCombString() {
        return combString;
    }

    public void setCombString(String combString) {
        this.combString = combString;
    }
}
