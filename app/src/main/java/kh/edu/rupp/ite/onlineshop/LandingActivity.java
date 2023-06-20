package kh.edu.rupp.ite.onlineshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;


import kh.edu.rupp.ite.onlineshop.databinding.ActivityLandingBinding;
import kh.edu.rupp.ite.onlineshop.ui.fragment.HomeFragment;
import kh.edu.rupp.ite.onlineshop.ui.fragment.MoreFragment;
import kh.edu.rupp.ite.onlineshop.ui.fragment.ProductFragment;
import kh.edu.rupp.ite.onlineshop.ui.fragment.ProfileFragment;

public class LandingActivity extends AppCompatActivity {

    private ActivityLandingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_landing);
        binding = ActivityLandingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showFragment(new HomeFragment());
//        getSupportActionBar().setTitle(R.string.home_navigation);

        binding.navigationBottom.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.home_navigation) {
                showFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.product_navigation) {
                showFragment(new ProductFragment());
            } else if (item.getItemId() == R.id.profile_navigation) {
                showFragment(new ProfileFragment());
            }else {
                showFragment(new MoreFragment());
            }
            return true;
        });
    }

    private void showFragment(Fragment fragment) {
        // Fragment Manager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Fragment Transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // Replace fragment
        fragmentTransaction.replace(R.id.fragment, fragment);
        // commit transaction
        fragmentTransaction.commit();
    }
}