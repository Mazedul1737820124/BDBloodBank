package com.example.bdbloodbank;

import android.app.Activity;
import android.text.format.DateFormat;

import java.util.Date;

import static java.lang.StrictMath.abs;

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
       return EnableSer;
    }

    public String getId() {
        return Id;
    }

    public String getLastDonationDate() {

        try {
            Date d = new Date();
            CharSequence s = DateFormat.format("yyyyMMdd ", d.getTime());
            String cDate = s.toString();
            String dDate = LastDonationDate;

            long currentDate = Integer.parseInt(cDate.substring(0, cDate.length() - 1));
            long donationDate = Integer.parseInt(dDate);
            int currentDay = Integer.parseInt(cDate.substring(6, 8));
            int currentMonth = Integer.parseInt(cDate.substring(4, 6));
            int donDay = Integer.parseInt(dDate.substring(6, 8));
            int donMonth = Integer.parseInt(dDate.substring(4, 6));

            if ((currentDay > donDay)) {
                if (((currentDate - donationDate) - 8800) <= 90) {
                    return "Ready to donate";
                }
                return "Last donation " + ((currentDate - donationDate) - 8800) + " days ago";

            } else if ((currentMonth > donMonth)) {
                if (((currentDate - donationDate) - 70) <= 90) {
                    return "Ready to donate";
                }
                return "Last donation " + ((currentDate - donationDate) - 70) + " days ago";

            } else {
                if (abs(currentDate - donationDate) <= 90) {
                    return "Ready to donate";
                }
                return "Last donation " + (currentDate - donationDate) + " days ago";

            }
        }catch (Exception e){}
        return "Last donation 20 days ago";
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
