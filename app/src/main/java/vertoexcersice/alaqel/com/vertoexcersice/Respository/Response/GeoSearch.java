package vertoexcersice.alaqel.com.vertoexcersice.Respository.Response;

import com.google.gson.annotations.SerializedName;

public class GeoSearch {

    @SerializedName("pageid")
    String pageid;

    @SerializedName("title")
    String title;

    public String getPageid() {
        return pageid;
    }

    public String getTitle() {
        return title;
    }
}
