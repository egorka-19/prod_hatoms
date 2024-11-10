package com.hatoms.prod.bottomnav.chats;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hatoms.prod.R;
import com.hatoms.prod.databinding.FragmentChatsBinding;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class ChatsFragment extends Fragment {

    private FragmentChatsBinding binding;
    private List<Member> membersList = new ArrayList<>(); // Список для хранения участников

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChatsBinding.inflate(inflater, container, false);
        CheckBox oneTimeCheckBox = binding.oneTimeCheckBox;

        setupCheckboxListeners();

        binding.addMember.setOnClickListener(v -> add_member());
        binding.continueButton.setOnClickListener(v -> addParticipant());
        binding.saveButton.setOnClickListener(v -> saveData(oneTimeCheckBox.isChecked()));


        return binding.getRoot();
    }

    private void setupCheckboxListeners() {
        CheckBox oneTimeCheckBox = binding.oneTimeCheckBox;
        CheckBox complexCheckBox = binding.complexCheckBox;

        oneTimeCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) complexCheckBox.setChecked(false);
        });

        complexCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) oneTimeCheckBox.setChecked(false);
        });
    }

    private void add_member() {
        if (binding.member.getVisibility() == View.GONE) {
            binding.member.setVisibility(View.VISIBLE);
        } else {
            binding.member.setVisibility(View.GONE);
        }
    }

    private void addParticipant() {
        String nameDolg = binding.nameDolg.getText().toString();
        String dolg = binding.dolg.getText().toString();

        if (!TextUtils.isEmpty(nameDolg) && !TextUtils.isEmpty(dolg)) {
            // Добавление нового участника в список
            membersList.add(new Member(nameDolg, dolg));

            // Отображение участника в списке
            LinearLayout newMemberLayout = new LinearLayout(getContext());
            newMemberLayout.setOrientation(LinearLayout.HORIZONTAL);
            newMemberLayout.setBackgroundResource(R.drawable.rounded_background);
            newMemberLayout.setPadding(16, 16, 16, 16);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 160);
            layoutParams.setMargins(0, 8, 0, 8);
            newMemberLayout.setLayoutParams(layoutParams);

            TextView nameTextView = new TextView(getContext());
            nameTextView.setText(nameDolg);
            nameTextView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f));
            nameTextView.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
            nameTextView.setTextSize(18);
            nameTextView.setTextColor(Color.BLACK);

            TextView dolgTextView = new TextView(getContext());
            dolgTextView.setText(String.format("%s руб.", dolg));
            dolgTextView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT));
            dolgTextView.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
            dolgTextView.setTextSize(18);
            dolgTextView.setTextColor(Color.BLACK);

            newMemberLayout.addView(nameTextView);
            newMemberLayout.addView(dolgTextView);

            binding.memberList.addView(newMemberLayout);

            binding.nameDolg.setText("");
            binding.dolg.setText("");
            binding.member.setVisibility(View.GONE);
        }
    }

    private void saveData(boolean odnor) {
        String accountName = binding.nameCoast.getText().toString();
        String accountCoast = binding.fullCoast.getText().toString();

        if (accountName.isEmpty() || accountCoast.isEmpty()) {
            Toast.makeText(getContext(), "Пожалуйста, заполните все поля счета", Toast.LENGTH_SHORT).show();
        } else {
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("accountData", getContext().MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("accountName", accountName);
            editor.putString("accountCoast", accountCoast);

            // Сохранение списка участников с помощью Gson
            Gson gson = new Gson();
            String membersJson = gson.toJson(membersList);
            editor.putString("membersList", membersJson);
            editor.apply();
            Toast.makeText(getContext(), "Сохранено!", Toast.LENGTH_SHORT).show();

            if (odnor) {
                Intent intent = new Intent(requireActivity(), page_sob.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(requireActivity(), add_cost.class);
                startActivity(intent);
            }
        }
    }

    private void logMembersJson() {
        Gson gson = new Gson();
        String membersJson = gson.toJson(membersList); // Преобразуем список участников в JSON
        Log.d("MembersJSON", membersJson); // Выводим JSON в Logcat для отладки
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
