package com.capgemini.poker.hands;

import java.util.Collection;

import com.capgemini.poker.cards.Card;
import com.capgemini.poker.sequences.sets.CardGameSequenceSet;
import com.capgemini.poker.sequences.sets.PokerSequenceSet;

public class PokerHand implements CardGameHand {
	private CardGameSequenceSet sequenceSet;
	
	public PokerHand(Collection<Card> cardsOnHand) {
		this.sequenceSet = new PokerSequenceSet(cardsOnHand);
	}

	@Override
	public int compareToHand(CardGameHand other) {
		return sequenceSet.compareToSequenceSet(other.getSequenceSet());
	}
	
	@Override
	public CardGameSequenceSet getSequenceSet() {
		return sequenceSet;
	}
}