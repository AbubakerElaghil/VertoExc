package vertoexcersice.alaqel.com.vertoexcersice.Respository.Server.Response;

import com.google.gson.annotations.SerializedName;


/**
 * Created by Abubaker on 06/12/2016.
 */

public class Result   {
    @SerializedName("_ErrMsg")
    String ErrorMessage="";

    @SerializedName("_ErrNo")
    int ErrorNumber;

    @SerializedName("_ErrStatuse")
    Boolean ErrorState;

    public String getErrorMessage() {
        return ErrorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public int getErrorNumber() {
        return ErrorNumber;
    }

    public void setErrorNumber(int errorNumber) {
        ErrorNumber = errorNumber;
    }

    public Boolean getErrorState() {
        return ErrorState;
    }

    public void setErrorState(Boolean errorState) {
        ErrorState = errorState;
    }
}
