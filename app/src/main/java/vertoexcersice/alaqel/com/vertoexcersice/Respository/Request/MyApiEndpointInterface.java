package vertoexcersice.alaqel.com.vertoexcersice.Respository.Request;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import vertoexcersice.alaqel.com.vertoexcersice.Respository.Response.ResponseObject;

/**
 * Created by Abubaker on 28/06/2016.
 */

public interface MyApiEndpointInterface {

    @GET
    Call<ResponseObject> getArticles(@Url String url);

}

