package vertoexcersice.alaqel.com.vertoexcersice.Respository.Server.Response;

import com.google.gson.annotations.SerializedName;


/**
 * Created by Abubaker Al-aqel on 3/19/2017.
 */

public class SingleObjectHeader {


    @SerializedName("Id")
    String Id;

    @SerializedName("Name")
    String Name;

    @SerializedName("Age")
    String Age;

    @SerializedName("BirthDate")
    String BirthDate;

    @SerializedName("Email")
    String Email;

    @SerializedName("EmployeeTypeId")
    String EmployeeTypeId;

    @SerializedName("GenderId")
    String GenderId;

    @SerializedName("MobileNumber")
    String MobileNumber;


    @SerializedName("Password")
    String Password;

    @SerializedName("PharmacyBranchId")
    String PharmacyBranchId;

    @SerializedName("PharmacyId")
    String PharmacyId;

    @SerializedName("PharmacyLatitude")
    String PharmacyLatitude;

    @SerializedName("PharmacyLongitude")
    String PharmacyLongitude;

    @SerializedName("PharmacyName")
    String PharmacyName;

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getAge() {
        return Age;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public String getEmail() {
        return Email;
    }

    public String getEmployeeTypeId() {
        return EmployeeTypeId;
    }

    public String getGenderId() {
        return GenderId;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public String getPassword() {
        return Password;
    }

    public String getPharmacyBranchId() {
        return PharmacyBranchId;
    }

    public String getPharmacyId() {
        return PharmacyId;
    }

    public String getPharmacyLatitude() {
        return PharmacyLatitude;
    }

    public String getPharmacyLongitude() {
        return PharmacyLongitude;
    }

    public String getPharmacyName() {
        return PharmacyName;
    }
}
