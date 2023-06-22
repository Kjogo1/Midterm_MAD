package kh.edu.rupp.ite.onlineshop.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import kh.edu.rupp.ite.onlineshop.R;
import kh.edu.rupp.ite.onlineshop.adapter.ProductsAdapter;
import kh.edu.rupp.ite.onlineshop.api.model.ApiService;
import kh.edu.rupp.ite.onlineshop.api.service.Product;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentMoreBinding;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentProductBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {

    private FragmentProductBinding fragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment = FragmentProductBinding.inflate(inflater, container, false);
        return fragment.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadProductListFromServer();
    }

    private void loadProductListFromServer() {
        // create retrofit client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<List<Product>> task = apiService.loadProductList();

        task.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    showProductList(response.body());
                }else {
                    Toast.makeText(getContext(), "Product List Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Product>> call, Throwable t) {
                Toast.makeText(getContext(), "Product List Failed", Toast.LENGTH_SHORT).show();
                Log.e("[ProductFragment]", "load failed" + t.getMessage());
            }
        });
    }

    private void showProductList(List<Product> productList) {
        // layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        fragment.productRecyclerView.setLayoutManager(linearLayoutManager);

        ProductsAdapter adapter = new ProductsAdapter();
        adapter.submitList(productList);
        fragment.productRecyclerView.setAdapter(adapter);
    }

}