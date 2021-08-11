package com.example.splash;

public class mode {
    String Uid,Name,Specialization,Pass,Email,Experience;

    public mode() {
    }

    public mode(String uid,String name, String specialization, String pass, String email, String experience) {
        Uid= uid;
        Name = name;
        Specialization = specialization;
        Pass = pass;
        Email = email;
        Experience = experience;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSpecialization() {
        return Specialization;
    }

    public void setSpecialization(String specialization) {
        Specialization = specialization;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getExperience() {
        return Experience;
    }

    public void setExperience(String experience) {
        Experience = experience;
    }
    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }
}
