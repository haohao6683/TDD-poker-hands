package com.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PokerTest {
    @Test
    void should_return_1_when_comparePokerValue_given_first_bigger_than_second() {
        //given
        PokerCard pokerCard1 = new PokerCard();
        pokerCard1.setValue("A");
        PokerCard pokerCard2 = new PokerCard();
        pokerCard2.setValue("2");

        //when
        int result = pokerCard1.compareTo(pokerCard2);

        //then
        assertEquals(1, result);
    }

    @Test
    void should_return_1_when_compare_two_pack_of_cards_given_the_first_pack_is_bigger() {
        //given
        List<PokerCard> biggerOne = Arrays.asList(
                new PokerCard("C", "A"),
                new PokerCard("H", "3"),
                new PokerCard("S", "8"),
                new PokerCard("D", "2"),
                new PokerCard("C","4"));

        List<PokerCard> smallerOne = Arrays.asList(
                new PokerCard("C", "2"),
                new PokerCard("H", "3"),
                new PokerCard("S", "4"),
                new PokerCard("D", "8"),
                new PokerCard("C","K"));

        //when
        PokerCardGroup pokerCardGroup1 = new PokerCardGroup(biggerOne);
        PokerCardGroup pokerCardGroup2 = new PokerCardGroup(smallerOne);
        int result = pokerCardGroup1.getHighCard().compareTo(pokerCardGroup2.getHighCard());

        //then
        assertEquals(1, result);
    }
}
