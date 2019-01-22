package vertoexcersice.alaqel.com.vertoexcersice.Respository.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Abubaker on 18/10/2016.
 */

public class ResponseObject   {

    @SerializedName("query")
    Query query;

    public Query getQuery() {
        return query;
    }

    String pageId;

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
}
