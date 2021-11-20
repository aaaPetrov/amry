package com.solvd.army;

import java.util.List;

public class RussianArmy extends Army {

    private static RussianArmy instance = null;

    private RussianArmy() {
        super("Russia");
    }

    private RussianArmy(List<MilitaryUnit> militaryUnits) {
        super("Russia", militaryUnits);
    }

    public static RussianArmy getInstance() {
        if(instance == null) {
            instance = new RussianArmy();
        }
        return instance;
    }

    public static RussianArmy getInstance(List<MilitaryUnit> militaryUnits) {
        if(instance == null) {
            instance = new RussianArmy(militaryUnits);
        }
        return instance;
    }

}
