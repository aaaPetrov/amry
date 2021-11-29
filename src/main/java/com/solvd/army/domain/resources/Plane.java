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
        MIG_35("MiG-35"), CY_57("Cy-57"), TY_160("Ty-160"), CY_25("Cy-25"), CY_35C("Cy-35C"),
        CY_47("Cy-47"), TY_22M3("Ty-22M3"), AN_124("An-124"), B_52("B-52");

        private final long planeId;
        private final String planeType;

        PlaneType(String planeType) {
            this.planeId = this.ordinal() + 1;
            this.planeType = planeType;
        }

        public String getPlaneType() {
            return planeType;
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
