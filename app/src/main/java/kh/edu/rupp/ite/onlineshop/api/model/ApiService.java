package kh.edu.rupp.ite.onlineshop.api.model;

import java.util.List;

import kh.edu.rupp.ite.onlineshop.api.service.Product;
import kh.edu.rupp.ite.onlineshop.api.service.Profile;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("kimsongsao/ferupp/main/products.json")
    Call<List<Product>> loadProductList();

    @GET("kimsongsao/ferupp/main/profile.json")
    Call<Profile> loadProfileList();
}
