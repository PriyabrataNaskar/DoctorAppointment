package com.blogspot.priyabratanaskar.firebaselogin;

public class Doctor {
    //Member variables representing doctor detail
    private String imageResource;
    private String doctorName;
    private String doctorExperience;
    private String doctorQualification;
    private String tag;
    private String about;
    private String fees;
    public Doctor() {
        //Required Empty Constructor for Firebase UI
    }

//    public Doctor(int imageResource, String doctorName, String doctorExperience, String doctorQualification) {
//        this.imageResource = imageResource;
//        this.doctorName = doctorName;
//        this.doctorExperience = doctorExperience;
//        this.doctorQualification = doctorQualification;
//    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorExperience() {
        return doctorExperience;
    }

    public String getDoctorQualification() {
        return doctorQualification;
    }

    public String getImageResource() {
        return imageResource;
    }

    public String getTag(){ return tag;}

    public String getAbout() {
        return about;
    }

    public String getFees() {
        return fees;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setDoctorExperience(String doctorExperience) {
        this.doctorExperience = doctorExperience;
    }

    public void setDoctorQualification(String doctorQualification) {
        this.doctorQualification = doctorQualification;
    }
    public void setTag(String tag){
        this.tag = tag;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }
}
