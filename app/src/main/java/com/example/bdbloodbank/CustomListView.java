package com.example.bdbloodbank;

import android.app.Activity;

public class CustomListView {
    private String Address;
    private String BloodGroup;
    private String District;
    private String Email;
    private String EnableSer;
    private String Id;
    private String LastDonationDate;
    private String Log;
    private String Name;
    private String Password;

    public CustomListView(String address, String bloodGroup,
                          String district, String email,
                          String enableSer, String id,
                          String lastDonationDate,
                          String log, String name,
                          String password) {
        Address = address;
        BloodGroup = bloodGroup;
        District = district;
        Email = email;
        EnableSer = enableSer;
        Id = id;
        LastDonationDate = lastDonationDate;
        Log = log;
        Name = name;
        Password = password;
    }

    public CustomListView() {
    }

    public String getAddress() {
        return Address;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public String getDistrict() {
        return District;
    }

    public String getEmail() {
        return Email;
    }

    public String getEnableSer() {
        if("true".equals(EnableSer))
        return "Available";
        else {
            return "Hidden";
        }
    }

    public String getId() {
        return Id;
    }

    public String getLastDonationDate() {
        if("90".equals(LastDonationDate)){
            return "Ready to donate";
        }
        else {
            return "Last donation 10 day ago";
        }
    }

    public String getLog() {
        return Log;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }
}
