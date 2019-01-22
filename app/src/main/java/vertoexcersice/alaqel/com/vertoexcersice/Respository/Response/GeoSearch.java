package vertoexcersice.alaqel.com.vertoexcersice.Respository.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeoSearch {

    @SerializedName("pageid")
    String pageid;

    @SerializedName("title")
    String title;

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

    public void setImagesList(List<Images> imagesList) {
        this.imagesList = imagesList;
    }
}
