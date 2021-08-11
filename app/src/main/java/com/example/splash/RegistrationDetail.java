package com.example.splash;

public class RegistrationDetail {
    String Uid,Name,Specialization,Pass,Email,Experience;



    public RegistrationDetail(String uid, String name, String specialization, String email, String pass, String experience) {
        Uid= uid;
        Name = name;
        Specialization = specialization;
        Email=email;
        Pass = pass;
        Experience=experience;
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
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

    public void setSpecialization(String lname) {
        Specialization = lname;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
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
