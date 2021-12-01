package com.solvd.army.domain.resources;

public class Tank {

    private TankType tankType;
    private int amount;

    public Tank() {
    }

    public Tank(TankType tankType, int amount) {
        this.tankType = tankType;
        this.amount = amount;
    }

    public enum TankType {

        T_2C25, T_2C31, BMD_4, BMD_4M, BTR_90, BTR_MD, T_14, T_15, T_90, T_95, TOC_1, TOC_1A, BLACK_EAGLE;

        private final long tankId;

        TankType() {
            this.tankId = this.ordinal() + 1;
        }

        public long getTankId() {
            return tankId;
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

    public void setTankType(TankType tankType) {
        this.tankType = tankType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Tank tank = (Tank) obj;
        return this.getTankType().equals(tank.getTankType()) && this.getAmount() == tank.getAmount();
    }

}
