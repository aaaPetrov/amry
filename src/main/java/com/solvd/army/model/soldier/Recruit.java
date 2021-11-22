package com.solvd.army.model.soldier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public abstract class Recruit {

    private String firstName;
    private String lastName;
    private int age;
    private LocalDate birthday;

    protected Recruit(String firstName, String lastName, LocalDate birthday) {
        int age = (int) ChronoUnit.YEARS.between(birthday, LocalDate.now());
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
        this.age = (int) ChronoUnit.YEARS.between(this.birthday, LocalDateTime.now());
    }

    public LocalDate getBirthday() {
        return birthday;
    }

}
