package vertoexcersice.alaqel.com.vertoexcersice.Respository.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pages {

    @SerializedName("pageid")
    String pageid;

    @SerializedName("title")
    String title;

    @SerializedName("images")
    List<Images> imagesList;

    public String getPageid() {
        return pageid;
    }

    public String getTitle() {
        return title;
    }

    public List<Images> getImagesList() {
        return imagesList;
    }
}
