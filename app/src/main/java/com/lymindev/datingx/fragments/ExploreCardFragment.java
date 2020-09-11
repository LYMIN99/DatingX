package com.lymindev.datingx.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.lymindev.datingx.R;
import com.lymindev.datingx.adapters.CardStackAdapter;
import com.lymindev.datingx.model.Spot;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;
import java.util.List;

public class ExploreCardFragment extends Fragment implements CardStackListener {


    public ExploreCardFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    private CardStackView cardStackView;
    private CardStackAdapter cardStackAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_explore_card, container, false);

        cardStackView = view.findViewById(R.id.card_stack_view);

        initialize();
        return view;

    }

    private void initialize() {
        CardStackLayoutManager manager = new CardStackLayoutManager(getContext());
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.HORIZONTAL);
        manager.setCanScrollHorizontal(true);
        manager.setCanScrollVertical(true);
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual);
        manager.setOverlayInterpolator(new LinearInterpolator());

        cardStackAdapter = new CardStackAdapter();
        cardStackAdapter.setSpots(spotList());

        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(cardStackAdapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());
//        cardStackView.itemAnimator.apply {
//            if (this is DefaultItemAnimator) {
//                supportsChangeAnimations = false;
//            }
//        }
    }

    private List<Spot> spotList (){
        List <Spot> spots = new ArrayList<>();
        spots.add(new Spot("Yasaka Shrine", "Kyoto", "https://source.unsplash.com/Xq1ntWruZQI/600x800"));
        spots.add(new Spot( "Fushimi Inari Shrine", "Kyoto","https://source.unsplash.com/NYyCqdBOKwc/600x800"));
        spots.add(new Spot("Bamboo Forest","Kyoto", "https://source.unsplash.com/buF62ewDLcQ/600x800"));
        spots.add(new Spot("Brooklyn Bridge", "New York", "https://source.unsplash.com/THozNzxEP3g/600x800"));
        spots.add(new Spot("Empire State Building",  "New York","https://source.unsplash.com/USrZRcRS2Lw/600x800"));
        spots.add(new Spot("The statue of Liberty", "New York", "https://source.unsplash.com/PeFk7fzxTdk/600x800"));
        spots.add(new Spot("Louvre Museum","Paris", "https://source.unsplash.com/LrMWHKqilUw/600x800"));
        spots.add(new Spot("Eiffel Tower", "Paris", "https://source.unsplash.com/HN-5Z6AmxrM/600x800"));
        spots.add(new Spot("Big Ben", "London",  "https://source.unsplash.com/CdVAUADdqEc/600x800"));
        spots.add(new Spot("Great Wall of China", "China", "https://source.unsplash.com/AWh9C-QjhE4/600x800"));
        return spots;
    }



    @Override
    public void onCardDragging(Direction direction, float ratio) {

    }

    @Override
    public void onCardSwiped(Direction direction) {

    }

    @Override
    public void onCardRewound() {

    }

    @Override
    public void onCardCanceled() {

    }

    @Override
    public void onCardAppeared(View view, int position) {

    }

    @Override
    public void onCardDisappeared(View view, int position) {

    }
}