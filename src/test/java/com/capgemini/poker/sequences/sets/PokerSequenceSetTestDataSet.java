package com.capgemini.poker.sequences.sets;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.poker.sequences.PokerSequence;

public class PokerSequenceSetTestDataSet {

	public static List<SequenceTestData> getTestData() {
		List<SequenceTestData> testData = new ArrayList<>();
		
		testData.add(SequenceTestData.FluentBuilder()
				.withAddedMultipleCardsToHand("5S 3C 2D TS AH")
				.withExpectedPrimaryPokerSequenceOf(PokerSequence.HIGH_CARD)
				.withANameOf("High card of Ten")
				.build());

		testData.add(SequenceTestData.FluentBuilder()
				.withAddedMultipleCardsToHand("5S 3C 2D 3S AH")
				.withExpectedPrimaryPokerSequenceOf(PokerSequence.PAIR)
				.withRemainingHighestAuxillaryCard("AH")
				.withANameOf("Pair of Threes and high card Ace")
				.build());
		
		testData.add(SequenceTestData.FluentBuilder()
				.withAddedMultipleCardsToHand("5S 3C AD 3S AH")
				.withExpectedPrimaryPokerSequenceOf(PokerSequence.TWO_PAIRS)
				.withRemainingHighestAuxillaryCard("5S")
				.withANameOf("Two pairs (Aces, Threes) and high card Five")
				.build());
		
		testData.add(SequenceTestData.FluentBuilder()
				.withAddedMultipleCardsToHand("TS TD 9S TC JS")
				.withExpectedPrimaryPokerSequenceOf(PokerSequence.THREE_OF_A_KIND)
				.withRemainingHighestAuxillaryCard("JS")
				.withANameOf("Three of a kind (Ten) and high card Jack")
				.build());
		
		testData.add(SequenceTestData.FluentBuilder()
				.withAddedMultipleCardsToHand("AS 2S 3D 5H 4C")
				.withExpectedPrimaryPokerSequenceOf(PokerSequence.STRAIGHT)
				.withANameOf("Straight from lower Ace")
				.build());
		
		testData.add(SequenceTestData.FluentBuilder()
				.withAddedMultipleCardsToHand("AS KS QD TH JC")
				.withExpectedPrimaryPokerSequenceOf(PokerSequence.STRAIGHT)
				.withANameOf("Straight to higher Ace")
				.build());
		
		testData.add(SequenceTestData.FluentBuilder()
				.withAddedMultipleCardsToHand("TS QS 9S 2S JS")
				.withExpectedPrimaryPokerSequenceOf(PokerSequence.FLUSH)
				.withANameOf("Flush of Spades")
				.build());
		
		testData.add(SequenceTestData.FluentBuilder()
				.withAddedMultipleCardsToHand("2C 5C 5H 5D 2S")
				.withExpectedPrimaryPokerSequenceOf(PokerSequence.FULL_HOUSE)
				.withANameOf("Full house with Fives and Twos")
				.build());
		
		testData.add(SequenceTestData.FluentBuilder()
				.withAddedMultipleCardsToHand("2C 5C 5H 5D 5S")
				.withExpectedPrimaryPokerSequenceOf(PokerSequence.FOUR_OF_A_KIND)
				.withRemainingHighestAuxillaryCard("2C")
				.withANameOf("Four of a kind - Fives")
				.build());
		
		testData.add(SequenceTestData.FluentBuilder()
				.withAddedMultipleCardsToHand("4D 5D 7D 8D 6D")
				.withExpectedPrimaryPokerSequenceOf(PokerSequence.STRAIGHT_FLUSH)
				.withANameOf("Straight flush from Four")
				.build());
			
		testData.add(SequenceTestData.FluentBuilder()
				.withAddedMultipleCardsToHand("TH JH QH KH AH")
				.withExpectedPrimaryPokerSequenceOf(PokerSequence.STRAIGHT_FLUSH)
				.withANameOf("ROYAL FLUSH")
				.build());
		
		testData.add(SequenceTestData.FluentBuilder()
				.withAddedMultipleCardsToHand("AS AS AS AS AS")
				.withExpectedExceptionClass(IllegalArgumentException.class)
				.withANameOf("THE ACE OF SPADES! THE ACE OF SPADES!") //sorry I couldn't resist
				.build());

		return testData;
	}
}

