package com.solvd.army;

import java.time.LocalDate;

public class Soldier extends Human {

    public static int count;

    private Rank rank;
    private ServiceTerm term;

    public Soldier(String firstName, String lastName, LocalDate birthday, Rank rank, ServiceTerm term) {
        super(firstName, lastName, birthday);
        this.rank = rank;
        this.term = term;
        count++;
    }

    public enum Rank {

        SQUADDIE("Squaddie"), CORPORAL("Corporal"), LANCE_SERGEANT("Lance-sergeant"), SERGEANT("Sergeant"),
        STAFF_SERGEANT("Staff-sergeant"), SENIOR_SERGEANT("Senior-sergeant"), WARRANT("Warrant"),
        SENIOR_WARRANT("Senior-warrant"), SUBLIEUTENANT("Sublieutenant"), LIEUTENANT("Lieutenant"),
        SENIOR_LIEUTENANT("Senior-lieutenant"), CAPTAIN("Captain"), MAJOR("Major"),
        LIEUTENANT_COLONEL("Lieutenant-colonel"), COLONEL("Colonel"), MAJOR_GENERAL("Major-general"),
        LIEUTENANT_GENERAL("Lieutenant-general"), COLONEL_GENERAL("Colonel-general");

        private final String rank;

        Rank(String rank) {
            this.rank = rank;
        }

        public String getRank() {
            return rank;
        }

    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public ServiceTerm getTerm() {
        return term;
    }

    public void setTerm(ServiceTerm term) {
        this.term = term;
    }

    public static int getCount() {
        return count;
    }

}
