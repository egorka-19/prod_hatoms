package com.hatoms.prod.UI.Users;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.hatoms.prod.R;
import com.hatoms.prod.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_register extends AppCompatActivity {
    public EditText login;
    public EditText mail;
    public EditText password;
    public ImageButton sign_button, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Найдем элементы на экране
        login = findViewById(R.id.login);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        sign_button = findViewById(R.id.sign_button);
        register = findViewById(R.id.register);

        // Устанавливаем слушатель для кнопки регистрации
        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = login.getText().toString();
                String email = mail.getText().toString();
                String passwordText = password.getText().toString();

                if (!name.isEmpty() && !email.isEmpty() && !passwordText.isEmpty()) {
                    registerNewUser(name, email, passwordText);
                } else {
                    Toast.makeText(activity_register.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_register.this, login.class);
                startActivity(intent);
            }
        });
    }

    private void registerNewUser(String name, String email, String password) {
        Register request = new Register(name, email, password);
        Call<Integer> call = RetrofitClient.getApiService().registerUser(request);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    int result = response.body();
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity_register.this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("id_uniq", result);
                    editor.apply();

                    // Выводим результат в лог для отладки
                    Log.d("RegisterResponse", "Result from server: " + result);

                    // Показать успешное сообщение и перейти на экран входа
                    Toast.makeText(activity_register.this, "Регистрация успешна, код: " + result, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity_register.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    // Обработка ошибки при регистрации
                    Log.e("RegisterError", "Ошибка регистрации: " + response.code());
                    Toast.makeText(activity_register.this, "Ошибка регистрации: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                // Обработка ошибки при отсутствии ответа
                Log.e("NetworkError", "Ошибка сети: " + t.getMessage());
                Toast.makeText(activity_register.this, "Ошибка сети: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
