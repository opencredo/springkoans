package com.programmingspring.koans;

import org.springframework.core.convert.converter.Converter;

public class WrappedStringConverter implements Converter<String, WrappedString> {

    @Override
    public WrappedString convert(String source) {
        WrappedString result = new WrappedString();
        result.setWrappedString(source);
        return result;
    }
}
