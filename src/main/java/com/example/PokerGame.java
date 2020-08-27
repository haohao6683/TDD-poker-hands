package com.example;

import java.util.*;

public class PokerGame {
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
}
