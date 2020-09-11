package com.lymindev.datingx.view.activities.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lymindev.datingx.MainActivity;
import com.lymindev.datingx.R;
import com.lymindev.datingx.databinding.ActivityPhoneLoginBinding;
import com.lymindev.datingx.view.DialogAllCountryCode;

public class PhoneLoginActivity extends AppCompatActivity {

    private ActivityPhoneLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_phone_login);


        initSpinnerCode();
        binding.btnContinuew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    private void initSpinnerCode() {
        binding.spinnerCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DialogAllCountryCode(PhoneLoginActivity.this);
            }
        });
    }
}