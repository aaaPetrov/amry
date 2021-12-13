package com.solvd.army.domain.resources;


public class Weapon {

    private WeaponType weaponType;
    private int amount;

    public Weapon() {
    }

    public Weapon(WeaponType weaponType, int amount) {
        this.weaponType = weaponType;
        this.amount = amount;
    }

    public enum WeaponType {
        P_APS, P_SPS, P_MR_444, P_MP_448, P_P_96, A_AKM, A_AK_47, A_AK_74M, A_9A91, A_A_91M,
        SR_SVD, SR_SVY_AS, SR_SV_98, SR_OSV_96, SR_ASVK, MG_RPK, MG_PK, MG_PKM, MG_PKMT, MG_PKMB, MG_KPV,
        GL_GP_25, GL_6G30, GL_GM_94, GL_RMG, GL_RPG_26, GL_RPG_32;

        private final long weaponId;

        WeaponType() {
            this.weaponId = this.ordinal() + 1;
        }

        public long getWeaponId() {
            return weaponId;
        }

    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
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
        Weapon weapon = (Weapon) obj;
        return this.getWeaponType().equals(weapon.getWeaponType()) && this.getAmount() == weapon.getAmount();
    }

}
