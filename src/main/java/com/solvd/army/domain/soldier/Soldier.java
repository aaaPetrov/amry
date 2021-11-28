package com.solvd.army.domain.soldier;

import java.time.LocalDate;

public class Soldier extends Recruit {

    public static int count;

    private long id;
    private Rank rank;
    private ServiceTerm term;

    public Soldier() {
        super();
    }

    public Soldier(String firstName, String lastName, LocalDate birthday, Rank rank, ServiceTerm term) {
        super(firstName, lastName, birthday);
        this.rank = rank;
        this.term = term;
        count++;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public enum Rank {

        SQUADDIE("Squaddie"), CORPORAL("Corporal"), LANCE_SERGEANT("Lance-sergeant"), SERGEANT("Sergeant"),
        STAFF_SERGEANT("Staff-sergeant"), SENIOR_SERGEANT("Senior-sergeant"), WARRANT("Warrant"),
        SENIOR_WARRANT("Senior-warrant"), SUBLIEUTENANT("Sublieutenant"), LIEUTENANT("Lieutenant"),
        SENIOR_LIEUTENANT("Senior-lieutenant"), CAPTAIN("Captain"), MAJOR("Major"),
        LIEUTENANT_COLONEL("Lieutenant-colonel"), COLONEL("Colonel"), MAJOR_GENERAL("Major-general"),
        LIEUTENANT_GENERAL("Lieutenant-general"), COLONEL_GENERAL("Colonel-general");

        private final String rank;
        private final long rankId;

        Rank(String rank) {
            this.rankId = this.ordinal() + 1;
            this.rank = rank;
        }

        public String getRank() {
            return rank;
        }

        public long getRankId() {
            return rankId;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Soldier soldier = (Soldier) obj;
        return this.getId() == soldier.getId() && this.getAge() == soldier.getAge() && this.getTerm().equals(soldier.getTerm())
                && this.getRank().equals(soldier.getRank()) && this.getBirthday().equals(soldier.getBirthday())
                && (this.getFirstName() != null && this.getFirstName().equals(soldier.getFirstName()))
                && (this.getLastName() != null && this.getLastName().equals(soldier.getLastName()));
    }

}
