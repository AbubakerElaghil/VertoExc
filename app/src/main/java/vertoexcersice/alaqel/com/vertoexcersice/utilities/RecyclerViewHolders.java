package vertoexcersice.alaqel.com.vertoexcersice.utilities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ultimate.pharmazadadmin.EventObject.DriverSelectedEvent;
import com.ultimate.pharmazadadmin.EventObject.OrderDetailsEvent;
import com.ultimate.pharmazadadmin.EventObject.SelectDriverEvent;
import com.ultimate.pharmazadadmin.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RecyclerViewHolders {
    public static class OrderDetailsViewHolder extends RecyclerView.ViewHolder {

        public ImageView ImgMedicine ;
        public TextView txtMedicineName;
        public TextView txtMedicineFromPrice;
        public TextView txtMedicineToPrice;
        public TextView txtDashSeprator;

         public OrderDetailsViewHolder(final View itemView) {
            super(itemView);
             ButterKnife.bind(this,itemView);

             ImgMedicine = (ImageView) itemView.findViewById(R.id.img_medicine);

            txtMedicineName = (TextView) itemView.findViewById(R.id.txt_medicine_name);
            txtDashSeprator = (TextView) itemView.findViewById(R.id.dash_seprator);
            txtMedicineFromPrice = (TextView) itemView.findViewById(R.id.txt_from_price);
            txtMedicineToPrice = (TextView) itemView.findViewById(R.id.txt_to_price);

        }
    }
    public static class DriverViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.txt_driver_name)
        public TextView txtDriverName;

        @BindView(R.id.txt_driver_number)
        public TextView txtDriverNumber;

        @BindView(R.id.ic_check)
        public ImageView ImgCheck;


         public DriverViewHolder(final View itemView) {
            super(itemView);
             ButterKnife.bind(this,itemView);

             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     EventBus.getDefault().post(new DriverSelectedEvent(getAdapterPosition()));
                 }
             });

        }
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_order_id)
     public TextView txtOrderId;

        @BindView(R.id.txt_date_time)
        public TextView txtOrderDate;

        @BindView(R.id.txt_order_status)
        public TextView txtOrderStatus;

        @BindView(R.id.txt_order_price)
        public TextView txtOrderPrice;

        @BindView(R.id.txt_address)
        public TextView txtOrderAddress;

        public View itemView;

         @BindView(R.id.btn_select_driver)
       public TextView BtnSelectDriver;


         @OnClick(R.id.btn_select_driver)
         void onClickDriver(){
            EventBus.getDefault().post(new SelectDriverEvent(getAdapterPosition()));
         }

         public OrderViewHolder(final View itemView) {
            super(itemView);
             ButterKnife.bind(this,itemView);

             this.itemView= itemView;

             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                EventBus.getDefault().post(new OrderDetailsEvent( getAdapterPosition()));
                 }
             });

        }

    }


}
