package com.solvd.army.domain.resources;

public class Ammo {

    private AmmoType ammoType;
    private int amount;

    public Ammo() {

    }

    public Ammo(AmmoType ammoType, int amount) {
        this.ammoType = ammoType;
        this.amount = amount;
    }

    public enum AmmoType {

        B_5_45x39, B_5_56x45, B_6x51, B_7_62x39, B_7_62x54_R, B_7_92x57mm, B_8_6x70;

        private final long ammoId;

        AmmoType() {
            this.ammoId = this.ordinal() + 1;
        }

        public long getAmmoId() {
            return ammoId;
        }

    }

    public void setAmmoType(AmmoType ammoType) {
        this.ammoType = ammoType;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Ammo ammo = (Ammo) obj;
        return this.getAmmoType().equals(ammo.getAmmoType()) && this.getAmount() == ammo.getAmount();
    }

}