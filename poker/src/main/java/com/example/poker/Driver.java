package com.example.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Driver {
    //private static final int DECK_SIZE = 52;
    private List<Card> deck = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private List<Card> board = new ArrayList<>();

    void main(String args[]) {
        buildDeck();

        // dealing
        // betting, repeat...
        // final round, calculate winner

    }

    private void buildDeck() {
        for (Suit s: Suit.values()) {
            for (int i = 2; i < 15; i++) {
                deck.add(new Card(i, s));
            }
        }
        Collections.shuffle(deck);
    }

    private void findWinningHand() {

    }

    // Assuming 5 cards
    private Hand identifyHand(List<Card> hand) {
        List<Card> cards = new ArrayList<>(hand);
        boolean isFlush = false;
        boolean isStraight = false;
        //kinds, straights, flushes
        cards.sort((Card c1, Card c2) -> c1.getRank() - (c2.getRank()));

        // filter into buckets by rank
        Map<Integer, List<Card>> kinds = new HashMap<>();
        for (Card c : cards) {
            int rank = c.getRank();
            kinds.computeIfAbsent(rank, v -> new ArrayList<>()).add(c);
            kinds.get(rank).add(c);
        }

        if (kinds.containsKey(2)) {
            if (kinds.keySet().containsAll(new HashSet<>(2, 2))) {
                return Hand.TWO_PAIR;
            }
            if (kinds.containsKey(3)) {
                return Hand.FULL_HOUSE;
            }
            return Hand.PAIR;
        }
        if (kinds.containsKey(3)) {
            return Hand.THREE_OF_A_KIND;
        }
        if (kinds.containsKey(4)) {
            return Hand.FOUR_OF_A_KIND;
        }

        //straight?
        if (cards.get(0).getRank() - cards.get(4).getRank() == 4) {
            isStraight = true;
        }
        else { // Consider {A = 14, 5, 4, 3, 2}
            if (cards.get(0).getRank() == 14 && cards.get(1).getRank() - 1 == 4) {
                isStraight = true;
            }
        }


        //flush?
        if (cards.get(0).getSuit() == cards.get(1).getSuit() &&
                cards.get(0).getSuit() == cards.get(2).getSuit() &&
                cards.get(0).getSuit() == cards.get(3).getSuit() &&
                cards.get(0).getSuit() == cards.get(4).getSuit()
            ) {
            isFlush = true;
        }

        if (isFlush && isStraight) {
            return Hand.STRAIGHT_FLUSH;
        }
        if (isStraight && !isFlush) {
            return Hand.STRAIGHT;
        }
        if (isFlush && !isStraight) {
            return Hand.FLUSH;
        }

        return Hand.HIGH;
    }
}
