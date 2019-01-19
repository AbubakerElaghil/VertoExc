package vertoexcersice.alaqel.com.vertoexcersice.utilities;

import com.ultimate.pharmazadadmin.Login.LoginPost;
import com.ultimate.pharmazadadmin.OrderDetails.AcceptOrderPost;

public class RequestObject {

    int RequestCode;


    String type="MySQL";
    String year="-1";
    String activityNumber="-1";

    String LanguageFlag;
    String UserId;
    String EmployeeId;


    String OrderId;
    String OrderStatus;
    String DeviceName;
    String rowCounts="10";
    int StartIndex=0;

    LoginPost loginPost;

     String BranchId;
    private String currentListId;

    AcceptOrderPost acceptOrderPost;

    public AcceptOrderPost getAcceptOrderPost() {
        return acceptOrderPost;
    }

    public void setAcceptOrderPost(AcceptOrderPost acceptOrderPost) {
        this.acceptOrderPost = acceptOrderPost;
    }

    public RequestObject(int requestCode) {
        RequestCode = requestCode;
    }

    public String getBranchId() {
        return BranchId;
    }

    public void setBranchId(String branchId) {
        BranchId = branchId;
    }



    public LoginPost getLoginPost() {
        return loginPost;
    }


    public int getRequestCode() {
        return RequestCode;
    }

    public void setRequestCode(int requestCode) {
        RequestCode = requestCode;
    }

    public void setLoginPost(LoginPost loginPost) {
        this.loginPost = loginPost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getActivityNumber() {
        return activityNumber;
    }

    public void setActivityNumber(String activityNumber) {
        this.activityNumber = activityNumber;
    }

    public String getLanguageFlag() {
        return LanguageFlag;
    }

    public void setLanguageFlag(String languageFlag) {
        LanguageFlag = languageFlag;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        DeviceName = deviceName;
    }

    public String getRowCounts() {
        return rowCounts;
    }

    public void setRowCounts(String rowCounts) {
        this.rowCounts = rowCounts;
    }

    public int getStartIndex() {
        return StartIndex;
    }

    public void setStartIndex(int startIndex) {
        StartIndex = startIndex;
    }

    public void setCurrentListId(String currentListId) {

        this.currentListId = currentListId;
    }

    public String getCurrentListId() {
        return currentListId;
    }
}
