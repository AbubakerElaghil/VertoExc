package vertoexcersice.alaqel.com.vertoexcersice.Respository.Server.Request;

import com.ultimate.pharmazadadmin.Login.LoginPost;
import com.ultimate.pharmazadadmin.OrderDetails.AcceptOrderPost;
import com.ultimate.pharmazadadmin.Respository.Server.Response.ResponseObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Abubaker on 28/06/2016.
 */

public interface MyApiEndpointInterface {


    @Headers("Content-Type: application/json")
    @POST("GetEmployeeLoginData")
    Call<ResponseObject> Login(@Body LoginPost loginPost);



    @GET("GetOrderDataList?")
    Call<ResponseObject> getOrder(
            @Query("type") String type,
            @Query("year") String year,
            @Query("activityNumber") String activeNo,
            @Query("languageFlag") String LangId,
            @Query("userId") String userId,
            @Query("employeeId") String employeeId,
            @Query("startIndex") String startIndex,
            @Query("rowCounts") String rowCounts);

    @GET("ChangeOrderStatus?")
    Call<ResponseObject> changeOrderStatus(@Query("type") String type
            , @Query("year") String year
            , @Query("activityNumber") String activeno
            , @Query("userId") String userId
            , @Query("orderId") String orderId
            , @Query("orderStatus") String orderStatus
            , @Query("driverId") String driverId);


    @GET("GetPharmacyOrderRequestDataList?")
    Call<ResponseObject> getOrders(@Query("type") String type, @Query("year") String year,
                                   @Query("activityNumber") String activityNumber, @Query("orderId") String orderId,
                                   @Query("pharmacyBranchId") String branchId, @Query("orderStatusTypeId") String orderStatus,
                                   @Query("languageFlag") String languageFlag
            , @Query("startIndex") String startIndex
            , @Query("rowCounts") String rowCounts);


    @GET("GetEmployeesDataList?")
    Call<ResponseObject> getEmployeeList(@Query("type") String type, @Query("year") String year,
                                         @Query("activityNumber") String activityNumber,
                                         @Query("pharmacyBranchId") String branchId, @Query("languageFlag") String languageFlag);




    @Headers("Content-Type: application/json")
    @POST("ChangePharmacyOrderRequestData")
    Call<ResponseObject> acceptOrder(@Body AcceptOrderPost acceptOrderPost);



}

