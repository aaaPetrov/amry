package com.solvd.army.model;

import java.util.List;

public class Army {

    private final String country;
    private List<MilitaryUnit> militaryUnits;

    public Army(String country) {
        this.country = country;
    }

    public Army(String country, List<MilitaryUnit> militaryUnits) {
        this.country = country;
        this.militaryUnits = militaryUnits;
    }

    public String getCountry() {
        return country;
    }

    public List<MilitaryUnit> getMilitaryUnits() {
        return militaryUnits;
    }

    public void setMilitaryUnits(List<MilitaryUnit> militaryUnits) {
        this.militaryUnits = militaryUnits;
    }

}
