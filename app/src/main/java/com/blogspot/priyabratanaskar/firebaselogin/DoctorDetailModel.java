package com.blogspot.priyabratanaskar.firebaselogin;

/**
 * Created by Priyabrata Naskar on 07-04-2021.
 */
public class DoctorDetailModel {
    private String day_name;
    String start_time;
    String end_time;
    String location;

    public DoctorDetailModel() {
    }

    public String getDay_name() {
        return day_name;
    }

    public void setDay_name(String day_name) {
        this.day_name = day_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
