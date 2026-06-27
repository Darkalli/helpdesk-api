package com.enums;

public enum Profile {

    ADMIN(0 , "ROLE_ADMIN"), CLIENT(1, "ROLE_CLIENT"), AGENT(2, "ROLE_AGENT");

    private Integer code;
    private String description;

    Profile(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static boolean isValid(Profile role) {
        for (Profile p : Profile.values()) {
            if (p.description.equalsIgnoreCase(role.getDescription())) {
                return true;
            }
        }
        return false;
    }

    public static Profile toEnum(Integer code) throws IllegalAccessException {
        if(code == null){
            return null;
            }else {
            for (Profile p : Profile.values()) {
                if (code.equals(p.getCode())) {
                    return p;
                }
            }
        }
        throw new IllegalAccessException("Invalid Profile");
    }
}
