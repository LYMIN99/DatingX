package com.lymindev.datingx.view.activities.setings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;

import com.crystal.crystalrangeseekbar.interfaces.OnRangeSeekbarChangeListener;
import com.lymindev.datingx.R;
import com.lymindev.datingx.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_settings);

        binding.seekbarAge.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                binding.min.setText(minValue+"");
                binding.max.setText(maxValue+"");
            }
        });
        binding.seekbarDistance.setOnRangeSeekbarChangeListener(new OnRangeSeekbarChangeListener() {
            @Override
            public void valueChanged(Number minValue, Number maxValue) {
                binding.mind.setText(minValue+" KM");
                binding.maxd.setText(maxValue+" KM");
            }
        });
    }
}