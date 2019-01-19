package vertoexcersice.alaqel.com.vertoexcersice.Respository.Server.Response;

import com.google.gson.annotations.SerializedName;

public class UserAddressDetails {
    @SerializedName("Value")
    String Value;

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
