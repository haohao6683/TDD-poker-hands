package com.example;

import java.util.*;
import java.util.stream.Collectors;

public class PokerGame {
    public static final String TIED = "Tied";
    private String firstName;
    private String secondName;
    private PokerCardGroup firstGroup;
    private PokerCardGroup secondGroup;

    public PokerCardGroup getFirstGroup() {
        return firstGroup;
    }

    public PokerCardGroup getSecondGroup() {
        return secondGroup;
    }

    public Map<String, List<PokerCard>> parse(String input) {
        Map<String, List<PokerCard>> result = new HashMap<>();
        String[] twoGroup = input.split(" {2}");
        String[] firstGroup = twoGroup[0].split(":");
        String firstGroupName = firstGroup[0];
        firstName = firstGroupName;
        List<PokerCard> firstPokerCards = parsePoker(firstGroup[1]);
        result.put(firstGroupName, firstPokerCards);
        String[] secondGroup = twoGroup[1].split(":");
        String secondGroupName = secondGroup[0];
        secondName = secondGroupName;
        List<PokerCard> secondPokerCards = parsePoker(secondGroup[1]);
        result.put(secondGroupName, secondPokerCards);
        return result;
    }

    public List<PokerCard> parsePoker(String pokers) {
        List<PokerCard> pokerCards = new ArrayList<>();
        String[] pokersArray = pokers.substring(1).split(" ");
        for (String poker : pokersArray) {
            String[] pokerAttr = poker.split("");
            pokerCards.add(new PokerCard(pokerAttr[1], pokerAttr[0]));
        }
        return pokerCards;
    }

    public void initPokerGroups(String input) {
        Map<String, List<PokerCard>> parse = parse(input);
        firstGroup = new PokerCardGroup(parse.get(firstName));
        secondGroup = new PokerCardGroup( parse.get(secondName));
    }



    public String play() {
        if (firstGroup.getType() > secondGroup.getType()) {
            return firstName;
        } else if (firstGroup.getType() < secondGroup.getType()) {
            return secondName;
        } else {
            return compareWithSameType(firstGroup.getType());
        }
    }

    private String compareWithSameType(Integer type) {
        switch (type) {
            case PokerGroupType.HIGH_CARD: {
                if (firstGroup.getHighCard().compareTo(secondGroup.getHighCard()) > 0) {
                    return firstName;
                } else if (firstGroup.getHighCard().compareTo(secondGroup.getHighCard()) < 0) {
                    return secondName;
                }
                return TIED;
            }
            case PokerGroupType.FULL_HOUSE: {
                int firstHighCard = firstGroup.getRepeatingCard().entrySet().stream()
                        .filter(repeating -> repeating.getValue().equals(PokerGroupType.THREE_REPEATING_CARD))
                        .collect(Collectors.toList()).get(0).getKey();
                int secondHighCard = secondGroup.getRepeatingCard().entrySet().stream()
                        .filter(repeating -> repeating.getValue().equals(PokerGroupType.THREE_REPEATING_CARD))
                        .collect(Collectors.toList()).get(0).getKey();
                if (firstHighCard > secondHighCard) {
                    return firstName;
                } else if (firstHighCard < secondHighCard) {
                    return secondName;
                }
                return TIED;
            }
            case PokerGroupType.PAIR: {
                int firstHighCard = firstGroup.getPokerCardList().stream()
                        .filter(eachCard -> !eachCard.getValue().equals(firstGroup.getHighCard().getValue()))
                        .max(PokerCard::compareTo).get().getValue();

                int secondHighCard = secondGroup.getPokerCardList().stream()
                        .filter(eachCard -> !eachCard.getValue().equals(secondGroup.getHighCard().getValue()))
                        .max(PokerCard::compareTo).get().getValue();

                return (firstHighCard > secondHighCard) ? firstName : secondName;
            }
            default: return TIED;
        }
    }
}
