package com.example.poker;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String id;
    private List pocket = new ArrayList<Card>();
    private int chips;
    private boolean hasFolded = false;

    Player(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<Card> getPocket() {
        return pocket;
    }

    public int getChips() {
        return chips;
    }

    public void setChips() {
        // TODO
    }
}
