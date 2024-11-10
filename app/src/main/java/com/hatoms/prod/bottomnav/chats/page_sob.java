package com.hatoms.prod.bottomnav.chats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.hatoms.prod.R;
import java.util.ArrayList;
import java.util.List;

public class page_sob extends AppCompatActivity {

    private LinearLayout debtorsListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_sob);
        debtorsListLayout = findViewById(R.id.debtorsList); // LinearLayout из XML

        // Пример данных должников
        List<Debtor> debtors = new ArrayList<>();
        debtors.add(new Debtor("Иван", "500 руб."));
        debtors.add(new Debtor("Мария", "300 руб."));
        debtors.add(new Debtor("Анна", "700 руб."));

        // Отображаем список должников
        displayDebtors(debtors);

        // Настройка Insets для полноэкранного режима
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void displayDebtors(List<Debtor> debtors) {
        // Очищаем список для обновления
        debtorsListLayout.removeAllViews();

        for (Debtor debtor : debtors) {
            // Создаём новый элемент должника из XML
            View debtorItemView = LayoutInflater.from(this).inflate(R.layout.debtor_item, debtorsListLayout, false);

            // Инициализируем поля
            CheckBox checkBox = debtorItemView.findViewById(R.id.checkBox);
            TextView debtorName = debtorItemView.findViewById(R.id.debtorName);
            TextView debtAmount = debtorItemView.findViewById(R.id.debtAmount);

            // Заполняем данные
            debtorName.setText(debtor.getName());
            debtAmount.setText(debtor.getDebt());

            // Добавляем элемент в LinearLayout
            debtorsListLayout.addView(debtorItemView);
        }
    }
}
