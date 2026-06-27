package com.enums;


public enum Status {
    PENDING(0 , "PENDING"), PROCESSING(1, "PROCESSING"), CONCLUDED(2, "CONCLUDED");

    private Integer code;
    private String description;

    Status(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static boolean isValid(Status status) {
        for (Status s : Status.values()) {
            if (s.description.equalsIgnoreCase(status.getDescription())) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unused")
    public static Status toEnum(Integer code) throws IllegalAccessException {
        if(code == null){
            return null;
        }else {
            for (Status s : Status.values()) {
                if (code.equals(s.getCode())) {
                    return s;
                }
            }
        }
        throw new IllegalAccessException("Invalid Status");
    }

}
