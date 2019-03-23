package hr.markic.rlc.converter;

import hr.markic.rlc.enums.BaseElementEnum;

import java.beans.PropertyEditorSupport;

public class BaseElementEnumConverter extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        BaseElementEnum type = BaseElementEnum.fromString(text);
        setValue(type);
    }
}