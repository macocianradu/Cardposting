package com.example.catal.cardposting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.catal.cardposting.DAO.Database;
import com.example.catal.cardposting.Model.Card;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ClientActivity extends AppCompatActivity implements ClientNavigator{
    Database db;

    private ArrayList<Card> cards = new ArrayList<>();
    private ImageView imageView, imageView2;
    private int currentPosition, currentView;
    private String deckType;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        new OnSwipeTouchListener(this);
        deckType = this.getIntent().getStringExtra("deckType");
        Log.d("DEKT", deckType);

        db = new Database(this, deckType);
        currentPosition = 0;
        currentView = 1;


//        cards.add(new Card("doi", "rosu"));
//        cards.add(new Card("trei", "ghinda"));
//        cards.add(new Card("patru", "verde"));
//        cards.add(new Card("zece", "rosu"));
//        cards.add(new Card("as", "verde"));
        imageView =  findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);


        imageView2.setVisibility(ImageView.GONE);
        imageView2.setBackgroundColor(getColor(R.color.colorPrimaryDark));

        imageView.setBackgroundColor(getColor(R.color.colorPrimaryDark));
        imageView.setOnTouchListener(new OnSwipeTouchListener(ClientActivity.this) {
            public void onSwipeTop() {
                if(currentView == 1) {
                    Animations.slideUp(imageView);
                    if (cards.size() > 0) {
                        db.placeCard(cards.get(currentPosition));
                        cards.remove(currentPosition);
                        if(currentPosition != 0){
                            currentPosition -- ;
                        }
                        if (cards.size() == 0){
                            imageView2.setImageResource(0);
                        }
                        else{
                            loadImage(cards.get(currentPosition), imageView2);
                        }
                        Animations.slideInLeft(imageView2);
                        imageView2.setVisibility(ImageView.VISIBLE);
                        currentView = 2;
                    }
                } else {
                    Animations.slideUp(imageView2);
                    if (cards.size() > 0) {
                        db.placeCard(cards.get(currentPosition));
                        cards.remove(currentPosition);
                        if(currentPosition != 0){
                            currentPosition -- ;
                        }
                        if (cards.size() == 0){
                            imageView.setImageResource(0);
                        }
                        else{
                            loadImage(cards.get(currentPosition), imageView);
                        }
                        Animations.slideInLeft(imageView);
                        imageView.setVisibility(ImageView.VISIBLE);
                        currentView = 1;
                    }
                }
            }
            public void onSwipeRight() {
                if(cards.size() > 1) {
                    if (currentView == 1) {
                        if (currentPosition < cards.size() - 1) {
                            currentPosition++;
                        } else {
                            currentPosition = 0;
                        }
                        loadImage(cards.get(currentPosition), imageView2);
                        imageView2.setVisibility(ImageView.VISIBLE);
                        Animations.slideOutRight(imageView);
                        Animations.slideInLeft(imageView2);
                        imageView.setVisibility(ImageView.GONE);
                        currentView = 2;
                    } else {
                        if (currentPosition < cards.size() - 1) {
                            currentPosition++;
                        } else {
                            currentPosition = 0;
                        }
                        loadImage(cards.get(currentPosition), imageView);
                        imageView.setVisibility(ImageView.VISIBLE);
                        Animations.slideOutRight(imageView2);
                        Animations.slideInLeft(imageView);
                        imageView2.setVisibility(ImageView.GONE);
                        currentView = 1;
                    }
                }
            }
            public void onSwipeLeft() {
                if(cards.size() > 1) {
                    if (currentView == 1) {
                        if (currentPosition > 0) {
                            currentPosition--;
                        } else {
                            currentPosition = cards.size() - 1;
                        }
                        loadImage(cards.get(currentPosition), imageView2);
                        imageView2.setVisibility(ImageView.VISIBLE);
                        Animations.slideOutLeft(imageView);
                        Animations.slideInRight(imageView2);
                        imageView.setVisibility(ImageView.GONE);
                        currentView = 2;
                    } else {
                        if (currentPosition > 0) {
                            currentPosition--;
                        } else {
                            currentPosition = cards.size() - 1;
                        }
                        loadImage(cards.get(currentPosition), imageView);
                        imageView.setVisibility(ImageView.VISIBLE);
                        Animations.slideOutLeft(imageView2);
                        Animations.slideInRight(imageView);
                        imageView2.setVisibility(ImageView.GONE);
                        currentView = 1;
                    }
                }
            }
            public void onSwipeBottom() {
                if(currentView == 1) {
                    Picasso.with(getApplicationContext()).load(R.drawable.back).into(imageView2);
                    db.getCard();
                    Animations.slideDown(imageView2);
                    Animations.slideOutRight(imageView);
                    currentView = 2;
                }
                else {
                    Picasso.with(getApplicationContext()).load(R.drawable.back).into(imageView);
                    db.getCard();
                    Animations.slideDown(imageView);
                    Animations.slideOutRight(imageView2);
                    currentView = 1;
                }
            }

        });
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
                /*
                 * The following method, "handleShakeEvent(count):" is a stub //
                 * method you would use to setup whatever you want done once the
                 * device has been shook.
                 */
                clear();
                Log.d("Shaking", "i shook lol " + currentPosition + " " + cards.size());

            }});
    }
    public void loadImage(Card currentCard, ImageView imageView) {
        Context context = getApplicationContext();
        if(deckType.equals("magyar")) {
            if (currentCard.getCuloare().equals("rosu")) {
                if (currentCard.getNr().equals("as"))
                    Picasso.with(context).load(R.drawable.m_as_rosu).into(imageView);
                if (currentCard.getNr().equals("doi"))
                    Picasso.with(context).load(R.drawable.m_doi_rosu).into(imageView);
                if (currentCard.getNr().equals("trei"))
                    Picasso.with(context).load(R.drawable.m_trei_rosu).into(imageView);
                if (currentCard.getNr().equals("patru"))
                    Picasso.with(context).load(R.drawable.m_patru_rosu).into(imageView);
                if (currentCard.getNr().equals("noua"))
                    Picasso.with(context).load(R.drawable.m_noua_rosu).into(imageView);
                if (currentCard.getNr().equals("zece"))
                    Picasso.with(context).load(R.drawable.m_zece_rosu).into(imageView);
            }

            if (currentCard.getCuloare().equals("ghinda")) {
                if (currentCard.getNr().equals("as"))
                    Picasso.with(context).load(R.drawable.m_as_ghinda).into(imageView);
                if (currentCard.getNr().equals("doi"))
                    Picasso.with(context).load(R.drawable.m_doi_ghinda).into(imageView);
                if (currentCard.getNr().equals("trei"))
                    Picasso.with(context).load(R.drawable.m_trei_ghinda).into(imageView);
                if (currentCard.getNr().equals("patru"))
                    Picasso.with(context).load(R.drawable.m_patru_ghinda).into(imageView);
                if (currentCard.getNr().equals("noua"))
                    Picasso.with(context).load(R.drawable.m_noua_ghinda).into(imageView);
                if (currentCard.getNr().equals("zece"))
                    Picasso.with(context).load(R.drawable.m_zece_ghinda).into(imageView);
            }

            if (currentCard.getCuloare().equals("bata")) {
                if (currentCard.getNr().equals("as"))
                    Picasso.with(context).load(R.drawable.m_as_duba).into(imageView);
                if (currentCard.getNr().equals("doi"))
                    Picasso.with(context).load(R.drawable.m_doi_duba).into(imageView);
                if (currentCard.getNr().equals("trei"))
                    Picasso.with(context).load(R.drawable.m_trei_duba).into(imageView);
                if (currentCard.getNr().equals("patru"))
                    Picasso.with(context).load(R.drawable.m_patru_duba).into(imageView);
                if (currentCard.getNr().equals("noua"))
                    Picasso.with(context).load(R.drawable.m_noua_duba).into(imageView);
                if (currentCard.getNr().equals("zece"))
                    Picasso.with(context).load(R.drawable.m_zece_duba).into(imageView);
            }

            if (currentCard.getCuloare().equals("verde")) {
                if (currentCard.getNr().equals("as"))
                    Picasso.with(context).load(R.drawable.m_as_verde).into(imageView);
                if (currentCard.getNr().equals("doi"))
                    Picasso.with(context).load(R.drawable.m_doi_verde).into(imageView);
                if (currentCard.getNr().equals("trei"))
                    Picasso.with(context).load(R.drawable.m_trei_verde).into(imageView);
                if (currentCard.getNr().equals("patru"))
                    Picasso.with(context).load(R.drawable.m_patru_verde).into(imageView);
                if (currentCard.getNr().equals("noua"))
                    Picasso.with(context).load(R.drawable.m_noua_verde).into(imageView);
                if (currentCard.getNr().equals("zece"))
                    Picasso.with(context).load(R.drawable.m_zece_verde).into(imageView);
            }
        }
        else if(deckType.equals("traditional")) {
            if (currentCard.getCuloare().equals("rosu")) {
                if (currentCard.getNr().equals("as"))
                    Picasso.with(context).load(R.drawable.t_as_rosu).into(imageView);
                if (currentCard.getNr().equals("doi"))
                    Picasso.with(context).load(R.drawable.t_doi_rosu).into(imageView);
                if (currentCard.getNr().equals("trei"))
                    Picasso.with(context).load(R.drawable.t_trei_rosu).into(imageView);
                if (currentCard.getNr().equals("patru"))
                    Picasso.with(context).load(R.drawable.t_patru_rosu).into(imageView);
                if (currentCard.getNr().equals("cinci"))
                    Picasso.with(context).load(R.drawable.t_cinci_rosu).into(imageView);
                if (currentCard.getNr().equals("sase"))
                    Picasso.with(context).load(R.drawable.t_sase_rosu).into(imageView);
                if (currentCard.getNr().equals("sapte"))
                    Picasso.with(context).load(R.drawable.t_sapte_rosu).into(imageView);
                if (currentCard.getNr().equals("opt"))
                    Picasso.with(context).load(R.drawable.t_opt_rosu).into(imageView);
                if (currentCard.getNr().equals("noua"))
                    Picasso.with(context).load(R.drawable.t_noua_rosu).into(imageView);
                if (currentCard.getNr().equals("zece"))
                    Picasso.with(context).load(R.drawable.t_zece_rosu).into(imageView);
                if (currentCard.getNr().equals("j"))
                    Picasso.with(context).load(R.drawable.t_j_rosu).into(imageView);
                if (currentCard.getNr().equals("q"))
                    Picasso.with(context).load(R.drawable.t_q_rosu).into(imageView);
                if (currentCard.getNr().equals("k"))
                    Picasso.with(context).load(R.drawable.t_k_rosu).into(imageView);
            }

            if (currentCard.getCuloare().equals("negru")) {
                if (currentCard.getNr().equals("as"))
                    Picasso.with(context).load(R.drawable.t_as_negru).into(imageView);
                if (currentCard.getNr().equals("doi"))
                    Picasso.with(context).load(R.drawable.t_doi_negru).into(imageView);
                if (currentCard.getNr().equals("trei"))
                    Picasso.with(context).load(R.drawable.t_trei_negru).into(imageView);
                if (currentCard.getNr().equals("patru"))
                    Picasso.with(context).load(R.drawable.t_patru_negru).into(imageView);
                if (currentCard.getNr().equals("cinci"))
                    Picasso.with(context).load(R.drawable.t_cinci_negru).into(imageView);
                if (currentCard.getNr().equals("sase"))
                    Picasso.with(context).load(R.drawable.t_sase_negru).into(imageView);
                if (currentCard.getNr().equals("sapte"))
                    Picasso.with(context).load(R.drawable.t_sapte_negru).into(imageView);
                if (currentCard.getNr().equals("opt"))
                    Picasso.with(context).load(R.drawable.t_opt_negru).into(imageView);
                if (currentCard.getNr().equals("noua"))
                    Picasso.with(context).load(R.drawable.t_noua_negru).into(imageView);
                if (currentCard.getNr().equals("zece"))
                    Picasso.with(context).load(R.drawable.t_zece_negru).into(imageView);
                if (currentCard.getNr().equals("j"))
                    Picasso.with(context).load(R.drawable.t_j_negru).into(imageView);
                if (currentCard.getNr().equals("q"))
                    Picasso.with(context).load(R.drawable.t_q_negru).into(imageView);
                if (currentCard.getNr().equals("k"))
                    Picasso.with(context).load(R.drawable.t_k_negru).into(imageView);
            }

            if (currentCard.getCuloare().equals("trefla")) {
                if (currentCard.getNr().equals("as"))
                    Picasso.with(context).load(R.drawable.t_as_trefla).into(imageView);
                if (currentCard.getNr().equals("doi"))
                    Picasso.with(context).load(R.drawable.t_doi_trefla).into(imageView);
                if (currentCard.getNr().equals("trei"))
                    Picasso.with(context).load(R.drawable.t_trei_trefla).into(imageView);
                if (currentCard.getNr().equals("patru"))
                    Picasso.with(context).load(R.drawable.t_patru_trefla).into(imageView);
                if (currentCard.getNr().equals("cinci"))
                    Picasso.with(context).load(R.drawable.t_cinci_trefla).into(imageView);
                if (currentCard.getNr().equals("sase"))
                    Picasso.with(context).load(R.drawable.t_sase_trefla).into(imageView);
                if (currentCard.getNr().equals("sapte"))
                    Picasso.with(context).load(R.drawable.t_sapte_trefla).into(imageView);
                if (currentCard.getNr().equals("opt"))
                    Picasso.with(context).load(R.drawable.t_opt_trefla).into(imageView);
                if (currentCard.getNr().equals("noua"))
                    Picasso.with(context).load(R.drawable.t_noua_trefla).into(imageView);
                if (currentCard.getNr().equals("zece"))
                    Picasso.with(context).load(R.drawable.t_zece_trefla).into(imageView);
                if (currentCard.getNr().equals("j"))
                    Picasso.with(context).load(R.drawable.t_j_trefla).into(imageView);
                if (currentCard.getNr().equals("q"))
                    Picasso.with(context).load(R.drawable.t_q_trefla).into(imageView);
                if (currentCard.getNr().equals("k"))
                    Picasso.with(context).load(R.drawable.t_k_trefla).into(imageView);
            }

            if (currentCard.getCuloare().equals("romb")) {
                if (currentCard.getNr().equals("as"))
                    Picasso.with(context).load(R.drawable.t_as_romb).into(imageView);
                if (currentCard.getNr().equals("doi"))
                    Picasso.with(context).load(R.drawable.t_doi_romb).into(imageView);
                if (currentCard.getNr().equals("trei"))
                    Picasso.with(context).load(R.drawable.t_trei_romb).into(imageView);
                if (currentCard.getNr().equals("patru"))
                    Picasso.with(context).load(R.drawable.t_patru_romb).into(imageView);
                if (currentCard.getNr().equals("cinci"))
                    Picasso.with(context).load(R.drawable.t_cinci_romb).into(imageView);
                if (currentCard.getNr().equals("sase"))
                    Picasso.with(context).load(R.drawable.t_sase_romb).into(imageView);
                if (currentCard.getNr().equals("sapte"))
                    Picasso.with(context).load(R.drawable.t_sapte_romb).into(imageView);
                if (currentCard.getNr().equals("opt"))
                    Picasso.with(context).load(R.drawable.t_opt_romb).into(imageView);
                if (currentCard.getNr().equals("noua"))
                    Picasso.with(context).load(R.drawable.t_noua_romb).into(imageView);
                if (currentCard.getNr().equals("zece"))
                    Picasso.with(context).load(R.drawable.t_zece_romb).into(imageView);
                if (currentCard.getNr().equals("j"))
                    Picasso.with(context).load(R.drawable.t_j_romb).into(imageView);
                if (currentCard.getNr().equals("q"))
                    Picasso.with(context).load(R.drawable.t_q_romb).into(imageView);
                if (currentCard.getNr().equals("k"))
                    Picasso.with(context).load(R.drawable.t_k_romb).into(imageView);
            }
        }
    }


    public void addCard(Card cardToAdd) {
        if(currentView == 1) {
            loadImage(cardToAdd, imageView);
        }
        else {
            loadImage(cardToAdd, imageView2);
        }
        boolean alreadyEx = false;
        for(Card c: cards) {
            if(c.getCuloare().equals(cardToAdd.getCuloare()) && c.getNr().equals(cardToAdd.getNr()))
                alreadyEx = true;
        }
        if(!alreadyEx) {
            cards.add(cardToAdd);
            currentPosition = cards.size() - 1;
            Log.i("START", Integer.toString(currentPosition));
        }
    }

        public void clear(){
            if(currentView == 1){
                Animations.slideUp(imageView);
            }
            else {
                Animations.slideUp(imageView2);
            }
            cards.clear();
        }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    public void emptyAlert() {
        Toast.makeText(getApplicationContext(), "The deck is empty!", Toast.LENGTH_SHORT).show();
    }
}



