package com.solvd.army.domain;

import java.util.ArrayList;
import java.util.List;

public class Army {

    private long id;
    private String country;
    private List<MilitaryUnit> militaryUnits;

    public Army() {
        militaryUnits = new ArrayList<>();
    }

    public Army(String country) {
        this.country = country;
    }

    public Army(String country, List<MilitaryUnit> militaryUnits) {
        this.country = country;
        this.militaryUnits = militaryUnits;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Army army = (Army) obj;
        boolean result = army.getId() == this.getId()
                && (this.getCountry() != null && this.getCountry().equals(army.getCountry()));
        if(army.getMilitaryUnits().size() != this.getMilitaryUnits().size()) {
            return false;
        }
        int counter = 0;
        boolean result1 = false;
        for(int i = 0; i < army.getMilitaryUnits().size(); i++) {
            if(army.getMilitaryUnits().get(i).equals(this.getMilitaryUnits().get(i))) {
                counter++;
                if(counter == army.getMilitaryUnits().size()) {
                    result1 = true;
                }
            }
            else {
                result1 = false;
            }
        }
        return result && result1;
    }
}
