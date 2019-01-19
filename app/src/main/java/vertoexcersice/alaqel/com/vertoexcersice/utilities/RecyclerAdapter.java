package vertoexcersice.alaqel.com.vertoexcersice.utilities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ultimate.pharmazadadmin.R;


/**
 * Created by User on 10/16/2017.
 */

public abstract class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static final int ORDERS =9;
    public static final int ORDERS_LIST =10;
    public static final int DRIVERS =11;


    private Context context;
     int ItemsCount;
     String CategoryID;
    String CatType;
    public RecyclerAdapter(Context context , int ItemsCount ) {
        this.context = context;
         this.ItemsCount = ItemsCount;
     }

     public RecyclerAdapter(Context context , int ItemsCount , String CatType) {
        this.context = context;
         this.ItemsCount = ItemsCount;
         this.CatType = CatType;
     }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView;
        RecyclerView.ViewHolder rcv;

        switch (viewType) {


                case ORDERS:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
                rcv = new RecyclerViewHolders.OrderViewHolder(layoutView);
                return rcv;
                case ORDERS_LIST:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_details_item, parent, false);
                rcv = new RecyclerViewHolders.OrderDetailsViewHolder(layoutView);
                return rcv;
               case DRIVERS:
                layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_item, parent, false);
                rcv = new RecyclerViewHolders.DriverViewHolder(layoutView);
                return rcv;


        }

        return null;

    }


    @Override
    public abstract void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position);

    @Override
    public abstract int getItemCount();

    public void updateItemCount(int count) {
        ItemsCount = count;
    }


}

