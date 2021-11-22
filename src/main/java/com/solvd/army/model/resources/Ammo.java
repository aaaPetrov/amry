package com.solvd.army.model.resources;

public class Ammo {

    private final AmmoType ammoType;
    private int amount;

    public Ammo(AmmoType ammoType, int amount) {
        this.ammoType = ammoType;
        this.amount = amount;
    }

    public enum AmmoType {

        B_5_45x39("5.45х39mm"), B_5_56x45("5.56х45mm"), B_6x51("6х51mm"),
        B_7_62x39("7.62х39mm"), B_7_62x54_R("7.62х54mm R"), B_7_92x57mm("7.92х57mm"),
        B_8_6x70("8.6x70mm");

        private final String ammoType;

        AmmoType(String ammoType) {
            this.ammoType = ammoType;
        }

        public String getAmmoType() {
            return ammoType;
        }

    }

    public AmmoType getAmmoType() {
        return ammoType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}