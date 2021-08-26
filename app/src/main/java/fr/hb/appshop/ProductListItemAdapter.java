package fr.hb.appshop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    //private Context context;
    private List<Teddie> data;
    private List<Camera> data2;
    private WeakReference<OnItemClickListener> listener;
    //private int recyclerItemRes;
    //private Context context;
    //private Product product;


    //Constructeur par defaut
    public ProductListItemAdapter() {
    }

    //Constructeur 2

//    public ProductListItemAdapter(List<Teddie> teddiesList) {
//        this.data = teddiesList;
//    }

    public ProductListItemAdapter(List<Teddie> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = new WeakReference<>(listener);
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        public TextView mainTvName, mainTvPrice,mainTvDescription;
        public ImageView mainIvImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mainTvName = itemView.findViewById(R.id.main_tv_name);
            mainTvPrice = itemView.findViewById(R.id.main_tv_price);
            mainIvImage = itemView.findViewById(R.id.main_iv_image);
            mainTvDescription = itemView.findViewById(R.id.main_tv_description);
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
        Product product = data.get(position);

        if (product instanceof Teddie){

            if (product.getImageUrl() != null) {
                Glide.with(holder.mainIvImage).load(( (Teddie) product).getImageUrl()).into(holder.mainIvImage);
            }
            holder.mainTvName.setText(( (Teddie) product).getName());
            holder.mainTvPrice.setText(String.valueOf(( (Teddie) product).getPrice()));
            holder.mainTvDescription.setText(( (Teddie) product).getDescription());

        }else if(product instanceof Camera){

            if (product.getImageUrl() != null) {
                Glide.with(holder.mainIvImage).load(( (Camera) product).getImageUrl()).into(holder.mainIvImage);
            }
            holder.mainTvName.setText(( (Camera) product).getName());
            holder.mainTvPrice.setText(String.valueOf(( (Camera) product).getPrice()));

        } else if (product instanceof Furniture) {

            //TODO:

        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
                if (listener.get() != null){
                    listener.get().onClick(product);
                    Log.d("Tag",product.toString() );
                }
           }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnItemClickListener {

        void onClick(Product productClicked);
    }


}
