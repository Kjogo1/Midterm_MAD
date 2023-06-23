package kh.edu.rupp.ite.onlineshop.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;

import kh.edu.rupp.ite.onlineshop.api.service.Product;
import kh.edu.rupp.ite.onlineshop.databinding.ProductListBinding;

public class ProductsAdapter extends ListAdapter<Product, ProductsAdapter.ProductViewHolder> {

    public ProductsAdapter() {
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
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ProductListBinding binding = ProductListBinding.inflate(layoutInflater, parent, false);
        ProductViewHolder holder = new ProductViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product item = getItem(position);
        holder.bind(item);
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private ProductListBinding listBinding;

        public ProductViewHolder(@NonNull ProductListBinding listBinding) {
            super(listBinding.getRoot());
            this.listBinding = listBinding;
        }

        public void bind(Product item) {
            Picasso.get().load(item.getImageUrl()).into(listBinding.productImage);
            listBinding.productTitle.setText(item.getName());
            listBinding.productPrice.setText(NumberFormat.getCurrencyInstance().format(item.getPrice()));
        }
    }
}
