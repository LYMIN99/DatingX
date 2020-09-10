package com.lymindev.datingx.fragments;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lymindev.datingx.R;
import com.lymindev.datingx.activities.StepperWizardColor;
import com.lymindev.datingx.activities.user.UserProfileActivity;
import com.lymindev.datingx.databinding.FragmentExploreBinding;


public class ExploreFragment extends Fragment {


    public ExploreFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private FragmentExploreBinding binding;
    private static final int MAX_STEP = 4;

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;

    private String about_title_array[] = {
            "Monika",
            "Taa",
            "Liza",
            "Lina"
    };
    private String about_description_array[] = {
            "Choose your destination, plan Your trip. Pick the best place for Your holiday",
            "Select the day, pick Your ticket. We give you the best prices. We guarantee!",
            "Safe and Comfort flight is our priority. Professional crew and services.",
            "Enjoy your holiday, Don't forget to feel the moment and take a photo!",
    };

    private int bg_images_array[] = {
            R.drawable.user_girl,
            R.drawable.user_girl,
            R.drawable.user_girl,
            R.drawable.user_girl
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_explore, container, false);

        viewPager = binding.viewPager;

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        return binding.getRoot();
    }


    //  viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(final int position) {
            //bottomProgressDots(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;
        private Button btnNext;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.item_card_user_explore, container, false);
            ((TextView) view.findViewById(R.id.tv_username)).setText(about_title_array[position]);
            ((TextView) view.findViewById(R.id.description)).setText(about_description_array[position]);
            ((ImageView) view.findViewById(R.id.image_bg)).setImageResource(bg_images_array[position]);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent sharedIntent = new Intent(getContext(), UserProfileActivity.class);
                    Pair[] pairs = new Pair[6];
                    pairs[0] = new Pair<View,String> (view.findViewById(R.id.image_bg),"imageTransaction");
                    pairs[1] = new Pair<View,String> (view.findViewById(R.id.tv_username),"nameTransaction");
                    pairs[2] = new Pair<View,String> (view.findViewById(R.id.layout_view),"layoutTransaction");
                    pairs[3] = new Pair<View,String> (view.findViewById(R.id.btn_2),"btn2");
                    pairs[4] = new Pair<View,String> (view.findViewById(R.id.btn_3),"btn3");
                    pairs[5] = new Pair<View,String> (view.findViewById(R.id.btn_4),"btn4");

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) getContext(),pairs);
                    startActivity(sharedIntent,options.toBundle());
                }
            });
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return about_title_array.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}