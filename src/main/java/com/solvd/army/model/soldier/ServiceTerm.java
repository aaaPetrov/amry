package com.solvd.army.model.soldier;

import java.time.LocalDate;

public class ServiceTerm {

    private LocalDate enteredAt;
    private LocalDate endOn;

    public ServiceTerm(LocalDate enteredAt, LocalDate endOn) {
        this.enteredAt = enteredAt;
        this.endOn = endOn;
    }

    public ServiceTerm(LocalDate enteredAt, int serviceTermInYears) {
        this.enteredAt = enteredAt;
        this.endOn = enteredAt.plusYears(serviceTermInYears);
    }

    public LocalDate getEntered() {
        return enteredAt;
    }

    public void setEntered(LocalDate enteredAt) {
        this.enteredAt = enteredAt;
    }

    public LocalDate getEnd() {
        return endOn;
    }

    public void setEnd(LocalDate endOn) {
        this.endOn = endOn;
    }

}
