package com.capgemini.poker.sequences;

import static com.capgemini.poker.helpers.SequenceHelper.reduceToSortedSetByProperty;

import java.util.SortedSet;

import com.capgemini.poker.cards.Card;

public class CardSequence implements Sequence {
	private final PokerSequence sequence;
	private SortedSet<Card> cardsInSequence;
	
	public CardSequence(PokerSequence pokerSequence, SortedSet<Card> cards) {
		this.sequence = pokerSequence;
		this.cardsInSequence = cards;
	}

	@Override
	public int sequenceStrength() {
		SortedSet<Integer> cardRanksSorted = reduceToSortedSetByProperty(cardsInSequence,
				card -> card.getRank().asInt());
		int multiplier = 1;
		final int multiplicationFactor = 15;
		int strength = 0;
		for(Integer cardRankIntValue : cardRanksSorted) {
			strength += cardRankIntValue * multiplier;
			multiplier *= multiplicationFactor;
		}
		return strength;
	};

	@Override
	public SortedSet<Card> getCardsInSequence() {
		return cardsInSequence;
	}

	@Override
	public int compareToSequence(Sequence other) {
		//for performance reasons to avoid unnecessary strength calculation
		if (this.sequence.equals(other.getSequence())) { 
			return this.sequenceStrength() - other.sequenceStrength();
		}
		return this.sequence.asInt() - other.getSequence().asInt();
	}

	@Override
	public PokerSequence getSequence() {
		return sequence;
	}
}