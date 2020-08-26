package com.example;

import org.junit.jupiter.api.Test;

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
}
