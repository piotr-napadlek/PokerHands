package com.capgemini.poker.sequences.specific;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import com.capgemini.poker.cards.*;
import com.capgemini.poker.sequences.PokerSequence;
import com.capgemini.poker.sequences.Sequence;
import com.capgemini.poker.sequences.specific.StraightSequence;

public class StraightSequenceTest {

	@Test
	public void shouldCompareWithHigherStraight() {
		// given
		List<Card> straight = new ArrayList<Card>();
		straight.add(new Card(Rank.NINE, Suit.DIAMOND));
		straight.add(new Card(Rank.TEN, Suit.HEART));
		straight.add(new Card(Rank.JACK, Suit.HEART));
		straight.add(new Card(Rank.QUEEN, Suit.HEART));
		straight.add(new Card(Rank.KING, Suit.HEART));
		Sequence straightSequence = new StraightSequence(PokerSequence.STRAIGHT, straight);

		List<Card> otherStraight = new ArrayList<Card>();
		otherStraight.add(new Card(Rank.TEN, Suit.DIAMOND));
		otherStraight.add(new Card(Rank.JACK, Suit.CLUB));
		otherStraight.add(new Card(Rank.QUEEN, Suit.CLUB));
		otherStraight.add(new Card(Rank.KING, Suit.CLUB));
		otherStraight.add(new Card(Rank.ACE, Suit.CLUB));
		Sequence higherStraight = new StraightSequence(PokerSequence.STRAIGHT, otherStraight);
		// when
		int comparisonResult = straightSequence.compareToSequence(higherStraight);
		// then
		assertTrue(comparisonResult < 0);
	}

	@Test
	public void shouldCompareWithLowerStraight() {
		// given
		List<Card> straight = new ArrayList<Card>();
		straight.add(new Card(Rank.NINE, Suit.DIAMOND));
		straight.add(new Card(Rank.TEN, Suit.HEART));
		straight.add(new Card(Rank.JACK, Suit.HEART));
		straight.add(new Card(Rank.QUEEN, Suit.HEART));
		straight.add(new Card(Rank.KING, Suit.HEART));
		Sequence straightSequence = new StraightSequence(PokerSequence.STRAIGHT, straight);

		List<Card> otherStraight = new ArrayList<Card>();
		otherStraight.add(new Card(Rank.TWO, Suit.DIAMOND));
		otherStraight.add(new Card(Rank.THREE, Suit.CLUB));
		otherStraight.add(new Card(Rank.FOUR, Suit.CLUB));
		otherStraight.add(new Card(Rank.FIVE, Suit.CLUB));
		otherStraight.add(new Card(Rank.ACE, Suit.CLUB));
		StraightSequence lowerStraight = new StraightSequence(PokerSequence.STRAIGHT,
				otherStraight);
		// when
		int comparisonResult = straightSequence.compareToSequence(lowerStraight);
		// then
		assertTrue(comparisonResult > 0);
	}

	@Test
	public void shouldCompareSameStraights() {
		// given
		List<Card> straight = new ArrayList<Card>();
		straight.add(new Card(Rank.NINE, Suit.DIAMOND));
		straight.add(new Card(Rank.TEN, Suit.HEART));
		straight.add(new Card(Rank.JACK, Suit.HEART));
		straight.add(new Card(Rank.QUEEN, Suit.HEART));
		straight.add(new Card(Rank.KING, Suit.HEART));
		Sequence straightSequence = new StraightSequence(PokerSequence.STRAIGHT, straight);

		List<Card> otherStraight = new ArrayList<Card>();
		otherStraight.add(new Card(Rank.KING, Suit.SPADE));
		otherStraight.add(new Card(Rank.QUEEN, Suit.CLUB));
		otherStraight.add(new Card(Rank.JACK, Suit.CLUB));
		otherStraight.add(new Card(Rank.TEN, Suit.CLUB));
		otherStraight.add(new Card(Rank.NINE, Suit.CLUB));
		Sequence sameStraight = new StraightSequence(PokerSequence.STRAIGHT, otherStraight);
		// when
		int comparisonResult = straightSequence.compareToSequence(sameStraight);
		// then
		assertTrue(comparisonResult == 0);
	}

}
