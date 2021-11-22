package com.solvd.army.model.resources;

public class Tank {

    private final TankType tankType;
    private int amount;

    public Tank(TankType tankType, int amount) {
        this.tankType = tankType;
        this.amount = amount;
    }

    public enum TankType {

        T_2C25("2С25 Sproot-CD"), T_2C31("2С31 Vena"), BMD_4("BMD-4"), BMD_4M("BMD-4M"), BTR_90("BTR-90"),
        BTR_MD("BTR-MD"), T_14("T-14 Armata"), T_15("T-15"), T_90("T-90"), T_95("T-95 Object"), TOC_1("TOC-1 Pinocchio"),
        TOC_1A("TOC-1A Sunfire"), BLACK_EAGLE("Black Eagle");

        private final String tankType;

        TankType(String tankType) {
            this.tankType = tankType;
        }

        public String getTankType() {
            return tankType;
        }

    }

    public TankType getTankType() {
        return tankType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
