package fr.hb.appshop.util;

import fr.hb.appshop.service.TeddieRetrofitService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApi {
    private static final String BASE_URL = "http://10.0.2.2:3000/api/";
    private static TeddieRetrofitService teddieRetrofitService;

    public static TeddieRetrofitService getInstance() {
        if (teddieRetrofitService == null) {
            synchronized (TeddieRetrofitService.class) {
                createApiBuilder();
            }
        }
        return teddieRetrofitService;
    }

    private static void createApiBuilder() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        teddieRetrofitService = retrofit.create(TeddieRetrofitService.class);
    }

}
