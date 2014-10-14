package com.pwr.teamfinder.domain;

public enum Role {

    ADMIN("Administrator"),
    SPORTSMAN("Sportowiec"),
    GYM_OWNER("Wlasciciel obiektu");

    String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
