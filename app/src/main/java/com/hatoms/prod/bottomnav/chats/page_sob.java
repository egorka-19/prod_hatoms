package com.hatoms.prod.bottomnav.chats;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hatoms.prod.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hatoms.prod.UI.Users.HomeActivity;

import java.util.List;

public class page_sob extends AppCompatActivity {

    private LinearLayout debtorsListLayout;
    private TextView eventNameTextView;  // TextView для отображения названия события
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_sob);

        debtorsListLayout = findViewById(R.id.debtorsList); // LinearLayout из XML
        eventNameTextView = findViewById(R.id.name); // TextView для отображения названия события
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(page_sob.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        // Извлечение данных из SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("accountData", MODE_PRIVATE);
        String accountName = sharedPreferences.getString("accountName", "Неизвестное событие");
        String membersJson = sharedPreferences.getString("all_members", "");

        // Устанавливаем значение в TextView
        eventNameTextView.setText(accountName);

        if (!membersJson.isEmpty()) {
            // Преобразуем JSON в список участников
            Gson gson = new Gson();
            List<Member> membersList = gson.fromJson(membersJson, new TypeToken<List<Member>>() {}.getType());

            // Отображаем участников
            displayDebtors(membersList);
        } else {
            Toast.makeText(this, "Нет участников для отображения", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayDebtors(List<Member> members) {
        // Очищаем список для обновления
        debtorsListLayout.removeAllViews();

        for (Member member : members) {
            // Создаём новый элемент для отображения участника
            View debtorItemView = LayoutInflater.from(this).inflate(R.layout.debtor_item, debtorsListLayout, false);

            // Инициализируем поля
            CheckBox checkBox = debtorItemView.findViewById(R.id.checkBox);
            TextView debtorName = debtorItemView.findViewById(R.id.debtorName);
            TextView debtAmount = debtorItemView.findViewById(R.id.debtAmount);

            // Заполняем данные
            debtorName.setText(member.getName());
            debtAmount.setText(String.format("%s руб.", member.getDebt()));

            // Добавляем элемент в LinearLayout
            debtorsListLayout.addView(debtorItemView);
        }
    }
}
