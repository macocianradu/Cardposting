package com.example.catal.cardposting.DAO;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.catal.cardposting.ClientNavigator;
import com.example.catal.cardposting.MainNavigator;
import com.example.catal.cardposting.Model.Card;
import com.example.catal.cardposting.ServerNavigator;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Database {
    FirebaseFirestore db;
    ArrayList<Card> deck;
    ServerNavigator server;
    ClientNavigator client;
    MainNavigator nav;
    String deckType;

//    public Database(MainNavigator callback) {
//        db = FirebaseFirestore.getInstance();
//        nav = callback;
//        deck = Card.getCompleteDeck(deckType);
   // }

    public Database(ClientNavigator callback, String type) {
        db = FirebaseFirestore.getInstance();
        this.deckType = type;
        deck = Card.getCompleteDeck(deckType);
        client = callback;
    }

    public Database(ServerNavigator callback, String type) {
        db = FirebaseFirestore.getInstance();
        this.deckType = type;
        deck = Card.getCompleteDeck(deckType);
        server = callback;
    }

    public void setAA() {
        Map<String, Object> data1 = new HashMap<>();
        data1.put("0", "A_A");
        db.collection("lastCard").document("lastCardPlayed").set(data1);
    }

    public void setDeck() {
        Map<String, Object> data = new HashMap<>();
        int i = 0;
        for(Card c : deck) {
            Log.i("DATABASE", Integer.toString(i) +  c.getNr() + "_" + c.getCuloare());
            data.put(Integer.toString(i), c.getNr() + "_" + c.getCuloare());
            i++;
        }
        Map<String, Object> data1 = new HashMap<>();
        data1.put("0", "A_A");


        db.collection("deck").document("currentDeck").set(data);
        db.collection("lastCard").document("lastCardPlayed").set(data1);
    }

    public void getCard() {
        db.collection("deck")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                int deckSize = document.getData().keySet().size();
                                Log.d("DATABASE", ""+document.getData().values());
                                ArrayList<Card> cards = new ArrayList<>();
                                Card c = new Card("", "");
                                Map<String, Object> data = new HashMap<>();
                                for(int i= 0; i<deckSize;i++)
                                {
                                    Log.d("DATABASE", document.get(String.valueOf(i)).toString());
                                    String carte = document.get(String.valueOf(i)).toString();
                                    String[] parts = carte.split("_", 2);
                                    String nr = parts[0], culoare = parts[1];
                                    cards.add(new Card(nr,culoare));
                                    if(i!=0) {
                                        data.put(Integer.toString(i-1), nr + "_" + culoare);
                                    }
                                    else
                                    {
                                        c = new Card(nr,culoare);
                                        client.addCard(c);
                                    }
                                }
                                if(deckSize==0) {
                                    client.emptyAlert();
                                }
                                db.collection("deck").document("currentDeck").set(data);
                            }
                        } else {
                            Log.d("DATABASE_GET", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void listenChange() {
        DocumentReference docRef = db.collection("lastCard").document("lastCardPlayed");
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {

                String carte = snapshot.get("0").toString();
                String[] parts = carte.split("_", 2);
                String nr = parts[0], culoare = parts[1];
                Card cardToSend = new Card(nr, culoare);
                if(!nr.equals("A"))
                    server.loadImage(cardToSend);
                Log.d("Acces", "O mers" + snapshot.getData());
            }
        });
    }

    public void placeCard(Card c)
    {
        HashMap<String, Object> lastCard = new HashMap<>();
        lastCard.put("0",c.getNr() + "_" + c.getCuloare());
        db.collection("lastCard").document("lastCardPlayed").set(lastCard);

    }

}
