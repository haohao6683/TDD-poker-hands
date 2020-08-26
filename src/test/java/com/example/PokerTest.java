package com.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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

    @Test
    void should_return_true_when_getFlush_given_flush() {
        //given
        List<PokerCard> pokerCards = Arrays.asList(
                new PokerCard("H", "A"),
                new PokerCard("H", "3"),
                new PokerCard("H", "8"),
                new PokerCard("H", "2"),
                new PokerCard("H","4"));
        PokerCardGroup pokerCardGroup = new PokerCardGroup(pokerCards);

        //when
        boolean result = pokerCardGroup.isFlush();

        //then
        assertTrue(result);
    }

    @Test
    void should_return_true_when_getStraight_given_straight() {
        //given
        List<PokerCard> pokerCards = Arrays.asList(
                new PokerCard("H", "T"),
                new PokerCard("C", "J"),
                new PokerCard("S", "Q"),
                new PokerCard("D", "K"),
                new PokerCard("H","A"));
        PokerCardGroup pokerCardGroup = new PokerCardGroup(pokerCards);

        //when
        boolean result = pokerCardGroup.isStraight();

        //then
        assertTrue(result);
    }

    @Test
    void should_return_true_when_getStraightAndFlush_given_straight_and_flush() {
        //given
        List<PokerCard> pokerCards = Arrays.asList(
                new PokerCard("H", "T"),
                new PokerCard("H", "J"),
                new PokerCard("H", "Q"),
                new PokerCard("H", "K"),
                new PokerCard("H","A"));
        PokerCardGroup pokerCardGroup = new PokerCardGroup(pokerCards);

        //when
        boolean result = pokerCardGroup.isStraight() && pokerCardGroup.isFlush();

        //then
        assertTrue(result);
    }

    @Test
    void should_return_two_and_pokerCard_when_getRepetitivePokerCard_given_pair_card() {
        //given
        List<PokerCard> pokerCards = Arrays.asList(
                new PokerCard("H", "2"),
                new PokerCard("C", "2"),
                new PokerCard("D", "4"),
                new PokerCard("H", "3"),
                new PokerCard("C","5"));
        PokerCardGroup pokerCardGroup = new PokerCardGroup(pokerCards);

        //when
        Map<Integer, Integer> repeatingCard = pokerCardGroup.getRepeatingCard();

        //then
        assertEquals(1, repeatingCard.size());
        assertEquals(2, repeatingCard.get(2));
    }

    @Test
    void should_return_3_and_pokerCard_when_getRepetitivePokerCard_given_three_same_card() {
        //given
        List<PokerCard> pokerCards = Arrays.asList(
                new PokerCard("H", "2"),
                new PokerCard("C", "2"),
                new PokerCard("D", "2"),
                new PokerCard("H", "3"),
                new PokerCard("C","5"));
        PokerCardGroup pokerCardGroup = new PokerCardGroup(pokerCards);

        //when
        Map<Integer, Integer> repeatingCard = pokerCardGroup.getRepeatingCard();

        //then
        assertEquals(1, repeatingCard.size());
        assertEquals(3, repeatingCard.get(2));
    }

    @Test
    void should_return_4_and_pokerCard_when_getRepetitivePokerCard_given_four_same_card() {
        //given
        List<PokerCard> pokerCards = Arrays.asList(
                new PokerCard("H", "2"),
                new PokerCard("C", "2"),
                new PokerCard("D", "2"),
                new PokerCard("H", "2"),
                new PokerCard("C","5"));
        PokerCardGroup pokerCardGroup = new PokerCardGroup(pokerCards);

        //when
        Map<Integer, Integer> repeatingCard = pokerCardGroup.getRepeatingCard();

        //then
        assertEquals(1, repeatingCard.size());
        assertEquals(4, repeatingCard.get(2));
    }

    @Test
    void should_return_two_pack_of_repeatCard_when_getRepetitivePokerCard_given_two_pack_same_card() {
        //given
        List<PokerCard> pokerCards = Arrays.asList(
                new PokerCard("H", "2"),
                new PokerCard("C", "2"),
                new PokerCard("D", "3"),
                new PokerCard("H", "3"),
                new PokerCard("C","5"));
        PokerCardGroup pokerCardGroup = new PokerCardGroup(pokerCards);

        //when
        Map<Integer, Integer> repeatingCard = pokerCardGroup.getRepeatingCard();

        //then
        assertEquals(2, repeatingCard.size());
        assertEquals(2, repeatingCard.get(2));
        assertEquals(2, repeatingCard.get(3));
    }
}
