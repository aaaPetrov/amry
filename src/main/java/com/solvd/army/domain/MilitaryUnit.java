package com.solvd.army.domain;

import com.solvd.army.domain.resources.Ammo;
import com.solvd.army.domain.resources.Plane;
import com.solvd.army.domain.resources.Tank;
import com.solvd.army.domain.resources.Weapon;
import com.solvd.army.domain.soldier.Soldier;

import java.util.ArrayList;
import java.util.List;

public class MilitaryUnit {

    private long id;
    private List<Soldier> soldiers;
    private List<Ammo> ammunition;
    private List<Weapon> weapons;
    private List<Tank> tanks;
    private List<Plane> planes;
    private String name;
    private Location location;

    public MilitaryUnit() {
        ammunition = new ArrayList<>();
        planes = new ArrayList<>();
        tanks = new ArrayList<>();
        weapons = new ArrayList<>();
        soldiers = new ArrayList<>();
    }

    public MilitaryUnit(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public List<Soldier> getSoldiers() {
        return soldiers;
    }

    public void setSoldiers(List<Soldier> soldiers) {
        this.soldiers = soldiers;
    }

    public List<Ammo> getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(List<Ammo> ammunition) {
        this.ammunition = ammunition;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }

    public List<Tank> getTanks() {
        return tanks;
    }

    public void setTanks(List<Tank> tanks) {
        this.tanks = tanks;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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
        MilitaryUnit militaryUnit = (MilitaryUnit) obj;
        if(militaryUnit.getAmmunition().size() != this.getAmmunition().size() || militaryUnit.getPlanes().size() != this.getPlanes().size()
                || militaryUnit.getTanks().size() != this.getTanks().size() || militaryUnit.getWeapons().size() != militaryUnit.getWeapons().size()
                || militaryUnit.getSoldiers().size() != this.getSoldiers().size()) {
            return false;
        }
        boolean result = this.getId() == militaryUnit.getId() && (this.getName() != null && this.getName().equals(militaryUnit.getName()))
                && (this.getLocation() != null && this.getLocation().equals(militaryUnit.getLocation()));

        int counter = 0;
        boolean result1 = false;
        for(int i = 0; i < militaryUnit.getSoldiers().size(); i++) {
            if(militaryUnit.getSoldiers().get(i).equals(this.getSoldiers().get(i))) {
                counter++;
                if(counter == militaryUnit.getSoldiers().size()) {
                    result1 = true;
                }
            }
            else {
                result1 = false;
            }
        }

        counter = 0;
        boolean result2 = false;
        for(int i = 0; i < militaryUnit.getTanks().size(); i++) {
            if(militaryUnit.getTanks().get(i).equals(this.getTanks().get(i))) {
                counter++;
                if(counter == militaryUnit.getTanks().size()) {
                    result2 = true;
                }
            }
            else {
                result2 = false;
            }
        }

        counter = 0;
        boolean result3 = false;
        for(int i = 0; i < militaryUnit.getWeapons().size(); i++) {
            if(militaryUnit.getWeapons().get(i).equals(this.getWeapons().get(i))) {
                counter++;
                if(counter == militaryUnit.getWeapons().size()) {
                    result3 = true;
                }
            }
            else {
                result3 = false;
            }
        }

        counter = 0;
        boolean result4 = false;
        for(int i = 0; i < militaryUnit.getPlanes().size(); i++) {
            if(militaryUnit.getPlanes().get(i).equals(this.getPlanes().get(i))) {
                counter++;
                if(counter == militaryUnit.getPlanes().size()) {
                    result4 = true;
                }
            }
            else {
                result4 = false;
            }
        }

        counter = 0;
        boolean result5 = false;
        for(int i = 0; i < militaryUnit.getAmmunition().size(); i++) {
            if(militaryUnit.getAmmunition().get(i).equals(this.getAmmunition().get(i))) {
                counter++;
                if(counter == militaryUnit.getAmmunition().size()) {
                    result5 = true;
                }
            }
            else {
                result5 = false;
            }
        }

        return result && result1 && result2 && result3 && result4 && result5;
    }
}
