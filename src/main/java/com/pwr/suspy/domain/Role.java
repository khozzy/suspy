package com.pwr.suspy.domain;

public enum Role {

    ADMIN("Administrator"),
    NORMAL_USER("Normal user"),
    PLACE_OWNER("Object owner"),
    UNVERIFIED("Unverified"),
    BLOCKED("Blocked");

    String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
