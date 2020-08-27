package com.example;

import org.junit.jupiter.api.Test;

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

    @Test
    void should_return_two_pack_of_repeatCard_when_getRepetitivePokerCard_given_two_same_and_three_same() {
        //given
        List<PokerCard> pokerCards = Arrays.asList(
                new PokerCard("H", "2"),
                new PokerCard("C", "2"),
                new PokerCard("D", "3"),
                new PokerCard("H", "3"),
                new PokerCard("C","3"));
        PokerCardGroup pokerCardGroup = new PokerCardGroup(pokerCards);

        //when
        Map<Integer, Integer> repeatingCard = pokerCardGroup.getRepeatingCard();

        //then
        assertEquals(2, repeatingCard.size());
        assertEquals(2, repeatingCard.get(2));
        assertEquals(3, repeatingCard.get(3));
    }

    @Test
    void should_return_correct_poker_group_type_when_getType_given_straight_and_flush_poker_group() {
        //given
        List<PokerCard> pokerCards = Arrays.asList(
                new PokerCard("H", "2"),
                new PokerCard("H", "3"),
                new PokerCard("H", "4"),
                new PokerCard("H", "5"),
                new PokerCard("H","6"));
        PokerCardGroup pokerCardGroup = new PokerCardGroup(pokerCards);

        //when
        Integer pokerGroupType = pokerCardGroup.getType();

        //then
        assertEquals(PokerGroupType.STRAIGHT_AND_FLUSH, pokerGroupType);
    }

    @Test
    void should_return_correct_poker_group_type_when_getType_given_four_of_a_kind_poker_group() {
        //given
        List<PokerCard> pokerCards = Arrays.asList(
                new PokerCard("H", "2"),
                new PokerCard("C", "3"),
                new PokerCard("D", "2"),
                new PokerCard("H", "2"),
                new PokerCard("S","2"));
        PokerCardGroup pokerCardGroup = new PokerCardGroup(pokerCards);

        //when
        Integer pokerGroupType = pokerCardGroup.getType();

        //then
        assertEquals(PokerGroupType.FOUR_OF_A_KIND, pokerGroupType);
    }

    @Test
    void should_return_correct_poker_group_type_when_getType_given_three_of_a_kind_and_two_of_other_kind_poker_group() {
        //given
        List<PokerCard> pokerCards = Arrays.asList(
                new PokerCard("H", "2"),
                new PokerCard("C", "3"),
                new PokerCard("D", "2"),
                new PokerCard("H", "2"),
                new PokerCard("S","3"));
        PokerCardGroup pokerCardGroup = new PokerCardGroup(pokerCards);

        //when
        Integer pokerGroupType = pokerCardGroup.getType();

        //then
        assertEquals(PokerGroupType.FULL_HOUSE, pokerGroupType);
    }

    @Test
    void should_return_correct_poker_group_type_when_getType_given_flush_poker_group() {
        //given
        List<PokerCard> pokerCards = Arrays.asList(
                new PokerCard("H", "2"),
                new PokerCard("H", "3"),
                new PokerCard("H", "5"),
                new PokerCard("H", "A"),
                new PokerCard("H","T"));
        PokerCardGroup pokerCardGroup = new PokerCardGroup(pokerCards);

        //when
        Integer pokerGroupType = pokerCardGroup.getType();

        //then
        assertEquals(PokerGroupType.FLUSH, pokerGroupType);
    }

    @Test
    void should_return_correct_poker_group_type_when_getType_given_straight_poker_group() {
        //given
        List<PokerCard> pokerCards = Arrays.asList(
                new PokerCard("H", "2"),
                new PokerCard("S", "3"),
                new PokerCard("H", "4"),
                new PokerCard("D", "5"),
                new PokerCard("C","6"));
        PokerCardGroup pokerCardGroup = new PokerCardGroup(pokerCards);

        //when
        Integer pokerGroupType = pokerCardGroup.getType();

        //then
        assertEquals(PokerGroupType.STRAIGHT, pokerGroupType);
    }

    @Test
    void should_return_correct_poker_group_type_when_getType_given_three_of_a_kind_poker_group() {
        //given
        List<PokerCard> pokerCards = Arrays.asList(
                new PokerCard("H", "2"),
                new PokerCard("S", "4"),
                new PokerCard("H", "4"),
                new PokerCard("D", "4"),
                new PokerCard("C","6"));
        PokerCardGroup pokerCardGroup = new PokerCardGroup(pokerCards);

        //when
        Integer pokerGroupType = pokerCardGroup.getType();

        //then
        assertEquals(PokerGroupType.THREE_OF_A_KIND, pokerGroupType);
    }

    @Test
    void should_return_correct_poker_group_type_when_getType_given_two_of_a_kind_and_two_of_other_kind_poker_group() {
        //given
        List<PokerCard> pokerCards = Arrays.asList(
                new PokerCard("H", "2"),
                new PokerCard("S", "4"),
                new PokerCard("H", "4"),
                new PokerCard("D", "2"),
                new PokerCard("C","6"));
        PokerCardGroup pokerCardGroup = new PokerCardGroup(pokerCards);

        //when
        Integer pokerGroupType = pokerCardGroup.getType();

        //then
        assertEquals(PokerGroupType.TWO_PAIRS, pokerGroupType);
    }

    @Test
    void should_return_correct_poker_group_type_when_getType_given_normal_poker_group() {
        //given
        List<PokerCard> pokerCards = Arrays.asList(
                new PokerCard("H", "2"),
                new PokerCard("S", "Q"),
                new PokerCard("H", "4"),
                new PokerCard("D", "7"),
                new PokerCard("C","T"));
        PokerCardGroup pokerCardGroup = new PokerCardGroup(pokerCards);

        //when
        Integer pokerGroupType = pokerCardGroup.getType();

        //then
        assertEquals(PokerGroupType.HIGH_CARD, pokerGroupType);
    }

    @Test
    void should_transfer_string_to_pokerCardGroup_when_initPokerGroups_given_input_string() {
        //given
        List<PokerCard> firstPokerCards = Arrays.asList(
                new PokerCard("H", "2"),
                new PokerCard("D", "3"),
                new PokerCard("S", "5"),
                new PokerCard("C", "9"),
                new PokerCard("D","K"));
        List<PokerCard> secondPokerCards = Arrays.asList(
                new PokerCard("C", "2"),
                new PokerCard("H", "3"),
                new PokerCard("S", "4"),
                new PokerCard("C", "8"),
                new PokerCard("H","A"));
        String input = "Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH";
        PokerGame pokerGame = new PokerGame();

        //when
        pokerGame.initPokerGroups(input);
        List<PokerCard> firstPokerCardList = pokerGame.getFirstGroup().getPokerCardList();
        List<PokerCard> secondPokerCardList = pokerGame.getSecondGroup().getPokerCardList();

        //then
        firstPokerCards.forEach(pokerCard -> assertTrue(firstPokerCardList.contains(pokerCard)));
        secondPokerCards.forEach(pokerCard -> assertTrue(secondPokerCardList.contains(pokerCard)));
    }
}
