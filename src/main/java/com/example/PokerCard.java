package com.example;

public class PokerCard implements Comparable<PokerCard> {
    private String suit;
    private Integer value;

    public PokerCard() {
    }

    public PokerCard(String suit, String value) {
        this.suit = suit;
        this.value = transferValue(value);
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = transferValue(value);
    }

    private Integer transferValue(String value) {
        for (PokerValue pokerValue : PokerValue.values()) {
            if (pokerValue.getValueName().equals(value)) {
                return pokerValue.getTotalValue();
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PokerCard)) {
            return false;
        }
        PokerCard pokerCard = (PokerCard) object;
        return this.suit.equals(pokerCard.suit) && this.value.equals(pokerCard.value);
    }

    @Override
    public int compareTo(PokerCard o) {
        if (this.value > o.getValue()) {
            return 1;
        } else if (this.value.equals(o.getValue())) {
            return 0;
        } else {
            return -1;
        }
    }
}
