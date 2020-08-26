package com.example;

import java.util.List;

public class PokerCardGroup {
    private final List<PokerCard> pokerCardList;
    private PokerCard highCard;
    private boolean isFlush = true;

    public PokerCardGroup(List<PokerCard> pokerCardList) {
        this.pokerCardList = pokerCardList;
        findHighCard();
        checkFlush();
    }

    public PokerCard getHighCard() {
        return highCard;
    }

    public boolean isFlush() {
        return isFlush;
    }

    private void findHighCard() {
        pokerCardList.sort(PokerCard::compareTo);
        highCard = pokerCardList.get(pokerCardList.size() - 1);
    }

    private void checkFlush() {
        String suit = pokerCardList.get(0).getSuit();
        for (PokerCard pokerCard : pokerCardList) {
            if (!pokerCard.getSuit().equals(suit)) {
                isFlush = false;
                break;
            }
        }
    }
}
