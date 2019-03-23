package hr.markic.rlc.enums;

public enum BaseElementEnum {
    CAPACITOR ("capacitor"),
    RESISTOR ("resistor");

    private String type;

    BaseElementEnum(String value) {
        this.type = value;
    }

    @Override
    public String toString() {
        return type;
    }

    public static BaseElementEnum fromString(String text) {
        for (BaseElementEnum b : BaseElementEnum.values()) {
            if (b.type.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

}
