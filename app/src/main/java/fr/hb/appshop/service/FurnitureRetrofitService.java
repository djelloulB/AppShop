package fr.hb.appshop.service;

import java.util.List;

import fr.hb.appshop.models.Furniture;
import fr.hb.appshop.models.Teddie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FurnitureRetrofitService {
    @GET("furnitures")
    Call<List<Furniture>> fetchAllFurnitures();
    @GET("furnitures/{id}")
    Call<Furniture> fetchFurnitureById(@Path("id") Long id);
}
