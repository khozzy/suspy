package com.pwr.suspy.domain;

public enum Activity {

    FOOTBALL("Pilka nozna"),
    VOLLEYBALL("Siatkowka"),
    BASKETBALL("Koszykowka"),
    VISITING_SEASIDE("Wizyta nad morzem");

    String name;

    Activity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
