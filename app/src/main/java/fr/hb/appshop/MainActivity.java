package fr.hb.appshop;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import fr.hb.appshop.models.Product;
import fr.hb.appshop.models.Teddie;
import fr.hb.appshop.service.TeddieRetrofitService;
import fr.hb.appshop.util.RetrofitApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  {


    public static final String KEY_BUNDLE_PRODUCT_ID = "KEY_BUNDLE_PRODUCT_ID";

    private TeddieRetrofitService teddiesService = RetrofitApi.getInstance();

    private RecyclerView rv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initToolbar();

        //Elements graphiques
        rv = findViewById(R.id.main_product_list);



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);


        ProductListItemAdapter.OnItemClickListener clickListener = new ProductListItemAdapter.OnItemClickListener() {


            @Override
            public void onClick(Product productClicked) {
                Intent intent = new Intent(MainActivity.this, DetailProductActivity.class);
                intent.putExtra(KEY_BUNDLE_PRODUCT_ID, productClicked.get_id());
                startActivity(intent);
            }

        };

//////////////////////// Retrofit Activity /////////////////////////////

        Call<List<Teddie>> call = teddiesService.fetchAllTeddies();

        call.enqueue(new Callback<List<Teddie>>() {

            @Override
            public void onResponse(Call<List<Teddie>> call, Response<List<Teddie>> response) {

                // Create and using adapter Context context,List<Teddie> productList, int product_item
                ProductListItemAdapter productRvAdapter = new ProductListItemAdapter(response.body(), clickListener );
                rv.setAdapter(productRvAdapter);
            }


            @Override
            public void onFailure(Call<List<Teddie>> call, Throwable t) {

                Snackbar.make(findViewById(android.R.id.content), "Une erreur est survenue", Snackbar.LENGTH_SHORT).show();
            }
        });



    }
//    public interface OnItemClickListener {
//        void onClick(Product productClicked);
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.furniture_link:
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    private void initToolbar() {
        //Action Bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {

            actionBar.setDisplayShowCustomEnabled(false);
        }
    }
}