package com.capgemini.poker.sequences.specific;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import com.capgemini.poker.cards.*;
import com.capgemini.poker.sequences.*;

public class FullHouseTest {
	
	@Test
	public void shouldCompareToLowerFullHouse() {
		// given
		Set<Card> fullHouse = new TreeSet<Card>();
		fullHouse.add(new Card(Rank.EIGHT, Suit.HEART));
		fullHouse.add(new Card(Rank.EIGHT, Suit.CLUB));
		fullHouse.add(new Card(Rank.JACK, Suit.HEART));
		fullHouse.add(new Card(Rank.JACK, Suit.CLUB));
		fullHouse.add(new Card(Rank.JACK, Suit.SPADE));
		Sequence fullHouseSequence = new FullHouseSequence(PokerSequence.FULL_HOUSE, fullHouse);
		
		List<Card> otherFullHouse = new ArrayList<Card>();
		otherFullHouse.add(new Card(Rank.TWO, Suit.CLUB));
		otherFullHouse.add(new Card(Rank.TWO, Suit.SPADE));
		otherFullHouse.add(new Card(Rank.FOUR, Suit.HEART));
		otherFullHouse.add(new Card(Rank.FOUR, Suit.DIAMOND));
		otherFullHouse.add(new Card(Rank.FOUR, Suit.CLUB));
		Sequence lowerFullHouse = new FullHouseSequence(PokerSequence.FULL_HOUSE, otherFullHouse);
		// when
		int comparisonResult = fullHouseSequence.compareToSequence(lowerFullHouse);
		// then
		assertTrue(comparisonResult > 0);
	}
	
	@Test
	public void shouldCompareToHigherFullHouse() {
		// given
		Set<Card> fullHouse = new TreeSet<Card>();
		fullHouse.add(new Card(Rank.ACE, Suit.HEART));
		fullHouse.add(new Card(Rank.ACE, Suit.CLUB));
		fullHouse.add(new Card(Rank.THREE, Suit.HEART));
		fullHouse.add(new Card(Rank.THREE, Suit.CLUB));
		fullHouse.add(new Card(Rank.THREE, Suit.SPADE));
		Sequence fullHouseSequence = new FullHouseSequence(PokerSequence.FULL_HOUSE, fullHouse);
		
		List<Card> otherFullHouse = new ArrayList<Card>();
		otherFullHouse.add(new Card(Rank.TWO, Suit.CLUB));
		otherFullHouse.add(new Card(Rank.TWO, Suit.SPADE));
		otherFullHouse.add(new Card(Rank.FOUR, Suit.HEART));
		otherFullHouse.add(new Card(Rank.FOUR, Suit.DIAMOND));
		otherFullHouse.add(new Card(Rank.FOUR, Suit.CLUB));
		Sequence higherFullHouse = new FullHouseSequence(PokerSequence.FULL_HOUSE, otherFullHouse);
		// when
		int comparisonResult = fullHouseSequence.compareToSequence(higherFullHouse);
		// then
		assertTrue(comparisonResult < 0);
	}

}
