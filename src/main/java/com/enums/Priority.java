package com.enums;

public enum Priority {

    LOW(0 , "LOW"), MEDIUM(1, "MEDIUM"), HIGH(2, "HIGH");

    private Integer code;
    private String description;

    Priority(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Priority toEnum(Integer code) throws IllegalAccessException {
        if(code == null){
            return null;
            }else {
            for (Priority p : Priority.values()) {
                if (code.equals(p.getCode())) {
                    return p;
                }
            }
        }
        throw new IllegalAccessException("Invalid Priority");
    }
}
