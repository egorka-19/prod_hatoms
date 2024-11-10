package com.hatoms.prod.UI.Users;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.hatoms.prod.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login extends AppCompatActivity {
    private static final String TAG = "login";
    private EditText mailEditText;
    private EditText passwordEditText;
    private ImageButton loginButton, register; // Добавим кнопку для запуска авторизации

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Инициализация EditText для mail и password
        mailEditText = findViewById(R.id.mail);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.sign_button); // Предположим, что в XML есть кнопка login_button
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, activity_register.class);
                startActivity(intent);
            }
        });
        // Инициализация Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://89.169.160.159:8080") // Укажите базовый URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Создайте экземпляр ApiService
        ApiService apiService = retrofit.create(ApiService.class);

        // Устанавливаем слушатель для кнопки логина
        loginButton.setOnClickListener(v -> {
            // Получаем введенные пользователем email и пароль
            String email = mailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            // Создайте объект LoginRequest с email и password из EditText
            LoginRequest loginRequest = new LoginRequest(email, password);

            // Выполните запрос на сервер
            Call<Integer> call = apiService.login(loginRequest); // Изменено на Call<Integer>
            call.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        int result = response.body(); // Получаем целое число из ответа
                        Log.d(TAG, "Ответ от сервера: " + result);
                        Intent intent = new Intent(login.this, HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Log.e(TAG, "Ошибка: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    Log.e(TAG, "Ошибка запроса: " + t.getMessage());
                }
            });
        });
    }
}
