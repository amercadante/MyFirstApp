package com.example.poker;

public class Card {
    private int rank;
    private Suit suit;

    Card(int rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() {
        return this.rank;
    }

    public Suit getSuit() {
        return this.suit;
    }
}
