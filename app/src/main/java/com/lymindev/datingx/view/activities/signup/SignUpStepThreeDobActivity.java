package com.lymindev.datingx.view.activities.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lymindev.datingx.R;
import com.lymindev.datingx.view.activities.BaseActivity;

public class SignUpStepThreeDobActivity extends BaseActivity {

    public static void launch(Context context, String id) {
        context.startActivity(new Intent(context,SignUpStepThreeDobActivity.class).putExtra("id",id));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step_three_dob);
        String id = getIntent().getStringExtra("id");
        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpStepFourProfileActivity.launch(SignUpStepThreeDobActivity.this,id);
            }
        });
    }
}