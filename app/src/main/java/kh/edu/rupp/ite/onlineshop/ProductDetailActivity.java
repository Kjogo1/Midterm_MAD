package kh.edu.rupp.ite.onlineshop;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;

import kh.edu.rupp.ite.onlineshop.databinding.ActivityProductDetailBinding;

public class ProductDetailActivity extends AppCompatActivity {

    private ActivityProductDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_product_detail);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        Integer id = getIntent().getIntExtra("id", 0);
//        binding.productName.setText(id.toString());

        binding.productName.setText(getIntent().getStringExtra("name"));
        String description = getIntent().getStringExtra("description");
        binding.productDescription.setText(description);
        binding.productPrice.setText(NumberFormat
                .getCurrencyInstance()
                .format(getIntent().getDoubleExtra("price", 0)));

        double rating = getIntent().getDoubleExtra("rating", 0.0);
        binding.productRating.setText(Double.toString(rating));
        Picasso.get().load(getIntent().getStringExtra("image")).into(binding.productImage);


    }
}