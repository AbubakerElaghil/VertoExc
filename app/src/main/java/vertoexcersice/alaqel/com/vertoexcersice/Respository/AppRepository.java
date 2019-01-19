package vertoexcersice.alaqel.com.vertoexcersice.Respository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.ultimate.pharmazadadmin.Database.AppDatabase;
import com.ultimate.pharmazadadmin.Database.Driver.Driver;
import com.ultimate.pharmazadadmin.Database.User.User;
import com.ultimate.pharmazadadmin.Respository.Server.Request.MyApiEndpointInterface;
import com.ultimate.pharmazadadmin.Respository.Server.Response.MultipleObjectHeader;
import com.ultimate.pharmazadadmin.Respository.Server.Response.ResponseObject;
import com.ultimate.pharmazadadmin.utilities.RequestObject;

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

public class AppRepository {

    public static final int LOGIN_RQST = 1;
    public static final int GET_ORDERS_RQST = 2;
    public static final int ACCEPT_ORDER_RQST = 3;
    public static final int EMPLOYEE_LIST = 4;
    public static final int CHANGE_ORDER_STATUS_RQST= 5;

    private MyApiEndpointInterface apiService;
    public static String BASE_URL = "http://ultieg.dyndns.org:8091";
    public static String Server_URL = BASE_URL + "/PharmazedService/Service.svc/";

    public LiveData<List<Driver>> driversLiveData ;



    private Executor executor = Executors.newSingleThreadExecutor();
    private AppDatabase mDb;

    public static AppRepository getInstance(Context context) {
        return new AppRepository(context);
    }

    public AppRepository(Context context) {

        mDb = AppDatabase.getInstance(context);

        initRetrofit();

    }

    private void initRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS).readTimeout(120, TimeUnit.SECONDS).addInterceptor(interceptor).build();

        try {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Server_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            apiService = retrofit.create(MyApiEndpointInterface.class);

        } catch (Exception e) {
            Log.e("EXCEPTION", e.getMessage());
        }
    }

    public User getUser() {
        return mDb.userDao().getUser();
    }

    public void saveUser(final User user) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.userDao().insertUser(user);
            }
        });
    }

    public void saveEmployess(final List<MultipleObjectHeader> multipleObjectHeaderList) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.driverDao().deleteDrivers();

                List<Driver> driverList = new ArrayList<>();
                for (int i = 0; i < multipleObjectHeaderList.size(); i++) {
                    if(multipleObjectHeaderList.get(i).getEmployeeTypeId().equalsIgnoreCase("Article")){
                        Driver driver = new Driver(multipleObjectHeaderList.get(i).getId(),multipleObjectHeaderList.get(i).getName()
                                ,multipleObjectHeaderList.get(i).getMobileNumber(),false);
                        driverList.add(driver);
                    }

                }
                mDb.driverDao().insertDriver(driverList);
            }
        });

    }


    public  void getDrivers() {

        //Live Data doesn't need to be in Executor because it doesn't run on the UI Thread.
       driversLiveData = mDb.driverDao().getDrivers();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.driverDao().UnselectDrivers(false);

            }
        });
     }

    public void driverSelect(final String id) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.driverDao().UnselectDrivers(false);
                mDb.driverDao().selectDriver(true,id);
            }
        });
    }
    public void clearUser(final User user) {

                mDb.userDao().deleteUser(user);

    }

    public void Request(RequestObject requestObject, MutableLiveData<ResponseObject> data) {
        switch (requestObject.getRequestCode()) {
            case LOGIN_RQST:
                apiService.Login(requestObject.getLoginPost()).enqueue(new ResponseCallBacks(data));
                break;

                case  GET_ORDERS_RQST:
                apiService.getOrders(requestObject.getType(),requestObject.getYear(),requestObject.getActivityNumber(),requestObject.getOrderId()
                ,requestObject.getBranchId(),requestObject.getOrderStatus(),requestObject.getLanguageFlag(),requestObject.getStartIndex()+"",requestObject.getRowCounts()).enqueue(new ResponseCallBacks(data,requestObject.getCurrentListId()));
                break;
                case  ACCEPT_ORDER_RQST:
                apiService.acceptOrder(requestObject.getAcceptOrderPost() ).enqueue(new ResponseCallBacks(data ));
                break;
                case  EMPLOYEE_LIST:
                apiService.getEmployeeList(requestObject.getType(),requestObject.getYear(),requestObject.getActivityNumber()
                        ,requestObject.getBranchId(),requestObject.getLanguageFlag() ).enqueue(new ResponseCallBacks(data ));
                break;

                case  CHANGE_ORDER_STATUS_RQST:
                    apiService.changeOrderStatus(requestObject.getType(), requestObject.getYear(), requestObject.getActivityNumber()
                            , requestObject.getUserId()  , requestObject.getOrderId(), requestObject.getOrderStatus(), requestObject.getEmployeeId()).enqueue(new ResponseCallBacks(data));
                    break;
        }

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
            if(responseObject!=null) {
                responseObject.setCurrentListId(currentListId);
            }
            data.setValue(responseObject);
        }
    }


}





