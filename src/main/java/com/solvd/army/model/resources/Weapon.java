package com.solvd.army.model.resources;

public class Weapon {

    private WeaponType weaponType;
    private int amount;

    public Weapon(WeaponType weaponType, int amount) {
        this.weaponType = weaponType;
        this.amount = amount;
    }

    public enum WeaponType {
        P_APS("P.APS"), P_SPS("P.SPS"), P_MR_444("P.MR-444"), P_MP_448("P.MP-448"), P_P_96("P.P-96"),
        A_AKM("A.AKM"), A_AK_47("A.AK-47"), A_AK_74M("A.AK-74M"), A_9A91("A.9A91"), A_A_91M("A.A-91M"),
        SR_SVD("SR.SVD"), SR_SVY_AS("SR.SVY_AS"), SR_SV_98("SR.SV-98"), SR_OSV_96("SR.OSV-96"), SR_ASVK("SR.ASVK"),
        MG_RPK("MG.RPK"), MG_PK("MG.PK"), MG_PKM("MG.PKM"), MG_PKMT("MG.PKMT"), MG_PKMB("MG.PKMB"), MG_KPV("MG.KPV"),
        GL_GP_25("GL.GP-25"), GL_6G30("GL.6G30"), GL_GM_94("GL.GM-94"), GL_RMG("GL.RMG"), GL_RPG_26("GL.RPG26"),
        GL_RPG_32("GL.RPG32");

        private String weaponType;

        WeaponType(String weaponType) {
            this.weaponType = weaponType;
        }

        public String getWeaponType() {
            return weaponType;
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

}
