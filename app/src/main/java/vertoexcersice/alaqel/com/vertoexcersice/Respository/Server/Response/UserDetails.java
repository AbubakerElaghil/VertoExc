package vertoexcersice.alaqel.com.vertoexcersice.Respository.Server.Response;

import com.google.gson.annotations.SerializedName;

public class UserDetails {

    @SerializedName("Id")
    String Id;


    @SerializedName("Name")
    String Name;

    @SerializedName("Mobile")
    String Mobile;

    @SerializedName("Address")
    String Address;


    public String getId() {
        return Id;
    }

    public String getAddress() {
        if(Address==null)
            return "";
        return Address;
    }

    public String getName() {
        if(Name==null)
            return "";
        return Name;
    }

    public String getMobile() {
        if(Mobile==null)
            return "";
        return Mobile;
    }
}
