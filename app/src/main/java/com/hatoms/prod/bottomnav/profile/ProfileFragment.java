package com.hatoms.prod.bottomnav.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hatoms.prod.UI.Users.activity_register;
import com.hatoms.prod.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ProfileFragment
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("id_uniq", -1);
                editor.apply();


                // Переход на экран регистрации
                Intent intent = new Intent(requireActivity(), activity_register.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }
}
