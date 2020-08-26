package com.example;

import java.util.List;

public class PokerCardGroup {
    private List<PokerCard> pokerCardList;
    private PokerCard highCard;

    public PokerCardGroup(List<PokerCard> pokerCardList) {
        this.pokerCardList = pokerCardList;
        findHighCard(pokerCardList);
    }

    public PokerCard getHighCard() {
        return highCard;
    }

    private void findHighCard(List<PokerCard> pokerCardList) {
        pokerCardList.sort(PokerCard::compareTo);
        highCard = pokerCardList.get(pokerCardList.size() - 1);
    }
}
