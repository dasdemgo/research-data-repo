package at.ac.tuwien.utils;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;

public enum DataType {
    DATE("dd/dd/dddd"),
    EMAIL("@gmail"),
    NUMBER("[0-9]+"),
    STRING("^[A-Za-z0-9? ,_-]+$"),
    INTEGER("[+-]?[0-9][0-9]*");
    //BOOLEAN("")

    private final String regEx;

    public String getRegEx() {
        return regEx;
    }

    DataType(String regEx) {
        this.regEx = regEx;
    }

    public static Optional<DataType> getTypeOfField(String str) {
        return Arrays.stream(DataType.values())
                .filter(dt -> {
                    return Pattern.compile(dt.getRegEx()).matcher(str).matches();
                })
                .findFirst();
    }
}

