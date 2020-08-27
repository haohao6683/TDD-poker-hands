package com.example;

public enum PokerValue {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("T", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("K", 13),
    ACE("A", 14);

    private final String valueName;
    private final Integer totalValue;

    PokerValue(String valueName, Integer totalValue) {
        this.valueName = valueName;
        this.totalValue = totalValue;
    }

    public String getValueName() {
        return valueName;
    }

    public Integer getTotalValue() {
        return totalValue;
    }
}
