package com.hatoms.prod.bottomnav.chats;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hatoms.prod.bottomnav.main.MainFragment;
import com.hatoms.prod.databinding.FragmentChatsBinding;

public class ChatsFragment extends Fragment {

    private FragmentChatsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChatsBinding.inflate(inflater, container, false);


        binding.addMember.setOnClickListener(v -> add_member());

        binding.continueButton.setOnClickListener(v -> addParticipant());

        binding.saveButton.setOnClickListener(v -> saveData());

        return binding.getRoot();
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

        EditText newMember = new EditText(getContext());
        newMember.setText(String.format("%s: %s руб.", nameDolg, dolg));
        newMember.setEnabled(false);

        binding.memberList.addView(newMember);

        binding.nameDolg.setText("");
        binding.dolg.setText("");
        binding.member.setVisibility(View.GONE);
    }

    private void saveData() {
        String accountName = binding.nameCoast.getText().toString();
        String accountCoast = binding.fullCoast.getText().toString();

        if (accountName.isEmpty() || accountCoast.isEmpty()) {
            Toast.makeText(getContext(), "Пожалуйста, заполните все поля счета", Toast.LENGTH_SHORT).show();
        } else {
            // Сохранение данных в SharedPreferences
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("accountData", getContext().MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("accountName", accountName);
            editor.putString("accountCoast", accountCoast);
            editor.apply();

            Toast.makeText(getContext(), "Сохранено!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(requireActivity(), add_cost.class);
            startActivity(intent);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
