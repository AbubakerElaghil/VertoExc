package vertoexcersice.alaqel.com.vertoexcersice.Respository.Request;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vertoexcersice.alaqel.com.vertoexcersice.Respository.Response.ResponseObject;

public class AppRepository {

    private MyApiEndpointInterface apiService;

   // https://en.wikipedia.org/w/api.php?action=query&list=geosearch&gsradius=10000&gscoord=37.786971|-122.399677&gslimit=50&format=json

    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        return new AppRepository(context);
    }

    public AppRepository(Context context) {


        initRetrofit();

    }

    private void initRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).addInterceptor(interceptor).build();

        try {

            Retrofit retrofit = new Retrofit.Builder()
           //         .baseUrl(Server_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiService = retrofit.create(MyApiEndpointInterface.class);

        } catch (Exception e) {
            Log.e("EXCEPTION", e.getMessage());
        }
    }

    public void getArticles(String lat, String longt, MutableLiveData<ResponseObject> articlesLists) {
          String url = "https://en.wikipedia.org/w/api.php?action=query&list=geosearch&gsradius=10000&gscoord="+lat+"|-"+
                  longt+"&gslimit=50&format=json";
        apiService.getArticles(url).enqueue(new ResponseCallBacks(articlesLists));
    }


    private class ResponseCallBacks implements Callback<ResponseObject> {


        MutableLiveData<ResponseObject> data;
        private String currentListId;

        public ResponseCallBacks(MutableLiveData<ResponseObject> data) {
            this.data = data;
        }
        public ResponseCallBacks(MutableLiveData<ResponseObject> data, String currentListId) {
            this.data = data;
            this.currentListId= currentListId;
        }

        @Override
        public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
            try {
                Log.e("RESPONSE", response.message());
                HandleResponse(response.body());
            } catch (Exception e) {
                Log.e("EXception", e.getMessage());
                HandleResponse(null);
            }
        }

        @Override
        public void onFailure(Call<ResponseObject> call, Throwable t) {
            HandleResponse(null);
        }


        private void HandleResponse(ResponseObject responseObject) {

            data.setValue(responseObject);
        }
    }


}





