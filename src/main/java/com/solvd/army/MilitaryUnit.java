package com.solvd.army;

import java.util.List;

public class MilitaryUnit {

    private List<Soldier> soldiers;
    private List<Ammo> ammunition;
    private List<Weapon> weapons;
    private List<Tank> tanks;
    private List<Plane> planes;
    private String name;
    private Location location;

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

}
