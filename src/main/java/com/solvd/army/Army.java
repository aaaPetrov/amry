package com.solvd.army;

import java.util.List;

public abstract class Army {

    private final String country;
    private List<MilitaryUnit> militaryUnits;

    protected Army(String country) {
        this.country = country;
    }

    protected Army(String country, List<MilitaryUnit> militaryUnits) {
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
