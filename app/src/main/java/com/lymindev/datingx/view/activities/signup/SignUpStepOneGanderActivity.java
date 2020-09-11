package com.lymindev.datingx.view.activities.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.lymindev.datingx.R;
import com.lymindev.datingx.managers.UsersManager;
import com.lymindev.datingx.view.activities.BaseActivity;
import com.lymindev.datingx.view.activities.login.PhoneLoginActivity;

import hari.bounceview.BounceView;

public class SignUpStepOneGanderActivity extends BaseActivity {

    private ImageButton btnMale, btnFemale;

    public static void launch(Context context, String id) {
        context.startActivity(new Intent(context,SignUpStepOneGanderActivity.class).putExtra("id",id).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step_one_gander);

        btnMale = findViewById(R.id.btn_male);
        btnFemale = findViewById(R.id.btn_female);

        BounceView.addAnimTo(btnMale);
        BounceView.addAnimTo(btnFemale);

        String id = getIntent().getStringExtra("id");
        btnMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new UsersManager().setGander("Male",id);
                SignUpStepThowNameActivity.launch(SignUpStepOneGanderActivity.this,id);
            }
        });

        btnFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new UsersManager().setGander("Female",id);
                SignUpStepThowNameActivity.launch(SignUpStepOneGanderActivity.this,id);
            }
        });
    }
}