package com.hatoms.prod.bottomnav.chats;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hatoms.prod.databinding.ActivityAddCostBinding;

public class add_cost extends AppCompatActivity {

    private ActivityAddCostBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Получение данных из SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("accountData", MODE_PRIVATE);
        String accountName = sharedPreferences.getString("accountName", "");
        String accountCoast = sharedPreferences.getString("accountCoast", "");

        // Установка данных в поля
        binding.nameCost.setText(accountName);
    }
}
