package vertoexcersice.alaqel.com.vertoexcersice.Respository.Server.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Abubaker Al-aqel on 3/20/2017.
 */

public class MultipleObjectHeader {

    @SerializedName("Id")
    String Id;

    @SerializedName("Name")
    String Name;

    @SerializedName("MobileNumber")
    String MobileNumber;

   @SerializedName("EmployeeTypeId")
   String EmployeeTypeId;

    @SerializedName("Latitude")
    String Latitude;

    @SerializedName("Longitude")
    String Longitude;

    @SerializedName("UserLatitude")
    String UserLatitude;

    @SerializedName("UserLongitude")
    String UserLongitude;

    @SerializedName("Value")
    String Value;

    @SerializedName("ImagePath")
    String ImagePath;

    @SerializedName("HasProductType")
    String HasProductType;


    @SerializedName("FromPrice")
    String FromPrice;

    @SerializedName("MedicineType")
    String MedicineType;

    @SerializedName("ToPrice")
    String ToPrice;

    @SerializedName("OrderDetails")
    List<OrderDetails> orderDetails;


    @SerializedName("CurrencySymbol")
    String CurrencySymbol;

    @SerializedName("CurrencyId")
    String CurrencyId;

    @SerializedName("Date")
    String Date;
  @SerializedName("UserAddressDetails")
  UserAddressDetails  userAddressDetails;

    @SerializedName("Description")
    String Description;
    @SerializedName("MeasurementUnitId")
    String MeasurementUnitId;
    @SerializedName("MeasurementUnitSymbol")
    String MeasurementUnitSymbol;
    @SerializedName("ProductBrandId")
    String ProductBrandId;
    @SerializedName("ProductSpecificationTypeId")
    String ProductSpecificationTypeId;

    @SerializedName("OrderStatusTypeId")
    String OrderStatusTypeId;


    @SerializedName("Delivery")
    String Delivery;


    @SerializedName("TotalDiscounts")
    String TotalDiscounts;


    @SerializedName("TotalOldlPrice")
    String TotalOldlPrice;

    @SerializedName("TotalTaxes")
    String TotalTaxes;


    @SerializedName("IsMyFavorite")
    String IsMyFavorite;


    @SerializedName("TotalPrice")
    String TotalPrice;


    @SerializedName("UserDetails")
    UserDetails userDetails;

    public String getEmployeeTypeId() {
        return EmployeeTypeId;
    }

    public UserAddressDetails getUserAddressDetails() {
        return userAddressDetails;
    }

    public String getUserLatitude() {
        return UserLatitude;
    }

    public String getUserLongitude() {
        return UserLongitude;
    }

    public UserDetails getUserDetails() {
        if(userDetails ==null)
            return  new UserDetails();
        return userDetails;
    }

    Boolean IsLastLevel=false;

    public Boolean getLastLevel() {
        return IsLastLevel;
    }

    public void setLastLevel(Boolean lastLevel) {
        IsLastLevel = lastLevel;
    }

    public String getLatitude() {
        return Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public String getValue() {
        return Value;
    }

    public String getIsMyFavorite() {
        return IsMyFavorite;
    }

    public void setIsMyFavorite(String isMyFavorite) {
        IsMyFavorite = isMyFavorite;
    }

    public String getDelivery() {
        return Delivery;
    }

    public String getTotalDiscounts() {
        return TotalDiscounts;
    }

    public String getTotalOldlPrice() {
        return TotalOldlPrice;
    }

    public String getTotalTaxes() {
        return TotalTaxes;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFromPrice() {
        return FromPrice;
    }

    public String getToPrice() {
        return ToPrice;
    }

    public String getMedicineType() {
        return MedicineType;
    }

    public String getOrderStatusTypeId() {
        return OrderStatusTypeId;
    }

    public String getTotalPrice() {
        return TotalPrice;
    }

    public String getCurrencySymbol() {
        return CurrencySymbol;
    }

    public String getCurrencyId() {
        return CurrencyId;
    }

    public String getDate() {
        return Date;
    }

    public String getDescription() {
        return Description;
    }

    public String getMeasurementUnitId() {
        return MeasurementUnitId;
    }

    public String getMeasurementUnitSymbol() {
        return MeasurementUnitSymbol;
    }

    public String getProductBrandId() {
        return ProductBrandId;
    }

    public String getProductSpecificationTypeId() {
        return ProductSpecificationTypeId;
    }

    Boolean IsSelected = false;

    public Boolean getSelected() {
        return IsSelected;
    }

    public void setSelected(Boolean selected) {
        IsSelected = selected;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public String getHasProductType() {
        return HasProductType;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }
}
