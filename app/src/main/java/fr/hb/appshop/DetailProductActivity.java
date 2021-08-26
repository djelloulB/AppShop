package fr.hb.appshop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import fr.hb.appshop.models.Product;
import fr.hb.appshop.models.Teddie;
import fr.hb.appshop.service.TeddieRetrofitService;
import fr.hb.appshop.util.RetrofitApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView tvDetailName = findViewById(R.id.tv_detail_name);


        Intent intent = getIntent();
        if (intent.hasExtra(MainActivity.KEY_BUNDLE_PRODUCT_ID)) {
            //Product product = intent.getParcelableExtra(MainActivity.KEY_BUNDLE_PRODUCT_ID);
            Long productId = intent.getLongExtra(MainActivity.KEY_BUNDLE_PRODUCT_ID,0);
            TeddieRetrofitService teddieService = RetrofitApi.getInstance();
            Call<Teddie> call = teddieService.fetchTeddieById(productId);
            //Log.d("tag",product.toString());

            call.enqueue(new Callback<Teddie>(){

                @Override
                public void onResponse(Call<Teddie> call, Response<Teddie> response) {
                    Log.d("tag",response.toString());
                    Teddie teddie = response.body();
                    tvDetailName.setText(teddie.getName());
                }

                @Override
                public void onFailure(Call<Teddie> call, Throwable t) {
                    Snackbar.make(findViewById(android.R.id.content), "Une erreur est survenue", Snackbar.LENGTH_SHORT).show();
                }
            });

        }
    }
}