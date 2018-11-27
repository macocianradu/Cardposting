package com.example.catal.cardposting.Model;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Card implements Serializable {
    private String nr;
    private String culoare;

    public Card(String nr, String culoare) {
        this.nr = nr;
        this.culoare = culoare;
    }

    public static ArrayList<Card> getCompleteDeck(String type) {
        ArrayList<Card> deck = new ArrayList<>();
        if(type.equals("magyar")) {
            deck.add(new Card("as", "rosu"));
            deck.add(new Card("as", "verde"));
            deck.add(new Card("as", "bata"));
            deck.add(new Card("as", "ghinda"));
            deck.add(new Card("doi", "rosu"));
            deck.add(new Card("doi", "verde"));
            deck.add(new Card("doi", "bata"));
            deck.add(new Card("doi", "ghinda"));
            deck.add(new Card("trei", "rosu"));
            deck.add(new Card("trei", "verde"));
            deck.add(new Card("trei", "bata"));
            deck.add(new Card("trei", "ghinda"));
            deck.add(new Card("patru", "rosu"));
            deck.add(new Card("patru", "verde"));
            deck.add(new Card("patru", "bata"));
            deck.add(new Card("patru", "ghinda"));
            deck.add(new Card("noua", "rosu"));
            deck.add(new Card("noua", "verde"));
            deck.add(new Card("noua", "bata"));
            deck.add(new Card("noua", "ghinda"));
            deck.add(new Card("zece", "rosu"));
            deck.add(new Card("zece", "verde"));
            deck.add(new Card("zece", "bata"));
            deck.add(new Card("zece", "ghinda"));
        } else if (type.equals("traditional")) {
            deck.add(new Card("as", "rosu"));
            deck.add(new Card("as", "negru"));
            deck.add(new Card("as", "trefla"));
            deck.add(new Card("as", "romb"));
            deck.add(new Card("doi", "rosu"));
            deck.add(new Card("doi", "negru"));
            deck.add(new Card("doi", "trefla"));
            deck.add(new Card("doi", "romb"));
            deck.add(new Card("trei", "rosu"));
            deck.add(new Card("trei", "negru"));
            deck.add(new Card("trei", "trefla"));
            deck.add(new Card("trei", "romb"));
            deck.add(new Card("patru", "rosu"));
            deck.add(new Card("patru", "negru"));
            deck.add(new Card("patru", "trefla"));
            deck.add(new Card("patru", "romb"));
            deck.add(new Card("cinci", "rosu"));
            deck.add(new Card("cinci", "negru"));
            deck.add(new Card("cinci", "trefla"));
            deck.add(new Card("cinci", "romb"));
            deck.add(new Card("sase", "rosu"));
            deck.add(new Card("sase", "negru"));
            deck.add(new Card("sase", "trefla"));
            deck.add(new Card("sase", "romb"));
            deck.add(new Card("sapte", "rosu"));
            deck.add(new Card("sapte", "negru"));
            deck.add(new Card("sapte", "trefla"));
            deck.add(new Card("sapte", "romb"));
            deck.add(new Card("opt", "rosu"));
            deck.add(new Card("opt", "negru"));
            deck.add(new Card("opt", "trefla"));
            deck.add(new Card("opt", "romb"));
            deck.add(new Card("noua", "rosu"));
            deck.add(new Card("noua", "negru"));
            deck.add(new Card("noua", "trefla"));
            deck.add(new Card("noua", "romb"));
            deck.add(new Card("zece", "rosu"));
            deck.add(new Card("zece", "negru"));
            deck.add(new Card("zece", "trefla"));
            deck.add(new Card("zece", "romb"));
            deck.add(new Card("j", "rosu"));
            deck.add(new Card("j", "negru"));
            deck.add(new Card("j", "trefla"));
            deck.add(new Card("j", "romb"));
            deck.add(new Card("q", "rosu"));
            deck.add(new Card("q", "negru"));
            deck.add(new Card("q", "trefla"));
            deck.add(new Card("q", "romb"));
            deck.add(new Card("k", "rosu"));
            deck.add(new Card("k", "negru"));
            deck.add(new Card("k", "trefla"));
            deck.add(new Card("k", "romb"));
        }

        Collections.shuffle(deck);
        for(Card c : deck) {
            Log.i("DECK", c.getCuloare() + " " + c.getNr());
        }
        return deck;
    }

    public String getCuloare() {
        return culoare;
    }

    public String getNr() {
        return nr;
    }
}
