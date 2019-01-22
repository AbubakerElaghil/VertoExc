package vertoexcersice.alaqel.com.vertoexcersice.Respository.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Query {

    @SerializedName("pages")
    @Expose
    private Map<Integer, Pages> result;


    @SerializedName("geosearch")
    List<GeoSearch> geoSearchList;

    public List<GeoSearch> getGeoSearchList() {
        return geoSearchList;
    }

    public Map<Integer, Pages> getResult() {
        return result;
    }
}
