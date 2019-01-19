package vertoexcersice.alaqel.com.vertoexcersice.Respository.Server.Request;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.ultimate.pharmazadadmin.Respository.Server.Response.ResponseObject;
import com.ultimate.pharmazadadmin.utilities.RequestObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abubaker on 01/07/2016.
 */

public class ServerRequestManager {
    public static final int LOGIN_REQUEST_CODE = 1;
    public static final int GET_ACTIVATION_CODE_REQUESTCODE = 12;
    public static final int FORGOT_PASSWORD_REQUEST_CODE = 13;
    public static final int SEND_ACTIVATION_CODE_REQUESTCODE = 14;

    public static final int GET_GEOLOCATION_REQUESTCODE = 4;
    public static final int GET_BRANCHES_REQUESTCODE = 41;

    public static final int REGISTER_REQUEST_CODE = 2;

    public static final int GET_CATEGORIES_RQST_CODE = 31;
    public static final int GET_SUB_CATEGORIES_RQST_CODE = 32;
    public static final int GET_PRODUCTS_RQST_CODE = 33;
    public static final int GET_MAIN_DATA_RQST_CODE = 34;

    public static final int GET_IMAGE_DATALIST_REQUESTCODE = 5;
    public static final int GET_PRODUCT_SEPCIFICATIONS_REQUESTCODE = 51;
    public static final int GET_PRODUCT_DETAILS_REQUESTCODE = 52;

    public static final int GET_ORDER_CHARGES_REQUEST_CODE = 61;
    public static final int GET_RECENT_ORDER_REQUESTCODE = 7;
    public static final int GET_SYSTEM_FLAGS_REQUESTCODE = 8;

    public static final int SAVE_INVOICE_REQUEST_CODE = 71;
    public static final int GET_ORDER_DETAILS_REQUESTCODE = 72;
    public static final int CANCEL_APPROVE_REJECT_ORDER_REQUESTCODE = 73;


    public static final int GET_USER_DETAILS_REQUESTCODE = 81;
    public static final int SAVE_ADDRESS_RQSTCODE = 82;


    MyApiEndpointInterface apiService;

    Retrofit retrofit;
    int RequestCode;
    Context mContext;

    public static String BASE_URL = "http://ultieg.dyndns.org:8091";
    public static String Server_URL = BASE_URL + "/PharmazedService/Service.svc/";


    FragmentActivity activity;

    public ServerRequestManager(Context context, int RequestCode) {
        this.activity = activity;
        this.RequestCode = RequestCode;
         mContext = context;

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).addInterceptor(interceptor).build();

        try {


            // String Server_URL = " http://mapp.yemensoft.net/TanazlWebService/Service.svc/";

            retrofit = new Retrofit.Builder()
                    .baseUrl(Server_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiService = retrofit.create(MyApiEndpointInterface.class);

        } catch (Exception e) {
             Log.e("EXCEPTION", e.getMessage());
        }
    }





    public void request(RequestObject requestObject) {

        Call<ResponseObject> responseObjectCall = null;
        switch (RequestCode) {


            case ServerRequestManager.LOGIN_REQUEST_CODE:
                Call<ResponseObject> call = apiService.Login(requestObject.getLoginPost());

                 break;



        }

            responseObjectCall.enqueue(new ResponseCallBacks());
    }


    private class ResponseCallBacks implements Callback<ResponseObject> {
        int ImageIndex = 0;
        String CategoryId;
        String SubCategoryId;

        public ResponseCallBacks() {

        }

        public ResponseCallBacks(int ImageIndex) {
            this.ImageIndex = ImageIndex;
        }

        public ResponseCallBacks(String CategoryId, String SubCategoryId) {
            this.CategoryId = CategoryId;
            this.SubCategoryId = SubCategoryId;
        }

        @Override
        public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
            try {
                if (response.isSuccessful()) {
                    Log.e("RESPONSE", response.message());
                    HandleResponse(response.body());
                } else {
                    if (response.errorBody() != null) {

                    }
                    HandleResponse(null);
                }


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

             /*
            eventObject.setResponseObject(responseObject);
            EventBus.getDefault().post(eventObject);*/
        }
    }

}
