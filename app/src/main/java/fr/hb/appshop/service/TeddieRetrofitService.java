package fr.hb.appshop.service;


import fr.hb.appshop.models.Product;
import fr.hb.appshop.models.Teddie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface TeddieRetrofitService {
    @GET("teddies")
    Call<List<Teddie>> fetchAllTeddies();
    @GET("teddies/{id}")
    Call<Teddie> fetchTeddieById(@Path("id") Long id);


}
