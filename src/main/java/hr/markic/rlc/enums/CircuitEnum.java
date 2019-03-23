package hr.markic.rlc.enums;

public enum CircuitEnum {
    SERIES ("series"),
    PARALLEL ("parallel"),
    ELEMENT ("element");

    private String type;

    CircuitEnum(String value) {
        this.type = value;
    }

    @Override
    public String toString() {
        return type;
    }

}
