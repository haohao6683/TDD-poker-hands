package com.example;

import java.util.List;

public class PokerCardGroup {
    private final List<PokerCard> pokerCardList;
    private PokerCard highCard;
    private boolean isFlush = true;
    private boolean isStraight = true;

    public PokerCardGroup(List<PokerCard> pokerCardList) {
        this.pokerCardList = pokerCardList;
        findHighCard();
        checkFlush();
        checkStraight();
    }

    public PokerCard getHighCard() {
        return highCard;
    }

    public boolean isStraight() {
        return isStraight;
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

    private void checkStraight() {
        for(int i = 0; i < pokerCardList.size() - 1; i++){
            if(!pokerCardList.get(i).getValue().equals(pokerCardList.get(i+1).getValue() - 1) ){
                isStraight = false;
                break;
            }
        }
    }
}
