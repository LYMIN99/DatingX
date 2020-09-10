package com.lymindev.datingx.activities.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;

import com.lymindev.datingx.R;
import com.lymindev.datingx.databinding.ActivityUserProfileBinding;

public class UserProfileActivity extends AppCompatActivity {

    private ActivityUserProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user_profile);
    }
}