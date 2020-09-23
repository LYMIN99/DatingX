package com.lymindev.datingx.view.activities.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.lymindev.datingx.R;
import com.lymindev.datingx.constance.Constants;
import com.lymindev.datingx.databinding.ActivityEditInfoBinding;
import com.lymindev.datingx.managers.UsersManager;
import com.lymindev.datingx.model.user.UsersRealm;
import com.lymindev.datingx.tools.SharePreferenceX;

public class EditInfoActivity extends AppCompatActivity {

    private ActivityEditInfoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_info);

        getInfo();
    }

    private void getInfo() {
        UsersRealm user = new UsersManager().getUserInfo(new SharePreferenceX(this).getPrefernces(Constants.SharePref.currentUserID));
        binding.tvUsername.setText(user.getName());
        binding.tvAddress.setText(user.getAddress());
        binding.tvAge.setText(user.getAge());
        binding.tvEmail.setText(user.getEmail());
        binding.tvGander.setText(user.getGander());
        binding.edAbout.setText(user.getAbout());
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}