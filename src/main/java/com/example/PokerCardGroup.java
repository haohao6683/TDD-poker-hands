package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PokerCardGroup {
    private final List<PokerCard> pokerCardList;
    private PokerCard highCard;
    private boolean isFlush = true;
    private boolean isStraight = true;
    private Map<Integer, Integer> repeatingCard = new HashMap<>();
    private Integer type = PokerGroupType.HIGH_CARD;

    public PokerCardGroup(List<PokerCard> pokerCardList) {
        this.pokerCardList = pokerCardList;
        findHighCard();
        checkFlush();
        checkStraight();
        calculateRepeatCard();
        calculatePokerGroupType();
    }

    public List<PokerCard> getPokerCardList() {
        return pokerCardList;
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

    public Map<Integer, Integer> getRepeatingCard() {
        return repeatingCard;
    }

    public Integer getType() {
        return type;
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
        for (int i = 0; i < pokerCardList.size() - 1; i++) {
            if (!pokerCardList.get(i).getValue().equals(pokerCardList.get(i + 1).getValue() - 1)) {
                isStraight = false;
                break;
            }
        }
    }

    private void calculateRepeatCard() {
        for (PokerCard pokerCard : pokerCardList) {
            if (repeatingCard.containsKey(pokerCard.getValue())) {
                repeatingCard.put(pokerCard.getValue(), repeatingCard.get(pokerCard.getValue()) + 1);
                continue;
            }
            repeatingCard.put(pokerCard.getValue(), 1);
        }
        Map<Integer, Integer> newRepeatCard = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : repeatingCard.entrySet()) {
            if (!entry.getValue().equals(1)) {
                newRepeatCard.put(entry.getKey(), entry.getValue());
            }
        }
        this.repeatingCard = newRepeatCard;
    }

    private void calculatePokerGroupType() {
        if (isStraight && isFlush) {
            type = PokerGroupType.STRAIGHT_AND_FLUSH;
            return;
        }
        if (repeatingCard.containsValue(PokerGroupType.FOUR_REPEATING_CARD)) {
            type = PokerGroupType.FOUR_OF_A_KIND;
            return;
        }
        if (repeatingCard.size() == PokerGroupType.TWO_PACK_REPEATING && repeatingCard.containsValue(PokerGroupType.THREE_REPEATING_CARD)) {
            type = PokerGroupType.FULL_HOUSE;
            return;
        }
        if (isFlush) {
            type = PokerGroupType.FLUSH;
            return;
        }
        if (isStraight) {
            type = PokerGroupType.STRAIGHT;
            return;
        }
        if (repeatingCard.containsValue(PokerGroupType.THREE_REPEATING_CARD) && repeatingCard.size() == PokerGroupType.ONE_PACK_REPEATING) {
            type = PokerGroupType.THREE_OF_A_KIND;
            return;
        }
        if (repeatingCard.size() == PokerGroupType.TWO_PACK_REPEATING && !repeatingCard.containsValue(PokerGroupType.THREE_REPEATING_CARD)) {
            type = PokerGroupType.TWO_PAIRS;
        }
    }
}
