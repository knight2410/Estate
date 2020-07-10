package com.estate.enums;

public enum ETypes {
    fullOption("Thuê nguyên căn"),
    groundFloor("Tầng trệt"),
    furniture("Nội thất");

    private final String value;
    private ETypes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
