package kh.edu.rupp.ite.onlineshop.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Locale;

import kh.edu.rupp.ite.onlineshop.api.service.ApiService;
import kh.edu.rupp.ite.onlineshop.api.model.Profile;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentProfileBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private FragmentProfileBinding fragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment = FragmentProfileBinding.inflate(inflater, container, false);
        return fragment.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadProfileFromServer();
    }

    private void loadProfileFromServer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ApiService apiService = retrofit.create(ApiService.class);

        Call<Profile> task = apiService.loadProfileList();

        task.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(@NonNull Call<Profile> call, @NonNull Response<Profile> response) {
                if (response.isSuccessful()) {
                    showProfile(response.body());
                } else {
                    Toast.makeText(getContext(), "Profile went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Profile> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Profile went wrong", Toast.LENGTH_SHORT).show();
                Log.e("[ProfileFragment]", "load failed" + t.getMessage());
            }
        });
    }

    private void showProfile(Profile profiles) {
//        String username = profiles.getFirstName() + " " + profiles.getLastName();
        Picasso.get().load(profiles.getImageUrl()).into(fragment.profilePicture);
        fragment.username.setText(profiles.getFirstName() + " " + profiles.getLastName());
        fragment.userEmail.setText(profiles.getEmail());
        fragment.email.setText(profiles.getEmail());
        fragment.phoneNumber.setText(profiles.getPhoneNumber());
        fragment.gender.setText(profiles.getGender());
        String date = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(profiles.getBirthday());
        fragment.birthday.setText(date);
        fragment.address.setText(profiles.getAddress());
//        fragment.address.setText(profiles.getAddress());
    }
}