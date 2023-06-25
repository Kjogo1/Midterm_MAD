package kh.edu.rupp.ite.onlineshop.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;

import kh.edu.rupp.ite.onlineshop.ProductDetailActivity;
import kh.edu.rupp.ite.onlineshop.api.model.Product;
import kh.edu.rupp.ite.onlineshop.databinding.GridItemListBinding;

public class GridProductsAdapter extends ListAdapter<Product, GridProductsAdapter.ProductViewHolder> {
    public GridProductsAdapter() {
        super(new DiffUtil.ItemCallback<Product>() {
            @Override
            public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                return oldItem.getId() == newItem.getId();
            }
        });

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        GridItemListBinding binding = GridItemListBinding.inflate(inflater, parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product item = getItem(position);
        holder.bind(item);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "Click" +item.getId(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(v.getContext(), ProductDetailActivity.class);
                intent.putExtra("id", item.getId());
                intent.putExtra("description", item.getDescription());
                intent.putExtra("price", item.getPrice());
                intent.putExtra("image", item.getImageUrl());
                intent.putExtra("name", item.getName());
                intent.putExtra("rating", item.getRating());
                v.getContext().startActivity(intent);
            }
        });
    }


    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        private GridItemListBinding binding;
        public ProductViewHolder(@NonNull GridItemListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Product item) {
            Picasso.get().load(item.getImageUrl()).into(binding.productImage);
            binding.productName.setText(item.getName());
            binding.productPrice.setText(NumberFormat.getCurrencyInstance().format(item.getPrice()));
            binding.productRating.setText(item.getRating().toString());
        }
    }
}
