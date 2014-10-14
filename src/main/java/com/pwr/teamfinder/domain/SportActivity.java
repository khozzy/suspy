package com.pwr.teamfinder.domain;

public enum  SportActivity {

    FOOTBALL("Pilka nozna"),
    VOLLEYBALL("Siatkowka"),
    BASKETBALL("Koszykowka");

    String name;

    SportActivity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
