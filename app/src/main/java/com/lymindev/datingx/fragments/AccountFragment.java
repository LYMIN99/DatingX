package com.lymindev.datingx.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.lymindev.datingx.R;
import com.lymindev.datingx.constance.Constants;
import com.lymindev.datingx.managers.UsersManager;
import com.lymindev.datingx.model.user.UsersRealm;
import com.lymindev.datingx.tools.SharePreferenceX;
import com.lymindev.datingx.view.activities.setings.SettingsActivity;
import com.lymindev.datingx.view.activities.user.EditInfoActivity;
import com.lymindev.datingx.databinding.FragmentAccountBinding;


public class AccountFragment extends Fragment {

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private FragmentAccountBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_account, container, false);

        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), EditInfoActivity.class));
            }
        });

        binding.btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SettingsActivity.class));
            }
        });

        getInfo();
        return binding.getRoot();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void getInfo() {
        UsersRealm user = new UsersManager().getUserInfo(new SharePreferenceX(getContext()).getPrefernces(Constants.SharePref.currentUserID));

        binding.tvUsername.setText(user.getName());
        binding.tvAge.setText(user.getAge());
        binding.tvBio.setText(user.getAbout());

        Glide.with(getContext()).load(user.getProfile()).override(300,300).into(binding.imageProfile);

        if (user.getGander().equals("Male")){
            Glide.with(getContext()).load(getContext().getDrawable(R.drawable.men_sign)).override(300,300).into(binding.imgGenderSign);
            try {
                binding.imgGenderSign.getDrawable().setTint(getContext().getResources().getColor(R.color.blue_800));
            }catch (Exception e){e.printStackTrace();}

        }else {
            Glide.with(getContext()).load(getContext().getDrawable(R.drawable.women_sign)).override(300,300).into(binding.imgGenderSign);
            try {
                binding.imgGenderSign.getDrawable().setTint(getContext().getResources().getColor(R.color.red_500));
            }catch (Exception e){e.printStackTrace();}

        }

    }
}