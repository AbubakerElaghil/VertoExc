package vertoexcersice.alaqel.com.vertoexcersice.Respository.Server.Response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by abubakr on 3/14/18.
 */

public class OrderDetails {


    @SerializedName("Name")
    String Name;

    @SerializedName("FromPrice")
    String FromPrice;

    @SerializedName("ToPrice")
    String ToPrice;

    @SerializedName("Quantity")
    String Quantity;

    @SerializedName("ActualPrice")
    String ActualPrice;

  @SerializedName("Price")
  String Price;

  @SerializedName("ImagePath")
  String ImagePath;

    public String getImagePath() {
        return ImagePath;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getActualPrice() {
        return ActualPrice;
    }

    public void setActualPrice(String actualPrice) {
        ActualPrice = actualPrice;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFromPrice() {
        return FromPrice;
    }

    public void setFromPrice(String fromPrice) {
        FromPrice = fromPrice;
    }

    public String getToPrice() {
        return ToPrice;
    }

    public void setToPrice(String toPrice) {
        ToPrice = toPrice;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}
