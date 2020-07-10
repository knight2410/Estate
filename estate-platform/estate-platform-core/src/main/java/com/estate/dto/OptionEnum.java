package com.estate.dto;

public enum  OptionEnum {
    thuenguyencan("Thuê nguyên căn"),
    tangtret("Tầng trệt"),
    noithat("Nội thất");

    private final String value;
    private OptionEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
