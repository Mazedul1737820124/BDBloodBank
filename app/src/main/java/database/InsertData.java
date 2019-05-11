package database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertData {

    DatabaseReference databaseReference;
    public static String primaryKey;

    private String  Phone, Email,Name, Password,
            District,Address,BloodGroup,
            EnableSer,Log,LastDonationDate;

    public InsertData(String phone, String email,
                      String name, String password,
                      String district, String address,
                      String bloodGroup, String enableSer,
                      String log, String lastDonationDate) {
        Phone = phone;
        Email = email;
        Name = name;
        Password = password;
        District = district;
        Address = address;
        BloodGroup = bloodGroup;
        EnableSer = enableSer;
        Log = log;
        LastDonationDate = lastDonationDate;

        //set all information of the user
        databaseReference = FirebaseDatabase.getInstance().getReference(Phone);
        databaseReference.child("Id").setValue(Phone);
        databaseReference.child("Name").setValue(Name);
        databaseReference.child("Email").setValue(Email);
        databaseReference.child("Password").setValue(Password);
        databaseReference.child("District").setValue(District);
        databaseReference.child("Address").setValue(Address);
        databaseReference.child("BloodGroup").setValue(BloodGroup);
        databaseReference.child("EnableSer").setValue(EnableSer);
        databaseReference.child("Log").setValue(Log);
        databaseReference.child("LastDonationDate").setValue(LastDonationDate);
    }
}
