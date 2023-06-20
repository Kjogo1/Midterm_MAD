package kh.edu.rupp.ite.onlineshop.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kh.edu.rupp.ite.onlineshop.R;
import kh.edu.rupp.ite.onlineshop.databinding.FragmentMoreBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoreFragment} factory method to
 * create an instance of this fragment.
 */
public class MoreFragment extends Fragment {
    private FragmentMoreBinding fragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment = FragmentMoreBinding.inflate(inflater, container, false);
        return fragment.getRoot();
    }
}