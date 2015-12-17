package com.capgemini.poker.sequences;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardSequenceTest {

	@Test
	public void shouldCompareTwoSequencesSecondLower() {
		//given
		CardSequence firstCardSequence = new CardSequence(PokerSequence.FLUSH, null) {		
			@Override
			public int sequenceStrength() {
				return 0;
			}
		};
		CardSequence secondCardSequence = new CardSequence(PokerSequence.PAIR, null) {		
			@Override
			public int sequenceStrength() {
				return 0;
			}
		};
		//when
		int comparisonResult = firstCardSequence.compareToSequence(secondCardSequence);
		//then
		assertTrue(comparisonResult > 0);
	}
	
	@Test
	public void shouldCompareTwoSequencesSecondHigher() {
		//given
		CardSequence firstCardSequence = new CardSequence(PokerSequence.THREE_OF_A_KIND, null) {		
			@Override
			public int sequenceStrength() {
				return 0;
			}
		};
		CardSequence secondCardSequence = new CardSequence(PokerSequence.STRAIGHT, null) {		
			@Override
			public int sequenceStrength() {
				return 0;
			}
		};
		//when
		int comparisonResult = firstCardSequence.compareToSequence(secondCardSequence);
		//then
		assertTrue(comparisonResult < 0);
	}
	
	@Test
	public void shouldCompareTwoEqualSequences() {
		//given
		CardSequence firstCardSequence = new CardSequence(PokerSequence.FOUR_OF_A_KIND, null) {		
			@Override
			public int sequenceStrength() {
				return 0;
			}
		};
		CardSequence secondCardSequence = new CardSequence(PokerSequence.FOUR_OF_A_KIND, null) {		
			@Override
			public int sequenceStrength() {
				return 0;
			}
		};
		//when
		int comparisonResult = firstCardSequence.compareToSequence(secondCardSequence);
		//then
		assertTrue(comparisonResult == 0);
	}
	
	
	@Test
	public void shouldReturnZeroStrengthForNoCards() {
		//given
		Sequence cardSequence = new CardSequence(PokerSequence.HIGH_CARD, null);
		//when
		int strength = cardSequence.sequenceStrength();
		//then
		assertEquals(0, strength);		
	}
}