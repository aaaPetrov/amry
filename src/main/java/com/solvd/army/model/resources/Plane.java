package com.solvd.army;

public class Plane {

    private final PlaneType planeType;
    private int amount;

    public Plane(PlaneType planeType, int amount) {
        this.planeType = planeType;
        this.amount = amount;
    }

    public enum PlaneType {
        MIG_35("MiG-35"), CY_57("Cy-57"), TY_160("Ty-160"), CY_25("Cy-25"), CY_35C("Cy-35C"),
        CY_47("Cy-47"), TY_22M3("Ty-22M3"), AN_124("An-124"), B_52("B-52");

        private final String planeType;

        PlaneType(String planeType) {
            this.planeType = planeType;
        }

        public String getPlaneType() {
            return planeType;
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

}
