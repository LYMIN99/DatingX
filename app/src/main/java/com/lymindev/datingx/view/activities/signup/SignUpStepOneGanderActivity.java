package com.lymindev.datingx.view.activities.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.lymindev.datingx.R;

import hari.bounceview.BounceView;

public class SignUpStepOneGanderActivity extends AppCompatActivity {

    private ImageButton btnMale, btnFemale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step_one_gander);

        btnMale = findViewById(R.id.btn_male);
        btnFemale = findViewById(R.id.btn_female);

        BounceView.addAnimTo(btnMale);
        BounceView.addAnimTo(btnFemale);

        btnMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUpStepThowNameActivity.class));
            }
        });

        btnFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUpStepThowNameActivity.class));
            }
        });
    }
}