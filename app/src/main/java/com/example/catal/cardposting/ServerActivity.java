package com.example.catal.cardposting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.catal.cardposting.DAO.Database;
import com.example.catal.cardposting.Model.Card;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ServerActivity extends AppCompatActivity implements ServerNavigator  {
    Database db;
    ArrayList<Card> hand;
    int currentPosition;
    ArrayList<ImageView> listOfImages;
    ImageView iv1;
    ImageView iv2;
    ImageView iv3;
    ImageView iv4;
    Button nextHand;
    Button restartHand;
    private String deckType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);


        currentPosition = 0;
        hand = new ArrayList<>();
        iv1 = findViewById(R.id.iv_1);
        iv2 = findViewById(R.id.iv_2);
        iv3 = findViewById(R.id.iv_3);
        iv4 = findViewById(R.id.iv_4);
        listOfImages = new ArrayList<>();
        listOfImages.add(iv1);
        listOfImages.add(iv2);
        listOfImages.add(iv3);
        listOfImages.add(iv4);
        deckType = this.getIntent().getStringExtra("deckType");


        nextHand = findViewById(R.id.bt_next);
        restartHand = findViewById(R.id.bt_restart);


        nextHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewHand();
            }
        });
        restartHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.setDeck();
                openNewHand();
            }
        });


        db = new Database(this, deckType);
        if(this.getIntent().getBooleanExtra("isOtherHand", false)) {
            db.setDeck();
        } else {
            db.setAA();
        }
        db.listenChange();
    }

    public void openNewHand() {
        Intent intent = new Intent(this, ServerActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean("isOtherHand", false);
        bundle.putString("deckType", deckType);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    public void loadImage(Card currentCard) {
        if(currentPosition < 4) {

            Context context = this.getApplicationContext();

            switch(deckType){
                case "magyar" : {
                    if (currentCard.getCuloare().equals("rosu")) {
                        if (currentCard.getNr().equals("as"))
                            Picasso.with(context).load(R.drawable.m_as_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("doi"))
                            Picasso.with(context).load(R.drawable.m_doi_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("trei"))
                            Picasso.with(context).load(R.drawable.m_trei_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("patru"))
                            Picasso.with(context).load(R.drawable.m_patru_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("noua"))
                            Picasso.with(context).load(R.drawable.m_noua_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("zece"))
                            Picasso.with(context).load(R.drawable.m_zece_rosu).into(listOfImages.get(currentPosition));
                    }

                    if (currentCard.getCuloare().equals("ghinda")) {
                        if (currentCard.getNr().equals("as"))
                            Picasso.with(context).load(R.drawable.m_as_ghinda).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("doi"))
                            Picasso.with(context).load(R.drawable.m_doi_ghinda).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("trei"))
                            Picasso.with(context).load(R.drawable.m_trei_ghinda).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("patru"))
                            Picasso.with(context).load(R.drawable.m_patru_ghinda).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("noua"))
                            Picasso.with(context).load(R.drawable.m_noua_ghinda).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("zece"))
                            Picasso.with(context).load(R.drawable.m_zece_ghinda).into(listOfImages.get(currentPosition));
                    }

                    if (currentCard.getCuloare().equals("bata")) {
                        if (currentCard.getNr().equals("as"))
                            Picasso.with(context).load(R.drawable.m_as_duba).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("doi"))
                            Picasso.with(context).load(R.drawable.m_doi_duba).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("trei"))
                            Picasso.with(context).load(R.drawable.m_trei_duba).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("patru"))
                            Picasso.with(context).load(R.drawable.m_patru_duba).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("noua"))
                            Picasso.with(context).load(R.drawable.m_noua_duba).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("zece"))
                            Picasso.with(context).load(R.drawable.m_zece_duba).into(listOfImages.get(currentPosition));
                    }

                    if (currentCard.getCuloare().equals("verde")) {
                        if (currentCard.getNr().equals("as"))
                            Picasso.with(context).load(R.drawable.m_as_verde).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("doi"))
                            Picasso.with(context).load(R.drawable.m_doi_verde).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("trei"))
                            Picasso.with(context).load(R.drawable.m_trei_verde).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("patru"))
                            Picasso.with(context).load(R.drawable.m_patru_verde).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("noua"))
                            Picasso.with(context).load(R.drawable.m_noua_verde).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("zece"))
                            Picasso.with(context).load(R.drawable.m_zece_verde).into(listOfImages.get(currentPosition));
                    }
                    break;
                }
                case "traditional": {
                    if (currentCard.getCuloare().equals("rosu")) {
                        if (currentCard.getNr().equals("as"))
                            Picasso.with(context).load(R.drawable.t_as_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("doi"))
                            Picasso.with(context).load(R.drawable.t_doi_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("trei"))
                            Picasso.with(context).load(R.drawable.t_trei_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("patru"))
                            Picasso.with(context).load(R.drawable.t_patru_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("cinci"))
                            Picasso.with(context).load(R.drawable.t_cinci_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("sase"))
                            Picasso.with(context).load(R.drawable.t_sase_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("sapte"))
                            Picasso.with(context).load(R.drawable.t_sapte_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("opt"))
                            Picasso.with(context).load(R.drawable.t_opt_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("noua"))
                            Picasso.with(context).load(R.drawable.t_noua_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("zece"))
                            Picasso.with(context).load(R.drawable.t_zece_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("j"))
                            Picasso.with(context).load(R.drawable.t_j_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("q"))
                            Picasso.with(context).load(R.drawable.t_q_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("k"))
                            Picasso.with(context).load(R.drawable.t_k_rosu).into(listOfImages.get(currentPosition));
                    }

                    if (currentCard.getCuloare().equals("negru")) {
                        if (currentCard.getNr().equals("as"))
                            Picasso.with(context).load(R.drawable.t_as_negru).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("doi"))
                            Picasso.with(context).load(R.drawable.t_doi_negru).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("trei"))
                            Picasso.with(context).load(R.drawable.t_trei_negru).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("patru"))
                            Picasso.with(context).load(R.drawable.t_patru_negru).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("cinci"))
                            Picasso.with(context).load(R.drawable.t_cinci_negru).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("sase"))
                            Picasso.with(context).load(R.drawable.t_sase_negru).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("sapte"))
                            Picasso.with(context).load(R.drawable.t_sapte_negru).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("opt"))
                            Picasso.with(context).load(R.drawable.t_opt_negru).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("noua"))
                            Picasso.with(context).load(R.drawable.t_noua_negru).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("zece"))
                            Picasso.with(context).load(R.drawable.t_zece_negru).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("j"))
                            Picasso.with(context).load(R.drawable.t_j_negru).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("q"))
                            Picasso.with(context).load(R.drawable.t_q_negru).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("k"))
                            Picasso.with(context).load(R.drawable.t_k_negru).into(listOfImages.get(currentPosition));
                    }

                    if (currentCard.getCuloare().equals("trefla")) {
                        if (currentCard.getNr().equals("as"))
                            Picasso.with(context).load(R.drawable.t_as_trefla).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("doi"))
                            Picasso.with(context).load(R.drawable.t_doi_trefla).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("trei"))
                            Picasso.with(context).load(R.drawable.t_trei_trefla).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("patru"))
                            Picasso.with(context).load(R.drawable.t_patru_trefla).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("cinci"))
                            Picasso.with(context).load(R.drawable.t_cinci_trefla).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("sase"))
                            Picasso.with(context).load(R.drawable.t_sase_trefla).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("sapte"))
                            Picasso.with(context).load(R.drawable.t_sapte_trefla).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("opt"))
                            Picasso.with(context).load(R.drawable.t_opt_trefla).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("noua"))
                            Picasso.with(context).load(R.drawable.t_noua_trefla).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("zece"))
                            Picasso.with(context).load(R.drawable.t_zece_trefla).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("j"))
                            Picasso.with(context).load(R.drawable.t_j_trefla).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("q"))
                            Picasso.with(context).load(R.drawable.t_q_trefla).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("k"))
                            Picasso.with(context).load(R.drawable.t_k_trefla).into(listOfImages.get(currentPosition));
                    }

                    if (currentCard.getCuloare().equals("romb")) {
                        if (currentCard.getNr().equals("as"))
                            Picasso.with(context).load(R.drawable.t_as_romb).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("doi"))
                            Picasso.with(context).load(R.drawable.t_doi_romb).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("trei"))
                            Picasso.with(context).load(R.drawable.t_trei_romb).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("patru"))
                            Picasso.with(context).load(R.drawable.t_patru_romb).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("cinci"))
                            Picasso.with(context).load(R.drawable.t_cinci_romb).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("sase"))
                            Picasso.with(context).load(R.drawable.t_sase_romb).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("sapte"))
                            Picasso.with(context).load(R.drawable.t_sapte_romb).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("opt"))
                            Picasso.with(context).load(R.drawable.t_opt_romb).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("noua"))
                            Picasso.with(context).load(R.drawable.t_noua_romb).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("zece"))
                            Picasso.with(context).load(R.drawable.t_zece_romb).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("j"))
                            Picasso.with(context).load(R.drawable.t_j_romb).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("q"))
                            Picasso.with(context).load(R.drawable.t_q_romb).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("k"))
                            Picasso.with(context).load(R.drawable.t_k_romb).into(listOfImages.get(currentPosition));
                    }
                    break;
                }
                default: {
                    if (currentCard.getCuloare().equals("rosu")) {
                        if (currentCard.getNr().equals("as"))
                            Picasso.with(context).load(R.drawable.m_as_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("doi"))
                            Picasso.with(context).load(R.drawable.m_doi_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("trei"))
                            Picasso.with(context).load(R.drawable.m_trei_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("patru"))
                            Picasso.with(context).load(R.drawable.m_patru_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("noua"))
                            Picasso.with(context).load(R.drawable.m_noua_rosu).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("zece"))
                            Picasso.with(context).load(R.drawable.m_zece_rosu).into(listOfImages.get(currentPosition));
                    }

                    if (currentCard.getCuloare().equals("ghinda")) {
                        if (currentCard.getNr().equals("as"))
                            Picasso.with(context).load(R.drawable.m_as_ghinda).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("doi"))
                            Picasso.with(context).load(R.drawable.m_doi_ghinda).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("trei"))
                            Picasso.with(context).load(R.drawable.m_trei_ghinda).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("patru"))
                            Picasso.with(context).load(R.drawable.m_patru_ghinda).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("noua"))
                            Picasso.with(context).load(R.drawable.m_noua_ghinda).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("zece"))
                            Picasso.with(context).load(R.drawable.m_zece_ghinda).into(listOfImages.get(currentPosition));
                    }

                    if (currentCard.getCuloare().equals("bata")) {
                        if (currentCard.getNr().equals("as"))
                            Picasso.with(context).load(R.drawable.m_as_duba).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("doi"))
                            Picasso.with(context).load(R.drawable.m_doi_duba).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("trei"))
                            Picasso.with(context).load(R.drawable.m_trei_duba).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("patru"))
                            Picasso.with(context).load(R.drawable.m_patru_duba).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("noua"))
                            Picasso.with(context).load(R.drawable.m_noua_duba).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("zece"))
                            Picasso.with(context).load(R.drawable.m_zece_duba).into(listOfImages.get(currentPosition));
                    }

                    if (currentCard.getCuloare().equals("verde")) {
                        if (currentCard.getNr().equals("as"))
                            Picasso.with(context).load(R.drawable.m_as_verde).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("doi"))
                            Picasso.with(context).load(R.drawable.m_doi_verde).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("trei"))
                            Picasso.with(context).load(R.drawable.m_trei_verde).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("patru"))
                            Picasso.with(context).load(R.drawable.m_patru_verde).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("noua"))
                            Picasso.with(context).load(R.drawable.m_noua_verde).into(listOfImages.get(currentPosition));
                        if (currentCard.getNr().equals("zece"))
                            Picasso.with(context).load(R.drawable.m_zece_verde).into(listOfImages.get(currentPosition));
                    }
                    break;
                }
            }
            currentPosition++;
        }
    }

}
