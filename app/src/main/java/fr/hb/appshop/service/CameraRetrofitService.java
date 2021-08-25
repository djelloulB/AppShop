package fr.hb.appshop.service;

import java.util.List;

import fr.hb.appshop.models.Camera;
import fr.hb.appshop.models.Teddie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CameraRetrofitService {

    @GET("cameras")
    Call<List<Camera>> fetchAllCameras();
    @GET("cameras/{id}")
    Call<Camera> fetchCameraById(@Path("id") Long id);
}
