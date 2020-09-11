package com.lymindev.datingx.view.activities.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.lymindev.datingx.R;
import com.lymindev.datingx.managers.UsersManager;
import com.lymindev.datingx.view.activities.BaseActivity;

public class SignUpStepThowNameActivity extends BaseActivity {

    public static void launch(Context context, String id) {
        context.startActivity(new Intent(context,SignUpStepThowNameActivity.class).putExtra("id",id));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step_thow_name);

        String id = getIntent().getStringExtra("id");
        EditText edName = findViewById(R.id.ed_name);
        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edName.getText().toString())){
                    edName.setError("Please input any name");
                    return;
                }

                new UsersManager().setName(edName.getText().toString().trim(),id);
                SignUpStepThreeDobActivity.launch(SignUpStepThowNameActivity.this,id);
            }
        });
    }
}