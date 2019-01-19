package vertoexcersice.alaqel.com.vertoexcersice.Respository.Server.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Abubaker on 18/10/2016.
 */

public class ResponseObject   {

    @SerializedName("SingleObjectHeader")
    SingleObjectHeader singleObjectHeader;

    @SerializedName("MultipleObjectHeader")
    List<MultipleObjectHeader> multipleObjectHeaderList;



    @SerializedName("_Result")
    Result result;


    String currentListId;

    public String getCurrentListId() {
        return currentListId;
    }

    public void setCurrentListId(String currentListId) {
        this.currentListId = currentListId;
    }

    public List<MultipleObjectHeader> getMultipleObjectHeaderList() {
        return multipleObjectHeaderList;
    }

    public SingleObjectHeader getSingleObjectHeader() {
        return singleObjectHeader;
    }

    public Result getResult() {
        return result;
    }
}
