package csit.semit.spr.webappssprlab3.entities;

import java.time.LocalDate;

public class DentistryPatient {
    private Long id;
    private String surname;
    private String name;
    private String pname;
    private LocalDate dr;
    private boolean gender;
    private String stateComments;

    public DentistryPatient(Long id, String surname, String name, String pname, LocalDate dr, boolean gender) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.pname = pname;
        this.dr = dr;
        this.gender = gender;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public LocalDate getDr() {
        return dr;
    }

    public void setDr(LocalDate dr) {
        this.dr = dr;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getStateComments() {
        return stateComments;
    }

    public String getGenderAsString() {
        return !gender ? "female" : "male";
    }

    public void setStateComments(String stateComments) {
        this.stateComments = stateComments;
    }

    @Override
    public String toString() {
        return "DentistryPatient{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", pname='" + pname + '\'' +
                ", dr=" + dr +
                ", gender=" + gender +
                ", stateComments='" + stateComments + '\'' +
                '}';
    }
}