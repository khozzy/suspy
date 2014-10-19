package com.pwr.teamfinder.domain;

public enum Role {

    ADMIN("Administrator"),
    SPORTSMAN("Sportsman"),
    GYM_OWNER("Object owner");

    String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
