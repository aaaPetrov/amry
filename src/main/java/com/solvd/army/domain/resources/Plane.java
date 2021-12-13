package com.solvd.army.domain.resources;

public class Plane {

    private PlaneType planeType;
    private int amount;

    public Plane() {
    }

    public Plane(PlaneType planeType, int amount) {
        this.planeType = planeType;
        this.amount = amount;
    }

    public enum PlaneType {
        MIG_35, CY_57, TY_160, CY_25, CY_35C, CY_47, TY_22M3, AN_124, B_52;

        private final long planeId;

        PlaneType() {
            this.planeId = this.ordinal() + 1;
        }

        public long getPlaneId() {
            return planeId;
        }
    }

    public PlaneType getPlaneType() {
        return planeType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPlaneType(PlaneType planeType) {
        this.planeType = planeType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Plane plane = (Plane) obj;
        return this.getPlaneType().equals(plane.getPlaneType()) && this.getAmount() == plane.getAmount();
    }

}
