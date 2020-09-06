package com.example.emailapplication.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.emailapplication.R;
import com.example.emailapplication.entity.Message;
import com.example.emailapplication.helpEntities.Setting;
import com.example.emailapplication.viewmodels.FoldersViewModel;
import com.example.emailapplication.viewmodels.SettingsViewModel;

import java.util.List;

public class SettingsFragment extends Fragment {
    SettingsViewModel settingsViewModel;
    Long accountId;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel.class);
        SharedPreferences prefs = this.getActivity().getSharedPreferences("currentUser", Context.MODE_PRIVATE);

        accountId = prefs.getLong("userId", 0);
        Setting defaultSetting = settingsViewModel.getSettings(accountId);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        final TextView refreshTime = root.findViewById(R.id.refreshTime);
        final Button applyButton = root.findViewById(R.id.applySettings);
        final CheckBox checkAscDsc = root.findViewById(R.id.orderAscendingDescending);
        refreshTime.setText(Float.toString(defaultSetting.refreshTime));
        checkAscDsc.setChecked(true);
        return root;
    }
}
