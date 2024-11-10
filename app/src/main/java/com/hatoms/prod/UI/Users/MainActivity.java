package com.hatoms.prod.UI.Users;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.hatoms.prod.R;
import com.hatoms.prod.bottomnav.main.MainFragment;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingBar = new ProgressDialog(this);

        // Получаем значение result из SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int result = sharedPreferences.getInt("id_uniq", -1); // -1 — значение по умолчанию
        System.out.println(result);
        // Проверяем, было ли сохранено значение result
        if (result != -1) {
            Log.d("MainActivity", "Сохраненный результат найден: " + result);
            // Если значение найдено, перенаправляем на экран MainFragment
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                }
            }, 1000); // Задержка 1 секунда
        } else {
            Log.d("MainActivity", "Сохраненный результат отсутствует, перенаправление на экран регистрации");
            // Если значения нет, перенаправляем на экран регистрации
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(MainActivity.this, activity_register.class);
                    startActivity(i);
                    finish();
                }
            }, 1000); // Задержка 1 секунда
        }
    }
}
