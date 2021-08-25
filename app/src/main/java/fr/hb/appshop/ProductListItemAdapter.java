package fr.hb.appshop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import fr.hb.appshop.models.Camera;
import fr.hb.appshop.models.Furniture;
import fr.hb.appshop.models.Product;
import fr.hb.appshop.models.Teddie;

public class ProductListItemAdapter extends RecyclerView.Adapter<ProductListItemAdapter.ViewHolder> {
    private Context context;
    private List<Teddie> data;
    private WeakReference<OnItemClickListener> listener;
    //private int recyclerItemRes;
    //private Context context;
    //private Product product;





//    public ProductListItemAdapter(Context context,List<Teddie> productList, int product_item) {
//        this.context = context;
//        this.data = productList;
//    }

    public ProductListItemAdapter(List<Teddie> productList) {
        this.data = productList;
        //this.listener = new WeakReference<>(listener);
    }




    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView mainTvName, mainTvPrice;
        public ImageView mainIvImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mainTvName = itemView.findViewById(R.id.main_tv_name);
            mainTvPrice = itemView.findViewById(R.id.main_tv_price);
            mainIvImage = itemView.findViewById(R.id.main_iv_image);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Teddie teddie = data.get(position);


        if (teddie.getImageUrl() != null) {
            Glide.with(holder.mainIvImage).load(teddie.getImageUrl()).into(holder.mainIvImage);
        }
        holder.mainTvName.setText(teddie.getName());
        holder.mainTvPrice.setText(String.valueOf(teddie.getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener.get() != null){
                    listener.get().onClick(teddie);
                }
            }
        });

//        if (product instanceof Teddie){
//            //TODO:
//        }else if(product instanceof Camera){
//            //TODO:
//        } else if (product instanceof Furniture) {
//            //TODO:
//        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListener {

        void onClick(Product productClicked);
    }


}
